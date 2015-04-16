package com.neirx.app.coffeealarmclock.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.neirx.app.coffeealarmclock.MainActivity;
import com.neirx.app.coffeealarmclock.R;

public class TopFragment extends Fragment implements View.OnClickListener {
    FrameLayout menuFrame;
    FragmentManager fragmentManager;
    static TopFragment fragment;
    float scale;
    ImageView iconAlarm, iconGraphic;
    int position;

    public static TopFragment newInstance() {
        if(fragment == null) {
            fragment = new TopFragment();
        }
        return fragment;
    }

    public TopFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_top, container, false);

        View viewTop = rootView.findViewById(R.id.topBar);
        BitmapDrawable bdTop = (BitmapDrawable) getResources().getDrawable(R.drawable.bg_tb);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        int intrinsicHeight = bdTop.getIntrinsicHeight();

        Rect bounds = new Rect(0,0,width, intrinsicHeight);
        bdTop.setTileModeX(Shader.TileMode.REPEAT);
        bdTop.setBounds(bounds);
        Bitmap bitmapTop = Bitmap.createBitmap(bounds.width(), bounds.height(), bdTop.getBitmap().getConfig());
        Canvas canvasTop = new Canvas(bitmapTop);
        bdTop.draw(canvasTop);
        viewTop.setBackground(new BitmapDrawable(bitmapTop));

        iconAlarm = (ImageView) rootView.findViewById(R.id.iconAlarm);
        iconGraphic = (ImageView) rootView.findViewById(R.id.iconGraphic);
        scale = this.getResources().getDisplayMetrics().density;


        fragmentManager = getChildFragmentManager();

        menuFrame = (FrameLayout) rootView.findViewById(R.id.frameMenu);
        menuFrame.setOnClickListener(this);

        position = 0;

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frameMenu:
                showOverflowMenu(v);
                break;
        }
    }

    public void setIconAlarmChanged(){

        iconGraphic.getLayoutParams().height = (int) (35 * scale + 0.5f);
        iconGraphic.getLayoutParams().width = (int) (35 * scale + 0.5f);
        iconGraphic.setImageDrawable(getResources().getDrawable(R.drawable.menu_graph_off));

        iconAlarm.getLayoutParams().height = (int) (40 * scale + 0.5f);
        iconAlarm.getLayoutParams().width = (int) (40 * scale + 0.5f);
        iconAlarm.setImageDrawable(getResources().getDrawable(R.drawable.menu_alarm_on));
    }

    public void setIconGraphicChanged(){
            iconGraphic.getLayoutParams().height = (int) (40 * scale + 0.5f);
            iconGraphic.getLayoutParams().width = (int) (40 * scale + 0.5f);
            iconGraphic.setImageDrawable(getResources().getDrawable(R.drawable.menu_graph_on));

            iconAlarm.getLayoutParams().height = (int) (35 * scale + 0.5f);
            iconAlarm.getLayoutParams().width = (int) (35 * scale + 0.5f);
            iconAlarm.setImageDrawable(getResources().getDrawable(R.drawable.menu_alarm_off));
    }

    public void setPosition(int position){
        this.position = position;
    }


    private void showOverflowMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        popupMenu.inflate(R.menu.menu_main);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        if (getActivity() != null) {
                            MainActivity activity = (MainActivity) getActivity();
                            activity.replacePreferFragment();
                        }
                        return true;
                    default:
                        return false;
                }

            }
        });
        popupMenu.show();
    }


}
