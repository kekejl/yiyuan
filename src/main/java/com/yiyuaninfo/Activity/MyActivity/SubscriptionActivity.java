package com.yiyuaninfo.Activity.MyActivity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Interface.DayServiceBiz;
import com.yiyuaninfo.Interface.SaveDayServiceBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.DayService;
import com.yiyuaninfo.entity.Status;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/2/24.
 */

public class SubscriptionActivity extends BaseActivity {
    private CheckBox  checkBox1;
    private CheckBox  checkBox2;
    private CheckBox  checkBox3;
    private CheckBox  checkBox4;
    private CheckBox  checkBox5;
    private CheckBox  checkBox6;
    private CheckBox  checkBox7;
    private CheckBox  checkBox8;
    private String  strings="";
    private List<Integer> listInteger=new ArrayList<>();
    private TextView  textView;
    private ImageView  imageView;
    @Override
    protected int getContentView() {
        return R.layout.activity_subscription;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isshowToolBar(false);
        initView();
        Log.d("订阅设置状态",checkBox1.isChecked()+"1212");
        getData();
        //setCheck();
        checkBox1.setChecked(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("订阅设置",isChenck()+"@@@"+SPUtil.getUser(SubscriptionActivity.this).getUserid());
                RetrofitUtil.getretrofit().create(SaveDayServiceBiz.class).getData("moddayservice",isChenck(),SPUtil.getUser(SubscriptionActivity.this).getUserid()).enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
                        Log.d("订阅设置",response.body().getStatus());
                        if(response.body().getStatus().equals("1")){
                            ToastUtils.showToast("保存成功");
                        }else {
                            ToastUtils.showToast("保存失败 ");

                        }

                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {

                    }
                });
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getData() {

       RetrofitUtil.getretrofit().create(DayServiceBiz.class).getData("dayservice", SPUtil.getUser(this).getUserid()).enqueue(new Callback<DayService>() {
           @Override
           public void onResponse(Call<DayService> call, Response<DayService> response) {
                Log.d("订阅状态的数据",response.body().getDayservice().toString());
               listInteger=response.body().getDayservice();
               setCheck(listInteger);
           }

           @Override
           public void onFailure(Call<DayService> call, Throwable t) {



           }
       });
    }

    /**
     * 获取状态  显示出来
     *
     */
    private void setCheck(List<Integer>  list ) {
           if(list.get(0)==1){
               checkBox1.setChecked(true);
           }else {
               checkBox1.setChecked(false);
           }
        if(list.get(1)==1){
            checkBox2.setChecked(true);
        }else {
            checkBox2.setChecked(false);
        }
        if(list.get(2)==1){
            checkBox3.setChecked(true);
        }else {
            checkBox3.setChecked(false);
        }
        if(list.get(3)==1){
            checkBox4.setChecked(true);
        }else {
            checkBox4.setChecked(false);
        }
        if(list.get(4)==1){
            checkBox5.setChecked(true);
        }else {
            checkBox5.setChecked(false);
        }
        if(list.get(5)==1){
            checkBox6.setChecked(true);
        }else {
            checkBox6.setChecked(false);
        }
        if(list.get(6)==1){
            checkBox7.setChecked(true);
        }else {
            checkBox7.setChecked(false);
        }
        if(list.get(7)==1){
            checkBox8.setChecked(true);
        }else {
            checkBox8.setChecked(false);
        }





    }

    private String isChenck() {
        strings="";
        if(checkBox1.isChecked()){
            strings="1";
        }else {
            strings="0";
        }
        if(checkBox2.isChecked()){
            strings=strings+",1";
        }else {
            strings=strings+",0";
        }
        if(checkBox3.isChecked()){
            strings=strings+",1";
        }else {
            strings=strings+",0";
        }
        if(checkBox4.isChecked()){
            strings=strings+",1";
        }else {
            strings=strings+",0";
        }
        if(checkBox5.isChecked()){
            strings=strings+",1";
        }else {
            strings=strings+",0";
        }
        if(checkBox6.isChecked()){
            strings=strings+",1";
        }else {
            strings=strings+",0";
        }
        if(checkBox7.isChecked()){
            strings=strings+",1";
        }else {
            strings=strings+",0";
        }
        if(checkBox8.isChecked()){
            strings=strings+",1";
        }else {
            strings=strings+",0";
        }

       Log.d("修改以后的状态",strings);
     return   strings;

    }

    private void initView() {
        checkBox1=(CheckBox)findViewById(R.id.checkbox1);
        checkBox2=(CheckBox)findViewById(R.id.checkbox2);
        checkBox3=(CheckBox)findViewById(R.id.checkbox3);
        checkBox4=(CheckBox)findViewById(R.id.checkbox4);
        checkBox5=(CheckBox)findViewById(R.id.checkbox5);
        checkBox6=(CheckBox)findViewById(R.id.checkbox6);
        checkBox7=(CheckBox)findViewById(R.id.checkbox7);
        checkBox8=(CheckBox)findViewById(R.id.checkbox8);
        textView=(TextView) findViewById(R.id.tv_sub_save);
        imageView=(ImageView)findViewById(R.id.image_sub_back);
    }


}
