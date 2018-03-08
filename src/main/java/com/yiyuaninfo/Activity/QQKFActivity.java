package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yiyuaninfo.R;

/**
 * Created by Administrator on 2017/2/24.
 */

public class QQKFActivity extends BaseActivity {
    private WebView webView;
    private ProgressBar pb;

    @Override
    protected int getContentView() {
        return R.layout.activity_msginfo;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("QQ客服");
        webView = (WebView) findViewById(R.id.web_msginfo);
        Intent intent = getIntent();
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
        webView.loadUrl("http://yyapp.1yuaninfo.com/yyfwapp/kefu.html");
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
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (url.startsWith("http") || url.startsWith("https")) { //http和https协议开头的执行正常的流程
                return super.shouldInterceptRequest(view, url);
            } else {  //其他的URL则会开启一个Acitity然后去调用原生APP
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(in);
                return null;
            }
        }

    }
}