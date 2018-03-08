package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/9/11.
 */

public class XhArrBean {
    /**
     * id : 32
     * actionname : 李易峰2017生日会
     * palace : 北京乐视生态体育中心
     * actiontime : 2017-05-01 19:00:00
     * indeximg : uploads/image/20170424/1493026725.jpg
     * tag : 自定义标签
     * price : 580起
     */

    private String id;
    private String actionname;
    private String palace;
    private String actiontime;
    private String indeximg;
    private String tag;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public String getPalace() {
        return palace;
    }

    public void setPalace(String palace) {
        this.palace = palace;
    }

    public String getActiontime() {
        return actiontime;
    }

    public void setActiontime(String actiontime) {
        this.actiontime = actiontime;
    }

    public String getIndeximg() {
        return indeximg;
    }

    public void setIndeximg(String indeximg) {
        this.indeximg = indeximg;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "XhArrBean{" +
                "id='" + id + '\'' +
                ", actionname='" + actionname + '\'' +
                ", palace='" + palace + '\'' +
                ", actiontime='" + actiontime + '\'' +
                ", indeximg='" + indeximg + '\'' +
                ", tag='" + tag + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
