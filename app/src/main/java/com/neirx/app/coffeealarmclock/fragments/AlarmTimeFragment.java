package com.neirx.app.coffeealarmclock.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neirx.app.coffeealarmclock.AlarmAdapter;
import com.neirx.app.coffeealarmclock.R;


public class AlarmTimeFragment extends Fragment {
    AlarmAdapter adapter;

    public AlarmTimeFragment() {
    }

    public static AlarmTimeFragment newInstance() {
        AlarmTimeFragment fragment = new AlarmTimeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_alarm_time, container, false);


        FragmentManager fragmentManager = getActivity().getFragmentManager();
        TimeFragment timeFragment = TimeFragment.newInstance();
        AlarmsFragment alarmsFragment = AlarmsFragment.newInstance();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerTime, timeFragment);
        fragmentTransaction.add(R.id.containerAlarms, alarmsFragment);
        fragmentTransaction.commit();

        return rootView;
    }

}
