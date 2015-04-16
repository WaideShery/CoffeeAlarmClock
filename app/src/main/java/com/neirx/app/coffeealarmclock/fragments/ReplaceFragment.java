package com.neirx.app.coffeealarmclock.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.neirx.app.coffeealarmclock.AlarmAdapter;
import com.neirx.app.coffeealarmclock.MainActivity;
import com.neirx.app.coffeealarmclock.R;

public class ReplaceFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    FragmentManager fragmentManager;
    Fragment topFragment, bottomFragment;
    ImageView iconAlarm, iconGraphic, iconAddAlarm;
    FrameLayout addAlarmFrame;

    public static ReplaceFragment newInstance() {
        ReplaceFragment fragment = new ReplaceFragment();
        return fragment;
    }

    public ReplaceFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_replace, container, false);

        fragmentManager = getFragmentManager();
        topFragment = fragmentManager.findFragmentById(R.id.fragmentTop);
        iconAlarm = (ImageView) topFragment.getView().findViewById(R.id.iconAlarm);
        iconGraphic = (ImageView) topFragment.getView().findViewById(R.id.iconGraphic);

        // Создание адаптера, который будет возвращать фрагмент для каждоц из
        // основных секций/разделов активити.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Настраивает ViewPager адаптером секций/разделов.

        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOnPageChangeListener(this);

        bottomFragment = getChildFragmentManager().findFragmentById(R.id.fragmentBottom);

        addAlarmFrame = (FrameLayout) bottomFragment.getView().findViewById(R.id.frameAddAlarm);

        addAlarmFrame.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        float scale = this.getResources().getDisplayMetrics().density;
        if (position == 0) {
            iconGraphic.getLayoutParams().height = (int) (35 * scale + 0.5f);
            iconGraphic.getLayoutParams().width = (int) (35 * scale + 0.5f);
            iconGraphic.setImageDrawable(getResources().getDrawable(R.drawable.menu_graph_off));

            iconAlarm.getLayoutParams().height = (int) (40 * scale + 0.5f);
            iconAlarm.getLayoutParams().width = (int) (40 * scale + 0.5f);
            iconAlarm.setImageDrawable(getResources().getDrawable(R.drawable.menu_alarm_on));
        } else if (position == 1) {
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
        switch (v.getId()) {
            case R.id.frameAddAlarm:

                Fragment setAlarmFragment = SetAlarmFragment.newInstance();
                Fragment prefTopFragment = PrefTopFragment.newInstance();
                Fragment replaceFragment = ReplaceFragment.newInstance();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.replace(R.id.container, setAlarmFragment);
                //fragmentTransaction.replace(R.id.containerTop, prefTopFragment);
                fragmentTransaction.remove(replaceFragment);
                fragmentTransaction.commit();
                break;

        }
    }


    /**
     * Наследуется от {Android.support.v13.app.FragmentPagerAdapter}. Возвращает фрагмент,
     * соответствующий одному из разделов/вкладок/страниц.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem вызывается для получения экземпляра фрагмента для данной страницы.
            if (position == 0) {
                return AlarmTimeFragment.newInstance();
            } else {
                return GraphicFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            // Показывает общее количество страниц.
            return 2;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }
}


