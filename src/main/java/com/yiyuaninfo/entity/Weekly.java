package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/23.
 */

public class Weekly {

    /**
     * info : [{"id":"58","title":"《壹元资讯服务》周刊NO.36","brief":"2017年9月/第1周\u201c365\u201d会员专属总结","time":"2017-09-08 09:20:00"},{"id":"57","title":"《壹元资讯服务》周刊NO.35","brief":"2017年8月/第5周\u201c365\u201d会员专属总结","time":"2017-08-31 10:25:00"},{"id":"56","title":"《壹元资讯服务》周刊NO.34","brief":"2017年8月/第4周\u201c365\u201d会员专属总结","time":"2017-08-25 21:26:00"},{"id":"55","title":"《壹元资讯服务》周刊NO.33","brief":"2017年8月/第3周\u201c365\u201d会员专属总结","time":"2017-08-18 22:48:00"},{"id":"53","title":"《壹元资讯服务》周刊NO.32","brief":"2017年8月/第2周\u201c365\u201d会员专属总结","time":"2017-08-11 16:48:20"},{"id":"52","title":"《壹元资讯服务》周刊NO.31","brief":"2017年8月/第1周\u201c365\u201d会员专属总结","time":"2017-08-04 09:39:00"},{"id":"51","title":"《壹元资讯服务》周刊NO.30","brief":"2017年7月/第4周\u201c365\u201d会员专属总结","time":"2017-07-28 09:40:00"},{"id":"50","title":"《壹元资讯服务》周刊NO.29","brief":"2017年7月/第3周\u201c365\u201d会员专属总结","time":"2017-07-21 15:44:00"},{"id":"48","title":"《壹元资讯服务》周刊NO.28","brief":"2017年7月/第2周\u201c365\u201d会员专属总结","time":"2017-07-14 19:25:34"},{"id":"47","title":"《壹元资讯服务》周刊NO.27","brief":"2017年7月/第1周\u201c365\u201d会员专属总结","time":"2017-07-07 00:10:00"}]
     * lastid : 47
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
         * id : 58
         * title : 《壹元资讯服务》周刊NO.36
         * brief : 2017年9月/第1周“365”会员专属总结
         * time : 2017-09-08 09:20:00
         */

        private String id;
        private String title;
        private String brief;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
