package me.aungkooo.locationservice.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ko Oo on 6/5/2018.
 */

@Entity(tableName = "location")
public class MyLocation
{
    public static final String ID = "location-id";
    public static final String NAME = "location-name";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String UTM = "utm";
    public static final String MGRS = "mgrs";

    @PrimaryKey(autoGenerate = true)
    private long locationId;

    @ColumnInfo(name = "location-name")
    private String locationName;

    @ColumnInfo(name = "latitude")
    private String latitude;

    @ColumnInfo(name = "longitude")
    private String longitude;

    @ColumnInfo(name = "utm")
    private String utm;

    @ColumnInfo(name = "mgrs")
    private String mgrs;

    @Ignore
    public MyLocation(long locationId, String locationName, String latitude, String longitude,
                      String utm, String mgrs) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.utm = utm;
        this.mgrs = mgrs;
    }

    public MyLocation(String locationName, String latitude, String longitude, String utm, String mgrs) {
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.utm = utm;
        this.mgrs = mgrs;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUtm() {
        return utm;
    }

    public void setUtm(String utm) {
        this.utm = utm;
    }

    public String getMgrs() {
        return mgrs;
    }

    public void setMgrs(String mgrs) {
        this.mgrs = mgrs;
    }
}
