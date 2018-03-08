package com.yiyuaninfo.Fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.yiyuaninfo.Activity.AllNiuActivity;
import com.yiyuaninfo.Activity.JFSCActivity;
import com.yiyuaninfo.Activity.MainActivity;
import com.yiyuaninfo.Activity.MyActivity.AboutActivity;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Activity.MyActivity.MessageListActivity;
import com.yiyuaninfo.Activity.MyActivity.MyCenter.MyCenterActivity;
import com.yiyuaninfo.Activity.MyActivity.MyFavoriteActivity;
import com.yiyuaninfo.Activity.MyActivity.MyOrderActivity;
import com.yiyuaninfo.Activity.MyActivity.SettingActivity;
import com.yiyuaninfo.Activity.QQKFActivity;
import com.yiyuaninfo.Interface.IsCheckLogInBiz;
import com.yiyuaninfo.Interface.QianDaoBiz;
import com.yiyuaninfo.Interface.UserInfoBiz;
import com.yiyuaninfo.Listener.PermissionsResultListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.IdentityImageView;
import com.yiyuaninfo.Ui.PullScrollView;
import com.yiyuaninfo.entity.QianDao;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.DateUtil;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2017/2/23.
 */

public class Myfragment extends BaseFragment implements PullScrollView.OnTurnListener, CommonPopupWindow.ViewInterface, View.OnClickListener,MainActivity.Submit {
    @BindView(R.id.layoutAbout)
    LinearLayout layoutAbout;
    @BindView(R.id.image_my_setting)
    ImageView setting;
    @BindView(R.id.layoutMyCenter)
    LinearLayout layoutMyCenter;
    @BindView(R.id.layoutMessage)
    LinearLayout layoutMessage;
    @BindView(R.id.layoutMyFavorite)
    LinearLayout layoutMyFavorite;
    @BindView(R.id.layoutMyOrder)
    LinearLayout layoutMyOrder;
    @BindView(R.id.layoutIntegral)
    LinearLayout layoutIntegral;
    @BindView(R.id.layoutShare)
    LinearLayout layoutShare;
    @BindView(R.id.layoutService)
    LinearLayout layoutService;
    @BindView(R.id.tv_my_nickname)
    TextView nickname;
    @BindView(R.id.tv_my_date)
    TextView viptime;
    @BindView(R.id.tv_my_jifen)
    TextView jifen;
    //    @BindView(R.id.ry_signin)
//    RecyclerView recyclerViewSignin;
    @BindView(R.id.imageMyHead)
    IdentityImageView head;
    @BindView(R.id.rl_my_login)
    RelativeLayout relativeLayout;
    @BindView(R.id.ll_my_login)
    LinearLayout linearLayout;
    @BindView(R.id.ll_my_dingyue)
    LinearLayout llDingyue;
    @BindView(R.id.rv_my_qiandao)
    RelativeLayout qiandao;
    @BindView(R.id.tv_my_qiandao)
    TextView tvqiandao;
    @BindView(R.id.view_view)
    View viewview;
    private boolean isqiandao = false;
    private CommonPopupWindow popupWindow;
    private CommonPopupWindow popupWindowkf;
    private CommonPopupWindow popupWindowqd;
    private CommonPopupWindow popupWindowtishi;
    private int i = 10;
    private int angle = 30;
    private boolean flag;//文字是否出现
    private TextView qq;
    private TextView call;
    private TextView cancle;
    private LinearLayout sharecancle;
    private ImageView ivweixin, ivqq, ivfriend, ivqzone, ivsina;
    private static final int PER_REQUEST_CODE = 2;
    private User.UserinfoBean userinfoBean;
    private File path;
    private UMShareListener shareListener;
    private TextView tvGoJFSC;
    private TextView tvjifen;
    private String string, path1;
    private ImageView ivclose;


    private IntentFilter intentFilter;
    private BroadReceiver broadReceiver;


