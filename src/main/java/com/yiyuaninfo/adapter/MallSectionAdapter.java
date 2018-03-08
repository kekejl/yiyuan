package com.yiyuaninfo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.Activity.JFDHActivity;
import com.yiyuaninfo.Activity.JFSCActivity;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.MallSection;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/11.
 */

public class MallSectionAdapter extends BaseSectionQuickAdapter<MallSection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    private Context context;
    public MallSectionAdapter(Context  context,int layoutResId, int sectionHeadResId, List<MallSection> data) {
        super(layoutResId, sectionHeadResId, data);
        this.context=context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MallSection item) {
       helper.setText(R.id.tv_mall_header,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MallSection item) {
        ImageLoaderUtils.displayImage(item.t.getPro_picture(), (ImageView) helper.getView(R.id.iv_mall));
        helper.setText(R.id.tv_mall_name,item.t.getPro_name().trim());
        helper.setText(R.id.tv_mall_tag,item.t.getPro_label());
        helper.setText(R.id.tv_mall_jifen,item.t.getPro_presentprice());
        LinearLayout  linearLayout=helper.getView(R.id.ll_jfsc_duihuan);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.goAactivity(context,JFDHActivity.class,"id",item.t.getId(),"jifen",item.t.getPro_presentprice());

            }
        });


    }
}
