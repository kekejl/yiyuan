package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.yiyuaninfo.R;

/**
 * Created by Administrator on 2017/2/24.
 */

public class WebViewActivity extends BaseActivity {
    private WebView  webView;
    private String  url;
    private String title;
    private  ProgressBar pb;
    private String  typ="0";
    private  View mErrorView;

    private LinearLayout webParentView;

    @Override
    protected int getContentView() {
        return R.layout.activity_msginfo;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
      webView=(WebView)findViewById(R.id.web_msginfo);
        webParentView=(LinearLayout)webView.getParent() ;
        initErrorPage();
        Intent  intent=getIntent();
        title=intent.getExtras().getString("title");
        url=intent.getExtras().getString("msgurl");
        typ=intent.getExtras().getString("typ");
        setToolBarTitle(title);
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
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if(typ!=null){

                    if(typ.equals("1")){

                        setToolBarTitle(title);

                    }
                }
            }
        });
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);

            showErrorPage();
        }
    }


    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    private void showErrorPage() {
        webParentView.removeAllViews(); //移除加载网页错误时，默认的提示信息
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        webParentView.addView(mErrorView, 0, layoutParams); //添加自定义的错误提示的View

    }
    /***
     * 显示加载失败时自定义的网页
     */
    private void initErrorPage() {
        if (mErrorView == null) {
            mErrorView = View.inflate(this, R.layout.error_view, null);
        }
    }

}