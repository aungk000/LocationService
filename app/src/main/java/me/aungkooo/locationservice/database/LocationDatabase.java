package me.aungkooo.locationservice.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Ko Oo on 6/5/2018.
 */

@Database(entities = {MyLocation.class}, version = 1, exportSchema = false)
public abstract class LocationDatabase extends RoomDatabase
{
    public abstract LocationDao locationDao();

    @NonNull
    public static LocationDatabase build(Context context)
    {
        return Room.databaseBuilder(context, LocationDatabase.class, "location-database")
                .build();
    }
}
