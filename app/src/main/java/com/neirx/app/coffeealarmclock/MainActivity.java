package com.neirx.app.coffeealarmclock;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.neirx.app.coffeealarmclock.fragments.AlarmTimeFragment;
import com.neirx.app.coffeealarmclock.fragments.GraphicFragment;


public class MainActivity extends Activity {
    private static final String TAG = "ThisApp";

    private TabBarView tabBarView;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pager);

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


        LayoutInflater inflator =
                (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflator.inflate(R.layout.custom_ab, null);
        tabBarView = (TabBarView) v.findViewById(R.id.tab_bar);
        ((LinearLayout)viewTop).addView(tabBarView);


        // Создание адаптера, который будет возвращать фрагмент для каждоц из
        // основных секций/разделов активити.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Настраивает ViewPager адаптером секций/разделов.

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabBarView.setViewPager(mViewPager);




    }

    //**********Меню************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //*******************************


    /**
     * Наследуется от {Android.support.v13.app.FragmentPagerAdapter}. Возвращает фрагмент,
     * соответствующий одному из разделов/вкладок/страниц.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter implements TabBarView.IconTabProvider {

        private int[] tab_icons={R.drawable.menu_alarm_on,
                R.drawable.menu_graph_on,
        };


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem вызывается для получения экземпляра фрагмента для данной страницы.
            // Возвращает PlaceholderFragment (определен как статический внутренний класс ниже).
            if(position == 0) {
                return AlarmTimeFragment.newInstance();
            } else {
                return GraphicFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            // Показывает общее количество страниц.
            return tab_icons.length;
        }

        @Override
        public int getPageIconResId(int position) {
            return tab_icons[position];
        }

    }
}
