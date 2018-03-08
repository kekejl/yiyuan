package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/19.
 */

public class Flash {


    /**
     * roll_words : [{"date":"05月30日","info":[{"wid":"12","title":"白糖需求会逐步好转12","addtime":"2017-05-30"}]},{"date":"05月29日","info":[{"wid":"11","title":"白糖需求会逐步好11","addtime":"2017-05-29"}]},{"date":"05月28日","info":[{"wid":"10","title":"白糖需求会逐步好转10","addtime":"2017-05-28"}]},{"date":"05月27日","info":[{"wid":"9","title":"白糖需求会逐步好转9","addtime":"2017-05-27"}]},{"date":"05月26日","info":[{"wid":"8","title":"白糖需求会逐步好转8","addtime":"2017-05-26"},{"wid":"7","title":"白糖需求会逐步好转7","addtime":"2017-05-26"},{"wid":"6","title":"白糖需求会逐步好转6","addtime":"2017-05-26"}]},{"date":"05月25日","info":[{"wid":"5","title":"白糖需求会逐步好转5","addtime":"2017-05-25"},{"wid":"4","title":"欧央行将进去\u201c政策纠结期\u201d","addtime":"2017-05-25"}]},{"date":"05月24日","info":[{"wid":"1","title":"投资私募要注意了 不要被\u201c精英\u201d欺骗了","addtime":"2017-05-24"}]}]
     * lastdate : 2017-05-24
     */

    private String lastdate;
    private List<RollWordsBean> roll_words;

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public List<RollWordsBean> getRoll_words() {
        return roll_words;
    }

    public void setRoll_words(List<RollWordsBean> roll_words) {
        this.roll_words = roll_words;
    }

    public static class RollWordsBean {
        /**
         * date : 05月30日
         * info : [{"wid":"12","title":"白糖需求会逐步好转12","addtime":"2017-05-30"}]
         */

        private String date;
        private List<FlashBean> info;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<FlashBean> getInfo() {
            return info;
        }

        public void setInfo(List<FlashBean> info) {
            this.info = info;
        }

    }
}
