package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Interface.MallEntityBiz;
import com.yiyuaninfo.Interface.VideoEntityBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.MallSectionAdapter;
import com.yiyuaninfo.adapter.VideoInfoAdapter;
import com.yiyuaninfo.entity.MallEntity;
import com.yiyuaninfo.entity.MallSection;
import com.yiyuaninfo.entity.VideoEntity;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.ShareUtil;
import com.yiyuaninfo.view.EasyJCVideoPlayer;
import com.yiyuaninfo.view.MyDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class VideoDetailActivity extends BaseActivity {
    private EasyJCVideoPlayer  player;
    private VideoEntity.VArrBean  entity;
    private ImageView  ivBack,share;
    private RecyclerView  recyclerView;
    private TextView  textView;
    private VideoInfoAdapter  adapter;
    @Override
    protected int getContentView() {
        return R.layout.activity_videodetail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isshowToolBar(false);
        ivBack=(ImageView)findViewById(R.id.iv_video_back);
        share=(ImageView)findViewById(R.id.iv_video_share);
        recyclerView=(RecyclerView)findViewById(R.id.rv_video);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this,MyDecoration.VERTICAL_LIST));
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView=(TextView)findViewById(R.id.tv_video_title);

        player=(EasyJCVideoPlayer)findViewById(R.id.videoPlaye_detail);
        Intent  intent=getIntent();
        entity=(VideoEntity.VArrBean)intent.getSerializableExtra("entity");
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil shareUtil = new ShareUtil(VideoDetailActivity.this,
                        recyclerView,
                        VideoDetailActivity.this,
                        entity.getV_name(),
                        entity.getV_picture(),
                        entity.getV_name(),
                        Constants.VIDEOSHARE+entity.getId()


                );
                shareUtil.ShowPopupWindow();
            }
        });
        WindowManager wm =this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        LinearLayout.LayoutParams params1= (LinearLayout.LayoutParams) player.getLayoutParams();
        params1.height=(width*3)/5;
        params1.width=width;
        player.setLayoutParams(params1);
        player.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (entity.getV_picture().startsWith("http")){

            ImageLoaderUtils.displayImage(entity.getV_picture(), player.thumbImageView);
        }else {
            ImageLoaderUtils.displayImage(Constants.HomeMUsic+entity.getV_picture(), player.thumbImageView);

        }
       // player.titleTextView.setText(entity.getV_name());
        //player.titleTextView.setTextSize(16);
        player.setDurationText(entity.getV_time());
        setPlayer(player,entity);

       // player.playOnThisJcvd();
       // player.startButton.performClick();
        textView.setText(entity.getV_name());
         getData();

    }

    private void getData() {
        Map<String,String>  params=new HashMap<>();
        params.put("act","randomvideo");
        params.put("vid",entity.getId());

        RetrofitUtil.getretrofit().create(VideoEntityBiz.class).getData(params).enqueue(new Callback<VideoEntity>() {
            @Override
            public void onResponse(Call<VideoEntity> call, Response<VideoEntity> response) {
                Log.d("视频详情数据",response.body().getV_arr().toString());
                adapter=new VideoInfoAdapter(R.layout.item_news,response.body().getV_arr());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        VideoEntity.VArrBean  video=(VideoEntity.VArrBean) adapter.getData().get(position);
                        setPlayer(player,video);
                        textView.setText(video.getV_name());


                    }
                });
            }

            @Override
            public void onFailure(Call<VideoEntity> call, Throwable t) {

            }
        });

    }

    private void setPlayer(JCVideoPlayerStandard videoPlayer, VideoEntity.VArrBean video) {
        Log.d("视频地址",video.getV_url());
        if(video.getV_url().startsWith("http")){

            videoPlayer.setUp(video.getV_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        }else {
            videoPlayer.setUp(Constants.Home3+video.getV_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");

        }
        player.startButton.performClick();

    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }




}
