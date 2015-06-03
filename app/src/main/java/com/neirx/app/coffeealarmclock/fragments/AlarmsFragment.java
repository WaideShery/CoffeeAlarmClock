package com.neirx.app.coffeealarmclock.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.neirx.app.coffeealarmclock.Alarm;
import com.neirx.app.coffeealarmclock.AlarmAdapter;
import com.neirx.app.coffeealarmclock.utility.DBHelper;
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

        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(alarmAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alarm alarm = (Alarm) alarmAdapter.getItem(position);
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.replaceSetAlarmFragment(alarm.getId());
                }
            }
        });

        return rootView;
    }

}
