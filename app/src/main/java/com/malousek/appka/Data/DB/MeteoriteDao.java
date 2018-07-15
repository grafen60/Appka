package com.malousek.appka.Data.DB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.malousek.appka.Data.Classes.Meteorite;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Jan Malousek on 13.07.2018.
 *
 * Data access object for Room SQLite database
 */

@Dao
public interface MeteoriteDao {
    @Query("SELECT * FROM Meteorites ORDER BY mass desc")
    Flowable<List<Meteorite>> subscribeToMeteoritesRoomDB();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewData(List<Meteorite> meteorite);

    @Query("DELETE FROM Meteorites")
    void clearRoomDB();
}
