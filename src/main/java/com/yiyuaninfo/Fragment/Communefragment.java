package com.yiyuaninfo.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.yiyuaninfo.App;
import com.yiyuaninfo.R;
import com.yiyuaninfo.util.CommonUtils;
import com.yiyuaninfo.util.FragmentFactory;
import com.yiyuaninfo.view.TabPageIndicator;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Administrator on 2017/2/24.
 */

public class Communefragment extends BaseFragment  {
    @BindView(R.id.tabPageIndicator)
    TabPageIndicator   indicator;
    @BindView(R.id.viewPagerCommune)
    ViewPager  viewPager;


    public static Communefragment newInstance(String title) {
        Communefragment f = new Communefragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){
            JCVideoPlayer.releaseAllVideos();

        }

        super.onHiddenChanged(hidden);
    }



    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        BasePagerAdapter adapter = new BasePagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        indicator.setViewPager(viewPager);
        setTabPagerIndicator();
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_commune;
    }

    @OnClick({ }  )
    public void  onClick( View view ){
        switch (view.getId()){



        }


    }

    private void setTabPagerIndicator() {
        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_SAME);// 设置模式，一定要先设置模式
        indicator.setDividerColor(Color.parseColor("#ffffff"));// 设置分割线的颜色
        indicator.setDividerPadding(CommonUtils.dip2px(App.getContext(), 10));
        indicator.setIndicatorColor(Color.parseColor("#b81c22"));// 设置底部导航线的颜色
        indicator.setTextColorSelected(Color.parseColor("#b81c22"));// 设置tab标题选中的颜色
        indicator.setTextColor(Color.parseColor("#000000"));// 设置tab标题未被选中的颜色
        indicator.setTextSize(CommonUtils.sp2px(App.getContext(), 16));// 设置字体大小
    }

    @Override
    public void onPause() {
        super.onPause();
       // JCVideoPlayer.releaseAllVideos();
    }

    class BasePagerAdapter extends FragmentStatePagerAdapter {
        String[] titles;

        public BasePagerAdapter(FragmentManager fm) {
            super(fm);
            this.titles = CommonUtils.getStringArray(R.array.no_expand_titles);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createForNoExpand(position);
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
