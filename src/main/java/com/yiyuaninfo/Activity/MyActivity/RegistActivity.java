package com.yiyuaninfo.Activity.MyActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Activity.MainActivity;
import com.yiyuaninfo.Activity.WebViewActivity;
import com.yiyuaninfo.Interface.RegisterBiz;
import com.yiyuaninfo.Interface.Registinfo;
import com.yiyuaninfo.Interface.UserIdBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Register;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.entity.UserId;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class RegistActivity extends BaseActivity {
    private EditText  phone,code,pwd;
    private Button button;
    private TextView  textView;
    private String  phonestring,codestring,pwdstirng;
    private LinearLayout   linearLayout;
    private ImageView   ivphone,ivcode,ivpwd;
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
        return R.layout.activity_regist;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("注册");
        phone=(EditText)findViewById(R.id.et_regist_phone);
        code=(EditText)findViewById(R.id.et_regist_code);
        pwd=(EditText)findViewById(R.id.et_regist_pwd);
        button=(Button)findViewById(R.id.bt_regist);
        textView=(TextView)findViewById(R.id.tv_regist_getcode );
        linearLayout=(LinearLayout)findViewById(R.id.ll_regist_xieyi);
        ivcode=(ImageView)findViewById(R.id.iv_regist_code);
        ivphone=(ImageView)findViewById(R.id.iv_regist_phone);
        ivpwd=(ImageView)findViewById(R.id.iv_regist_pwd);
        listener();
        if(SPUtil.getIsFirst(this,"isone",false)){
        getUserId();

        }

    }

    private void getUserId() {
        Map<String,String> params=new HashMap<>();
        params.put("act","getuserid");
        params.put("devicetoken",SPUtil.getData(this,"deviceToken",null));
        Log.d("用户的devicetoken",SPUtil.getData(this,"deviceToken",null));
        RetrofitUtil.getretrofit().create(UserIdBiz.class).getData(params).enqueue(new Callback<UserId>() {
            @Override
            public void onResponse(Call<UserId> call, Response<UserId> response) {
                Log.d("第一次使用userid111111",response.body().getUserid());

                if(response.body().getUserid().equals("")){
                    SPUtil.setData(RegistActivity.this,"USERID",response.body().getUserid());

                }
            }

            @Override
            public void onFailure(Call<UserId> call, Throwable t) {

            }
        });



    }

    private void listener() {



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   phonestring=phone.getText().toString();
                if(CommonUtil.checkPhone(phonestring)){
                    RetrofitUtil.getretrofit().create(RegisterBiz.class).getData("reg",phonestring).enqueue(new Callback<Register>() {
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
                                case "3":
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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonestring = phone.getText().toString();
                codestring = code.getText().toString();
                pwdstirng = pwd.getText().toString();
                Log.d("登录返回的用户信息",CommonUtil.md5(pwdstirng));

                if(CommonUtil.checkRegist(phonestring,codestring,pwdstirng)){
                    getUser();
                }
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.goAactivity(RegistActivity.this, WebViewActivity.class, "msgurl", Constants.agreement,"title","用户声明及协议");
                RegistActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });


    }

    private void getUser() {
        //String mima2 = MD5.getMessageDigest(mima.getText().toString().getBytes()).toUpperCase();

        Map<String,String> params=new HashMap<>();
        params.put("devicetoken",SPUtil.getData(this,"deviceToken",null));
        params.put("mobile",phonestring);
        params.put("yzm",codestring);
        params.put("password",CommonUtil.md5(pwdstirng));
        params.put("userid",SPUtil.getData(this,"USERID",null));
        params.put("mobiletype","0");
        Log.d("注册数据",SPUtil.getData(this,"deviceToken",null)+"\n"+CommonUtil.md5(pwdstirng)+"\n"+SPUtil.getData(this,"USERID",null));
        RetrofitUtil.getretrofit().create(Registinfo.class).getData(params).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("注册返回用户信息", response.body().getStatus() + "####" + response.body().getUserinfo());
                switch (response.body().getStatus()) {
                    case "0":

                        break;
                    case "1":
                        if (SPUtil.setUser(RegistActivity.this, response.body().getUserinfo())) {
                            CommonUtil.showToast(RegistActivity.this, "注册成功");
                            CommonUtil.goAactivity(RegistActivity.this, MainActivity.class);


                        } else {
                            CommonUtil.showToast(RegistActivity.this, "稍后再试");
                        }
                        break;
                    case "2":
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
            public void onFailure(Call<User> call, Throwable t) {

            }
        });



    }
}
