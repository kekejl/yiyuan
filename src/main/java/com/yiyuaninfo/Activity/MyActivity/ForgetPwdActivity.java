package com.yiyuaninfo.Activity.MyActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Activity.MainActivity;
import com.yiyuaninfo.Interface.ForgetPwdBiz;
import com.yiyuaninfo.Interface.ForgetPwdCodeBiz;
import com.yiyuaninfo.Interface.Registinfo;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Register;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class ForgetPwdActivity extends BaseActivity {
    private EditText phone,code,pwd;
    private Button button;
    private TextView textView;
    private String  phonestring,codestring,pwdstirng;
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
        return R.layout.activity_forgetpwd;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("忘记密码");
        phone=(EditText)findViewById(R.id.et_forget_phone);
        code=(EditText)findViewById(R.id.et_forget_code);
        pwd=(EditText)findViewById(R.id.et_forget_pwd);
        button=(Button)findViewById(R.id.bt_forget);
        textView=(TextView)findViewById(R.id.tv_forget_getcode);
        listener();
    }

    private void listener() {



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonestring=phone.getText().toString();
                if(CommonUtil.checkPhone(phonestring)){
                    RetrofitUtil.getretrofit().create(ForgetPwdCodeBiz.class).getData(phonestring).enqueue(new Callback<Register>() {
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
                                    ToastUtils.showToast("发送失败,请重试");

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonestring = phone.getText().toString();
                codestring = code.getText().toString();
                pwdstirng = pwd.getText().toString();
                Log.d("忘记密码",CommonUtil.md5(pwdstirng));

                if(CommonUtil.checkRegist(phonestring,codestring,pwdstirng)){
                    getUser();
                }
            }
        });



    }

    private void getUser() {
        //String mima2 = MD5.getMessageDigest(mima.getText().toString().getBytes()).toUpperCase();
        Log.d("修改后密码",CommonUtil.md5(pwdstirng+ Constants.FLAG));
        RetrofitUtil.getretrofit().create(ForgetPwdBiz.class).getData(phonestring, CommonUtil.md5(pwdstirng),codestring).enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                Log.d("忘记密码返回", response.body().getStatus() + "####");
                switch (response.body().getStatus()) {
                    case "1":
                        ToastUtils.showToast("修改成功，请登录");
                        finish();
                        break;
                    case "2":
                        ToastUtils.showToast("验证码超时或错误");

                        break;
                    case "3":
                        ToastUtils.showToast("系统繁忙");
                        break;

                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

            }
        });



    }
}
