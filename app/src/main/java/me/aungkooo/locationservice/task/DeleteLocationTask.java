package me.aungkooo.locationservice.task;

import android.os.AsyncTask;

import me.aungkooo.locationservice.database.LocationDao;
import me.aungkooo.locationservice.database.MyLocation;

/**
 * Created by User on 6/5/2018.
 */

public class DeleteLocationTask extends AsyncTask<MyLocation, Void, Void>
{
    private LocationDao locationDao;

    public DeleteLocationTask(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    protected Void doInBackground(MyLocation... myLocations)
    {
        locationDao.deleteLocation(myLocations[0]);
        return null;
    }
}
