package com.wega.gankclient.home.android;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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

    @Override
    protected void convert(WBaseViewHolder helper, GankEntity item) {
        super.convert(helper, item);
        String desc = item.desc;
        String who = item.who;
        String publishedAt = item.publishedAt;
        helper.setText(R.id.tv_title,desc);
        helper.setText(R.id.tv_who,who);
        helper.setText(R.id.tv_publish_time,publishedAt);

        ImageView ivImage = helper.getView(R.id.iv_image);
        List<String> images = item.images;
        if(images!=null)
        {
            String url = images.get(0);
            Glide.with(mContext)
                    .load(url)
                    .into(ivImage);
        }
        


    }
}
