package com.wega.gankclient.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.wega.gankclient.R;
import com.wega.gankclient.base.BaseActivity;
import com.wega.gankclient.home.android.AndroidFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GankActivity extends BaseActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    private FragmentManager mFragmentManager;

    /**
     *  onCreate()-->initData()-->initUI()-->loadData();
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        mFragmentManager = getSupportFragmentManager();
    }

    void switchContainer(String tag)
    {
//        mFragmentManager.findFragmentByTag();
        Fragment fragmentByTag = mFragmentManager.findFragmentByTag(tag);
        Fragment fragment=null;
        switch(tag){
            case AndroidFragment.TAG:
                if(fragmentByTag==null)
                {
                    fragment = new AndroidFragment();
                }
                break;
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.fl_container,fragment,tag);


    }


}
