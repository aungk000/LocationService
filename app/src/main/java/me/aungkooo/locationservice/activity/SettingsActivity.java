package me.aungkooo.locationservice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import me.aungkooo.locationservice.R;
import me.aungkooo.locationservice.fragment.SettingsFragment;
import me.aungkooo.locationservice.util.Base;

/**
 * Created by Ko Oo on 7/5/2018.
 */

public class SettingsActivity extends Base.Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .add(android.R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
