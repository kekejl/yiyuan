package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/27.
 */

public class RankArr {

    /**
     * rank_arr : [{"id":"112","self_name":"环球网","self_link":"http://www.huanqiu.com/","pop_value":"797"},{"id":"120","self_name":"动点科技","self_link":"http://cn.technode.com/","pop_value":"758"},{"id":"113","self_name":"澎湃网","self_link":"http://www.huanqiu.com/","pop_value":"689"},{"id":"116","self_name":"站长之家","self_link":"http://www.chinaz.com/","pop_value":"585"},{"id":"118","self_name":"新浪网","self_link":"http://www.sina.com.cn/","pop_value":"374"}]
     * rank_lastid : 374
     */

    private String rank_lastid;
    private List<RankArrBean> rank_arr;

    public String getRank_lastid() {
        return rank_lastid;
    }

    public void setRank_lastid(String rank_lastid) {
        this.rank_lastid = rank_lastid;
    }

    public List<RankArrBean> getRank_arr() {
        return rank_arr;
    }

    public void setRank_arr(List<RankArrBean> rank_arr) {
        this.rank_arr = rank_arr;
    }

    public static class RankArrBean {
        /**
         * id : 112
         * self_name : 环球网
         * self_link : http://www.huanqiu.com/
         * pop_value : 797
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
