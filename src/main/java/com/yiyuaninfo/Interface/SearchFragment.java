package com.yiyuaninfo.Interface;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yiyuaninfo.Fragment.BaseFragment;
import com.yiyuaninfo.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by melo on 2016/11/29.
 */

public class SearchFragment extends BaseFragment {

    private static final String TAG = "SearchFragment";
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_fragment_search)
    ViewPager mViewPager;

    private List<Fragment> fragmentList;

    public static SearchFragment newInstance(String title) {
        SearchFragment f = new SearchFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }


    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_search;
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {


    }



}
