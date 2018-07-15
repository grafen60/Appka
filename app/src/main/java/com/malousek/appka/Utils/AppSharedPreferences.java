package com.malousek.appka.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Jan Malousek on 14.07.2018.
 */
public class AppSharedPreferences {

    private static final AppSharedPreferences mInstance = new AppSharedPreferences();
    private SharedPreferences mSharedPref;

    public static AppSharedPreferences getInstance() {
        return mInstance;
    }

    public void init(Context context){
        mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public long readLongPreference(String key){
        return mSharedPref.getLong(key,0);
    }

    public void writeLongPreference(String key, long value){
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putLong(key, value);
        editor.apply();
    }
}
