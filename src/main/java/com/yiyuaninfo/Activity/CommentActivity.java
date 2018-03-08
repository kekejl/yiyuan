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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Interface.CommentBiz;
import com.yiyuaninfo.Interface.CommentListBiz;
import com.yiyuaninfo.Interface.CommentOneBiz;
import com.yiyuaninfo.Interface.ZanBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.CommentAdapter;
import com.yiyuaninfo.adapter.CommentListAdapter;
import com.yiyuaninfo.entity.Comment;
import com.yiyuaninfo.entity.CommentList;
import com.yiyuaninfo.entity.FirstCommentBean;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.MyDecoration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaocongcong on 2017/9/20.
 */

public class CommentActivity extends BaseActivity  implements CommonPopupWindow.ViewInterface ,View.OnClickListener{
    private RecyclerView recyclerView ,recyclerViewheader;
    private List<FirstCommentBean> list = new ArrayList<>();
    private List<FirstCommentBean> listheader = new ArrayList<>();
    private List<FirstCommentBean> list1 = new ArrayList<>();
    private int   p;
    private CommentListAdapter adapter ;
    private CommentAdapter  adapterheader;
    private String newsid, userid;
    private Intent intent;
    private TextView  textView,textViewnull;
    private EditText  editText;
    boolean iszan;
    private CommonPopupWindow  popupWindow;
    int b;
    private String  comment_id,to_user_id,to_user_name;
    private String lastid;

    private FirstCommentBean  entity,comment,commentResult,firstCommentBean;


    private LinearLayout linearLayout;
    private  View  view;
    @Override
    protected int getContentView() {
        return R.layout.activity_comment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("全部评论");
        intent = getIntent();
        newsid = intent.getStringExtra("newsid");
        userid = intent.getStringExtra("userid");
        recyclerView = (RecyclerView) findViewById(R.id.rv_ct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        linearLayout=(LinearLayout)findViewById(R.id.ll_emptyview);
        textViewnull=(TextView)findViewById(R.id.tv_nodata);
        view = LayoutInflater.from(this).inflate(R.layout.activity_comment_header, null);

        recyclerViewheader=(RecyclerView)view.findViewById(R.id.rv_comment_header);
        recyclerViewheader.setLayoutManager(new LinearLayoutManager(this));
      //  recyclerViewheader.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));



        getHeaderData();
        getData();
    }

    private void getHeaderData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "first");
        params.put("articleid", newsid);
        params.put("userid", userid);

