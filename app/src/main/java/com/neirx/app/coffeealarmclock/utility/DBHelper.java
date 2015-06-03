package com.neirx.app.coffeealarmclock.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.neirx.app.coffeealarmclock.Alarm;
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
    private final String KEY_HOUR = "hour";
    private final String KEY_MINUTE = "minute";
    private final String KEY_REPEAT = "repeat";
    private final String KEY_TYPE_SIGNAL = "type_signal";
    private final String KEY_TRACK = "track";
    private final String KEY_VIBRATION = "vibration";
    private final String KEY_VOLUME = "volume";
    private final String KEY_INCREASE_VOLUME = "increase_volume";
    private final String KEY_START_VOLUME = "start_volume";
    private final String KEY_POINT = "point";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALARMS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TITLE + " TEXT," +
                KEY_ON_OFF + " TEXT," + //boolean, вкл./выкл. будильника
                KEY_HOUR + " INTEGER," + //часы будильника (0-23)
                KEY_MINUTE + " INTEGER," + //минуты будильника (0-59)
                KEY_REPEAT + " TEXT," + //дни недели(mon, tue и т.д.); разовый(once#15.03.2015 - сегодня или завтра);
                                        //в определенный день(15.03.2015); периодичное срабатывание(every-4#15.03.2015)
                KEY_TYPE_SIGNAL + " TEXT," + //тип сыгнала(музыка, стандартный и т.д.)
                KEY_TRACK + " TEXT," + //uri.toString
                KEY_POINT + " INTEGER," + //следующая сработка будильника
                KEY_VIBRATION + " TEXT," + //boolean, вкл./выкл. вибрации
                KEY_VOLUME + " INTEGER," + //громкость в процентах
                KEY_INCREASE_VOLUME + " TEXT," + //boolean, вкл./выкл. наростания громкости
                KEY_START_VOLUME + " INTEGER" + ")"; // начальная громкость
        db.execSQL(CREATE_ALARMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteAlarm(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=" + id, null);
    }

    public void addAlarm(Alarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ON_OFF, Boolean.toString(alarm.isOn()));
        values.put(KEY_TITLE, alarm.getTitle());
        values.put(KEY_HOUR, alarm.getWakeHour());
        values.put(KEY_MINUTE, alarm.getWakeMinute());
        values.put(KEY_VIBRATION, Boolean.toString(alarm.isVibration()));
        values.put(KEY_INCREASE_VOLUME, Boolean.toString(alarm.isIncreaseVolume()));
        values.put(KEY_VOLUME, alarm.getVolume());
        values.put(KEY_TYPE_SIGNAL, alarm.getTypeSignal());
        values.put(KEY_TRACK, alarm.getTrack());
        values.put(KEY_START_VOLUME, 0);
        values.put(KEY_POINT, alarm.getPoint());
        values.put(KEY_REPEAT, alarm.getRepeat());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateAlarm(Alarm alarm, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ON_OFF, Boolean.toString(alarm.isOn()));
        values.put(KEY_TITLE, alarm.getTitle());
        values.put(KEY_HOUR, alarm.getWakeHour());
        values.put(KEY_MINUTE, alarm.getWakeMinute());
        values.put(KEY_VIBRATION, Boolean.toString(alarm.isVibration()));
        values.put(KEY_INCREASE_VOLUME, Boolean.toString(alarm.isIncreaseVolume()));
        values.put(KEY_VOLUME, alarm.getVolume());
        values.put(KEY_TYPE_SIGNAL, alarm.getTypeSignal());
        values.put(KEY_TRACK, alarm.getTrack());
        values.put(KEY_START_VOLUME, 0);
        values.put(KEY_POINT, alarm.getPoint());
        values.put(KEY_REPEAT, alarm.getRepeat());

        db.update(TABLE_NAME, values, KEY_ID + "=" + id, null);
        db.close();
    }

    public List<Alarm> getAllAlarms() {
        List<Alarm> alarmList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Alarm alarm = new Alarm();
                alarm.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                alarm.setOn(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_ON_OFF))));
                alarm.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                alarm.setWakeHour(cursor.getInt(cursor.getColumnIndex(KEY_HOUR)));
                alarm.setWakeMinute(cursor.getInt(cursor.getColumnIndex(KEY_MINUTE)));
                alarm.setVibration(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_VIBRATION))));
                alarm.setIncreaseVolume(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_INCREASE_VOLUME))));
                alarm.setVolume(cursor.getInt(cursor.getColumnIndex(KEY_VOLUME)));
                alarm.setTypeSignal(cursor.getString(cursor.getColumnIndex(KEY_TYPE_SIGNAL)));
                alarm.setTrack(cursor.getString(cursor.getColumnIndex(KEY_TRACK)));
                alarm.setStartVolume(cursor.getString(cursor.getColumnIndex(KEY_START_VOLUME)));
                alarm.setPoint(cursor.getLong(cursor.getColumnIndex(KEY_POINT)));
                alarm.setRepeat(cursor.getString(cursor.getColumnIndex(KEY_REPEAT)));

                alarmList.add(alarm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return alarmList;
    }

    public Alarm getAlarm(int id) {

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " where " + KEY_ID + "='" + id + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Alarm alarm = new Alarm();
        if (cursor.moveToFirst()){
            alarm.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
            alarm.setOn(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_ON_OFF))));
            alarm.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            alarm.setWakeHour(cursor.getInt(cursor.getColumnIndex(KEY_HOUR)));
            alarm.setWakeMinute(cursor.getInt(cursor.getColumnIndex(KEY_MINUTE)));
            alarm.setVibration(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_VIBRATION))));
            alarm.setIncreaseVolume(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_INCREASE_VOLUME))));
            alarm.setVolume(cursor.getInt(cursor.getColumnIndex(KEY_VOLUME)));
            alarm.setTypeSignal(cursor.getString(cursor.getColumnIndex(KEY_TYPE_SIGNAL)));
            alarm.setTrack(cursor.getString(cursor.getColumnIndex(KEY_TRACK)));
            alarm.setStartVolume(cursor.getString(cursor.getColumnIndex(KEY_START_VOLUME)));
            alarm.setPoint(cursor.getLong(cursor.getColumnIndex(KEY_POINT)));
            alarm.setRepeat(cursor.getString(cursor.getColumnIndex(KEY_REPEAT)));
        }
        cursor.close();
        return alarm;
    }
}
