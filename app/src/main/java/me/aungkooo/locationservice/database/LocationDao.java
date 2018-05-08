package me.aungkooo.locationservice.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ko Oo on 6/5/2018.
 */

@Dao
public interface LocationDao
{
    @Query("SELECT * FROM location")
    List<MyLocation> getLocations();

    @Query("SELECT * FROM location WHERE locationId = :id")
    MyLocation getLocation(long id);

    @Insert
    long insertLocation(MyLocation location);

    @Delete
    void deleteLocation(MyLocation location);

    @Query("DELETE FROM location WHERE locationId = :id")
    void deleteLocation(long id);

    @Query("DELETE FROM location")
    void deleteLocations();
}
