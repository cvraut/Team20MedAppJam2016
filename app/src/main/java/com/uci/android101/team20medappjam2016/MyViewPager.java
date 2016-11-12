package com.uci.android101.team20medappjam2016;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by codyx on 11/11/2016.
 */

public class MyViewPager extends ViewPager {
    private boolean isPagingEnabled;
    public MyViewPager(Context context, AttributeSet attributes) {
        super(context, attributes);
        this.isPagingEnabled = false;
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }

    public boolean isPagingEnabled() {
        return isPagingEnabled;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }


}
