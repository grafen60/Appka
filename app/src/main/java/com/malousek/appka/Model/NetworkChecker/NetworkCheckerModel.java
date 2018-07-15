package com.malousek.appka.Model.NetworkChecker;

import android.content.Context;
import android.net.ConnectivityManager;

import com.malousek.appka.Utils.AppConstants;
import com.malousek.appka.Utils.AppkaApplication;

import java.net.InetAddress;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jan Malousek on 14.07.2018.
 *
 * Class for checking internet connection
 */
public class NetworkCheckerModel implements NetworkCheckerContract.Model {

    private final NetworkCheckerContract.ModelCallBackListener mViewModel;

    private DisposableSingleObserver<Boolean> connectionObserver;

    @Inject
    Context mContext;

    public NetworkCheckerModel(NetworkCheckerContract.ModelCallBackListener mViewModel) {
        this.mViewModel = mViewModel;
        AppkaApplication.getAppInstance().getAppComponent().inject(this);
    }


    //public method for ViewModel, which asynchronously returns connection status as boolean value (internet is/isn't available)
    @Override
    public void checkConnection() {
        Single<Boolean> connectionCompletable = createConnectionObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        connectionObserver = new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean isInternetAvailable) {
                if (isInternetAvailable)
                    mViewModel.connectionResult(AppConstants.SUCCESS_RESULT);
                else
                    mViewModel.connectionResult(AppConstants.FAILURE_RESULT);
            }

            @Override
            public void onError(Throwable e) {
                mViewModel.connectionResult(AppConstants.FAILURE_RESULT);
            }
        };

        connectionCompletable.subscribe(connectionObserver);
    }

    private Single<Boolean> createConnectionObservable() {
        return Single.create(e-> e.onSuccess(isNetworkConnected()&&isInternetAvailable()));
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null;
    }

    private boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onCleared() {
        if(connectionObserver!= null && !connectionObserver.isDisposed())
            connectionObserver.dispose();
    }
}
