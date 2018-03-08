package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/8/6.
 */

public class TagArrBean {

    /**
     * id : 12
     * classname : 时政
     */

    private String id;
    private String classname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Override
    public String toString() {
        return "TagArrBean{" +
                "id='" + id + '\'' +
                ", classname='" + classname + '\'' +
                '}';
    }
}
