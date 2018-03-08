package com.yiyuaninfo.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.yiyuaninfo.R;

/**
 * Created by gaocongcong on 2018/2/7.
 */

public class EmptyUtil {

    View  mErrorView;
    LinearLayout  webParentView;
    Context  context;

    public EmptyUtil(Context context,RecyclerView  recyclerview) {
        this.context = context;
        this.webParentView=(LinearLayout) recyclerview.getParent();
    }

    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    public void showErrorPage() {
        webParentView.removeAllViews(); //移除加载网页错误时，默认的提示信息
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        webParentView.addView(mErrorView, 0, layoutParams); //添加自定义的错误提示的View

    }

    /***
     * 显示加载失败时自定义的网页
     */
    public   void initErrorPage() {
        if (mErrorView == null) {
            mErrorView = View.inflate(context, R.layout.error_view, null);
        }
    }
}
