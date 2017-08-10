package com.singh.vinay.mymeterial.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.singh.vinay.mymeterial.R;
import com.singh.vinay.mymeterial.model.DrawerItem;

import java.util.ArrayList;

/**
 * Created by root on 5/4/16.
 */
public class DrawerMenuAdapter extends BaseAdapter {

    Context context;
    private ArrayList<DrawerItem> navDrawerItems;
    public DrawerMenuAdapter(Context context, ArrayList<DrawerItem> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        RelativeLayout rel = (RelativeLayout) convertView.findViewById(R.id.ll);
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        return convertView;

        // displaying count
        // check whether it set visible or not

    }
}
