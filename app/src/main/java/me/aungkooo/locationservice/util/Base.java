package me.aungkooo.locationservice.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ko Oo on 6/5/2018.
 */

public abstract class Base
{
    public static abstract class Activity extends AppCompatActivity
    {
        public void changeActivityForResult(Class to, int requestCode)
        {
            Intent intent = new Intent(this, to);
            if(intent.resolveActivity(getPackageManager()) != null)
            {
                startActivityForResult(intent, requestCode);
            }
        }

        public void changeActivity(Class to)
        {
            Intent intent = new Intent(this, to);
            if(intent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(intent);
            }
        }

        public void makeShortToast(String message)
        {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        public void makeShortToast(@StringRes int message)
        {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        public void setToolbarTitle(@StringRes int title)
        {
            if(getSupportActionBar() != null)
            {
                getSupportActionBar().setTitle(title);
            }
        }

        public void setToolbarTitle(CharSequence title)
        {
            if(getSupportActionBar() != null)
            {
                getSupportActionBar().setTitle(title);
            }
        }

        public String get(TextInputEditText editText)
        {
            return editText.getText().toString();
        }
    }

    public static abstract class Model<OBJ, REPO extends Base.Repository<OBJ>>
    {
        private REPO repository;
        private List<OBJ> itemList;

        public Model() {
        }

        public REPO getRepository() {
            return repository;
        }

        public void setRepository(REPO repository) {
            this.repository = repository;
            this.itemList = repository.getItemList();
        }

        public List<OBJ> getItemList() {
            return itemList;
        }
    }

    public static abstract class Repository<OBJ>
    {
        private List<OBJ> itemList;

        public Repository() {
        }

        public void setItemList(List<OBJ> itemList) {
            this.itemList = itemList;
        }

        public List<OBJ> getItemList() {
            return itemList;
        }
    }

    public static abstract class RecyclerViewHolder<OBJ> extends RecyclerView.ViewHolder
        implements View.OnClickListener
    {
        private Context context;
        private View view;

        public RecyclerViewHolder(View itemView, Context context) {
            super(itemView);
            this.view = itemView;
            this.context = context;

            itemView.setOnClickListener(this);
        }

        public OBJ getItem(List<OBJ> itemList)
        {
            return itemList.get(getAdapterPosition());
        }

        public Context getContext() {
            return context;
        }

        public View getView() {
            return view;
        }

        public abstract void onBind(List<OBJ> itemList);
    }

    public static abstract class RecyclerAdapter<VH extends RecyclerViewHolder, OBJ,
            REPO extends Base.Repository<OBJ>, MODEL extends Base.Model<OBJ, REPO>>
            extends RecyclerView.Adapter<VH>
    {
        private Context context;
        private List<OBJ> itemList;
        private MODEL model;
        private View parentView;

        public RecyclerAdapter(Context context, MODEL model) {
            this.context = context;
            this.model = model;
            this.itemList = model.getItemList();
        }

        public View createView(@LayoutRes int resource, @Nullable ViewGroup parent)
        {
            parentView = LayoutInflater.from(context).inflate(resource, parent, false);

            return parentView;
        }

        @Override
        public int getItemCount() {
            if(itemList != null)
            {
                return itemList.size();
            }
            else {
                return 0;
            }
        }

        public MODEL getModel() {
            return model;
        }

        public View getParentView() {
            return parentView;
        }

        public OBJ getItem(int index) {
            return itemList.get(index);
        }

        public List<OBJ> getItemList() {
            return itemList;
        }

        public Context getContext() {
            return context;
        }

        public void addItem(OBJ item, int position)
        {
            itemList.add(position, item);
            notifyItemInserted(position);
        }

        public void addItem(OBJ item)
        {
            itemList.add(item);
            notifyDataSetChanged();
        }

        public void removeItem(int position)
        {
            itemList.remove(position);
            notifyItemRemoved(position);
        }

        public void removeItem(OBJ item, int position)
        {
            itemList.remove(item);
            notifyItemRemoved(position);
        }
    }
}
