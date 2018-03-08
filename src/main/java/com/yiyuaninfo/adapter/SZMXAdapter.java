package com.yiyuaninfo.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.DuiHuan;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/7/20.
 */

public class SZMXAdapter extends BaseQuickAdapter<DuiHuan.InoutBean,BaseViewHolder> {
    public SZMXAdapter(int layoutResId, List<DuiHuan.InoutBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DuiHuan.InoutBean item) {
       helper.setText(R.id.tv_szmx_title,item.getGoodtitle());
       //helper.setText(R.id.tv_szmx_time, DateUtils.getTimePoint(item.getAddtime()));
       helper.setText(R.id.tv_szmx_time, item.getAddtime());
      switch (item.getType()){
          case "0":
       helper.setText(R.id.tv_szmx_jifen,"+"+item.getAmount());

              break;
          case "1":
              helper.setText(R.id.tv_szmx_jifen,"-"+item.getAmount());

              break;
      }

    }
}
