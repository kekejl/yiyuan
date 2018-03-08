package com.yiyuaninfo.Activity.MyActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.App;
import com.yiyuaninfo.Fragment.Homefragment;
import com.yiyuaninfo.R;
import com.yiyuaninfo.util.CommonUtils;
import com.yiyuaninfo.util.FragmentFactory;
import com.yiyuaninfo.view.TabPageIndicator;
import com.yiyuaninfo.view.WrapContentHeightViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MessageActivity extends BaseActivity {
    private WrapContentHeightViewPager  viewPager;
    private TabPageIndicator  indicator;

    @Override
    protected int getContentView() {
        return R.layout.activity_message;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("消息推送记录");
        viewPager=(WrapContentHeightViewPager)findViewById(R.id.wv);
        indicator=(TabPageIndicator)findViewById(R.id.tab);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        indicator.setViewPager(viewPager);
        setTabPagerIndicator();
    }
    /**
     * tab指示器
     */
    private void setTabPagerIndicator() {
        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_NOSAME);// 设置模式，一定要先设置模式
        indicator.setDividerColor(Color.parseColor("#b81c22"));// 设置分割线的颜色
        indicator.setDividerPadding(CommonUtils.dip2px(App.getContext(), 10));
        indicator.setIndicatorColor(Color.parseColor("#b81c22"));// 设置底部导航线的颜色
        indicator.setTextColorSelected(Color.parseColor("#b81c22"));// 设置tab标题选中的颜色
        indicator.setTextColor(Color.parseColor("#797979"));// 设置tab标题未被选中的颜色
        indicator.setTextSize(CommonUtils.sp2px(App.getContext(), 16));// 设置字体大小
    }
    /**
     * Fragment适配器
     */

    class PagerAdapter extends FragmentStatePagerAdapter {
        String[] titles;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
            this.titles = CommonUtils.getStringArray(R.array.home_titles);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createhome(position,viewPager);
        }

        @Override
        public int getCount() {
            return titles.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


}
