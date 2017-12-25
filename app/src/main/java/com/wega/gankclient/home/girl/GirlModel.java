package com.wega.gankclient.home.girl;

import com.wega.gankclient.data.GankBean;
import com.wega.gankclient.home.ios.IOSContract;
import com.wega.gankclient.remote.RetrofitHelper;
import com.wega.gankclient.remote.api.GankServer;

import io.reactivex.Observable;

/**
 * Created by lwj on 2017/12/16 23:56.
 */

public class GirlModel implements GirlContract.Model{


    private GankServer gankServer;

    @Override
    public Observable<GankBean> getGirl(int page_rows,int page){
        GankServer gankServer =  RetrofitHelper.getInstance().getGankServer();
        Observable<GankBean> girl = gankServer.getGirl(page_rows, page);
        return girl;
    }

}
