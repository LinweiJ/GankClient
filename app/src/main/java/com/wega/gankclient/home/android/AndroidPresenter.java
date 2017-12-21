package com.wega.gankclient.home.android;

import com.wega.gankclient.data.GankBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lwj on 2017/12/16 23:54.
 */

public class AndroidPresenter {

    private AndroidFragment mView;
    private final AndroidModel mModel;

    public AndroidPresenter(AndroidFragment fragment) {

        this.mView = fragment;
        mModel = new AndroidModel();
    }

    /**
     * 刷新列表
     * @param page_rows
     */
    public void refreshList(int page_rows){
        Observable<GankBean> android = mModel.getAndroid(page_rows, 1);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GankBean gankBean) {
                if(mView!=null&&!gankBean.error)
                {
                    mView.refreshListByData(gankBean.results);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void setView(AndroidFragment fragment){

    }

}
