package com.neirx.app.coffeealarmclock.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.neirx.app.coffeealarmclock.Alarm;
import com.neirx.app.coffeealarmclock.DBHelper;
import com.neirx.app.coffeealarmclock.MainActivity;
import com.neirx.app.coffeealarmclock.R;
import com.neirx.app.coffeealarmclock.dialogs.LabelDialog;
import com.neirx.app.coffeealarmclock.dialogs.SignalDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.Calendar;



public class SetAlarmFragment extends Fragment implements View.OnClickListener, TimePickerDialog.OnTimeSetListener,
        LabelDialog.OnLabelSetListener, SignalDialog.OnSignalSetListener{
    SignalDialog signalDialog;
    SignalDialog.Signals signal;
    TextView tvLabel, tvTime, tvSignal, tvRepeat, tvSelect, tvPercent;
    ToggleButton togMonday, togTuesday, togWednesday, togThursday, togFriday, togSaturday, togSunday;
    RelativeLayout relTime, relSignal, relSelect;
    TimePickerDialog timePickerDialog;
    public static final String TIMEPICKER_TAG = "timepicker";
    public static final String LABELSET_TAG = "labelsetter";
    public static final String SIGNALSET_TAG = "signalsetter";
    boolean is24Format;
    int hourOfDay, minute;
    int defaultProgress = 80;
    Button btnSave, btnCancel;
    SeekBar seekBar;
    Switch switchVibrate, switchIncreaseVolume;
    String ringtoneUri = "";
    DBHelper dbHelper;
    String repeatDays;

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

        relSelect = (RelativeLayout) rootView.findViewById(R.id.relSelect);
        relSelect.setOnClickListener(this);
        tvSelect = (TextView) rootView.findViewById(R.id.tvSelect);

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
        togTuesday = (ToggleButton) rootView.findViewById(R.id.togTuesday);
        togTuesday.setOnClickListener(onToggleClick);
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

        onSignalSet(signal);

        btnSave = (Button) rootView.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        if(tvTime.getText().toString().isEmpty()){
            btnSave.setEnabled(false);
        }

        seekBar = (SeekBar) rootView.findViewById(R.id.seekBar);
        tvPercent = (TextView) rootView.findViewById(R.id.tvPercent);
        tvPercent.setText("" + defaultProgress + "%");
        seekBar.setProgress(defaultProgress);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvPercent.setText("" + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        switchVibrate = (Switch) rootView.findViewById(R.id.switchVibrate);
        switchIncreaseVolume = (Switch) rootView.findViewById(R.id.switchIncreaseVolume);

        dbHelper = new DBHelper(getActivity());
        return rootView;
    }

    private View.OnClickListener onToggleClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(togMonday.isChecked() || togTuesday.isChecked() || togWednesday.isChecked()
                    || togThursday.isChecked() || togFriday.isChecked()
                    || togSaturday.isChecked() || togSunday.isChecked()){
                tvRepeat.setText("Дни недели");
                repeatDays = setRepeatDays();
            } else if(!tvTime.getText().toString().isEmpty()){
                Calendar c = Calendar.getInstance();
                int curHours = c.get(Calendar.HOUR_OF_DAY);
                int curMinutes = c.get(Calendar.MINUTE);
                if(hourOfDay > curHours){
                    tvRepeat.setText("Сегодня");
                    repeatDays = "today";
                } else if(hourOfDay == curHours && minute > curMinutes){
                    tvRepeat.setText("Сегодня");
                    repeatDays = "today";
                } else {
                    tvRepeat.setText("Завтра");
                    repeatDays = "tomorrow";
                }
            } else {
                tvRepeat.setText("");
            }
        }

        private String setRepeatDays() {
            StringBuilder sb = new StringBuilder();
            if(togMonday.isChecked())
                sb.append("mon+");
            if(togTuesday.isChecked())
                sb.append("tue+");
            if(togWednesday.isChecked())
                sb.append("wed+");
            if(togThursday.isChecked())
                sb.append("thu+");
            if(togFriday.isChecked())
                sb.append("fri+");
            if(togSaturday.isChecked())
                sb.append("sat+");
            if(togSunday.isChecked())
                sb.append("sun");

            return sb.toString();
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
                signalDialog = SignalDialog.newInstance(this, signal);
                signalDialog.show(getFragmentManager(), SIGNALSET_TAG);
                break;
            case R.id.relSelect:
                Intent tmpIntent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                startActivityForResult(tmpIntent, 0);
                break;
            case R.id.btnCancel:
                if (getActivity() != null) {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.replaceAlarmsFragment();
                }
                break;
            case R.id.btnSave:
                Alarm alarm = new Alarm();
                alarm.setOn(true);
                alarm.setTitle(tvLabel.getText().toString());
                alarm.setWakeHour(hourOfDay);
                alarm.setWakeMinute(minute);
                alarm.setVibration(switchVibrate.isChecked());
                alarm.setIncreaseVolume(switchIncreaseVolume.isChecked());
                alarm.setVolume(seekBar.getProgress());

                String typeSignal = tvSignal.getText().toString();
                alarm.setTypeSignal(typeSignal);
                alarm.setTrack(ringtoneUri);

                alarm.setRepeat(repeatDays);

                dbHelper.addAlarm(alarm);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            if (uri != null) {
                Ringtone ringtone = RingtoneManager.getRingtone(getActivity(), uri);
                String title = ringtone.getTitle(getActivity());
                tvSelect.setText(title);
                ringtoneUri = uri.toString();
            }
        }
    }


    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        Calendar c = Calendar.getInstance();
        int curHours = c.get(Calendar.HOUR_OF_DAY);
        int curMinutes = c.get(Calendar.MINUTE);
        String strRepeat = tvRepeat.getText().toString();
        if(hourOfDay >= curHours && minute > curMinutes){
            if(!strRepeat.equals("Дни недели")) {
                tvRepeat.setText("Сегодня");
                repeatDays = "today";
            }
        } else {
            if(!strRepeat.equals("Дни недели")) {
                tvRepeat.setText("Завтра");
                repeatDays = "tomorrow";
            }
        }

        String strMinute, strHour;
        btnSave.setEnabled(true);


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
        if(signal == SignalDialog.Signals.Standart){
            relSelect.setVisibility(RelativeLayout.GONE);
            } else {
            relSelect.setVisibility(RelativeLayout.VISIBLE);
        }
    }
}
