package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/9/12.
 */

public class CompanyDetailsProduct {
    public CompanyDetailsProduct(String id, String com_pic, String label, String introduce, String part, String yname, String ystate, String yprice) {
        this.id = id;
        this.com_pic = com_pic;
        this.label = label;
        this.introduce = introduce;
        this.part = part;
        this.yname = yname;
        this.ystate = ystate;
        this.yprice = yprice;
    }

    /**
     * id : 1
     * com_pic : logo
     * label : 1
     * introduce : 按时间的
     * part : 股票型
     * yname : 画龙点睛
     * ystate : 1
     * yprice : 1889
     */

    private String id;
    private String com_pic;
    private String label;
    private String introduce;
    private String part;
    private String yname;
    private String ystate;
    private String yprice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCom_pic() {
        return com_pic;
    }

    public void setCom_pic(String com_pic) {
        this.com_pic = com_pic;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getYname() {
        return yname;
    }

    public void setYname(String yname) {
        this.yname = yname;
    }

    public String getYstate() {
        return ystate;
    }

    public void setYstate(String ystate) {
        this.ystate = ystate;
    }

    public String getYprice() {
        return yprice;
    }

    public void setYprice(String yprice) {
        this.yprice = yprice;
    }

    @Override
    public String toString() {
        return "CompanyDetailsProduct{" +
                "id='" + id + '\'' +
                ", com_pic='" + com_pic + '\'' +
                ", label='" + label + '\'' +
                ", introduce='" + introduce + '\'' +
                ", part='" + part + '\'' +
                ", yname='" + yname + '\'' +
                ", ystate='" + ystate + '\'' +
                ", yprice='" + yprice + '\'' +
                '}';
    }
}
