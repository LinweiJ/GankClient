package com.wega.gankclient.home.ios;

import com.wega.gankclient.data.GankBean;
import com.wega.gankclient.remote.RetrofitHelper;
import com.wega.gankclient.remote.api.GankService;

import io.reactivex.Observable;

/**
 * Created by lwj on 2017/12/16 23:56.
 */

public class IOSModel implements IOSContract.Model{


    private GankService gankService;

    @Override
    public Observable<GankBean> getIOS(int page_rows,int page){
        GankService gankService =  RetrofitHelper.getInstance().getGankServer();
        Observable<GankBean> iOS = gankService.getIOS(page_rows, page);
        return iOS;
    }

}
