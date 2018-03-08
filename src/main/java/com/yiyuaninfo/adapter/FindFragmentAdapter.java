package com.yiyuaninfo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yiyuaninfo.Fragment.TGFragment;

import java.util.List;


/**
 * Created by Administrator on 2016/3/30.
 */
public class FindFragmentAdapter extends FragmentStatePagerAdapter {

    List<  ? extends Fragment> fragments;
    private List<String> mChannels;

    public FindFragmentAdapter(FragmentManager fm, List<? extends Fragment> fragments, List<String> channels) {
        super(fm);
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
        return mChannels.get(position);
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
