package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/9/28.
 */

public class PushList {

    private String  name;
    private String  content;

    public PushList(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
