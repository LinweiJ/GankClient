package com.wega.gankclient.remote;

import com.wega.gankclient.remote.api.GankServer;

/**
 * Created by lwj on 2017/12/20 00:04.
 */

public class RetrofitHelper {


    private GankServer mGankServer;

    public RetrofitHelper() {
        mGankServer = RetrofitManager.getInstance().getRetrofit().create(GankServer.class);
    }

    private static RetrofitHelper mAPIClient;


    public static RetrofitHelper getInstance() {
        if (mAPIClient == null) {
            mAPIClient = new RetrofitHelper();
        }
        return mAPIClient;
    }

    public GankServer getGankServer() {
        return mGankServer;
    }
}