    public static Myfragment newInstance(String title) {
        Myfragment f = new Myfragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void initData() {
        path1 = getActivity().getExternalFilesDir("yiyuaninfo").getPath() + "/myhead.jpg";

        setData();

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.text_change");
        broadReceiver = new BroadReceiver();
        getActivity().registerReceiver(broadReceiver, intentFilter);

        // SingIn();

    }

    private void setData() {

        if (SPUtil.isLogin(getActivity())) {

            isQianDao();
        }

        path = getActivity().getExternalFilesDir("yiyuaninfo");
        if (SPUtil.isLogin(getActivity())) {

            relativeLayout.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
            llDingyue.setVisibility(View.VISIBLE);
            viewview.setVisibility(View.GONE);
            userinfoBean = SPUtil.getUser(getActivity());
            Log.d("用户的信息111", userinfoBean.toString());
            nickname.setText("昵称: " + userinfoBean.getUsername());
            viptime.setText("会员: " + DateUtils.getTimePoint(userinfoBean.getExpiretime())+"到期");
            jifen.setText("积分: " + userinfoBean.getIntegral());
        } else {
            relativeLayout.setVisibility(View.GONE);
            llDingyue.setVisibility(View.GONE);
            viewview.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
        }


        Log.d("下载头像地址11111", path.getPath() + "myhead.jpg");


        // Bitmap bt = BitmapFactory.decodeFile(path + "myhead.jpg");// 从SD卡中找头像，转换成Bitmap
        Bitmap bt = BitmapFactory.decodeFile(path.getPath() + "/myhead.jpg");// 从SD卡中找头像，转换成Bitmap


        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            head.getBigCircleImageView().setImageDrawable(drawable);
        } else {

            // head.getBigCircleImageView().setImageResource(R.drawable.head_default);
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
            if (SPUtil.isLogin(getActivity())) {

                if(SPUtil.getUser(getActivity()).getAvatar()==null){
                    head.getBigCircleImageView().setImageResource(R.drawable.head_default);

                }else {

                if (SPUtil.getUser(getActivity()).getAvatar().length() == 0) {
                    head.getBigCircleImageView().setImageResource(R.drawable.head_default);

                }else {

                EasyHttp.downLoad(userinfoBean.getAvatar())
                        .savePath(path.getPath())
                        .saveName("myhead.jpg")
                        .execute(new DownloadProgressCallBack<String>() {
                            @Override
                            public void onStart() {


                            }

                            @Override
                            public void onError(ApiException e) {

                            }

                            @Override
                            public void update(long bytesRead, long contentLength, boolean done) {
                                //ToastUtils.showToast("下载完成");
                                //Log.d("下载完成", "下载完成");


                            }

                            @Override
                            public void onComplete(String path) {
                                File mfile = new File(path);
                                Log.d("下载头像地址", path);

                                if (mfile.exists()) {
                                    Bitmap bm = BitmapFactory.decodeFile(path);
                                    Drawable drawable = new BitmapDrawable(bm);// 转换成drawable
                                    head.getBigCircleImageView().setImageDrawable(drawable);

                                }


                            }
                        });
            }
            }
            }
            else {
                head.getBigCircleImageView().setImageResource(R.drawable.head_default);

            }


        }
        //填充头像
        //head.getBigCircleImageView().setImageResource(R.drawable.my_head_default);
        //改变图片比例
        // head.setRadiusScale(0.2f);
        //增加边框
        //head.setBorderWidth(10);
        // head.setBorderColor(R.color.colorAccent);
        //增加进度条，没按一次加10,以及改变的角度
        // head.setIsprogress(true);
        // head.setProgressColor(R.color.white);
        //head.setProgress( 100);
        if (SPUtil.isLogin(getActivity())) {
            Log.d("取出用户的数据", userinfoBean.toString());

            if (userinfoBean.getGroupid().contains("3")) {

                head.getSmallCircleImageView().setImageResource(R.drawable.my_vip);

            }else {
                viptime.setText("会员: " +"未开通");

            }

        } else {
            head.getSmallCircleImageView().setVisibility(View.GONE);

        }

        // head.setAngle(angle += 7);
    }

