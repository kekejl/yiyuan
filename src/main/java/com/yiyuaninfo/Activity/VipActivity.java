package com.yiyuaninfo.Activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Listener.PermissionListener;
import com.yiyuaninfo.Listener.PermissionsResultListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.wxapi.WXPayEntryActivity;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by gaocongcong on 2017/9/29.
 */

public class VipActivity extends BaseActivity implements CommonPopupWindow.ViewInterface {
    private WebView webView;
    private String url = "http://yyapp.1yuaninfo.com/app/yyfwapp/goods_info.php?goodtpye=";
    private ProgressBar pb;
    private Product.ProArrBean entity;
    private Button button;
    private String type, id;
    private CommonPopupWindow popupWindowtishi;
    private TextView tvfalse, tvtrue;
    private static final int PER_REQUEST_CODE = 2;
    private Date  afterDate;
    private View  upView;
    @Override
    protected int getContentView() {
        return R.layout.activity_vip;
    }

    @Override
    protected void init(Bundle  savedInstanceState) {
        webView = (WebView) findViewById(R.id.web_msginfo);
        Intent intent = getIntent();
        entity = (Product.ProArrBean) intent.getSerializableExtra("entity");
        type = intent.getStringExtra("type");
        if (type.equals("1")) {

            id = intent.getStringExtra("id");

        }
        if (type.equals("2")) {

            id = entity.getId();

        }
        if (type.equals("3")) {


        }
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
        Log.d("产品地址", url + type + "&goodid=" + id);
        webView.loadUrl(url + type + "&goodid=" + id);
        //设置Web视图
        webView.setWebViewClient(new VipActivity.webViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setProgress(newProgress);
                if (newProgress == 100) {
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


                if (type.equals("1")) {

                    if (SPUtil.isLogin(VipActivity.this)) {



                        Format f = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar c = Calendar.getInstance();
                        Log.d("当前时间:" , f.format(c.getTime()));
                        c.add(Calendar.YEAR, 4);
                        Log.d("当前时间:" , f.format(c.getTime()));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        Date  beforeDate=c.getTime();
                        try {
                            afterDate=sdf.parse(SPUtil.getUser(VipActivity.this).getExpiretime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        Log.d("日期比较",beforeDate.before(afterDate)+"");

                        if(beforeDate.before(afterDate)){

                            ToastUtils.showToast("您的会员已达五年，暂无法购买");
                        }else {
                            CommonUtil.goAactivity(VipActivity.this, WXPayEntryActivity.class, "id", id, "type", type);

                        }


                        Log.d("产品地址", id + "++" + type);




                    } else {
                        CommonUtil.goAactivity(VipActivity.this, LogInActivity.class);
                    }

                } else {


                    //ToastUtils.showToast("弹框");

                    showpopwindowtishi();


                }
            }
        });

    }

    @Override
    public void getChildView(View view, int layoutResId) {
        TextView  textView=(TextView)view.findViewById(R.id.tv_pop_cp_kf_title);
        if(entity.getTip()==null){

            textView.setText("如欲了解关于产品的详细信息，请雨我们的客服代表联系，点击确定拨打客服电话 010—8777077");

        }else {

         textView.setText(entity.getTip());
        }
       // textView.setText("哈哈啊哈哈啊哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
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
                final String   phone;
                if(entity.getMobile()==null){
                    phone="010—8777077";

                }else {

                   phone=entity.getMobile();
                }

                requestRuntimePermission(new String[]{Manifest.permission.READ_PHONE_STATE},
                        new PermissionListener() {
                            @Override
                            public void onGranted() {

                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:"+phone));
                                startActivity(intent);
                            }

                            @Override
                            public void onDenied(List<String> deniedPermission) {
                                CommonUtil.showToast(VipActivity.this, "已拒绝权限");

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
         upView = LayoutInflater.from(this).inflate(R.layout.popwindow_cp_kf, null);
        //测量View的宽高
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowtishi = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_cp_kf)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        popupWindowtishi.showAtLocation(button, Gravity.CENTER, 0, 0);

    }
//    private void showpopwindowtishi() {
//        if (popupWindowtishi != null && popupWindowtishi.isShowing()) return;
//         upView = LayoutInflater.from(this).inflate(R.layout.popwindow_text, null);
//        //测量View的宽高
//        WindowManager wm = this.getWindowManager();
//        int width = wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();
//        CommonUtil.measureWidthAndHeight(upView);
//        popupWindowtishi = new CommonPopupWindow.Builder(this)
//                .setView(R.layout.popwindow_text)
//                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
//                .setAnimationStyle(R.style.AnimUpkf)
//                .setViewOnclickListener(this)
//                .create();
//        popupWindowtishi.showAtLocation(button, Gravity.CENTER, 0, 0);
//
//    }

}
