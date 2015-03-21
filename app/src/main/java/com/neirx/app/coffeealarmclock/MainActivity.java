package com.neirx.app.coffeealarmclock;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.neirx.app.coffeealarmclock.fragments.AlarmTimeFragment;
import com.neirx.app.coffeealarmclock.fragments.GraphicFragment;
import com.neirx.app.coffeealarmclock.fragments.PreferFragment;


public class MainActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static final String TAG = "ThisApp";

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    ImageView iconAlarm, iconGraphic, iconMenu;
    FrameLayout menuFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View viewTop = findViewById(R.id.top_bar);
        View viewBottom = findViewById(R.id.bottom_bar);
        BitmapDrawable bdTop = (BitmapDrawable) getResources().getDrawable(R.drawable.bg_tb);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        int intrinsicHeight = bdTop.getIntrinsicHeight();

        Rect bounds = new Rect(0,0,width, intrinsicHeight);
        bdTop.setTileModeX(Shader.TileMode.REPEAT);
        bdTop.setBounds(bounds);
        Bitmap bitmapTop = Bitmap.createBitmap(bounds.width(), bounds.height(), bdTop.getBitmap().getConfig());
        Canvas canvasTop = new Canvas(bitmapTop);
        bdTop.draw(canvasTop);
        viewTop.setBackground(new BitmapDrawable(bitmapTop));

        Matrix mat = new Matrix();
        mat.postRotate(180);
        Bitmap bMapRotate = Bitmap.createBitmap(bitmapTop, 0, 0,
                bitmapTop.getWidth(), bitmapTop.getHeight(), mat, true);
        viewBottom.setBackground(new BitmapDrawable(bMapRotate));



        // Создание адаптера, который будет возвращать фрагмент для каждоц из
        // основных секций/разделов активити.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Настраивает ViewPager адаптером секций/разделов.

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(this);

        iconAlarm = (ImageView) findViewById(R.id.icon_alarm);
        iconGraphic = (ImageView) findViewById(R.id.icon_graphic);

        iconMenu = (ImageView) findViewById(R.id.icon_menu);

        menuFrame = (FrameLayout) findViewById(R.id.main_menu);
        menuFrame.setOnClickListener(this);


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
            case R.id.main_menu:
                showOverflowMenu(v);
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
                        PreferencePagerAdapter prefPagerAdapter = new PreferencePagerAdapter(getFragmentManager());
                        mViewPager.setAdapter(prefPagerAdapter);
                        mViewPager.setOnPageChangeListener(null);
                        return true;
                    default:
                        return false;
                }

            }
        });
        popupMenu.show();
    }


    //*******************************


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
            Log.d(TAG, "getItem");
            // getItem вызывается для получения экземпляра фрагмента для данной страницы.
            if(position == 0) {
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

    public class PreferencePagerAdapter extends FragmentPagerAdapter {



        public PreferencePagerAdapter (FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem вызывается для получения экземпляра фрагмента для данной страницы.
                return PreferFragment.newInstance();
        }

        @Override
        public int getCount() {
            // Показывает общее количество страниц.
            return 1;
        }

    }
}
