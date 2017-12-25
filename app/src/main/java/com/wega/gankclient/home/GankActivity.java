package com.wega.gankclient.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;

import com.wega.gankclient.R;
import com.wega.gankclient.base.BaseActivity;
import com.wega.gankclient.home.android.AndroidFragment;
import com.wega.gankclient.home.ios.IOSFragment;

import butterknife.BindView;

public class GankActivity extends BaseActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.tl_bottom)
    TabLayout tlBottom;
    private FragmentManager mFragmentManager;

    /**
     * onCreate()-->initData()-->initUI()-->loadData();
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

    @Override
    protected void initUI() {
        super.initUI();
        tlBottom.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("TAGonTabSelected",tab.getText().toString());
                switchContainer(tab.getText().toString() );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e("TAG",tab.getText().toString());
                detachContainer(tab.getText().toString());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //m默认Android
//        tlBottom.getTabAt(0).select();
    }

    @Override
    protected void loadData() {
        super.loadData();
        switchContainer(AndroidFragment.TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    private void switchContainer(String tag) {
        Fragment fragmentByTag = mFragmentManager.findFragmentByTag(tag);
        Fragment fragment = fragmentByTag;
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (tag) {
            case AndroidFragment.TAG://Android
                if (fragmentByTag == null) {
                    fragment = new AndroidFragment();
                    transaction.add(R.id.fl_container, fragment, tag);
                } else {

                    transaction.attach(fragmentByTag);
                }
                transaction.commit();
                break;
            case IOSFragment.TAG://IOS
                if (fragmentByTag == null) {
                    fragment = new IOSFragment();
                    transaction.add(R.id.fl_container, fragment, tag);
                } else {
                    transaction.attach(fragmentByTag);
                }
                transaction.commit();
                break;
        }
        Log.d("GankActivity", "" + fragment);
    }

    private void detachContainer(String tag) {
        Fragment fragmentByTag = mFragmentManager.findFragmentByTag(tag);
        Fragment fragment = fragmentByTag;
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (tag) {
            case AndroidFragment.TAG://Android
                if (fragmentByTag == null) {

                } else {
                    transaction.detach(fragmentByTag);
                }

                break;
            case IOSFragment.TAG://IOS
                if (fragmentByTag == null) {
                } else {
                    transaction.detach(fragmentByTag);
                }
                break;
        }
        transaction.commit();
        Log.d("GankActivity", "" + fragment);
    }

}
