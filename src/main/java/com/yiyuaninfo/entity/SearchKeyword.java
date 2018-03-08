package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/10/24.
 */

public class SearchKeyword {

    private List<HsarrBean> hsarr;

    public List<HsarrBean> getHsarr() {
        return hsarr;
    }

    public void setHsarr(List<HsarrBean> hsarr) {
        this.hsarr = hsarr;
    }

    public static class HsarrBean {
        /**
         * search_title : 宝兰高铁通车运营 中国高铁实现
         */

        private String search_title;

        public String getSearch_title() {
            return search_title;
        }

        public void setSearch_title(String search_title) {
            this.search_title = search_title;
        }
    }
}
