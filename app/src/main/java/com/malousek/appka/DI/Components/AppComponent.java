package com.malousek.appka.DI.Components;

import com.malousek.appka.DI.Modules.AppModule;
import com.malousek.appka.Model.NetworkChecker.NetworkCheckerModel;
import com.malousek.appka.Model.RoomDbRead.RoomReadContract;
import com.malousek.appka.Model.RoomDbRead.RoomReadModel;
import com.malousek.appka.Model.RoomDbWrite.RoomWriteModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jan Malousek on 13.07.2018.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(RoomWriteModel roomWriteModel);
    void inject(RoomReadModel roomReadModel);
    void inject(NetworkCheckerModel networkCheckerModel);
}
