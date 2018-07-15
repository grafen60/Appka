package com.malousek.appka.Model.NASA_DbReadModel;

import com.malousek.appka.Data.Classes.Meteorite;

import java.util.List;

/**
 * Created by Jan Malousek on 13.07.2018.
 */
public interface NASA_DbReadContract {

    /**
     * Model methods available to ViewModel
     *      ViewModel calls Models fcn
     */
    interface Model{
       void requestNewData();

    }

    /**
     * ViewModel methods available in model
     *      Model calls ViewModel fcn ViewModels fcn
     */
    interface ModelCallBackListener {
       void processDownloadedData(List<Meteorite> data);
       void onError(Throwable t);
    }
}
