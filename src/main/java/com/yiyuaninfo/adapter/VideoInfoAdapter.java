package com.yiyuaninfo.adapter;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.VideoEntity;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/10/21.
 */

public class VideoInfoAdapter extends BaseQuickAdapter<VideoEntity.VArrBean,BaseViewHolder> {
    public VideoInfoAdapter(int layoutResId, List<VideoEntity.VArrBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoEntity.VArrBean item) {
        helper.setVisible(R.id.rlRightImg,true);
        helper.setVisible(R.id.llVideo,true);
        if(item.getV_picture().startsWith("http")){

        ImageLoaderUtils.displayImage(item.getV_picture(),(ImageView) helper.getView(R.id.ivRightImg1));
        }else {
            ImageLoaderUtils.displayImage(Constants.FInder+item.getV_picture(),(ImageView) helper.getView(R.id.ivRightImg1));

        }
        if(item.getV_time()!=null){

            helper.setText(R.id.tvDuration,item.getV_time());
        }
        if(item.getV_name()!=null){

            helper.setText(R.id.tvName,item.getV_name());
        }
        helper.setText(R.id.tvAuthorName,item.getV_source());
        helper.setText(R.id.tvTime,item.getV_hits()+"次点击");

        helper.setVisible(R.id.viewFill,true);




    }
}
