package com.malousek.appka.UI.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.malousek.appka.Data.Classes.Meteorite;
import com.malousek.appka.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan Malousek on 15.07.2018.
 *
 * Adapter for recyclerView in Main Activity
 */
public class MeteoriteAdapter extends RecyclerView.Adapter<MeteoriteAdapter.MeteoriteViewHolder> {

    public interface OnItemClickListener {
        void onItemClicked(Meteorite clickedItem);
    }

    private List<Meteorite> meteoriteList;
    private final OnItemClickListener listener;
    private final Context mContext;


    MeteoriteAdapter(OnItemClickListener listener, Context mContext) {
        this.listener = listener;
        this.mContext = mContext;
        meteoriteList = new ArrayList<>();
    }

    void updateList(List<Meteorite> newMeteoriteList){
        final MeteoriteDiffCallback diffCallBack = new MeteoriteDiffCallback(this.meteoriteList,newMeteoriteList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallBack);

        this.meteoriteList.clear();
        this.meteoriteList.addAll(newMeteoriteList);

        diffResult.dispatchUpdatesTo(this);
    }


    @NonNull
    @Override
    public MeteoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_meteorite,null);

        return new MeteoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeteoriteViewHolder holder, int position) {
        holder.bind(meteoriteList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return meteoriteList.size();
    }

    class MeteoriteViewHolder extends RecyclerView.ViewHolder {
        TextView meteoriteNameTextView;

        MeteoriteViewHolder(View itemView) {
            super(itemView);
            this.meteoriteNameTextView = itemView.findViewById(R.id.textViewInMeteoriteItem);
        }

        void bind(final Meteorite item, final OnItemClickListener listener){
            meteoriteNameTextView.setText(item.getName());
            itemView.setOnClickListener(view -> listener.onItemClicked(item));
        }
    }
}
