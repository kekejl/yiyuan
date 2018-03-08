package com.yiyuaninfo.entity;

/**
 * Created by Administrator on 2017/6/14.
 */

public class VideoInfo {
    private String url;
    private String title;
    private String time;
    private String image;
    private String tag;
    private String source;
    private String clidks;
    private boolean collect;

    public VideoInfo(String url, String title, String time, String image, String tag, String source, String clidks, boolean collect) {
        this.url = url;
        this.title = title;
        this.time = time;
        this.image = image;
        this.tag = tag;
        this.source = source;
        this.clidks = clidks;
        this.collect = collect;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getClidks() {
        return clidks;
    }

    public void setClidks(String clidks) {
        this.clidks = clidks;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }
}
