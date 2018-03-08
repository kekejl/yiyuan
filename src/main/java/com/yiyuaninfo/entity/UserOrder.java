package com.yiyuaninfo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaocongcong on 2017/9/29.
 */

public class UserOrder {


    /**
     * user_order : [{"id":"6","goodid":"106","packid":"0","packname":"画龙点睛（体验）","ordernum":"14538339022017092914074598773417","leixing":"2","mobile":"13718040895","username":"13718040895","userid":"1506586197qs54uw","buytime":"","price":"19800","number":"1","cost":"19800","deltime":"","delstate":"","suctimes":"0","failtimes":"0","alltimes":"0","lastime":"0","sucrate":"0","expiretime":null,"times":"0","paystatus":"0","sign":"6C2D655202433F0C0DFA54C83902DA1A","transaction_id":null,"bank_type":null,"pay_type":null},{"id":"5","goodid":"109","packid":"7","packname":"锦上添花","ordernum":"14538339022017092914072476788011","leixing":"2","mobile":"13718040895","username":"13718040895","userid":"1506586197qs54uw","buytime":"","price":"16800","number":"1","cost":"16800","deltime":"","delstate":"","suctimes":"0","failtimes":"0","alltimes":"0","lastime":"0","sucrate":"0","expiretime":null,"times":"0","paystatus":"0","sign":"3B5E4274F4C615FE09E13CA69C0E37AC","transaction_id":null,"bank_type":null,"pay_type":null},{"id":"4","goodid":"108","packid":"0","packname":"金榜题名","ordernum":"14538339022017092914061166133932","leixing":"2","mobile":"13718040895","username":"13718040895","userid":"1506586197qs54uw","buytime":"","price":"6800","number":"1","cost":"6800","deltime":"","delstate":"","suctimes":"0","failtimes":"0","alltimes":"0","lastime":"0","sucrate":"0","expiretime":null,"times":"0","paystatus":"0","sign":"AB46A2CDAD627B9332B238CB00190D9E","transaction_id":null,"bank_type":null,"pay_type":"1"},{"id":"3","goodid":"110","packid":"6","packname":"画龙点睛","ordernum":"14538339022017092914054167438374","leixing":"2","mobile":"13718040895","username":"13718040895","userid":"1506586197qs54uw","buytime":"","price":"36800","number":"1","cost":"36800","deltime":"","delstate":"","suctimes":"0","failtimes":"0","alltimes":"0","lastime":"0","sucrate":"0","expiretime":null,"times":"0","paystatus":"0","sign":"9515E501E05286BD75403EC0FE75CABE","transaction_id":null,"bank_type":null,"pay_type":"1"},{"id":"2","goodid":"1","packid":"3","packname":"365会员","ordernum":"14538339022017092911343800026296","leixing":"1","mobile":"13718040895","username":"13718040895","userid":"1506586197qs54uw","buytime":"","price":"365","number":"1","cost":"1","deltime":"","delstate":"","suctimes":"0","failtimes":"0","alltimes":"0","lastime":"0","sucrate":"0","expiretime":null,"times":"0","paystatus":"0","sign":"9837A53F99ADC96973DF4424546436CE","transaction_id":null,"bank_type":null,"pay_type":"1"},{"id":"1","goodid":"111","packid":"0","packname":"红运当头","ordernum":"14538339022017092911341420976266","leixing":"2","mobile":"13718040895","username":"13718040895","userid":"1506586197qs54uw","buytime":"","price":"1680","number":"1","cost":"1680","deltime":"","delstate":"","suctimes":"0","failtimes":"0","alltimes":"0","lastime":"0","sucrate":"0","expiretime":null,"times":"0","paystatus":"0","sign":"E07E6A7D02BC434FFB23CD10D032A55C","transaction_id":null,"bank_type":null,"pay_type":"1"}]
     * lastid : 1
     */

    private String lastid;
    private List<UserOrderBean> user_order;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<UserOrderBean> getUser_order() {
        return user_order;
    }

    public void setUser_order(List<UserOrderBean> user_order) {
        this.user_order = user_order;
    }

