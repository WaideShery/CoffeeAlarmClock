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


public class MainActivity extends Activity implements View.OnClickListener {
    public static final String TAG = "ThisApp";

    FragmentManager fragmentManager;
    Fragment replaceFragment, topFragment, bottomFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        topFragment = TopFragment.newInstance();
        //fragmentTransaction.add(R.id.containerTop, topFragment);

        replaceFragment = ReplaceFragment.newInstance();
        fragmentTransaction.add(R.id.container, replaceFragment);

        fragmentTransaction.commit();
        FrameLayout addAlarmFrame = (FrameLayout) (replaceFragment.getChildFragmentManager()
                .findFragmentById(R.id.fragmentBottom)).getView().findViewById(R.id.frameAddAlarm);
        addAlarmFrame.setOnClickListener(this);


        /*
        iconAddAlarm = (ImageView) bottomFragment.getView().findViewById(R.id.iconAddAlarm);

        addAlarmFrame = (FrameLayout) bottomFragment.findViewById(R.id.frameAddAlarm);

        addAlarmFrame.setOnClickListener(this);
        */

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frameMenu:
                showOverflowMenu(v);
                break;
            case R.id.frameAddAlarm:
                fragmentTransaction.remove(replaceFragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

        }
    }

    private void showOverflowMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_main);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Fragment prefFragment = PreferFragment.newInstance();
                        Fragment prefTopFragment = PrefTopFragment.newInstance();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.hide(replaceFragment);
                        fragmentTransaction.add(R.id.container, prefFragment);
                        fragmentTransaction.hide(topFragment);
                        fragmentTransaction.add(R.id.containerTop, prefTopFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        return true;
                    default:
                        return false;
                }

            }
        });
        popupMenu.show();
    }


}
