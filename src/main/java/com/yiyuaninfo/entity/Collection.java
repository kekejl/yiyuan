package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/23.
 */

public class Collection {

    /**
     * usercollect : [{"id":"10","col_uid":"1499064765j6qavy","col_img":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170919/1505802437.jpg","col_title":" 美军摆放军靴 纪念两次撞船死亡士兵","col_id":"18546","col_type":"1","col_time":"2017-09-23 21:34:49","col_state":"0","col_deltime":null},{"id":"9","col_uid":"1499064765j6qavy","col_img":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170919/1505802437.jpg","col_title":" 美军摆放军靴 纪念两次撞船死亡士兵","col_id":"18546","col_type":"1","col_time":"2017-09-23 21:31:28","col_state":"0","col_deltime":null},{"id":"5","col_uid":"1499064765j6qavy","col_img":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170919/1505802437.jpg","col_title":"平衡车、双轮电动车招商代理加盟　","col_id":"16425","col_type":"5","col_time":"2017-09-12","col_state":"0","col_deltime":null},{"id":"4","col_uid":"1499064765j6qavy","col_img":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170919/1505802437.jpg","col_title":"监拍: 女童被公交车挤脚惨遭拖行","col_id":"1335","col_type":"4","col_time":"2017-09-13","col_state":"0","col_deltime":null},{"id":"3","col_uid":"1499064765j6qavy","col_img":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170919/1505802437.jpg","col_title":"宝兰高铁通车运营 中国高铁实现","col_id":"6","col_type":"3","col_time":"2017-09-12","col_state":"0","col_deltime":null},{"id":"2","col_uid":"1499064765j6qavy","col_img":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170919/1505802437.jpg","col_title":"e租宝案宣判- 涉案公司罚19亿 主犯丁宁获无期罚1亿人民币","col_id":"1344","col_type":"2","col_time":"2017-09-14","col_state":"0","col_deltime":null},{"id":"1","col_uid":"1499064765j6qavy","col_img":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170919/1505802437.jpg","col_title":"新加坡海域两船相撞事故：找到2名失踪船员遗体","col_id":"18588","col_type":"1","col_time":"2017-09-15","col_state":"0","col_deltime":""}]
     * lastid : 1
     */

    private String lastid;
    private List<UsercollectBean> usercollect;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<UsercollectBean> getUsercollect() {
        return usercollect;
    }

    public void setUsercollect(List<UsercollectBean> usercollect) {
        this.usercollect = usercollect;
    }

    public static class UsercollectBean {
        /**
         * id : 10
         * col_uid : 1499064765j6qavy
         * col_img : http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170919/1505802437.jpg
         * col_title :  美军摆放军靴 纪念两次撞船死亡士兵
         * col_id : 18546
         * col_type : 1
         * col_time : 2017-09-23 21:34:49
         * col_state : 0
         * col_deltime : null
         */

        private String id;
        private String col_uid;
        private String col_img;
        private String col_title;
        private String col_id;
        private String col_type;
        private String col_time;
        private String col_state;
        private String col_deltime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCol_uid() {
            return col_uid;
        }

        public void setCol_uid(String col_uid) {
            this.col_uid = col_uid;
        }

        public String getCol_img() {
            return col_img;
        }

        public void setCol_img(String col_img) {
            this.col_img = col_img;
        }

        public String getCol_title() {
            return col_title;
        }

        public void setCol_title(String col_title) {
            this.col_title = col_title;
        }

        public String getCol_id() {
            return col_id;
        }

        public void setCol_id(String col_id) {
            this.col_id = col_id;
        }

        public String getCol_type() {
            return col_type;
        }

        public void setCol_type(String col_type) {
            this.col_type = col_type;
        }

        public String getCol_time() {
            return col_time;
        }

        public void setCol_time(String col_time) {
            this.col_time = col_time;
        }

        public String getCol_state() {
            return col_state;
        }

        public void setCol_state(String col_state) {
            this.col_state = col_state;
        }

        public String getCol_deltime() {
            return col_deltime;
        }

        public void setCol_deltime(String col_deltime) {
            this.col_deltime = col_deltime;
        }
    }
}
