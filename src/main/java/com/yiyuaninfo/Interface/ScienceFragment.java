package com.yiyuaninfo.Interface;

import android.os.Bundle;
import android.util.Log;

import com.yiyuaninfo.Fragment.BaseFragment;
import com.yiyuaninfo.R;

/**
 * Created by melo on 2016/11/30.
 */

public class ScienceFragment extends BaseFragment {

    private static final String TAG = "ScienceFragment";

    public static ScienceFragment newInstance(String title) {
        ScienceFragment f = new ScienceFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_science;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        isViewCreated = true;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void lazyLoadData() {
        super.lazyLoadData();
        if (isViewCreated) {
            Log.e(TAG, "lazyLoadData...");
        }
    }

}
