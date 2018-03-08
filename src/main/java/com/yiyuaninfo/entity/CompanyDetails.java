package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/12.
 */

public class CompanyDetails {


    /**
     * com_intro_arr : {"logo":"uploads/image/20170327/1490580571.png","gname":"安信基金管理有限责任公司","url":"http://www.essencefund.com","regmoney":"35000.00","comtype":"基金","auth_tag":"国际认证","trendcontent":"公司动态","introduction":"安信基金注册资本2亿元，公司股东为安信证券股份有限公司、五矿投资发展有限责任公司和中广核财务有限责任公司。其中安信证券持有49%股份，五矿投资和中广核财务持股比例分别为36%和15%。\r\n安信证券股份有限公司是国内大型综合类证券公司之一，综合实力位居国内证券业前列。其研究业务在宏观、策略及重点行业研究等领域处于业内领先水平，团队研究成果在\u201c新财富最佳分析师\u201d评选、\u201c卖方分析师水晶球奖\u201d评选、\u201c中国证券分析师金牛奖\u201d评选上屡获殊荣。"}
     * prod_arr : [{"id":"1","com_pic":"logo","label":"1","introduce":"按时间的","part":"股票型","yname":"画龙点睛","ystate":"1","yprice":"1889"},{"id":"2","com_pic":"logo","label":"2","introduce":"按次数的","part":"股票型","yname":"锦上添花","ystate":"1","yprice":"3960"},{"id":"3","com_pic":"logo","label":"3","introduce":"简介","part":"股票型","yname":"金榜题名","ystate":"1","yprice":"3960"},{"id":"4","com_pic":"logo","label":"0","introduce":"简介","part":"货币型","yname":"红运当头（体验）","ystate":"2","yprice":"188"}]
     */

    private ComIntroArrBean com_intro_arr;
    private List<CompanyDetailsProduct> prod_arr;

    public ComIntroArrBean getCom_intro_arr() {
        return com_intro_arr;
    }

    public void setCom_intro_arr(ComIntroArrBean com_intro_arr) {
        this.com_intro_arr = com_intro_arr;
    }

    public List<CompanyDetailsProduct> getProd_arr() {
        return prod_arr;
    }

    public void setProd_arr(List<CompanyDetailsProduct> prod_arr) {
        this.prod_arr = prod_arr;
    }

    public static class ComIntroArrBean {
        /**
         * logo : uploads/image/20170327/1490580571.png
         * gname : 安信基金管理有限责任公司
         * url : http://www.essencefund.com
         * regmoney : 35000.00
         * comtype : 基金
         * auth_tag : 国际认证
         * trendcontent : 公司动态
         * introduction : 安信基金注册资本2亿元，公司股东为安信证券股份有限公司、五矿投资发展有限责任公司和中广核财务有限责任公司。其中安信证券持有49%股份，五矿投资和中广核财务持股比例分别为36%和15%。
         安信证券股份有限公司是国内大型综合类证券公司之一，综合实力位居国内证券业前列。其研究业务在宏观、策略及重点行业研究等领域处于业内领先水平，团队研究成果在“新财富最佳分析师”评选、“卖方分析师水晶球奖”评选、“中国证券分析师金牛奖”评选上屡获殊荣。
         */

        private String logo;
        private String gname;
        private String url;
        private String regmoney;
        private String comtype;
        private String auth_tag;
        private String trendcontent;
        private String introduction;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRegmoney() {
            return regmoney;
        }

        public void setRegmoney(String regmoney) {
            this.regmoney = regmoney;
        }

        public String getComtype() {
            return comtype;
        }

        public void setComtype(String comtype) {
            this.comtype = comtype;
        }

        public String getAuth_tag() {
            return auth_tag;
        }

        public void setAuth_tag(String auth_tag) {
            this.auth_tag = auth_tag;
        }

        public String getTrendcontent() {
            return trendcontent;
        }

        public void setTrendcontent(String trendcontent) {
            this.trendcontent = trendcontent;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        @Override
        public String toString() {
            return "ComIntroArrBean{" +
                    "logo='" + logo + '\'' +
                    ", gname='" + gname + '\'' +
                    ", url='" + url + '\'' +
                    ", regmoney='" + regmoney + '\'' +
                    ", comtype='" + comtype + '\'' +
                    ", auth_tag='" + auth_tag + '\'' +
                    ", trendcontent='" + trendcontent + '\'' +
                    ", introduction='" + introduction + '\'' +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "CompanyDetails{" +
                "com_intro_arr=" + com_intro_arr +
                ", prod_arr=" + prod_arr +
                '}';
    }
}
