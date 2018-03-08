package com.yiyuaninfo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gaocongcong on 2017/8/13.
 */

public class Pay {


    /**
     * params : {"appid":"wx11d19ee9062c616a","partnerid":"1453833902","prepayid":"wx201708131422037e241ef8d30336993137","package":"Sign=WXPay","noncestr":"7h8sh7btbkehtu40qw24flbyeu87ufc4","timestamp":1502605329,"sign":"C99495DE982F51E69019474D264A5747"}
     */

    private ParamsBean params;

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {
        /**
         * appid : wx11d19ee9062c616a
         * partnerid : 1453833902
         * prepayid : wx201708131422037e241ef8d30336993137
         * package : Sign=WXPay
         * noncestr : 7h8sh7btbkehtu40qw24flbyeu87ufc4
         * timestamp : 1502605329
         * sign : C99495DE982F51E69019474D264A5747
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private String timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        @Override
        public String toString() {
            return "ParamsBean{" +
                    "appid='" + appid + '\'' +
                    ", partnerid='" + partnerid + '\'' +
                    ", prepayid='" + prepayid + '\'' +
                    ", packageX='" + packageX + '\'' +
                    ", noncestr='" + noncestr + '\'' +
                    ", timestamp=" + timestamp +
                    ", sign='" + sign + '\'' +
                    '}';
        }
    }
}
