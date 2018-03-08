package com.yiyuaninfo.Activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.view.BaseDialog;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Activity.MyActivity.SettingActivity;
import com.yiyuaninfo.Interface.CollectionBiz;
import com.yiyuaninfo.Interface.CommentBiz;
import com.yiyuaninfo.Interface.CommentOneBiz;
import com.yiyuaninfo.Interface.IsCollectionBiz;
import com.yiyuaninfo.Interface.ZanBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.CommentAdapter;
import com.yiyuaninfo.entity.Collection;
import com.yiyuaninfo.entity.Comment;
import com.yiyuaninfo.entity.CommentReply;
import com.yiyuaninfo.entity.FirstCommentBean;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.BaseProgressDialog;
import com.yiyuaninfo.view.MyDecoration;
import com.yiyuaninfo.view.ProgressDialog;
import com.yiyuaninfo.view.ProgressWebView;

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
 * Created by gaocongcong on 2017/8/2.
 */

public class NewsDetailActivity extends BaseActivity implements DialogInterface, View.OnClickListener, CommonPopupWindow.ViewInterface {
    private static final String TAG = "NewsDetailActivity";
    private WebView webView;
    private ProgressBar pb;
    private String url, title, description;
    private Intent intent;
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    //   private View view;
    private LinearLayout linearLayout;
    private ImageView comment, favor, share;
    private CommonPopupWindow popupWindow, popupWindowShare;
    private EditText editText;
    private TextView textView;
    private User.UserinfoBean userinfoBean;
    private List<FirstCommentBean> list = new ArrayList<>();
    private String newsid;
    private LinearLayoutManager linearLayoutManager;
    private ImageView imageViewfavort;
    private boolean isFavort;
    private String collectionid;
    private FirstCommentBean firstCommentBean;
    private boolean iszan;
    int b;
    private LinearLayout sharecancle;
    private ImageView ivweixin, ivqq, ivfriend, ivqzone, ivsina;
    private String comment_id, to_user_id, to_user_name;
    private UMShareListener shareListener;
    private NewsEntity.HangqingBean entity;
    private BaseProgressDialog dialog;

    private ScrollView scrollView;
    private View mErrorView;
    private LinearLayout linearLayoutparent;
    private boolean islike = false;

    private ImageView imageViewback;
    private TextView textViewtitle;
    private RelativeLayout textsize, link;

    private WebSettings  webSettings;

    int yourChoice;
    private int  numsize;


    @Override

    protected int getContentView() {
        return R.layout.activity_newsdetail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        isshowToolBar(false);
        dialog = new ProgressDialog(this).setLabel("加载中...");
        dialog.show();
        scrollView = (ScrollView) findViewById(R.id.sll_news);
        linearLayoutparent = (LinearLayout) scrollView.getParent();
        initErrorPage();
        imageViewback = (ImageView) findViewById(R.id.image_back_newsdetail);
        textViewtitle = (TextView) findViewById(R.id.tvTitle_newdetail);
        imageViewback.setOnClickListener(this);
        // view = LayoutInflater.from(this).inflate(R.layout.webview, null);
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
        recyclerView.setVisibility(View.GONE);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        intent = getIntent();
        entity = (NewsEntity.HangqingBean) intent.getSerializableExtra("entity");
        title = entity.getTitle();
        description = entity.getDescription();
        newsid = entity.getId();
        if (SPUtil.isLogin(this)) {
            userinfoBean = SPUtil.getUser(this);
            Log.d("收藏返回的数据", "已登录");

            isFavort();
        } else {
            Log.d("收藏返回的数据", "未登录");


        }
        if (SPUtil.isLogin(this)) {

            url = Constants.NEWSURL.concat("id=" + entity.getId() + "&userid=" + userinfoBean.getUserid());
        } else {
            url = Constants.NEWSURL.concat("id=" + entity.getId());

        }

        //url="http://yyapp.1yuaninfo.com/app/share/video_share.php?id=1350";


        // newstitle = intent.getStringExtra("newstitle");
        Log.d("新闻的url", url);
        //title=intent.getStringExtra("newstitle");
        //setToolBarTitle(title);
        webView = (WebView) findViewById(R.id.wv_news_detail);
        pb = (ProgressBar) findViewById(R.id.pb_msginfo);
        pb.setMax(100);
        pb.setVisibility(View.GONE);
         webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);


