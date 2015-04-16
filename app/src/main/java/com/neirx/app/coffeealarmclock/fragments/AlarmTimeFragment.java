package com.neirx.app.coffeealarmclock.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.neirx.app.coffeealarmclock.AlarmAdapter;
import com.neirx.app.coffeealarmclock.MainActivity;
import com.neirx.app.coffeealarmclock.R;


public class AlarmTimeFragment extends Fragment {
    AlarmAdapter adapter;

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
