package com.yiyuaninfo.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.CircleImageView;
import com.yiyuaninfo.entity.CommentList;
import com.yiyuaninfo.entity.CommentReply;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/7/20.
 */

public class CommentReplyAdapter extends BaseQuickAdapter<CommentReply.ReplyCommentBean,BaseViewHolder> {
    private ImageView imageView;
    private String name;
    public CommentReplyAdapter(int layoutResId, List<CommentReply.ReplyCommentBean> data,String name) {
        super(layoutResId, data);
        this.name=name;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentReply.ReplyCommentBean item) {
        ImageLoaderUtils.displayImage(item.getFrom_user_avatar(),(CircleImageView)helper.getView(R.id.civ_newsdetail));

        if(item.getTo_user_name()!=""&&!item.getTo_user_name().equals(name)&&!item.getTo_user_name().equals(item.getFrom_user_name())){
            helper.setText(R.id.tv_newsdetail_name,item.getFrom_user_name()+"||回复@"+item.getTo_user_name());

        }else {

        helper.setText(R.id.tv_newsdetail_name,item.getFrom_user_name());
        }

        helper.setText(R.id.tv_newsdetail_zan,item.getZan_count());
        helper.setText(R.id.tv_newsdetail_title,item.getReply_msg());
        helper.setText(R.id.tv_newsdetail_time,DateUtils.getShortTime(item.getCreate_date()));


        imageView=helper.getView(R.id.iv_newsdetail_zan);
        if(item.getFlag().equals("0")){
            helper.getView(R.id.iv_newsdetail_zan).setBackgroundResource(R.drawable.dz00);
        }if(item.getFlag().equals("1")){
            helper.getView(R.id.iv_newsdetail_zan).setBackgroundResource(R.drawable.dz02);
        }
        helper.addOnClickListener(R.id.iv_newsdetail_zan);
        helper.addOnClickListener(R.id.tv_newsdetail_hf);

    }
}
