package com.yiyuaninfo.Activity.MyActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.R;
import com.yiyuaninfo.util.ToastUtils;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/24.
 */

public class AccountActivity extends BaseActivity {
    private   LinearLayout  phone,pwd;
    @Override
    protected int getContentView() {
        return R.layout.activity_account;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("账号与安全");
       phone=(LinearLayout)findViewById(R.id.ll_account_phone);
       pwd=(LinearLayout)findViewById(R.id.ll_account_pwd);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, PhoneActivity.class));
                AccountActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, PwdActivity.class));
                AccountActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
    }
}