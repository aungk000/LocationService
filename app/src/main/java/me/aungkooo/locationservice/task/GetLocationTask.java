package me.aungkooo.locationservice.task;

import android.os.AsyncTask;

import me.aungkooo.locationservice.database.LocationDao;
import me.aungkooo.locationservice.database.MyLocation;

/**
 * Created by Ko Oo on 8/5/2018.
 */

public class GetLocationTask extends AsyncTask<Long, Void, MyLocation>
{
    private LocationDao locationDao;

    public GetLocationTask(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    protected MyLocation doInBackground(Long... longs) {
        return locationDao.getLocation(longs[0]);
    }
}
