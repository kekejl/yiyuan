package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.yiyuaninfo.R;
import com.yiyuaninfo.util.SharedPreferencesUtil;
import com.yiyuaninfo.view.CustomVideoView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Eric on 2017/1/21.
 */

public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.cv_video)
    CustomVideoView  videoView;
    @BindView(R.id.bt_video)
    Button  button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.welcome));

        //播放
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

    }
    @OnClick(R.id.bt_video)
    public   void   click(){
        Intent intent = new Intent(VideoActivity.this, SplashActivity.class);
        startActivity(intent);
        SharedPreferencesUtil.putBoolean(VideoActivity.this, SharedPreferencesUtil.FIRST_OPEN, false);
        finish();
    }

}
