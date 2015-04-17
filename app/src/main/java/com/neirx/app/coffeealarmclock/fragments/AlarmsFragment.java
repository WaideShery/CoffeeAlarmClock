package com.neirx.app.coffeealarmclock.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.neirx.app.coffeealarmclock.Alarm;
import com.neirx.app.coffeealarmclock.AlarmAdapter;
import com.neirx.app.coffeealarmclock.DBHelper;
import com.neirx.app.coffeealarmclock.MainActivity;
import com.neirx.app.coffeealarmclock.R;

import java.util.List;

public class AlarmsFragment extends Fragment{
    DBHelper dbHelper;
    List<Alarm> alarms;
    AlarmAdapter alarmAdapter;

    public static AlarmsFragment newInstance() {
        AlarmsFragment fragment = new AlarmsFragment();
        return fragment;
    }


    public AlarmsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alarms, container, false);
        dbHelper = new DBHelper(getActivity());

        alarms = dbHelper.getAllAlarms();


        alarmAdapter = new AlarmAdapter(getActivity(), alarms);

        GridView gridView = (GridView) rootView.findViewById(R.id.gridView);
        gridView.setAdapter(alarmAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alarm alarm = (Alarm) alarmAdapter.getItem(position);
                if (getActivity() != null) {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.replaceSetAlarmFragment(alarm.getId());
                }
            }
        });


        return rootView;
    }

    public void customMethod(){

    }
}
