package com.yiyuaninfo.adapter;

import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.ShowEntity;
import com.yiyuaninfo.entity.VideoEntity;
import com.yiyuaninfo.entity.XhArrBean;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.view.EasyJCVideoPlayer;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class ShowAdapter extends BaseQuickAdapter<XhArrBean,BaseViewHolder> {
    public ShowAdapter(List<XhArrBean> data) {
        super(R.layout.item_show_xh, data);
        Log.d("111111111", data.toString());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, XhArrBean show) {
        if(show.getIndeximg().startsWith("http")){

        ImageLoaderUtils.displayImage(show.getIndeximg(), (ImageView) baseViewHolder.getView(R.id.iv_show));
        }else {
            ImageLoaderUtils.displayImage(Constants.FInder+show.getIndeximg(), (ImageView) baseViewHolder.getView(R.id.iv_show));

        }

        baseViewHolder.setText(R.id.tv_show_title,show.getActionname())
                     .setText(R.id.tv_show_time,"时间:"+show.getActiontime())
                     .setText(R.id.tv_show_address,"地点:"+show.getPalace())
                     .setText(R.id.tv_show_price,show.getPrice());
}
}

