package com.yiyuaninfo.adapter;


import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Music;

import java.util.List;


/**
 * Created by gaocongcong on 2017/8/1.
 */

public class MusicAdapter extends BaseQuickAdapter<Music.MArrBean, BaseViewHolder> {
    public MusicAdapter(List<Music.MArrBean> data) {
        super(R.layout.item_music_list, data);
        Log.d("111111111", data.toString());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Music.MArrBean music) {
       baseViewHolder.setText(R.id.tv_music_name,music.getSname().trim());
        baseViewHolder.setText(R.id.tv_music_singer,music.getSinger());





}

}

