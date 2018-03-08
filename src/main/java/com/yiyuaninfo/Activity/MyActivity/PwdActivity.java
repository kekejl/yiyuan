package com.yiyuaninfo.Activity.MyActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Interface.XiuGaiPwdBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Register;
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

public class PwdActivity extends BaseActivity {
    private EditText etoldpwd,etnewpwd;
    private Button   button;
    private String  oldpwd,newpwd;
    @Override
    protected int getContentView() {
        return R.layout.activity_pwd;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("修改密码");
       etoldpwd=(EditText) findViewById(R.id.et_pwd_old);
       etnewpwd=(EditText) findViewById(R.id.et_pwd_new);
        button=(Button)findViewById(R.id.btn_pwd_true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldpwd=etoldpwd.getText().toString();
                newpwd=etnewpwd.getText().toString();
                if(CommonUtil.checkOldPwd(oldpwd,newpwd)){
                    Log.d("修改密码返回的数据",SPUtil.getUser(PwdActivity.this).getUserid()+"\n"+CommonUtil.md5(oldpwd)+"\n"+CommonUtil.md5(newpwd));


                    RetrofitUtil.getretrofit().create(XiuGaiPwdBiz.class)
                            .getData("modpwd", SPUtil.getUser(PwdActivity.this).getUserid(),CommonUtil.md5(oldpwd),CommonUtil.md5(newpwd)).enqueue(new Callback<Register>() {
                        @Override
                        public void onResponse(Call<Register> call, Response<Register> response) {
                            Log.d("修改密码返回的数据",response.body().getStatus());
                            if(response.body().getStatus().equals("0")){
                                ToastUtils.showToast("旧密码错误");
                            }if(response.body().getStatus().equals("1")){
                                ToastUtils.showToast("修改成功");

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