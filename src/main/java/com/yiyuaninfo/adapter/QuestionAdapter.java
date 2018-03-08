package com.yiyuaninfo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.CircleImageView;
import com.yiyuaninfo.entity.Project;
import com.yiyuaninfo.entity.WenDa;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import static com.yiyuaninfo.util.CommonUtils.getResources;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class QuestionAdapter extends BaseQuickAdapter<WenDa.WdArrBean, BaseViewHolder> {
    public QuestionAdapter( List<WenDa.WdArrBean> data) {
        super(R.layout.item_question, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WenDa.WdArrBean news) {
        baseViewHolder.setText(R.id.tv_question_name,news.getNiu_name());
        baseViewHolder.setText(R.id.tv_question_title,news.getTitle());
        baseViewHolder.setText(R.id.tv_question_content,news.getDescription());
        CircleImageView  circleImageView=baseViewHolder.getView(R.id.civ_question);
        ImageLoaderUtils.displayImage(news.getNiu_img(),circleImageView);
        if(news.getDescription().equals("")){
          baseViewHolder.setVisible(R.id.tv_question_content,false);
        }
    }
}