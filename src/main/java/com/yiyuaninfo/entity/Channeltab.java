package com.yiyuaninfo.entity;


import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/16.
 */

public class Channeltab implements MultiItemEntity , Serializable {
    public static final int TYPE_MY = 1;
    public static final int TYPE_OTHER = 2;
    public static final int TYPE_MY_CHANNEL = 3;
    public static final int TYPE_OTHER_CHANNEL = 4;
    public String Title;
    public String TitleCode;
    private int itemType;

    public Channeltab(String title, String titleCode) {
        this(TYPE_MY_CHANNEL, title, titleCode);
    }

    public Channeltab(int type, String title, String titleCode) {
        Title = title;
        TitleCode = titleCode;
        itemType = type;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
