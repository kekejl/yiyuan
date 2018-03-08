package com.yiyuaninfo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.Activity.PayActivity;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.TeSe;
import com.yiyuaninfo.entity.UserOrder;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.DateUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/30.
 */

public class TSFWAdapter extends BaseQuickAdapter<TeSe.UsarrBean, BaseViewHolder> {
    public TSFWAdapter(int layoutResId, List<TeSe.UsarrBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeSe.UsarrBean item) {
          helper.setText(R.id.tv_tsfu_time,"服务时间:"+item.getAddtime());
          helper.setText(R.id.tv_tsfw_daima,"代码名称:"+item.getGpdm());
          switch (item.getIsgain()){
              case "0":
                  helper.setText(R.id.tv_tsfw_state,"状态:"+"成功");

                  break;
              case "1":
                  helper.setText(R.id.tv_tsfw_state,"状态:"+"失败");

                  break;
              case "2":
                  helper.setText(R.id.tv_tsfw_state,"状态:"+"持股中");

                  break;
          }
          helper.addOnClickListener(R.id.ll_tsfw_xiangqing);

}
}
