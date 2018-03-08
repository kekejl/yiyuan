package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.yiyuaninfo.App;
import com.yiyuaninfo.Fragment.CPFragment;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.FindFragmentAdapter;
import com.yiyuaninfo.util.CommonUtils;
import com.yiyuaninfo.view.TabPageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class CPActivity extends BaseActivity {

    //@BindView(R.id.ColorTrackTabLayout_hq)
    //ColorTrackTabLayout colorTrackTabLayout;
    // @BindView(R.id.view_pager_hq)
    private TabPageIndicator  tabPageIndicator;
    ViewPager viewPager;
    private List<CPFragment> CPFragmentList=new ArrayList<>();
    private FindFragmentAdapter findFragmentAdapter;
    private static final String[] CHANNELS = new String[]{ "产品分类","公司分类"};
    private List<String> ChannelsList = Arrays.asList(CHANNELS);
    @Override
    protected int getContentView() {
        return R.layout.activity_cp;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Intent intent=getIntent();
        //colorTrackTabLayout=(ColorTrackTabLayout) findViewById(R.id.ColorTrackTabLayout_cp);
        tabPageIndicator=(TabPageIndicator)findViewById(R.id.tabPageIndicator_cp);
        viewPager=(ViewPager)findViewById(R.id.view_pager_cp);

        setToolBarTitle("产品");

//        for (int i = 0; i < ChannelsList.size(); i++) {
//            CPFragment fragment =CPFragment.newInstance(ChannelsList.get(i));
//            CPFragmentList.add(fragment);
//        }
//        findFragmentAdapter = new FindFragmentAdapter(getSupportFragmentManager(), CPFragmentList, ChannelsList);
//        viewPager.setAdapter(findFragmentAdapter);
//        //限定预加载的页面的个数
//        viewPager.setOffscreenPageLimit(ChannelsList.size());

        BasePagerAdapter adapter = new BasePagerAdapter((getSupportFragmentManager()));
        viewPager.setAdapter(adapter);
        tabPageIndicator.setViewPager(viewPager);
        setTabPagerIndicator();
        viewPager.setCurrentItem(Integer.parseInt(intent.getExtras().getString("channelid")));
//        colorTrackTabLayout.setTabPaddingLeftAndRight(CommonUtil.dip2px(12), CommonUtil.dip2px(12));
//        colorTrackTabLayout.setupWithViewPager(viewPager);
//        colorTrackTabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                //设置最小宽度，使其可以在滑动一部分距离
//                ViewGroup slidingTabStrip = (ViewGroup) colorTrackTabLayout.getChildAt(0);
//                slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth());
//            }
//        });
//        //隐藏指示器
//        colorTrackTabLayout.setSelectedTabIndicatorHeight(5);


    }

    private void setTabPagerIndicator() {
        tabPageIndicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_SAME);// 设置模式，一定要先设置模式
        tabPageIndicator.setDividerColor(Color.parseColor("#ffffff"));// 设置分割线的颜色
        tabPageIndicator.setDividerPadding(CommonUtils.dip2px(App.getContext(), 10));
        tabPageIndicator.setIndicatorColor(Color.parseColor("#b81c22"));// 设置底部导航线的颜色
        tabPageIndicator.setTextColorSelected(Color.parseColor("#b81c22"));// 设置tab标题选中的颜色
        tabPageIndicator.setTextColor(Color.parseColor("#000000"));// 设置tab标题未被选中的颜色
        tabPageIndicator.setTextSize(CommonUtils.sp2px(App.getContext(), 16));// 设置字体大小
    }
    class BasePagerAdapter extends FragmentStatePagerAdapter {
        String[] titles;

        public BasePagerAdapter(FragmentManager fm) {
            super(fm);
            this.titles =CHANNELS;
        }

        @Override
        public Fragment getItem(int position) {
                        CPFragment fragment =CPFragment.newInstance(CHANNELS[position]);
            return fragment;
        }

        @Override
        public int getCount() {
            return CHANNELS.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CHANNELS[position];
        }
    }
}
