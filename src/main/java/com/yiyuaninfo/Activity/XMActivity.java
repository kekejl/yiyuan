package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

import com.yiyuaninfo.Activity.colortrackview.ColorTrackTabLayout;
import com.yiyuaninfo.Fragment.ContentFragment;
import com.yiyuaninfo.Fragment.XMFragment;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.ChannelInfoAdapter;
import com.yiyuaninfo.adapter.FindFragmentAdapter;
import com.yiyuaninfo.entity.ChannelEntity;
import com.yiyuaninfo.util.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class XMActivity extends BaseActivity {

    //@BindView(R.id.ColorTrackTabLayout_hq)
    ColorTrackTabLayout colorTrackTabLayout;
    // @BindView(R.id.view_pager_hq)
    ViewPager viewPager;
    private List<XMFragment> xmFragmentList=new ArrayList<>();
    private FindFragmentAdapter channelInfoAdapter;
    private List<ChannelEntity.AllChannelListBean.GroupListBean>   groupListBeen;

    private static final String[] CHANNELS = new String[]{"推荐", "房产", "移民", "旅游", "教育", "文化", "科技", "汽车"};
    private List<String> ChannelsList = Arrays.asList(CHANNELS);
    @Override
    protected int getContentView() {
        return R.layout.activity_xm;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Intent intent = getIntent();
        colorTrackTabLayout = (ColorTrackTabLayout) findViewById(R.id.ColorTrackTabLayout_xm);
        viewPager = (ViewPager) findViewById(R.id.view_pager_xm);

        setToolBarTitle("项目");
        ChannelEntity entity = CommonUtil.analysisJsonFile(this, "channel");
        groupListBeen=entity.getAllChannelList().get(intent.getExtras().getInt("id")).getGroupList();
        Log.d("项目频道列表",groupListBeen.toString());

        ChannelEntity.AllChannelListBean.GroupListBean  xiangmu=new  ChannelEntity.AllChannelListBean.GroupListBean("推荐","00","项目","0");
        groupListBeen.add(0,xiangmu);
        Log.d("项目频道列表",groupListBeen.toString());

        for (int i = 0; i < ChannelsList.size(); i++) {
            Log.d("项目频道列表ID",groupListBeen.get(i).getTagType());
            XMFragment fragment = XMFragment.newInstance(groupListBeen.get(i).getTagType());
            xmFragmentList.add(fragment);
        }
        channelInfoAdapter = new FindFragmentAdapter(getSupportFragmentManager(), xmFragmentList, ChannelsList);
        viewPager.setAdapter(channelInfoAdapter);
        //限定预加载的页面的个数
        viewPager.setOffscreenPageLimit(ChannelsList.size());

        colorTrackTabLayout.setTabPaddingLeftAndRight(CommonUtil.dip2px(12), CommonUtil.dip2px(12));
        colorTrackTabLayout.setupWithViewPager(viewPager);
        colorTrackTabLayout.post(new Runnable() {
            @Override
            public void run() {
                //设置最小宽度，使其可以在滑动一部分距离
                ViewGroup slidingTabStrip = (ViewGroup) colorTrackTabLayout.getChildAt(0);
                slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth());
            }
        });
        //隐藏指示器
        colorTrackTabLayout.setSelectedTabIndicatorHeight(5);
        colorTrackTabLayout.setCurrentItem(Integer.parseInt(intent.getExtras().getString("channelid")));

    }
    }
