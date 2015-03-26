package com.neirx.app.coffeealarmclock.fragments;

import android.app.Fragment;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.neirx.app.coffeealarmclock.R;

import java.lang.reflect.Field;


public class SetAlarmFragment extends Fragment {
    public static SetAlarmFragment newInstance() {
        SetAlarmFragment fragment = new SetAlarmFragment();
        return fragment;
    }

    public SetAlarmFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_set_alarm, container, false);


        return rootView;
    }
}
