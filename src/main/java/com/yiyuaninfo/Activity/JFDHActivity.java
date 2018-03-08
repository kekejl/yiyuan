package com.yiyuaninfo.Activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyuaninfo.Activity.MyActivity.AddAddressActivity;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Interface.JFDHBiz;
import com.yiyuaninfo.Listener.PermissionListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaocongcong on 2017/9/29.
 */

public class JFDHActivity extends BaseActivity implements CommonPopupWindow.ViewInterface{
    private WebView webView;
    private String url = "http://yyapp.1yuaninfo.com/app/yyfwapp/gift_details.php";
    private ProgressBar pb;
    private Product.ProArrBean entity;
    private Button button;
    private String  id;
    private CommonPopupWindow popupWindowtishi;
    private TextView tvfalse, tvtrue;
    private  String  jifen;
    @Override
    protected int getContentView() {
        return R.layout.activity_jfdh;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        webView = (WebView) findViewById(R.id.web_msginfo);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        jifen=intent.getStringExtra("jifen");
        pb = (ProgressBar) findViewById(R.id.pb_msginfo);
        button = (Button) findViewById(R.id.bt_bay);
        pb.setMax(100);
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webView.loadUrl(url+"?id="+id);
        //设置Web视图
        webView.setWebViewClient(new JFDHActivity.webViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setProgress(newProgress);
                if (newProgress == 100) {
                    button.setVisibility(View.VISIBLE);
                    //      pb.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                setToolBarTitle(title);
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showpopwindowtishi();



            }
        });

    }

    @Override
    public void getChildView(View view, int layoutResId) {
        TextView  textView=(TextView)view.findViewById(R.id.tv_pop_cp_kf_title);
        TextView  textViewnum=(TextView)view.findViewById(R.id.tv_pop_cp_kf_num);
        textViewnum.setText("您是否要花"+jifen+"积分兑换该商品");
        tvfalse = (TextView) view.findViewById(R.id.tv_pop_cp_kf_false);
        tvtrue = (TextView) view.findViewById(R.id.tv_pop_cp_kf_true);
        tvfalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindowtishi.dismiss();

            }
        });
        tvtrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowtishi.dismiss();
                Map<String,String> params= new HashMap<>();
                params.put("userid",SPUtil.getUser(JFDHActivity.this).getUserid());
                params.put("goodid",id);
                RetrofitUtil.getretrofit().create(JFDHBiz.class).getData(params).enqueue(new Callback<State>() {
                    @Override
                    public void onResponse(Call<State> call, Response<State> response) {
                        if(response.body().getState().equals("0")){
                            CommonUtil.goAactivity(JFDHActivity.this, AddAddressActivity.class);

                        }else if(response.body().getState().equals("1")){
                            ToastUtils.showToast("兑换成功，客服人员稍后与你联系！");

                        }else if(response.body().getState().equals("2")){
                            ToastUtils.showToast("积分不足");

                        }else if(response.body().getState().equals("3")){
                            ToastUtils.showToast("服务器繁忙，请稍后再试");
                        }
                    }

                    @Override
                    public void onFailure(Call<State> call, Throwable t) {

                    }
                });
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


    private void showpopwindowtishi() {

        if (popupWindowtishi != null && popupWindowtishi.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popwindow_duihuan_tishi, null);
        //测量View的宽高

        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowtishi = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_duihuan_tishi)
                .setWidthAndHeight(width - 300, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        popupWindowtishi.showAtLocation(button, Gravity.CENTER, 0, 0);

    }


}
