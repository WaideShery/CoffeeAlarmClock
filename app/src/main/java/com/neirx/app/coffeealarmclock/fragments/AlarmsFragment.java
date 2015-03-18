package com.neirx.app.coffeealarmclock.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neirx.app.coffeealarmclock.R;

public class AlarmsFragment extends Fragment{

    public static AlarmsFragment newInstance() {
        AlarmsFragment fragment = new AlarmsFragment();
        return fragment;
    }

    public AlarmsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_alarms, container, false);

        return rootView;
    }
}
