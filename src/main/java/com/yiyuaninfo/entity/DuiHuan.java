package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/19.
 */

public class DuiHuan {


    /**
     * inout : [{"id":"12","userid":"1499064765j6qavy","type":"1","amount":"300","goodtitle":"西门子冰箱","goodimg":"uploads/image/20170911/ximenzi.jpg","explain":"积分商城兑换电视机","addtime":"2017-09-10 23:30:22"},{"id":"8","userid":"1499064765j6qavy","type":"1","amount":"300","goodtitle":"西门子冰箱","goodimg":"uploads/image/20170911/ximenzi.jpg","explain":"积分商城兑换电视机","addtime":"2017-09-01 22:09:38"},{"id":"9","userid":"1499064765j6qavy","type":"1","amount":"300","goodtitle":"西门子冰箱","goodimg":"uploads/image/20170911/ximenzi.jpg","explain":"积分商城兑换电视机","addtime":"2017-06-03 03:03:23"},{"id":"11","userid":"1499064765j6qavy","type":"1","amount":"300","goodtitle":"西门子冰箱","goodimg":"uploads/image/20170911/ximenzi.jpg","explain":"积分商城兑换电视机","addtime":"2017-04-23 22:29:22"},{"id":"10","userid":"1499064765j6qavy","type":"1","amount":"300","goodtitle":"西门子冰箱","goodimg":"uploads/image/20170911/ximenzi.jpg","explain":"积分商城兑换电视机","addtime":"2017-03-23 14:49:44"},{"id":"7","userid":"1499064765j6qavy","type":"1","amount":"200","goodtitle":"ip8","goodimg":"uploads/image/20170911/ip.jpg","explain":"积分商城兑换手机","addtime":"2017-03-09 23:33:43"},{"id":"4","userid":"1499064765j6qavy","type":"1","amount":"300","goodtitle":"西门子冰箱","goodimg":"uploads/image/20170911/ximenzi.jpg","explain":"积分商城兑换电视机","addtime":"2017-02-11 03:33:21"},{"id":"5","userid":"1499064765j6qavy","type":"1","amount":"200","goodtitle":"ip8","goodimg":"uploads/image/20170911/ip.jpg","explain":"积分商城兑换手机","addtime":"2017-01-11 03:33:21"},{"id":"2","userid":"1499064765j6qavy","type":"1","amount":"200","goodtitle":"ip8","goodimg":"uploads/image/20170911/ip.jpg","explain":"积分商城兑换手机","addtime":"2016-09-11 03:33:21"},{"id":"6","userid":"1499064765j6qavy","type":"1","amount":"300","goodtitle":"西门子冰箱","goodimg":"uploads/image/20170911/ximenzi.jpg","explain":"积分商城兑换电视机","addtime":"2015-09-11 12:32:23"}]
     * lastid : 6
     */

    private String lastid;
    private List<InoutBean> inout;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<InoutBean> getInout() {
        return inout;
    }

    public void setInout(List<InoutBean> inout) {
        this.inout = inout;
    }

    public static class InoutBean {
        /**
         * id : 12
         * userid : 1499064765j6qavy
         * type : 1
         * amount : 300
         * goodtitle : 西门子冰箱
         * goodimg : uploads/image/20170911/ximenzi.jpg
         * explain : 积分商城兑换电视机
         * addtime : 2017-09-10 23:30:22
         */

        private String id;
        private String userid;
        private String type;
        private String amount;
        private String goodtitle;
        private String goodimg;
        private String explain;
        private String addtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getGoodtitle() {
            return goodtitle;
        }

        public void setGoodtitle(String goodtitle) {
            this.goodtitle = goodtitle;
        }

        public String getGoodimg() {
            return goodimg;
        }

        public void setGoodimg(String goodimg) {
            this.goodimg = goodimg;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
