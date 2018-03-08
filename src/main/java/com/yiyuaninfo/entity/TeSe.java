package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2018/1/31.
 */

public class TeSe {


    private List<UsarrBean> usarr;

    public List<UsarrBean> getUsarr() {
        return usarr;
    }

    public void setUsarr(List<UsarrBean> usarr) {
        this.usarr = usarr;
    }

    public static class UsarrBean {
        /**
         * addtime : 2018-01-31 15:29:48
         * gpdm : 002571
         * isgain : 2
         * yanbao : null
         */

        private String addtime;
        private String gpdm;
        private String isgain;
        private String yanbao;

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getGpdm() {
            return gpdm;
        }

        public void setGpdm(String gpdm) {
            this.gpdm = gpdm;
        }

        public String getIsgain() {
            return isgain;
        }

        public void setIsgain(String isgain) {
            this.isgain = isgain;
        }

        public String getYanbao() {
            return yanbao;
        }

        public void setYanbao(String yanbao) {
            this.yanbao = yanbao;
        }

        @Override
        public String toString() {
            return "UsarrBean{" +
                    "addtime='" + addtime + '\'' +
                    ", gpdm='" + gpdm + '\'' +
                    ", isgain='" + isgain + '\'' +
                    ", yanbao=" + yanbao +
                    '}';
        }
    }
}
