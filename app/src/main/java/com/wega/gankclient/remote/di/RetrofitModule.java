package com.wega.gankclient.remote.di;



import com.wega.gankclient.application.di.AppScope;
import com.wega.gankclient.remote.GankRetrofit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by mguy on 2018/1/5.
 */


@Module
public class RetrofitModule {

    @AppScope
    @Provides
    public GankRetrofit providerLocalRetrofit(@Named(OkhttpModule.DEBUG) OkHttpClient okHttpClient) {
        return new GankRetrofit(okHttpClient);
    }




}


