package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/29.
 */

public class CPCompany {

    /**
     * com_arr : [{"id":"225","logo":"http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170914/1505392436.jpg","gname":"中文全称：","regmoney":"0.00","auth_tag":null,"part":"1","comtype":"投顾"},{"id":"224","logo":"uploads/image/20170327/1490594550.png","gname":"中原证券股份有限公司","regmoney":"392373.47","auth_tag":"国际认证","part":"2,3","comtype":"券商"},{"id":"223","logo":"uploads/image/20170327/1490592956.png","gname":"中邮证券有限责任公司","regmoney":"206000.00","auth_tag":"国际认证","part":"2,3","comtype":"券商"},{"id":"222","logo":"uploads/image/20170327/1490594785.png","gname":"中银国际证券有限责任公司","regmoney":"250000.00","auth_tag":"国际认证","part":"1,2,3","comtype":"券商"},{"id":"221","logo":"uploads/image/20170327/1490594649.png","gname":"中信证券股份有限公司","regmoney":"1000000.00","auth_tag":"国际认证","part":"1,2,3","comtype":"券商"},{"id":"220","logo":"uploads/image/20170327/1490595835.png","gname":"中信建投证券股份有限公司","regmoney":"724638.50","auth_tag":"国际认证","part":"2,3","comtype":"券商"},{"id":"219","logo":"uploads/image/20170327/1490595903.png","gname":"中天证券股份有限公司","regmoney":"222500.00","auth_tag":"国际认证","part":"2,3","comtype":"券商"},{"id":"218","logo":"uploads/image/20170327/1490599260.png","gname":"中泰证券股份有限公司","regmoney":"627176.31","auth_tag":"国际认证","part":"2,3","comtype":"券商"},{"id":"217","logo":"uploads/image/20170327/1490598466.png","gname":"中山证券有限责任公司","regmoney":"135500.00","auth_tag":"国际认证","part":"2","comtype":"券商"},{"id":"216","logo":"uploads/image/20170327/1490592568.png","gname":"中航证券有限公司","regmoney":"198522.09","auth_tag":"国际认证","part":"2,3","comtype":"券商"}]
     * lastid : 216
     */

    private String lastid;
    private List<FinderBean> com_arr;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<FinderBean> getCom_arr() {
        return com_arr;
    }

    public void setCom_arr(List<FinderBean> com_arr) {
        this.com_arr = com_arr;
    }

}
