package com.yiyuaninfo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yiyuaninfo.entity.Channeltab;

import java.util.List;


/**
 * Created by Administrator on 2016/3/30.
 */
public class ChannelPagerAdapter extends FragmentStatePagerAdapter {

    private final FragmentManager mFm;
    private List<Fragment> fragments;
    private List<Channeltab> mChannels;
    private int mChildCount;
    private boolean[] fragmentsUpdateFlag;

    public ChannelPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<Channeltab> channels) {
        super(fm);
        mFm = fm;
        this.fragments = fragments;
        this.mChannels = channels;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return mChannels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels == null ? "" : mChannels.get(position).Title;
    }

//    @Override
//    public void notifyDataSetChanged() {
//        mChildCount = getCount();
//        super.notifyDataSetChanged();
//    }



    @Override
    public int getItemPosition(Object object) {
//        if (mChildCount > 0) {
//            mChildCount--;
            return POSITION_NONE;
//        }
//        return super.getItemPosition(object);
    }

}
