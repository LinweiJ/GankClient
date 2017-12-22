package com.wega.gankclient.home.android;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wega.gankclient.R;
import com.wega.gankclient.base.adapter.WBaseAdapter;
import com.wega.gankclient.base.adapter.WBaseViewHolder;
import com.wega.gankclient.data.GankEntity;
import com.wega.gankclient.home.detail.DetailActivity;

import java.util.List;

/**
 * Created by lwj on 2017/12/21 00:15.
 */

public class AndroidAdapter extends WBaseAdapter<GankEntity,WBaseViewHolder>
implements WBaseAdapter.OnItemClickListener{

    public AndroidAdapter( @Nullable List data) {
        super(R.layout.item_rv_gank_list,data);
        this.setOnItemClickListener(this);
    }

    @Override
    protected void convert(WBaseViewHolder helper, GankEntity item) {
        super.convert(helper, item);
        String desc = item.desc;
        String who = item.who;
        String publishedAt = item.publishedAt;
        String publishTime = publishedAt.split("T")[0];
        helper.setText(R.id.tv_title,desc);
        helper.setText(R.id.tv_who,who);
        helper.setText(R.id.tv_publish_time,publishTime);

        ImageView ivImage = helper.getView(R.id.iv_image);
        List<String> images = item.images;
        if(images!=null)
        {
            String url = images.get(0);
            Glide.with(mContext)
                    .load(url)
                    .into(ivImage);
            helper.setGone(R.id.iv_image,true);
        }else {
            helper.setGone(R.id.iv_image,false);
        }
        

    }

    /**
     * 列表行点击
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List data = adapter.getData();
        GankEntity entity = (GankEntity) data.get(position);

        //详情
        DetailActivity.launch(mContext,entity.url);


    }
}
