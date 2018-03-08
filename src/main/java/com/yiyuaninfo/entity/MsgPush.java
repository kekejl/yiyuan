package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/12.
 */

public class MsgPush {

    private List<MsglistArrBean> msglist_arr;

    public List<MsglistArrBean> getMsglist_arr() {
        return msglist_arr;
    }

    public void setMsglist_arr(List<MsglistArrBean> msglist_arr) {
        this.msglist_arr = msglist_arr;
    }

    public static class MsglistArrBean {
        /**
         * id : 1
           * push_activity : com.yiyuaninfo.Activity.MyActivity.SettingActivity
         * news_activty : com.yiyuaninfo.Activity.UmengInfoActivity
         * push_ticker : ticker
         * push_content : 简介
         * push_type : 2
         * sub_title : ios10副标题
         * main_title : ios10主标题
         * title : app标题
         * url :
         * keyword1 : 5
         * keyword2 : null
         * remark : 早上好
         * fxtime : 2017-08-24
         * dmname : 002615
         * sellremark :
         * fxcontent : 早上的分享理由
         * Section :
         * fxmoney :
         * zsmoney :
         * mention :
         * article :
         * status : 2
         * creator : admin
         * auditor : admin
         * addtime : 2017-08-2410:56:51
         * checktime : 2017-08-2410:59:17
         * typeid : 0
         * personlimit : 0
         * havesend : 0
         * timelimit : 0
         * timeout :
         * newstype : 0
         * sellmoney :
         * sellcontent :
         * getmention :
         * sellcreator :
         * selltime :
         * sellauditor :
         * sellchecktime :
         * sellstatus : 0
         * isgain : 0
         * delstate :
         * deltime : 0
         */

        private String id;
        private String push_activity;
        private String news_activty;
        private String push_ticker;
        private String push_content;
        private String push_type;
        private String sub_title;
        private String main_title;
        private String title;
        private String url;
        private String keyword1;
        private Object keyword2;
        private String remark;
        private String fxtime;
        private String dmname;
        private String sellremark;
        private String fxcontent;
        private String Section;
        private String fxmoney;
        private String zsmoney;
        private String mention;
        private String article;
        private String status;
        private String creator;
        private String auditor;
        private String addtime;
        private String checktime;
        private String typeid;
        private String personlimit;
        private String havesend;
        private String timelimit;
        private String timeout;
        private String newstype;
        private String sellmoney;
        private String sellcontent;
        private String getmention;
        private String sellcreator;
        private String selltime;
        private String sellauditor;
        private String sellchecktime;
        private String sellstatus;
        private String isgain;
        private String delstate;
        private String deltime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPush_activity() {
            return push_activity;
        }

        public void setPush_activity(String push_activity) {
            this.push_activity = push_activity;
        }

        public String getNews_activty() {
            return news_activty;
        }

        public void setNews_activty(String news_activty) {
            this.news_activty = news_activty;
        }

        public String getPush_ticker() {
            return push_ticker;
        }

        public void setPush_ticker(String push_ticker) {
            this.push_ticker = push_ticker;
        }

        public String getPush_content() {
            return push_content;
        }

        public void setPush_content(String push_content) {
            this.push_content = push_content;
        }

        public String getPush_type() {
            return push_type;
        }

