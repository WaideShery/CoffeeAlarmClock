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
import com.neirx.app.coffeealarmclock.R;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.Calendar;


public class SetAlarmFragment extends Fragment implements View.OnClickListener, TimePickerDialog.OnTimeSetListener{
    TextView tvLabel, tvTime;
    RelativeLayout relTime;
    TimePickerDialog timePickerDialog;
    public static final String TIMEPICKER_TAG = "timepicker";

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
        boolean is24Format = false;
        if (DateFormat.is24HourFormat(getActivity())){
            //is24Format = true;
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
                labelDialog();
                //labelDialog.setTargetFragment(this, getTargetRequestCode());
                //labelDialog.show(getFragmentManager(), "dlgLabel");
                break;
            case R.id.relTime:
                timePickerDialog.setVibrate(false);
                timePickerDialog.setCloseOnSingleTapMinute(false);
                timePickerDialog.show(getFragmentManager(), TIMEPICKER_TAG);
                break;
        }
    }

    private void labelDialog() {
        new DialogFragment(){
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                final EditText etInput = new EditText(getActivity());
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                        .setTitle("Название будильника")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String newLabel = etInput.getText().toString();
                                tvLabel.setText(newLabel);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setView(etInput);
                return adb.create();
            }

        }.show(getFragmentManager(), "dlgLabel");
    }


    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        String strMinute;
        if(minute < 10){
            strMinute = "0"+minute;
        } else {
            strMinute = ""+minute;
        }
        switch (view.getIsCurrentlyAmOrPm()){
            case TimePickerDialog.AM:
                tvTime.setText(hourOfDay+":"+strMinute + " am");
                break;
            case TimePickerDialog.PM:
                tvTime.setText(hourOfDay+":"+strMinute + " pm");
                break;
            default:
                tvTime.setText(hourOfDay+":"+strMinute);
                break;
        }

        if(view.getIsCurrentlyAmOrPm() != -1){
            tvTime.setText(hourOfDay+":"+minute);
        } else{
            tvTime.setText(hourOfDay+":"+minute);
        }
    }


}
