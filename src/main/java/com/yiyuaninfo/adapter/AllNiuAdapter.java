package com.yiyuaninfo.adapter;

import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.CircleImageView;
import com.yiyuaninfo.entity.AllNiu;
import com.yiyuaninfo.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class AllNiuAdapter extends BaseQuickAdapter<AllNiu.AllsubarrBean,BaseViewHolder> {
    public AllNiuAdapter(List<AllNiu.AllsubarrBean> data) {
        super(R.layout.item_my_niu, data);
        Log.d("111111111", data.toString());
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, AllNiu.AllsubarrBean news) {
        ImageLoaderUtils.displayImage(news.getNiu_head(),(CircleImageView)baseViewHolder.getView(R.id.iv_my_media_header));
        baseViewHolder.setText(R.id.tv_my_media_title,news.getNiu_introduction());
        baseViewHolder.setText(R.id.tv_my_media_name,news.getNiu_name());
        baseViewHolder.setText(R.id.tv_my_media_time,news.getPosttime());
    }

}