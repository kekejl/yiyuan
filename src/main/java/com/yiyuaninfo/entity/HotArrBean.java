package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/8/6.
 */

public class HotArrBean {
    /**
     * rname : 壹元服务一块钱
     * rlink : http://www.1yuaninfo.com
     * pop_num : 1111
     */

    private String rname;
    private String rlink;
    private String pop_num;

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRlink() {
        return rlink;
    }

    public void setRlink(String rlink) {
        this.rlink = rlink;
    }

    public String getPop_num() {
        return pop_num;
    }

    public void setPop_num(String pop_num) {
        this.pop_num = pop_num;
    }

    @Override
    public String toString() {
        return "HotArrBean{" +
                "rname='" + rname + '\'' +
                ", rlink='" + rlink + '\'' +
                ", pop_num='" + pop_num + '\'' +
                '}';
    }


}
