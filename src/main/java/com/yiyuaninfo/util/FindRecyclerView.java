package com.yiyuaninfo.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by gaocongcong on 2018/1/23.
 */

public class FindRecyclerView extends RecyclerView {

    public FindRecyclerView(Context context) {
        this(context, null);
    }

    public FindRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }



    public boolean isTop() {

        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            if (firstVisibleItemPosition == 0) {
                return true;
            }
        }
        return false;

    }


}
