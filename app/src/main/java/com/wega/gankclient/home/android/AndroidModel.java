package com.wega.gankclient.home.android;

import com.wega.gankclient.data.GankBean;
import com.wega.gankclient.remote.RetrofitHelper;
import com.wega.gankclient.remote.api.GankService;

import io.reactivex.Observable;

/**
 * Created by lwj on 2017/12/16 23:56.
 */

public class AndroidModel implements AndroidContract.Model{


    private GankService gankService;

    @Override
    public Observable<GankBean> getAndroid(int page_rows,int page){
        GankService gankService =  RetrofitHelper.getInstance().getGankServer();
        Observable<GankBean> andriod = gankService.getAndriod(page_rows, page);
        return andriod;
    }

}
