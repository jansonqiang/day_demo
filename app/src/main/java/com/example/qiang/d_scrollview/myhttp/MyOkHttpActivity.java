package com.example.qiang.d_scrollview.myhttp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.qiang.d_scrollview.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的ok http
 * Created by qiang on 2015/10/30.
 */
public class MyOkHttpActivity extends Activity {


    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.but)
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_ok);
        ButterKnife.bind(this);


    }


}
