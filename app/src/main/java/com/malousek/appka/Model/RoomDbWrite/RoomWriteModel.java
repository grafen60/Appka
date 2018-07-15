package com.malousek.appka.Model.RoomDbWrite;

import com.malousek.appka.Data.Classes.Meteorite;
import com.malousek.appka.Data.DB.MeteoriteDao;
import com.malousek.appka.Utils.AppkaApplication;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jan Malousek on 13.07.2018.
 *
 * Model for writing to Room SQLite DB
 */
public class RoomWriteModel implements RoomWriteContract.Model{

    private final RoomWriteContract.ModelCallBackListener mViewModel;

    @Inject
    MeteoriteDao meteoriteDao;

    public RoomWriteModel(RoomWriteContract.ModelCallBackListener viewModel) {
        this.mViewModel = viewModel;
        AppkaApplication.getAppInstance().getAppComponent().inject(this);
    }

    /*
        public method for ViewModels. Input param is a List<Meteorite> with updated data
        previous data saved in Room DB are complete erased before saving new data into Room
     */
    @Override
    public void insertNewData(List<Meteorite> data) {
        Completable completable = Completable.create(e->{
            meteoriteDao.clearRoomDB();
            meteoriteDao.insertNewData(data);
            e.onComplete();
        } )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        CompletableObserver observer = new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                mViewModel.onSuccessRoomUpdate();
            }

            @Override
            public void onError(Throwable e) {
                mViewModel.onError(e);
            }
        };

        completable.subscribe(observer);
    }

    @Override
    public void onCleared() {

    }
}
