package com.example.qiang.d_scrollview.retrolambda;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.qiang.d_scrollview.R;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 研究Retrolambda
 * Created by qiang on 15/11/1.
 */
public class MyRetrolambdaActivity extends Activity {

    public static final String TAG = MyRetrolambdaActivity.class.getSimpleName();


    @Bind(R.id.textView) TextView textView;

    String[] atp = {"Rafael Nadal", "Novak Djokovic",
            "Stanislas Wawrinka",
            "David Ferrer","Roger Federer",
            "Andy Murray","Tomas Berdych",
            "Juan Martin Del Potro"};
    List<String> players =  Arrays.asList(atp);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.but)   void clickBut(){

       testThrad();


    }


    /***
     * lambdas的run示例
     */
    private void testThrad(){

         new Thread(()-> Log.d(TAG ,"Thread")).start();

        Runnable runnable  = ()->Log.d(TAG,"runnable");

        new Thread(runnable).start();

    }

    /***
     * ladmbdas 的Array
     */
    private void  testArrays(){



    }


}
