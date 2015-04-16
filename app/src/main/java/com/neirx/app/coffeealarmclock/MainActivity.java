package com.neirx.app.coffeealarmclock;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.neirx.app.coffeealarmclock.fragments.AlarmsFragment;
import com.neirx.app.coffeealarmclock.fragments.PrefTopFragment;
import com.neirx.app.coffeealarmclock.fragments.PreferFragment;
import com.neirx.app.coffeealarmclock.fragments.ReplaceFragment;
import com.neirx.app.coffeealarmclock.fragments.SetAlarmFragment;
import com.neirx.app.coffeealarmclock.fragments.TopFragment;


public class MainActivity extends Activity {
    public static final String TAG = "ThisApp";

    FragmentManager fragmentManager;
    ReplaceFragment replaceFragment;

    public TopFragment getTopFragment() {
        return topFragment;
    }

    TopFragment topFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        topFragment = TopFragment.newInstance();
        fragmentTransaction.add(R.id.containerTop, topFragment);

        replaceFragment = ReplaceFragment.newInstance();
        fragmentTransaction.add(R.id.container, replaceFragment);

        fragmentTransaction.commit();

    }

    public void replaceSetAlarmFragment(){
        Fragment setAlarmFragment = SetAlarmFragment.newInstance();
        Fragment prefTopFragment = PrefTopFragment.newInstance();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, setAlarmFragment);
        fragmentTransaction.replace(R.id.containerTop, prefTopFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void replacePreferFragment(){
        Fragment prefFragment = PreferFragment.newInstance();
        Fragment prefTopFragment = PrefTopFragment.newInstance();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, prefFragment);
        fragmentTransaction.replace(R.id.containerTop, prefTopFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void replaceAlarmsFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, replaceFragment);
        fragmentTransaction.replace(R.id.containerTop, topFragment);
        fragmentTransaction.commit();
    }
}
