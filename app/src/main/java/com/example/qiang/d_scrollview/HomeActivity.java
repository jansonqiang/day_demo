package com.example.qiang.d_scrollview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.qiang.d_scrollview.measure.MeasureActivity;
import com.example.qiang.d_scrollview.myRxJava.RxJavaActivity;
import com.example.qiang.d_scrollview.myTouch.MyScrollerActivity;
import com.example.qiang.d_scrollview.myTouch.MyTouchActivity;
import com.example.qiang.d_scrollview.myhttp.HttpActivity;
import com.example.qiang.d_scrollview.myhttp.MyOkHttpActivity;
import com.example.qiang.d_scrollview.retrolambda.MyRetrolambdaActivity;

/**homeActivity
 * Created by qiang on 2015/10/20.
 */
public class HomeActivity extends ListActivity implements AdapterView.OnItemClickListener {



    final Class[]  clases = {
            MainActivity.class,MultiScreenActivity.class, MyTouchActivity.class,
            MyScrollerActivity.class, MeasureActivity.class , RxJavaActivity.class ,
            HttpActivity.class , MyOkHttpActivity.class, MyRetrolambdaActivity.class
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String tags[] = new String[clases.length];

        for (int i = 0; i < tags.length; i++) {
            tags[i] = clases[i].getSimpleName();
        }

        setListAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, tags));
        getListView().setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getBaseContext(),clases[position]);
        startActivity(intent);


    }
}
