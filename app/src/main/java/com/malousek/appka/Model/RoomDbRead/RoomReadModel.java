package com.malousek.appka.Model.RoomDbRead;

import com.malousek.appka.Data.Classes.Meteorite;
import com.malousek.appka.Data.DB.MeteoriteDao;
import com.malousek.appka.Utils.AppkaApplication;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Jan Malousek on 13.07.2018.
 *
 * Model for reading from Room SQLite DB
 */
public class RoomReadModel implements RoomReadContract.Model {

    private final RoomReadContract.ModelCallBackListener mViewModel;

    private CompositeDisposable disposable;

    @Inject
    MeteoriteDao meteoriteDao;

    public RoomReadModel(RoomReadContract.ModelCallBackListener viewModel) {
        this.mViewModel = viewModel;
        AppkaApplication.getAppInstance().getAppComponent().inject(this);
    }

    //public method for ViewModels by which viewModels can asynchronously subscribe to Room SQLite DB
    @Override
    public void subscribeToMeteoritesRoomDB() {
        disposable = new CompositeDisposable();

        disposable.add(meteoriteDao.subscribeToMeteoritesRoomDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<Meteorite>>() {
                    @Override
                    public void onNext(List<Meteorite> meteorites) {
                        mViewModel.updatedDataFromRoom(meteorites);
                    }

                    @Override
                    public void onError(Throwable t) {
                        mViewModel.onError(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }

    @Override
    public void onCleared() {
        if(disposable!= null && !disposable.isDisposed())
            disposable.dispose();
    }
}
