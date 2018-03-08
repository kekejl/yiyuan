package com.yiyuaninfo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yiyuaninfo.Fragment.ContentFragment;
import com.yiyuaninfo.Fragment.NewsContentFragment;
import com.yiyuaninfo.entity.ChannelEntity;

import java.util.List;


/**
 * Created by Administrator on 2016/3/30.
 */
public class ChannelNewsAdapter extends FragmentStatePagerAdapter {

    private final FragmentManager mFm;
    private List<NewsContentFragment> fragments;
    private List<ChannelEntity.AllChannelListBean.GroupListBean> mChannels;

    public ChannelNewsAdapter(FragmentManager fm, List<NewsContentFragment> fragments, List<ChannelEntity.AllChannelListBean.GroupListBean> channels) {
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
        return mChannels.get(position).getChannelname();
    }




    @Override
    public int getItemPosition(Object object) {
//        if (mChildCount > 0) {
//            mChildCount--;
            return POSITION_NONE;
//        }
//        return super.getItemPosition(object);
    }

}
