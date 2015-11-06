package com.example.qiang.d_scrollview.myhttp;

/**
 * Created by qiang on 2015/10/27.
 */
public class Repo<U> {


    public int errCode;

    public   String errMsg;


    @Override
    public String toString() {
        return errCode+errMsg;
    }
}
