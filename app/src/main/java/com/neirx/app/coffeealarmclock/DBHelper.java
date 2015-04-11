package com.neirx.app.coffeealarmclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    // константы для конструктора
    private static final String DATABASE_NAME = "alarmsDB";
    private static final int DATABASE_VERSION = 1;
    private final String TABLE_NAME = "alarms";
    private final String KEY_ON_OFF = "on_ofF";
    private final String KEY_ID = "_id";
    private final String KEY_TITLE = "name";
    private final String KEY_TIME = "time";
    private final String KEY_REPEAT = "repeat";
    private final String KEY_DATE = "date";
    private final String KEY_TYPE_SIGNAL = "type_signal";
    private final String KEY_TRACK = "track";
    private final String KEY_VIBRATION = "vibration";
    private final String KEY_VOLUME = "volume";
    private final String KEY_INCREASE_VOLUME = "increase_volume";
    private final String KEY_START_VOLUME = "start_volume";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALARMS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TITLE + " TEXT," +
                KEY_ON_OFF + " TEXT," + //boolean
                KEY_TIME + " TEXT," + //HH:MM
                KEY_REPEAT + " TEXT," + //дни недели
                KEY_DATE + " TEXT," + //YYYY-MM-DD
                KEY_TYPE_SIGNAL + " TEXT," +
                KEY_TRACK + " TEXT," +
                KEY_VIBRATION + " TEXT," + //boolean
                KEY_VOLUME + " INTEGER," +
                KEY_INCREASE_VOLUME + " TEXT," + //boolean
                KEY_START_VOLUME + " INTEGER" + ")";
        db.execSQL(CREATE_ALARMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public void addAlarm(Alarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ON_OFF, alarm.isOn());
        values.put(KEY_TITLE, alarm.getTitle());
        values.put(KEY_TIME, alarm.getWakeTime());
        values.put(KEY_REPEAT, alarm.getRepeat());
        values.put(KEY_DATE, alarm.getDate());
        values.put(KEY_TYPE_SIGNAL, alarm.getTypeSignal());
        values.put(KEY_TRACK, alarm.getTrack());
        values.put(KEY_VIBRATION, alarm.isVibration());
        values.put(KEY_VOLUME, alarm.getVolume());
        values.put(KEY_INCREASE_VOLUME, alarm.isIncreaseVolume());
        values.put(KEY_START_VOLUME, alarm.getStartVolume());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Alarm> getAllAlarms() {
        List<Alarm> alarmtList = new ArrayList<Alarm>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Alarm alarm = new Alarm();
                alarm.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                alarm.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                alarm.setOn(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_ON_OFF))));
                alarm.setWakeTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                alarm.setRepeat(cursor.getString(cursor.getColumnIndex(KEY_REPEAT)));
                alarm.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                alarm.setTypeSignal(cursor.getString(cursor.getColumnIndex(KEY_TYPE_SIGNAL)));
                alarm.setTrack(cursor.getString(cursor.getColumnIndex(KEY_TRACK)));
                alarm.setVibration(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_VIBRATION))));
                alarm.setVolume(cursor.getString(cursor.getColumnIndex(KEY_VOLUME)));
                alarm.setIncreaseVolume(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_INCREASE_VOLUME))));
                alarm.setStartVolume(cursor.getString(cursor.getColumnIndex(KEY_START_VOLUME)));
                alarmtList.add(alarm);
            } while (cursor.moveToNext());
        }

        return alarmtList;
    }
}
