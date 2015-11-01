package com.example.qiang.d_scrollview.measure;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 用来研究
 * Created by qiang on 2015/10/23.
 */
public class MeasureView extends View {

    public static final String TAG = MeasureView.class.getSimpleName();

    Context mConcext;

    public MeasureView(Context context) {
        super(context);
        init(context);
    }

    public MeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        this.mConcext = context;

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        //Log.d(TAG,String.format("widthMode = %i ,heightMode = %i , widthSize = %i,");




        }



}
