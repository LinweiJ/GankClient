package com.wega.gankclient.home.android;

import android.support.annotation.Nullable;

import com.wega.gankclient.R;
import com.wega.gankclient.base.adapter.WBaseAdapter;
import com.wega.gankclient.base.adapter.WBaseViewHolder;
import com.wega.gankclient.data.GankEntity;

import java.util.List;

/**
 * Created by lwj on 2017/12/21 00:15.
 */

public class AndroidAdapter extends WBaseAdapter<GankEntity,WBaseViewHolder> {

    public AndroidAdapter(@Nullable List data) {
        super(R.layout.item_rv_gank_list,data);
    }

}
