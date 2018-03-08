package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.yiyuaninfo.Activity.colortrackview.ColorTrackTabLayout;
import com.yiyuaninfo.Fragment.ContentFragment;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.ChannelInfoAdapter;
import com.yiyuaninfo.util.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class RDActivity extends BaseActivity {

    //@BindView(R.id.ColorTrackTabLayout_hq)
    ColorTrackTabLayout colorTrackTabLayout;
    // @BindView(R.id.view_pager_hq)
    ViewPager viewPager;
    private List<ContentFragment> contentFragmentList = new ArrayList<>();
    private ChannelInfoAdapter channelInfoAdapter;
    private static final String[] CHANNELS = new String[]{"要闻", "时政", "社会", "国际", "军事", "教育", "科技", "体育"};
    private List<String> ChannelsList = Arrays.asList(CHANNELS);

    @Override
    protected int getContentView() {
        return R.layout.activity_rd;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        Intent intent = getIntent();
        colorTrackTabLayout = (ColorTrackTabLayout) findViewById(R.id.ColorTrackTabLayout_rd);
        viewPager = (ViewPager) findViewById(R.id.view_pager_rd);

        setToolBarTitle("热点");

        for (int i = 0; i < ChannelsList.size(); i++) {
            ContentFragment fragment = ContentFragment.newInstance("1");
            contentFragmentList.add(fragment);
        }
        channelInfoAdapter = new ChannelInfoAdapter(getSupportFragmentManager(), contentFragmentList, ChannelsList);
        viewPager.setAdapter(channelInfoAdapter);
        //限定预加载的页面的个数
        //viewPager.setOffscreenPageLimit(ChannelsList.size());

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