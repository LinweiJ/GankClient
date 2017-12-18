package com.wega.gankclient.base.adapter;


import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.wega.gankclient.R;

/**
 * Created by lwj on 2017/9/28.
 */

public final class WLoadMoreView extends LoadMoreView {

    @Override public int getLayoutId() {
        return R.layout.rv_load_more;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
