package com.yiyuaninfo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.CircleImageView;
import com.yiyuaninfo.entity.Comment;
import com.yiyuaninfo.entity.Weekly;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/7/20.
 */

public class WeekAdapter extends BaseQuickAdapter<Weekly.InfoBean,BaseViewHolder> {
    private  List<Weekly.InfoBean>  data;
    private  TextView  textView,textViewTopLine;
    private Context   context;
    public WeekAdapter(int layoutResId, List<Weekly.InfoBean> data,Context  context) {
        super(layoutResId, data);
        this.data=data;
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Weekly.InfoBean item) {
              textViewTopLine=helper.getView(R.id.tvTopLine);
        if(helper.getLayoutPosition()==0){
            textViewTopLine.setVisibility(View.INVISIBLE);

        }else {
            helper.setVisible(R.id.tvTopLine,true);

        }
        if(helper.getLayoutPosition()==data.size()-1){
            TextView  textView=helper.getView(R.id.tvBottomLine);
            textView.setVisibility(View.INVISIBLE);
        }else {
            helper.setVisible(R.id.tvBottomLine,true);

        }
        helper.setText(R.id.tv_weekly_name,item.getTitle());
        helper.setText(R.id.tv_weekly_time,item.getTime());
        helper.setText(R.id.tv_weekly_title,item.getBrief());
        textView=(TextView)helper.getView(R.id.tv_weekly_name);
        textView.post(new Runnable() {
            @Override
            public void run() {
        Log.d("textview的高度", textView.getMeasuredHeight()+"");

            }
        });



    }
}
