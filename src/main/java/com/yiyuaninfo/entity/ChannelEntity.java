package com.yiyuaninfo.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */

public class ChannelEntity {


    private List<AllChannelListBean> allChannelList;

    public List<AllChannelListBean> getAllChannelList() {
        return allChannelList;
    }

    public void setAllChannelList(List<AllChannelListBean> allChannelList) {
        this.allChannelList = allChannelList;
    }

    public static class AllChannelListBean {
        /**
         * groupName : 行情
         * groupList : [{"channelname":"股市","tagType":"1","group":"行情","id":"0"},{"channelname":"基金","tagType":"2","group":"行情","id":"1"},{"channelname":"债券","tagType":"3","group":"行情","id":"2"},{"channelname":"外汇","tagType":"4","group":"行情","id":"3"},{"channelname":"期货","tagType":"5","group":"行情","id":"4"},{"channelname":"大宗","tagType":"6","group":"行情","id":"5"},{"channelname":"黄金","tagType":"7","group":"行情","id":"6"},{"channelname":"比特币","tagType":"8","group":"行情","id":"7"}]
         */

        private String groupName;
        private List<GroupListBean> groupList;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public List<GroupListBean> getGroupList() {
            return groupList;
        }

        public void setGroupList(List<GroupListBean> groupList) {
            this.groupList = groupList;
        }

        public static class GroupListBean {
            /**
             * channelname : 股市
             * tagType : 1
             * group : 行情
             * id : 0
             */

            private String channelname;
            private String tagType;
            private String group;
            private String id;

            public GroupListBean(String channelname, String tagType, String group, String id) {
                this.channelname = channelname;
                this.tagType = tagType;
                this.group = group;
                this.id = id;
            }

            public String getChannelname() {
                return channelname;
            }

            public void setChannelname(String channelname) {
                this.channelname = channelname;
            }

            public String getTagType() {
                return tagType;
            }

            public void setTagType(String tagType) {
                this.tagType = tagType;
            }

            public String getGroup() {
                return group;
            }

            public void setGroup(String group) {
                this.group = group;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return "GroupListBean{" +
                        "channelname='" + channelname + '\'' +
                        ", tagType='" + tagType + '\'' +
                        ", group='" + group + '\'' +
                        ", id='" + id + '\'' +
                        '}';
            }
        }


    }
}
