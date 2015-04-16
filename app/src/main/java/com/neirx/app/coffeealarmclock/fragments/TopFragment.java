package com.neirx.app.coffeealarmclock.fragments;

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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupMenu;

import com.neirx.app.coffeealarmclock.R;

public class TopFragment extends Fragment implements View.OnClickListener {
    FrameLayout menuFrame;
    FragmentManager fragmentManager;

    public static TopFragment newInstance() {
        TopFragment fragment = new TopFragment();
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


        fragmentManager = getChildFragmentManager();

        menuFrame = (FrameLayout) rootView.findViewById(R.id.frameMenu);
        menuFrame.setOnClickListener(this);

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

    private void showOverflowMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        popupMenu.inflate(R.menu.menu_main);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Fragment prefFragment = PreferFragment.newInstance();
                        Fragment prefTopFragment = PrefTopFragment.newInstance();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Fragment replaceFragment = ReplaceFragment.newInstance();
                        fragmentTransaction.remove(replaceFragment);
                        //fragmentTransaction.replace(R.id.container, prefFragment);
                        //fragmentTransaction.replace(R.id.containerTop, prefTopFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        return true;
                    default:
                        return false;
                }

            }
        });
        popupMenu.show();
    }


}
