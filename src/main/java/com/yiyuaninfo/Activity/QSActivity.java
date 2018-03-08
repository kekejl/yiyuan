package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.yiyuaninfo.Activity.colortrackview.ColorTrackTabLayout;
import com.yiyuaninfo.App;
import com.yiyuaninfo.Fragment.ContentFragment;
import com.yiyuaninfo.Fragment.QSFragment;
import com.yiyuaninfo.Fragment.TGFragment;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.ChannelInfoAdapter;
import com.yiyuaninfo.adapter.FindFragmentAdapter;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.CommonUtils;
import com.yiyuaninfo.view.TabPageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class QSActivity extends BaseActivity {

    //@BindView(R.id.ColorTrackTabLayout_hq)
   // ColorTrackTabLayout colorTrackTabLayout;
    // @BindView(R.id.view_pager_hq)
    private TabPageIndicator tabPageIndicator;

    ViewPager viewPager;
    private List<QSFragment> QSFragmentList =new ArrayList<>();
    private FindFragmentAdapter findFragmentAdapter;
    private static final String[] CHANNELS = new String[]{"全部","QDII/QFII", "交易", "资管"};
    private List<String> ChannelsList = Arrays.asList(CHANNELS);

    @Override
    protected int getContentView() {
        return R.layout.activity_qs;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Intent intent = getIntent();
        tabPageIndicator=(TabPageIndicator)findViewById(R.id.tabPageIndicator_qs);

        // colorTrackTabLayout = (ColorTrackTabLayout) findViewById(R.id.ColorTrackTabLayout_qs);
        viewPager = (ViewPager) findViewById(R.id.view_pager_qs);

        setToolBarTitle("券商");

        for (int i = 0; i < ChannelsList.size(); i++) {
            QSFragment fragment = QSFragment.newInstance(ChannelsList.get(i));
            QSFragmentList.add(fragment);
        }
        findFragmentAdapter = new FindFragmentAdapter(getSupportFragmentManager(), QSFragmentList, ChannelsList);
        viewPager.setAdapter(findFragmentAdapter);
        //限定预加载的页面的个数
        viewPager.setOffscreenPageLimit(ChannelsList.size());
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
//        colorTrackTabLayout.setCurrentItem(Integer.parseInt(intent.getExtras().getString("channelid")));



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
}