package com.example.qiang.d_scrollview.myTouch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**测试startScroll
 * Created by qiang on 2015/10/22.
 */
public class MyTouchScrooer extends LinearLayout {


    Scroller mScroller;

    Context mContext;
    public MyTouchScrooer(Context context) {
        super(context);
    }

    public MyTouchScrooer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    
    private void init(Context context){

        mScroller = new Scroller(context);

        this.mContext = context;


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if(event.getAction()==MotionEvent.ACTION_UP){

        mScroller.startScroll(0,0,-0,-100);
         //   mScroller.startScroll(0,0,-1500,-0);
            invalidate();

        }


        return true;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();


        if(mScroller.computeScrollOffset()){

            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();



        }

    }
}
