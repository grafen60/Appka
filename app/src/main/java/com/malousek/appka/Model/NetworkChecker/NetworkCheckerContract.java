package com.malousek.appka.Model.NetworkChecker;

/**
 * Created by Jan Malousek on 14.07.2018.
 */
public interface NetworkCheckerContract {
    /**
     * Model methods available to ViewModel
     *      ViewModel calls Models fcn
     */
    interface Model{
        void checkConnection();
        void onCleared();
    }

    /**
     * ViewModel methods available in model
     *      Model calls ViewModels fcn
     */
    interface ModelCallBackListener {
        void connectionResult(int result);
        void onError(Throwable t);
    }
}
