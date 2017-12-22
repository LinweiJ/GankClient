package com.wega.gankclient.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by lwj on 2017/12/22 23:26.
 */

public class GankApplication extends Application {

    public static Context MainContext;

    @Override
    public void onCreate() {
        super.onCreate();
        MainContext = getApplicationContext();
    }

}
