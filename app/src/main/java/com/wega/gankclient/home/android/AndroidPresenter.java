package com.wega.gankclient.home.android;

import com.wega.gankclient.data.GankBean;
import com.wega.gankclient.utils.ToastUtil;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lwj on 2017/12/16 23:54.
 */

public class AndroidPresenter implements AndroidContract.Presenter{

    private AndroidContract.View mView;
    private final AndroidContract.Model mModel;
    private ArrayList<Disposable> mDisposableList=new ArrayList<>();
    private int mPage_rows;

    public AndroidPresenter(AndroidFragment fragment) {

        this.mView = fragment;
        mModel = new AndroidModel();
    }




    /**
     * 每页行数
     * @param page_rows
     */
    @Override
    public void setPageRows(int page_rows) {

        mPage_rows = page_rows;
    }
    /**
     * 刷新列表
     */
    @Override
    public void refreshList() {
        Observable<GankBean> android = mModel.getAndroid(mPage_rows, 1);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankBean>() {
                    Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposableList.add(d) ;
                        mDisposable=d;
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
                        if(mView!=null )
                        {
                            mView.showErrorView();
                        }
                    }

                    @Override
                    public void onComplete() {
                        mDisposableList.remove(mDisposable);
                    }
                });
    }

    /**
     * 加载更多
     * @param page
     */
    @Override
    public void loadMoreList(int page) {
        Observable<GankBean> android = mModel.getAndroid(mPage_rows, page);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankBean>() {
                    Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposableList.add(d) ;
                        mDisposable=d;
                    }

                    @Override
                    public void onNext(GankBean gankBean) {
                        if(mView!=null&&!gankBean.error)
                        {
                            mView.addListByData(gankBean.results);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mView!=null )
                        {
                            mView.loadMoreFail();
                        }
                    }

                    @Override
                    public void onComplete() {
                        mDisposableList.remove(mDisposable);
                    }
                });
    }

    /**
     * 开始
     */
    @Override
    public void onStart()
    {


    }

    /**
     * 销毁 等待回收
     */
    @Override
    public void onDestroy()
    {
        //清除网络请求
        for(Disposable disposable:mDisposableList)
        {
            if(!disposable.isDisposed())
            {
                disposable.dispose();
            }
        }
        mDisposableList.clear();
        //清除View连接
        mView=null;
    }

}
