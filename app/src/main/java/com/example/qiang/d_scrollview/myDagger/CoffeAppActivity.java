package com.example.qiang.d_scrollview.myDagger;

import android.app.Activity;
import android.os.Bundle;

import dagger.ObjectGraph;

/**http://square.github.io/dagger/
 * Created by qiang on 2015/11/9.
 */
public class CoffeAppActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ObjectGraph objectGraph = ObjectGraph.create(new DripCoffeeModule());
        CoffeeApp coffeeApp = objectGraph.get(CoffeeApp.class);
        coffeeApp.run();
    }
}
