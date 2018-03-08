package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiyuaninfo.Activity.colortrackview.ColorTrackTabLayout;
import com.yiyuaninfo.Fragment.QSFragment;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.FindFragmentAdapter;
import com.yiyuaninfo.util.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class WenActivity extends BaseActivity {

     private TextView  textView;
    @Override
    protected int getContentView() {
        return R.layout.fankui_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("提问");

        textView=(TextView)findViewById(R.id.tv_wen_fankui);
        textView.setText(Html.fromHtml("\"<p>\n" +
                " dkawnlkdjalkwjdkj2\n" +
                "</p >\n" +
                "<p>\n" +
                " 打我打我打大威德啊我打完\n" +
                "</p >\n" +
                "<p>\n" +
                " 111111111111\n" +
                "</p >\n" +
                "<p>\n" +
                " 打我打我\n" +
                "</p >\n" +
                "<p>\n" +
                " <br />\n" +
                "</p >\""));
    }
}