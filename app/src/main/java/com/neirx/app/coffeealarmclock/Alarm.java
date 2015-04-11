package com.neirx.app.coffeealarmclock;


public class Alarm {

    int id;
    private boolean isOn;
    private String title;
    private String wakeTime;// -
    private String repeat;// -
    private String date;// -
    private String typeSignal;// -
    private String track;// -
    private boolean isVibration;
    private String volume;// -
    private boolean isIncreaseVolume;
    private String startVolume;// -

    public Alarm(int id, boolean isOn, String title, String wakeTime, String repeat, String date,
                 String typeSignal, String track, boolean isVibration, String volume,
                 boolean isIncreaseVolume, String startVolume) {
        this.id = id;
        this.isOn = isOn;
        this.title = title;
        this.wakeTime = wakeTime;
        this.repeat = repeat;
        this.date = date;
        this.typeSignal = typeSignal;
        this.track = track;
        this.isVibration = isVibration;
        this.volume = volume;
        this.isIncreaseVolume = isIncreaseVolume;
        this.startVolume = startVolume;
    }

    public Alarm(boolean isOn, String title, String wakeTime, String repeat, String date,
                 String typeSignal, String track, boolean isVibration, String volume,
                 boolean isIncreaseVolume, String startVolume) {
        new Alarm(0, isOn, title, wakeTime, repeat, date, typeSignal, track, isVibration,
                volume, isIncreaseVolume, startVolume);
    }
    public Alarm(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVibration() {
        return isVibration;
    }

    public void setVibration(boolean isVibration) {
        this.isVibration = isVibration;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    public boolean isIncreaseVolume() {
        return isIncreaseVolume;
    }

    public void setIncreaseVolume(boolean isIncreaseVolume) {
        this.isIncreaseVolume = isIncreaseVolume;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(String wakeTime) {
        this.wakeTime = wakeTime;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeSignal() {
        return typeSignal;
    }

    public void setTypeSignal(String typeSignal) {
        this.typeSignal = typeSignal;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getStartVolume() {
        return startVolume;
    }

    public void setStartVolume(String startVolume) {
        this.startVolume = startVolume;
    }

    public static enum Signal {
        music,
    }
}
