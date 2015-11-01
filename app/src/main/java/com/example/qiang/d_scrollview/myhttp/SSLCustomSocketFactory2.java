package com.example.qiang.d_scrollview.myhttp;

import android.content.Context;

import com.example.qiang.d_scrollview.R;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.InputStream;
import java.security.KeyStore;

/**
 * Created by qiang on 2015/10/29.
 */

public class SSLCustomSocketFactory2 extends SSLSocketFactory {

    private static final String KEY_PASS = "123456";

    public SSLCustomSocketFactory2(KeyStore trustStore) throws Throwable {
        super(trustStore);
    }

    public static SSLSocketFactory getSocketFactory(Context context) {
        try {
//		 InputStream ins = context.getAssets().open("efu_service.taskmed.com.cn.cer");

            KeyStore trustStore = KeyStore.getInstance("BKS");
            InputStream ins=null;


                ins = context.getResources().openRawResource(R.raw.efuservice);

//			trustStore.load(ins, KEY_PASS.toCharArray());


//			InputStream ins = context.getResources().openRawResource(R.raw.efuservicetest_taskmed_com_cn);
//			KeyStore trustStore = KeyStore.getInstance(KeyStore
//					.getDefaultType());
            try {
                trustStore.load(ins, KEY_PASS.toCharArray());
            } finally {
                ins.close();
            }
            SSLSocketFactory factory = new SSLCustomSocketFactory2(trustStore);
            return factory;
        } catch (Throwable e) {
            System.out.println("!!Throwable:"+e);
            e.printStackTrace();
        }
        return null;
    }
}