package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/8.
 */

public class KuaiXun {

    private List<MsglistBean> msglist;

    public List<MsglistBean> getMsglist() {
        return msglist;
    }

    public void setMsglist(List<MsglistBean> msglist) {
        this.msglist = msglist;
    }

    public static class MsglistBean {
        private List<String> datearr;
        private List<InfoarrBean> infoarr;

        public List<String> getDatearr() {
            return datearr;
        }

        public void setDatearr(List<String> datearr) {
            this.datearr = datearr;
        }

        public List<InfoarrBean> getInfoarr() {
            return infoarr;
        }

        public void setInfoarr(List<InfoarrBean> infoarr) {
            this.infoarr = infoarr;
        }

        public static class InfoarrBean {
            /**
             * id : 5
             * title : 文字公告
             * addtime : 2017-04-10
             */

            private String id;
            private String title;
            private String addtime;

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

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }
    }
}
