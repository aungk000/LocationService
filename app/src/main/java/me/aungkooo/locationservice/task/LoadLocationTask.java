package me.aungkooo.locationservice.task;

import android.content.Context;
import android.os.AsyncTask;

import me.aungkooo.locationservice.model.LocationRepository;

/**
 * Created by User on 7/5/2018.
 */

public class LoadLocationTask extends AsyncTask<Context, Void, LocationRepository>
{
    public LoadLocationTask() {
    }

    @Override
    protected LocationRepository doInBackground(Context... contexts) {
        return new LocationRepository(contexts[0]);
    }

}
