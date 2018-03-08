package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/8/10.
 */

public class Version {

    /**
     * version : 1.0
     * msg : 1.asdasd/n2.sadasdasd/n3.erwqreqwe
     */

    private String version;
    private String msg;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Version{" +
                "version='" + version + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
