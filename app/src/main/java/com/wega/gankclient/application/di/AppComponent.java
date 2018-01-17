package com.wega.gankclient.application.di;

import android.app.Application;


import com.wega.gankclient.remote.di.OkhttpModule;
import com.wega.gankclient.remote.di.RetrofitModule;
import com.wega.gankclient.remote.di.ServiceModule;

import dagger.Component;

/**
 * Created by mguy on 2017/12/29.
 */
@AppScope
@Component(modules={AppModule.class,
        RetrofitModule.class,
        OkhttpModule.class,
        ServiceModule.class})
public interface AppComponent {

    void inject(Application app);


    //SubComponent 继承当前Component
//    MainComponent addSub(MainModule mainModule);
}
