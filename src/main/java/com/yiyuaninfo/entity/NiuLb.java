package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/27.
 */

public class NiuLb {

    private List<LbArrBean> lb_arr;

    public List<LbArrBean> getLb_arr() {
        return lb_arr;
    }

    public void setLb_arr(List<LbArrBean> lb_arr) {
        this.lb_arr = lb_arr;
    }

    public static class LbArrBean {
        /**
         * picurl : http://yyapp.1yuaninfo.com/uploads/image/20170801/ding1.jpg
         * piclink : null
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
