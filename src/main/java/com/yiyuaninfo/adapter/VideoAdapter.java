package com.yiyuaninfo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.VideoEntity;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.FlowLayoutUtil;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.EasyJCVideoPlayer;
import com.yiyuaninfo.view.FlowLayout;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class VideoAdapter extends BaseQuickAdapter<VideoEntity.VArrBean,BaseViewHolder> {
  //  private  ImageView  imageView;
    private FlowLayout  flowLayout;
    private Context context;
    public VideoAdapter(List<VideoEntity.VArrBean> data,Context context) {
        super(R.layout.item_video_list, data);
        Log.d("111111111", data.toString());
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, VideoEntity.VArrBean video) {
        EasyJCVideoPlayer videoPlayer = baseViewHolder.getView(R.id.videoPlayer);
        videoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (video.getV_picture().startsWith("http")){

        ImageLoaderUtils.displayImage(video.getV_picture(), videoPlayer.thumbImageView);
        }else {
            ImageLoaderUtils.displayImage(Constants.HomeMUsic+video.getV_picture(), videoPlayer.thumbImageView);

        }

        baseViewHolder
//                .setText(R.id.tvDuration, news.video_duration_str)
                .setText(R.id.tvFrom, video.getV_source());
                //.setText(R.id.tv_video_keyword, video.getV_tag());
        videoPlayer.titleTextView.setText(video.getV_name());
        videoPlayer.titleTextView.setTextSize(16);
        videoPlayer.setDurationText(video.getV_time());

        videoPlayer.setDianJiText(CommonUtil.getNumber(video.getV_hits())+"次播放");
        setPlayer(videoPlayer,video);


        //imageView=(ImageView)baseViewHolder.getView(R.id.ivMore);
         baseViewHolder.addOnClickListener(R.id.ivMore);
         baseViewHolder.addOnClickListener(R.id.ivMore);
         flowLayout=baseViewHolder.getView(R.id.fl_video);
        FlowLayoutUtil  flowLayoutUtil=new FlowLayoutUtil(flowLayout,context);
        flowLayoutUtil.showFlowLayout(video.getV_tag());




}

    private void setPlayer(JCVideoPlayerStandard videoPlayer,  VideoEntity.VArrBean video) {

        if(Network.isConnected()){


        Log.d("视频地址",video.getV_url());
        if(video.getV_url().startsWith("http")){

        videoPlayer.setUp(video.getV_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, video.getV_name());
        }else {
            videoPlayer.setUp(Constants.Home3+video.getV_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, video.getV_name());

        }
        }else {
            ToastUtils.showToast("暂无网络");
        }


    }
}

