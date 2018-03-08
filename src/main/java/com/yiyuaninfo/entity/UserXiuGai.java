package com.yiyuaninfo.entity;

/**
 * Created by Administrator on 2017/4/11.
 */

public class UserXiuGai {
    /**
     * userinfo : {"userid":"1507529879iwlkxj","username":"哈哈","avatar":"http://yyapp.1yuaninfo.com/app/application/userhead/1507529879iwlkxj.jpg","mobile":"13718040895","guling":"0","capital":null,"email":"哈哈","qqnum":null,"weixin":null,"weibo":null,"integral":"219759","groupid":"3,5,6,7,8,","expiretime":"2018-10-10"}
     * addintegral : 20
     * status : 1
     */

    private User.UserinfoBean userinfo;
    private String addintegral;
    private String status;

    public User.UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(User.UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public String getAddintegral() {
        return addintegral;
    }

    public void setAddintegral(String addintegral) {
        this.addintegral = addintegral;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



//    /**
//     * userinfo : {"userid":"1502883673g2pzyd","username":"123","avatar":"","mobile":"13718040895","guling":"100","capital":"1000","email":"112233","qqnum":null,"weixin":null,"weibo":null,"groupid":"2","expiretime":"2017-09-16"}
//     * state : 1
//     */
//
//    private UserinfoBean userinfo;
//    private String state;
//
//    public UserinfoBean getUserinfo() {
//        return userinfo;
//    }
//
//    public void setUserinfo(UserinfoBean userinfo) {
//        this.userinfo = userinfo;
//    }
//
//    public String getStatus() {
//        return state;
//    }
//
//    public void setStatus(String state) {
//        this.state = state;
//    }
//
//    public static class UserinfoBean {
//        /**
//         * userid : 1502883673g2pzyd
//         * username : 123
//         * avatar :
//         * mobile : 13718040895
//         * guling : 100
//         * capital : 1000
//         * email : 112233
//         * qqnum : null
//         * weixin : null
//         * weibo : null
//         * groupid : 2
//         * expiretime : 2017-09-16
//         */
//
//        private String userid;
//        private String username;
//        private String avatar;
//        private String mobile;
//        private String guling;
//        private String capital;
//        private String email;
//        private String qqnum;
//        private String weixin;
//        private String weibo;
//        private String groupid;
//        private String expiretime;
//        private String integral;
//
//        public String getUserid() {
//            return userid;
//        }
//
//        public void setUserid(String userid) {
//            this.userid = userid;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getAvatar() {
//            return avatar;
//        }
//
//        public void setAvatar(String avatar) {
//            this.avatar = avatar;
//        }
//
//        public String getMobile() {
//            return mobile;
//        }
//
//        public void setMobile(String mobile) {
//            this.mobile = mobile;
//        }
//
//        public String getGuling() {
//            return guling;
//        }
//
//        public void setGuling(String guling) {
//            this.guling = guling;
//        }
//
//        public String getCapital() {
//            return capital;
//        }
//
//        public void setCapital(String capital) {
//            this.capital = capital;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getQqnum() {
//            return qqnum;
//        }
//
//        public void setQqnum(String qqnum) {
//            this.qqnum = qqnum;
//        }
//
//        public String getWeixin() {
//            return weixin;
//        }
//
//        public void setWeixin(String weixin) {
//            this.weixin = weixin;
//        }
//
//        public String getWeibo() {
//            return weibo;
//        }
//
//        public void setWeibo(String weibo) {
//            this.weibo = weibo;
//        }
//
//        public String getGroupid() {
//            return groupid;
//        }
//
//        public void setGroupid(String groupid) {
//            this.groupid = groupid;
//        }
//
//        public String getExpiretime() {
//            return expiretime;
//        }
//
//        public void setExpiretime(String expiretime) {
//            this.expiretime = expiretime;
//        }
//
//        public String getIntegral() {
//            return integral;
//        }
//
//        public void setIntegral(String integral) {
//            this.integral = integral;
//        }
//
//
//        @Override
//        public String toString() {
//            return "UserinfoBean{" +
//                    "userid='" + userid + '\'' +
//                    ", username='" + username + '\'' +
//                    ", avatar='" + avatar + '\'' +
//                    ", mobile='" + mobile + '\'' +
//                    ", guling='" + guling + '\'' +
//                    ", capital='" + capital + '\'' +
//                    ", email='" + email + '\'' +
//                    ", qqnum='" + qqnum + '\'' +
//                    ", weixin='" + weixin + '\'' +
//                    ", weibo='" + weibo + '\'' +
//                    ", groupid='" + groupid + '\'' +
//                    ", expiretime='" + expiretime + '\'' +
//                    ", integral='" + integral + '\'' +
//                    '}';
//        }
//    }
}
