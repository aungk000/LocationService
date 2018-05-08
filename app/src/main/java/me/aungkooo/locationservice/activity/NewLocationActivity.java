package me.aungkooo.locationservice.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aungkooo.locationservice.R;
import me.aungkooo.locationservice.database.MyLocation;
import me.aungkooo.locationservice.util.Base;
import me.aungkooo.locationservice.util.LocationService;

/**
 * Created by Ko Oo on 6/5/2018.
 */

public class NewLocationActivity extends Base.Activity implements LocationService.OnLocationChangedListener
{
    @BindView(R.id.edit_latitude)
    TextInputEditText editLatitude;
    @BindView(R.id.edit_longitude)
    TextInputEditText editLongitude;
    @BindView(R.id.edit_mgrs)
    TextInputEditText editMgrs;
    @BindView(R.id.edit_utm)
    TextInputEditText editUtm;
    @BindView(R.id.edit_location_name)
    TextInputEditText editLocationName;

    private LocationService locationService;
    private final int REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location);
        ButterKnife.bind(this);

        locationService = new LocationService(this, REQUEST_LOCATION, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode)
            {
                case REQUEST_LOCATION:
                    locationService.requestLocation();
                    break;
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        locationService.stop();
    }

    @OnClick(R.id.btn_request_location)
    public void onViewClicked() {
        locationService.requestLocation();
    }

    @Override
    public void onLocationChanged(Location location, String utm, String mgrs)
    {
        // Update UI here

        editLatitude.setText(String.valueOf(location.getLatitude()));
        editLongitude.setText(String.valueOf(location.getLongitude()));
        editMgrs.setText(mgrs);
        editUtm.setText(utm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_location, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.menu_done:
                onDone();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onDone()
    {
        Intent data = new Intent();
        data.putExtra(MyLocation.NAME, get(editLocationName));
        data.putExtra(MyLocation.LATITUDE, get(editLatitude));
        data.putExtra(MyLocation.LONGITUDE, get(editLongitude));
        data.putExtra(MyLocation.UTM, get(editUtm));
        data.putExtra(MyLocation.MGRS, get(editMgrs));

        setResult(RESULT_OK, data);
        onBackPressed();
        makeShortToast("Saved");
    }
}
