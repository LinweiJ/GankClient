package com.wega.gankclient.home.ios;

import com.wega.gankclient.data.GankBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lwj on 2017/12/16 23:54.
 */

public interface IOSContract {

    interface View<T> {
          //刷新列表
          void  refreshListByData(List<T> data);
          //加载更多
          void  addListByData(List<T> data);
          //跳转详情
//          void  toDetail();
          //加载更多失败
          void loadMoreFail();
          //刷新列表失败/网络异常
          void showErrorView();
    }

    interface Presenter{
       //每页行数
       void setPageRows(int page_rows);
       //刷新列表
       void refreshList();
       //加载更多
       void loadMoreList(int page);
       //开始处理
       void onStart();
       //销毁处理
       void onDestroy();
    }

    interface  Model{
        Observable<GankBean> getIOS(int page_rows, int page);
    }

}
