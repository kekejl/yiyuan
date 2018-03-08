package com.yiyuaninfo.entity;


import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/6.
 */

public class InfoArrBean   implements MultiItemEntity {
    public static final int Header = 1;
    public static final int Content = 2;
    /**
     * id : 17673
     * title : 金砖国家领导人第九次会晤举行
     * source : 人民网
     * description : 图集摘要
     * picurl :
     * picarr : [{"img":"uploads/image/20170906/1504661674.jpg","description":"图片1的描述"},{"img":"uploads/image/20170906/1504669411.jpg","description":"图片2的描述"},{"img":"uploads/image/20170906/1504666992.jpg","description":"图片3的描述"},{"img":"uploads/image/20170906/1504664929.jpg","description":"图片4的描述"}]
     * posttime : 2017-09-06 09:26:58
     * keywords : 图集
     * picstate : 4
     */

    private String id;
    private String title;
    private String source;
    private String description;
    private String picurl;
    private String posttime;
    private String keywords;
    private String picstate;
    private List<PicarrBean> picarr;
    private List<TagArrBean> tag_arr;
    private List<HotArrBean> hot_arr;
    private int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public InfoArrBean(String id, String title, String source, String description, String picurl, String posttime, String keywords, String picstate, List<PicarrBean> picarr, List<TagArrBean> tag_arr, List<HotArrBean> hot_arr, int itemType) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.description = description;
        this.picurl = picurl;
        this.posttime = posttime;
        this.keywords = keywords;
        this.picstate = picstate;
        this.picarr = picarr;
        this.tag_arr = tag_arr;
        this.hot_arr = hot_arr;
        this.itemType = itemType;
    }

    public List<TagArrBean> getTag_arr() {
        return tag_arr;
    }

    public void setTag_arr(List<TagArrBean> tag_arr) {
        this.tag_arr = tag_arr;
    }

    public List<HotArrBean> getHot_arr() {
        return hot_arr;
    }

    public void setHot_arr(List<HotArrBean> hot_arr) {
        this.hot_arr = hot_arr;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPicstate() {
        return picstate;
    }

    public void setPicstate(String picstate) {
        this.picstate = picstate;
    }

    public List<PicarrBean> getPicarr() {
        return picarr;
    }

    public void setPicarr(List<PicarrBean> picarr) {
        this.picarr = picarr;
    }

    @Override
    public String toString() {
        return "InfoArrBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", description='" + description + '\'' +
                ", picurl='" + picurl + '\'' +
                ", posttime='" + posttime + '\'' +
                ", keywords='" + keywords + '\'' +
                ", picstate='" + picstate + '\'' +
                ", picarr=" + picarr +
                '}';
    }


    @Override
    public int getItemType() {


        return itemType;
    }
}
