package com.yiyuaninfo.server;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import java.util.List;

/**
 * Created by ainsc on 2016/4/15/015.
 */
public class MusicServer extends Service {

    private Context mContext;
    private MusicUtil musicUtil;
    private List<String> mMusicPathList;


    public static final int INIT_MUSIC_PATH = 10000;
    public static final int PREPARE_MUSIC = 100002;
    public static final int STOP_MUSIC = 100003;
    public static final int PAUSE_MUSIC = 100004;
    public static final int START_MUSIC = 100005;
    public static final int NEXT_MUSIC = 100006;
    public static final int PRE_MUSIC = 100007;

    public static final int ONCLICK_MUSIC_LIST = 100008;

    //KEY:
    public static final String EXEC_COMMAND = "exec_command";

    //Handler KEY:
    public static final String UPDATE_PROGRESS = "UPDATE_PROGRESS";
    public static final String MUSIC_INDEX = "MUSIC_INDEX";
    public static final String MUSIC_CURR = "MUSIC_CURR";
    public static final String MUSIC_TOTAL = "MUSIC_TOTAL";

    public static int INDEX = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("onStartCommand", "onStartCommand");
        int command = intent.getIntExtra(MusicServer.EXEC_COMMAND, -1);
        switch (command) {
            //初始化音乐路径，准备音乐
            case INIT_MUSIC_PATH:
            case PREPARE_MUSIC:
                mMusicPathList = intent.getStringArrayListExtra("music_list");
                Log.d("音乐播放地址",mMusicPathList.toString());
                musicUtil = new MusicUtil(mContext, mMusicPathList);
                break;
            //可以从MusicListMainActivity直接点击列表的音乐播放
            case ONCLICK_MUSIC_LIST:
                INDEX = intent.getIntExtra("position", 0);
                Log.e("ONCLICK_MUSIC_LIST", "INDEX" + INDEX);
                musicUtil.playIndex(INDEX);
                break;
            case START_MUSIC:
                musicUtil.play();
                break;
            case STOP_MUSIC:
                musicUtil.stop();
                break;
            case PAUSE_MUSIC:
                musicUtil.pause();
                break;
            case NEXT_MUSIC:
                Log.e("INDEX", "INDEX" + INDEX);
                musicUtil.playIndex(INDEX);
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (musicUtil != null) {
            musicUtil.stop();
            System.exit(0);

        }

    }
}
