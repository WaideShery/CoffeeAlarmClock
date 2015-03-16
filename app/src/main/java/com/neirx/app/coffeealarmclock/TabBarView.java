package com.neirx.app.coffeealarmclock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;

public class TabBarView extends LinearLayout {
	public interface IconTabProvider {
		public int getPageIconResId(int position);
	}


	private static final int STRIP_HEIGHT = 6;

	public final Paint mPaint;

	private int mStripHeight;
	private float mOffset = 0f;
	public static int mSelectedTab = 0;
	public ViewPager pager;

	public static int tabCount;
	private final PageListener pageListener = new PageListener();
	public OnPageChangeListener delegatePageListener;

	private View child;

	private View nextChild;

	public static int a;

	public TabBarView(Context context) {
		this(context, null);
	}

	public TabBarView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.actionBarTabBarStyle);
	}

	public TabBarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

        setWillNotDraw(false);

		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		mPaint.setAntiAlias(true);

		mStripHeight = (int) (STRIP_HEIGHT * getResources().getDisplayMetrics().density + .5f);
    }



	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// Draw the strip manually
		child = getChildAt(mSelectedTab);
		int height = getHeight();
		if (child != null) {
			float left = child.getLeft();
			float right = child.getRight();
			if (mOffset >  0f && mSelectedTab < tabCount - 1 ) {
				nextChild = getChildAt(mSelectedTab + 1);
				if (nextChild != null) {
					final float nextTabLeft = nextChild.getLeft();
					final float nextTabRight = nextChild.getRight();
					left =  (mOffset * nextTabLeft + (1f - mOffset) * left);
					right = (mOffset * nextTabRight + (1f - mOffset) * right);
				}
			}
			canvas.drawRect(left, height - mStripHeight, right, height, mPaint);
		}
	}

	public void setViewPager(ViewPager pager) {
		this.pager = pager;

		if (pager.getAdapter() == null) {
			throw new IllegalStateException("ViewPager does not have adapter instance.");
		}

		pager.setOnPageChangeListener(pageListener);

		notifyDataSetChanged();
	}
	
	private class PageListener implements OnPageChangeListener {

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			mSelectedTab = position;
			mOffset = positionOffset;

			invalidate();

			if (delegatePageListener != null) {
				delegatePageListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE) {

			}

			if (delegatePageListener != null) {
				delegatePageListener.onPageScrollStateChanged(state);
			}
		}

		@Override
		public void onPageSelected(int position) {
			if (delegatePageListener != null) {
				delegatePageListener.onPageSelected(position);
			}
		}

	}

	public void notifyDataSetChanged() {

		this.removeAllViews();
		
		tabCount = pager.getAdapter().getCount();

		for (int i = 0; i < tabCount; i++) {
            addTabView(i, ((IconTabProvider) pager.getAdapter()).getPageIconResId(i));
		}
		
		getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @SuppressLint("NewApi")
            @Override
            public void onGlobalLayout() {

                getViewTreeObserver().removeOnGlobalLayoutListener(this);

                mSelectedTab = pager.getCurrentItem();

            }
        });

	}

	private void addTabView(final int i, int pageIconResId) {
		// TODO Auto-generated method stub
		TabView tab = new TabView(getContext());
		tab.setIcon(pageIconResId);
		tab.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pager.setCurrentItem(i);
			}
		});

		this.addView(tab);
	}

}