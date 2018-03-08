package com.yiyuaninfo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.CircleImageView;
import com.yiyuaninfo.entity.Comment;
import com.yiyuaninfo.entity.FirstCommentBean;
import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.util.DateUtil;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.view.FlowLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.yiyuaninfo.util.CommonUtils.getResources;

/**
 * Created by gaocongcong on 2017/7/20.
 */

public class CommentAdapter extends BaseQuickAdapter<FirstCommentBean,BaseViewHolder> {

    public CommentAdapter( int layoutResId, List<FirstCommentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FirstCommentBean item) {
        ImageLoaderUtils.displayImage(item.getAvatar(),(CircleImageView)helper.getView(R.id.civ_newsdetail));
        if(item.getFlag().equals("0")){
            helper.getView(R.id.iv_newsdetail_zan).setBackgroundResource(R.drawable.dz00);
        }if(item.getFlag().equals("1")){
            helper.getView(R.id.iv_newsdetail_zan).setBackgroundResource(R.drawable.dz02);
        }
        helper.setText(R.id.tv_newsdetail_name,item.getUsername());
        helper.setText(R.id.tv_newsdetail_zan,item.getZan_count());
        helper.setText(R.id.tv_newsdetail_title,item.getReply_msg());
        helper.setText(R.id.tv_newsdetail_time, DateUtils.getShortTime(item.getCreate_date()));
        helper.addOnClickListener(R.id.iv_newsdetail_zan);
        helper.addOnClickListener(R.id.tv_newsdetail_hf);
    }
}
