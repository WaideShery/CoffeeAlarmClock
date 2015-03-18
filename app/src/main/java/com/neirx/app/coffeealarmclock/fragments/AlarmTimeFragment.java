package com.neirx.app.coffeealarmclock.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neirx.app.coffeealarmclock.R;


public class AlarmTimeFragment extends Fragment {
    public static AlarmTimeFragment newInstance() {
        AlarmTimeFragment fragment = new AlarmTimeFragment();
        return fragment;
    }

    public AlarmTimeFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_alarm_time, container, false);

        return rootView;
    }
}
