package com.example.qiang.d_scrollview.myhttp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.qiang.d_scrollview.R;
import com.squareup.okhttp.OkHttpClient;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.JacksonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * 网络请求
 * Created by qiang on 2015/10/27
 */
public class HttpActivity extends Activity implements View.OnClickListener {


    public static final String TAG = HttpActivity.class.getSimpleName();

    private static final String KEY_PASS = "123456";

    @Bind(R.id.textView)
    TextView textView;

    @Bind(R.id.but)
    Button but;

    @Bind(R.id.but2)
    Button but2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_http);
        ButterKnife.bind(this);
        but.setOnClickListener(this);
        but2.setOnClickListener(this);


    }


    private void testHttp() {


        OkHttpClient httpClient = new OkHttpClient();


        Https2OkhttpClient https2OkhttpClient = new Https2OkhttpClient(this);
        https2OkhttpClient.makeHttps(httpClient);


             /*
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("User-Agent", "Your-App-Name")

                        .method(original.method(), original.body())
                        .build();


                Log.d(TAG, "method =" + request.method());
                Log.d(TAG, "request =" + request.url());
                Log.d(TAG , "requestBody = "+request.body().toString());


                return chain.proceed(request);
            }
        });
        */


        // Synchronous Call in Retrofit 2.0
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://efuservice.taskmed.com.cn")
                .addConverterFactory(JacksonConverterFactory.create()) //通过这个转换成JSON
                .client(httpClient)
                .build();


        APIService apiService = retrofit.create(APIService.class);


        Call<Repo> call = apiService.loadRepoPost("zhangsen", "lisi");

        call.enqueue(new Callback<Repo>() {


            @Override
            public void onResponse(Response<Repo> response, Retrofit retrofit) {


                Log.d(TAG, "response.isSuccess : +" + response.isSuccess() + " code =" + response.code());

                Repo repo = response.body();

                if (repo != null)
                    Log.d(TAG, repo.toString());
                else

                    Log.d(TAG, response.message());


            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "来到了onFailure " + t.getMessage());

            }
        });

    }


    private void testHttp2() {

        // Synchronous Call in Retrofit 2.0
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://efuservice.taskmed.com.cn")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        //retrofit.requestConverter(RequestFactory);

        APIService apiService = retrofit.create(APIService.class);


        Call<Repo> call = apiService.loadRepoPost("zhang", "1");

        call.enqueue(new Callback<Repo>() {


            @Override
            public void onResponse(Response<Repo> response, Retrofit retrofit) {


                Log.d(TAG, "response.isSuccess : +" + response.isSuccess() + " code =" + response.code());









            }

            @Override
            public void onFailure(Throwable t) {

                Log.d(TAG, "来到了onFailure " + t.getMessage());

            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.but:
                testHttp();

                break;

            case R.id.but2:
                testHttp2();
                break;

        }

    }


    public interface APIService {

        @GET("/doctor/users/stat")
        Call<Repo> loadRepo(@Query("first_name") int page, @QueryMap() Map<String, String> baseMap);

        @GET("/doctor/users/stat")
        Call<Repo> loadRepoPost(@Query("first_name") String first, @Query("last_name") String last
        );

    }


    public class User {


        public User(String name) {
            this.name = name;
        }

        String name;


    }

    /* Asynchronous in Retrofit 1.9 */


}
