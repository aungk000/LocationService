package me.aungkooo.locationservice.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import me.aungkooo.locationservice.R;
import me.aungkooo.locationservice.fragment.SettingsFragment;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Ko Oo on 2/5/2018.
 */

public class LocationService implements LocationListener
{
    private LocationManager locationManager;
    private Context context;
    private int requestCode;
    private String permissionAccessFineLocation = Manifest.permission.ACCESS_FINE_LOCATION;
    private String permissionAccessCoarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION;
    private int permissionGranted = PackageManager.PERMISSION_GRANTED;

    private CoordinateConversion coordinateConversion;
    private OnLocationChangedListener listener;
    private String locationProvider;

    public LocationService(Context context, int requestCode, OnLocationChangedListener listener) {
        this.context = context;
        this.requestCode = requestCode;
        this.listener = listener;

        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        coordinateConversion = new CoordinateConversion();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        locationProvider = pref.getString(SettingsFragment.KEY_PROVIDER, LocationManager.GPS_PROVIDER);
    }

    public void stop()
    {
        locationManager.removeUpdates(this);
    }

    public void requestLocation()
    {
        if (ContextCompat.checkSelfPermission(
                context, permissionAccessFineLocation) != permissionGranted &&
                ContextCompat.checkSelfPermission(
                        context, permissionAccessCoarseLocation) != permissionGranted) {
            ActivityCompat.requestPermissions(
                    (Activity) context,
                    new String[]{permissionAccessFineLocation,
                            permissionAccessCoarseLocation},
                    requestCode
            );
        }
        else
        {
            if(locationProvider.equals(LocationManager.NETWORK_PROVIDER)
                    && !Util.isNetworkAvailable(context))
            {
                Toast.makeText(context, "No network connection", Toast.LENGTH_SHORT).show();
            }
            else {
                locationManager.requestLocationUpdates(locationProvider, 15000, 10, this);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location)
    {
        if (ContextCompat.checkSelfPermission(
                context, permissionAccessFineLocation) != permissionGranted &&
                ContextCompat.checkSelfPermission(
                        context, permissionAccessCoarseLocation) != permissionGranted) {
            ActivityCompat.requestPermissions(
                    (Activity) context,
                    new String[]{permissionAccessFineLocation,
                            permissionAccessCoarseLocation},
                    requestCode
            );
        }
        else
        {
            if (location == null)
            {
                location = locationManager.getLastKnownLocation(locationProvider);
            }

            String mgrs = coordinateConversion
                    .latLon2MGRUTM(location.getLatitude(), location.getLongitude());

            String utm = coordinateConversion.latLon2UTM(location.getLatitude(), location.getLongitude());

            listener.onLocationChanged(location, utm, mgrs);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {
        context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    public interface OnLocationChangedListener
    {
        void onLocationChanged(Location location, String utm, String mgrs);
    }
}
