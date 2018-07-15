package com.malousek.appka.UI.Main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.malousek.appka.Data.Classes.Meteorite;
import com.malousek.appka.R;
import com.malousek.appka.UI.MeteorDetail.MeteorDetailActivity;
import com.malousek.appka.Utils.AppConstants;

/**
 * Created by Jan Malousek on 13.07.2018.
 *
 * MainActivity displays main information about meteorite landings which includes number and list of meteorites landed on earth from 2011 till now.
 */

public class MainActivity extends AppCompatActivity implements MeteoriteAdapter.OnItemClickListener{

    MainActViewModel mViewModel;

    TextView meteoritesTextView;
    RecyclerView recyclerView;
    MeteoriteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainActViewModel.class);
        mViewModel.requestDataForUI();
        initUI();
        observe();
    }

    private void initUI() {
        meteoritesTextView = findViewById(R.id.NoMeteoritesTextView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new MeteoriteAdapter(this,this);
        recyclerView.setAdapter(adapter);

    }

    //method for subscribing to LiveData in ViewModel
    private void observe() {
        mViewModel.getDataSet().observe(this, meteorites -> {
            //do with it something
            if(meteorites==null)
                return;

            meteoritesTextView.setText(getString(R.string.NumberOfMeteoritesMSG,meteorites.size()));
            adapter.updateList(meteorites);

        });
    }


    @Override
    public void onItemClicked(Meteorite clickedItem) {
        Intent intent = new Intent(MainActivity.this, MeteorDetailActivity.class);
        intent.putExtra(AppConstants.METEORITE_CLASS,clickedItem);

        startActivity(intent);
    }
}
