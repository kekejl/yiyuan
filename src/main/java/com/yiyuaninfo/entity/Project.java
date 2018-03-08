package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/8.
 */

public class Project {

    /**
     * recommend : [{"id":"16114","classid":"41","title":"课外喵网络教育加盟代理_课外喵加盟条件费用　","description":"","picurl":"uploads/image/20170523/1495526721.png","label":"2","auth_tag":"自定义标签","hits":"1842"},{"id":"15932","classid":"40","title":"欧洲最鲜为人知的神秘小镇 你想去哪个？","description":"","picurl":"uploads/image/20170522/1495446004.png","label":"2","auth_tag":"自定义标签","hits":"2565"},{"id":"15922","classid":"40","title":"荷兰风车故事 是一首随风起舞的乡村民谣","description":"","picurl":"uploads/image/20170522/1495447765.png","label":"2","auth_tag":"自定义标签","hits":"7529"},{"id":"15912","classid":"40","title":"细数全世界最美小镇,只看一眼已心驰神往","description":"","picurl":"uploads/image/20170522/1495445411.png","label":"3","auth_tag":"自定义标签","hits":"2701"},{"id":"15872","classid":"38","title":"永定河孔雀城莱茵河谷凡尔赛花园均价23000元/平","description":"","picurl":"uploads/image/20170522/1495449405.png","label":"2","auth_tag":"自定义标签","hits":"1763"},{"id":"15810","classid":"38","title":"领秀·雁栖翡翠花园","description":"","picurl":"uploads/image/20170522/1495437933.png","label":"3","auth_tag":"自定义标签","hits":"6757"},{"id":"15776","classid":"38","title":"首开保利·熙悦诚郡","description":"","picurl":"uploads/image/20170522/1495439668.jpg","label":"2","auth_tag":"自定义标签","hits":"1874"},{"id":"14848","classid":"44","title":"车睿仕汽车美容加盟招商　　","description":"","picurl":"uploads/image/20170517/1495015740.jpg","label":"2","auth_tag":"自定义标签","hits":"1519"},{"id":"14842","classid":"44","title":"艾斯卡汽车清凉罩加盟","description":"","picurl":"uploads/image/20170517/1495017192.jpg","label":"2","auth_tag":"自定义标签","hits":"1261"},{"id":"14782","classid":"43","title":"乐赢乐高科技中心加盟　　","description":"","picurl":"uploads/image/20170517/1495004463.png","label":"2","auth_tag":"自定义标签","hits":"1500"}]
     * lastid : 14782
     */

    private String lastid;
    private List<RecommendBean> recommend;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public static class RecommendBean {
        /**
         * id : 16114
         * classid : 41
         * title : 课外喵网络教育加盟代理_课外喵加盟条件费用　
         * description :
         * picurl : uploads/image/20170523/1495526721.png
         * label : 2
         * auth_tag : 自定义标签
         * hits : 1842
         */

        private String id;
        private String classid;
        private String title;
        private String description;
        private String picurl;
        private String label;
        private String auth_tag;
        private String hits;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getClassid() {
            return classid;
        }

        public void setClassid(String classid) {
            this.classid = classid;
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

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getAuth_tag() {
            return auth_tag;
        }

        public void setAuth_tag(String auth_tag) {
            this.auth_tag = auth_tag;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        @Override
        public String toString() {
            return "RecommendBean{" +
                    "id='" + id + '\'' +
                    ", classid='" + classid + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", picurl='" + picurl + '\'' +
                    ", label='" + label + '\'' +
                    ", auth_tag='" + auth_tag + '\'' +
                    ", hits='" + hits + '\'' +
                    '}';
        }
    }
}
