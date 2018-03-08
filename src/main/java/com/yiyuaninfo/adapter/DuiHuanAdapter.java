package com.yiyuaninfo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.DuiHuan;
import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.view.FlowLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.yiyuaninfo.util.CommonUtils.getResources;

/**
 * Created by gaocongcong on 2017/7/20.
 */

public class DuiHuanAdapter extends BaseQuickAdapter<DuiHuan.InoutBean,BaseViewHolder> {
    public DuiHuanAdapter( int layoutResId, List<DuiHuan.InoutBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DuiHuan.InoutBean item) {
        if(item.getGoodimg()!=null){

       if(item.getGoodimg().startsWith("http")){
           ImageLoaderUtils.displayImage(item.getGoodimg(),(ImageView) helper.getView(R.id.iv_dhjl));
       }else {
           ImageLoaderUtils.displayImage(Constants.Home3+item.getGoodimg(),(ImageView) helper.getView(R.id.iv_dhjl));

       }
       }
       helper.setText(R.id.tv_dhjl_name,item.getGoodtitle());
       helper.setText(R.id.tv_dhjl_content,item.getExplain());
       helper.setText(R.id.tv_dhjl_jifen,item.getAmount());
       helper.setText(R.id.tv_dhjl_time,item.getAddtime());

    }
}
