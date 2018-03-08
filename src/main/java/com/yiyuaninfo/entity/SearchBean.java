package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/9/26.
 */

public class SearchBean {
    /**
     * id : 77
     * title : 特朗普称正考虑向波音公司大量购买F-18装备美军
     * link : http://yyapp.1yuaninfo.com/app/yyfwapp/news-details.php?id=''&userid=''
     *
     */

    private String id;
    private String title;
    private String link;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "SearchBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
