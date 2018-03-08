package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/8/18.
 */

public class Custom {
    private String  key;
    private String  value;

    public Custom(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
