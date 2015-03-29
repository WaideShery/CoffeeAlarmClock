package com.neirx.app.coffeealarmclock.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neirx.app.coffeealarmclock.R;
import com.neirx.app.coffeealarmclock.dialogs.LabelDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.Calendar;


public class SetAlarmFragment extends Fragment implements View.OnClickListener, TimePickerDialog.OnTimeSetListener{
    TextView tvLabel, tvTime;
    RelativeLayout relTime;
    TimePickerDialog timePickerDialog;
    public static final String TIMEPICKER_TAG = "timepicker";
    boolean is24Format;

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
        final Calendar calendar = Calendar.getInstance();
        is24Format = false;
        if (DateFormat.is24HourFormat(getActivity())){
            is24Format = true;
        }
        timePickerDialog = TimePickerDialog.newInstance(
                (TimePickerDialog.OnTimeSetListener) this,
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), is24Format, false);


        RelativeLayout rlLabel = (RelativeLayout) rootView.findViewById(R.id.rlLabel);
        rlLabel.setOnClickListener(this);

        tvLabel = (TextView) rootView.findViewById(R.id.tvLabel);
        tvTime = (TextView) rootView.findViewById(R.id.tvTime);

        relTime = (RelativeLayout) rootView.findViewById(R.id.relTime);
        relTime.setOnClickListener(this);
        return rootView;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlLabel:
                LabelDialog labelDialog = new LabelDialog();
                labelDialog.setTargetFragment(this, getTargetRequestCode());
                labelDialog.show(getFragmentManager(), "dlgLabel");
                break;
            case R.id.relTime:
                timePickerDialog.setVibrate(false);
                timePickerDialog.setCloseOnSingleTapMinute(false);
                timePickerDialog.setStartTime(14, 14);
                timePickerDialog.show(getFragmentManager(), TIMEPICKER_TAG);
                break;
        }
    }


    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        String strMinute, strHour;

        if(minute < 10){
            strMinute = "0"+minute;
        } else {
            strMinute = ""+minute;
        }

        if(is24Format){
            if(hourOfDay < 10){
                strHour = "0"+hourOfDay;
            } else {
                strHour = ""+hourOfDay;
            }
            tvTime.setText(strHour+":"+strMinute);
        } else {
            String meridiem;
            if(hourOfDay < 12){
                meridiem = " am";
            } else {
                meridiem = " pm";
            }
            if(hourOfDay > 12){
                hourOfDay -= 12;
            }
            if(hourOfDay == 0){
                hourOfDay = 12;
            }
            tvTime.setText(hourOfDay+":"+strMinute + meridiem);
        }
    }


}
