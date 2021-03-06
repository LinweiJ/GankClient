package com.wega.gankclient.home.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wega.gankclient.base.BaseListFragment;
import com.wega.gankclient.base.adapter.WBaseAdapter;
import com.wega.gankclient.data.GankEntity;

import java.util.List;

/**
 * Created by lwj on 2017/12/16 23:55.
 */

public class AndroidFragment extends BaseListFragment<GankEntity> implements
        AndroidContract.View<GankEntity> {

    public static final String TAG="Android";
    private AndroidPresenter mPresenter;

    public AndroidFragment( ) {
        Log.d("AndroidFragment",TAG);
    }

    /**
     * onCreateView-->initData-->initUI-->onRefresh
     */
    @Override
    protected WBaseAdapter setAdapter() {
        AndroidAdapter androidAdapter = new AndroidAdapter(null);
        return androidAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = new AndroidPresenter(this);
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
