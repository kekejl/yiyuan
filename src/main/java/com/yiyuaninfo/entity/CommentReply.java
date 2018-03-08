package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/20.
 */

public class CommentReply {


    /**
     * reply_comment : [{"id":"3","comment_id":"27","from_user_id":"1507529879iwlkxj","from_user_avatar":"user_avatar","to_user_name":"13718040895","to_user_id":"1507529879iwlkxj","reply_msg":"不对不对v","zan_count":"0","create_date":"2017-10-19 13:47:00","from_user_name":"13718040895","flag":"1"},{"id":"1","comment_id":"27","from_user_id":"1507529879iwlkxj","from_user_avatar":"user_avatar","to_user_name":"13718040895","to_user_id":"1507529879iwlkxj","reply_msg":"还不到不错吧","zan_count":"0","create_date":"2017-10-19 13:46:39","from_user_name":"13718040895","flag":"1"}]
     * lastid : 1
     */

    private String lastid;
    private List<ReplyCommentBean> reply_comment;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<ReplyCommentBean> getReply_comment() {
        return reply_comment;
    }

    public void setReply_comment(List<ReplyCommentBean> reply_comment) {
        this.reply_comment = reply_comment;
    }

    public static class ReplyCommentBean {
        /**
         * id : 3
         * comment_id : 27
         * from_user_id : 1507529879iwlkxj
         * from_user_avatar : user_avatar
         * to_user_name : 13718040895
         * to_user_id : 1507529879iwlkxj
         * reply_msg : 不对不对v
         * zan_count : 0
         * create_date : 2017-10-19 13:47:00
         * from_user_name : 13718040895
         * flag : 1
         */

        private String id;
        private String comment_id;
        private String from_user_id;
        private String from_user_avatar;
        private String to_user_name;
        private String to_user_id;
        private String reply_msg;
        private String zan_count;
        private String create_date;
        private String from_user_name;
        private String flag;


        public ReplyCommentBean(String id, String comment_id, String from_user_id, String from_user_avatar, String to_user_name, String to_user_id, String reply_msg, String zan_count, String create_date, String from_user_name, String flag) {
            this.id = id;
            this.comment_id = comment_id;
            this.from_user_id = from_user_id;
            this.from_user_avatar = from_user_avatar;
            this.to_user_name = to_user_name;
            this.to_user_id = to_user_id;
            this.reply_msg = reply_msg;
            this.zan_count = zan_count;
            this.create_date = create_date;
            this.from_user_name = from_user_name;
            this.flag = flag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getFrom_user_id() {
            return from_user_id;
        }

        public void setFrom_user_id(String from_user_id) {
            this.from_user_id = from_user_id;
        }

        public String getFrom_user_avatar() {
            return from_user_avatar;
        }

        public void setFrom_user_avatar(String from_user_avatar) {
            this.from_user_avatar = from_user_avatar;
        }

        public String getTo_user_name() {
            return to_user_name;
        }

        public void setTo_user_name(String to_user_name) {
            this.to_user_name = to_user_name;
        }

        public String getTo_user_id() {
            return to_user_id;
        }

        public void setTo_user_id(String to_user_id) {
            this.to_user_id = to_user_id;
        }

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

        public String getFrom_user_name() {
            return from_user_name;
        }

        public void setFrom_user_name(String from_user_name) {
            this.from_user_name = from_user_name;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        @Override
        public String
        toString() {
            return "ReplyCommentBean{" +
                    "id='" + id + '\'' +
                    ", comment_id='" + comment_id + '\'' +
                    ", from_user_id='" + from_user_id + '\'' +
                    ", from_user_avatar='" + from_user_avatar + '\'' +
                    ", to_user_name='" + to_user_name + '\'' +
                    ", to_user_id='" + to_user_id + '\'' +
                    ", reply_msg='" + reply_msg + '\'' +
                    ", zan_count='" + zan_count + '\'' +
                    ", create_date='" + create_date + '\'' +
                    ", from_user_name='" + from_user_name + '\'' +
                    ", flag='" + flag + '\'' +
                    '}';
        }
    }

}