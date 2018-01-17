package com.wega.gankclient.remote.di;



import com.wega.gankclient.application.di.AppScope;
import com.wega.gankclient.remote.GankRetrofit;
import com.wega.gankclient.remote.api.GankService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mguy on 2018/1/5.
 */
@Module
public class ServiceModule {

    @AppScope
    @Provides
    public GankService providerUserService(GankRetrofit retrofit){
        return retrofit.getRetrofit().create(GankService.class);
    }



}
