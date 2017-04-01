package com.drolmen.swipeitemdemo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.drolmen.swipeitemdemo.R;

/**
 * Created by drolmen on 2017/3/28.
 */

public class SwipeItemView extends RelativeLayout {

    private GestureDetector detector;

    private int maxTranslate = 0;

//    private int topViewId;
    private View topView;

//    private int bottomViewId;
    private View bottomView;

    private boolean hasScroll;

    private boolean swipeEnable = true;

    public SwipeItemView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                hasScroll = false;     //重置
                return super.onDown(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                getParent().requestDisallowInterceptTouchEvent(true);
                if (topView.getTranslationX() >= 0 && distanceX < 0) {

                } else {
                    hasScroll = true;
                    float translate = -distanceX + topView.getTranslationX();
                    if (translate > 0) {
                        translate = 0;
                    } else if (translate < -maxTranslate) {
                        translate = -maxTranslate;
                    }
                    topView.setTranslationX(translate);
                }
                return false;
            }
        });

        if (attrs == null) {
            return;
        }
    }

    public SwipeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        //侧滑是否开启
        if (!swipeEnable) {
            return super.onInterceptTouchEvent(ev);
        }

        // 是否要 拦截view事件
        boolean b = detector.onTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_UP
                || ev.getAction() == MotionEvent.ACTION_CANCEL) {

            if (-topView.getTranslationX() > maxTranslate / 2) {
                //滑到底
                topView.setTranslationX(-maxTranslate);
            } else {
                //滑到初始位置
                topView.setTranslationX(0);
            }

            return hasScroll ? true : b;
        }
        return b;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        topView = findViewById(R.id.top_view);
        bottomView = findViewById(R.id.bottom_view);
        bottomView.post(new Runnable() {
            @Override
            public void run() {
                maxTranslate = bottomView.getWidth();
            }
        });
    }

    public void setSwipeEnable(boolean b) {
        swipeEnable = b;
    }
}
