package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/19.
 */

public class FlashBean {

    /**
     * wid : 12
     * title : 白糖需求会逐步好转12
     * addtime : 2017-05-30
     */

    private String wid;
    private String title;
    private String addtime;

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    @Override
    public String toString() {
        return "FlashBean{" +
                "wid='" + wid + '\'' +
                ", title='" + title + '\'' +
                ", addtime='" + addtime + '\'' +
                '}';
    }
}
