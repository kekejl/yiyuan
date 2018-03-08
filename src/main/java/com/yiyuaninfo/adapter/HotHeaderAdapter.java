package com.yiyuaninfo.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.HotArrBean;
import com.yiyuaninfo.entity.HotEntity;
import com.yiyuaninfo.entity.NewsEntity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class HotHeaderAdapter extends BaseQuickAdapter<HotArrBean,BaseViewHolder> {
    public HotHeaderAdapter(List<HotArrBean> data) {
        super(R.layout.item_news_ph, data);
        Log.d("111111111", data.toString());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HotArrBean news) {
        Log.d("排行id", baseViewHolder.getLayoutPosition() + "");
        ImageView imageView = baseViewHolder.getView(R.id.iv_news_ph_id);
        TextView textView = baseViewHolder.getView(R.id.tv_news_ph_id);
        if (baseViewHolder.getLayoutPosition() == 0) {
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.ph_one);
            baseViewHolder.setText(R.id.tv_news_ph_name, news.getRname())
                    .setText(R.id.tv_news_ph_wz, news.getRlink())
                    .setText(R.id.tv_news_ph_rq, news.getPop_num());
        } else if (baseViewHolder.getLayoutPosition() == 1) {
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.ph_two);
            baseViewHolder.setText(R.id.tv_news_ph_name, news.getRname())
                    .setText(R.id.tv_news_ph_wz, news.getRlink())
                    .setText(R.id.tv_news_ph_rq, news.getPop_num());
        } else if (baseViewHolder.getLayoutPosition() == 2) {
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.ph_three);
            baseViewHolder.setText(R.id.tv_news_ph_name, news.getRname())
                    .setText(R.id.tv_news_ph_wz, news.getRlink())
                    .setText(R.id.tv_news_ph_rq, news.getPop_num());
        } else {
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            baseViewHolder.setText(R.id.tv_news_ph_id, baseViewHolder.getLayoutPosition() + 1 + ".")
                    .setText(R.id.tv_news_ph_name, news.getRname())
                    .setText(R.id.tv_news_ph_wz, news.getRlink())
                    .setText(R.id.tv_news_ph_rq, news.getPop_num());

        }
    }

}

