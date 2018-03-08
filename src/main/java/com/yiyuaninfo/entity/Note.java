package com.yiyuaninfo.entity;

import java.io.Serializable;

/**
 * Created by tan6458 on 2016/4/16.
 */
public class Note implements Serializable {
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Note() {
    }
}
