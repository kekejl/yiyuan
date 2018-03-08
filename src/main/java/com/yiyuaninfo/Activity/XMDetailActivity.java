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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Interface.IsCollectionBiz;
import com.yiyuaninfo.Listener.PermissionListener;
import com.yiyuaninfo.Listener.PermissionsResultListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.User;
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
 * Created by gaocongcong on 2017/8/2.
 */

public class XMDetailActivity extends BaseActivity implements View.OnClickListener,CommonPopupWindow.ViewInterface {
    private WebView webView;
    private ProgressBar pb;
    private String url;
    private String title;
    private Intent intent;
    private ImageView imageView;
    private TextView textView;
    private LinearLayout linearLayoutzx, linearLayoutsc;
    private boolean  isTrue=true;
    private boolean  isFavort;
    private User.UserinfoBean  userinfoBean;
    private String collectionid;
    private String id;

    private TextView  tvzx;

    private CommonPopupWindow popupWindowkf;

    private TextView qq;
    private TextView call;
    private TextView cancle;
    @Override
    protected int getContentView() {
        return R.layout.activity_xmdetail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        intent = getIntent();
        url = intent.getStringExtra("xmurl");
        id=intent.getStringExtra("id");

        Log.d("新闻的url", url);
        //title=intent.getStringExtra("newstitle");
        //setToolBarTitle(title);
        tvzx=(TextView)findViewById(R.id.tv_xmdetail_zx);
        webView = (WebView) findViewById(R.id.web_msginfo);
        linearLayoutsc = (LinearLayout) findViewById(R.id.ll_xm_sc);
        linearLayoutzx = (LinearLayout) findViewById(R.id.ll_xm_zx);
        imageView = (ImageView) findViewById(R.id.iv_xm_sc);
        textView = (TextView) findViewById(R.id.tv_xm_sc);
        linearLayoutsc.setOnClickListener(this);
        linearLayoutzx.setOnClickListener(this);
        pb = (ProgressBar) findViewById(R.id.pb_msginfo);
        pb.setMax(100);
        pb.setVisibility(View.GONE);
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
                    // pb.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setToolBarTitle(title);
            }
        });

        if (SPUtil.isLogin(this)) {
            userinfoBean = SPUtil.getUser(this);

            isFavort();
        } else {


        }




        tvzx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            showKF();

            }
        });



    }


    private void showKF() {

        if (popupWindowkf != null && popupWindowkf.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popwindow_kf, null);



        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowkf = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_kf)
                .setWidthAndHeight(width-300, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        popupWindowkf.showAtLocation(webView, Gravity.CENTER, 0, 0);


    }
    @Override
    public void getChildView(View view, int layoutResId) {

        switch (layoutResId) {
            case R.layout.popwindow_kf:
                qq = (TextView) view.findViewById(R.id.tv_kf_qq);
                call = (TextView) view.findViewById(R.id.tv_kf_call);
                cancle = (TextView) view.findViewById(R.id.tv_kf_cancle);
                qq.setOnClickListener(this);
                call.setOnClickListener(this);
                cancle.setOnClickListener(this);
                break;


        }

    }


    private void isFavort() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "collectstate");
        params.put("id", id);
        params.put("col_type", "5");
        params.put("userid", userinfoBean.getUserid());

        RetrofitUtil.getretrofit().create(IsCollectionBiz.class).getData(params).enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                Log.d("项目收藏返回的数据", response.body().getState().toString());
                collectionid=response.body().getState();
                if (response.body().getState().equals("0")) {
                    imageView.setImageResource(R.drawable.xm_collect03);
                    isFavort = false;
                }
                else {
                    imageView.setImageResource(R.drawable.xm_collect04);
                    isFavort = true;
                }
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {


            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
              case R.id.ll_xm_sc :
                  if(SPUtil.isLogin(this)){

                  if(!isFavort){
                      Map<String, String> params = new HashMap<>();
                      params.put("act", "add");
                      params.put("arid", id);
                      params.put("type", "5");
                      params.put("userid",userinfoBean.getUserid());
                      Log.d("用户的id",userinfoBean.getUserid());
                      RetrofitUtil.getretrofit().create(IsCollectionBiz.class).getData(params).enqueue(new Callback<State>() {
                          @Override
                          public void onResponse(Call<State> call, Response<State> response) {
                              Log.d("点击项目收藏返回的状态",response.body().getState());
                              collectionid=response.body().getState();
                              if(!response.body().getState().equals("0")){
                                  imageView.setImageResource(R.drawable.xm_collect04);
                                  isFavort=true;
                                  ToastUtils.showToast("收藏成功");

                              }else {
                                  ToastUtils.showToast("收藏失败");
                              }

                          }

                          @Override
                          public void onFailure(Call<State> call, Throwable t) {

                          }
                      });
                  }else {
                      Map<String, String> params = new HashMap<>();
                      params.put("act", "del");
                      params.put("colid", collectionid);
                      params.put("userid",userinfoBean.getUserid());
                      RetrofitUtil.getretrofit().create(IsCollectionBiz.class).getData(params).enqueue(new Callback<State>() {
                          @Override
                          public void onResponse(Call<State> call, Response<State> response) {
                              Log.d("点击项目收藏返回的状态1",response.body().getState());
                              if(response.body().getState().equals("1")){
                                  imageView.setImageResource(R.drawable.xm_collect03);
                                  isFavort=false;
                                  ToastUtils.showToast("收藏已取消");

                              }else {
                                  ToastUtils.showToast("失败");
                              }

                          }

                          @Override
                          public void onFailure(Call<State> call, Throwable t) {

                          }
                      });
                  }
                  }else {
                      CommonUtil.goAactivity(XMDetailActivity.this, LogInActivity.class);
                  }

            break;
               case R.id.ll_xm_zx:
            break;

                case R.id.tv_kf_qq:
                    CommonUtil.goAactivity(this, QQKFActivity.class);
                    this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    break;
                case R.id.tv_kf_call:


                    requestRuntimePermission(new String[]{Manifest.permission.READ_PHONE_STATE}
                            , new PermissionListener() {
                                @Override
                                public void onGranted() {
                                    CommonUtil.showToast(XMDetailActivity.this, "已申请权限");
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_CALL);
                                    intent.setData(Uri.parse("tel:01087777077"));
                                    startActivity(intent);
                                }

                                @Override
                                public void onDenied(List<String> deniedPermission) {
                                    CommonUtil.showToast(XMDetailActivity.this, "已拒绝权限");

                                }
                            });


                    break;
                case R.id.tv_kf_cancle:
                    popupWindowkf.dismiss();
                    break;

        }
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }
}