    public static class UserOrderBean    implements Serializable{
        /**
         * id : 6
         * goodid : 106
         * packid : 0
         * packname : 画龙点睛（体验）
         * ordernum : 14538339022017092914074598773417
         * leixing : 2
         * mobile : 13718040895
         * username : 13718040895
         * userid : 1506586197qs54uw
         * buytime :
         * price : 19800
         * number : 1
         * cost : 19800
         * deltime :
         * delstate :
         * suctimes : 0
         * failtimes : 0
         * alltimes : 0
         * lastime : 0
         * sucrate : 0
         * expiretime : null
         * times : 0
         * paystatus : 0
         * sign : 6C2D655202433F0C0DFA54C83902DA1A
         * transaction_id : null
         * bank_type : null
         * pay_type : null
         */

        private String id;
        private String goodid;
        private String packid;
        private String packname;
        private String ordernum;
        private String leixing;
        private String mobile;
        private String username;
        private String userid;
        private String buytime;
        private String price;
        private String number;
        private String cost;
        private String deltime;
        private String delstate;
        private String suctimes;
        private String failtimes;
        private String alltimes;
        private String lastime;
        private String sucrate;
        private String expiretime;
        private String times;
        private String paystatus;

        public String getUnique_ordernum() {
            return unique_ordernum;
        }

        public void setUnique_ordernum(String unique_ordernum) {
            this.unique_ordernum = unique_ordernum;
        }

        private String sign;
        private String unique_ordernum;
        private String transaction_id;
        private String bank_type;
        private String pay_type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodid() {
            return goodid;
        }

        public void setGoodid(String goodid) {
            this.goodid = goodid;
        }

        public String getPackid() {
            return packid;
        }

        public void setPackid(String packid) {
            this.packid = packid;
        }

        public String getPackname() {
            return packname;
        }

        public void setPackname(String packname) {
            this.packname = packname;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getLeixing() {
            return leixing;
        }

        public void setLeixing(String leixing) {
            this.leixing = leixing;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getBuytime() {
            return buytime;
        }

        public void setBuytime(String buytime) {
            this.buytime = buytime;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getDeltime() {
            return deltime;
        }

        public void setDeltime(String deltime) {
            this.deltime = deltime;
        }

        public String getDelstate() {
            return delstate;
        }

        public void setDelstate(String delstate) {
            this.delstate = delstate;
        }

        public String getSuctimes() {
            return suctimes;
        }

        public void setSuctimes(String suctimes) {
            this.suctimes = suctimes;
        }

        public String getFailtimes() {
            return failtimes;
        }

        public void setFailtimes(String failtimes) {
            this.failtimes = failtimes;
        }

        public String getAlltimes() {
            return alltimes;
        }

        public void setAlltimes(String alltimes) {
            this.alltimes = alltimes;
        }

        public String getLastime() {
            return lastime;
        }

        public void setLastime(String lastime) {
            this.lastime = lastime;
        }

        public String getSucrate() {
            return sucrate;
        }

        public void setSucrate(String sucrate) {
            this.sucrate = sucrate;
        }

        public String getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(String expiretime) {
            this.expiretime = expiretime;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(String paystatus) {
            this.paystatus = paystatus;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getBank_type() {
            return bank_type;
        }

        public void setBank_type(String bank_type) {
            this.bank_type = bank_type;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        @Override
        public String toString() {
            return "UserOrderBean{" +
                    "id='" + id + '\'' +
                    ", goodid='" + goodid + '\'' +
                    ", packid='" + packid + '\'' +
                    ", packname='" + packname + '\'' +
                    ", ordernum='" + ordernum + '\'' +
                    ", leixing='" + leixing + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", username='" + username + '\'' +
                    ", userid='" + userid + '\'' +
                    ", buytime='" + buytime + '\'' +
                    ", price='" + price + '\'' +
                    ", number='" + number + '\'' +
                    ", cost='" + cost + '\'' +
                    ", deltime='" + deltime + '\'' +
                    ", delstate='" + delstate + '\'' +
                    ", suctimes='" + suctimes + '\'' +
                    ", failtimes='" + failtimes + '\'' +
                    ", alltimes='" + alltimes + '\'' +
                    ", lastime='" + lastime + '\'' +
                    ", sucrate='" + sucrate + '\'' +
                    ", expiretime='" + expiretime + '\'' +
                    ", times='" + times + '\'' +
                    ", paystatus='" + paystatus + '\'' +
                    ", sign='" + sign + '\'' +
                    ", transaction_id='" + transaction_id + '\'' +
                    ", bank_type='" + bank_type + '\'' +
                    ", pay_type='" + pay_type + '\'' +
                    '}';
        }
    }
}
