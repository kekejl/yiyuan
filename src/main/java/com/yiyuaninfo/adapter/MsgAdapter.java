package com.yiyuaninfo.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.FlashSection;
import com.yiyuaninfo.entity.MsgSection;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/19.
 */

public class MsgAdapter extends BaseSectionQuickAdapter<MsgSection,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public MsgAdapter(int layoutResId, int sectionHeadResId, List<MsgSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MsgSection item) {
        helper.setText(R.id.tv_flash_header, item.header);

    }
    @Override
    protected void convert(BaseViewHolder helper, MsgSection item) {
        helper.setText(R.id.tv_flash_title,item.t.getTitle());
        helper.addOnClickListener(R.id.tv_flash_title);

    }
}
