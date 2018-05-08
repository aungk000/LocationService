package me.aungkooo.locationservice.model;

import android.content.Context;

import java.util.concurrent.ExecutionException;

import me.aungkooo.locationservice.task.GetLocationTask;
import me.aungkooo.locationservice.util.Base;
import me.aungkooo.locationservice.database.LocationDao;
import me.aungkooo.locationservice.database.LocationDatabase;
import me.aungkooo.locationservice.database.MyLocation;
import me.aungkooo.locationservice.task.DeleteLocationTask;
import me.aungkooo.locationservice.task.InsertLocationTask;

/**
 * Created by Ko Oo on 6/5/2018.
 */

public class LocationRepository extends Base.Repository<MyLocation>
{
    private LocationDao locationDao;

    public LocationRepository(Context context) {
        LocationDatabase locationDatabase = LocationDatabase.build(context);
        locationDao = locationDatabase.locationDao();
        setItemList(locationDao.getLocations());
    }

    public long insertLocation(MyLocation location) throws ExecutionException, InterruptedException
    {
        InsertLocationTask insertLocationTask = new InsertLocationTask(locationDao);
        insertLocationTask.execute(location);

        return insertLocationTask.get();
    }

    public void deleteLocation(MyLocation location)
    {
        DeleteLocationTask deleteLocationTask = new DeleteLocationTask(locationDao);
        deleteLocationTask.execute(location);
    }

    public MyLocation getLocation(long id) throws ExecutionException, InterruptedException
    {
        GetLocationTask getLocationTask = new GetLocationTask(locationDao);
        getLocationTask.execute(id);

        return getLocationTask.get();
    }
}
