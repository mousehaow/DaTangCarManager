package com.datang.datangcarmanager.presentation.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.view
 * @class describe
 * @anthor toby QQ:1032006226
 * @time 16/11/15 下午12:04
 * @change
 * @chang time
 * @class describe
 */
public class APSTSViewPager extends ViewPager {
    private boolean mNoFocus = false; //if true, keep View don't move
    public APSTSViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public APSTSViewPager(Context context){
        this(context,null);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mNoFocus) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    public void setNoFocus(boolean b){
        mNoFocus = b;
    }
}

