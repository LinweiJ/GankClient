package com.wega.gankclient.home.android;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.wega.gankclient.base.BaseListFragment;
import com.wega.gankclient.base.adapter.WBaseAdapter;
import com.wega.gankclient.data.GankEntity;

import java.util.List;

/**
 * Created by lwj on 2017/12/16 23:55.
 */

public class AndroidFragment extends BaseListFragment<GankEntity> {

    public static final String TAG="android";
    private AndroidPresenter mPresenter;

    public AndroidFragment( ) {
        Log.d("AndroidFragment",TAG);
    }

    @Override
    protected WBaseAdapter setAdapter() {
        return new AndroidAdapter(null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new AndroidPresenter(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
        Log.d("AndroidFragment",TAG+"onResume");
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.refreshList(page_rows);
    }

    @Override
    protected List getListData(List data) {
        return null;
    }
}
