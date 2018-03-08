package com.yiyuaninfo.server;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * Created by ainsc on 2016/4/18/018.
 */
public class MusicUtil {
    private Context mContext;
    private List<String> mMusicPathList;
    private MediaPlayer mMediaPlayer;
    private boolean isPrepareAsyncComplete;

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (isPrepareAsyncComplete) {
                Intent intent = new Intent(MusicServer.UPDATE_PROGRESS);
                intent.putExtra(MusicServer.MUSIC_INDEX, MusicServer.INDEX);
                intent.putExtra(MusicServer.MUSIC_CURR, mMediaPlayer.getCurrentPosition());
                intent.putExtra(MusicServer.MUSIC_TOTAL, mMediaPlayer.getDuration());
                //更新
                mContext.sendBroadcast(intent);
                mHandler.sendEmptyMessageDelayed(1, 500);
            }

        }
    };

    public MusicUtil(Context context, List<String> list) {
        this.mContext = context;
        this.mMusicPathList = list;
        mMediaPlayer = new MediaPlayer();

        //如果异步准备完成，会触发OnPreparedListener.onPrepared()，进而进入Prepared状态。
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                isPrepareAsyncComplete = true;
                mHandler.sendEmptyMessage(1);
            }
        });
    }

    public void playIndex(int index) {
        if (mMediaPlayer != null) {
            isPrepareAsyncComplete = false;
            mMediaPlayer.reset();
        }
        try {

            mMediaPlayer.setDataSource(mContext, Uri.parse(mMusicPathList.get(index)));
            mMediaPlayer.prepareAsync();

        } catch (IOException e) {
            Log.e("MusicSeIOException", e.getMessage());
        }
    }

    public void play() {
        if (!mMediaPlayer.isPlaying())
            mMediaPlayer.start();
    }

    public void pause() {
        if (mMediaPlayer.isPlaying())
            mMediaPlayer.pause();
    }

    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
        }

    }
}