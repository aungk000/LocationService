package me.aungkooo.locationservice.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.aungkooo.locationservice.R;
import me.aungkooo.locationservice.activity.DetailLocationActivity;
import me.aungkooo.locationservice.database.MyLocation;
import me.aungkooo.locationservice.util.Base;

/**
 * Created by Ko Oo on 6/5/2018.
 */

public class LocationViewHolder extends Base.RecyclerViewHolder<MyLocation>
{
    @BindView(R.id.txt_location_name)
    TextView txtLocationName;
    @BindView(R.id.txt_utm)
    TextView txtUtm;

    private MyLocation location;

    public LocationViewHolder(View itemView, Context context) {
        super(itemView, context);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(List<MyLocation> itemList) {
        location = getItem(itemList);
        txtLocationName.setText(location.getLocationName());
        txtUtm.setText(location.getUtm());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), DetailLocationActivity.class);
        intent.putExtra(MyLocation.ID, location.getLocationId());
        intent.putExtra(MyLocation.NAME, location.getLocationName());
        if(intent.resolveActivity(getContext().getPackageManager()) != null)
        {
            getContext().startActivity(intent);
        }
    }
}
