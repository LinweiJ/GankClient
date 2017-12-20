package com.wega.gankclient.remote;

import com.wega.gankclient.utils.GankConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lwj on 2017/12/17 00:48.
 */

public class RetrofitManager {

    public static  boolean DEBUG = true;
    private Retrofit retrofit;

    private static RetrofitManager mHttpUtil;

    private RetrofitManager() {
        retrofit = getRetrofit();
    }

    public static RetrofitManager getInstance() {
        if (mHttpUtil == null) {
            synchronized (RetrofitManager.class) {
                if (mHttpUtil == null) {
                    mHttpUtil = new RetrofitManager();
                }
            }
        }
        return mHttpUtil;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(GankConstants.HOST)
                    //增加返回值为String的支持
//                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getNewClient())
                    .build();
        }
        return retrofit;
    }

    private final int TIMEOUT_READ = 20;
    private final int TIMEOUT_CONNECTION = 20;
    private final int TIMEOUT_WRITE = 20;

    public OkHttpClient getNewClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient;
        if(DEBUG)
        {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                    .build();
        }else
        {
            okHttpClient = new OkHttpClient.Builder()
//                    .addInterceptor(logging)
                    .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                    .build();
        }


        return okHttpClient;
    }
}
