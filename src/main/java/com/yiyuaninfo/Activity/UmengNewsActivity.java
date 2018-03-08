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
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.umeng.analytics.MobclickAgent;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Interface.CommentBiz;
import com.yiyuaninfo.Interface.CommentOneBiz;
import com.yiyuaninfo.Interface.IsCollectionBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.CommentAdapter;
import com.yiyuaninfo.entity.Comment;
import com.yiyuaninfo.entity.Custom;
import com.yiyuaninfo.entity.FirstCommentBean;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
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
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaocongcong on 2017/10/16.
 */

public class UmengNewsActivity   extends BaseActivity implements View.OnClickListener,CommonPopupWindow.ViewInterface {
    private static final String TAG = "NewsDetailActivity";
    private WebView webView;
    private ProgressBar pb;
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private View view;
    private LinearLayout linearLayout;
    private ImageView comment, favor, share;
    private CommonPopupWindow popupWindow;
    private EditText editText;
    private TextView textView;
    private User.UserinfoBean userinfoBean;
    private List<FirstCommentBean> list = new ArrayList<>();
    private String newsid, newstype;
    private LinearLayoutManager linearLayoutManager;
    private ImageView imageViewfavort;
    private boolean isFavort;
    private String collectionid;
    private List<Custom> listcustom=new ArrayList<>();
    private String url;
    @Override
    protected int getContentView() {
        return R.layout.activity_umengnewsdetail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        view = LayoutInflater.from(this).inflate(R.layout.webview, null);
        linearLayout = (LinearLayout) findViewById(R.id.ll_news_pl);
        comment = (ImageView) findViewById(R.id.iv_view_comment);
        favor = (ImageView) findViewById(R.id.iv_news_favor);
        share = (ImageView) findViewById(R.id.iv_news_repost);
        imageViewfavort = (ImageView) findViewById(R.id.iv_news_favor);
        imageViewfavort.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        comment.setOnClickListener(this);
        favor.setOnClickListener(this);
        share.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_news_detail);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        //title=intent.getStringExtra("newstitle");
        //setToolBarTitle(title);
        webView = (WebView) view.findViewById(R.id.wv_news_detail);
        pb = (ProgressBar) view.findViewById(R.id.pb_msginfo);
        pb.setMax(100);
        pb.setVisibility(View.GONE);
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        //设置Web视图
        webView.setWebViewClient(new UmengNewsActivity.webViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setProgress(newProgress);
                if (newProgress == 100) {
                    // pb.setVisibility(View.GONE);

                }
                super.onProgressChanged(view, newProgress);
            }

        });

        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setToolBarTitle(title);
            }
        };
        webView.setWebChromeClient(wvcc);
        if (SPUtil.isLogin(this)) {
            userinfoBean = SPUtil.getUser(this);

            isFavort();
        } else {


        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        Bundle bun = getIntent().getExtras();
        if (bun != null) {
            Set<String> keySet = bun.keySet();
            for (String key : keySet) {
                String value = bun.getString(key);
                //ToastUtils.showToast(value);
                Custom custom = new Custom(key, value);
                listcustom.add(custom);

            }
        }
        if (listcustom.size() != 0) {
            newstype=listcustom.get(0).getValue();
            newsid=listcustom.get(1).getValue();
           // ToastUtils.showToast("id"+newsid+"\n"+"type"+newstype);

            url= Constants.NEWSURL.concat("id=" + listcustom.get(0).getValue() + "&userid=");

            webView.loadUrl(url);
            newsid=listcustom.get(0).getValue();
            getData();


        }
        Log.d("友盟自定义参数111", listcustom.toString());

    }




    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }




    //Web视图

    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    /**
     *
     * 判断是否收藏
     *
     */

    private void isFavort() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "collectstate");
        params.put("id", newsid);
        params.put("col_type", "1");
        params.put("userid", userinfoBean.getUserid());

        RetrofitUtil.getretrofit().create(IsCollectionBiz.class).getData(params).enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                Log.d("收藏返回的数据", response.body().getState().toString());
                collectionid=response.body().getState();
                if (response.body().getState().equals("0")) {
                    imageViewfavort.setImageResource(R.drawable.newsdetail01);
                    isFavort = false;
                }
                else {
                    imageViewfavort.setImageResource(R.drawable.newsdetail02);
                    isFavort = true;
                }
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {


            }
        });

    }
    /**
     * webview 加载完  显示评论
     */
    private void getData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "first");
        params.put("articleid", newsid);
        RetrofitUtil.getretrofit().create(CommentBiz.class).getData(params).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                list = response.body().getFirst_comment();
                adapter = new CommentAdapter(R.layout.item_comment, list);
                recyclerView.setAdapter(adapter);
                adapter.addHeaderView(view);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        FirstCommentBean comment = (FirstCommentBean) adapter.getData().get(position);
                        CommonUtil.goAactivity(UmengNewsActivity.this, CommentDetailActivity.class, "commentid", comment.getCommentid());
                    }
                });

            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });

    }



    @Override
    public void onClick(View v) {
        if (SPUtil.isLogin(this)) {
            userinfoBean = SPUtil.getUser(this);}
        else {
            CommonUtil.goAactivity(UmengNewsActivity.this, LogInActivity.class);
            return;
        }


        switch (v.getId()) {
            case R.id.ll_news_pl:

                showPopupWindow();
                break;
            case R.id.iv_view_comment:

                CommonUtil.goAactivity(UmengNewsActivity.this, CommentActivity.class, "newsid", newsid, "userid", userinfoBean.getUserid());


                break;
            case R.id.iv_news_favor:
                if(!isFavort){
                    Map<String, String> params = new HashMap<>();
                    params.put("act", "add");
                    params.put("arid", newsid);
                    params.put("type", "1");
                    params.put("userid",userinfoBean.getUserid());
                    Log.d("用户的id",userinfoBean.getUserid());
                    RetrofitUtil.getretrofit().create(IsCollectionBiz.class).getData(params).enqueue(new Callback<State>() {
                        @Override
                        public void onResponse(Call<State> call, Response<State> response) {
                            Log.d("点击收藏返回的状态",response.body().getState());
                            collectionid=response.body().getState();
                            if(!response.body().getState().equals("0")){
                                imageViewfavort.setImageResource(R.drawable.newsdetail02);
                                isFavort=true;
                                ToastUtils.showToast("收藏成功");

                            }else {
                                ToastUtils.showToast("收藏失败");
                            }

                        }

                        @Override
                        public void onFailure(Call<State> call, Throwable t) {

                        }
                    });
                }else {
                    Map<String, String> params = new HashMap<>();
                    params.put("act", "del");
                    params.put("colid", collectionid);
                    params.put("userid",userinfoBean.getUserid());
                    RetrofitUtil.getretrofit().create(IsCollectionBiz.class).getData(params).enqueue(new Callback<State>() {
                        @Override
                        public void onResponse(Call<State> call, Response<State> response) {
                            Log.d("点击收藏返回的状态1",response.body().getState());
                            if(response.body().getState().equals("1")){
                                imageViewfavort.setImageResource(R.drawable.newsdetail01);
                                isFavort=false;
                                ToastUtils.showToast("收藏已取消");

                            }else {
                                ToastUtils.showToast("失败");
                            }

                        }

                        @Override
                        public void onFailure(Call<State> call, Throwable t) {

                        }
                    });
                }

                break;
            case R.id.iv_news_repost:
                break;
            case R.id.tv_news_pl:
                if (SPUtil.isLogin(this)) {
                    userinfoBean = SPUtil.getUser(this);

                    Log.d(TAG, userinfoBean.toString());
                    if (!editText.getText().toString().equals("")) {
                        Map<String, String> params = new HashMap<>();
                        params.put("act", "addcomment");
                        params.put("comment", "0");
                        params.put("articleid", newsid);
                        params.put("userid", userinfoBean.getUserid());
                        params.put("reply_msg", editText.getText().toString());
                        RetrofitUtil.getretrofit().create(CommentOneBiz.class).getData(params).enqueue(new Callback<State>() {
                            @Override
                            public void onResponse(Call<State> call, Response<State> response) {
                                switch (response.body().getState()) {
                                    case "1":
                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
                                        Date curDate = new Date(System.currentTimeMillis());
                                        String str = formatter.format(curDate);
                                        ToastUtils.showToast("发布成功");
                                        popupWindow.dismiss();
                                        FirstCommentBean entity = new FirstCommentBean(
                                                userinfoBean.getAvatar(),
                                                userinfoBean.getUsername(),
                                                userinfoBean.getUserid(),
                                                editText.getText().toString(),
                                                str,
                                                "0", "","","");
                                        list.add(0, entity);
                                        adapter.setNewData(list);
                                        MoveToPosition(linearLayoutManager, recyclerView, 1);

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
                    CommonUtil.goAactivity(UmengNewsActivity.this, LogInActivity.class);
                }
                break;
        }
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        textView = (TextView) view.findViewById(R.id.tv_news_pl);
        textView.setOnClickListener(this);
        editText = (EditText) view.findViewById(R.id.et_news_pl);

    }


    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }

    /**
     * 弹出客服
     */
    private void showPopupWindow() {

        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(UmengNewsActivity.this).inflate(R.layout.popwindow_pl, null);

        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(UmengNewsActivity.this)
                .setView(R.layout.popwindow_pl)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        // popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.showAtLocation(comment, Gravity.BOTTOM, 0, 0);
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }


    }