    private void isQianDao() {

        if (SPUtil.isLogin(getActivity())) {
            Map<String, String> params = new HashMap<>();
            params.put("act", "quesign");
            params.put("userid", SPUtil.getUser(getActivity()).getUserid());

            RetrofitUtil.getretrofit().create(QianDaoBiz.class).getData(params).enqueue(new Callback<QianDao>() {
                @Override
                public void onResponse(Call<QianDao> call, Response<QianDao> response) {
                    if (response.body().getState().equals("0")) {
                        isqiandao = true;
                        tvqiandao.setText("已签到" + response.body().getSigntimes() + "天");
                    } else if (response.body().getState().equals("2")) {
                        isqiandao = false;
                        tvqiandao.setText("签到");

                    }
                }

                @Override
                public void onFailure(Call<QianDao> call, Throwable t) {

                }
            });
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
        getUserInfo();
        isQianDao();
    }

    @Override
    protected void initView() {


    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_my;


    }

    @OnClick({
            R.id.image_my_setting,
            R.id.layoutAbout,
            R.id.layoutMyCenter,
            R.id.layoutMessage,
            R.id.layoutMyFavorite,
            R.id.layoutMyOrder,
            R.id.layoutIntegral,
            R.id.imageMyHead,
            R.id.layoutService,
            R.id.ll_my_login,
            R.id.ll_my_dingyue,
            R.id.rv_my_qiandao,
            R.id.layoutShare})
    public void jump(View view) {

        if(Network.isConnected()){


        switch (view.getId()) {
            case R.id.layoutMyFavorite:

                startActivity(new Intent(getActivity(), MyFavoriteActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.layoutMessage:
                startActivity(new Intent(getActivity(), MessageListActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.layoutMyOrder:

                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.layoutIntegral:
                if (SPUtil.isLogin(getActivity())) {

                    startActivity(new Intent(getActivity(), JFSCActivity.class));
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                } else {
                    CommonUtil.goAactivity(getActivity(), LogInActivity.class);

                }

                break;
            case R.id.layoutShare:
                //CommonUtil.showToast(getActivity(), "敬请期待！");
                showAll();
                break;
            case R.id.layoutService:

                showKF();
                break;
            case R.id.ll_my_dingyue:
                if (SPUtil.isLogin(getActivity())) {

                    CommonUtil.goAactivity(getActivity(), AllNiuActivity.class);
                } else {
                    CommonUtil.goAactivity(getActivity(), LogInActivity.class);
                }
                break;
            case R.id.layoutAbout:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;


            case R.id.image_my_setting:
                if (SPUtil.isLogin(getActivity())) {

                    startActivity(new Intent(getActivity(), SettingActivity.class));
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                } else {
                    CommonUtil.goAactivity(getActivity(), LogInActivity.class);
                }
                break;
            case R.id.layoutMyCenter:

                tomycenter();


                break;
            case R.id.imageMyHead:
                if (!SPUtil.isLogin(getActivity())) {
                    startActivity(new Intent(getActivity(), LogInActivity.class));
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                } else {
                    tomycenter();
                }

                break;
            case R.id.ll_my_login:
                startActivity(new Intent(getActivity(), LogInActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);


                break;
            case R.id.rv_my_qiandao:
                if (!isqiandao) {
                    singin();
                } else {

                    ToastUtils.showToast("您已签到过，明天再来");
                }


                break;
        }

        }else {
            ToastUtils.showToast("暂无网络");
        }

    }

    private void singin() {
        if (SPUtil.isLogin(getActivity())) {
            Map<String, String> params = new HashMap<>();
            params.put("act", "signup");
            params.put("userid", SPUtil.getUser(getActivity()).getUserid());

            RetrofitUtil.getretrofit().create(QianDaoBiz.class).getData(params).enqueue(new Callback<QianDao>() {
                @Override
                public void onResponse(Call<QianDao> call, Response<QianDao> response) {
                    if (response.body().getState().equals("1")) {
                        getUserInfo();
                        isqiandao = true;
                        tvqiandao.setText("已签到" + response.body().getSigntimes() + "天");
                        string = response.body().getGetintegral();
                        SingIn();
                        //ToastUtils.showToast("签到成功积分+"+response.body().getGetintegral());
                        Log.d("签到积分", response.body().getGetintegral());
                    } else {
                        ToastUtils.showToast("签到失败");
                        isqiandao = false;
                        tvqiandao.setText("签到");
                    }
                }

                @Override
                public void onFailure(Call<QianDao> call, Throwable t) {

                }
            });
        } else {
            CommonUtil.goAactivity(getActivity(), LogInActivity.class);
        }


    }

    private void getUserInfo() {
        if (SPUtil.isLogin(getActivity())) {


            Map<String, String> map = new HashMap<>();
            map.put("userid", SPUtil.getUser(getActivity()).getUserid());
            RetrofitUtil.getretrofit().create(UserInfoBiz.class).getUser(map).enqueue(new Callback<User.UserinfoBean>() {
                @Override
                public void onResponse(Call<User.UserinfoBean> call, Response<User.UserinfoBean> response) {
                    Log.d("请求用户的数据", response.body().toString());
                    if (SPUtil.setUser(getActivity(), response.body())) {

                        setData();
                    }
                }

                @Override
                public void onFailure(Call<User.UserinfoBean> call, Throwable t) {

                }
            });
        }
    }


    private void tomycenter() {

        performRequestPermissions(getString(R.string.permission_desc), new String[]{
                        Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CONTACTS}
                , PER_REQUEST_CODE, new PermissionsResultListener() {
                    @Override
                    public void onPermissionGranted() {
                        startActivity(new Intent(getActivity(), MyCenterActivity.class));
                        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    }

                    @Override
                    public void onPermissionDenied() {
                        CommonUtil.showToast(getActivity(), "已拒绝权限");
                    }
                });

    }

    /**
     * 弹出客服
     */
    private void showKF() {

        if (popupWindowkf != null && popupWindowkf.isShowing()) return;
        View upView = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_kf, null);


        WindowManager wm = getActivity().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowkf = new CommonPopupWindow.Builder(getActivity())
                .setView(R.layout.popwindow_kf)
                .setWidthAndHeight(width - 300, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        popupWindowkf.showAtLocation(layoutShare, Gravity.CENTER, 0, 0);


    }

    @Override
    public void onTurn() {

    }

    /**
     * 弹出分享壹元服务
     */
    public void showAll() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_share, null);

        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(getActivity())
                .setView(R.layout.popwindow_share)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAtLocation(layoutShare, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 弹出签到签到成功
     */
    public void SingIn() {
        if (popupWindowqd != null && popupWindowqd.isShowing()) return;
        View upView = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_qiandao, null);
        //测量View的宽高
        WindowManager wm = getActivity().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowqd = new CommonPopupWindow.Builder(getActivity())
                .setView(R.layout.popwindow_qiandao)
                .setWidthAndHeight(width - 300, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        popupWindowqd.showAtLocation(layoutShare, Gravity.CENTER, 0, 0);
        tvjifen.setText(string + "积分");

    }

    private void showpopwindowtishi() {

        if (popupWindowtishi != null && popupWindowtishi.isShowing()) return;
        View upView = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_tishi, null);
        //测量View的宽高
        WindowManager wm = getActivity().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowtishi = new CommonPopupWindow.Builder(getActivity())
                .setView(R.layout.popwindow_tishi)
                .setWidthAndHeight(width - 400, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        popupWindowtishi.setOutsideTouchable(false);
        popupWindowtishi.setFocusable(false);
        popupWindowtishi.showAtLocation(layoutShare, Gravity.CENTER, 0, 0);

    }

    /**
     * 判断用户是否登录   未登录删除数据
     */
    private void isCheckLogin() {
        Map<String, String> params = new HashMap<>();
        params.put("devicetoken", SPUtil.getData(getActivity(), "deviceToken", null));
        params.put("userid", SPUtil.getUser(getActivity()).getUserid());
        RetrofitUtil.getretrofit().create(IsCheckLogInBiz.class).getData(params).enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                if (response.body().getState().equals("1")) {
                    delFile(path1);
                    SPUtil.Exit(getActivity());
                    //ToastUtils.showToast("您已在其他地方登录，已退出");
                    showpopwindowtishi();
                } else {

                }
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });


    }

    @Override
    public void getChildView(View view, int layoutResId) {

        switch (layoutResId) {
            case R.layout.popwindow_kf:
                qq = (TextView) view.findViewById(R.id.tv_kf_qq);
                call = (TextView) view.findViewById(R.id.tv_kf_call);
                cancle = (TextView) view.findViewById(R.id.tv_kf_cancle);
                qq.setOnClickListener(this);
                call.setOnClickListener(this);
                cancle.setOnClickListener(this);
                break;
            case R.layout.popwindow_share:
                sharecancle = (LinearLayout) view.findViewById(R.id.ll_my_share_cancle);

                ivfriend = (ImageView) view.findViewById(R.id.iv_my_share_friend);
                ivqq = (ImageView) view.findViewById(R.id.iv_my_share_qq);
                ivqzone = (ImageView) view.findViewById(R.id.iv_my_share_qzone);
                ivweixin = (ImageView) view.findViewById(R.id.iv_my_share_weixin);
                ivsina = (ImageView) view.findViewById(R.id.iv_my_share_sina);
                LinearLayout  text=(LinearLayout)view.findViewById(R.id.ll_share_text);
                LinearLayout  link=(LinearLayout)view.findViewById(R.id.ll_share_link);
                text.setVisibility(View.INVISIBLE);
                link.setVisibility(View.INVISIBLE);
                ivfriend.setOnClickListener(this);
                ivqq.setOnClickListener(this);
                ivqzone.setOnClickListener(this);
                ivweixin.setOnClickListener(this);
                ivsina.setOnClickListener(this);
                sharecancle.setOnClickListener(this);
                break;
            case R.layout.popwindow_qiandao:
                tvGoJFSC = (TextView) view.findViewById(R.id.tv_qiandao_sc);
                tvGoJFSC.setOnClickListener(this);
                tvjifen = (TextView) view.findViewById(R.id.tv_qiandao_jifen);
                tvjifen.setText(string + "积分");
                ivclose = (ImageView) view.findViewById(R.id.iv_singin_close);
                ivclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindowqd.dismiss();
                    }
                });
                break;
            case R.layout.popwindow_tishi:
                TextView textView = (TextView) view.findViewById(R.id.tv_tishi_qx);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindowtishi.dismiss();
                    }
                });

                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_kf_qq:
                CommonUtil.goAactivity(getActivity(), QQKFActivity.class);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.tv_kf_call:


