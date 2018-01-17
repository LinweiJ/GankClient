package com.wega.gankclient.home.android;

import com.wega.gankclient.data.GankBean;
import com.wega.gankclient.remote.RetrofitHelper;
import com.wega.gankclient.remote.api.GankService;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lwj on 2017/12/20 22:18.
 */
public class AndroidModelTest {
    @Test
    public void getAndroid() throws Exception {
        int page_rows=10;
        int page=1;
        GankService gankService =  RetrofitHelper.getInstance().getGankServer();
        Observable<GankBean> andriod = gankService.getAndriod(page_rows, page);
        andriod.subscribe(new Observer<GankBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.print("Disposable=="+d);
            }

            @Override
            public void onNext(GankBean gankBean) {
                  System.out.print(gankBean.toString());
            }

            @Override
            public void onError(Throwable e) {
                System.out.print(e.toString());
            }

            @Override
            public void onComplete() {
                System.out.print("onComplete");
            }
        });

    }

}