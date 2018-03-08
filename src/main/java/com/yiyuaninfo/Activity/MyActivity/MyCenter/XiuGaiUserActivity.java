package com.yiyuaninfo.Activity.MyActivity.MyCenter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Activity.MyActivity.RegistActivity;
import com.yiyuaninfo.Activity.MyActivity.SubscriptionActivity;
import com.yiyuaninfo.Interface.XiuGaiUserBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.entity.UserXiuGai;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.CommonUtils;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaocongcong on 2017/8/17.
 */

public class XiuGaiUserActivity extends BaseActivity {
    private EditText  editText;
    private Button button;
    private  String  value;
    private  String   key;
    private  String  name, name2;
    private TextView   textViewname,tishi;
    @Override
    protected int getContentView() {
        return R.layout.activity_xiugaiuser;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("修改个人信息");
        Intent intent=getIntent();
        key=intent.getStringExtra("key");
        name=intent.getStringExtra("name");
        name2=intent.getStringExtra("value");

        editText = (EditText) findViewById(R.id.et_xiugaiuser);
        textViewname=(TextView)findViewById(R.id.tv_xiugai);
        tishi=(TextView)findViewById(R.id.tv_xuigai_tishi);
        textViewname.setText(name+" : ");
        switch (key){
            case "username":
                if(name2!=null){

                editText.setText(name2);
                editText.setSelection(name2.length());
                }else {
                    editText.setHint("请输入昵称");

                }
                break;
            case "guling":
                if(name2!=null){
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});


                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.setText(name2);
                    editText.setSelection((name2).length());
                }else {
                     editText.setHint("请输入股龄0-99");
                }

                break;

            case "capital":
                if(name2!=null){
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.setMaxLines(5);
                    editText.setText(name2);
                    editText.setSelection((name2).length());

                }else {
                    editText.setHint("请输入有效资金量");
                }

                break;
            case "email":
                if(name2!=null){
                editText.setText(name2);
                editText.setSelection(name2.length());
                }else {
                    editText.setHint("请输入正确的邮箱格式");

                }

                break;
        }


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               Log.d("edittext","输入前");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("edittext","输入变化");

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("edittext","输入结束"+s);
                String  ss=s.toString();
                switch (key){
                    case "username":
                        editText.setHint("请输入昵称");

                        break;
                    case "guling":
                            editText.setHint("请输入股龄0-99");
                        if(!ss.equals("")){

                        if(Integer.parseInt(ss)>=0&&Integer.parseInt(ss)<=99){
                            tishi.setVisibility(View.INVISIBLE);


                        }else {
                            tishi.setText("*股龄不能超过99年");
                            tishi.setVisibility(View.VISIBLE);
                        }
                        }

                        break;

                    case "capital":
                            editText.setHint("请输入有效资金量");

                        if(!ss.equals("")){

                        if(Integer.parseInt(ss)>=0&&Integer.parseInt(ss)<=10000){
                            tishi.setVisibility(View.INVISIBLE);

                        }else {
                            tishi.setText("*资金量不能超过10000万元");
                            tishi.setVisibility(View.VISIBLE);
                        }
                        }
                        break;
                    case "email":
                        editText.setHint("请输入正确的邮箱格式");
                        if(!ss.equals("")){

                        if(CommonUtil.checkEmall(ss,false)){
                            tishi.setVisibility(View.INVISIBLE);

                        }else {
                            tishi.setText("*邮箱格式不正确");
                            tishi.setVisibility(View.VISIBLE);
                        }
                        }
                        break;
                }

            }
        });



        button=(Button)findViewById(R.id.btn_xiugaiuser);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   value=editText.getText().toString();

                if(!TextUtils.isEmpty(value)){


                    switch (key){
                        case "username":
                             tijiao();
                            break;
                        case "guling":

                            if(Integer.parseInt(value)>=0&&Integer.parseInt(value)<=99){
                                 tijiao();
                            }else {
                                ToastUtils.showToast("输入股龄（0-99）");
                            }

                            break;

                        case "capital":

                            if(Integer.parseInt(value)>=0&&Integer.parseInt(value)<=10000){
                                tijiao();
                            }else {
                                ToastUtils.showToast("输入资金量（0-10000）");
                            }

                            break;
                        case "email":

                              if(CommonUtil.checkEmall(value,true)){
                                  tijiao();
                              }else {

                              }
                            break;
                    }



                   }else {
                       ToastUtils.showToast("输入不能为空");
                   }
            }
        });
    }

    private void tijiao() {
        Map<String, String> params = new HashMap<>();

        params.put("act",key);
        params.put(key,value);
        params.put("userid", SPUtil.getUser(XiuGaiUserActivity.this).getUserid());

        RetrofitUtil.getretrofit().create(XiuGaiUserBiz.class).getData(params).enqueue(new Callback<UserXiuGai>() {
            @Override
            public void onResponse(Call<UserXiuGai> call, Response<UserXiuGai> response) {
                Log.d("修改用户信息返回的数据",response.body().getStatus()+"\n"+response.body().getUserinfo());
                if(response.body().getStatus().equals("1")){
                    if(SPUtil.setUser(XiuGaiUserActivity.this, response.body().getUserinfo())){
                        ToastUtils.showToast("修改成功");
                        if(response.body().getAddintegral()!=null&&!response.body().getAddintegral().equals("")){
                            ToastUtils.showToast("成功,+"+response.body().getAddintegral()+"积分");
                        }
                        finish();
                    }
                }else {
                    ToastUtils.showToast("修改失败");

                }
            }

            @Override
            public void onFailure(Call<UserXiuGai> call, Throwable t) {

            }
        });

    }
}
