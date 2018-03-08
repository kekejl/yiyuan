package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/9/28.
 */

public class Devicetoken {

    /**
     * devicetoken : AslHVi9Av6uZnH1gEzhjy8n9e_iONsdvGFqvHiQw57sj
     * userid : 1506568027m1la4j
     * groupid : 1
     * expiretime : 2017-10-28
     */

    private String devicetoken;
    private String userid;
    private String groupid;
    private String expiretime;

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(String expiretime) {
        this.expiretime = expiretime;
    }
}
