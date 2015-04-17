package com.neirx.app.coffeealarmclock;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

public class AlarmService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId){
        Log.d(MainActivity.TAG, "onStartCommand");
        DBHelper dbHelper = new DBHelper(this);
        List<Alarm> alarms = dbHelper.getAllAlarms();
        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        if(alarms != null){
            for (Alarm alarm : alarms){
                if(alarm.isOn()) {
                    Intent alarmIntent = new Intent(this, AlarmActivity.class);
                    alarmIntent.setAction("" + alarm.getId());
                    alarmIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                    manager.set(AlarmManager.RTC_WAKEUP, alarm.getPoint(), pi);
                    Log.d(MainActivity.TAG, "curTime = "+System.currentTimeMillis());
                    Log.d(MainActivity.TAG, "point   = "+alarm.getPoint());
                } else {
                    Intent alarmIntent = new Intent(this, AlarmActivity.class);
                    alarmIntent.setAction("" + alarm.getId());
                    PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    if(pi != null) {
                        manager.cancel(pi);
                    }
                    Log.d(MainActivity.TAG, "isOff");
                }
            }
        }
        return START_NOT_STICKY;
    }

}
