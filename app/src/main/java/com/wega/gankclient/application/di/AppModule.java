package com.wega.gankclient.application.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mguy on 2017/12/28.
 */

@Module
public class AppModule {


    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }


    @AppScope
    @Provides
    public Application providerApplication() {
        return mApplication;
    }



}