        switch (SPUtil.getData(NewsDetailActivity.this, "size", "0")) {
            case "0":
                webSettings.setTextZoom(100);

                break;
            case "1":
                webSettings.setTextZoom(120);

                break;
            case "2":
                webSettings.setTextZoom(150);

                break;
            case "3":
                webSettings.setTextZoom(200);
                break;
        }
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页

        webView.loadUrl(url);
        //设置Web视图
        webView.setWebViewClient(new webViewClient());
        webView.addJavascriptInterface(new JavascriptHandler(), "handler");

        webView.setWebChromeClient(new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setProgress(newProgress);
                dialog.show();

                if (newProgress == 100) {
                    // pb.setVisibility(View.GONE);
                    dialog.dismiss();
                    recyclerView.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, final String message, JsResult result) {
                Log.d("main", "onJsAlert:" + message);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        new AlertDialog.Builder(NewsDetailActivity.this)
                                .setTitle("提示")
                                .setMessage(message)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // webView.reload();//重写刷新页面
                                        dialog.dismiss();

                                    }
                                })
                                .setNegativeButton("取消", null)
                                .show();

                    }
                });
                result.confirm();//这里必须调用，否则页面会阻塞造成假死
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, final String message, final JsResult result) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(NewsDetailActivity.this);
                builder
                        .setTitle("提示")
                        // .setCustomTitle(title)
                        // .setView(msg)
                        .setMessage(message)

                        .setPositiveButton("确定", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("不喜欢", "不喜欢");

                                result.confirm();
                                islike = true;
                            }
                        })
                        .setNegativeButton("取消", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                result.cancel();
                            }
                        });
                // setNeutralButton
                builder.setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        result.cancel();
                    }
                });

                // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
                builder.setOnKeyListener(new OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        Log.v("onJsConfirm", "keyCode==" + keyCode + "event=" + event);
                        return true;
                    }
                });
                // 禁止响应按back键的事件
                // builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();


