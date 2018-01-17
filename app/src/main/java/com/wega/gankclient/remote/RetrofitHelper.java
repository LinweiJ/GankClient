package com.wega.gankclient.remote;

import com.wega.gankclient.remote.api.GankService;

/**
 * Created by lwj on 2017/12/20 00:04.
 */

public class RetrofitHelper {


    private GankService mGankService;

    public RetrofitHelper() {
        mGankService = RetrofitManager.getInstance().getRetrofit().create(GankService.class);
    }

    private static RetrofitHelper mAPIClient;


    public static RetrofitHelper getInstance() {
        if (mAPIClient == null) {
            mAPIClient = new RetrofitHelper();
        }
        return mAPIClient;
    }

    public GankService getGankServer() {
        return mGankService;
    }
}
