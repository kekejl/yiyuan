package com.yiyuaninfo.util;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.yiyuaninfo.Fragment.Fragment1;
import com.yiyuaninfo.Fragment.Geniusfragment;
import com.yiyuaninfo.Fragment.Hotfragment;
import com.yiyuaninfo.Fragment.Mediafragment;
import com.yiyuaninfo.Fragment.MsgListFragment;
import com.yiyuaninfo.Fragment.NewsContentFragment;
import com.yiyuaninfo.Fragment.Questionsfragment;
import com.yiyuaninfo.Fragment.Vediofragment;
import com.yiyuaninfo.Interface.GetMoreData;
import com.yiyuaninfo.view.WrapContentHeightViewPager;


/**
 * Created by shan_yao on 2016/6/17.
 */
public class FragmentFactory {
    public static Fragment createForNoExpand(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                Log.d("执行了0次","执行了0次");
                    fragment = new Mediafragment();

                break;
            case 1:
                Log.d("执行了0次","执行了1次");

                // fragment = NewsContentFragment.newInstance("20");
                fragment = new Vediofragment();
                break;
            case 2:
                Log.d("执行了0次","执行了2次");

                fragment = new Questionsfragment();
                break;
        }
        return fragment;
    }
    public static Fragment createhome(int position, WrapContentHeightViewPager  vp) {
        Fragment fragment = null;
        switch (position) {
            case 0:
             Hotfragment   hotfragment = new Hotfragment();
              // hotfragment.setViewPage(vp,0);
                fragment=hotfragment;

                break;
            case 1:
            Geniusfragment    geniusfragment = new Geniusfragment();
               // geniusfragment.setViewPage(vp,1);
                fragment=geniusfragment;
                break;

        }
        return fragment;
    }
    public static Fragment createhome(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                Hotfragment   hotfragment = new Hotfragment();
                fragment=hotfragment;

                break;
            case 1:
                Geniusfragment    geniusfragment = new Geniusfragment();
                fragment=geniusfragment;
                break;

        }
        return fragment;
    }
    public static Fragment creatmsglist(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment=MsgListFragment.newInstance("1");


                break;
            case 1:
                fragment=MsgListFragment.newInstance("2");

                break;

        }
        return fragment;
    }



}
