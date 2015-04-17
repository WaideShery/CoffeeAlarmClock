package com.neirx.app.coffeealarmclock;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;


public class AlarmActivity extends Activity {
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Intent intent = getIntent();
        if(intent != null){
            id = Integer.parseInt(intent.getAction());
        }

        Button btnRingOff = (Button) findViewById(R.id.btnRingOff);
        btnRingOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id > 0){
                    DBHelper dbHelper = new DBHelper(AlarmActivity.this);
                    Alarm alarm = dbHelper.getAlarm(id);
                    String repeatDay = alarm.getRepeat();
                    if(repeatDay.equals("today") || repeatDay.equals("tomorrow")){
                        alarm.setOn(false);
                        dbHelper.updateAlarm(alarm, id);
                    } else {
                        alarm.setPoint(setAlarmTime(repeatDay, alarm.getWakeHour(), alarm.getWakeMinute()));
                        dbHelper.updateAlarm(alarm, id);
                    }

                }
                startService(new Intent(AlarmActivity.this, AlarmService.class));
                finish();
            }
        });
    }

    private long setAlarmTime(String repeatDay, int hourOfDay, int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day){
            case Calendar.SUNDAY:
                if(repeatDay.contains("mon"))
                    dayOfYear += 1;
                else if(repeatDay.contains("tue"))
                    dayOfYear += 2;
                else if(repeatDay.contains("wed"))
                    dayOfYear += 3;
                else if(repeatDay.contains("thu"))
                    dayOfYear += 4;
                else if(repeatDay.contains("fri"))
                    dayOfYear += 5;
                else if(repeatDay.contains("sat"))
                    dayOfYear += 6;
                else if(repeatDay.contains("sun"))
                    dayOfYear += 7;
                break;
            case Calendar.MONDAY:
                if(repeatDay.contains("tue"))
                    dayOfYear += 1;
                else if(repeatDay.contains("wed"))
                    dayOfYear += 2;
                else if(repeatDay.contains("thu"))
                    dayOfYear += 3;
                else if(repeatDay.contains("fri"))
                    dayOfYear += 4;
                else if(repeatDay.contains("sat"))
                    dayOfYear += 5;
                else if(repeatDay.contains("sun"))
                    dayOfYear += 6;
                else if(repeatDay.contains("mon"))
                    dayOfYear += 7;
                break;
            case Calendar.TUESDAY:
                if(repeatDay.contains("wed"))
                    dayOfYear += 1;
                else if(repeatDay.contains("thu"))
                    dayOfYear += 2;
                else if(repeatDay.contains("fri"))
                    dayOfYear += 3;
                else if(repeatDay.contains("sat"))
                    dayOfYear += 4;
                else if(repeatDay.contains("sun"))
                    dayOfYear += 5;
                else if(repeatDay.contains("mon"))
                    dayOfYear += 6;
                else if(repeatDay.contains("tue"))
                    dayOfYear += 7;
                break;
            case Calendar.WEDNESDAY:
                if(repeatDay.contains("thu"))
                    dayOfYear += 1;
                else if(repeatDay.contains("fri"))
                    dayOfYear += 2;
                else if(repeatDay.contains("sat"))
                    dayOfYear += 3;
                else if(repeatDay.contains("sun"))
                    dayOfYear += 4;
                else if(repeatDay.contains("mon"))
                    dayOfYear += 5;
                else if(repeatDay.contains("tue"))
                    dayOfYear += 6;
                else if(repeatDay.contains("wed"))
                    dayOfYear += 7;
                break;
            case Calendar.THURSDAY:
                if(repeatDay.contains("fri"))
                    dayOfYear += 1;
                else if(repeatDay.contains("sat"))
                    dayOfYear += 2;
                else if(repeatDay.contains("sun"))
                    dayOfYear += 3;
                else if(repeatDay.contains("mon"))
                    dayOfYear += 4;
                else if(repeatDay.contains("tue"))
                    dayOfYear += 5;
                else if(repeatDay.contains("wed"))
                    dayOfYear += 6;
                else if(repeatDay.contains("thu"))
                    dayOfYear += 7;
                break;
            case Calendar.FRIDAY:
                if(repeatDay.contains("sat"))
                    dayOfYear += 1;
                else if(repeatDay.contains("sun"))
                    dayOfYear += 2;
                else if(repeatDay.contains("mon"))
                    dayOfYear += 3;
                else if(repeatDay.contains("tue"))
                    dayOfYear += 4;
                else if(repeatDay.contains("wed"))
                    dayOfYear += 5;
                else if(repeatDay.contains("thu"))
                    dayOfYear += 6;
                else if(repeatDay.contains("fri"))
                    dayOfYear += 7;
                break;
            case Calendar.SATURDAY:
                if(repeatDay.contains("sun"))
                    dayOfYear += 1;
                else if(repeatDay.contains("mon"))
                    dayOfYear += 2;
                else if(repeatDay.contains("tue"))
                    dayOfYear += 3;
                else if(repeatDay.contains("wed"))
                    dayOfYear += 4;
                else if(repeatDay.contains("thu"))
                    dayOfYear += 5;
                else if(repeatDay.contains("fri"))
                    dayOfYear += 6;
                else if(repeatDay.contains("sat"))
                    dayOfYear += 7;
                break;
        }
        calendar.add(Calendar.DAY_OF_YEAR, dayOfYear);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTimeInMillis();
    }
}
