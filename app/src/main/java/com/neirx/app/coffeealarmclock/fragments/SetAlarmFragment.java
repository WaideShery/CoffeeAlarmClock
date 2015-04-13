package com.neirx.app.coffeealarmclock.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.neirx.app.coffeealarmclock.R;
import com.neirx.app.coffeealarmclock.dialogs.LabelDialog;
import com.neirx.app.coffeealarmclock.dialogs.SignalDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.Calendar;



public class SetAlarmFragment extends Fragment implements View.OnClickListener, TimePickerDialog.OnTimeSetListener,
        LabelDialog.OnLabelSetListener, SignalDialog.OnSignalSetListener{
    SignalDialog.Signals signal;
    TextView tvLabel, tvTime, tvSignal, tvRepeat;
    ToggleButton togMonday, togTuesday, togWednesday, togThursday, togFriday, togSaturday, togSunday;
    RelativeLayout relTime, relSignal;
    TimePickerDialog timePickerDialog;
    public static final String TIMEPICKER_TAG = "timepicker";
    public static final String LABELSET_TAG = "labelsetter";
    public static final String SIGNALSET_TAG = "signalsetter";
    boolean is24Format;

    public static SetAlarmFragment newInstance() {
        SetAlarmFragment fragment = new SetAlarmFragment();
        fragment.signal = SignalDialog.Signals.Standart;
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
        tvRepeat = (TextView) rootView.findViewById(R.id.tvRepeat);

        relTime = (RelativeLayout) rootView.findViewById(R.id.relTime);
        relTime.setOnClickListener(this);

        relSignal = (RelativeLayout) rootView.findViewById(R.id.relSignal);
        relSignal.setOnClickListener(this);
        tvSignal = (TextView) rootView.findViewById(R.id.tvSignal);

        togMonday = (ToggleButton) rootView.findViewById(R.id.togMonday);
        togMonday.setOnClickListener(onToggleClick);
        togThursday = (ToggleButton) rootView.findViewById(R.id.togThursday);
        togThursday.setOnClickListener(onToggleClick);
        togWednesday = (ToggleButton) rootView.findViewById(R.id.togWednesday);
        togWednesday.setOnClickListener(onToggleClick);
        togThursday = (ToggleButton) rootView.findViewById(R.id.togThursday);
        togThursday.setOnClickListener(onToggleClick);
        togFriday = (ToggleButton) rootView.findViewById(R.id.togFriday);
        togFriday.setOnClickListener(onToggleClick);
        togSaturday = (ToggleButton) rootView.findViewById(R.id.togSaturday);
        togSaturday.setOnClickListener(onToggleClick);
        togSunday = (ToggleButton) rootView.findViewById(R.id.togSunday);
        togSunday.setOnClickListener(onToggleClick);

        return rootView;
    }

    private View.OnClickListener onToggleClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(togMonday.isChecked() || togThursday.isChecked() || togWednesday.isChecked()
                    || togThursday.isChecked() || togFriday.isChecked()
                    || togSaturday.isChecked() || togSunday.isChecked()){
                tvRepeat.setText("Дни недели");
            } else{
                tvRepeat.setText("");
            }
        }
    };



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlLabel:
                String curLabel = tvLabel.getText().toString();
                LabelDialog labelDialog = LabelDialog.newInstance(this, curLabel);
                labelDialog.show(getFragmentManager(), LABELSET_TAG);
                break;
            case R.id.relTime:
                timePickerDialog.setVibrate(false);
                timePickerDialog.setCloseOnSingleTapMinute(false);
                timePickerDialog.setStartTime(14, 14);
                timePickerDialog.show(getFragmentManager(), TIMEPICKER_TAG);
                break;
            case R.id.relSignal:
                SignalDialog signalDialog = SignalDialog.newInstance(this, signal);
                signalDialog.show(getFragmentManager(), SIGNALSET_TAG);
                break;
            case R.id.relSelect:
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


    @Override
    public void onTimeSet(String Label) {
        tvLabel.setText(Label);
    }

    @Override
    public void onSignalSet(SignalDialog.Signals signal) {
        this.signal = signal;
        tvSignal.setText(signal.toString());
    }
}
