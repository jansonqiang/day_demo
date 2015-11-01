package com.example.qiang.d_scrollview;

import android.app.Application;

import com.example.qiang.d_scrollview.myhttp.utils.OkHttpClientManager;

/**
 * Created by qiang on 2015/10/30.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClientManager.init(this);

    }
}
