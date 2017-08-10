package com.singh.vinay.mymeterial.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.singh.vinay.mymeterial.R;
import com.singh.vinay.mymeterial.activities.Login;
import com.singh.vinay.mymeterial.activities.SlidingMenuActvity;
import com.singh.vinay.mymeterial.utils.Constants;
import com.singh.vinay.mymeterial.utils.SlidingMenuItem;

import java.util.ArrayList;

/**
 * Created by proloy on 1/7/16.
 */
public class SlidingMenuFragment extends CommonBaseFragment  {

    private final static String TAG = "SlidingMenuFragment";
    SlidingMenuItem item;
    int mSelectedItem, dotpos;
    SlidingMenuAdapter adapter;
    int last_position;
    boolean[] mVisisbilityList = {false, false, false, false, false, false, false, false, false, false};
    String from_booking;
    private View rootView;
    private ListView listView;
    private ArrayList<SlidingMenuItem> listMenuItems;
    private Dialog dialog;
    private String log_InOutText = "";
    public static Fragment newInstance() {
        SlidingMenuFragment fragment = new SlidingMenuFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        set_view_method();
    }
    private void set_view_method() {
        listMenuItems = new ArrayList<SlidingMenuItem>();
        listMenuItems.add(new SlidingMenuItem("Home"));
        listMenuItems.add(new SlidingMenuItem("Featured Listing"));
        listMenuItems.add(new SlidingMenuItem("ThirstyBuddy Content"));
        listMenuItems.add(new SlidingMenuItem("Upcoming Events"));
        listMenuItems.add(new SlidingMenuItem("Directory"));
        listMenuItems.add(new SlidingMenuItem("My Bookings"));
        listMenuItems.add(new SlidingMenuItem("Following"));
        listMenuItems.add(new SlidingMenuItem("Messages"));
        listMenuItems.add(new SlidingMenuItem("Account Settings"));

        if (!prefs.getStringValueForTag(Constants.API_KEY).equalsIgnoreCase("")) {
            log_InOutText = "Log Out";
            listMenuItems.add(new SlidingMenuItem(log_InOutText));
        } else {
            log_InOutText = "Log In / SignUp";
            listMenuItems.add(new SlidingMenuItem(log_InOutText));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        listView = (ListView) rootView.findViewById(R.id.list);
        ((SlidingMenuActvity) getActivity()).transactionFragments(Home_fragmets.newInstance(), false, R.id.container, false, "DirectoryFragment");
        setThingsInMenu();
        Intent intent = getActivity().getIntent();
        if(intent!=null) {
            from_booking = intent.getStringExtra("FROM_BOOKING_Detail");
            if (from_booking != null) {
                ((SlidingMenuActvity) getActivity()).transactionFragments(Home_fragmets.newInstance(), true, R.id.container, true, "MessageFragment");
            }
        }
        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListViewAdapter();
    }
    @Override
    public void onResume() {
        set_view_method();
        setListViewAdapter();
        super.onResume();
        String userName = "";
        String userImage = "";
        userName = prefs.getStringValueForTag(Constants.USER_NAME);
        userImage = prefs.getStringValueForTag(Constants.USER_IMAGE);
        setDrawerUserName(userName);
        setDrawerUserImage(userImage);
        setThingsInMenu();
    }
    public void setDrawerUserImage(String userImage) {
       final ImageView pro = (ImageView) rootView.findViewById(R.id.user_image);
        if (userImage != null && !userImage.equalsIgnoreCase("")) {
         /* Picasso.with(SlidingMenuFragment.this.getActivity())
                    .load(userImage).transform(new CircleTransform())
                    .placeholder(R.drawable.largeimg)
                    .fit()
                    .error(R.drawable.largeimg)
                    .into(pro);*/
    }
        }
    public void setDrawerUserName(String userName) {
        TextView menu_username = (TextView) rootView.findViewById(R.id.user_Name);
        if (!userName.equalsIgnoreCase("")) {
            menu_username.setText(userName);
        } else {
            menu_username.setText("User Name");
        }
    }

    public  void adapterRefresh(){
        setListViewAdapter();
    }
    private void setListViewAdapter() {
        adapter = new SlidingMenuAdapter(getActivity(), R.layout.item_menu, listMenuItems, mVisisbilityList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener());
        Log.i(TAG, "create adapter " + listMenuItems.size());
    }

    /**
     * Go to fragment when menu item clicked!
     *
     * @return
     */
    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (last_position == 9) {
                        mVisisbilityList[9] = false;
                    }
                    for (int i = 0; i < 9; i++) {
                        mVisisbilityList[i] = false;
                    }
                    mVisisbilityList[position] = true;
                    adapter.setVisibilityList(mVisisbilityList);
                    adapter.notifyDataSetChanged();
                    SlidingMenuItem item = (SlidingMenuItem) parent.getItemAtPosition(position);
                    if (position == 0) {
                        ((SlidingMenuActvity) getActivity()).transactionFragments(Home_fragmets.newInstance(), true, R.id.container, false, "HomeNewFragment");
                        //((SlidingMenuActvity) getActivity()).transactionFragments(LandingFragment.newInstance(), false, R.id.container, false, "LandingFragment");
                    } else if (position == 1) {
                        ((SlidingMenuActvity) getActivity()).transactionFragments(Home_fragmets.newInstance(), true, R.id.container, false, "HomeNewFragment");
                    }

                    else if (position == 2) {
                        ((SlidingMenuActvity) getActivity()).transactionFragments(Home_fragmets.newInstance(), true, R.id.container, false, "HomeNewFragment");

                    }

                    else if (position == 3) {
                        ((SlidingMenuActvity) getActivity()).transactionFragments(Home_fragmets.newInstance(), true, R.id.container, false, "HomeNewFragment");

                    }
                    else if (position == 4) {
                        prefs.setStringValueForTag(Constants.SAVE_CHECK_ADAPTER,"");
                        ((SlidingMenuActvity) getActivity()).transactionFragments(Home_fragmets.newInstance(), true, R.id.container, false, "HomeNewFragment");

                    }


                    else if (position == 5) {
                        prefs.setStringValueForTag(Constants.SAVE_CHECK_ADAPTER,"");
                        if (!prefs.getStringValueForTag(Constants.API_KEY).equalsIgnoreCase("")) {
                            ((SlidingMenuActvity) getActivity()).transactionFragments(Home_fragmets.newInstance(), true, R.id.container, false, "HomeNewFragment");
                        } else {
                            Toast.makeText(getActivity(), "Please Login First to see your details", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), Login.class);
                            startActivity(intent);
                        }
                    }


