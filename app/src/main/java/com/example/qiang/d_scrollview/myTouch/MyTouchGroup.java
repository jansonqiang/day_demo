package com.example.qiang.d_scrollview.myTouch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**测试触摸事件的分发 onInterceptTouchEvent
 * onInterceptTouchEvent是ViewGroup提供的方法，默认返回false，返回true表示拦截。
 * Created by qiang on 2015/10/20.
 */
public class MyTouchGroup extends LinearLayout {


    public static final String TAG=MyTouchGroup.class.getSimpleName();


    Scroller mScroller ;

    Context mContext;

    private float mLastionMotionX = 0 ;

    private VelocityTracker mVelocityTracker = null ;

    int childHights[];

    int childCountHight;

    public MyTouchGroup(Context context) {
        super(context);
        init(context);

    }


    public MyTouchGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){

        this.mContext = context;

        mScroller = new Scroller(context);


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        //Log.d(TAG,"dispatchTouchEvent  evName = "+TouchUtils.getActionName(ev.getAction()));

        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);




    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        super.onLayout(changed, l, t, r, b);


        childHights = new int[getChildCount()];



        for (int i =0; i<getChildCount();i++){
            View view = getChildAt(i);

            childHights[i] =view.getMeasuredHeight();

            childCountHight += childHights[i];

        }
    }




    /***
     * 拦截某些事件  分发与拦截  分发会分发到自己的onTouchEvent ,
     * @param ev
     * @return  true拦截 , false不拦截  父类只返回false
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent  evName = " + TouchUtils.getActionName(ev.getAction()));


        if(ev.getAction()==MotionEvent.ACTION_MOVE){
            return true;
        }else
        return super.onInterceptTouchEvent(ev);
    }



    @Override
    public boolean onTouchEvent(MotionEvent ev){
        Log.d(TAG, "onTouchEvent  evName = " + TouchUtils.getActionName(ev.getAction()));
        if (mVelocityTracker == null) {

            Log.e(TAG, "onTouchEvent start-------** VelocityTracker.obtain");

            mVelocityTracker = VelocityTracker.obtain();
        }

        mVelocityTracker.addMovement(ev);


        final float  x = ev.getX();
        final float y =ev.getY();


        if(ev.getAction()==MotionEvent.ACTION_DOWN){


            mLastionMotionX = y;
            Log.d(TAG, " ACTION_DOWN mLastionMotionX  = "+mLastionMotionX  +" ey ="+y);
            return true;
        }


        if(ev.getAction()==MotionEvent.ACTION_MOVE){


            float zuni = 1;

            boolean resutl ;

            if(getScrollY()>0&&getScrollY()<childCountHight-childHights[childHights.length-1]){
                zuni = 1.5f;
            }else{
                zuni = 0.5f;
            }


           int resultY =  (int) ((mLastionMotionX- y)*zuni);

            scrollBy(0, resultY);//太慢了 快一点


            mLastionMotionX = y;

            Log.d(TAG, " ACTION_MOVE mLastionMotionX  = " + mLastionMotionX + " ey =" + y+" resultY"+resultY);


            return true;

        }

        if(ev.getAction()==MotionEvent.ACTION_UP){
           // scrollTo(0,0);
            int reusltY = 0;
            if(getScrollY()>0&&getScrollY()<childCountHight){

                reusltY = scrollToLayout(getScrollY());
            }else if(getScrollY()<0){
                reusltY = 0;
            }else{
                reusltY = childCountHight;
            }

          //  scrollTo(0,reusltY);

            mScroller.startScroll(getScrollX(), getScrollY(), 0, reusltY);


            mLastionMotionX = reusltY;



            return true;
        }



        return super.onTouchEvent(ev);
    }



    private int scrollToLayout(int y){

        int result =0;

        int lastHight = 0;





        for (int i = 0; i <childHights.length ; i++) {


            int childHigt = childHights[i];



            if(y>lastHight&&y<childHigt/2+lastHight){

                result =lastHight;
                break;
            }else if(y>lastHight&&y<childHigt+lastHight){

                result = lastHight+childHigt;
                break;
            }


             lastHight += childHights[i];
        }

        return result;

    }




    @Override
    public void computeScroll() {


        if (mScroller.computeScrollOffset()) {

            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }

        super.computeScroll();
    }
}
