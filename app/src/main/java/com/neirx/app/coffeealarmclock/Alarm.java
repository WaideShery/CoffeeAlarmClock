package com.neirx.app.coffeealarmclock;


public class Alarm {

    int id;
    private boolean isOn;
    private String title;
    private int wakeHour;
    private int wakeMinute;
    private String repeat;// -
    private String date;// -
    private String typeSignal;// -
    private String track;// -
    private boolean isVibration;
    private int volume;
    private boolean isIncreaseVolume;
    private String startVolume;// -

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    private long point;

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

    public int getWakeHour() {
        return wakeHour;
    }

    public int getWakeMinute() {
        return wakeMinute;
    }

    public void setWakeHour(int wakeHour) {
        this.wakeHour =  wakeHour;
    }

    public void setWakeMinute(int wakeMinute) {
        this.wakeMinute = wakeMinute;
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getStartVolume() {
        return startVolume;
    }

    public void setStartVolume(String startVolume) {
        this.startVolume = startVolume;
    }

}
