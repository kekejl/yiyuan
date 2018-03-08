package com.yiyuaninfo.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Interface.CommentBiz;
import com.yiyuaninfo.Interface.CommentOneBiz;
import com.yiyuaninfo.Interface.DaShangBiz;
import com.yiyuaninfo.Interface.IsCollectionBiz;
import com.yiyuaninfo.Interface.TiWenBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.CommentAdapter;
import com.yiyuaninfo.entity.Comment;
import com.yiyuaninfo.entity.FirstCommentBean;
import com.yiyuaninfo.entity.JiFen;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Network;
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
 * Created by gaocongcong on 2017/8/2.
 */

public class MediaArtDetailActivity extends BaseActivity implements View.OnClickListener, CommonPopupWindow.ViewInterface {
    private static final String TAG = "NewsDetailActivity";
    private WebView webView;
    private ProgressBar pb;
    private String url,description,image;
    private String title;
    private Intent intent;
    private CommentAdapter adapter;
    private LinearLayout linearLayout;
    private ImageView  favor, share,dashang;
    private CommonPopupWindow popupWindow,popupWindowShare;
    private EditText editText;
    private TextView textView;
    private User.UserinfoBean userinfoBean;
    private List<FirstCommentBean> list = new ArrayList<>();
    private String newsid, newstitle;
    private ImageView imageViewfavort;
    private boolean isFavort;
    private String collectionid;
    private RecyclerView recyclerView;
    private TextView  tvDaShang;

