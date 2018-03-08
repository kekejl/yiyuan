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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Interface.CommentOneBiz;
import com.yiyuaninfo.Interface.CommentReplyBiz;
import com.yiyuaninfo.Interface.ZanBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.CircleImageView;
import com.yiyuaninfo.adapter.CommentReplyAdapter;
import com.yiyuaninfo.entity.Comment;
import com.yiyuaninfo.entity.CommentReply;
import com.yiyuaninfo.entity.FirstCommentBean;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.Text;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.MyDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaocongcong on 2017/9/20.
 */

public class CommentDetailActivity extends  BaseActivity  implements View.OnClickListener,CommonPopupWindow.ViewInterface{
    private RecyclerView  recyclerView;
    private Intent intent;
    private String  zan;
    private List<CommentReply.ReplyCommentBean>   list=new ArrayList();
    int  a,b;
    private CommentReplyAdapter  adapter;
    private FirstCommentBean  entity;
    private CommentReply.ReplyCommentBean  commentReply;
    private View view;
    private CircleImageView circleImageView;
    private TextView  name,title,time,num;
    private ImageView  imageView ,back;
    private boolean  iszanheader,iszan;
    private String lastid;
    private LinearLayout commenthf;
    private CommonPopupWindow  popupWindow;
    private EditText  editText;
    private TextView  tvHF;
    private String comment_id,to_user_id,to_user_name;
    private View notDataView;
    private View errorView;

