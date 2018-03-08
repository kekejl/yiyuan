package com.yiyuaninfo.Activity.MyActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.App;
import com.yiyuaninfo.Fragment.MyOrderFragment;
import com.yiyuaninfo.Fragment.TGFragment;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.FindFragmentAdapter;
import com.yiyuaninfo.util.CommonUtils;
import com.yiyuaninfo.view.TabPageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MyOrderActivity extends BaseActivity {
    ViewPager viewPager;
    private TabPageIndicator tabPageIndicator;

    private List<MyOrderFragment> TGFragmentList =new ArrayList<>();
    private FindFragmentAdapter findFragmentAdapter;
    //private static final String[] CHANNELS = new String[]{ "已完成订单","未完成订单"};
    private static final String[] CHANNELS = new String[]{ "已完成订单"};
    private List<String> ChannelsList = Arrays.asList(CHANNELS);
    @Override
    protected int getContentView() {
        return R.layout.activity_order;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("我的订单");
        viewPager = (ViewPager) findViewById(R.id.view_pager_order);
        tabPageIndicator=(TabPageIndicator)findViewById(R.id.tabPageIndicator_myorder);
        tabPageIndicator.setVisibility(View.GONE);

        for (int i = 0; i < ChannelsList.size(); i++) {
            Log.d("三找",ChannelsList.get(i));
            MyOrderFragment fragment = MyOrderFragment.newInstance(ChannelsList.get(i));
            TGFragmentList.add(fragment);
        }
        findFragmentAdapter = new FindFragmentAdapter(getSupportFragmentManager(), TGFragmentList, ChannelsList);
        viewPager.setAdapter(findFragmentAdapter);
        //限定预加载的页面的个数
        viewPager.setOffscreenPageLimit(ChannelsList.size());
        tabPageIndicator.setViewPager(viewPager);
        setTabPagerIndicator();
    }
    private void setTabPagerIndicator() {
       // tabPageIndicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_NOSAME);// 设置模式，一定要先设置模式
       // tabPageIndicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);// 设置模式，一定要先设置模式
        //tabPageIndicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_NOSAME);// 设置模式，一定要先设置模式
        tabPageIndicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_SAME);// 设置模式，一定要先设置模式
       // tabPageIndicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_NOEXPAND_NOSAME);// 设置模式，一定要先设置模式
       // tabPageIndicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_NOEXPAND_SAME);// 设置模式，一定要先设置模式
        tabPageIndicator.setDividerColor(Color.parseColor("#ffffff"));// 设置分割线的颜色
        tabPageIndicator.setDividerPadding(CommonUtils.dip2px(App.getContext(), 10));
        tabPageIndicator.setIndicatorColor(Color.parseColor("#b81c22"));// 设置底部导航线的颜色

        tabPageIndicator.setTextColorSelected(Color.parseColor("#b81c22"));// 设置tab标题选中的颜色
        tabPageIndicator.setTextColor(Color.parseColor("#000000"));// 设置tab标题未被选中的颜色
        tabPageIndicator.setTextSize(CommonUtils.sp2px(App.getContext(), 16));// 设置字体大小
    }

}
