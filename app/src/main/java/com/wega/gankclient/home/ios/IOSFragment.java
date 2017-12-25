package com.wega.gankclient.home.ios;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wega.gankclient.base.BaseListFragment;
import com.wega.gankclient.base.adapter.WBaseAdapter;
import com.wega.gankclient.data.GankEntity;
import com.wega.gankclient.home.android.AndroidAdapter;
import com.wega.gankclient.home.android.AndroidContract;
import com.wega.gankclient.home.android.AndroidPresenter;

/**
 * Created by lwj on 2017/12/16 23:55.
 */

public class IOSFragment extends BaseListFragment<GankEntity> implements
        IOSContract.View<GankEntity> {

    public static final String TAG="IOS";
    private IOSContract.Presenter mPresenter;

    public IOSFragment( ) {
        Log.d("AndroidFragment",TAG);
    }
    /**
     * onCreateView-->initData-->initUI-->onRefresh
     */
    @Override
    protected WBaseAdapter setAdapter() {
        IOSAdapter iOSAdapter = new IOSAdapter(null);
        return iOSAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = new IOSPresenter(this);
        mPresenter.setPageRows(page_rows);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
        Log.d("AndroidFragment",TAG+"onResume");
    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.refreshList();
    }

    /**
     * 加载更多
     */
    @Override
    protected void loadMore() {
        super.loadMore();
        mPresenter.loadMoreList(page);
    }

//    /**
//     * 跳转详情页
//     */
//    @Override
//    public void toDetail() {
//
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDestroy();
    }
}
