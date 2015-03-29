package com.neirx.app.coffeealarmclock;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.neirx.app.coffeealarmclock.fragments.PrefTopFragment;
import com.neirx.app.coffeealarmclock.fragments.PreferFragment;
import com.neirx.app.coffeealarmclock.fragments.SetAlarmFragment;


public class MainActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener{
    public static final String TAG = "ThisApp";

    ImageView iconAlarm, iconGraphic, iconAddAlarm;
    FrameLayout menuFrame, addAlarmFrame;
    ViewPager pager;
    FragmentManager fragmentManager;
    Fragment replaceFragment, topFragment, bottomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        topFragment = fragmentManager.findFragmentById(R.id.fragmentTop);
        bottomFragment = fragmentManager.findFragmentById(R.id.fragmentBottom);
        replaceFragment = fragmentManager.findFragmentById(R.id.fragmentReplace);


        pager = (ViewPager) replaceFragment.getView().findViewById(R.id.pager);
        iconAlarm = (ImageView) topFragment.getView().findViewById(R.id.iconAlarm);
        iconGraphic = (ImageView) topFragment.getView().findViewById(R.id.iconGraphic);
        iconAddAlarm = (ImageView) bottomFragment.getView().findViewById(R.id.iconAddAlarm);
        menuFrame = (FrameLayout) topFragment.getView().findViewById(R.id.frameMenu);
        addAlarmFrame = (FrameLayout) bottomFragment.getView().findViewById(R.id.frameAddAlarm);

        pager.setOnPageChangeListener(this);
        menuFrame.setOnClickListener(this);
        addAlarmFrame.setOnClickListener(this);




    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        float scale = this.getResources().getDisplayMetrics().density;
        if(position == 0){
            iconGraphic.getLayoutParams().height = (int) (35 * scale + 0.5f);
            iconGraphic.getLayoutParams().width = (int) (35 * scale + 0.5f);
            iconGraphic.setImageDrawable(getResources().getDrawable(R.drawable.menu_graph_off));

            iconAlarm.getLayoutParams().height = (int) (40 * scale + 0.5f);
            iconAlarm.getLayoutParams().width = (int) (40 * scale + 0.5f);
            iconAlarm.setImageDrawable(getResources().getDrawable(R.drawable.menu_alarm_on));
        } else if(position == 1){
            iconGraphic.getLayoutParams().height = (int) (40 * scale + 0.5f);
            iconGraphic.getLayoutParams().width = (int) (40 * scale + 0.5f);
            iconGraphic.setImageDrawable(getResources().getDrawable(R.drawable.menu_graph_on));

            iconAlarm.getLayoutParams().height = (int) (35 * scale + 0.5f);
            iconAlarm.getLayoutParams().width = (int) (35 * scale + 0.5f);
            iconAlarm.setImageDrawable(getResources().getDrawable(R.drawable.menu_alarm_off));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frameMenu:
                showOverflowMenu(v);
                break;
            case R.id.frameAddAlarm:
                Fragment setAlarmFragment = SetAlarmFragment.newInstance();
                Fragment prefTopFragment = PrefTopFragment.newInstance();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(replaceFragment);
                fragmentTransaction.add(R.id.container, setAlarmFragment);
                fragmentTransaction.hide(topFragment);
                fragmentTransaction.add(R.id.containerTop, prefTopFragment);
                fragmentTransaction.addToBackStack(null);
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
                switch (item.getItemId()){
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
