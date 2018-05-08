package me.aungkooo.locationservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aungkooo.locationservice.database.MyLocation;
import me.aungkooo.locationservice.util.Base;
import me.aungkooo.locationservice.R;
import me.aungkooo.locationservice.adapter.LocationAdapter;
import me.aungkooo.locationservice.model.LocationModel;
import me.aungkooo.locationservice.view.AnimatedRecyclerView;

public class MainActivity extends Base.Activity
{
    @BindView(R.id.recycler_view_main)
    AnimatedRecyclerView recyclerViewMain;
    @BindView(R.id.fab_main)
    FloatingActionButton fabMain;

    private LocationModel locationModel;
    private LocationAdapter locationAdapter;
    private final int REQUEST_ACTIVITY = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        locationModel = new LocationModel(this);
        locationAdapter = new LocationAdapter(this, locationModel);

        recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMain.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewMain.setAdapter(locationAdapter);
        recyclerViewMain.animateFabOnScrolled(fabMain);
        recyclerViewMain.setSwipeable(locationAdapter);
    }

    @OnClick(R.id.fab_main)
    public void onViewClicked() {
        changeActivityForResult(NewLocationActivity.class, REQUEST_ACTIVITY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.menu_settings:
                changeActivity(SettingsActivity.class);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case REQUEST_ACTIVITY:
                    addNewLocation(data);
                    break;
            }
        }
    }

    public void addNewLocation(Intent data)
    {
        String name = data.getStringExtra(MyLocation.NAME);
        String latitude = data.getStringExtra(MyLocation.LATITUDE);
        String longitude = data.getStringExtra(MyLocation.LONGITUDE);
        String utm = data.getStringExtra(MyLocation.UTM);
        String mgrs = data.getStringExtra(MyLocation.MGRS);

        try {
            MyLocation location = new MyLocation(name, latitude, longitude, utm, mgrs);
            long id = locationModel.insertLocation(location);

            locationAdapter.addItem(new MyLocation(id, name, latitude, longitude, utm, mgrs));
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
