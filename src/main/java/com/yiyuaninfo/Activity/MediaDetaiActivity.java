package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.yiyuaninfo.R;
import com.yiyuaninfo.util.CommonUtil;

/**
 * Created by gaocongcong on 2017/8/2.
 */

public class MediaDetaiActivity extends BaseActivity {
    private WebView  webView;
    private ProgressBar   pb;
    private String  url;
    private String  title;
    private Intent  intent;
    private Button imageView;
    @Override
    protected int getContentView() {
        return R.layout.activity_mediadetail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        intent=getIntent();
        url=intent.getStringExtra("newsurl");
        Log.d("新闻的url",url);
        //title=intent.getStringExtra("newstitle");
        //setToolBarTitle(title);
        webView=(WebView)findViewById(R.id.web_msginfo);
        imageView=(Button) findViewById(R.id.iv_wen);
        pb = (ProgressBar) findViewById(R.id.pb_msginfo);
        pb.setMax(100);
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webView.loadUrl(url);
        //设置Web视图
        webView.setWebViewClient(new webViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setProgress(newProgress);
                if (newProgress == 100) {
                    pb.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }

        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.goAactivity(MediaDetaiActivity.this, WenActivity.class);
            }
        });
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
