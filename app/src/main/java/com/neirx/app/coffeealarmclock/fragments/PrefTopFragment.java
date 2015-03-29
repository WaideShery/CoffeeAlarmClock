package com.neirx.app.coffeealarmclock.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neirx.app.coffeealarmclock.R;

public class PrefTopFragment extends Fragment {
    public static PrefTopFragment newInstance() {
        PrefTopFragment fragment = new PrefTopFragment();
        return fragment;
    }

    public PrefTopFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pref_top, container, false);

        View viewTop = rootView.findViewById(R.id.topBar);
        BitmapDrawable bdTop = (BitmapDrawable) getResources().getDrawable(R.drawable.sett_bg);
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



        return rootView;
    }
}
