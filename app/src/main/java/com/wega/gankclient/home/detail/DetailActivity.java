package com.wega.gankclient.home.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wega.gankclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wega.gankclient.utils.GankConstants.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {


    @BindView(R.id.wv_detail)
    WebView wvDetail;
    private String url;

    public static void launch(Context context, String url) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_URL, url);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra(EXTRA_URL);
        setWebView();
    }

    private void setWebView(){
//声明WebSettings子类
        WebSettings webSettings = wvDetail.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //步骤3. 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        wvDetail.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        //方式1. 加载一个网页：
        wvDetail.loadUrl(url);
    }
}
