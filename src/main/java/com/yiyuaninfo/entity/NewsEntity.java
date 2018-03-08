package com.yiyuaninfo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaocongcong on 2017/7/31.
 */

public class NewsEntity {


    /**
     * hangqing : [{"id":"17664","title":"美景美句","source":"美景美句","description":"美景美句","picurl":"","picarr":[{"img":"uploads/image/20170801/1501558248.jpg","description":"One day someone will walk into your life"},{"img":"uploads/image/20170801/1501563745.jpg","description":"We have all got our "},{"img":"uploads/image/20170801/1501560639.jpg","description":"You are at a wonderful stage of life.You have many wonderful stages of life yet to come"},{"img":"uploads/image/20170801/1501559476.jpg","description":"Loneliness is a beautiful thought"}],"posttime":"1501557122","keywords":"1个标签","picstate":"1"},{"id":"17618","title":"军方人士:中国应该会参加2018年\u201c环太军演\u201d","source":"壹元服务","description":"","picurl":"uploads/image/20170601/1496290845.jpg","picarr":[],"posttime":"2017-06-01 10:24:20","keywords":"1个标签","picstate":"1"},{"id":"17599","title":"4辆发射车瞒报丑闻影响\"萨德\"？文在寅:决定不变","source":"壹元服务","description":"","picurl":"uploads/image/20170601/1496292881.png","picarr":[],"posttime":"2017-06-01 10:11:14","keywords":"1个标签","picstate":"1"},{"id":"17519","title":"阿富汗首都爆炸至少90人死亡 地面留7米深坑","source":"壹元服务","description":"","picurl":"uploads/image/20170601/1496286193.jpg","picarr":[],"posttime":"2017-06-01 09:06:05","keywords":"1个标签","picstate":"1"},{"id":"17449","title":"中国电信原董事长常小兵受贿376万 一审获刑6年","source":"壹元服务","description":"","picurl":"uploads/image/20170531/1496227127.png","picarr":[],"posttime":"2017-05-31 16:01:15","keywords":"1个标签","picstate":"1"},{"id":"17385","title":"外媒：马克龙首次会见普京 开诚布公谈\"分歧\"","source":"壹元服务","description":"","picurl":"uploads/image/20170531/1496218387.jpg","picarr":[],"posttime":"2017-05-31 13:28:38","keywords":"1个标签","picstate":"1"},{"id":"17384","title":"\u201c通俄门\u201d事件发酵：弗林改口愿提供相关文件","source":"壹元服务","description":"","picurl":"uploads/image/20170531/1496216371.jpg","picarr":[],"posttime":"2017-05-31 13:26:43","keywords":"1个标签","picstate":"1"},{"id":"17381","title":"阿富汗首都使馆区附近发生爆炸 警方称致数人死伤","source":"壹元服务","description":"","picurl":"uploads/image/20170531/1496213714.jpg","picarr":[],"posttime":"2017-05-31 13:25:06","keywords":"1个标签","picstate":"1"},{"id":"17366","title":"上海出租车接受高考用车预约 仅四小时几乎订满","source":"壹元服务","description":"","picurl":"uploads/image/20170531/1496204245.jpg","picarr":[],"posttime":"2017-05-31 11:46:22","keywords":"1个标签","picstate":"1"},{"id":"17364","title":"云南大理州巍山县发生3.0级地震 震源深度8千米","source":"壹元服务","description":"","picurl":"uploads/image/20170531/1496204180.jpg","picarr":[],"posttime":"2017-05-31 11:43:49","keywords":"1个标签","picstate":"1"}]
     * rank_arr : [{"id":"9","self_name":"基金","self_link":"http://www.baidu.com","pop_value":"666"},{"id":"8","self_name":"基金","self_link":"http://www.baidu.com","pop_value":"444"},{"id":"7","self_name":"基金","self_link":"http://www.baidu.com","pop_value":"333"},{"id":"5","self_name":"基金","self_link":"http://www.baidu.com","pop_value":"321"},{"id":"6","self_name":"基金","self_link":"http://www.baidu.com","pop_value":"222"}]
     * lastid : [{lastid:17364}]
     */

    private String lastid;
    private List<HangqingBean> hangqing;
    private List<RankArrBean> rank_arr;

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

    public List<RankArrBean> getRank_arr() {
        return rank_arr;
    }

    public void setRank_arr(List<RankArrBean> rank_arr) {
        this.rank_arr = rank_arr;
    }

    public static class HangqingBean   implements Serializable {
        /**
         * id : 17664
         * title : 美景美句
         * source : 美景美句
         * description : 美景美句
         * picurl :
         * picarr : [{"img":"uploads/image/20170801/1501558248.jpg","description":"One day someone will walk into your life"},{"img":"uploads/image/20170801/1501563745.jpg","description":"We have all got our "},{"img":"uploads/image/20170801/1501560639.jpg","description":"You are at a wonderful stage of life.You have many wonderful stages of life yet to come"},{"img":"uploads/image/20170801/1501559476.jpg","description":"Loneliness is a beautiful thought"}]
         * posttime : 1501557122
         * keywords : 1个标签
         * picstate : 1
         */

        private String id;
        private String title;
        private String source;
        private String description;
        private String picurl;
        private List<PicarrBean> picarr;
        private String posttime;
        private String keywords;
        private String picstate;

        public HangqingBean(String id, String title, String source, String description, String picurl, List<PicarrBean> picarr, String posttime, String keywords, String picstate) {
            this.id = id;
            this.title = title;
            this.source = source;
            this.description = description;
            this.picurl = picurl;
            this.picarr = picarr;
            this.posttime = posttime;
            this.keywords = keywords;
            this.picstate = picstate;
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

        public List<PicarrBean> getPicarr() {
            return picarr;
        }

        public void setPicarr(List<PicarrBean> picarr) {
            this.picarr = picarr;
        }

    }

    public static class RankArrBean {
        /**
         * id : 9
         * self_name : 基金
         * self_link : http://www.baidu.com
         * pop_value : 666
         */

        private String id;
        private String self_name;
        private String self_link;
        private String pop_value;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSelf_name() {
            return self_name;
        }

        public void setSelf_name(String self_name) {
            this.self_name = self_name;
        }

        public String getSelf_link() {
            return self_link;
        }

        public void setSelf_link(String self_link) {
            this.self_link = self_link;
        }

        public String getPop_value() {
            return pop_value;
        }

        public void setPop_value(String pop_value) {
            this.pop_value = pop_value;
        }
    }
    }