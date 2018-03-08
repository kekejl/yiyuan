package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/9/27.
 */

public class IsGuanZhu {


    /**
     * niu_arr : {"id":"3","niu_name":"方风雷","niu_img":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170801/fangfenglei.jpg","niu_introduce":"【牛气表现】20世纪90年代初，方风雷参与筹建中国首家中外合资投资银行\u2014\u2014中国国际金融有限公司，并出任副总裁，成为\u201c第一代中国本土投资银行家\u201d。从中金到中银，到工商东亚，再到高盛高华，以及如今的厚朴基金，投身投行十多年来，方风雷一直站在资本市场的最前沿。"}
     * info : 1
     * error : 0
     */

    private NiuArrBean niu_arr;
    private String info;
    private String error;

    public NiuArrBean getNiu_arr() {
        return niu_arr;
    }

    public void setNiu_arr(NiuArrBean niu_arr) {
        this.niu_arr = niu_arr;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class NiuArrBean {
        /**
         * id : 3
         * niu_name : 方风雷
         * niu_img : http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170801/fangfenglei.jpg
         * niu_introduce : 【牛气表现】20世纪90年代初，方风雷参与筹建中国首家中外合资投资银行——中国国际金融有限公司，并出任副总裁，成为“第一代中国本土投资银行家”。从中金到中银，到工商东亚，再到高盛高华，以及如今的厚朴基金，投身投行十多年来，方风雷一直站在资本市场的最前沿。
         */

        private String id;
        private String niu_name;
        private String niu_img;
        private String niu_introduce;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNiu_name() {
            return niu_name;
        }

        public void setNiu_name(String niu_name) {
            this.niu_name = niu_name;
        }

        public String getNiu_img() {
            return niu_img;
        }

        public void setNiu_img(String niu_img) {
            this.niu_img = niu_img;
        }

        public String getNiu_introduce() {
            return niu_introduce;
        }

        public void setNiu_introduce(String niu_introduce) {
            this.niu_introduce = niu_introduce;
        }
    }
}
