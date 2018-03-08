package com.yiyuaninfo.entity;


import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by gaocongcong on 2017/9/19.
 */

public class FlashSection  extends SectionEntity<FlashBean> {

    public FlashSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public FlashSection(FlashBean flashBean) {
        super(flashBean);
    }
}
