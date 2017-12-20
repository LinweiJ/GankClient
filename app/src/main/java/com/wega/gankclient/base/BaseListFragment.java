package com.wega.gankclient.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wega.gankclient.R;
import com.wega.gankclient.base.adapter.WBaseAdapter;
import com.wega.gankclient.base.adapter.WLoadMoreView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.wega.gankclient.utils.GankConstants.LIST_ROWS_DEFAULT;

/**
 * Created by lwj on 2017/12/16 23:21.
 */

public abstract class BaseListFragment<T>  extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    protected View mLayout;
    protected LayoutInflater mInflater;
    protected Handler mHandler;

    //页数
    protected int page;
    //每页数
    protected int page_rows = LIST_ROWS_DEFAULT;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;
    private LinearLayoutManager mLayoutManager;
    private View mLoadingView;
    private View mNotDataView;
    private View mErrorView;
    private WBaseAdapter mBaseListAdapter;
    private boolean mIsLoadMoreEnd;

    /**
     * onCreateView-->initData-->initUI-->onRefresh
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLayout == null) {

            mInflater = inflater;
            mLayout = inflater.inflate(setLayout(), container, false);
            unbinder = ButterKnife.bind(this, mLayout);
            initData();
            initUI();
        }

        return mLayout;
    }

    /**
     * 布局
     *
     * @return
     */
    protected int setLayout() {
        return R.layout.fragment_base_list;
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 初始化UI
     */
    protected void initUI() {


        setupSwipeRefreshLayout();

        setupRecyclerView();

        mHandler = new Handler();

    }

    /**
     * 设置SwipeRefreshLayout
     */
    private void setupSwipeRefreshLayout() {


        //改变加载显示的颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimaryDark);
        //设置监听
        mSwipeRefreshLayout.setOnRefreshListener(this);


    }

    /**
     * 设置RecyclerView
     */
    private void setupRecyclerView() {

        //布局管理
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //适配器
        mBaseListAdapter = setAdapter();
        //动画
//        mGDOrderListAdapter.openLoadAnimation();

        mRecyclerView.setAdapter(mBaseListAdapter);

        //底部加载
        mBaseListAdapter.setLoadMoreView(new WLoadMoreView());

        //底部加载更多
        mBaseListAdapter.setOnLoadMoreListener(new WBaseAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mIsLoadMoreEnd) {
                    loadMoreEnd();
                } else {

                    loadMore();
                }
            }
        }, mRecyclerView);

        //加载中
        mLoadingView = mInflater.inflate(R.layout.rv_loading_view, (ViewGroup) mRecyclerView.getParent(), false);
        //空数据
        mNotDataView = mInflater.inflate(R.layout.rv_empty_view, (ViewGroup) mRecyclerView.getParent(), false);
        mNotDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        //网络异常
        mErrorView = mInflater.inflate(R.layout.rv_error_view, (ViewGroup) mRecyclerView.getParent(), false);
        mErrorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    /**
     * rv 适配器
     *
     * @return
     */
    protected abstract WBaseAdapter setAdapter();

    /**
     * 获取列表数据
     */
    protected abstract List getListData(List<T> data);

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        page = 1;

        //列表加载中
        showLoadingView();

        //关闭加载
        mBaseListAdapter.setEnableLoadMore(false);
    }

    /**
     * 底部加载更多
     */
    protected void loadMore() {

        page++;
    }


    /**
     * 刷新列表数据
     *
     * @param data
     */
    public void refreshListByData(List<T> data) {
        mIsLoadMoreEnd = data == null || data.size() < page_rows;
        if (data != null && data.size() > 0) {
            if (page == 1) {//第一次加载or 刷新
                //更新数据
                mBaseListAdapter.setNewData(data);
            }
            //停止刷新
            stopRefresh();
        } else {
            //无数据
            showNotDataView();
        }
    }


    /**
     * 数据全部加载完毕
     */
    public  void  loadMoreEnd(){
        mBaseListAdapter.loadMoreEnd();
    }

    /**
     * 获取更多数据失败
     */
    public  void  loadMoreFail(){
        mBaseListAdapter.loadMoreFail();
    }
    /**
     * 获取更多数据加载成功
     */
    public  void  loadMoreComplete(){
        mBaseListAdapter.loadMoreComplete();
    }


    /**
     * 加载更多数据
     *
     * @param resData
     */
    private void addListByData(List resData) {

        mIsLoadMoreEnd = resData == null || resData.size() < page_rows;

        //成功获取更多数据
        mBaseListAdapter.addData(resData);
        if (mIsLoadMoreEnd) {
            //数据全部加载完毕
            loadMoreEnd();
        } else {
            //加载成功
            loadMoreComplete();
        }

        //检查是否满一屏，如果不满足关闭loadMore
//        mServerOrderListAdapter.disableLoadMoreIfNotFullPage();
    }




    /**
     * 列表加载中
     */
    public void showLoadingView() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            mBaseListAdapter.setEmptyView(mLoadingView);
            mBaseListAdapter.setNewData(null);
        }
    }


    /**
     * 列表无数据
     */
    public void showNotDataView() {
        mBaseListAdapter.setEmptyView(mNotDataView);
        mBaseListAdapter.setNewData(null);
        //停止刷新
        stopRefresh();
    }

    /**
     * 网络异常
     */
    public void showErrorView() {
        mBaseListAdapter.setEmptyView(mErrorView);
        mBaseListAdapter.setNewData(null);
        //停止刷新
        stopRefresh();
    }

    /**
     * 停止刷新
     */
    private void stopRefresh() {
        //停止刷新
        mSwipeRefreshLayout.setRefreshing(false);
        //打开加载
        mBaseListAdapter.setEnableLoadMore(true);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
