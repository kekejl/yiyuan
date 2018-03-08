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
import android.widget.ProgressBar;

import com.umeng.analytics.MobclickAgent;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Custom;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/24.
 */

public class UmengInfoActivity extends BaseActivity {

    private WebView webView;
    private ProgressBar pb;
    private List<Custom> listcustom=new ArrayList<>();
    @Override
    protected int getContentView() {
        return R.layout.activity_newsdetail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //title=intent.getStringExtra("newstitle");
        setToolBarTitle("特色服务");
        webView=(WebView)findViewById(R.id.web_msginfo);
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
        //设置Web视图
        webView.setWebViewClient(new UmengInfoActivity.webViewClient());
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

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        Bundle bun = getIntent().getExtras();
        if (bun != null) {
            Set<String> keySet = bun.keySet();
            for (String key : keySet) {
                String value = bun.getString(key);
                //ToastUtils.showToast(value);
                Custom custom = new Custom(key, value);
                listcustom.add(custom);

            }
        }
        if (listcustom.size() != 0) {

            webView.loadUrl(Constants.UmengMessage+listcustom.get(0).getValue());
        }

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}