                performRequestPermissions(getString(R.string.permission_desc), new String[]{Manifest.permission.READ_PHONE_STATE}
                        , PER_REQUEST_CODE, new PermissionsResultListener() {
                            @Override
                            public void onPermissionGranted() {
                                CommonUtil.showToast(getActivity(), "已申请权限");
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:01087777077"));
                                startActivity(intent);
                            }

                            @Override
                            public void onPermissionDenied() {
                                CommonUtil.showToast(getActivity(), "已拒绝权限");
                            }
                        });

                break;
            case R.id.tv_kf_cancle:
                popupWindowkf.dismiss();
                break;
            case R.id.iv_my_share_friend:
                ShareWeb(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.iv_my_share_qq:
                ShareWeb(SHARE_MEDIA.QQ);
                popupWindow.dismiss();
                break;
            case R.id.iv_my_share_weixin:
                ShareWeb(SHARE_MEDIA.WEIXIN);
                popupWindow.dismiss();
                break;
            case R.id.iv_my_share_qzone:
                ShareWeb(SHARE_MEDIA.QZONE);
                popupWindow.dismiss();
                break;
            case R.id.iv_my_share_sina:
                ShareWeb(SHARE_MEDIA.SINA);
                popupWindow.dismiss();
                break;
            case R.id.ll_my_share_cancle:
                popupWindow.dismiss();
                break;
            case R.id.tv_qiandao_sc:

                CommonUtil.goAactivity(getActivity(), JFSCActivity.class);
                popupWindowqd.dismiss();
                break;

        }
    }


    private void ShareWeb(SHARE_MEDIA type) {
        UMImage thumb = new UMImage(getActivity(), R.mipmap.logo);
        UMWeb web = new UMWeb("http://www.1yuaninfo.com/index.php");
        web.setThumb(thumb);
        web.setDescription("快来下载");
        web.setTitle("壹元服务");
        new ShareAction(getActivity()).withMedia(web).setPlatform(type).setCallback(shareListener).share();
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

    //删除文件
    public void delFile(String path) {
        File file = new File(path);
        Log.d("删除文件", file.isFile() + "11");
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

    @Override
    public void submit(String userStr) {

        if(userStr=="1"){

            relativeLayout.setVisibility(View.GONE);
            llDingyue.setVisibility(View.GONE);
            viewview.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
            head.getBigCircleImageView().setImageResource(R.drawable.head_default);

           // ToastUtils.showToast("刷新数据");
           // setData();
            //getUserInfo();
           // isQianDao();
        }

    }



    public class BroadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle data= intent.getExtras();
            if(data.getString("text")=="1"){

            }
        }
        }
}
