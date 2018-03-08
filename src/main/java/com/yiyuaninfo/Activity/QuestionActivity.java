package com.yiyuaninfo.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Interface.QuestionAllBiz;
import com.yiyuaninfo.Interface.TiWenBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.CircleImageView;
import com.yiyuaninfo.adapter.QuestionAllAdapter;
import com.yiyuaninfo.entity.QuestionAll;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.entity.WenDa;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by gaocongcong on 2017/10/17.
 */

public class QuestionActivity  extends   BaseActivity implements CommonPopupWindow.ViewInterface,View.OnClickListener{
    private RecyclerView  recyclerView;
    private Intent  intent;
    private WenDa.WdArrBean  entity;
    private QuestionAllAdapter  allAdapter;
    private View view;
    private TextView  question,name,content;
    private CircleImageView  imageView;
    private CommonPopupWindow  popupWindow;
    private EditText  editText;
    private TextView textView;
    private LinearLayout  linearLayout;
    private User.UserinfoBean  userinfoBean;
    @Override
    protected int getContentView() {
        return R.layout.activity_question;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("问答");
        view= LayoutInflater.from(this).inflate(R.layout.header_questionall, null);
        imageView=(CircleImageView)view.findViewById(R.id.civ_questionall);
        question=(TextView)view.findViewById(R.id.tv_questionall_title);
        name=(TextView)view.findViewById(R.id.tv_questionall_name);
        content=(TextView)view.findViewById(R.id.tv_questionall_content);
        linearLayout=(LinearLayout)findViewById(R.id.ll_questionall_pl);
        linearLayout.setOnClickListener(this);
        intent=getIntent();
        entity=(WenDa.WdArrBean)intent.getSerializableExtra("entity");
        recyclerView=(RecyclerView)findViewById(R.id.rv_activity_question);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageLoaderUtils.displayBigImage(entity.getNiu_img(),imageView);
        question.setText(entity.getTitle());
        name.setText(entity.getNiu_name());
        content.setText(entity.getDescription());
        getdata();


    }

    private void getdata() {
        Map<String,String> params=new HashMap<>();
        params.put("act","wendaqa");
        params.put("userid", SPUtil.getUser(this).getUserid());
        params.put("artid",entity.getId());
        RetrofitUtil.getretrofit().create(QuestionAllBiz.class).getData(params).enqueue(new Callback<QuestionAll>() {
            @Override
            public void onResponse(Call<QuestionAll> call, Response<QuestionAll> response) {
                allAdapter=new QuestionAllAdapter(R.layout.item_questionall,response.body().getInfo());
                recyclerView.setAdapter(allAdapter);
                allAdapter.addHeaderView(view);
            }

            @Override
            public void onFailure(Call<QuestionAll> call, Throwable t) {

            }
        });


    }

    private void showPopupWindow() {

        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popwindow_tw, null);

        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_tw)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        // popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.showAtLocation(recyclerView, Gravity.BOTTOM, 0, 0);
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId){
        case R.layout.popwindow_tw:

        textView = (TextView) view.findViewById(R.id.tv_news_pl);
        textView.setOnClickListener(this);
        editText = (EditText) view.findViewById(R.id.et_news_pl);

        break;

    }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.tv_news_pl :
                if (SPUtil.isLogin(this)) {
                    userinfoBean = SPUtil.getUser(this);

                    if (!editText.getText().toString().equals("")) {
                        Map<String, String> params = new HashMap<>();
                        params.put("act", "useraddq");
                        params.put("userid", userinfoBean.getUserid());
                        params.put("artid", entity.getId());
                        params.put("arttitle", entity.getTitle());
                        params.put("content", editText.getText().toString());
                        RetrofitUtil.getretrofit().create(TiWenBiz.class).getData(params).enqueue(new Callback<State>() {
                            @Override
                            public void onResponse(Call<State> call, Response<State> response) {
                                switch (response.body().getState()) {
                                    case "1":
                                        ToastUtils.showToast("成功");
                                        getnewdata();
                                        popupWindow.dismiss();
                                        break;
                                    case "0":
                                        ToastUtils.showToast("发布失败！请重试");
                                        break;
                                }
                            }

                            @Override
                            public void onFailure(Call<State> call, Throwable t) {

                            }
                        });
                    } else {
                        ToastUtils.showToast("请先输入内容！");
                    }
                } else {
                    CommonUtil.goAactivity(QuestionActivity.this, LogInActivity.class);
                }


                break;
            case R.id.ll_questionall_pl:
                showPopupWindow();
                break;
        }

    }
    private void getnewdata() {
        Map<String,String> params=new HashMap<>();
        params.put("act","wendaqa");
        params.put("userid", SPUtil.getUser(this).getUserid());
        params.put("artid",entity.getId());
        RetrofitUtil.getretrofit().create(QuestionAllBiz.class).getData(params).enqueue(new Callback<QuestionAll>() {
            @Override
            public void onResponse(Call<QuestionAll> call, Response<QuestionAll> response) {
                     allAdapter.setNewData(response.body().getInfo());
            }

            @Override
            public void onFailure(Call<QuestionAll> call, Throwable t) {

            }
        });


    }

}
