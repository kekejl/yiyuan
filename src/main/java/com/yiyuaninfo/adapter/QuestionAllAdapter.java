package com.yiyuaninfo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Util;
import com.yiyuaninfo.entity.DuiHuan;
import com.yiyuaninfo.entity.QuestionAll;
import com.yiyuaninfo.util.DateUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/7/20.
 */

public class QuestionAllAdapter extends BaseQuickAdapter<QuestionAll.InfoBean,BaseViewHolder> {
    public QuestionAllAdapter(int layoutResId, List<QuestionAll.InfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionAll.InfoBean item) {
          helper.setText(R.id.tv_question1,item.getQucotent());
          if(!item.getContent().equals("")){
              helper.setText(R.id.tv_question2,item.getContent());
              helper.setText(R.id.tv_question2_time, DateUtils.getShortTime(item.getPosttime()));
          }else {
              helper.setText(R.id.tv_question2,"暂无回复");
              helper.setVisible(R.id.tv_question2_time,false);
          }

    }
}
