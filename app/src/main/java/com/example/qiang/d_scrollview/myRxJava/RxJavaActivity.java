package com.example.qiang.d_scrollview.myRxJava;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.qiang.d_scrollview.R;
import com.example.qiang.d_scrollview.myhttp.HttpActivity;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 测试RxJava
 * Created by qiang on 2015/10/27.
 * http://gank.io/post/560e15be2dca930e00da1083
 */
public class RxJavaActivity extends Activity implements View.OnClickListener {


    public static final String TAG = RxJavaActivity.class.getSimpleName();


    @Bind(R.id.iamgeView)
    ImageView imageView;
    @Bind(R.id.but)
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);

        but = (Button) findViewById(R.id.but);
        but.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {

            case R.id.but:

                labGetFrist();

                break;

        }


    }

    //订阅者 基类Observer
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

    //订阅者的继承者
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
    private void testObservableGeneral() {


        //创造一个订阅
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {

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


    private void testObserbableSimple() {

        Observable observable = Observable.just("hello", "Hi", "Aloha");


    }

    private void testObserableIterable() {
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable = Observable.from(words);

    }


    //不完整返回
    private void testAction() {
        Observable observable = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {

            }
        });

        Action1<String> onNextAciont = new Action1<String>() {
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


    private void testScheduler() {


        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                               @Override
                               public void call(Integer integer) {
                                   Log.d(TAG, "number: " + integer);
                               }
                           }
                );


    }


    private void testSchedulerFromImage() {


        int drawableRes = R.mipmap.ic_launcher;




        Observable.create(new Observable.OnSubscribe<Drawable>() {

            @Override
            public void call(Subscriber<? super Drawable> subscriber) {

                Drawable drawable = getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(Drawable drawable) {

                        imageView.setImageDrawable(drawable);
                        Log.d(TAG, "imageViewa");

                    }
                });


    }

    /**转换*/
    private void testChange() {


        Observable.just(R.mipmap.ic_launcher)
                .map(new Func1<Integer, Bitmap>() {

                    @Override
                    public Bitmap call(Integer integer) {

                        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(integer);

                        return bitmapDrawable.getBitmap();

                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {

                        imageView.setImageBitmap(bitmap);

                    }
                });


    }

    /***
     * 转换 一对一
     */
    private void testMap(){

        List<Student> studentList = Student.newStudentList();
        Student[] students = studentList.toArray(new Student[studentList.size()]);

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

                Log.d(TAG,s);
            }
        };

        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.toString();
                    }
                }).subscribe(subscriber);

    }

    /***
     * 1对多
     */
    private void fitMap(){

        List<Student> studentList = Student.newStudentList();

        Student[] students = studentList.toArray(new Student[studentList.size()]);

        Subscriber<Student.Course> subscriber = new Subscriber<Student.Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student.Course course) {

                Log.d(TAG , course.toString());
            }
        };

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Student.Course>>() {

                    @Override
                    public Observable<Student.Course> call(Student student) {
                        return Observable.from(student.courses);
                    }
                })
                .subscribe(subscriber);


    }



    public void labChange(){

        Observable.just("Hello , world!")
                .subscribe( s -> Log.d(TAG , s));

    }
    
    public void labMap(){
        
        Observable.just("Hello , world")
                .map(s -> s + " !!!")
                .subscribe( s -> Log.d(TAG , s));

    }

    /***
     * 多次转换
     */
    public void labMap2(){
        Observable.just("htlo , world")
                .map(s -> s.hashCode())
                .map(i ->  Integer.toString(i))
                .subscribe(s -> Log.d(TAG , s));
    }

    public void labQuery(){


        query("hello work")
                .subscribe(urls -> {

                    for(String url : urls){

                        Log.d(TAG ,url);

                    }

                });

    }


    /***
     * 与labQuery 方法相同 但少了for
     */
    public void labQueryNoFor() {

        query("hello wrold").subscribe(urls -> {

            Observable.from(urls)
                    .subscribe(url -> Log.d(TAG, url));

        });


    }


    /***
     * 与labQueryNoFor 使用了flatMap方法 , 更简洁
     */
    public void labFlatMap(){

        query("hello work")
                .flatMap(urls -> Observable.from(urls)) //urls 转成Observable
                .subscribe(url -> Log.d(TAG, url));

    }


    public void labGetFrist(){

        query("hello work")
                .flatMap(
                        urls ->{
                            Log.d(TAG,"urls = "+urls.toString());
                            return  Observable.from(urls);
                        }

                        )
                .filter(url -> Integer.parseInt(url.charAt(url.length() - 1) + "") >= 2)
                //.take(2) //拿2个 只发两个消息
                .doOnNext(url -> Log.d(TAG, "next 是" + url)) //在输出下一个之前刚什么
                .subscribe(url -> Log.d(TAG, "title 是" + url)); //发送


    }

    public void androidThreadOn(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://efuservice.taskmed.com.cn")
                .addConverterFactory(JacksonConverterFactory.create()) //通过这个转换成JSON
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//使用rxjava 作为call
                .client(new OkHttpClient())
                .build();


        HttpActivity.APIService apiService = retrofit.create(HttpActivity.APIService.class);




    }



      public Observable<List<String>> query(String text){

        List<String> list = new ArrayList<>();
        List<List<String>> list2 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            list.add(text + i);

            list2.add(new ArrayList<>(list));
        }

       return  Observable.from(list2);

    }





}
