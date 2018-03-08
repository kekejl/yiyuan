package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/3.
 */

public class ShowMroeEntity {

    /**
     * xh_arr : [{"id":"32","actionname":"李易峰2017生日会 ","palace":"北京乐视生态体育中心","actiontime":"2017-05-01 19:00:00","indeximg":"uploads/image/20170424/1493026725.jpg","tag":"自定义标签","price":"580起"},{"id":"30","actionname":"中央民族歌舞团 2017年春季演出季 音乐会《草原赞歌》演出","palace":"民族剧院","actiontime":"2017-04-28 19:30:00","indeximg":"uploads/image/20170411/1491894222.jpg","tag":"自定义标签","price":"50-380元"},{"id":"28","actionname":"谭咏麟银河岁月40载巡回演唱会 北京站","palace":"乐视体育生态中心 LeSports Center（原万事达中心、五棵松体育馆） ","actiontime":"2017-04-22 19:00:00","indeximg":"uploads/image/20170410/1491792745.jpg","tag":"自定义标签","price":""},{"id":"26","actionname":"encounter with the prince 2017Happy BirthdayTo ZHA JIE 与\u201c小王子\u201d查杰的生日邂逅","palace":"壹空间THE ONE CLUB","actiontime":"2017-04-08 19:00:00","indeximg":"uploads/image/20170410/1491813221.jpg","tag":"自定义标签","price":"380元"},{"id":"24","actionname":"热斑《无感人》专辑北京首唱会","palace":"","actiontime":"2017-04-05 19:30:00","indeximg":"uploads/image/20170410/1491808769.jpg","tag":"自定义标签","price":"100元"},{"id":"22","actionname":"2017零点乐队北京演唱会","palace":"工人体育馆门","actiontime":"2017-05-20 18:00:00","indeximg":"uploads/image/20170327/1490596753.jpg","tag":"自定义标签","price":"180,-,888元"},{"id":"20","actionname":"Live 4 LIVE《尖叫现场》·周笔畅","palace":"北京展览馆剧场","actiontime":"2017-04-07 20:00:00","indeximg":"uploads/image/20170316/1489663698.jpg","tag":"自定义标签","price":"280元起"},{"id":"18","actionname":"周传雄世界巡演启动 回顾28年歌坛生涯","palace":"北京乐视体育生态中心（原五棵松体育馆）","actiontime":"2017-04-15 01:48:00","indeximg":"uploads/image/20170315/1489555530.jpg","tag":"自定义标签","price":"惠情侣2人套票1314元（原价895元/张）"},{"id":"16","actionname":"汪峰2017岁月超级巡回演唱会","palace":"国家体育场(鸟巢)","actiontime":"2017-09-09 19:30:00","indeximg":"uploads/image/20170313/1489378447.jpg","tag":"自定义标签","price":"280-1680元"},{"id":"14","actionname":"边吃边看的都市癫狂音乐喜剧《疯狂约会》（含超值自助餐） ","palace":"大麦Live-音乐剧主题餐厅","actiontime":"2017-02-23 15:23:34","indeximg":"uploads/image/20170223/1487839161.jpg","tag":"自定义标签","price":"188-268元"}]
     * lastid : 14
     */

    private String lastid;
    private List<XhArrBean> xh_arr;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<XhArrBean> getXh_arr() {
        return xh_arr;
    }

    public void setXh_arr(List<XhArrBean> xh_arr) {
        this.xh_arr = xh_arr;
    }


}
