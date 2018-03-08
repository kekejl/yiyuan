package com.yiyuaninfo.Activity.MyActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Interface.RegisterBiz;
import com.yiyuaninfo.Interface.XiuGaiPhoeBiz;
import com.yiyuaninfo.Interface.XuiGaiPgoneCodeBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Register;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class PhoneActivity extends BaseActivity {
    private EditText  etphone,etcode;
    private TextView  textView;
    private Button  button;
    private String  phonestring;
    private String  code;
    private CountDownTimer countDownTimer=new CountDownTimer(60000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText("重新发送" + millisUntilFinished / 1000 + "s");
            textView.setEnabled(false);
            textView.setTextColor(Color.parseColor("#8B8B8B"));
        }

        @Override
        public void onFinish() {
            textView.setText("重新获取验证");
            textView.setEnabled(true);
            textView.setTextColor(Color.parseColor("#000000"));

        }
    };
    @Override
    protected int getContentView() {
        return R.layout.activity_phone;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("修改手机号");
        etphone=(EditText)findViewById(R.id.et_phone_phone);
        etcode=(EditText)findViewById(R.id.et_phone_code);
        textView=(TextView)findViewById(R.id.tv_phone_getcode);
        button=(Button)findViewById(R.id.btn_phone_true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonestring=etphone.getText().toString();
                 code=etcode.getText().toString();
                if(CommonUtil.checkPhone(phonestring)||CommonUtil.checkCode(code)){
                   RetrofitUtil.getretrofit().create(XiuGaiPhoeBiz.class).getData("modphone",phonestring, SPUtil.getUser(PhoneActivity.this).getUserid(),code)
                    .enqueue(new Callback<Register>() {
                        @Override
                        public void onResponse(Call<Register> call, Response<Register> response) {
                            Log.d("修改手机",response.body().toString());
                             switch (response.body().getStatus()){
                                 case "0":
                                     ToastUtils.showToast("输入不能为空");
                                     break;
                                 case "1":
                                     ToastUtils.showToast("修改成功");
                                     break;
                                 case "2":
                                     ToastUtils.showToast("系统繁忙");
                                     break;
                                 case "3":
                                     ToastUtils.showToast("验证码超时");
                                     break;
                                 case "4":
                                     ToastUtils.showToast("验证码错误");
                                     break;

                             }
                        }

                        @Override
                        public void onFailure(Call<Register> call, Throwable t) {

                        }
                    });
                }

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonestring=etphone.getText().toString();
                if(CommonUtil.checkPhone(phonestring)){
                    RetrofitUtil.getretrofit().create(XuiGaiPgoneCodeBiz.class).getData("check",phonestring).enqueue(new Callback<Register>() {
                        @Override
                        public void onResponse(Call<Register> call, Response<Register> response) {
                            Log.d("获取验证码",response.body().getStatus());
                            switch (response.body().getStatus()){
                                case "1":

                                    ToastUtils.showToast("发送成功，请注意查收");
                                    //时间倒计时
                                    countDownTimer.start();
                                    break;
                                case "0":
                                    ToastUtils.showToast("发送失败");

                                    break;
                                case "2":
                                    ToastUtils.showToast("该手机号已经注册");

                                    break;
                            }
                        }

                        @Override
                        public void onFailure(Call<Register> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }

}