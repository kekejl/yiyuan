package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

public class YanBaoActivity extends BaseActivity {
    private WebView webView;
    private ProgressBar pb;
    private List<Custom> listcustom = new ArrayList<>();
    private Intent intent;
    private String umengUrl;

    @Override
    protected int getContentView() {
        return R.layout.activity_uengtese;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //title=intent.getStringExtra("newstitle");
        setToolBarTitle("研报");
        intent = getIntent();
        umengUrl = intent.getStringExtra("yanbao");
        Log.d("特色服务接收的参数", umengUrl);
        webView = (WebView) findViewById(R.id.web_msginfo);
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
      //  webView.setWebViewClient(new YanBaoActivity.webViewClient());
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


        webView.loadUrl(umengUrl);

    }


    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("pdf地址",url);

          //  CommonUtil.goAactivity(YanBaoActivity.this,PDFActivity.class,"url",url);
            view.loadUrl(url);

            //view.loadUrl("http://docs.google.com/gview?embedded=true&url=" +url);

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            startActivity(Intent.createChooser(intent, "请选择浏览器"));



            return false;
        }

    }


}

