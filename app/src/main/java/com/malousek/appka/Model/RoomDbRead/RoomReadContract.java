package com.malousek.appka.Model.RoomDbRead;

import com.malousek.appka.Data.Classes.Meteorite;

import java.util.List;

/**
 * Created by Jan Malousek on 13.07.2018.
 */
public interface RoomReadContract {
    /**
     * Model methods available to ViewModel
     *      ViewModel calls Models fcn
     */
    interface Model{
        void subscribeToMeteoritesRoomDB();
        void onCleared();
    }

    /**
     * ViewModel methods available in model
     *      Model calls ViewModels fcn
     */
    interface ModelCallBackListener {
        void updatedDataFromRoom(List<Meteorite> updatedData);
        void onError(Throwable t);
    }
}
