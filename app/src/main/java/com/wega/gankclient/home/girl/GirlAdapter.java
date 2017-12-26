package com.wega.gankclient.home.girl;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lwj.widget.picturebrowser.PictureBrowser;
import com.lwj.widget.picturebrowser.PictureFragment;
import com.lwj.widget.picturebrowser.PictureLoader;
import com.wega.gankclient.R;
import com.wega.gankclient.base.adapter.WBaseAdapter;
import com.wega.gankclient.base.adapter.WBaseViewHolder;
import com.wega.gankclient.data.GankEntity;
import com.wega.gankclient.home.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lwj on 2017/12/21 00:15.
 */

public class GirlAdapter extends WBaseAdapter<GankEntity, WBaseViewHolder>
        implements WBaseAdapter.OnItemClickListener {

    private Fragment fragment;

    //    public GirlAdapter(@Nullable List data) {
//        super(R.layout.item_rv_girl_list,data);
//        this.setOnItemClickListener(this);
//    }
    public GirlAdapter(Fragment fragment) {
        super(R.layout.item_rv_girl_list);
        this.fragment = fragment;
        this.setOnItemClickListener(this);
    }

    @Override
    protected void convert(WBaseViewHolder helper, GankEntity item) {
        super.convert(helper, item);
//        String desc = item.desc;
//        String who = item.who;
//        String publishedAt = item.publishedAt;
//        String publishTime = publishedAt.split("T")[0];
//        helper.setText(R.id.tv_title,desc);
//        helper.setText(R.id.tv_who,who);
//        helper.setText(R.id.tv_publish_time,publishTime);

        ImageView ivImage = helper.getView(R.id.iv_image);
        String url = item.url;
        int random = new Random().nextInt(5);
        Log.e("random", random + "");
        ColorDrawable colorDrawable = new ColorDrawable();
//        switch (random) {
//            case 0:
//                //绿
//                colorDrawable = new ColorDrawable(0xFF33cccc);
//                break;
//            case 1:
//                //粉
//                colorDrawable = new ColorDrawable(0xFFFFB5C5);
//                break;
//            case 2:
//                //黄
//                colorDrawable = new ColorDrawable(0xFFFAFAD2);
//                break;
//            case 3:
//                //紫
//                colorDrawable = new ColorDrawable(0xff9999ff);
//
//                break;
//            case 4:
//                //蓝
//                colorDrawable = new ColorDrawable(0xFF87CEFA);
//
//                break;
//            case 5:
//                //蓝绿
////                colorDrawable = new ColorDrawable(0xFF00CED1);
//                break;
//        }
        RequestOptions options = new RequestOptions()
                .placeholder(colorDrawable)
                .centerCrop();
        Glide.with(mContext)

                .load(url)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivImage)
        ;
//        List<String> images = item.images;
//        if(images!=null)
//        {
//            String url = images.get(0);
//            Glide.with(mContext)
//                    .load(url)
//                    .into(ivImage);
//            helper.setGone(R.id.iv_image,true);
//        }else {
//            helper.setGone(R.id.iv_image,false);
//        }


    }

    /**
     * 列表行点击
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List data = adapter.getData();
        GankEntity entity = (GankEntity) data.get(position);
        ArrayList<String> pictureUrl = new ArrayList<>();
        pictureUrl.add(entity.url);

        //详情
//        DetailActivity.launch(mContext,entity.url);
        PictureBrowser.Builder builder = new PictureBrowser.Builder();
        builder.setFragmentManager(fragment.getFragmentManager())
                .setUrlList(pictureUrl)
                .setStartIndex(position)
                .initPictureLoader(pictureLoader)
                .setShowDeleteIcon(true)
                .setShowIndexHint(true)
                .setCancelOutside(true)
                .create()
                .show();

    }

    public PictureLoader pictureLoader = new PictureLoader() {
        @Override
        public void showPicture(PictureFragment pictureFragment, PhotoView photoView, String s) {
            //使用Glide加载图片,可自行根据需求选用其他图片加载库
            RequestOptions options = new RequestOptions()
                    .placeholder(new ColorDrawable(Color.LTGRAY));
            Glide.with(fragment)
                    .load(s)
                    .apply(options)
//                    .placeholder(new ColorDrawable(Color.LTGRAY))
                    .into(photoView);
        }


    };
}
