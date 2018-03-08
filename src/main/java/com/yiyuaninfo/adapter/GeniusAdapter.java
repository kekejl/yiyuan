package com.yiyuaninfo.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Genius;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.util.ConstanceValue;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class GeniusAdapter extends BaseQuickAdapter<Genius.NiuartArrBean,BaseViewHolder> {
    public GeniusAdapter(List<Genius.NiuartArrBean> data) {
        super(R.layout.item_media, data);
        Log.d("111111111", data.toString());
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Genius.NiuartArrBean news) {
          baseViewHolder.setText(R.id.tv_media_title,news.getTitle());
          baseViewHolder.setText(R.id.tv_media_description,news.getDescription());
          baseViewHolder.setText(R.id.tv_meida_time,DateUtils.getShortTime(news.getPosttime()));
        Log.d("@@@@",news.getPosttime());
        if(news.getPicurl().startsWith("http")){
            ImageLoaderUtils.displayImage(news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.iv_media  ));

        }else {
        ImageLoaderUtils.displayImage(Constants.Home4+news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.iv_media  ));
    }
    }

}