package com.yiyuaninfo.Interface;

import android.os.Bundle;

import com.yiyuaninfo.Fragment.BaseFragment;
import com.yiyuaninfo.R;

/**
 * Created by melo on 2016/11/29.
 */

public class MusicFragment extends BaseFragment {

    private static final String TAG = "MusicFragment";

    public static MusicFragment newInstance(String title) {
        MusicFragment f = new MusicFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_home;
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
}
