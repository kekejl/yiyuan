package com.yiyuaninfo.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by gaocongcong on 2017/9/11.
 */

public class MallSection extends SectionEntity<Mall> {
    public MallSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MallSection(Mall mall) {
        super(mall);
    }
}