    private LinearLayout sharecancle;
    private ImageView ivweixin, ivqq, ivfriend, ivqzone, ivsina;
    private SparseBooleanArray mBooleanArray;
    com.github.library.BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private int mLastCheckedPosition = -1;
    private String  jifen="";
    private UMShareListener  shareListener;
    private View mErrorView;
    private  LinearLayout  linearLayoutparent;
    private FrameLayout  frameLayout;
    @Override
    protected int getContentView() {
        return R.layout.activity_media_art_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        frameLayout=(FrameLayout)findViewById(R.id.fl_mediaArt);
        linearLayoutparent=(LinearLayout) frameLayout.getParent();
        initErrorPage();
        linearLayout = (LinearLayout) findViewById(R.id.ll_news_pl);
        favor = (ImageView) findViewById(R.id.iv_media_favor);
        dashang = (ImageView) findViewById(R.id.iv_media_dashang);
        share = (ImageView) findViewById(R.id.iv_media_repost);
        imageViewfavort = (ImageView) findViewById(R.id.iv_media_favor);
        imageViewfavort.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        favor.setOnClickListener(this);
        share.setOnClickListener(this);
        dashang.setOnClickListener(this);
        intent = getIntent();
        url = intent.getStringExtra("newsurl");
        newsid = intent.getStringExtra("newsid");
        newstitle = intent.getStringExtra("newstitle");
        description=intent.getStringExtra("description");
        image=intent.getStringExtra("image");
        Log.d("新闻的url", url+description);
        //title=intent.getStringExtra("newstitle");
        //setToolBarTitle(title);
        webView = (WebView) findViewById(R.id.web_msginfo);
        pb = (ProgressBar) findViewById(R.id.pb_msginfo);
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
        webView.loadUrl(url);
        //设置Web视图
        webView.setWebViewClient(new webViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setProgress(newProgress);
                if (newProgress == 100) {
                    // pb.setVisibility(View.GONE);

                }
                super.onProgressChanged(view, newProgress);
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setToolBarTitle(title);
            }



        });

        if (SPUtil.isLogin(this)) {
            userinfoBean = SPUtil.getUser(this);
            if(Network.isConnected()){

                isFavort();
            }else {
                ToastUtils.showToast("暂无网络");
            }
        } else {


        }
    }

    private void isFavort() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "collectstate");
        params.put("id", newsid);
        params.put("col_type", "3");
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

    @Override
    public void onClick(View v) {
        if (SPUtil.isLogin(this)) {
            userinfoBean = SPUtil.getUser(this);}
        else {
            CommonUtil.goAactivity(MediaArtDetailActivity.this, LogInActivity.class);
           return;
        }
        switch (v.getId()) {
            case R.id.ll_news_pl:

                showPopupWindow();
                break;
            case R.id.iv_media_dashang:
                showDashang();
                break;
            case R.id.iv_view_comment:

                    CommonUtil.goAactivity(MediaArtDetailActivity.this, CommentActivity.class, "newsid", newsid, "userid", userinfoBean.getUserid());


                break;
            case R.id.iv_media_favor:
                   if(!isFavort){
                       Map<String, String> params = new HashMap<>();
                       params.put("act", "add");
                       params.put("arid", newsid);
                       params.put("type", "3");
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
            case R.id.iv_media_repost:
                /**
                 *分享
                 *
                 */
                 showShare();
                break;
            case R.id.tv_news_pl:

                if (SPUtil.isLogin(this)) {
                    userinfoBean = SPUtil.getUser(this);

                    Log.d(TAG, userinfoBean.toString());
                    if (!editText.getText().toString().equals("")) {
                        Map<String, String> params = new HashMap<>();
                        params.put("act", "useraddq");
                        params.put("userid", userinfoBean.getUserid());
                        params.put("artid", newsid);
                        params.put("arttitle", newstitle);
                        params.put("content", editText.getText().toString());
                        Log.d("提问参数",userinfoBean.getUserid()+"++"+newsid+"++"+newstitle+"++"+editText.getText().toString());
                        RetrofitUtil.getretrofit().create(TiWenBiz.class).getData(params).enqueue(new Callback<State>() {
                            @Override
                            public void onResponse(Call<State> call, Response<State> response) {
                                switch (response.body().getState()) {
                                    case "1":
                                        ToastUtils.showToast("发布成功，请到问答查看");
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
                    CommonUtil.goAactivity(MediaArtDetailActivity.this, LogInActivity.class);
                }
                break;
            case R.id.tv_dashang:

                if(!jifen.equals("")) {


                    Map<String, String> params = new HashMap<>();
                    params.put("act", "reward");
                    params.put("userid", userinfoBean.getUserid());
                    params.put("num", jifen);
                    params.put("articleid", newsid);
                    Log.d("打赏参数",userinfoBean.getUserid()+jifen+newsid);
                    RetrofitUtil.getretrofit().create(DaShangBiz.class).getData(params).enqueue(new Callback<State>() {
                        @Override
                        public void onResponse(Call<State> call, Response<State> response) {
                            if (response.body().getState().equals("1")) {
                                ToastUtils.showToast("打赏成功");
                                popupWindow.dismiss();
                            } else if (response.body().getState().equals("0")) {
                                ToastUtils.showToast("积分不足");
                            } else if (response.body().getState().equals("2")) {
                                ToastUtils.showToast("服务器繁忙！稍后再试");
                            }
                        }

                        @Override
                        public void onFailure(Call<State> call, Throwable t) {

                        }
                    });
                }else {
                    ToastUtils.showToast("请选择打赏数额");
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
        }
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId){
            case R.layout.popwindow_tw:

                textView = (TextView) view.findViewById(R.id.tv_news_pl);
                textView.setOnClickListener(this);
                editText = (EditText) view.findViewById(R.id.et_news_pl);

                break;
            case R.layout.popwindow_ds:
                recyclerView=(RecyclerView)view.findViewById(R.id.rv_dashang);
                tvDaShang=(TextView)view.findViewById(R.id.tv_dashang);
                tvDaShang.setOnClickListener(this);
                recyclerView.setLayoutManager(new GridLayoutManager(this,3));
                recyclerView.setAdapter(mAdapter = new com.github.library.BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_ds, getDatas()) {
                    @Override
                    protected void convert(final BaseViewHolder helper, final String item) {
                        helper.setText(R.id.tv_dashang_jifen,item+"积分");
                        if (mBooleanArray.get(helper.getAdapterPosition())) {

                            helper.setBackgroundRes(R.id.tv_dashang_jifen,R.drawable.button_media_press_border);
                            helper.setTextColor(R.id.tv_dashang_jifen, Color.parseColor("#ffffff"));
                        } else {
                            helper.setBackgroundRes(R.id.tv_dashang_jifen,R.drawable.button_media_border);
                            helper.setTextColor(R.id.tv_dashang_jifen,Color.parseColor("#d43c33"));
                        }

                        helper.setOnClickListener(R.id.ll_buyjifen_bg, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setItemChecked(helper.getAdapterPosition());
                                jifen=item;
                                //ToastUtils.showToast(money);
                            }
                        });

                    }
                });




                break;


            case R.layout.popwindow_share:
                sharecancle = (LinearLayout) view.findViewById(R.id.ll_my_share_cancle);
                ivfriend = (ImageView) view.findViewById(R.id.iv_my_share_friend);
                ivqq = (ImageView) view.findViewById(R.id.iv_my_share_qq);
                ivqzone = (ImageView) view.findViewById(R.id.iv_my_share_qzone);
                ivweixin = (ImageView) view.findViewById(R.id.iv_my_share_weixin);
                ivsina = (ImageView) view.findViewById(R.id.iv_my_share_sina);
                ivfriend.setOnClickListener(this);
                ivqq.setOnClickListener(this);
                ivqzone.setOnClickListener(this);
                ivweixin.setOnClickListener(this);
                ivsina.setOnClickListener(this);
                sharecancle.setOnClickListener(this);
                break;
        }

    }
    /**
     * @param position
     */
    public void setItemChecked(int position) {
        if (mLastCheckedPosition == position)
            return;

        mBooleanArray.put(position, true);

        if (mLastCheckedPosition > -1) {
            mBooleanArray.put(mLastCheckedPosition, false);
            mAdapter.notifyItemChanged(mLastCheckedPosition);
        }

        mAdapter.notifyDataSetChanged();

        mLastCheckedPosition = position;
    }


    public List<String> getDatas() {
        List<String> datas = new ArrayList<>();
        datas.add("10");
        datas.add("25");
        datas.add("30");
        datas.add("50");
        datas.add("60");
        datas.add("66");
        datas.add("88");
        datas.add("100");
        datas.add("120");
        mBooleanArray = new SparseBooleanArray(datas.size());

        return datas;
    }
    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            showErrorPage();
        }
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
     * 弹出提问界面
     */
    private void showPopupWindow() {

        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(MediaArtDetailActivity.this).inflate(R.layout.popwindow_tw, null);

        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(MediaArtDetailActivity.this)
                .setView(R.layout.popwindow_tw)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        // popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.showAtLocation(favor, Gravity.BOTTOM, 0, 0);
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

    }
    /**
     * 打赏界面
     */
    private void showDashang() {

        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(MediaArtDetailActivity.this).inflate(R.layout.popwindow_ds, null);
        Log.d("屏幕的宽度",ViewGroup.LayoutParams.MATCH_PARENT+"");

        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(MediaArtDetailActivity.this)
                .setView(R.layout.popwindow_ds)
                .setWidthAndHeight(width-200, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        // popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

        popupWindow.showAtLocation(favor, Gravity.CENTER, 0, 0);
    }


    /**
     * 弹出分享壹元服务
     */
    public void showShare() {
        if (popupWindowShare != null && popupWindowShare.isShowing()) return;
        View upView = LayoutInflater.from(MediaArtDetailActivity.this).inflate(R.layout.popwindow_share, null);

        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowShare = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_share)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindowShare.showAtLocation(favor, Gravity.BOTTOM, 0, 0);
    }

    private void ShareWeb(SHARE_MEDIA type) {
        UMImage thumb = new UMImage(this,image );
        UMWeb web = new UMWeb(url);
        web.setThumb(thumb);
        web.setDescription(description);
        web.setTitle(newstitle);
        new ShareAction(this).withMedia(web).setPlatform(type).setCallback(shareListener).share();
        shareListener=new UMShareListener() {
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

}