    @Override
    protected int getContentView() {
        return R.layout.activity_commentdetail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isshowToolBar(false);
        view= LayoutInflater.from(this).inflate(R.layout.header_comment,null);
        imageView=(ImageView)view.findViewById(R.id.iv_header_hf_zan);
        imageView.setOnClickListener(this);
        circleImageView=(CircleImageView)view.findViewById(R.id.civ_header_hf);
        back=(ImageView)findViewById(R.id.image_comment_back);
        name=(TextView) view.findViewById(R.id.tv_header_hf_name);
        title=(TextView) view.findViewById(R.id.tv_header_hf_title);
        time=(TextView) view.findViewById(R.id.tv_header_hf_time);
        num=(TextView) view.findViewById(R.id.tv_header_hf_zan);
        commenthf=(LinearLayout)findViewById(R.id.ll_comment_hf);
        commenthf.setOnClickListener(this);
        back.setOnClickListener(this);
        intent=getIntent();
        entity=(FirstCommentBean) intent.getSerializableExtra("entity");
        setData();
        recyclerView=(RecyclerView)findViewById(R.id.rv_ctd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onRefresh();
            }
        });
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) recyclerView.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onRefresh();
            }
        });

    }

    private void setData() {
         zan=entity.getZan_count();
         a=Integer.parseInt(zan);
        ImageLoaderUtils.displayImage(entity.getAvatar(),circleImageView);
        name.setText(entity.getUsername());
        title.setText(entity.getReply_msg());
        time.setText(DateUtils.getShortTime(entity.getCreate_date()));
        num.setText(entity.getZan_count());

        if (entity.getFlag().equals("0")) {
            iszanheader = false;
            imageView.setBackgroundResource(R.drawable.dz00);

        } else if (entity.getFlag().equals("1")) {
            iszanheader = true;
            imageView.setBackgroundResource(R.drawable.dz02);

        }
    }

    private void getData() {
        final Map<String,String> params=new HashMap<>();
        params.put("act","relaymore");
        params.put("commentid",entity.getCommentid());
        params.put("userid",SPUtil.getUser(this).getUserid());
        RetrofitUtil.getretrofit().create(CommentReplyBiz.class).getData(params).enqueue(new Callback<CommentReply>() {
            @Override
            public void onResponse(Call<CommentReply> call, Response<CommentReply> response) {
                lastid=response.body().getLastid();
                list=response.body().getReply_comment();
                adapter=new CommentReplyAdapter(R.layout.item_comment,list,entity.getUsername());
                recyclerView.setAdapter(adapter);
                adapter.addHeaderView(view);
                adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getMoreData();
                            }
                        },1000);
                    }
                },recyclerView);

                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {

                        ToastUtils.showToast(position+"");
                            commentReply = (CommentReply.ReplyCommentBean) adapter.getData().get(position);
                            switch (view.getId()) {
                                case R.id.iv_newsdetail_zan:

                                   String userid= SPUtil.getUser(CommentDetailActivity.this).getUserid();
                                    if (commentReply.getFlag().equals("0")) {
                                        iszan = false;
                                    } else if (commentReply.getFlag().equals("1")) {
                                        iszan = true;
                                    }
                                    final ImageView imageView1 = (ImageView) view;
                                    final String zan =commentReply.getZan_count();
                                     b=Integer.parseInt(zan);
                                    if (!iszan) {


                                        Map<String, String> params = new HashMap<>();
                                        params.put("act", "addlike");
                                        params.put("userid", userid);
                                        params.put("comment", "1");
                                        params.put("commentid", commentReply.getId());


                                        RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
                                            @Override
                                            public void onResponse(Call<State> call, Response<State> response) {
                                                Log.d("CheckBox", response.body().getState() + "@@");

                                                if (response.body().getState().equals("1")) {
                                                    imageView1.setBackgroundResource(R.drawable.dz02);
                                                    //ToastUtils.showToast("点赞成功");
                                                    commentReply.setFlag("1");
                                                    b=b+1;
                                                    commentReply.setZan_count(b + "");
                                                   adapter.notifyDataSetChanged();

                                                } else {

                                                    ToastUtils.showToast("网络繁忙  稍后再试");

                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<State> call, Throwable t) {

                                            }
                                        });


                                    } else {
                                        Map<String, String> params = new HashMap<>();
                                        params.put("act", "dellike");
                                        params.put("userid", userid);
                                        params.put("comment", "1");
                                        params.put("commentid", commentReply.getId());
                                        RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
                                            @Override
                                            public void onResponse(Call<State> call, Response<State> response) {
                                                if (response.body().getState().equals("1")) {
                                                    imageView1.setBackgroundResource(R.drawable.dz00);
                                                    //ToastUtils.showToast("点赞取消");
                                                    b=b-1;
                                                    commentReply.setZan_count(b + "");
                                                    adapter.notifyDataSetChanged();

                                                    commentReply.setFlag("0");

                                                } else {
                                                    ToastUtils.showToast("网络繁忙  稍后再试");

                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<State> call, Throwable t) {

                                            }
                                        });
                                    }


                                    break;
                                case R.id.tv_newsdetail_hf:


                                    comment_id=commentReply.getComment_id();
                                    to_user_id=commentReply.getFrom_user_id();
                                    to_user_name=commentReply.getFrom_user_name();
                                    showPopupWindow();


                                    break;


                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<CommentReply> call, Throwable t) {

            }
        });

    }

    private void getMoreData() {

        Map<String,String> params=new HashMap<>();
        params.put("act","relaymore");
        params.put("commentid",entity.getCommentid());
        params.put("userid",SPUtil.getUser(this).getUserid());
        params.put("lastid",lastid);
        RetrofitUtil.getretrofit().create(CommentReplyBiz.class).getData(params).enqueue(new Callback<CommentReply>() {
            @Override
            public void onResponse(Call<CommentReply> call, Response<CommentReply> response) {
                if(response.body().getReply_comment().size()!=0){
                    adapter.addData(response.body().getReply_comment());
                    adapter.loadMoreComplete();
                }else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<CommentReply> call, Throwable t) {
                    adapter.loadMoreFail();
            }
        });







    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_header_hf_zan:
                DianZan();

                break;
            case R.id.ll_comment_hf:

                comment_id=entity.getCommentid();
                to_user_id=entity.getUserid();
                to_user_name=entity.getUsername();

                showPopupWindow();



                break;
            case R.id.image_comment_back:


                Bundle bundle =new Bundle();
                bundle.putSerializable("entity",entity);
                setResult(RESULT_OK,new Intent().putExtras(bundle));
                finish();
                break;

            case R.id.tv_news_pl:


                if (!editText.getText().toString().equals("")) {
                    Map<String, String> params = new HashMap<>();
                    params.put("act", "addcomment");
                    params.put("comment", "1");
                    params.put("articleid", SPUtil.getUser(this).getUserid());
                    params.put("userid", SPUtil.getUser(this).getUserid());
                    params.put("reply_msg", editText.getText().toString());
                    params.put("comment_id",comment_id);
                    params.put("to_user_id",to_user_id);
                    params.put("to_user_name",to_user_name);
                    params.put("user_avatar",SPUtil.getUser(this).getAvatar());
                    Log.d("回复参数",
                            "\n"+SPUtil.getUser(this).getUserid()+
                            "\n"+editText.getText().toString()+
                            "\n"+comment_id+
                            "\n"+to_user_id+
                            "\n"+to_user_name+
                            "\n"+SPUtil.getUser(this).getAvatar());
                    RetrofitUtil.getretrofit().create(CommentOneBiz.class).getData(params).enqueue(new Callback<State>() {
                        @Override
                        public void onResponse(Call<State> call, Response<State> response) {
                            switch (response.body().getState()) {
                                case "1":
                                    refreshData();
                                    ToastUtils.showToast("成功");
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
                }else {
                    ToastUtils.showToast("请先输入内容");
                }




                break;
        }
    }

    private void refreshData() {
        Map<String,String> params=new HashMap<>();
        params.put("act","relaymore");
        params.put("commentid",entity.getCommentid());
        params.put("userid",SPUtil.getUser(this).getUserid());
        RetrofitUtil.getretrofit().create(CommentReplyBiz.class).getData(params).enqueue(new Callback<CommentReply>() {
            @Override
            public void onResponse(Call<CommentReply> call, Response<CommentReply> response) {
                Log.d("回复后的数据",list.toString());

                list.clear();
                lastid=response.body().getLastid();
                list=response.body().getReply_comment();
                Log.d("回复后的数据",list.toString());


                adapter.setNewData(list);





            }

            @Override
            public void onFailure(Call<CommentReply> call, Throwable t) {

            }
        });


    }

    /**
     *
     * 弹出输入框
     */
    private void showPopupWindow() {

        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popwindow_hf, null);

        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_hf)
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

    /**
     * 点赞
     *
     */
    private void DianZan() {
        if(SPUtil.isLogin(this)){
            String userid=SPUtil.getUser(this).getUserid();
            if (!iszanheader) {
                Map<String, String> params = new HashMap<>();
                params.put("act", "addlike");
                params.put("userid",userid) ;
                params.put("comment", "0");
                params.put("commentid", entity.getCommentid());


                RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
                    @Override
                    public void onResponse(Call<State> call, Response<State> response) {
                        Log.d("CheckBox", response.body().getState() + "@@");

                        if (response.body().getState().equals("1")) {
                            imageView.setBackgroundResource(R.drawable.dz02);
                            a=a+1 ;
                            num.setText(a+"");
                            entity.setZan_count(a+"");
                            entity.setFlag("1");
                            iszanheader=true;
                        } else {

                            ToastUtils.showToast("网络繁忙  稍后再试");

                        }
                    }

                    @Override
                    public void onFailure(Call<State> call, Throwable t) {

                    }
                });


            } else {
                Map<String, String> params = new HashMap<>();
                params.put("act", "dellike");
                params.put("userid", userid);
                params.put("comment", "0");
                params.put("commentid", entity.getCommentid());
                RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
                    @Override
                    public void onResponse(Call<State> call, Response<State> response) {
                        if (response.body().getState().equals("1")) {
                            imageView.setBackgroundResource(R.drawable.dz00);
                            a=a-1 ;
                            num.setText(a+"");
                            entity.setZan_count(a+"");
                            entity.setFlag("0");
                            iszanheader=false;
                        } else {
                            ToastUtils.showToast("网络繁忙  稍后再试");

                        }
                    }

                    @Override
                    public void onFailure(Call<State> call, Throwable t) {

                    }
                });
            }
        }else {

        }
    }

    @Override
    public void getChildView(View view, int layoutResId) {
            switch (layoutResId){
                case R.layout.popwindow_hf:
                     editText=(EditText)view.findViewById(R.id.et_news_pl);
                     tvHF=(TextView)view.findViewById(R.id.tv_news_pl);
                     tvHF.setOnClickListener(this);
                    break;
            }

    }

    @Override
    public void onBackPressed() {


        Bundle bundle =new Bundle();
        bundle.putSerializable("entity",entity);
        setResult(RESULT_OK,new Intent().putExtras(bundle));
        finish();
        super.onBackPressed();
    }
}
