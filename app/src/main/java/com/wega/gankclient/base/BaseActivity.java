package com.wega.gankclient.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.wega.gankclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lwj on 2017/12/16 23:19.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    protected BaseActivity mActivity;


    /**
     *  onCreate()-->initData()-->initUI()-->loadData();
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //布局
        setContentView(setLayout());
        ButterKnife.bind(this);
        initData();
        initUI();
        loadData();
    }




    /**
     * 布局
     *
     * @return
     */
    protected abstract int setLayout();


    /**
     * 初始化数据
     */
    protected void initData() {
        mActivity = this;
    }

    /**
     * 初始化UI
     */
    protected void initUI() {
        setupToolbar();
        setupLoadingDialog();
    }
    protected  void loadData(){};
    /**
     * 设置加载框
     */
    private void setupLoadingDialog() {
    }

    /**
     * 设置toolbar
     */
    private void setupToolbar() {
        toolbar.setTitle(setTitle());
        setSupportActionBar(toolbar);
    }

    /**
     * 设置toolbar title
     */
    protected String setTitle() {
        Log.e("setTitle",null+"");
        return null;
    }

    /**
     * 标题
     *
     * @param title
     * @return
     */
    protected String setTitle(String title) {
        toolbar.setTitle(title);
        Log.e("setTitle",toolbar.getTitle()+title);
        return title;
    }

    /**
     * 设置menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_base_list, menu);
        return true;
    }

    /**
     * 设置menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
