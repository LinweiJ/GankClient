package com.wega.gankclient.home.girl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wega.gankclient.base.BaseListFragment;
import com.wega.gankclient.base.adapter.WBaseAdapter;
import com.wega.gankclient.data.GankEntity;
import com.wega.gankclient.home.ios.IOSAdapter;
import com.wega.gankclient.home.ios.IOSContract;
import com.wega.gankclient.home.ios.IOSPresenter;

/**
 * Created by lwj on 2017/12/16 23:55.
 */

public class GirlFragment extends BaseListFragment<GankEntity> implements
        GirlContract.View<GankEntity> {

    public static final String TAG="妹子";
    private GirlContract.Presenter mPresenter;

    public GirlFragment( ) {
        Log.d("AndroidFragment",TAG);
    }
    /**
     * onCreateView-->initData-->initUI-->onRefresh
     */
    @Override
    protected WBaseAdapter setAdapter() {
        GirlAdapter girlAdapter = new GirlAdapter(this);
        return girlAdapter;
    }

    @Override
    protected void setupLayoutManager() {


            mLayoutManager = new GridLayoutManager(getActivity(),2);
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = new GirlPresenter(this);
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
