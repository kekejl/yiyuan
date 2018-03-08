package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/26.
 */

public class NiuArt {

    /**
     * niu_arr : [{"id":"15","niu_id":"3","title":"曾被视作第一例有望被治愈狂犬病患者家属：要求放弃治疗 ","source":"澎湃","author":"123","keywords":"","description":"狂犬病患者发病后，还有可能被治愈吗？ ","picurl":"https://img.yzcdn.cn/upload_files/2015/03/12/Fk78SKXBKGUYiMDNe7Fxu8G6yyO_.jpg?imageView2/2/w/730/h/0","posttime":"2017-09-23"},{"id":"16","niu_id":"3","title":"女生婚前是否应该拥有自己的窝？ ","source":"重庆商报","author":"123","keywords":"","description":"女生婚前是否应该拥有自己的窝？ ","picurl":"http://mpic.tiankong.com/cc3/ec5/cc3ec5c2e2d22120cab5cefc2e0e968b/640.jpg","posttime":"2017-09-19"},{"id":"2","niu_id":"3","title":"16省份报告H7N9病例 卫生计生委加强疫情防控","source":"百度","author":"admin","keywords":"","description":"","picurl":"uploads/image/20170706/1499319340.png","posttime":"2017-07-21 13:34:43"},{"id":"4","niu_id":"3","title":"还在等茅台涨到600元？价值派基金经理早卖了","source":"壹元服务","author":"admin","keywords":"哈哈 哈哈1","description":"价值蓝筹股近日连续两日回调，贵州茅台(600519,股吧)连续下跌，海康威视(002415,股吧)也开始逐步回调。价值股自去年以来一路高歌猛进，在一年多时间里不断上涨。如今蓝筹股龙头的回调究竟是上涨路上的一段\u201c小歇\u201d还是见顶信号?","picurl":"uploads/image/20170706/1499329116.jpg","posttime":"2017-07-21 13:34:43"},{"id":"6","niu_id":"3","title":"宝兰高铁通车运营 中国高铁实现","source":"壹元服务","author":"admin","keywords":"西藏 拉萨","description":"今天，从陕西宝鸡到甘肃兰州的宝兰高铁将正式通车运营。","picurl":"uploads/image/20170710/1499655302.png","posttime":"2017-07-21 13:34:43"}]
     * lastid : 2017-07-21 13:34:43
     */

    private String lastid;
    private List<NiuArrBean> niu_arr;

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

    public static class NiuArrBean {
        /**
         * id : 15
         * niu_id : 3
         * title : 曾被视作第一例有望被治愈狂犬病患者家属：要求放弃治疗
         * source : 澎湃
         * author : 123
         * keywords :
         * description : 狂犬病患者发病后，还有可能被治愈吗？
         * picurl : https://img.yzcdn.cn/upload_files/2015/03/12/Fk78SKXBKGUYiMDNe7Fxu8G6yyO_.jpg?imageView2/2/w/730/h/0
         * posttime : 2017-09-23
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
    }
}
