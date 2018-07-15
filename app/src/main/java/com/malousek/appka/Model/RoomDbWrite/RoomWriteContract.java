package com.malousek.appka.Model.RoomDbWrite;

import com.malousek.appka.Data.Classes.Meteorite;

import java.util.List;

/**
 * Created by Jan Malousek on 13.07.2018.
 */
public interface RoomWriteContract {
    /**
     * Model methods available to ViewModel
     *      ViewModel calls Models fcn
     */
    interface Model{
        void insertNewData(List<Meteorite> data);
        void onCleared();
    }

    /**
     * ViewModel methods available in model
     *      Model calls ViewModels fcn
     */
    interface ModelCallBackListener {
        void onError(Throwable t);
        void onSuccessRoomUpdate();
    }
}
