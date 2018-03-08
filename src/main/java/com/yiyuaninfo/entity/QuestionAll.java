package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/10/18.
 */

public class QuestionAll {


    /**
     * info : [{"id":"","questionid":"","niuhead":"","content":"","niuid":"","posttime":"","qucotent":"治标不治本准备","qtime":"2017-10-18 14:53:00"},{"id":"","questionid":"","niuhead":"","content":"","niuid":"","posttime":"","qucotent":"vv哈哈","qtime":"17-10-18 08:55:21"}]
     * lastid : 42
     */

    private String lastid;
    private List<InfoBean> info;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id :
         * questionid :
         * niuhead :
         * content :
         * niuid :
         * posttime :
         * qucotent : 治标不治本准备
         * qtime : 2017-10-18 14:53:00
         */

        private String id;
        private String questionid;
        private String niuhead;
        private String content;
        private String niuid;
        private String posttime;
        private String qucotent;
        private String qtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuestionid() {
            return questionid;
        }

        public void setQuestionid(String questionid) {
            this.questionid = questionid;
        }

        public String getNiuhead() {
            return niuhead;
        }

        public void setNiuhead(String niuhead) {
            this.niuhead = niuhead;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNiuid() {
            return niuid;
        }

        public void setNiuid(String niuid) {
            this.niuid = niuid;
        }

        public String getPosttime() {
            return posttime;
        }

        public void setPosttime(String posttime) {
            this.posttime = posttime;
        }

        public String getQucotent() {
            return qucotent;
        }

        public void setQucotent(String qucotent) {
            this.qucotent = qucotent;
        }

        public String getQtime() {
            return qtime;
        }

        public void setQtime(String qtime) {
            this.qtime = qtime;
        }
    }
}
