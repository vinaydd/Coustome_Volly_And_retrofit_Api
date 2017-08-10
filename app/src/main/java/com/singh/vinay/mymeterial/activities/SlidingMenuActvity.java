package com.singh.vinay.mymeterial.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.singh.vinay.mymeterial.R;
import com.singh.vinay.mymeterial.fragments.SlidingMenuFragment;
import com.singh.vinay.mymeterial.utils.AppPreference;

import java.util.ArrayList;

/**
 * Created by proloy on 1/7/16.
 */
public class SlidingMenuActvity extends SlidingActivity {
    EditText edit_search;
    ImageView filter, back_Arrow, menubtn, message;
    ImageView search;
    RelativeLayout relativeLayout, search_layout;
    AppPreference prefs;
    private View mCustomView;
    private TextView title, megActio;
    ArrayList<String> lol = new ArrayList<>();
    ArrayList<String> lolrestore = new ArrayList<>();
    SlidingMenu sm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);
        setBehindView();
        prefs = new AppPreference(this.getApplicationContext());
        sm = getSlidingMenu();
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        final ActionBar actionBar = getSupportActionBar();
        LayoutInflater mInflater = LayoutInflater.from(this);
        mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.header));
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(mCustomView, layoutParams);
        actionBar.setDisplayShowCustomEnabled(true);
        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        menubtn = (ImageView) mCustomView.findViewById(R.id.menu_Button);
        title = (TextView) mCustomView.findViewById(R.id.header_title);
        search = (ImageView) mCustomView.findViewById(R.id.search_icon);
        filter = (ImageView) mCustomView.findViewById(R.id.filterIcon);
        back_Arrow = (ImageView) mCustomView.findViewById(R.id.back_Arrow);
        search_layout = (RelativeLayout) mCustomView.findViewById(R.id.search_edit_layout);
        edit_search = (EditText) mCustomView.findViewById(R.id.search_edittext);
        relativeLayout = (RelativeLayout) findViewById(R.id.notifications);
        megActio = (TextView) findViewById(R.id.action_msgcv);
        message = (ImageView) findViewById(R.id.message);
        megActio.setVisibility(View.VISIBLE);
        message.setVisibility(View.VISIBLE);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMessageView();
            }
        });
        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sm.toggle();
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("someVarA", lol);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lolrestore = savedInstanceState.getStringArrayList("someVarA");
    }
    private void setBehindView() {
        setBehindContentView(R.layout.menu_slide);
        transactionFragments(SlidingMenuFragment.newInstance(), false, R.id.menu_slide, false, "MainFragment");
    }
    public void transactionFragments(Fragment fragment, boolean backStackTag, int viewResource, boolean toogleOff, String fragmentTag) {
        final Fragment selectedFrag = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        lol.add(fragmentTag);
        SlidingMenuFragment fragmentMenu = (SlidingMenuFragment) getSupportFragmentManager().findFragmentByTag("MainFragment");
        if (selectedFrag != null && selectedFrag.isVisible()) {
            toggle();
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(viewResource, fragment, fragmentTag);
        if (backStackTag)
            ft.addToBackStack(null);
        ft.commit();
        if (!toogleOff) {
            toggle();
        }
    }
    public void togol_off() {
        toggle();
    }
    public void setActionBarTitle(String actionBarTitle) {
        title.setText(actionBarTitle);

    }
    public void setMessageView() {
    }

}
