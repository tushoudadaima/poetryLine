package com.example.poetryline.detail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    private boolean scroll = true;

    public MyScrollView(Context context) {
        this(context,null);
    }
    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setScroll(boolean scroll) {
        this.scroll = scroll;

    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //关键点在这
        if (scroll) {
            getParent().requestDisallowInterceptTouchEvent(true);
            return super.onInterceptTouchEvent(ev);
        }else {
            return true;
        }
    }

}

