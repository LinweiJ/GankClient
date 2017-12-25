package com.wega.gankclient.home.ios;

import com.wega.gankclient.data.GankBean;
import com.wega.gankclient.home.android.AndroidContract;
import com.wega.gankclient.remote.RetrofitHelper;
import com.wega.gankclient.remote.api.GankServer;

import io.reactivex.Observable;

/**
 * Created by lwj on 2017/12/16 23:56.
 */

public class IOSModel implements IOSContract.Model{


    private GankServer gankServer;

    @Override
    public Observable<GankBean> getIOS(int page_rows,int page){
        GankServer gankServer =  RetrofitHelper.getInstance().getGankServer();
        Observable<GankBean> iOS = gankServer.getIOS(page_rows, page);
        return iOS;
    }

}
