package me.aungkooo.locationservice.task;

import android.os.AsyncTask;

import me.aungkooo.locationservice.database.LocationDao;
import me.aungkooo.locationservice.database.MyLocation;

/**
 * Created by Ko Oo on 6/5/2018.
 */

public class InsertLocationTask extends AsyncTask<MyLocation, Void, Long>
{
    private LocationDao locationDao;

    public InsertLocationTask(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    protected Long doInBackground(MyLocation... myLocations)
    {
        return locationDao.insertLocation(myLocations[0]);
    }
}
