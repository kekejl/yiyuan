package com.yiyuaninfo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaocongcong on 2017/8/6.
 */

public class Genius {


    /**
     * niu_arr : [{"id":"1","niu_name":"胡说","niu_introduce":" 小胡说的是八戒也不知道","niu_tag":"小虎 八戒","niu_img":"http://img.bimg.126.net/photo/ZWdvRTBDrymgJueo73kgpw==/449797012800471966.jpg","niu_pop":"112","niu_modtime":"11133111","niu_follow":"1231","niu_type":"0"},{"id":"3","niu_name":"往往无所","niu_introduce":"大碗大碗个","niu_tag":"往往 得到","niu_img":"http://img.bimg.126.net/photo/ZWdvRTBDrymgJueo73kgpw==/449797012800471966.jpg","niu_pop":"1232","niu_modtime":"112334312","niu_follow":"212","niu_type":"0"},{"id":"2","niu_name":"拜拜","niu_introduce":"阿呆桑丽卡我可没","niu_tag":"拜拜","niu_img":"http://img.bimg.126.net/photo/ZWdvRTBDrymgJueo73kgpw==/449797012800471966.jpg","niu_pop":"1234","niu_modtime":"32142222","niu_follow":"3332","niu_type":"0"}]
     * niuart_arr : [{"id":"6","niu_id":null,"title":"宝兰高铁通车运营 中国高铁实现","source":"壹元服务","author":"admin","keywords":"西藏 拉萨","description":"今天，从陕西宝鸡到甘肃兰州的宝兰高铁将正式通车运营。","picurl":"uploads/image/20170710/1499655302.png","posttime":"1499650846"},{"id":"5","niu_id":null,"title":"文章","source":"来源","author":"admin","keywords":"基金 文章QQ","description":"1111111111111","picurl":"uploads/image/20170707/1499420238.jpg","posttime":"1499410938"},{"id":"4","niu_id":null,"title":"还在等茅台涨到600元？价值派基金经理早卖了","source":"壹元服务","author":"admin","keywords":"哈哈 哈哈1","description":"　　价值蓝筹股近日连续两日回调，贵州茅台(600519,股吧)连续下跌，海康威视(002415,股吧)也开始逐步回调。价值股自去年以来一路高歌猛进，在一年多时间里不断上涨。如今蓝筹股龙头的回调究竟是上涨路上的一段\u201c小歇\u201d还是见顶信号?","picurl":"uploads/image/20170706/1499329116.jpg","posttime":"1499325486"},{"id":"3","niu_id":null,"title":"waihui","source":"","author":"admin","keywords":"","description":"","picurl":"","posttime":"1499319736"},{"id":"2","niu_id":null,"title":"债券","source":"百度","author":"admin","keywords":"","description":"","picurl":"","posttime":"1499319250"},{"id":"1","niu_id":null,"title":"王卫\u201c搞圈子\u201d 顺丰的BAT之路","source":"驿站","author":"admin","keywords":"","description":"","picurl":"uploads/image/20170706/1499309145.jpg","posttime":"1499306383"}]
     * lastid : 1
     */

    private String lastid;
    private List<NiuArrBean> niu_arr;
    private List<NiuartArrBean> niuart_arr;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<NiuArrBean> getNiu_arr() {
        return niu_arr;
    }

    public void setNiu_arr(List<NiuArrBean> niu_arr) {
        this.niu_arr = niu_arr;
    }

    public List<NiuartArrBean> getNiuart_arr() {
        return niuart_arr;
    }

    public void setNiuart_arr(List<NiuartArrBean> niuart_arr) {
        this.niuart_arr = niuart_arr;
    }

    public static class NiuArrBean   implements Serializable{
        /**
         * id : 1
         * niu_name : 胡说
         * niu_introduce :  小胡说的是八戒也不知道
         * niu_tag : 小虎 八戒
         * niu_img : http://img.bimg.126.net/photo/ZWdvRTBDrymgJueo73kgpw==/449797012800471966.jpg
         * niu_pop : 112
         * niu_modtime : 11133111
         * niu_follow : 1231
         * niu_type : 0
         */

