package com.wega.gankclient.base.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * Created by lwj on 2017/9/28.
 */

public class WBaseAdapter<T,K extends WBaseViewHolder>  extends BaseQuickAdapter<T, K> {

    public WBaseAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public WBaseAdapter(@Nullable List<T> data) {
        super(data);
    }

    public WBaseAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(K helper, T item) {

    }


}
