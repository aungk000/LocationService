package me.aungkooo.locationservice.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;

/**
 * Created by Ko Oo on 8/5/2018.
 */

public class AnimatedRecyclerView extends RecyclerView
{

    public AnimatedRecyclerView(Context context) {
        super(context);
    }

    public AnimatedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void animateFabOnScrolled(final FloatingActionButton fab)
    {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState > 0)
                {
                    fab.hide();
                }
                else {
                    fab.show();
                }
            }
        });
    }

    public void setSwipeable(SwipeHelper.OnSwipeListener listener)
    {
        SwipeHelper.SwipeCallback callback = new SwipeHelper.SwipeCallback(listener);
        SwipeHelper swipeHelper = new SwipeHelper(callback);
        swipeHelper.attachToRecyclerView(this);
    }

    public static class SwipeHelper extends ItemTouchHelper
    {
        public SwipeHelper(ItemTouchHelper.Callback callback) {
            super(callback);
        }

        public static class SwipeCallback extends ItemTouchHelper.Callback
        {
            private OnSwipeListener listener;

            public SwipeCallback(OnSwipeListener listener) {
                this.listener = listener;
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if(recyclerView.getLayoutManager() instanceof LinearLayoutManager)
                {
                    int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

                    return makeMovementFlags(0, swipeFlags);
                }
                else {
                    return 0;
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                listener.onSwiped(viewHolder.getAdapterPosition());
            }
        }

        public interface OnSwipeListener {
            void onSwiped(int position);
        }
    }
}
