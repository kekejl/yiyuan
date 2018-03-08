package com.yiyuaninfo.Interface;

import android.os.Bundle;

import com.yiyuaninfo.Fragment.BaseFragment;
import com.yiyuaninfo.R;


/**
 * Created by melo on 2016/11/29.
 */

public class SettingFragment extends BaseFragment {

    private static final String TAG = "SettingFragment";
    private static final int PER_REQUEST_CODE = 2;

    public static SettingFragment newInstance(String title) {
        SettingFragment f = new SettingFragment();
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
    protected void initView() {

    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_setting;
    }


}