        RetrofitUtil.getretrofit().create(CommentBiz.class).getData(params).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                listheader = response.body().getFirst_comment();
                adapterheader = new CommentAdapter(R.layout.item_comment, listheader);
                // adapter.addHeaderView(view);
                recyclerViewheader.setAdapter(adapterheader);
                adapterheader.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        list1=adapter.getData();
                        p=position;
                        comment = (FirstCommentBean) adapter.getData().get(position);
                        Intent intent = new Intent(CommentActivity.this, CommentDetailActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("entity", comment);
                        Log.d("返回的数据",position+"+++"+comment.toString());

                        intent.putExtras(bundle);
                        startActivityForResult(intent,1);


                    }


                });


                adapterheader.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                        entity = (FirstCommentBean) adapter.getData().get(position);
                        switch (view.getId()) {
                            case R.id.iv_newsdetail_zan:


                                if (entity.getFlag().equals("0")) {
                                    iszan = false;
                                } else if (entity.getFlag().equals("1")) {
                                    iszan = true;
                                }


                                Log.d("CheckBox", "1111111");

                                final ImageView imageView = (ImageView) view;
                                final String zan = entity.getZan_count();
                                if (!iszan) {


                                    Map<String, String> params = new HashMap<>();
                                    params.put("act", "addlike");
                                    params.put("userid", userid);
                                    params.put("comment", "0");
                                    params.put("commentid", entity.getCommentid());


                                    RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
                                        @Override
                                        public void onResponse(Call<State> call, Response<State> response) {
                                            Log.d("CheckBox", response.body().getState() + "@@");

                                            if (response.body().getState().equals("1")) {
                                                imageView.setBackgroundResource(R.drawable.dz02);
                                                //ToastUtils.showToast("点赞成功");
                                                entity.setFlag("1");
                                                entity.setZan_count(Integer.parseInt(zan) + 1 + "");
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
                                    params.put("comment", "0");
                                    params.put("commentid", entity.getCommentid());
                                    RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
                                        @Override
                                        public void onResponse(Call<State> call, Response<State> response) {
                                            if (response.body().getState().equals("1")) {
                                                imageView.setBackgroundResource(R.drawable.dz00);
                                                //ToastUtils.showToast("点赞取消");
                                                entity.setZan_count(Integer.parseInt(zan) - 1 + "");
                                                adapter.notifyDataSetChanged();
                                                entity.setFlag("0");

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
                                comment_id=entity.getCommentid();
                                to_user_id=entity.getUserid();
                                to_user_name=entity.getUsername();


                                showPopupWindow();


                                break;
                    }
                    }
                });


            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });

    }




    private void getData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "firstmore");
        params.put("userid", userid);
        params.put("articleid", newsid);
        RetrofitUtil.getretrofit().create(CommentListBiz.class).getData(params).enqueue(new Callback<CommentList>() {
            @Override
            public void onResponse(final Call<CommentList> call, Response<CommentList> response) {
                if(response.body().getFirst_comment().size()!=0){



                list = response.body().getFirst_comment();
                lastid=response.body().getLastid();
//                if(response.body().getUser_comment().size()!=0){
//                    CommentList.FirstCommentBean  entity=new CommentList.FirstCommentBean("","","",
//                            response.body().getUser_comment().get(0).getReply_msg(),
//                            response.body().getUser_comment().get(0).getCreate_date(),
//                            response.body().getUser_comment().get(0).getZan_count(),"","","");
//                    list.add(0,entity);
//                }

                adapter = new CommentListAdapter(R.layout.item_comment, list);
                    adapter.setHeaderView(view);
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        list1=adapter.getData();
                        p=position;
                        comment = (FirstCommentBean) adapter.getData().get(position);
                        Intent intent = new Intent(CommentActivity.this, CommentDetailActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("entity", comment);
                        Log.d("返回的数据",position+"+++"+comment.toString());

                        intent.putExtras(bundle);
                        startActivityForResult(intent,1);



                        // CommonUtil.goAactivity(CommentActivity.this, CommentDetailActivity.class, "commentid", comment.getCommentid());


                    }
                });

                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(final BaseQuickAdapter adapter, View view, int position) {

                        entity = (FirstCommentBean) adapter.getData().get(position);
                        switch (view.getId()) {
                            case R.id.iv_newsdetail_zan:


                                if (entity.getFlag().equals("0")) {
                                    iszan = false;
                                } else if (entity.getFlag().equals("1")) {
                                    iszan = true;
                                }


                                Log.d("CheckBox", "1111111");

                                final ImageView imageView = (ImageView) view;
                                final String zan = entity.getZan_count();
                                if (!iszan) {


                                    Map<String, String> params = new HashMap<>();
                                    params.put("act", "addlike");
                                    params.put("userid", userid);
                                    params.put("comment", "0");
                                    params.put("commentid", entity.getCommentid());


                                    RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
                                        @Override
                                        public void onResponse(Call<State> call, Response<State> response) {
                                            Log.d("CheckBox", response.body().getState() + "@@");

                                            if (response.body().getState().equals("1")) {
                                                imageView.setBackgroundResource(R.drawable.dz02);
                                                //ToastUtils.showToast("点赞成功");
                                                entity.setFlag("1");
                                                entity.setZan_count(Integer.parseInt(zan) + 1 + "");
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
                                    params.put("comment", "0");
                                    params.put("commentid", entity.getCommentid());
                                    RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
                                        @Override
                                        public void onResponse(Call<State> call, Response<State> response) {
                                            if (response.body().getState().equals("1")) {
                                                imageView.setBackgroundResource(R.drawable.dz00);
                                                //ToastUtils.showToast("点赞取消");
                                                entity.setZan_count(Integer.parseInt(zan) - 1 + "");
                                                adapter.notifyDataSetChanged();
                                                entity.setFlag("0");

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
                                 comment_id=entity.getCommentid();
                                 to_user_id=entity.getUserid();
                                 to_user_name=entity.getUsername();


                                 showPopupWindow();


                                break;
                        }

                    }
                });



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

                }else {
                    recyclerView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    textViewnull.setText("暂无评论");
                }
            }

            @Override
            public void onFailure(Call<CommentList> call, Throwable t) {

            }
        });

    }

    private void getMoreData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "firstmore");
        params.put("userid", userid);
        params.put("articleid", newsid);
        params.put("lastid", lastid);
        RetrofitUtil.getretrofit().create(CommentListBiz.class).getData(params).enqueue(new Callback<CommentList>() {
            @Override
            public void onResponse(Call<CommentList> call, Response<CommentList> response) {
                    if(response.body().getFirst_comment().size()!=0){
                        lastid=response.body().getLastid();
                        adapter.addData(response.body().getFirst_comment());
                        adapter.loadMoreComplete();
                    }else {
                        adapter.loadMoreEnd();
                    }
            }

            @Override
            public void onFailure(Call<CommentList> call, Throwable t) {
                    adapter.loadMoreFail();
            }
        });





    }


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

    @Override
    public void getChildView(View view, int layoutResId) {
        editText=(EditText) view.findViewById(R.id.et_news_pl);
        textView=(TextView)view.findViewById(R.id.tv_news_pl);
        textView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.tv_news_pl:

                 if (!editText.getText().toString().equals("")) {
                     Map<String, String> params = new HashMap<>();
                     params.put("act", "addcomment");
                     params.put("comment", "1");
                     params.put("articleid", newsid);
                     params.put("userid", SPUtil.getUser(this).getUserid());
                     params.put("reply_msg", editText.getText().toString());
                     params.put("comment_id",comment_id);
                     params.put("to_user_id",to_user_id);
                     params.put("to_user_name",to_user_name);
                     params.put("user_avatar",SPUtil.getUser(this).getAvatar());
                     Log.d("回复参数",newsid+
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1){
            if(resultCode==RESULT_OK){
                commentResult=(FirstCommentBean) data.getSerializableExtra("entity");
                Log.d("返回的数据",commentResult.toString());
                list1.remove(p);
                list1.add(p,commentResult);
                Log.d("返回的数据",comment.toString());

                adapter.notifyDataSetChanged();
                adapterheader.notifyDataSetChanged();

            }

        }else {
            Log.d("返回的数据","12331333");

        }

    }
}
