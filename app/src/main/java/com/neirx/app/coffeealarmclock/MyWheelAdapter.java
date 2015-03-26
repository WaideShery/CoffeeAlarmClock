package com.neirx.app.coffeealarmclock;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import com.neirx.app.coffeealarmclock.customs.timepicker.WheelViewAdapter;

public class MyWheelAdapter implements WheelViewAdapter {
    @Override
    public int getItemsCount() {
        return 0;
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getEmptyItem(View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }
}
