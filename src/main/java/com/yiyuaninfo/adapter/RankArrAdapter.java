package com.yiyuaninfo.adapter;

import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.entity.RankArr;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class RankArrAdapter extends BaseQuickAdapter<RankArr.RankArrBean,BaseViewHolder> {
    public RankArrAdapter(List<RankArr.RankArrBean> data) {
        super(R.layout.item_news_ph, data);
        Log.d("111111111", data.toString());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RankArr.RankArrBean news) {

        baseViewHolder.setText(R.id.tv_news_ph_id,baseViewHolder.getLayoutPosition()+1+".")
                     .setText(R.id.tv_news_ph_name,news.getSelf_name())
                     .setText(R.id.tv_news_ph_wz,news.getSelf_link())
                     .setText(R.id.tv_news_ph_rq,news.getPop_value());

}

}

