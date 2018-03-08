package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/20.
 */

public class CommentList {


    /**
     * first_comment : [{"avatar":"http://yyapp.1yuaninfo.com/app/application/userhead/1499064765j6qavy.jpg","username":"安卓用户","userid":"1499064765j6qavy","reply_msg":"顶顶顶起来!","create_date":"2017-07-31","zan_count":"123","commentid":"1"},{"avatar":"http://yyapp.1yuaninfo.com/app/application/userhead/1502883673g2pzyd.jpg","username":"IOS手机用户","userid":"1502883673g2pzyd","reply_msg":"ios用户的喝点","create_date":"2017-07-31","zan_count":"22","commentid":"2"}]
     * user_comment : [{"reply_msg":"顶顶顶起来!","zan_count":"123","create_date":"2017-07-31"}]
     * lastid : 22
     */

    private String lastid;
    private List<FirstCommentBean> first_comment;
    private List<UserCommentBean> user_comment;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<FirstCommentBean> getFirst_comment() {
        return first_comment;
    }

    public void setFirst_comment(List<FirstCommentBean> first_comment) {
        this.first_comment = first_comment;
    }

    public List<UserCommentBean> getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(List<UserCommentBean> user_comment) {
        this.user_comment = user_comment;
    }

//    public static class FirstCommentBean {
//
//
//
//        /**
//         * avatar : http://yyapp.1yuaninfo.com/app/application/userhead/1499064765j6qavy.jpg
//         * username : 安卓用户
//         * userid : 1499064765j6qavy
//         * reply_msg : 顶顶顶起来!
//         * create_date : 2017-07-31
//         * zan_count : 123
//         * commentid : 1
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
//        private String flag;
//
//        public FirstCommentBean(String avatar, String username, String userid, String reply_msg, String create_date, String zan_count, String zan_userid, String commentid, String flag) {
//            this.avatar = avatar;
//            this.username = username;
//            this.userid = userid;
//            this.reply_msg = reply_msg;
//            this.create_date = create_date;
//            this.zan_count = zan_count;
//            this.zan_userid = zan_userid;
//            this.commentid = commentid;
//            this.flag = flag;
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
//
//        public String getFlag() {
//            return flag;
//        }
//
//        public void setFlag(String flag) {
//            this.flag = flag;
//        }
//
//        @Override
//        public String toString() {
//            return "FirstCommentBean{" +
//                    "avatar='" + avatar + '\'' +
//                    ", username='" + username + '\'' +
//                    ", userid='" + userid + '\'' +
//                    ", reply_msg='" + reply_msg + '\'' +
//                    ", create_date='" + create_date + '\'' +
//                    ", zan_count='" + zan_count + '\'' +
//                    ", zan_userid='" + zan_userid + '\'' +
//                    ", commentid='" + commentid + '\'' +
//                    ", flag='" + flag + '\'' +
//                    '}';
//        }
//    }

    public static class UserCommentBean {
        /**
         * reply_msg : 顶顶顶起来!
         * zan_count : 123
         * create_date : 2017-07-31
         */

        private String reply_msg;
        private String zan_count;
        private String create_date;

        public String getReply_msg() {
            return reply_msg;
        }

        public void setReply_msg(String reply_msg) {
            this.reply_msg = reply_msg;
        }

        public String getZan_count() {
            return zan_count;
        }

        public void setZan_count(String zan_count) {
            this.zan_count = zan_count;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        @Override
        public String toString() {
            return "UserCommentBean{" +
                    "reply_msg='" + reply_msg + '\'' +
                    ", zan_count='" + zan_count + '\'' +
                    ", create_date='" + create_date + '\'' +
                    '}';
        }
    }
}
