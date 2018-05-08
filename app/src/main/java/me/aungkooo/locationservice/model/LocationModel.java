package me.aungkooo.locationservice.model;

import android.content.Context;

import java.util.concurrent.ExecutionException;

import me.aungkooo.locationservice.task.LoadLocationTask;
import me.aungkooo.locationservice.util.Base;
import me.aungkooo.locationservice.database.MyLocation;

/**
 * Created by Ko Oo on 6/5/2018.
 */

public class LocationModel extends Base.Model<MyLocation, LocationRepository>
{
    public LocationModel(Context context)
    {
        LoadLocationTask loadLocationTask = new LoadLocationTask();
        loadLocationTask.execute(context);
        try {
            setRepository(loadLocationTask.get());
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public long insertLocation(MyLocation location) throws ExecutionException, InterruptedException
    {
        return getRepository().insertLocation(location);
    }

    public void deleteLocation(MyLocation location)
    {
        getRepository().deleteLocation(location);
    }

    public MyLocation getLocation(long id) throws ExecutionException, InterruptedException {
        return getRepository().getLocation(id);
    }
}
