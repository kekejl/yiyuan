package com.yiyuaninfo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaocongcong on 2017/9/16.
 */

public class WenDa {


    /**
     * wd_arr : [{"niu_name":"方风雷","niu_img":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170801/fangfenglei.jpg","id":"4","title":"还在等茅台涨到600元？价值派基金经理早卖了","description":"价值蓝筹股近日连续两日回调，贵州茅台(600519,股吧)连续下跌，海康威视(002415,股吧)也开始逐步回调。价值股自去年以来一路高歌猛进，在一年多时间里不断上涨。如今蓝筹股龙头的回调究竟是上涨路上的一段\u201c小歇\u201d还是见顶信号?","picurl":"uploads/image/20170706/1499329116.jpg"},{"niu_name":"杨怀定","niu_img":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170801/yanghuaiding.jpg","id":"3","title":"总理力推的\u201c双创\u201d，学者：并非 权宜之策，而是转型大计","description":"","picurl":"uploads/image/20170222/1487749977.png"}]
     * lastid : 3
     */

    private String lastid;
    private List<WdArrBean> wd_arr;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<WdArrBean> getWd_arr() {
        return wd_arr;
    }

    public void setWd_arr(List<WdArrBean> wd_arr) {
        this.wd_arr = wd_arr;
    }

    public static class WdArrBean   implements Serializable {
        /**
         * niu_name : 方风雷
         * niu_img : http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170801/fangfenglei.jpg
         * id : 4
         * title : 还在等茅台涨到600元？价值派基金经理早卖了
         * description : 价值蓝筹股近日连续两日回调，贵州茅台(600519,股吧)连续下跌，海康威视(002415,股吧)也开始逐步回调。价值股自去年以来一路高歌猛进，在一年多时间里不断上涨。如今蓝筹股龙头的回调究竟是上涨路上的一段“小歇”还是见顶信号?
         * picurl : uploads/image/20170706/1499329116.jpg
         */

        private String niu_name;
        private String niu_img;
        private String id;
        private String title;
        private String description;
        private String picurl;

        public String getNiu_name() {
            return niu_name;
        }

        public void setNiu_name(String niu_name) {
            this.niu_name = niu_name;
        }

        public String getNiu_img() {
            return niu_img;
        }

        public void setNiu_img(String niu_img) {
            this.niu_img = niu_img;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }
    }
}
