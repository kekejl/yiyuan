package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/10/9.
 */

public class QianDao {

    /**
     * state : 1
     * signtimes : 1
     * lastsigndate : 2017-10-09
     * getintegral : 5
     */

    private String state;
    private String signtimes;
    private String lastsigndate;
    private String getintegral;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSigntimes() {
        return signtimes;
    }

    public void setSigntimes(String signtimes) {
        this.signtimes = signtimes;
    }

    public String getLastsigndate() {
        return lastsigndate;
    }

    public void setLastsigndate(String lastsigndate) {
        this.lastsigndate = lastsigndate;
    }

    public String getGetintegral() {
        return getintegral;
    }

    public void setGetintegral(String getintegral) {
        this.getintegral = getintegral;
    }

    @Override
    public String toString() {
        return "QianDao{" +
                "state='" + state + '\'' +
                ", signtimes='" + signtimes + '\'' +
                ", lastsigndate='" + lastsigndate + '\'' +
                ", getintegral='" + getintegral + '\'' +
                '}';
    }
}
