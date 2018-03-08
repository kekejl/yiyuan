package com.yiyuaninfo.Listener;

import android.view.View;

import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.entity.VideoInfo;

/**
 * Created by Administrator on 2017/6/13.
 */

public interface OnRecyclerViewItemClickListener<T> {
    void onItemClick(View view , T data);

}