//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        new AlertDialog.Builder(NewsDetailActivity.this)
//                                .setTitle("提示")
//                                .setMessage(message)
//                                .setPositiveButton("确定", new OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // webView.reload();//重写刷新页面
//                                        result.confirm();
//
//                                    }
//                                })
//                                .setNegativeButton("取消", new OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        result.cancel();
//                                    }
//                                })
//                                .show();
//
//                    }
//                });
//                result.confirm();


                return true;
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                //  setToolBarTitle(title);
                textViewtitle.setText(title);

            }
        });
        if (SPUtil.isLogin(this)) {
            if (Network.isConnected()) {

                getData();
            } else {
                ToastUtils.showToast("暂无网络");
            }
        }

    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            webView.loadUrl("javascript:window.handler.resize(document.body.getBoundingClientRect().height)");
            super.onPageFinished(view, url);
            dialog.dismiss();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);

            showErrorPage();
        }
    }

    /**
     * 检查时候收藏过
     */
    private void isFavort() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "collectstate");
        params.put("id", newsid);
        params.put("col_type", "1");
        params.put("userid", userinfoBean.getUserid());

        Log.d("收藏返回的数据", newsid + "++" + userinfoBean.getUserid());

        RetrofitUtil.getretrofit().create(IsCollectionBiz.class).getData(params).enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                Log.d("收藏返回的数据", response.body().getState().toString());
                collectionid = response.body().getState();
                if (response.body().getState().equals("0")) {
                    imageViewfavort.setImageResource(R.drawable.newsdetail01);
                    isFavort = false;
                } else {
                    imageViewfavort.setImageResource(R.drawable.newsdetail02);
                    isFavort = true;
                }
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {
                Log.d("收藏返回的数据", "失败");


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
        params.put("userid", userinfoBean.getUserid());

        RetrofitUtil.getretrofit().create(CommentBiz.class).getData(params).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                list = response.body().getFirst_comment();
                adapter = new CommentAdapter(R.layout.item_comment, list);
                // adapter.addHeaderView(view);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                        if (SPUtil.isLogin(NewsDetailActivity.this)) {
                            FirstCommentBean comment = (FirstCommentBean) adapter.getData().get(position);
                            Intent intent = new Intent(NewsDetailActivity.this, CommentDetailActivity.class);

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("entity", comment);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        //CommonUtil.goAactivity(NewsDetailActivity.this, CommentDetailActivity.class, "commentid", comment.getCommentid());
                    }


                });


                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                        firstCommentBean = (FirstCommentBean) adapter.getData().get(position);
                        switch (view.getId()) {
                            case R.id.iv_newsdetail_zan:
                                ToastUtils.showToast(position + "");
                                if (SPUtil.isLogin(NewsDetailActivity.this)) {
                                    final ImageView imageView1 = (ImageView) view;
                                    final String zan = firstCommentBean.getZan_count();

                                    String userid = SPUtil.getUser(NewsDetailActivity.this).getUserid();
                                    if (firstCommentBean.getFlag().equals("0")) {
                                        iszan = false;
                                        imageView1.setBackgroundResource(R.drawable.dz00);
                                    } else if (firstCommentBean.getFlag().equals("1")) {
                                        iszan = true;
                                        imageView1.setBackgroundResource(R.drawable.dz02);

                                    }

                                    b = Integer.parseInt(zan);
                                    if (!iszan) {


                                        Map<String, String> params = new HashMap<>();
                                        params.put("act", "addlike");
                                        params.put("userid", userid);
                                        params.put("comment", "0");
                                        params.put("commentid", firstCommentBean.getCommentid());


                                        RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
                                            @Override
                                            public void onResponse(Call<State> call, Response<State> response) {
                                                Log.d("CheckBox", response.body().getState() + "@@");

                                                if (response.body().getState().equals("1")) {
                                                    imageView1.setBackgroundResource(R.drawable.dz02);
                                                    //ToastUtils.showToast("点赞成功");
                                                    firstCommentBean.setFlag("1");
                                                    b = b + 1;
                                                    firstCommentBean.setZan_count(b + "");
                                                    // adapter.notifyDataSetChanged();
                                                    // adapter.notifyItemChanged(position);
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
                                        params.put("commentid", firstCommentBean.getCommentid());
                                        RetrofitUtil.getretrofit().create(ZanBiz.class).getData(params).enqueue(new Callback<State>() {
                                            @Override
                                            public void onResponse(Call<State> call, Response<State> response) {
                                                if (response.body().getState().equals("1")) {

                                                    imageView1.setBackgroundResource(R.drawable.dz00);
                                                    //ToastUtils.showToast("点赞取消");
                                                    b = b - 1;
                                                    firstCommentBean.setZan_count(b + "");
                                                    firstCommentBean.setFlag("0");
                                                    adapter.notifyDataSetChanged();

                                                } else {
                                                    ToastUtils.showToast("网络繁忙  稍后再试");

                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<State> call, Throwable t) {

                                            }
                                        });
                                    }
                                } else {
                                    CommonUtil.goAactivity(NewsDetailActivity.this, LogInActivity.class);

                                }

                                break;
                            case R.id.tv_newsdetail_hf:

//                                if(SPUtil.isLogin(NewsDetailActivity.this)) {
//
//                                    comment_id = firstCommentBean.getCommentid();
//                                    to_user_id = firstCommentBean.getUserid();
//                                    to_user_name = firstCommentBean.getUsername();
//                                showPopupWindow();
//                                }else {
//
//                                    CommonUtil.goAactivity(NewsDetailActivity.this,LogInActivity.class);
//
//                                }


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

    @Override
    public void onClick(View v) {
        if (SPUtil.isLogin(this)) {
            userinfoBean = SPUtil.getUser(this);
        } else {
            CommonUtil.goAactivity(NewsDetailActivity.this, LogInActivity.class);
            return;
        }


        switch (v.getId()) {
            case R.id.ll_news_pl:

                showPopupWindow();
                break;
            case R.id.iv_view_comment:

                CommonUtil.goAactivity(NewsDetailActivity.this, CommentActivity.class, "newsid", newsid, "userid", userinfoBean.getUserid());


                break;
            case R.id.iv_news_favor:
                if (!isFavort) {
                    Map<String, String> params = new HashMap<>();
                    params.put("act", "add");
                    params.put("arid", newsid);
                    params.put("type", "1");
                    params.put("userid", userinfoBean.getUserid());
                    Log.d("用户的id", userinfoBean.getUserid());
                    RetrofitUtil.getretrofit().create(IsCollectionBiz.class).getData(params).enqueue(new Callback<State>() {
                        @Override
                        public void onResponse(Call<State> call, Response<State> response) {
                            Log.d("点击收藏返回的状态", response.body().getState());
                            collectionid = response.body().getState();
                            if (!response.body().getState().equals("0")) {
                                imageViewfavort.setImageResource(R.drawable.newsdetail02);
                                isFavort = true;
                                ToastUtils.showToast("收藏成功");

                            } else {
                                ToastUtils.showToast("收藏失败");
                            }

                        }

                        @Override
                        public void onFailure(Call<State> call, Throwable t) {

                        }
                    });
                } else {
                    Map<String, String> params = new HashMap<>();
                    params.put("act", "del");
                    params.put("colid", collectionid);
                    params.put("userid", userinfoBean.getUserid());
                    RetrofitUtil.getretrofit().create(IsCollectionBiz.class).getData(params).enqueue(new Callback<State>() {
                        @Override
                        public void onResponse(Call<State> call, Response<State> response) {
                            Log.d("点击收藏返回的状态1", response.body().getState());
                            if (response.body().getState().equals("1")) {
                                imageViewfavort.setImageResource(R.drawable.newsdetail01);
                                isFavort = false;
                                ToastUtils.showToast("收藏已取消");

                            } else {
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
                showShare();
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
                                                "0", "", "", "");
                                        list.add(0, entity);
                                        adapter.setNewData(list);
                                        //MoveToPosition(linearLayoutManager, recyclerView, 1);

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
                    CommonUtil.goAactivity(NewsDetailActivity.this, LogInActivity.class);
                }
                break;

            case R.id.iv_my_share_friend:
                ShareWeb(SHARE_MEDIA.WEIXIN_CIRCLE);
                popupWindowShare.dismiss();

                break;
            case R.id.iv_my_share_qq:
                ShareWeb(SHARE_MEDIA.QQ);
                popupWindowShare.dismiss();
                break;
            case R.id.iv_my_share_weixin:
                ShareWeb(SHARE_MEDIA.WEIXIN);
                popupWindowShare.dismiss();
                break;
            case R.id.iv_my_share_qzone:
                ShareWeb(SHARE_MEDIA.QZONE);
                popupWindowShare.dismiss();
                break;
            case R.id.iv_my_share_sina:
                ShareWeb(SHARE_MEDIA.SINA);
                popupWindowShare.dismiss();
                break;
            case R.id.ll_my_share_cancle:
                popupWindowShare.dismiss();
                break;
            case R.id.image_back_newsdetail:

                if (islike) {
                    Intent intent2 = new Intent();
                    setResult(RESULT_OK, intent2);
                    finish();
                } else {
                    finish();
                    // finish();
                }

                break;
            case R.id.share_link:

                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(url);
                ToastUtils.showToast("复制成功");
                break;
            case R.id.share_textsize:

                 showTextSizePopupWindow();
                popupWindowShare.dismiss();
                break;
        }
    }

    private void showTextSizePopupWindow() {

        final String[] items = { "标准","大","极大","超级大" };
        yourChoice = -1;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(NewsDetailActivity.this);
        singleChoiceDialog.setTitle("字体大小");
        // 第二个参数是默认选项，此处设置为0
        numsize=Integer.parseInt(SPUtil.getData(NewsDetailActivity.this,"size","0"));
        singleChoiceDialog.setSingleChoiceItems(items, numsize,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",

                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice != -1) {
//                            Toast.makeText(SettingActivity.this,
//                                    "你选择了" + items[yourChoice]+"+++"+yourChoice,
//                                    Toast.LENGTH_SHORT).show();
                            SPUtil.setData(NewsDetailActivity.this,"size",yourChoice+"");


                        }
                        switch (SPUtil.getData(NewsDetailActivity.this, "size", "0")) {
                            case "0":
                                webSettings.setTextZoom(100);

                                break;
                            case "1":
                                webSettings.setTextZoom(120);

                                break;
                            case "2":
                                webSettings.setTextZoom(150);

                                break;
                            case "3":
                                webSettings.setTextZoom(200);
                                break;
                        }

                        webView.reload();
                    }
                });
        singleChoiceDialog.show();



    }



    @Override
    public void getChildView(View view, int layoutResId) {

        switch (layoutResId) {
            case R.layout.popwindow_pl:

                textView = (TextView) view.findViewById(R.id.tv_news_pl);
                textView.setOnClickListener(this);
                editText = (EditText) view.findViewById(R.id.et_news_pl);
                break;
            case R.layout.popwindow_share:
                sharecancle = (LinearLayout) view.findViewById(R.id.ll_my_share_cancle);
                ivfriend = (ImageView) view.findViewById(R.id.iv_my_share_friend);
                ivqq = (ImageView) view.findViewById(R.id.iv_my_share_qq);
                ivqzone = (ImageView) view.findViewById(R.id.iv_my_share_qzone);
                ivweixin = (ImageView) view.findViewById(R.id.iv_my_share_weixin);
                ivsina = (ImageView) view.findViewById(R.id.iv_my_share_sina);
                textsize = (RelativeLayout) view.findViewById(R.id.share_textsize);
                link = (RelativeLayout) view.findViewById(R.id.share_link);
                link.setOnClickListener(this);
                textsize.setOnClickListener(this);
                ivfriend.setOnClickListener(this);
                ivqq.setOnClickListener(this);
                ivqzone.setOnClickListener(this);
                ivweixin.setOnClickListener(this);
                ivsina.setOnClickListener(this);
                sharecancle.setOnClickListener(this);
                break;
        }

    }

    @Override
    public void cancel() {

    }

    @Override
    public void dismiss() {


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
     * 弹出分享壹元服务
     */
    public void showShare() {
        if (popupWindowShare != null && popupWindowShare.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popwindow_share, null);

        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowShare = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_share)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindowShare.showAtLocation(recyclerView, Gravity.BOTTOM, 0, 0);
    }


    /**
     * 弹出评论窗口
     */
    private void showPopupWindow() {

        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(NewsDetailActivity.this).inflate(R.layout.popwindow_pl, null);

        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(NewsDetailActivity.this)
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

    private void ShareWeb(SHARE_MEDIA type) {
        UMImage thumb = new UMImage(this, entity.getPicurl());
        UMWeb web = new UMWeb(url);
        web.setThumb(thumb);
        web.setDescription(description);
        web.setTitle(title);
        new ShareAction(this).withMedia(web).setPlatform(type).setCallback(shareListener).share();
        shareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                ToastUtils.showToast("正在分享");
            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {
                ToastUtils.showToast("分享成功");

            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                ToastUtils.showToast("分享失败");

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                ToastUtils.showToast("分享取消");

            }
        };
    }

    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    public void showErrorPage() {
        linearLayoutparent.removeAllViews(); //移除加载网页错误时，默认的提示信息
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayoutparent.addView(mErrorView, 0, layoutParams); //添加自定义的错误提示的View

    }

    /***
     * 显示加载失败时自定义的网页
     */
    public void initErrorPage() {
        if (mErrorView == null) {
            mErrorView = View.inflate(this, R.layout.error_view, null);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (islike) {
                Intent intent2 = new Intent();
                setResult(RESULT_OK, intent2);
                finish();
            } else {
                finish();
                // finish();
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }


    class JavascriptHandler {
        @JavascriptInterface
        public void resize(final float height) {
            NewsDetailActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView.setLayoutParams(new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, (int) (height * getResources().getDisplayMetrics().density)));
                }
            });
        }
    }
}
