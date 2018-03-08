package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/29.
 */

public class Address {

    private List<UserAddBean> user_add;

    public List<UserAddBean> getUser_add() {
        return user_add;
    }

    public void setUser_add(List<UserAddBean> user_add) {
        this.user_add = user_add;
    }

    public static class UserAddBean {
        /**
         * id : 1
         * userid : 1506586197qs54uw
         * prov_city : 江苏省南京秦淮区
         * detailed_add : 呵呵
         * re_name : 是你不是
         * re_phone : 13718040895
         */

        private String id;
        private String userid;
        private String prov_city;
        private String detailed_add;
        private String re_name;
        private String re_phone;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getProv_city() {
            return prov_city;
        }

        public void setProv_city(String prov_city) {
            this.prov_city = prov_city;
        }

        public String getDetailed_add() {
            return detailed_add;
        }

        public void setDetailed_add(String detailed_add) {
            this.detailed_add = detailed_add;
        }

        public String getRe_name() {
            return re_name;
        }

        public void setRe_name(String re_name) {
            this.re_name = re_name;
        }

        public String getRe_phone() {
            return re_phone;
        }

        public void setRe_phone(String re_phone) {
            this.re_phone = re_phone;
        }

        @Override
        public String toString() {
            return "UserAddBean{" +
                    "id='" + id + '\'' +
                    ", userid='" + userid + '\'' +
                    ", prov_city='" + prov_city + '\'' +
                    ", detailed_add='" + detailed_add + '\'' +
                    ", re_name='" + re_name + '\'' +
                    ", re_phone='" + re_phone + '\'' +
                    '}';
        }
    }
}
