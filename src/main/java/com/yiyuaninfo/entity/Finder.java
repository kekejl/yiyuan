package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/7/19.
 */

public class Finder {


    /**
     * other : [{"id":"18","logo":"uploads/image/20170327/1490581882.png","gname":"嘉实基金管理有限公司","regmoney":"15000.00","auth_tag":null,"part":"1,2","comtype":"基金"},{"id":"17","logo":"uploads/image/20170327/1490580264.png","gname":"汇添富基金管理股份有限公司","regmoney":"13300.00","auth_tag":null,"part":"1,2","comtype":"基金"},{"id":"16","logo":"uploads/image/20170327/1490578243.png","gname":"汇丰晋信基金管理有限公司","regmoney":"20000.00","auth_tag":null,"part":"1,2","comtype":"基金"},{"id":"15","logo":"uploads/image/20170327/1490581952.png","gname":"华商基金管理有限公司","regmoney":"10000.00","auth_tag":null,"part":"1,2","comtype":"基金"},{"id":"14","logo":"uploads/image/20170327/1490580859.png","gname":"华安基金管理有限公司","regmoney":"1.50","auth_tag":null,"part":"1,3","comtype":"基金"},{"id":"12","logo":"uploads/image/20170327/1490585178.png","gname":"国泰基金管理有限公司","regmoney":"1.10","auth_tag":null,"part":"1","comtype":"基金"},{"id":"11","logo":"uploads/image/20170327/1490583311.png","gname":"广发基金管理有限公司","regmoney":"12700.00","auth_tag":null,"part":"1,3","comtype":"基金"},{"id":"10","logo":"uploads/image/20170327/1490582005.png","gname":"光大保德信基金管理有限公司","regmoney":"16000.00","auth_tag":null,"part":"1","comtype":"基金"},{"id":"9","logo":"uploads/image/20170327/1490578571.png","gname":"工银瑞信基金管理有限公司","regmoney":"20000.00","auth_tag":null,"part":"1,2,3","comtype":"基金"},{"id":"4","logo":"uploads/image/20170327/1490578671.png","gname":"大成基金管理有限公司","regmoney":"20000.00","auth_tag":null,"part":"1","comtype":"基金"}]
     * lastid : 4
     */

    private String lastid;
    private List<FinderBean> other;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<FinderBean> getOther() {
        return other;
    }

    public void setOther(List<FinderBean> other) {
        this.other = other;
    }

}