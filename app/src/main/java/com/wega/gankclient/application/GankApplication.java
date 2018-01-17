package com.wega.gankclient.application;

import android.app.Application;
import android.content.Context;

import com.wega.gankclient.application.di.AppComponent;
import com.wega.gankclient.application.di.AppModule;
import com.wega.gankclient.application.di.DaggerAppComponent;

/**
 * Created by lwj on 2017/12/22 23:26.
 */

public class GankApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();


    }

    private static AppComponent mAppComponent;

    private void setupApplicationComponent() {
        //Dagger开头的注入类DaggerAppComponent
        mAppComponent = DaggerAppComponent.builder()
                //此时appModule方法是过时方法，因为我们没有使用到任何一个module中提供的对象
                .appModule(new AppModule(this))
                .build();
    }

    //获取AppComponent 以便于SubComponent继承
    public AppComponent getAppComponent() {
        if(mAppComponent == null){
            this.setupApplicationComponent();
        }
        return mAppComponent;
    }

}
