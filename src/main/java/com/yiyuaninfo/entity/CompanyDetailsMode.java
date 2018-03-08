package com.yiyuaninfo.entity;

/**
 * Created by gaocongcong on 2017/9/12.
 */

public class CompanyDetailsMode {
    public CompanyDetailsMode(String string, CompanyDetailsProduct prod_arr) {
        this.string = string;
        this.prod_arr = prod_arr;
    }

    /**
     * string : 安信基金注册资本2亿元，公司股东为安信证券股份有限公司、五矿投资发展有限责任公司和中广核财务有限责任公司。其中安信证券持有49%股份，五矿投资和中广核财务持股比例分别为36%和15%。
     安信证券股份有限公司是国内大型综合类证券公司之一，综合实力位居国内证券业前列。其研究业务在宏观、策略及重点行业研究等领域处于业内领先水平，团队研究成果在“新财富最佳分析师”评选、“卖方分析师水晶球奖”评选、“中国证券分析师金牛奖”评选上屡获殊荣。
     * prod_arr : {"id":"1","com_pic":"logo","label":"1","introduce":"按时间的","part":"股票型","yname":"画龙点睛","ystate":"1","yprice":"1889"}
     */

    private String string;
    private CompanyDetailsProduct prod_arr;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public CompanyDetailsProduct getProd_arr() {
        return prod_arr;
    }

    public void setProd_arr(CompanyDetailsProduct prod_arr) {
        this.prod_arr = prod_arr;
    }

}
