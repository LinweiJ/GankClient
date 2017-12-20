package com.wega.gankclient.home.android;

import com.wega.gankclient.data.GankBean;
import com.wega.gankclient.remote.RetrofitHelper;
import com.wega.gankclient.remote.api.GankServer;

import io.reactivex.Observable;

/**
 * Created by lwj on 2017/12/16 23:56.
 */

public class AndroidModel {


    private GankServer gankServer;

    public Observable<GankBean> getAndroid(int page_rows,int page){
        GankServer gankServer =  RetrofitHelper.getInstance().getGankServer();
        Observable<GankBean> andriod = gankServer.getAndriod(page_rows, page);
        return andriod;
    }

}
