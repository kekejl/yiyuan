package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/9/11.
 */

public class Mall {
    /**
     * id : 1
     * pro_name : 努比亚nubia Z17
     * pro_picture : http://img14.360buyimg.com/n1/s450x450_jfs/t6001/278/1127057487/180744/9690a23a/592ffa7fN79cda7de.jpg
     * pro_category : 热门
     * pro_state : 上架
     * pro_label : 热
     * pro_originalprice : 2799
     * pro_presentprice : 2099
     * pro_stock : 212
     * pro_introduce :
     商品名称：努比亚 Z17
     商品编号：4220709
     商品毛重：370.00g
     商品产地：中国大陆
     系统：安卓（Android）
     购买方式：其他
     电池容量：3000mAh-3999mAh
     机身颜色：黑色
     热点：骁龙芯片，双卡双待，快速充电，指纹识别，Type-C，拍照神器，支持NFC，三防手机，后置双摄像头
     运行内存：6GB
     前置摄像头像素：1600万及以上
     后置摄像头像素：2000万及以上
     机身内存：64GB

     * pro_process : 本产品全国联保，享受三包服务，质保期为：一年质保
     如因质量问题或故障，凭厂商维修中心或特约维修点的质量检测证明，享受7日内退货，15日内换货，15日以上在质保期内享受免费保修等三包服务！
     (注:如厂家在商品介绍中有售后保障的说明,则此商品按照厂家说明执行售后保障服务。)
     * pro_notice : 注：因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、产地、附件说明完全一致。只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若本商城没有及时更新，请大家谅解！
     * pro_midtime : 2017-07-21
     */

    private String id;
    private String pro_name;
    private String pro_picture;
    private String pro_category;
    private String pro_state;
    private String pro_label;
    private String pro_originalprice;
    private String pro_presentprice;
    private String pro_stock;
    private String pro_introduce;
    private String pro_process;
    private String pro_notice;
    private String pro_midtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_picture() {
        return pro_picture;
    }

    public void setPro_picture(String pro_picture) {
        this.pro_picture = pro_picture;
    }

    public String getPro_category() {
        return pro_category;
    }

    public void setPro_category(String pro_category) {
        this.pro_category = pro_category;
    }

    public String getPro_state() {
        return pro_state;
    }

    public void setPro_state(String pro_state) {
        this.pro_state = pro_state;
    }

    public String getPro_label() {
        return pro_label;
    }

    public void setPro_label(String pro_label) {
        this.pro_label = pro_label;
    }

    public String getPro_originalprice() {
        return pro_originalprice;
    }

    public void setPro_originalprice(String pro_originalprice) {
        this.pro_originalprice = pro_originalprice;
    }

    public String getPro_presentprice() {
        return pro_presentprice;
    }

    public void setPro_presentprice(String pro_presentprice) {
        this.pro_presentprice = pro_presentprice;
    }

    public String getPro_stock() {
        return pro_stock;
    }

    public void setPro_stock(String pro_stock) {
        this.pro_stock = pro_stock;
    }

    public String getPro_introduce() {
        return pro_introduce;
    }

    public void setPro_introduce(String pro_introduce) {
        this.pro_introduce = pro_introduce;
    }

    public String getPro_process() {
        return pro_process;
    }

    public void setPro_process(String pro_process) {
        this.pro_process = pro_process;
    }

    public String getPro_notice() {
        return pro_notice;
    }

    public void setPro_notice(String pro_notice) {
        this.pro_notice = pro_notice;
    }

    public String getPro_midtime() {
        return pro_midtime;
    }

    public void setPro_midtime(String pro_midtime) {
        this.pro_midtime = pro_midtime;
    }

    @Override
    public String toString() {
        return "Mall{" +
                "id='" + id + '\'' +
                ", pro_name='" + pro_name + '\'' +
                ", pro_picture='" + pro_picture + '\'' +
                ", pro_category='" + pro_category + '\'' +
                ", pro_state='" + pro_state + '\'' +
                ", pro_label='" + pro_label + '\'' +
                ", pro_originalprice='" + pro_originalprice + '\'' +
                ", pro_presentprice='" + pro_presentprice + '\'' +
                ", pro_stock='" + pro_stock + '\'' +
                ", pro_introduce='" + pro_introduce + '\'' +
                ", pro_process='" + pro_process + '\'' +
                ", pro_notice='" + pro_notice + '\'' +
                ", pro_midtime='" + pro_midtime + '\'' +
                '}';
    }
}