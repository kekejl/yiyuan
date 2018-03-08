package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ImageInfoType {

    /**
     * imagesid : [1]
     * type : 0
     * name : 校长
     */

    private int type;
    private String name;
    private List<Integer> imagesid;

    public ImageInfoType(int type, String name, List<Integer > imagesid) {
        this.type = type;
        this.name = name;
        this.imagesid = imagesid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer > getImagesid() {
        return imagesid;
    }

    public void setImagesid(List<Integer > imagesid) {
        this.imagesid = imagesid;
    }
}
