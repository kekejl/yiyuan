package com.yiyuaninfo.Activity;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.yiyuaninfo.Activity.colortrackview.ColorTrackTabLayout;
import com.yiyuaninfo.App;
import com.yiyuaninfo.Fragment.ContentFragment;
import com.yiyuaninfo.Fragment.NewsContentFragment;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.ChannelInfoAdapter;
import com.yiyuaninfo.adapter.ChannelNewsAdapter;
import com.yiyuaninfo.adapter.ChannelPagerAdapter;
import com.yiyuaninfo.entity.ChannelEntity;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.CommonUtils;
import com.yiyuaninfo.view.TabPageIndicator;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/2/24.
 */

public class  HQActivity extends BaseActivity {

    //@BindView(R.id.ColorTrackTabLayout_hq)
    ColorTrackTabLayout colorTrackTabLayout;
   // @BindView(R.id.view_pager_hq)
    ViewPager  viewPager;
    private List<NewsContentFragment> newsContentFragments=new ArrayList<>();
    private ChannelNewsAdapter channelNewsAdapter;
    private List<ChannelEntity.AllChannelListBean.GroupListBean>   groupListBeen;
    @Override
    protected int getContentView() {
        return R.layout.activity_hq;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
         Intent  intent=getIntent();
        colorTrackTabLayout=(ColorTrackTabLayout) findViewById(R.id.ColorTrackTabLayout_hq);
        viewPager=(ViewPager)findViewById(R.id.view_pager_hq);

        ChannelEntity entity = CommonUtil.analysisJsonFile(this, "channel");
        groupListBeen=entity.getAllChannelList().get(intent.getExtras().getInt("id")).getGroupList();
        setToolBarTitle(entity.getAllChannelList().get(intent.getExtras().getInt("id")).getGroupName());


        for (int i = 0; i < groupListBeen.size();i++) {
            NewsContentFragment fragment =NewsContentFragment.newInstance(groupListBeen.get(i).getTagType());
            newsContentFragments.add(fragment);
        }
        channelNewsAdapter = new ChannelNewsAdapter(getSupportFragmentManager(), newsContentFragments, groupListBeen);
        viewPager.setAdapter(channelNewsAdapter);
        //限定预加载的页面的个数
        viewPager.setOffscreenPageLimit(groupListBeen.size());

        //viewPager.setCurrentItem(Integer.parseInt(intent.getExtras().getString("channelid")));

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
