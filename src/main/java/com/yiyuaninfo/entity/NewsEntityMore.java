package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/7/31.
 */

public class NewsEntityMore {


    /**
     * hangqing : [{"id":"17839","title":"台媒:赖清德上任后面临三大难题 或令其走下神坛","source":"壹元服务","description":"","picurl":"uploads/image/20170906/1504693942.png","picarr":[],"posttime":"2017-09-06 17:37:22","keywords":"","picstate":"1"},{"id":"17724","title":"高善文：经济增速下行底部确认，股市已转入牛市","source":"壹元服务","description":"","picurl":"uploads/image/20170906/1504695116.jpg","picarr":[],"posttime":"2017-09-06 16:18:52","keywords":"","picstate":"1"},{"id":"17722","title":"央行启动ICO交易平台账户强监管 多家平台调整","source":"壹元服务","description":"","picurl":"uploads/image/20170906/1504688437.jpg","picarr":[],"posttime":"2017-09-06 16:17:59","keywords":"","picstate":"1"},{"id":"17721","title":"万达起诉造谣传谣者 分别索赔500万","source":"壹元服务","description":"","picurl":"uploads/image/20170906/1504689326.jpg","picarr":[],"posttime":"2017-09-06 16:12:31","keywords":"","picstate":"1"},{"id":"17720","title":"创业板成功站上1900点，创今年四月以来新高","source":"壹元服务","description":"","picurl":"uploads/image/20170906/1504686090.jpg","picarr":[],"posttime":"2017-09-06 16:10:33","keywords":"","picstate":"1"},{"id":"17714","title":"管涛：汇率企稳\u201c一石多鸟\u201d 未来汇率走势预期分化","source":"壹元服务","description":"","picurl":"uploads/image/20170906/1504686929.jpg","picarr":[],"posttime":"2017-09-06 15:59:02","keywords":"","picstate":"1"},{"id":"17711","title":"丁志杰：人民币汇率升值 降准释放流动性成可能选项","source":"壹元服务","description":"","picurl":"uploads/image/20170906/1504689568.jpg","picarr":[],"posttime":"2017-09-06 15:57:56","keywords":"","picstate":"1"},{"id":"17709","title":"孙宏斌微博发声力挺贾跃亭 贾仍为乐视网第一大股东","source":"壹元服务","description":"","picurl":"uploads/image/20170906/1504687057.jpg","picarr":[],"posttime":"2017-09-06 15:56:46","keywords":"","picstate":"1"},{"id":"17696","title":"保监会：外资保险公司总资产达10022亿 继续扩大对外开放","source":"壹元服务","description":"","picurl":"uploads/image/20170906/1504687129.jpg","picarr":[],"posttime":"2017-09-06 15:48:16","keywords":"","picstate":"1"},{"id":"17693","title":"360上市进程有望提速：拟借壳\u201c超车\u201d 多家上市公司参股","source":"壹元服务","description":"","picurl":"uploads/image/20170906/1504693679.jpg","picarr":[],"posttime":"2017-09-06 15:47:06","keywords":"","picstate":"1"}]
     * lastid : 17693
     */

    private String lastid;
    private List<HangqingBean> hangqing;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<HangqingBean> getHangqing() {
        return hangqing;
    }

    public void setHangqing(List<HangqingBean> hangqing) {
        this.hangqing = hangqing;
    }

    public static class HangqingBean {
        /**
         * id : 17839
         * title : 台媒:赖清德上任后面临三大难题 或令其走下神坛
         * source : 壹元服务
         * description :
         * picurl : uploads/image/20170906/1504693942.png
         * picarr : []
         * posttime : 2017-09-06 17:37:22
         * keywords :
         * picstate : 1
         */

        private String id;
        private String title;
        private String source;
        private String description;
        private String picurl;
        private String posttime;
        private String keywords;
        private String picstate;
        private List<?> picarr;

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

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getPicstate() {
            return picstate;
        }

        public void setPicstate(String picstate) {
            this.picstate = picstate;
        }

        public List<?> getPicarr() {
            return picarr;
        }

        public void setPicarr(List<?> picarr) {
            this.picarr = picarr;
        }
    }
}