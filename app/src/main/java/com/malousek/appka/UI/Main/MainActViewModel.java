package com.malousek.appka.UI.Main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.malousek.appka.Data.Classes.Meteorite;
import com.malousek.appka.Model.NASA_DbReadModel.NASA_DbReadContract;
import com.malousek.appka.Model.NASA_DbReadModel.NASA_DbReadModel;
import com.malousek.appka.Model.NetworkChecker.NetworkCheckerContract;
import com.malousek.appka.Model.NetworkChecker.NetworkCheckerModel;
import com.malousek.appka.Model.RoomDbRead.RoomReadContract;
import com.malousek.appka.Model.RoomDbRead.RoomReadModel;
import com.malousek.appka.Model.RoomDbWrite.RoomWriteContract;
import com.malousek.appka.Model.RoomDbWrite.RoomWriteModel;
import com.malousek.appka.Utils.AppConstants;
import com.malousek.appka.Utils.AppSharedPreferences;

import java.util.List;

/**
 * Created by Jan Malousek on 13.07.2018.
 *
 * ViewModel of MainActivity. It provides data for MainActivity by requesting them from RoomReadModel,
 * and updates data from NASA DB
 */
public class MainActViewModel extends ViewModel implements NASA_DbReadContract.ModelCallBackListener,
        RoomWriteContract.ModelCallBackListener, RoomReadContract.ModelCallBackListener,
        NetworkCheckerContract.ModelCallBackListener {

    private static final String TAG = "MainActViewModel";

    private final NASA_DbReadContract.Model nasaDBModel;
    private final RoomReadContract.Model roomReadModel;
    private final RoomWriteContract.Model roomWriteModel;
    private final NetworkCheckerContract.Model networkChecker;

    private MutableLiveData<List<Meteorite>> dataSet = new MutableLiveData<>();

    public MainActViewModel() {
        nasaDBModel = new NASA_DbReadModel(this);
        roomReadModel = new RoomReadModel(this);
        roomWriteModel = new RoomWriteModel(this);
        networkChecker = new NetworkCheckerModel(this);

        roomReadModel.subscribeToMeteoritesRoomDB();
    }

    public MutableLiveData<List<Meteorite>> getDataSet() {
        return dataSet;
    }

    /*
        public method for MainActivity which is used for subscribing to Room SQLite DB
        If last update is more then 1 day old, it requests new data from NASA
     */
    public void requestDataForUI() {
        long lastUpdateTime = AppSharedPreferences
                .getInstance()
                .readLongPreference(AppConstants.SHARED_PREF_LAST_TIME_UPDATE);

        roomReadModel.subscribeToMeteoritesRoomDB();

        if((System.currentTimeMillis()-24*3600*1000)>lastUpdateTime)
            networkChecker.checkConnection();
    }

    //connection result from NetworkCheckerModel
    @Override
    public void connectionResult(int result) {
        if(result==AppConstants.SUCCESS_RESULT)
            nasaDBModel.requestNewData();
    }

    //Downloaded data from NASA_DbReadModel
    @Override
    public void processDownloadedData(List<Meteorite> data) {
        roomWriteModel.insertNewData(data);
    }

    /*
        Data from Room SQLite DB, this method is called, whenever there is a change in data saved in Room.
        Updated data are asynchronously provided for UI via LiveData object dataSet
     */
    @Override
    public void updatedDataFromRoom(List<Meteorite> updatedData) {
        //for UI
        dataSet.postValue(updatedData);
    }


    @Override
    public void onError(Throwable t) {
        Log.e(TAG, "onError: ",t.getCause());
    }

    /*
        After successful update of data in Room DB, time of this updated is saved in shared pref, so
        the app won't requests update from NASA for another 24 hours.
     */
    @Override
    public void onSuccessRoomUpdate() {
        AppSharedPreferences
                .getInstance()
                .writeLongPreference(AppConstants.SHARED_PREF_LAST_TIME_UPDATE,System.currentTimeMillis());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        networkChecker.onCleared();
        roomReadModel.onCleared();
        roomWriteModel.onCleared();
    }
}
