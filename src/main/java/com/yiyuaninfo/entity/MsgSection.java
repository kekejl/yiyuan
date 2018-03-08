package com.yiyuaninfo.entity;


import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by gaocongcong on 2017/9/19.
 */

public class MsgSection extends SectionEntity<MsgEntity> {
    public MsgSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MsgSection(MsgEntity msgEntity) {
        super(msgEntity);
    }
}
