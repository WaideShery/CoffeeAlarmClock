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
import com.neirx.app.coffeealarmclock.fragments.TopSetAlarmFragment;


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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MainActivity.TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MainActivity.TAG, "onRestart");
        replaceAlarmsFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MainActivity.TAG, "onResume");
    }

    public void replaceSetAlarmFragment(int id){
        Bundle bundle = new Bundle();
        bundle.putInt("alarmId", id);
        Fragment setAlarmFragment = SetAlarmFragment.newInstance();
        setAlarmFragment.setArguments(bundle);
        Fragment topSetAlarmFragment = TopSetAlarmFragment.newInstance();
        topSetAlarmFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, setAlarmFragment);
        fragmentTransaction.replace(R.id.containerTop, topSetAlarmFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void replaceSetAlarmFragment(){
        replaceSetAlarmFragment(0);
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
        topFragment = TopFragment.newInstance();
        replaceFragment = ReplaceFragment.newInstance();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, replaceFragment);
        fragmentTransaction.replace(R.id.containerTop, topFragment);
        fragmentTransaction.commit();
    }

}
