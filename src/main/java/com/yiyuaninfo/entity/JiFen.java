package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/10/11.
 */

public class JiFen {

    private String jifen;
    private String money;

    public JiFen(String jifen, String money) {
        this.jifen = jifen;
        this.money = money;
    }

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "JiFen{" +
                "jifen='" + jifen + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
