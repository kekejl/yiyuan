package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */

public class HotTagsEntity {


    public   List<AllTagsListBean> allTagsList;

  /*  public List<AllTagsListBean> getAllTagsList() {
        return allTagsList;
    }

    public void setAllTagsList(List<AllTagsListBean> allTagsList) {
        this.allTagsList = allTagsList;
    }*/

    public static class AllTagsListBean {
        /**
         * tagsName : 行情
         * tagsId : 0
         * tagInfoList : [{"tagName":"美食天下","tagId":"0","url":"www.meishichina.com","popular":"5200"},{"tagName":"美食天下","tagId":"0","url":"www.meishichina.com","popular":"5200"},{"tagName":"美食天下","tagId":"0","url":"www.meishichina.com","popular":"5200"},{"tagName":"美食天下","tagId":"0","url":"www.meishichina.com","popular":"5200"},{"tagName":"美食天下","tagId":"0","url":"www.meishichina.com","popular":"5200"}]
         */

        private String tagsName;
        private String tagsId;
        private List<TagInfoListBean> tagInfoList;

        public String getTagsName() {
            return tagsName;
        }

        public void setTagsName(String tagsName) {
            this.tagsName = tagsName;
        }

        public String getTagsId() {
            return tagsId;
        }

        public void setTagsId(String tagsId) {
            this.tagsId = tagsId;
        }

        public List<TagInfoListBean> getTagInfoList() {
            return tagInfoList;
        }

        public void setTagInfoList(List<TagInfoListBean> tagInfoList) {
            this.tagInfoList = tagInfoList;
        }

        public static class TagInfoListBean {
            /**
             * tagName : 美食天下
             * tagId : 0
             * url : www.meishichina.com
             * popular : 5200
             */

            private String tagName;
            private String tagId;
            private String url;
            private String popular;

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }

            public String getTagId() {
                return tagId;
            }

            public void setTagId(String tagId) {
                this.tagId = tagId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPopular() {
                return popular;
            }

            public void setPopular(String popular) {
                this.popular = popular;
            }
        }
    }
}