        private String id;
        private String niu_name;
        private String niu_introduce;
        private String niu_tag;
        private String niu_img;
        private String niu_pop;
        private String niu_modtime;
        private String niu_follow;
        private String niu_type;

        public NiuArrBean(String id, String niu_name, String niu_introduce, String niu_tag, String niu_img, String niu_pop, String niu_modtime, String niu_follow, String niu_type) {
            this.id = id;
            this.niu_name = niu_name;
            this.niu_introduce = niu_introduce;
            this.niu_tag = niu_tag;
            this.niu_img = niu_img;
            this.niu_pop = niu_pop;
            this.niu_modtime = niu_modtime;
            this.niu_follow = niu_follow;
            this.niu_type = niu_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNiu_name() {
            return niu_name;
        }

        public void setNiu_name(String niu_name) {
            this.niu_name = niu_name;
        }

        public String getNiu_introduce() {
            return niu_introduce;
        }

        public void setNiu_introduce(String niu_introduce) {
            this.niu_introduce = niu_introduce;
        }

        public String getNiu_tag() {
            return niu_tag;
        }

        public void setNiu_tag(String niu_tag) {
            this.niu_tag = niu_tag;
        }

        public String getNiu_img() {
            return niu_img;
        }

        public void setNiu_img(String niu_img) {
            this.niu_img = niu_img;
        }

        public String getNiu_pop() {
            return niu_pop;
        }

        public void setNiu_pop(String niu_pop) {
            this.niu_pop = niu_pop;
        }

        public String getNiu_modtime() {
            return niu_modtime;
        }

        public void setNiu_modtime(String niu_modtime) {
            this.niu_modtime = niu_modtime;
        }

        public String getNiu_follow() {
            return niu_follow;
        }

        public void setNiu_follow(String niu_follow) {
            this.niu_follow = niu_follow;
        }

        public String getNiu_type() {
            return niu_type;
        }

        public void setNiu_type(String niu_type) {
            this.niu_type = niu_type;
        }

        @Override
        public String toString() {
            return "NiuArrBean{" +
                    "id='" + id + '\'' +
                    ", niu_name='" + niu_name + '\'' +
                    ", niu_introduce='" + niu_introduce + '\'' +
                    ", niu_tag='" + niu_tag + '\'' +
                    ", niu_img='" + niu_img + '\'' +
                    ", niu_pop='" + niu_pop + '\'' +
                    ", niu_modtime='" + niu_modtime + '\'' +
                    ", niu_follow='" + niu_follow + '\'' +
                    ", niu_type='" + niu_type + '\'' +
                    '}';
        }
    }

    public static class NiuartArrBean {
        /**
         * id : 6
         * niu_id : null
         * title : 宝兰高铁通车运营 中国高铁实现
         * source : 壹元服务
         * author : admin
         * keywords : 西藏 拉萨
         * description : 今天，从陕西宝鸡到甘肃兰州的宝兰高铁将正式通车运营。
         * picurl : uploads/image/20170710/1499655302.png
         * posttime : 1499650846
         */

        private String id;
        private String niu_id;
        private String title;
        private String source;
        private String author;
        private String keywords;
        private String description;
        private String picurl;
        private String posttime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNiu_id() {
            return niu_id;
        }

        public void setNiu_id(String niu_id) {
            this.niu_id = niu_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
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

        public String getPosttime() {
            return posttime;
        }

        public void setPosttime(String posttime) {
            this.posttime = posttime;
        }

        @Override
        public String toString() {
            return "NiuartArrBean{" +
                    "id='" + id + '\'' +
                    ", niu_id='" + niu_id + '\'' +
                    ", title='" + title + '\'' +
                    ", source='" + source + '\'' +
                    ", author='" + author + '\'' +
                    ", keywords='" + keywords + '\'' +
                    ", description='" + description + '\'' +
                    ", picurl='" + picurl + '\'' +
                    ", posttime='" + posttime + '\'' +
                    '}';
        }
    }
}
