package com.example.qiang.d_scrollview.myRxJava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.qiang.d_scrollview.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**测试RxJava
 * Created by qiang on 2015/10/27.
 */
public class RxJavaActivity extends Activity implements View.OnClickListener {


    public static final String TAG = RxJavaActivity.class.getSimpleName();


    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        but = (Button) findViewById(R.id.but);
        but.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

       int id = v.getId();

        switch (id){

            case R.id.but:

               testScheduler();

                break;

        }


    }

    Observer<String> observer = new Observer<String>() {

        @Override
        public void onNext(String s) {

            Log.d(TAG, "Item: " + s);
        }

        //已经完成通知了
        @Override
        public void onCompleted() {
            Log.d(TAG, "Completed!");
        }

        @Override
        public void onError(Throwable e) {

            Log.d(TAG, "Error!");

        }



    };

    Subscriber<String> subscriber = new Subscriber<String>() {

        //未发送之前干的 例如数据清0  非主线程
        @Override
        public void onStart() {
            super.onStart();

            Log.d(TAG, "onStart!");
        }



        @Override
        public void onCompleted() {
            Log.d(TAG, "Completed!");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError!");
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "Item: " + s);
        }
    };


    //发送一个订阅
    private void testObservableGeneral(){


        //创造一个订阅
        Observable observable = Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext("hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();


            }
        });

        observable.subscribe(subscriber);

    }


    private void testObserbableSimple(){

        Observable observable = Observable.just("hello", "Hi", "Aloha");


    }

    private void testObserableIterable(){
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable = Observable.from(words);

    }


    //不完整返回
    private void testAction(){
        Observable observable =Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {

            }
        });

        Action1<String> onNextAciont= new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, s);

            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                Log.d(TAG, "completed");
            }
        };

// 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAciont);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAciont, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAciont, onErrorAction, onCompletedAction);

    }



    private void testScheduler(){


        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG,"number: "+integer);
                    }
                });


    }

    private void testSchedulerFromImage(){



    }







}
