package com.example.qiang.d_scrollview.myTouch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 *
 * 测试触摸事件的分发  onTouchEvent
 * onTouchEvent是View中提供的方法，ViewGroup也有这个方法，view中不提供onInterceptTouchEvent。view中默认返回true，表示消费了这个事件。
 * Created by qiang on 2015/10/20.
 */
public class MyTouchView extends View {

    public static final String TAG=MyTouchView.class.getSimpleName();


    public MyTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchView(Context context) {
        super(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

      //  Log.d(TAG,"onTouchEvent  evName = "+TouchUtils.getActionName(event.getAction()));








        return super.onTouchEvent(event);
    }
}
