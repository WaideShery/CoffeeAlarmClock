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

public class ReplaceFragment extends Fragment implements ViewPager.OnPageChangeListener {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    FragmentManager fragmentManager;
    Fragment bottomFragment;

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

        fragmentManager = getActivity().getFragmentManager();
        bottomFragment = BottomFragment.newInstance();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragBottom, bottomFragment);
        fragmentTransaction.commit();



        // Создание адаптера, который будет возвращать фрагмент для каждоц из
        // основных секций/разделов активити.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Настраивает ViewPager адаптером секций/разделов.

        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOnPageChangeListener(this);

        return rootView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (getActivity() != null) {
            MainActivity activity = (MainActivity) getActivity();
            TopFragment topFragment = activity.getTopFragment();
            if (position == 0) {
                topFragment.setIconAlarmChanged();
            } else if (position == 1) {
                topFragment.setIconGraphicChanged();
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
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


