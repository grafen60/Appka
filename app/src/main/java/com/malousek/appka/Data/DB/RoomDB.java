package com.malousek.appka.Data.DB;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.malousek.appka.Data.Classes.Meteorite;

/**
 * Created by Jan Malousek on 13.07.2018.
 */

@Database(entities = {Meteorite.class},version = 1)
public abstract class RoomDB extends RoomDatabase {
    public abstract MeteoriteDao meteoriteDao();
}
