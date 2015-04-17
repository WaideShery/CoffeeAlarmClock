package com.neirx.app.coffeealarmclock.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.neirx.app.coffeealarmclock.DBHelper;
import com.neirx.app.coffeealarmclock.MainActivity;
import com.neirx.app.coffeealarmclock.R;

public class TopSetAlarmFragment extends Fragment implements View.OnClickListener {
    FrameLayout editFrame;
    TextView tvTite;
    LinearLayout goBack;
    int id = 0;

    public static TopSetAlarmFragment newInstance() {
        TopSetAlarmFragment fragment = new TopSetAlarmFragment();
        return fragment;
    }

    public TopSetAlarmFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_setalarm_top, container, false);
        id = getArguments().getInt("alarmId");

        View viewTop = rootView.findViewById(R.id.topBar);
        BitmapDrawable bdTop = (BitmapDrawable) getResources().getDrawable(R.drawable.sett_bg);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        int intrinsicHeight = bdTop.getIntrinsicHeight();

        Rect bounds = new Rect(0,0,width, intrinsicHeight);
        bdTop.setTileModeX(Shader.TileMode.REPEAT);
        bdTop.setBounds(bounds);
        Bitmap bitmapTop = Bitmap.createBitmap(bounds.width(), bounds.height(), bdTop.getBitmap().getConfig());
        Canvas canvasTop = new Canvas(bitmapTop);
        bdTop.draw(canvasTop);
        viewTop.setBackground(new BitmapDrawable(bitmapTop));

        editFrame = (FrameLayout) rootView.findViewById(R.id.editFrame);
        goBack = (LinearLayout) rootView.findViewById(R.id.go_back);
        tvTite = (TextView) rootView.findViewById(R.id.tvTite);
        if(id > 0) {
            editFrame.setOnClickListener(this);
            tvTite.setText("Редактирование будильника");
        } else {
            editFrame.setVisibility(View.GONE);
            tvTite.setText("Новый будильник");
        }

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editFrame:
                showOverflowMenu(v);
                break;
        }
    }

    private void showOverflowMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        popupMenu.inflate(R.menu.menu_edit);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        if (id > 0){
                            DBHelper dbHelper = new DBHelper(getActivity());
                            dbHelper.deleteAlarm(id);
                            getActivity().getFragmentManager().popBackStack();
                        }
                        return true;
                    default:
                        return false;
                }

            }
        });
        popupMenu.show();
    }
}
