package com.example.qiang.d_scrollview.myhttp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.qiang.d_scrollview.R;
import com.example.qiang.d_scrollview.myhttp.utils.OkHttpClientManager;
import com.squareup.okhttp.Request;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的ok http
 * Created by qiang on 2015/10/30.
 */
public class MyOkHttpActivity extends Activity {


    @Bind(R.id.textView)
    TextView textView;


    public static final String TAG = MyOkHttpActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_ok);
        ButterKnife.bind(this);





    }

    @OnClick(R.id.but) void pushDate(){

        OkHttpClientManager.getAsyn(this,"https://efuservice.taskmed.com.cn", new OkHttpClientManager.ResultCallback<Repo>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Repo response) {



                Log.d(TAG, response.toString());


            }

        });

    }


}
