package com.malousek.appka.UI.Main;

import android.support.v7.util.DiffUtil;

import com.malousek.appka.Data.Classes.Meteorite;

import java.util.List;

/**
 * Created by Jan Malousek on 15.07.2018.
 */
public class MeteoriteDiffCallback extends DiffUtil.Callback {

    private final List<Meteorite> oldList;
    private final List<Meteorite> newList;

    MeteoriteDiffCallback(List<Meteorite> oldList, List<Meteorite> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }


    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId()==newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
