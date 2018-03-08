package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/8/10.
 */

public class FinderBean {


    /**
     * id : 76
     * logo : uploads/image/20170327/1490589950.jpg
     * gname : 广州市万隆证券咨询顾问有限公司
     * regmoney : 5500.00
     * auth_tag : 认证标签
     * part : 2
     * comtype : 投顾
     */

    private String id;
    private String logo;
    private String gname;
    private String regmoney;
    private String auth_tag;
    private String part;
    private String comtype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getRegmoney() {
        return regmoney;
    }

    public void setRegmoney(String regmoney) {
        this.regmoney = regmoney;
    }

    public String getAuth_tag() {
        return auth_tag;
    }

    public void setAuth_tag(String auth_tag) {
        this.auth_tag = auth_tag;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getComtype() {
        return comtype;
    }

    public void setComtype(String comtype) {
        this.comtype = comtype;
    }

    @Override
    public String toString() {
        return "FinderBean{" +
                "id='" + id + '\'' +
                ", logo='" + logo + '\'' +
                ", gname='" + gname + '\'' +
                ", regmoney='" + regmoney + '\'' +
                ", auth_tag='" + auth_tag + '\'' +
                ", part='" + part + '\'' +
                ", comtype='" + comtype + '\'' +
                '}';
    }
}
