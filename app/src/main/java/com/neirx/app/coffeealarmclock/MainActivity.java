package com.neirx.app.coffeealarmclock;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Calendar;


public class MainActivity extends Activity {
    private static final String TAG = "ThisApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.topLayout);
        BitmapDrawable bd = (BitmapDrawable) getResources().getDrawable(R.drawable.bg_tb);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        int intrinsicHeight = bd.getIntrinsicHeight();

        Rect bounds = new Rect(0,0,width, intrinsicHeight);
        bd.setTileModeX(Shader.TileMode.REPEAT);
        bd.setBounds(bounds);
        Bitmap bitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), bd.getBitmap().getConfig());
        Canvas canvas = new Canvas(bitmap);
        bd.draw(canvas);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        view.setBackground(bitmapDrawable);

        CustomDigitalClock dc = (CustomDigitalClock) findViewById(R.id.dgClock);

        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        switch (today) {
            case Calendar.MONDAY:
                findViewById(R.id.tvMon).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.TUESDAY:
                findViewById(R.id.tvTue).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.WEDNESDAY:
                findViewById(R.id.tvWed).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.THURSDAY:
                findViewById(R.id.tvThu).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.FRIDAY:
                findViewById(R.id.tvFri).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.SATURDAY:
                findViewById(R.id.tvSat).setBackgroundResource(R.drawable.line);
                break;
            case Calendar.SUNDAY:
                findViewById(R.id.tvSun).setBackgroundResource(R.drawable.line);
                break;
            default:
                break;
        }
    }


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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