        public void setPush_type(String push_type) {
            this.push_type = push_type;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getMain_title() {
            return main_title;
        }

        public void setMain_title(String main_title) {
            this.main_title = main_title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getKeyword1() {
            return keyword1;
        }

        public void setKeyword1(String keyword1) {
            this.keyword1 = keyword1;
        }

        public Object getKeyword2() {
            return keyword2;
        }

        public void setKeyword2(Object keyword2) {
            this.keyword2 = keyword2;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getFxtime() {
            return fxtime;
        }

        public void setFxtime(String fxtime) {
            this.fxtime = fxtime;
        }

        public String getDmname() {
            return dmname;
        }

        public void setDmname(String dmname) {
            this.dmname = dmname;
        }

        public String getSellremark() {
            return sellremark;
        }

        public void setSellremark(String sellremark) {
            this.sellremark = sellremark;
        }

        public String getFxcontent() {
            return fxcontent;
        }

        public void setFxcontent(String fxcontent) {
            this.fxcontent = fxcontent;
        }

        public String getSection() {
            return Section;
        }

        public void setSection(String Section) {
            this.Section = Section;
        }

        public String getFxmoney() {
            return fxmoney;
        }

        public void setFxmoney(String fxmoney) {
            this.fxmoney = fxmoney;
        }

        public String getZsmoney() {
            return zsmoney;
        }

        public void setZsmoney(String zsmoney) {
            this.zsmoney = zsmoney;
        }

        public String getMention() {
            return mention;
        }

        public void setMention(String mention) {
            this.mention = mention;
        }

        public String getArticle() {
            return article;
        }

        public void setArticle(String article) {
            this.article = article;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getAuditor() {
            return auditor;
        }

        public void setAuditor(String auditor) {
            this.auditor = auditor;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getChecktime() {
            return checktime;
        }

        public void setChecktime(String checktime) {
            this.checktime = checktime;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getPersonlimit() {
            return personlimit;
        }

        public void setPersonlimit(String personlimit) {
            this.personlimit = personlimit;
        }

        public String getHavesend() {
            return havesend;
        }

        public void setHavesend(String havesend) {
            this.havesend = havesend;
        }

        public String getTimelimit() {
            return timelimit;
        }

        public void setTimelimit(String timelimit) {
            this.timelimit = timelimit;
        }

        public String getTimeout() {
            return timeout;
        }

        public void setTimeout(String timeout) {
            this.timeout = timeout;
        }

        public String getNewstype() {
            return newstype;
        }

        public void setNewstype(String newstype) {
            this.newstype = newstype;
        }

        public String getSellmoney() {
            return sellmoney;
        }

        public void setSellmoney(String sellmoney) {
            this.sellmoney = sellmoney;
        }

        public String getSellcontent() {
            return sellcontent;
        }

        public void setSellcontent(String sellcontent) {
            this.sellcontent = sellcontent;
        }

        public String getGetmention() {
            return getmention;
        }

        public void setGetmention(String getmention) {
            this.getmention = getmention;
        }

        public String getSellcreator() {
            return sellcreator;
        }

        public void setSellcreator(String sellcreator) {
            this.sellcreator = sellcreator;
        }

        public String getSelltime() {
            return selltime;
        }

        public void setSelltime(String selltime) {
            this.selltime = selltime;
        }

        public String getSellauditor() {
            return sellauditor;
        }

        public void setSellauditor(String sellauditor) {
            this.sellauditor = sellauditor;
        }

        public String getSellchecktime() {
            return sellchecktime;
        }

        public void setSellchecktime(String sellchecktime) {
            this.sellchecktime = sellchecktime;
        }

        public String getSellstatus() {
            return sellstatus;
        }

        public void setSellstatus(String sellstatus) {
            this.sellstatus = sellstatus;
        }

        public String getIsgain() {
            return isgain;
        }

        public void setIsgain(String isgain) {
            this.isgain = isgain;
        }

        public String getDelstate() {
            return delstate;
        }

        public void setDelstate(String delstate) {
            this.delstate = delstate;
        }

        public String getDeltime() {
            return deltime;
        }

        public void setDeltime(String deltime) {
            this.deltime = deltime;
        }

        @Override
        public String toString() {
            return "MsglistArrBean{" +
                    "id='" + id + '\'' +
                    ", push_activity='" + push_activity + '\'' +
                    ", news_activty='" + news_activty + '\'' +
                    ", push_ticker='" + push_ticker + '\'' +
                    ", push_content='" + push_content + '\'' +
                    ", push_type='" + push_type + '\'' +
                    ", sub_title='" + sub_title + '\'' +
                    ", main_title='" + main_title + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", keyword1='" + keyword1 + '\'' +
                    ", keyword2=" + keyword2 +
                    ", remark='" + remark + '\'' +
                    ", fxtime='" + fxtime + '\'' +
                    ", dmname='" + dmname + '\'' +
                    ", sellremark='" + sellremark + '\'' +
                    ", fxcontent='" + fxcontent + '\'' +
                    ", Section='" + Section + '\'' +
                    ", fxmoney='" + fxmoney + '\'' +
                    ", zsmoney='" + zsmoney + '\'' +
                    ", mention='" + mention + '\'' +
                    ", article='" + article + '\'' +
                    ", status='" + status + '\'' +
                    ", creator='" + creator + '\'' +
                    ", auditor='" + auditor + '\'' +
                    ", addtime='" + addtime + '\'' +
                    ", checktime='" + checktime + '\'' +
                    ", typeid='" + typeid + '\'' +
                    ", personlimit='" + personlimit + '\'' +
                    ", havesend='" + havesend + '\'' +
                    ", timelimit='" + timelimit + '\'' +
                    ", timeout='" + timeout + '\'' +
                    ", newstype='" + newstype + '\'' +
                    ", sellmoney='" + sellmoney + '\'' +
                    ", sellcontent='" + sellcontent + '\'' +
                    ", getmention='" + getmention + '\'' +
                    ", sellcreator='" + sellcreator + '\'' +
                    ", selltime='" + selltime + '\'' +
                    ", sellauditor='" + sellauditor + '\'' +
                    ", sellchecktime='" + sellchecktime + '\'' +
                    ", sellstatus='" + sellstatus + '\'' +
                    ", isgain='" + isgain + '\'' +
                    ", delstate='" + delstate + '\'' +
                    ", deltime='" + deltime + '\'' +
                    '}';
        }
    }
}
