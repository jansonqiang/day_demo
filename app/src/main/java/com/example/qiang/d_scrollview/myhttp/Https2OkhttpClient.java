package com.example.qiang.d_scrollview.myhttp;

/**
 * Created by qiang on 2015/10/29.
 */

import android.content.Context;
import android.util.Log;

import com.example.qiang.d_scrollview.R;
import com.squareup.okhttp.OkHttpClient;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;


/**
 * Created by martin on 02/06/14.
 */
public class Https2OkhttpClient {

    Context context;
    public static String TRUST_STORE_PASSWORD = "123456";
    private static final String ENDPOINT = "https://efuservice.taskmed.com.cn/doctor/users/stat";

    public Https2OkhttpClient(Context c) {
        this.context = c;
    }

    private SSLSocketFactory getPinnedCertSslSocketFactory(Context context) {
        try {
            KeyStore trusted = KeyStore.getInstance("BKS");
            InputStream in = context.getResources().openRawResource(R.raw.efuservice);
            trusted.load(in, TRUST_STORE_PASSWORD.toCharArray());
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trusted);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            Log.e("MyApp", e.getMessage(), e);
        }
        return null;
    }

    public void makeHttps( OkHttpClient client) {

            client.setSslSocketFactory(getPinnedCertSslSocketFactory(context));


    }
}