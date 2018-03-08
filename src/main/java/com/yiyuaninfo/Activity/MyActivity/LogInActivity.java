package com.yiyuaninfo.Activity.MyActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Interface.ForgetPwdBiz;
import com.yiyuaninfo.Interface.UserBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.MD5;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.view.BaseProgressDialog;
import com.yiyuaninfo.view.ProgressDialog;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/2/24.
 */

public class LogInActivity extends BaseActivity implements DialogInterface.OnClickListener, View.OnClickListener {
    private EditText editTextphone;
    private EditText editTextpwd;
    private Button btnlogin;
    private TextView tvregiset;
    private TextView tvforgetpwd;
    private String phone;
    private String pwd;
    private BaseProgressDialog dialog;
    private boolean booleanphone=false;
    private boolean booleanpwd=false;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        dialog = new ProgressDialog(this).setLabel("登录...");


        setToolBarTitle("登录");
        editTextphone = (EditText) findViewById(R.id.et_login_phone);
        editTextpwd = (EditText) findViewById(R.id.et_login_pwd);
        btnlogin = (Button) findViewById(R.id.button_login);
       // btnlogin.setOnClickListener(this);

        btnlogin.setTextColor(Color.parseColor("#E0E1E2"));

        tvregiset = (TextView) findViewById(R.id.tv_login_regist);
        tvforgetpwd = (TextView) findViewById(R.id.tv_login_forgetpwd);
        tvregiset.setOnClickListener(this);
        tvforgetpwd.setOnClickListener(this);


        editTextphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!editTextphone.getText().toString().equals("")) {
                    booleanphone = true;
                } else {
                    booleanphone = false;
                }

                setBtnListener();

            }
        });
        editTextpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!editTextpwd.getText().toString().equals("")) {
                    booleanpwd = true;
                } else {
                    booleanpwd=false;
                }
                setBtnListener();


            }
        });


    }

    private void setBtnListener() {
        Log.d("判断",booleanphone+""+booleanpwd+"");
        if(booleanphone&&booleanpwd){
           btnlogin.setOnClickListener(this);
            btnlogin.setTextColor(Color.parseColor("#ffffff"));
        }else {
            btnlogin.setTextColor(Color.parseColor("#E0E1E2"));

        }

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    /**
     * 点击登录收起键盘，安卓不会自动隐藏虚拟键盘
     */

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                phone = editTextphone.getText().toString();
                pwd = editTextpwd.getText().toString();

                if (CommonUtil.checkLogin(phone, pwd)) {


                    dialog.show();


                    Map<String, String> params = new HashMap<>();
                    params.put("devicetoken", SPUtil.getData(this, "deviceToken", null));
                    params.put("mobile", phone);
                    params.put("password", CommonUtil.md5(pwd));
                    params.put("mobiletype", "0");
                    Log.d("登录deviceToken", SPUtil.getData(this, "deviceToken", null));
                    RetrofitUtil.getretrofit().create(UserBiz.class).getUser(params).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Log.d("登录返回的用户信息", response.body().getStatus() + response.body().getUserinfo());

                            switch (response.body().getStatus()) {
                                case "0":
                                    CommonUtil.showToast(LogInActivity.this, "登录失败！密码错误！");

                                    dialog.dismiss();
                                    break;
                                case "2":
                                    CommonUtil.showToast(LogInActivity.this, "登录失败！手机号错误！");

                                    dialog.dismiss();
                                    break;
                                case "1":

                                    if (SPUtil.setUser(LogInActivity.this, response.body().getUserinfo())) {
                                        CommonUtil.showToast(LogInActivity.this, "登录成功");
                                        dialog.setLabel("登录成功");
                                        dialog.dismiss();
                                        finish();
                                    } else {
                                        CommonUtil.showToast(LogInActivity.this, "稍后再试");
                                    }

                                    break;
                            }


                            //Log.d("用户信息", response.body().getUserinfo().toString());

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                } else {
                    CommonUtil.showToast(LogInActivity.this, "输入的手机号或密码错误");
                }
                break;
            case R.id.tv_login_regist:
                CommonUtil.goAactivity(this, RegistActivity.class);
                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.tv_login_forgetpwd:
                CommonUtil.goAactivity(this, ForgetPwdActivity.class);
                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
        }
    }
}
