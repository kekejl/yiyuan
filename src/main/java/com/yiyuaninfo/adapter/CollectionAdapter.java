package com.yiyuaninfo.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Collection;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/7/20.
 */

public class CollectionAdapter extends BaseQuickAdapter<Collection.UsercollectBean,BaseViewHolder> {

    public CollectionAdapter(int layoutResId, List<Collection.UsercollectBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Collection.UsercollectBean item) {
        if(item.getCol_img().startsWith("http")){
            ImageLoaderUtils.displayImage(item.getCol_img(),(ImageView)helper.getView( R.id.iv_collection));

        }else {
        if(item.getCol_type().equals("1")){

            ImageLoaderUtils.displayImage(Constants.Home3+item.getCol_img(),(ImageView)helper.getView( R.id.iv_collection));
        }else if(item.getCol_type().equals("3")){
            ImageLoaderUtils.displayImage(Constants.XINGMU+item.getCol_img(),(ImageView)helper.getView( R.id.iv_collection));

        }else if(item.getCol_type().equals("5")){
                ImageLoaderUtils.displayImage(Constants.XINGMU+item.getCol_img(),(ImageView)helper.getView( R.id.iv_collection));

            }

        }
        helper.setText(R.id.tv_collection_title,item.getCol_title());
        helper.setText(R.id.tv_collection_time, DateUtils.getShortTime(item.getCol_time()));
        helper.setText(R.id.tv_collection_title,item.getCol_title());
        helper.addOnClickListener(R.id.btnDelete);
        helper.addOnClickListener(R.id.ll_collection);


    }
}
