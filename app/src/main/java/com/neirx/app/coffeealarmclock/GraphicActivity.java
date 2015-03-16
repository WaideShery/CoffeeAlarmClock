package com.neirx.app.coffeealarmclock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Calendar;

/**
 * Created by Вадим on 10.03.2015.
 */
public class GraphicActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        View viewTop = findViewById(R.id.topLayout);
        View viewBottom = findViewById(R.id.bottomLayout);
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

        LinearLayout btnGraphic = (LinearLayout) findViewById(R.id.btn_alarms);
        btnGraphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
