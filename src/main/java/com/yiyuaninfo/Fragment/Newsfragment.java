package com.yiyuaninfo.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yiyuaninfo.Activity.colortrackview.ColorTrackTabLayout;
import com.yiyuaninfo.Listener.OnChannelListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.ChannelPagerAdapter;
import com.yiyuaninfo.adapter.ContentFragmentAdapter;
import com.yiyuaninfo.entity.Channel;
import com.yiyuaninfo.entity.Channeltab;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.ConstanceValue;
import com.yiyuaninfo.util.SharedPreferencesMgr;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yiyuaninfo.util.ConstanceValue.TITLE_SELECTED;
import static com.yiyuaninfo.util.ConstanceValue.TITLE_UNSELECTED;

/**
 * Created by Administrator on 2017/2/23.
 */

public class Newsfragment extends BaseFragment implements OnChannelListener {
    @BindView(R.id.news_viewpager)
    ViewPager  viewPager;
    @BindView(R.id.magic_news)
    MagicIndicator magicIndicator;
    @BindView(R.id.imageAddChannle)
    ImageView  imageView;
    @BindView(R.id.ColorTrackTabLayout)
    ColorTrackTabLayout  colorTrackTabLayout;
    @BindView(R.id.icon_category)
    ImageView  iconCategory;
    //选中的频道
    public List<Channeltab> mSelectedDatas = new ArrayList<>();
    //未被选中的
    public List<Channeltab> mUnSelectedDatas = new ArrayList<>();
    private Gson mGson = new Gson();
    private List<Fragment> mFragments;
    private ChannelPagerAdapter  mTitlePagerAdapter;
    public static List<?> images = new ArrayList<>();


    private List<Channel>  channelList=new ArrayList<>();
    private ArrayList<Fragment>  fragments=new ArrayList<>();
   // private ContentFragmentAdapter   adapter;
    private static final String[] CHANNELS = new String[]{"推荐", "行情", "要闻", "时政", "社会", "国际", "军事", "教育", "科技", "体育", "视频", "电影", "音乐", "演出", "游戏", "段子"
            , "时尚", "美食", "健康", "家居", "购物", "丽人", "汽车", "旅游", "情感"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    public static Newsfragment newInstance(String title) {


        Newsfragment f = new Newsfragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {


    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_news;
    }


    @Override
    protected void initView() {
       /* initdatachannel();
        initfragment();
        initMagicIndicator1();*/



        getTitleData();
        mFragments = new ArrayList<>();
        for (int i = 0; i < mSelectedDatas.size(); i++) {
            ContentFragment fragment =ContentFragment.newInstance(mSelectedDatas.get(i).TitleCode);
            mFragments.add(fragment);
        }
        mTitlePagerAdapter = new ChannelPagerAdapter(getChildFragmentManager(), mFragments, mSelectedDatas);
        viewPager.setAdapter(mTitlePagerAdapter);
        //限定预加载的页面的个数
        viewPager.setOffscreenPageLimit(mSelectedDatas.size());

        colorTrackTabLayout.setTabPaddingLeftAndRight(CommonUtil.dip2px(12), CommonUtil.dip2px(12));
        colorTrackTabLayout.setupWithViewPager(viewPager);
        colorTrackTabLayout.post(new Runnable() {
            @Override
            public void run() {
                //设置最小宽度，使其可以在滑动一部分距离
                ViewGroup slidingTabStrip = (ViewGroup) colorTrackTabLayout.getChildAt(0);
                slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth() + iconCategory.getMeasuredWidth());
            }
        });
        //隐藏指示器
        colorTrackTabLayout.setSelectedTabIndicatorHeight(5);


        /*FragmentManager fragmentManager=getFragmentManager();
        adapter adapter=new adapter( fragmentManager,getActivity());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);*/

    }

    @OnClick({R.id.imageAddChannle,R.id.icon_category})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.imageAddChannle:
                //getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.icon_category:
                ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance(mSelectedDatas, mUnSelectedDatas);
                dialogFragment.setOnChannelListener(this);
                dialogFragment.show(getChildFragmentManager(), "CHANNEL");
                dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //mVp.setOffscreenPageLimit(mSelectedDatas.size());
                        mTitlePagerAdapter.notifyDataSetChanged();
                        colorTrackTabLayout.setCurrentItem(colorTrackTabLayout.getSelectedTabPosition());
                        ViewGroup slidingTabStrip = (ViewGroup) colorTrackTabLayout.getChildAt(0);
                        //注意：因为最开始设置了最小宽度，所以重新测量宽度的时候一定要先将最小宽度设置为0
                        slidingTabStrip.setMinimumWidth(0);
                        slidingTabStrip.measure(0, 0);
                        slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth() + iconCategory.getMeasuredWidth());

                        //保存选中和未选中的channel
                        SharedPreferencesMgr.setString(ConstanceValue.TITLE_SELECTED, mGson.toJson(mSelectedDatas));
                        SharedPreferencesMgr.setString(ConstanceValue.TITLE_UNSELECTED, mGson.toJson(mUnSelectedDatas));

                    }
                });
                break;
        }
    }
     /**
     * 获取标题数据
     */
    private void getTitleData() {

        String selectTitle = SharedPreferencesMgr.getString(TITLE_SELECTED, "");
        String unselectTitle = SharedPreferencesMgr.getString(TITLE_UNSELECTED, "");
        if (TextUtils.isEmpty(selectTitle) || TextUtils.isEmpty(unselectTitle)) {
            //本地没有title
            String[] titleStr = getResources().getStringArray(R.array.home_title);
            String[] titlesCode = getResources().getStringArray(R.array.home_title_code);
            //默认添加了全部频道
            for (int i = 0; i < titlesCode.length; i++) {
                String t = titleStr[i];
                String code = titlesCode[i];
                mSelectedDatas.add(new Channeltab(t, code));
            }

            String selectedStr = mGson.toJson(mSelectedDatas);
            com.orhanobut.logger.Logger.d(selectedStr);
            SharedPreferencesMgr.setString(TITLE_SELECTED, selectedStr);
        } else {
            //之前添加过
            List<Channeltab> selecteData = mGson.fromJson(selectTitle, new TypeToken<List<Channeltab>>() {

            }.getType());
            com.orhanobut.logger.Logger.d(selecteData.toString());
            List<Channeltab> unselecteData = mGson.fromJson(unselectTitle, new TypeToken<List<Channeltab>>() {
            }.getType());
            com.orhanobut.logger.Logger.d(unselecteData.toString());
            mSelectedDatas.addAll(selecteData);
            mUnSelectedDatas.addAll(unselecteData);
        }

    }
    @Override
    public void onItemMove(int starPos, int endPos) {
        listMove(mSelectedDatas, starPos, endPos);
        listMove(mFragments, starPos, endPos);
    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {
        //移动到我的频道
        Channeltab channel = mUnSelectedDatas.remove(starPos);
        mSelectedDatas.add(endPos, channel);
        mFragments.add(ContentFragment.newInstance(channel.TitleCode));
    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {
        //移动到推荐频道
        mUnSelectedDatas.add(endPos, mSelectedDatas.remove(starPos));
        mFragments.remove(starPos);
    }
    private void listMove(List datas, int starPos, int endPos) {
        Object o = datas.get(starPos);
        //先删除之前的位置
        datas.remove(starPos);
        //添加到现在的位置
        datas.add(endPos, o);
    }
}