                    else if (position == 6) {
                        prefs.setStringValueForTag(Constants.SAVE_CHECK_ADAPTER,"");
                        if (!prefs.getStringValueForTag(Constants.API_KEY).equalsIgnoreCase("")) {
                            ((SlidingMenuActvity) getActivity()).transactionFragments(Home_fragmets.newInstance(), true, R.id.container, false, "HomeNewFragment");

                        } else {
                            Toast.makeText(getActivity(), "Please Login First to see your Following", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), Login.class);
                            startActivity(intent);
                        }
                    }


                    else if (position == 7) {
                        if (!prefs.getStringValueForTag(Constants.API_KEY).equalsIgnoreCase("")) {
                            ((SlidingMenuActvity) getActivity()).transactionFragments(Home_fragmets.newInstance(), true, R.id.container, false, "HomeNewFragment");

                        }else {
                            Toast.makeText(getActivity(), "Please Login First to see your Messages", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), Login.class);
                            startActivity(intent);
                        }



                    }
                    else if (position == 8) {
                        if (!prefs.getStringValueForTag(Constants.API_KEY).equalsIgnoreCase("")) {
                            ((SlidingMenuActvity) getActivity()).transactionFragments(Home_fragmets.newInstance(), true, R.id.container, false, "HomeNewFragment");

                        } else {
                            Toast.makeText(getActivity(), "Please Login First to see your Account  details", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), Login.class);
                            startActivity(intent);
                        }
                    }
                    else if (position == 9) {
                        if (log_InOutText.equalsIgnoreCase("Log In / SignUp")) {
                            ((SlidingMenuActvity)getActivity()).togol_off();
                            Intent intent = new Intent(getActivity(), Login.class);
                            startActivity(intent);
                        }
                        if (log_InOutText.equalsIgnoreCase("Log Out")) {
                            logout();
                        }
                    }

                }

        };
    }

    private void showSimpleAlertMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("ThirstyBuddy");
        alertDialogBuilder
                .setMessage("Coming Soon...")
                .setCancelable(false)
                .setPositiveButton("",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                    }
                })
                .setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void logout() {
        prefs.clearPreferences();
//        prefs.setBooleanValueForTag(Constants.IS_TO_REMEMBER, false);
        Intent intent = new Intent(getActivity(), SlidingMenuActvity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void setDotinSliding(int i) {
        dotpos = i;
        setThingsInMenu();
    }

    public void setThingsInMenu() {
        if (dotpos != 0) {

            if (mVisisbilityList[9] == true) {
                mVisisbilityList[9] = false;
            }


            for (int j = 0; j < 9; j++) {
                mVisisbilityList[j] = false;
            }

            mVisisbilityList[dotpos] = true;
            adapter.setVisibilityList(mVisisbilityList);
            adapter.notifyDataSetChanged();
        }
    }

    private class SlidingMenuAdapter extends ArrayAdapter<SlidingMenuItem> {

        ArrayList<Integer> hiddenPositions = new ArrayList<>();
        int mSelect;
        private FragmentActivity activity;
        private ArrayList<SlidingMenuItem> items;
        private boolean[] mVisibilityList = null;
        TextView notification ,line_text;

        public SlidingMenuAdapter(FragmentActivity activity, int resource, ArrayList<SlidingMenuItem> objects, boolean[] iVisibilityList) {
            super(activity, resource, objects);
            this.activity = activity;
            this.items = objects;
            mVisibilityList = iVisibilityList;
        }

        public void setVisibilityList(boolean[] iVisibilityList) {
            mVisibilityList = iVisibilityList;
        }

        @Override
        public int getCount() {
            return mVisibilityList.length;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_menu, parent, false);
                line_text = (TextView) convertView.findViewById(R.id.text_color);
                ImageView image=(ImageView)convertView.findViewById(R.id.icon_Br);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
                // if holder created, get tag from view

            if (mVisibilityList[position]) {
                holder.rightSideArrow.setVisibility(View.VISIBLE);
                last_position = position;
            } else {
                holder.rightSideArrow.setVisibility(View.INVISIBLE);
            }
            RelativeLayout prog_relative = (RelativeLayout) convertView.findViewById(R.id.prog_relative);
            ViewGroup.LayoutParams paramsrel = prog_relative.getLayoutParams();
            String api_key = prefs.getStringValueForTag(Constants.API_KEY);

            if(position==7) {
                holder.notification.setVisibility(View.VISIBLE);
                String message = prefs.getStringValueForTag(Constants.MESSAGE_COUNT);
                holder.notification.setText(message);
                if(message==null){
                    message = "0";
                }
                if (message.equalsIgnoreCase("0")) {
                    holder.notification.setVisibility(View.GONE);
                }
                else
                {
                    holder.notification.setText(message);
                    holder.notification.setVisibility(View.VISIBLE);
                }
            }
            holder.itemName.setText(getItem(position).getMenuItemName());
            holder = (ViewHolder) convertView.getTag();
            return convertView;
        }

        private class ViewHolder {
            private TextView itemName;
            private TextView notification;
            private ImageView rightSideArrow;
            public ViewHolder(View v) {
                notification = (TextView) v.findViewById(R.id.notification);
                itemName = (TextView) v.findViewById(R.id.name);
                rightSideArrow = (ImageView) v.findViewById(R.id.icon_Br);
            }
        }

    }

}
