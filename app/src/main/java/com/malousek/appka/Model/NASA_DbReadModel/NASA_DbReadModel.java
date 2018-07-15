package com.malousek.appka.Model.NASA_DbReadModel;

import android.support.annotation.NonNull;
import android.util.Log;

import com.malousek.appka.Data.Classes.Meteorite;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jan Malousek on 13.07.2018.
 *
 * Model for accessing NASA meteorite database
 */
public class NASA_DbReadModel implements NASA_DbReadContract.Model{

    private static final String TAG = "NASA_DbReadModel";

    //reference to ViewModel
    private NASA_DbReadContract.ModelCallBackListener mViewModel;

    public NASA_DbReadModel(NASA_DbReadContract.ModelCallBackListener viewModel) {
        this.mViewModel = viewModel;
    }

    //public method for ViewModels which returns downloaded data as List<Meteorites>
    @Override
    public void requestNewData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Meteorite>> call = api.getMeteorites();

        call.enqueue(new retrofit2.Callback<List<Meteorite>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meteorite>> call, @NonNull retrofit2.Response<List<Meteorite>> response) {
                mViewModel.processDownloadedData(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Meteorite>> call, @NonNull Throwable t) {
                mViewModel.onError(t);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
