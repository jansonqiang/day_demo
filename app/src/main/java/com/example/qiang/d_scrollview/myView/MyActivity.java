package com.example.qiang.d_scrollview.myView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.qiang.d_scrollview.R;

/**自己的实验
 * Created by qiang on 2015/10/19.
 */
public class MyActivity extends Activity implements View.OnClickListener {


    ImageView imageView;
    Button leftBut,rightBut;
    LinearLayout layout;

    int layoutWidth;
    int imageWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        layout = (LinearLayout) findViewById(R.id.layout);

        imageView = (ImageView) findViewById(R.id.imageView);
        leftBut = (Button) findViewById(R.id.leftBut);
        leftBut.setOnClickListener(this);
        rightBut = (Button) findViewById(R.id.rightBut);
        rightBut.setOnClickListener(this);
        reckon();
    }


    private void reckon(){

        layoutWidth =  layout.getWidth();
        imageWidth  = imageView.getWidth();


    }




    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.leftBut:




                layout.scrollTo(0, 0);
               // layout.scrollBy(20, 0);

                break;
            case R.id.rightBut:
             //  if(layout.getScaleX()==0)
                layout.scrollTo(layoutWidth-imageWidth, 0);
             //   layout.scrollBy(-20, 0);
                break;

        }
    }
}
