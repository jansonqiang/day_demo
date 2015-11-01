package com.example.qiang.d_scrollview.myTouch;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.qiang.d_scrollview.R;

/** 测试触摸事件的分发
 * dispatchTouchEvent是处理触摸事件分发,事件(多数情况)是从Activity的dispatchTouchEvent开始的。执行
    super.dispatchTouchEvent(ev)，事件向下分发。
 * Created by qiang on 2015/10/20.
 */
public class MyTouchActivity extends Activity{

    public static final String TAG=MyTouchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mytouch);





    }


    /***
     * false 为不分发
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

       // Log.d(TAG,"dispatchTouchEvent  evName = "+TouchUtils.getActionName(ev.getAction()));

        return super.dispatchTouchEvent(ev);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {

       // Log.d(TAG,"onTouchEvent  evName = "+TouchUtils.getActionName(event.getAction()));
        return super.onTouchEvent(event);

        // return  false;
          }
}
