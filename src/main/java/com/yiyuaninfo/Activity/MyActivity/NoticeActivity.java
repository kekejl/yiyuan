package com.yiyuaninfo.Activity.MyActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/24.
 */

public class NoticeActivity extends BaseActivity {


    @Override
    protected int getContentView() {
        return R.layout.activity_notice;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("公告");
    }

}
