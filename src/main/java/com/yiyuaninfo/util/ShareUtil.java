package com.yiyuaninfo.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;
import com.yiyuaninfo.R;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;

/**
 * Created by gaocongcong on 2017/11/2.
 */

public class ShareUtil  implements CommonPopupWindow.ViewInterface,View.OnClickListener{
    private static  CommonPopupWindow  popupWindow;
    private Context context;
    private View view;
    private UMShareListener  shareListener;
    private Activity activity;
    private LinearLayout sharecancle;
    private ImageView ivweixin, ivqq, ivfriend, ivqzone, ivsina;
    private String title,image,description,url;

    public ShareUtil(Context context, View view, Activity activity, String title, String image, String description, String url) {
        this.context = context;
        this.view = view;
        this.activity = activity;
        this.title = title;
        this.image = image;
        this.description = description;
        this.url = url;
    }

    public  void ShowPopupWindow(){
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(context).inflate(R.layout.popwindow_share, null);
        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(context)
                .setView(R.layout.popwindow_share)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId){

            case R.layout.popwindow_share:
                sharecancle = (LinearLayout) view.findViewById(R.id.ll_my_share_cancle);
                ivfriend = (ImageView) view.findViewById(R.id.iv_my_share_friend);
                ivqq = (ImageView) view.findViewById(R.id.iv_my_share_qq);
                ivqzone = (ImageView) view.findViewById(R.id.iv_my_share_qzone);
                ivweixin = (ImageView) view.findViewById(R.id.iv_my_share_weixin);
                ivsina = (ImageView) view.findViewById(R.id.iv_my_share_sina);
                LinearLayout  text=(LinearLayout)view.findViewById(R.id.ll_share_text);
                LinearLayout  link=(LinearLayout)view.findViewById(R.id.ll_share_link);
                text.setVisibility(View.INVISIBLE);
                link.setVisibility(View.INVISIBLE);
                ivfriend.setOnClickListener(this);
                ivqq.setOnClickListener(this);
                ivqzone.setOnClickListener(this);
                ivweixin.setOnClickListener(this);
                ivsina.setOnClickListener(this);
                sharecancle.setOnClickListener(this);
                break;
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_my_share_friend:
                ShareWeb(SHARE_MEDIA.WEIXIN_CIRCLE);
                popupWindow.dismiss();

                break;
            case R.id.iv_my_share_qq:
                ShareWeb(SHARE_MEDIA.QQ);
                popupWindow.dismiss();
                break;
            case R.id.iv_my_share_weixin:
                ShareWeb(SHARE_MEDIA.WEIXIN);
                popupWindow.dismiss();
                break;
            case R.id.iv_my_share_qzone:
                ShareWeb(SHARE_MEDIA.QZONE);
                popupWindow.dismiss();
                break;
            case R.id.iv_my_share_sina:
                ShareWeb(SHARE_MEDIA.SINA);
                popupWindow.dismiss();
                break;
            case R.id.ll_my_share_cancle:
                popupWindow.dismiss();
                break;
        }
    }

    private void ShareWeb(SHARE_MEDIA  type) {
        UMImage thumb = new UMImage(context,image);
        UMWeb web = new UMWeb(url);
        web.setThumb(thumb);
        web.setDescription(description);
        web.setTitle(title);
        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(type)
                .setCallback(shareListener)
                .share();
        shareListener=new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                ToastUtils.showToast("正在分享");
            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {
                ToastUtils.showToast("分享成功");

            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                ToastUtils.showToast("分享失败");

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                ToastUtils.showToast("分享取消");

            }
        };
    }

}
