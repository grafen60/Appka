package com.malousek.appka.Utils;


import com.malousek.appka.DI.Components.AppComponent;
import com.malousek.appka.DI.Components.DaggerAppComponent;
import com.malousek.appka.DI.Modules.AppModule;

/**
 * Created by Jan Malousek on 13.07.2018.
 */
public class AppkaApplication extends android.app.Application {

    private static AppkaApplication instance;

    private AppComponent appComponent;

    public static AppkaApplication getAppInstance(){
        return instance;
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();


        //init appSharedPreferencesFile
        AppSharedPreferences.getInstance().init(this);
    }
}
