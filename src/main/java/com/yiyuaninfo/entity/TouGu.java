package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/10.
 */

public class TouGu {

    /**
     * recommend : [{"id":"130","logo":"uploads/image/20170327/1490594125.jpg","gname":"重庆东金投资顾问有限公司","regmoney":"1000.00","auth_tag":"认证标签","part":"2","comtype":"投顾"},{"id":"129","logo":"uploads/image/20170327/1490589496.jpg","gname":"浙江同花顺投资咨询有限公司","regmoney":"500.00","auth_tag":"认证标签","part":"1","comtype":"投顾"},{"id":"128","logo":"uploads/image/20170327/1490590113.jpg","gname":"云南产业投资管理有限公司","regmoney":"4000.00","auth_tag":"认证标签","part":"3","comtype":"投顾"},{"id":"127","logo":"uploads/image/20170327/1490589129.jpg","gname":"天一星辰（北京）科技有限公司","regmoney":"1000.00","auth_tag":"认证标签","part":"2","comtype":"投顾"},{"id":"126","logo":"uploads/image/20170327/1490591946.jpg","gname":"天相投资顾问有限公司","regmoney":"9091.23","auth_tag":"认证标签","part":"2","comtype":"投顾"}]
     * other : [{"id":"76","logo":"uploads/image/20170327/1490589950.jpg","gname":"广州市万隆证券咨询顾问有限公司","regmoney":"5500.00","auth_tag":"认证标签","part":"2","comtype":"投顾"},{"id":"75","logo":"uploads/image/20170327/1490588366.jpg","gname":"广州经传多赢投资咨询有限公司","regmoney":"2500.00","auth_tag":"认证标签","part":"1","comtype":"投顾"},{"id":"74","logo":"uploads/image/20170327/1490583387.jpg","gname":"广州汇正财经顾问有限公司","regmoney":"100.00","auth_tag":"认证标签","part":"3","comtype":"投顾"},{"id":"73","logo":"uploads/image/20170327/1490583247.jpg","gname":"广州广证恒生证券研究所有限公司","regmoney":"4468.00","auth_tag":"认证标签","part":"2","comtype":"投顾"},{"id":"72","logo":"uploads/image/20170327/1490583125.jpg","gname":"广东科德投资顾问有限公司","regmoney":"1000.00","auth_tag":"认证标签","part":"1","comtype":"投顾"}]
     * roll_word : [{"id":"1","content":"我们只认准国家认证的84家正规机构","modtime":"2017-07-14"}]
     * lastid : 72
     */

    private String lastid;
    private List<FinderBean> recommend;
    private List<FinderBean> other;
    private List<RollWordBean> roll_word;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<FinderBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<FinderBean> recommend) {
        this.recommend = recommend;
    }

    public List<FinderBean> getOther() {
        return other;
    }

    public void setOther(List<FinderBean> other) {
        this.other = other;
    }

    public List<RollWordBean> getRoll_word() {
        return roll_word;
    }

    public void setRoll_word(List<RollWordBean> roll_word) {
        this.roll_word = roll_word;
    }


    public static class RollWordBean {
        /**
         * id : 1
         * content : 我们只认准国家认证的84家正规机构
         * modtime : 2017-07-14
         */

        private String id;
        private String content;
        private String modtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getModtime() {
            return modtime;
        }

        public void setModtime(String modtime) {
            this.modtime = modtime;
        }
    }
}
