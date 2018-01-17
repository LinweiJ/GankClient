package com.wega.gankclient.remote;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mguy on 2018/1/5.
 */

public class GankRetrofit {

    private static final String BASE_URL = "http://gank.io/api/";
    private static Retrofit retrofit;

    public GankRetrofit(OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                //增加返回值为String的支持
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }




}
