package com.neirx.app.coffeealarmclock;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

public class TimeFragment extends Fragment {

    public static TimeFragment newInstance() {
        TimeFragment fragment = new TimeFragment();
        return fragment;
    }

    public TimeFragment() {}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_time, container, false);

        CustomDigitalClock dc = (CustomDigitalClock) rootView.findViewById(R.id.dgClock);

        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        switch (today) {
            case Calendar.MONDAY:
                rootView.findViewById(R.id.tvMon).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.TUESDAY:
                rootView.findViewById(R.id.tvTue).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.WEDNESDAY:
                rootView.findViewById(R.id.tvWed).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.THURSDAY:
                rootView.findViewById(R.id.tvThu).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.FRIDAY:
                rootView.findViewById(R.id.tvFri).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.SATURDAY:
                rootView.findViewById(R.id.tvSat).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.SUNDAY:
                rootView.findViewById(R.id.tvSun).setBackgroundResource(R.drawable.line);
                break;
            default:
                break;
        }

        return rootView;
    }
}
