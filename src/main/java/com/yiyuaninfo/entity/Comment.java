package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/20.
 */

public class Comment {


    private List<FirstCommentBean> first_comment;

    public List<FirstCommentBean> getFirst_comment() {
        return first_comment;
    }

    public void setFirst_comment(List<FirstCommentBean> first_comment) {
        this.first_comment = first_comment;
    }

//    public static class FirstCommentBean {
//        /**
//         * avatar : http://yyapp.1yuaninfo.com/app/application/userhead/1506759741diy7hy.jpg
//         * username : 15010422439
//         * userid : 1506759741diy7hy
//         * reply_msg : 非常今年
//         * create_date : 2017-10-09 15:38:08
//         * zan_count : 0
//         * zan_userid :
//         * commentid : 21
//         */
//
//        private String avatar;
//        private String username;
//        private String userid;
//        private String reply_msg;
//        private String create_date;
//        private String zan_count;
//        private String zan_userid;
//        private String commentid;
//
//
//        public FirstCommentBean(String avatar, String username, String userid, String reply_msg, String create_date, String zan_count, String zan_userid, String commentid) {
//            this.avatar = avatar;
//            this.username = username;
//            this.userid = userid;
//            this.reply_msg = reply_msg;
//            this.create_date = create_date;
//            this.zan_count = zan_count;
//            this.zan_userid = zan_userid;
//            this.commentid = commentid;
//        }
//
//        public String getAvatar() {
//            return avatar;
//        }
//
//        public void setAvatar(String avatar) {
//            this.avatar = avatar;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getUserid() {
//            return userid;
//        }
//
//        public void setUserid(String userid) {
//            this.userid = userid;
//        }
//
//        public String getReply_msg() {
//            return reply_msg;
//        }
//
//        public void setReply_msg(String reply_msg) {
//            this.reply_msg = reply_msg;
//        }
//
//        public String getCreate_date() {
//            return create_date;
//        }
//
//        public void setCreate_date(String create_date) {
//            this.create_date = create_date;
//        }
//
//        public String getZan_count() {
//            return zan_count;
//        }
//
//        public void setZan_count(String zan_count) {
//            this.zan_count = zan_count;
//        }
//
//        public String getZan_userid() {
//            return zan_userid;
//        }
//
//        public void setZan_userid(String zan_userid) {
//            this.zan_userid = zan_userid;
//        }
//
//        public String getCommentid() {
//            return commentid;
//        }
//
//        public void setCommentid(String commentid) {
//            this.commentid = commentid;
//        }
//    }
}