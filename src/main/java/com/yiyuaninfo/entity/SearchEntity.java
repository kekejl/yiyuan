package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/26.
 */

public class SearchEntity {


    private List<SearchBean> art_arr;
    private List<SearchBean> vid_arr;
    private List<SearchBean> sh_arr;
    private List<SearchBean> nar_arr;
    private List<SearchBean> sm_arr;
    private List<SearchBean> sa_arr;

    public List<SearchBean> getArt_arr() {
        return art_arr;
    }

    public void setArt_arr(List<SearchBean> art_arr) {
        this.art_arr = art_arr;
    }

    public List<SearchBean> getVid_arr() {
        return vid_arr;
    }

    public void setVid_arr(List<SearchBean> vid_arr) {
        this.vid_arr = vid_arr;
    }

    public List<SearchBean> getNr_arr() {
        return nar_arr;
    }

    public void setNr_arr(List<SearchBean> nr_arr) {
        this.nar_arr = nr_arr;
    }

    public List<SearchBean> getSh_arr() {
        return sh_arr;
    }

    public void setSh_arr(List<SearchBean> sh_arr) {
        this.sh_arr = sh_arr;
    }

    public List<SearchBean> getSm_arr() {
        return sm_arr;
    }

    public void setSm_arr(List<SearchBean> sm_arr) {
        this.sm_arr = sm_arr;
    }

    public List<SearchBean> getSa_arr() {
        return sa_arr;
    }

    public void setSa_arr(List<SearchBean> sa_arr) {
        this.sa_arr = sa_arr;
    }


}
