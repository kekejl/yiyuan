package com.yiyuaninfo.adapter;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.Interface.ZanBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.CircleImageView;
import com.yiyuaninfo.entity.CommentList;
import com.yiyuaninfo.entity.FirstCommentBean;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaocongcong on 2017/7/20.
 */

public class CommentListAdapter extends BaseQuickAdapter<FirstCommentBean,BaseViewHolder> {
    boolean iszan;
    ImageView imageView;
    public CommentListAdapter(int layoutResId, List<FirstCommentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final  BaseViewHolder helper, final FirstCommentBean item) {

        imageView=helper.getView(R.id.iv_newsdetail_zan);
        if(item.getFlag().equals("0")){
            iszan=false;
            helper.getView(R.id.iv_newsdetail_zan).setBackgroundResource(R.drawable.dz00);
        }if(item.getFlag().equals("1")){
            iszan=true;
            helper.getView(R.id.iv_newsdetail_zan).setBackgroundResource(R.drawable.dz02);


        }
        CheckBox  checkBox=helper.getView(R.id.checkbox_zan);
        if(item.getFlag().equals("0")){
            checkBox.setChecked(false);
        }if(item.getFlag().equals("1")){
            checkBox.setChecked(true);

        }
        ImageLoaderUtils.displayImage(item.getAvatar(),(CircleImageView)helper.getView(R.id.civ_newsdetail));
        helper.setText(R.id.tv_newsdetail_name,item.getUsername());
        helper.setText(R.id.tv_newsdetail_zan,item.getZan_count());
        helper.setText(R.id.tv_newsdetail_title,item.getReply_msg());
        helper.setText(R.id.tv_newsdetail_time, DateUtils.getShortTime(item.getCreate_date()));
        helper.addOnClickListener(R.id.iv_newsdetail_zan);
        helper.addOnClickListener(R.id.tv_newsdetail_hf);

        //helper.addOnClickListener(R.id.checkbox_zan);

//        helper.getView(R.id.iv_newsdetail_zan).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!iszan){
//
//
//                    Map<String,String> params=new HashMap<>();
//                    params.put("act","addlike");
//                    params.put("userid","1507529879iwlkxj");
//                    params.put("comment","0");
//                    params.put("commentid",item.getCommentid());
//
//
//                    RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
//                        @Override
//                        public void onResponse(Call<State> call, Response<State> response) {
//                            Log.d("CheckBox",response.body().getState()+"@@");
//
//                            if(response.body().getState().equals("1")){
//                                iszan=true;
//                                helper.getView(R.id.iv_newsdetail_zan).setBackgroundResource(R.drawable.dz02);
//                                ToastUtils.showToast("点赞成功");
//                            }else {
//
//                                ToastUtils.showToast("网络繁忙  稍后再试");
//
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<State> call, Throwable t) {
//
//                        }
//                    });
//
//
//
//
//
//                }else {
//                    Map<String,String> params=new HashMap<>();
//                    params.put("act","dellike");
//                    params.put("userid","1507529879iwlkxj");
//                    params.put("comment","0");
//                    params.put("commentid",item.getCommentid());
//                    RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
//                        @Override
//                        public void onResponse(Call<State> call, Response<State> response) {
//                            if(response.body().getState().equals("1")){
//                                iszan=false;
//                                helper.getView(R.id.iv_newsdetail_zan).setBackgroundResource(R.drawable.dz00);
//                                ToastUtils.showToast("点赞取消");
//                            }else {
//                                ToastUtils.showToast("网络繁忙  稍后再试");
//                            }
//                        }
//                        @Override
//                        public void onFailure(Call<State> call, Throwable t) {
//
//                        }
//                    });
//
//                }
//
//
//            }
//        });


    }



}
