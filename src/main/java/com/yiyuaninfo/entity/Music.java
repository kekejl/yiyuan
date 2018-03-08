package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/2.
 */

public class Music {


    /**
     * m_arr : [{"id":"253","picurl":"uploads/image/20170717/1500291002.jpg","hits":"0","sname":"你是我哭着唱的歌","singer":"唐古 ","URL":"uploads/media/20170717/1500283467.mp3","posttime":"","delstate":"","deltime":"0"},{"id":"252","picurl":"uploads/image/20170717/1500282175.jpg","hits":"0","sname":"爱情间谍","singer":"郑晓填","URL":"uploads/media/20170717/1500290497.mp3","posttime":"","delstate":"","deltime":"0"},{"id":"251","picurl":"uploads/image/20170717/1500287533.jpg","hits":"0","sname":" 热血三国","singer":"阿兰","URL":"uploads/media/20170717/1500280686.mp3","posttime":"","delstate":"","deltime":"0"},{"id":"250","picurl":"uploads/image/20170717/1500283333.jpg","hits":"0","sname":"江湖有情","singer":"尚雯婕","URL":"uploads/media/20170717/1500283471.mp3","posttime":"","delstate":"","deltime":"0"},{"id":"249","picurl":"uploads/image/20170717/1500280024.jpg","hits":"0","sname":" 鼓声若响","singer":"齐秦","URL":"uploads/media/20170717/1500281458.mp3","posttime":"","delstate":"","deltime":"0"},{"id":"248","picurl":"uploads/image/20170717/1500282642.jpg","hits":"0","sname":"心若倦了","singer":"程响了","URL":"uploads/media/20170717/1500281462.mp3","posttime":"","delstate":"","deltime":"0"},{"id":"247","picurl":"uploads/image/20170717/1500284237.jpg","hits":"0","sname":" 思念有重量","singer":"冯建宇","URL":"uploads/media/20170717/1500285871.mp3","posttime":"","delstate":"","deltime":"0"},{"id":"246","picurl":"uploads/image/20170717/1500277050.jpg","hits":"0","sname":"调侃","singer":"许佳慧 ","URL":"uploads/media/20170717/1500285930.mp3","posttime":"","delstate":"","deltime":"0"},{"id":"245","picurl":"uploads/image/20170717/1500284538.jpg","hits":"0","sname":" 高攀","singer":"李炜","URL":"uploads/media/20170717/1500284207.mp3","posttime":"","delstate":"","deltime":"0"},{"id":"244","picurl":"uploads/image/20170717/1500281129.jpg","hits":"0","sname":" 我不送","singer":"金南玲 ","URL":"uploads/media/20170717/1500275795.mp3","posttime":"","delstate":"","deltime":"0"}]
     * lastid : [{lastid:244}]
     */

    private String lastid;
    private List<MArrBean> m_arr;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<MArrBean> getM_arr() {
        return m_arr;
    }

    public void setM_arr(List<MArrBean> m_arr) {
        this.m_arr = m_arr;
    }

    public static class MArrBean {
        /**
         * id : 253
         * picurl : uploads/image/20170717/1500291002.jpg
         * hits : 0
         * sname : 你是我哭着唱的歌
         * singer : 唐古
         * URL : uploads/media/20170717/1500283467.mp3
         * posttime :
         * delstate :
         * deltime : 0
         */

        private String id;
        private String picurl;
        private String hits;
        private String sname;
        private String singer;
        private String URL;
        private String posttime;
        private String delstate;
        private String deltime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getSinger() {
            return singer;
        }

        public void setSinger(String singer) {
            this.singer = singer;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public String getPosttime() {
            return posttime;
        }

        public void setPosttime(String posttime) {
            this.posttime = posttime;
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
            return "MArrBean{" +
                    "id='" + id + '\'' +
                    ", picurl='" + picurl + '\'' +
                    ", hits='" + hits + '\'' +
                    ", sname='" + sname + '\'' +
                    ", singer='" + singer + '\'' +
                    ", URL='" + URL + '\'' +
                    ", posttime='" + posttime + '\'' +
                    ", delstate='" + delstate + '\'' +
                    ", deltime='" + deltime + '\'' +
                    '}';
        }
    }
}
