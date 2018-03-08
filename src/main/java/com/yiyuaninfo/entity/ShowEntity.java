package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/3.
 */

public class ShowEntity {

    /**
     * lb_arr : [{"picurl":"http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg","piclink":"http://www.baidu5.com"},{"picurl":"http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg","piclink":"http://www.baidu5.com"},{"picurl":"http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg","piclink":"http://www.baidu5.com"},{"picurl":"http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg","piclink":"http://www.baidu5.com"}]
     * tj_arr : [{"id":"1","indeximg":"uploads/image/20170225/1488014998.png"},{"id":"3","indeximg":"uploads/image/20170222/1487738667.jpg"},{"id":"5","indeximg":"uploads/image/20170225/1488019670.png"},{"id":"7","indeximg":"uploads/image/20170222/1487731896.jpg"},{"id":"9","indeximg":"uploads/image/20170223/1487838915.jpg"},{"id":"11","indeximg":"uploads/image/20170223/1487838581.png"},{"id":"13","indeximg":"uploads/image/20170223/1487835442.png"},{"id":"15","indeximg":"uploads/image/20170224/1487925721.png"},{"id":"17","indeximg":"uploads/image/20170313/1489382537.jpg"},{"id":"19","indeximg":"uploads/image/20170315/1489551496.jpg"},{"id":"21","indeximg":"uploads/image/20170410/1491818047.jpg"},{"id":"23","indeximg":"uploads/image/20170410/1491809862.jpg"},{"id":"25","indeximg":"uploads/image/20170410/1491809549.jpg"},{"id":"27","indeximg":"uploads/image/20170410/1491816129.jpg"},{"id":"29","indeximg":"uploads/image/20170411/1491897946.jpg"},{"id":"31","indeximg":"uploads/image/20170424/1493025884.jpg"},{"id":"33","indeximg":"uploads/image/20170424/1493030997.jpg"},{"id":"35","indeximg":"uploads/image/20170428/1493372529.jpg"},{"id":"37","indeximg":"uploads/image/20170516/1494913884.jpg"},{"id":"39","indeximg":"uploads/image/20170517/1495014571.jpg"},{"id":"41","indeximg":"uploads/image/20170522/1495435097.jpg"},{"id":"43","indeximg":"uploads/image/20170608/1496912996.jpg"},{"id":"45","indeximg":"uploads/image/20170615/1497518381.jpg"},{"id":"47","indeximg":"uploads/image/20170622/1498115514.jpg"},{"id":"49","indeximg":"uploads/image/20170707/1499412667.jpg"}]
     * xh_arr : [{"id":"50","actionname":"2017张北草原音乐节","palace":"张北中都原始草原度假村 ","actiontime":"2017-07-28 14:00:00","indeximg":"uploads/image/20170707/1499419050.jpg","tag":"自定义标签","price":"200-340元"},{"id":"48","actionname":"古典音乐的异想世界 ","palace":"国图艺术中心","actiontime":"2017-07-29 14:00:00","indeximg":"uploads/image/20170622/1498115584.jpg","tag":"自定义标签","price":"150元"},{"id":"46","actionname":"ofo轻睐北京演唱会\u2014北京站","palace":" 工人体育场","actiontime":"2017-07-02 19:30:00","indeximg":"uploads/image/20170616/1497596333.jpg","tag":"自定义标签","price":" ￥99.00~￥599.00"},{"id":"44","actionname":"五月天LIFE《人生无限公司》2017 MAYDAY LIFE TOUR-北京站","palace":"国家体育场","actiontime":"2017-08-18 18:30:00","indeximg":"uploads/image/20170615/1497508628.png","tag":"自定义标签","price":"255-1855元"},{"id":"42","actionname":"梁咏琪2017","palace":"五棵松体育馆","actiontime":"2017-08-05 19:30:00","indeximg":"uploads/image/20170608/1496909267.jpg","tag":"自定义标签","price":"280-1280元"},{"id":"40","actionname":"2017狮子合唱团演唱会季节限定版","palace":"五棵松体育馆","actiontime":"2017-07-22 19:30:00","indeximg":"uploads/image/20170522/1495436603.jpg","tag":"自定义标签","price":"380元起"},{"id":"38","actionname":" 分享到： 立即订阅 2017\u201c如果\u201d田馥甄巡回演唱会PLUS 北京站 ","palace":"首都体育馆","actiontime":"2017-07-08 19:30:00","indeximg":"uploads/image/20170516/1494926883.jpg","tag":"自定义标签","price":"280起"},{"id":"36","actionname":"信GentleMonster2017巡回演唱会","palace":"乐视体育生态中心 ","actiontime":"2017-05-13 19:00:00","indeximg":"uploads/image/20170428/1493368053.jpg","tag":"自定义标签","price":"280-1280元"},{"id":"34","actionname":"Live 4 LIVE《尖叫现场》","palace":"北京展览馆剧场","actiontime":"2017-05-05 20:00:00","indeximg":"uploads/image/20170424/1493026493.jpg","tag":"自定义标签","price":"280起"},{"id":"32","actionname":"李易峰2017生日会 ","palace":"北京乐视生态体育中心","actiontime":"2017-05-01 19:00:00","indeximg":"uploads/image/20170424/1493026725.jpg","tag":"自定义标签","price":"580起"}]
     * lastid : [{lastid:32}]
     */

    private String lastid;
    private List<LbArrBean> lb_arr;
    private List<TjArrBean> tj_arr;
    private List<XhArrBean> xh_arr;

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }

    public List<LbArrBean> getLb_arr() {
        return lb_arr;
    }

    public void setLb_arr(List<LbArrBean> lb_arr) {
        this.lb_arr = lb_arr;
    }

    public List<TjArrBean> getTj_arr() {
        return tj_arr;
    }

    public void setTj_arr(List<TjArrBean> tj_arr) {
        this.tj_arr = tj_arr;
    }

    public List<XhArrBean> getXh_arr() {
        return xh_arr;
    }

    public void setXh_arr(List<XhArrBean> xh_arr) {
        this.xh_arr = xh_arr;
    }

    public static class LbArrBean {
        /**
         * picurl : http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg
         * piclink : http://www.baidu5.com
         */

        private String picurl;
        private String piclink;

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getPiclink() {
            return piclink;
        }

        public void setPiclink(String piclink) {
            this.piclink = piclink;
        }
    }

    public static class TjArrBean {
        /**
         * id : 1
         * indeximg : uploads/image/20170225/1488014998.png
         */

        private String id;
        private String indeximg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIndeximg() {
            return indeximg;
        }

        public void setIndeximg(String indeximg) {
            this.indeximg = indeximg;
        }
    }

}
