package com.neirx.app.coffeealarmclock;


import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AlarmAdapter extends BaseAdapter {
    LayoutInflater lInflater;
    TextView tvTime, tvRepeat, tvTitle;
    ImageView ivOn, ivOff;
    FrameLayout layOnOff;
    boolean isOn;
    Context context;
    List<Alarm> objects;
    boolean is24Format;
    Alarm alarm;
    Animation animationFadeIn, animationFadeOut;

    public AlarmAdapter(Context context, List<Alarm> alarms){
        this.context = context;
        objects = alarms;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        is24Format = false;
        if (DateFormat.is24HourFormat(context)){
            is24Format = true;
        }
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.activity_left_alarm, parent, false);
        }

        alarm = getAlarm(position);


        tvTime = (TextView) view.findViewById(R.id.tvTime);
        tvTime.setText(alarmTime(alarm.getWakeHour(), alarm.getWakeMinute()));
        tvRepeat = (TextView) view.findViewById(R.id.tvRepeat);
        tvRepeat.setText(alarmRepeat(alarm.getRepeat()));
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(alarm.getTitle());

        layOnOff = (FrameLayout) view.findViewById(R.id.layOnOff);
        ivOn = (ImageView) view.findViewById(R.id.ivOn);
        ivOff = (ImageView) view.findViewById(R.id.ivOff);
        isOn = alarm.isOn();
        if(!isOn){
            ivOn.setVisibility(View.INVISIBLE);
        }

        animationFadeIn = AnimationUtils.loadAnimation(context, R.anim.fadein);
        animationFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {ivOn.setVisibility(View.VISIBLE);}
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        animationFadeOut = AnimationUtils.loadAnimation(context, R.anim.fadeout);
        animationFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {ivOn.setVisibility(View.INVISIBLE);}
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        ivOn.setOnClickListener(clickImg);
        ivOff.setOnClickListener(clickImg);

        return view;
    }

    public View.OnClickListener clickImg = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBHelper dbHelper = new DBHelper(context);
            if (isOn) {
                isOn = false;
                alarm.setOn(isOn);
                dbHelper.updateAlarm(alarm, alarm.getId());
                ivOn.startAnimation(animationFadeOut);
                ivOn.setVisibility(View.INVISIBLE);
                Log.d(MainActivity.TAG, "Выключение");
            } else {
                isOn = true;
                alarm.setOn(isOn);
                dbHelper.updateAlarm(alarm, alarm.getId());
                ivOn.startAnimation(animationFadeIn);
                Log.d(MainActivity.TAG, "Включение");
            }
            context.startService(new Intent(context, AlarmService.class));
        }
    };

    private Alarm getAlarm(int position) {
        return ((Alarm) getItem(position));
    }

    private String alarmTime(int hourOfDay, int minute){
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
            return strHour+":"+strMinute;
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
            return hourOfDay+":"+strMinute + meridiem;
        }
    }

    private String alarmRepeat(String repeat){
        if(repeat.equals("today")){
            return "Сегодня";
        } else if(repeat.equals("tomorrow")){
            return "Завтра";
        } else {
            StringBuilder sb = new StringBuilder();
            String[] days = repeat.split("\\+");
            if (days != null){
                for(String day : days){
                    switch (day){
                        case "mon":
                            sb.append("ПН ");
                            break;
                        case "tue":
                            sb.append("ВТ ");
                            break;
                        case "wed":
                            sb.append("СР ");
                            break;
                        case "thu":
                            sb.append("ЧТ ");
                            break;
                        case "fri":
                            sb.append("ПТ ");
                            break;
                        case "sat":
                            sb.append("СБ ");
                            break;
                        case "sun":
                            sb.append("ВС ");
                            break;

                    }
                }
            }
            return sb.toString();
        }
    }
}
