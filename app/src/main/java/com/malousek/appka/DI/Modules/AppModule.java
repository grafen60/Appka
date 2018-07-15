package com.malousek.appka.DI.Modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.malousek.appka.Data.DB.MeteoriteDao;
import com.malousek.appka.Data.DB.RoomDB;
import com.malousek.appka.Utils.AppkaApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jan Malousek on 13.07.2018.
 */

@Module
public class AppModule {

    private final AppkaApplication mApplication;

    public AppModule(AppkaApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    Application providesApplication(){
        return mApplication;
    }

    @Provides
    Context providesContext(){
        return mApplication;
    }

    @Provides
    @Singleton
    RoomDB provideMeteoriteDB(Context context){
        return Room.databaseBuilder(context, RoomDB.class, "MeteoritesDB")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    MeteoriteDao provideMeteoriteDao(RoomDB db){
        return db.meteoriteDao();
    }
}
