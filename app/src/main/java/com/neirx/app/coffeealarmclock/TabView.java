package com.neirx.app.coffeealarmclock;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabView extends LinearLayout {

    private ImageView mImageView;

    public TabView(Context context) {
        this(context, null);
    }

	public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.actionBarTabStyle);
    }

    public TabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mImageView = new ImageView(context);
        mImageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mImageView.setScaleType(ScaleType.CENTER_INSIDE);
        this.addView(mImageView);
        this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

	public void setIcon(int resId) {
        setIcon(getContext().getResources().getDrawable(resId));
    }

    public void setIcon(Drawable icon) {
        if (icon != null) {
            mImageView.setVisibility(VISIBLE);
            mImageView.setImageDrawable(icon);
        } else {
            mImageView.setImageResource(GONE);
        }
    }

}