package com.yiyuaninfo.adapter;

import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Genius;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class MediaHeaderAdapter extends BaseQuickAdapter<Genius.NiuArrBean,BaseViewHolder> {
    public MediaHeaderAdapter(List<Genius.NiuArrBean> data) {
        super(R.layout.item_media_header_media, data);
        Log.d("111111111", data.toString());
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Genius.NiuArrBean news) {
//          if(baseViewHolder.getLayoutPosition()==0){
//              baseViewHolder.setVisible(R.id.iv_media_ph_id,true);
//              baseViewHolder.setImageResource(R.id.iv_media_ph_id,R.drawable.ph_one);
//              baseViewHolder.setVisible(R.id.tv_media_ph_id,false);
//          }else
//        if(baseViewHolder.getLayoutPosition()==1){
//            baseViewHolder.setVisible(R.id.iv_media_ph_id,true);
//            baseViewHolder.setImageResource(R.id.iv_media_ph_id,R.drawable.ph_two);
//            baseViewHolder.setVisible(R.id.tv_media_ph_id,false);
//        }else
//        if(baseViewHolder.getLayoutPosition()==2){
//            baseViewHolder.setVisible(R.id.iv_media_ph_id,true);
//            baseViewHolder.setImageResource(R.id.iv_media_ph_id,R.drawable.ph_three);
//            baseViewHolder.setVisible(R.id.tv_media_ph_id,false);
//        }else {
//            baseViewHolder.setVisible(R.id.iv_media_ph_id,false);
//            baseViewHolder.setVisible(R.id.tv_media_ph_id,true);
//            baseViewHolder.setText(R.id.tv_media_ph_id,baseViewHolder.getLayoutPosition()+1+".");
//        }
        baseViewHolder.setVisible(R.id.iv_media_ph_id,false);
        baseViewHolder.setVisible(R.id.tv_media_ph_id,false);
          baseViewHolder.setText(R.id.tv_media_header_name,news.getNiu_name());
          baseViewHolder.setText(R.id.tv_media_header_dingwei,news.getNiu_tag());
          baseViewHolder.setText(R.id.tv_media_header_pop, CommonUtil.getNumber(news.getNiu_pop()));
          baseViewHolder.setText(R.id.tv_media_header_introduce,news.getNiu_introduce());
          //baseViewHolder.setText(R.id.tv_media_header_time,DateUtils.getShortTime(news.getNiu_modtime()));

        ImageLoaderUtils.displayImage(news.getNiu_img(), (ImageView) baseViewHolder.getView(R.id.iv_media_header  ));
    }

}