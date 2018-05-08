package me.aungkooo.locationservice.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import me.aungkooo.locationservice.R;
import me.aungkooo.locationservice.database.MyLocation;
import me.aungkooo.locationservice.model.LocationModel;
import me.aungkooo.locationservice.model.LocationRepository;
import me.aungkooo.locationservice.util.Base;
import me.aungkooo.locationservice.view.AnimatedRecyclerView;
import me.aungkooo.locationservice.viewholder.LocationViewHolder;

/**
 * Created by Ko Oo on 6/5/2018.
 */

public class LocationAdapter extends Base.RecyclerAdapter<LocationViewHolder, MyLocation, LocationRepository,
        LocationModel> implements AnimatedRecyclerView.SwipeHelper.OnSwipeListener
{
    public LocationAdapter(Context context, LocationModel model) {
        super(context, model);
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = createView(R.layout.view_location, parent);
        return new LocationViewHolder(view, getContext());
    }

    @Override
    public void onBindViewHolder(final LocationViewHolder holder, int position)
    {
        holder.onBind(getItemList());

        getParentView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getContext(), "" + getItem(holder.getAdapterPosition()).getLocationId(),
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onSwiped(int position)
    {
        final int index = position;
        final MyLocation location = getItem(index);

        removeItem(index);
        getModel().deleteLocation(location);

        Snackbar.make(getParentView(), "Deleted " + location.getLocationName(), Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            getModel().insertLocation(location);
                        } catch (ExecutionException | InterruptedException e) {
                            e.printStackTrace();
                        }

                        addItem(location, index);
                    }
                }).show();
    }
}
