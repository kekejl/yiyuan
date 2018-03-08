package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/7/12.
 */

public class HomeEntity {


    /**
     * roll_pic : [{"picurl":"http://yyapp.1yuaninfo.com/uploads/image/20170801/ding1.jpg","piclink":"http://www.baidu1.com"},{"picurl":"http://yyapp.1yuaninfo.com/uploads/image/20170801/ding2.jpg","piclink":"http://www.baidu2.com"},{"picurl":"http://yyapp.1yuaninfo.com/uploads/image/20170801/ding3.jpg","piclink":"http://www.baidu3.com"},{"picurl":"http://yyapp.1yuaninfo.com/uploads/image/20170801/ding1.jpg","piclink":"http://www.baidu4.com"}]
     * roll_words : [{"wid":"26","title":"字幕","addtime":"2017-09-15 10:07:57"},{"wid":"25","title":"哩哩啦啦","addtime":"2017-09-15 09:52:54"},{"wid":"20","title":"白糖需求会逐步好转","addtime":"2017-09-14 17:20:37"},{"wid":"19","title":"白糖需求会逐步好转","addtime":"2017-09-14 17:12:17"},{"wid":"18","title":"白糖需求会逐步好转","addtime":"2017-09-14 17:12:13"}]
     * sroll_pic : [{"picurl":"http://yyapp.1yuaninfo.com/uploads/image/20170801/1.jpg","piclink":"http://www.baidu5.com"},{"picurl":"http://yyapp.1yuaninfo.com/uploads/image/20170801/2.jpg","piclink":"http://www.baidu5.com"},{"picurl":"http://yyapp.1yuaninfo.com/uploads/image/20170801/3.jpg","piclink":"http://www.baidu5.com"},{"picurl":"http://yyapp.1yuaninfo.com/uploads/image/20170801/1.jpg","piclink":"http://www.baidu5.com"},{"picurl":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170914/1505377804.png","piclink":"http://baidu.com"}]
     * post_msg : {"id":"65","keyword1":"14","title":"获利提示","remark":"获利提示获利提示获利提示获利提示获利提示","addtime":"2017-09-13 15:58:36"}
     * zhishu : {"picurl":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170914/zhishu.png"}
     */

    private PostMsgBean post_msg;
    private ZhishuBean zhishu;
    private List<RollPicBean> roll_pic;
    private List<RollWordsBean> roll_words;
    private List<SrollPicBean> sroll_pic;

    public PostMsgBean getPost_msg() {
        return post_msg;
    }

    public void setPost_msg(PostMsgBean post_msg) {
        this.post_msg = post_msg;
    }

    public ZhishuBean getZhishu() {
        return zhishu;
    }

    public void setZhishu(ZhishuBean zhishu) {
        this.zhishu = zhishu;
    }

    public List<RollPicBean> getRoll_pic() {
        return roll_pic;
    }

    public void setRoll_pic(List<RollPicBean> roll_pic) {
        this.roll_pic = roll_pic;
    }

    public List<RollWordsBean> getRoll_words() {
        return roll_words;
    }

    public void setRoll_words(List<RollWordsBean> roll_words) {
        this.roll_words = roll_words;
    }

    public List<SrollPicBean> getSroll_pic() {
        return sroll_pic;
    }

    public void setSroll_pic(List<SrollPicBean> sroll_pic) {
        this.sroll_pic = sroll_pic;
    }

    public static class PostMsgBean {
        /**
         * id : 65
         * keyword1 : 14
         * title : 获利提示
         * remark : 获利提示获利提示获利提示获利提示获利提示
         * addtime : 2017-09-13 15:58:36
         */

        private String id;
        private String keyword1;
        private String title;
        private String remark;
        private String addtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeyword1() {
            return keyword1;
        }

        public void setKeyword1(String keyword1) {
            this.keyword1 = keyword1;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }

    public static class ZhishuBean {
        /**
         * picurl : http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170914/zhishu.png
         */

        private String picurl;

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }
    }

    public static class RollPicBean {
        /**
         * picurl : http://yyapp.1yuaninfo.com/uploads/image/20170801/ding1.jpg
         * piclink : http://www.baidu1.com
         */

        private String picurl;
        private String piclink;

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getPiclink() {
            return piclink;
        }

        public void setPiclink(String piclink) {
            this.piclink = piclink;
        }
    }

    public static class RollWordsBean {
        /**
         * wid : 26
         * title : 字幕
         * addtime : 2017-09-15 10:07:57
         */

        private String wid;
        private String title;
        private String addtime;

        public String getWid() {
            return wid;
        }

        public void setWid(String wid) {
            this.wid = wid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }

    public static class SrollPicBean {
        /**
         * picurl : http://yyapp.1yuaninfo.com/uploads/image/20170801/1.jpg
         * piclink : http://www.baidu5.com
         */

        private String picurl;
        private String piclink;

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getPiclink() {
            return piclink;
        }

        public void setPiclink(String piclink) {
            this.piclink = piclink;
        }
    }
}