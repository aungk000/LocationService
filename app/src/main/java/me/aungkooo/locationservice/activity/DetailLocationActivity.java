package me.aungkooo.locationservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.aungkooo.locationservice.R;
import me.aungkooo.locationservice.database.MyLocation;
import me.aungkooo.locationservice.model.LocationModel;
import me.aungkooo.locationservice.util.Base;

/**
 * Created by Ko Oo on 8/5/2018.
 */

public class DetailLocationActivity extends Base.Activity
{
    @BindView(R.id.txt_latitude)
    TextView txtLatitude;
    @BindView(R.id.txt_longitude)
    TextView txtLongitude;
    @BindView(R.id.txt_utm)
    TextView txtUtm;
    @BindView(R.id.txt_mgrs)
    TextView txtMgrs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent != null)
        {
            String name = intent.getStringExtra(MyLocation.NAME);
            setToolbarTitle(name);

            LocationModel locationModel = new LocationModel(this);
            long id = intent.getLongExtra(MyLocation.ID, 0);

            try {
                MyLocation location = locationModel.getLocation(id);

                txtLatitude.setText(location.getLatitude());
                txtLongitude.setText(location.getLongitude());
                txtUtm.setText(location.getUtm());
                txtMgrs.setText(location.getMgrs());
            }
            catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.menu_detail_location, menu);
        return super.onCreateOptionsMenu(menu);
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
