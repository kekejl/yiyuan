package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.umeng.analytics.MobclickAgent;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Custom;
import com.yiyuaninfo.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/24.
 */

public class UmengTeSeActivity extends BaseActivity {
    private WebView webView;
    private ProgressBar pb;
    private List<Custom> listcustom=new ArrayList<>();
    private Intent  intent;
    private String umengUrl;
    @Override
    protected int getContentView() {
        return R.layout.activity_uengtese;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //title=intent.getStringExtra("newstitle");
        intent=getIntent();
        umengUrl=intent.getStringExtra("umengid");
        Log.d("友盟特色服务接收的参数",umengUrl);
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
        webView.setWebViewClient(new UmengTeSeActivity.webViewClient());
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

        if(intent.getStringExtra("umengid")!=null&&!intent.getStringExtra("umengid").equals("")){
            webView.loadUrl(umengUrl);
        }
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setToolBarTitle(title);
            }
        };
        webView.setWebChromeClient(wvcc);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
//        Bundle bun = getIntent().getExtras();
//        if (bun != null) {
//            Set<String> keySet = bun.keySet();
//            for (String key : keySet) {
//                String value = bun.getString(key);
//                //ToastUtils.showToast(value);
//                Custom custom = new Custom(key, value);
//                listcustom.add(custom);
//
//            }
//        }
//        if (listcustom.size() != 0) {
//
//            webView.loadUrl(Constants.UmengMessage+listcustom.get(0).getValue());
//        }
//        Log.d("友盟自定义参数12", listcustom.toString());

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

