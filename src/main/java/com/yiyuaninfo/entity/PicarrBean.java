package com.yiyuaninfo.entity;

import java.io.Serializable;

/**
 * Created by gaocongcong on 2017/9/7.
 */

public class PicarrBean implements Serializable {
    /**
     * img : uploads/image/20170906/1504661674.jpg
     * description : 图片1的描述
     */

    private String img;
    private String description;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PicarrBean{" +
                "img='" + img + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
