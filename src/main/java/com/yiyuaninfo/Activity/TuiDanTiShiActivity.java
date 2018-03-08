package com.yiyuaninfo.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.yiyuaninfo.Interface.BackOrderBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by gaocongcong on 2017/8/14.
 */

public  class TuiDanTiShiActivity extends  BaseActivity {
    Button  button;
    @Override
    protected int getContentView() {
        return R.layout.activity_tuidantishi;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        setToolBarTitle("申请终止服务");
        button=(Button)findViewById(R.id.btn_tuidantishi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

}
}
