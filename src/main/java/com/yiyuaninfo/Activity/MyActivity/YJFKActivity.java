package com.yiyuaninfo.Activity.MyActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.R;
import com.yiyuaninfo.util.ToastUtils;

/**
 * Created by gaocongcong on 2017/9/26.
 */

public class YJFKActivity    extends BaseActivity{

    private TextView title;
    private Button button;
    private EditText editText;
    @Override
    protected int getContentView() {
        return R.layout.activity_yjfk;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
       setToolBarTitle("意见反馈");
        editText=(EditText)findViewById(R.id.edit_fankui);
        button=(Button) findViewById(R.id.button_fankui);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editText.getText().toString())){
                    ToastUtils.showToast("内容不能为空！");
                }else {
                    ToastUtils.showToast("已提交，非常感谢您的宝贵意见！");
                    finish();
                }

            }
        });
    }
}
