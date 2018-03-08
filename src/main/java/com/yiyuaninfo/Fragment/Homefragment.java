package com.yiyuaninfo.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paradoxie.autoscrolltextview.VerticalTextview;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.message.PushAgent;
import com.yiyuaninfo.Activity.CPActivity;
import com.yiyuaninfo.Activity.FlashActivity;
import com.yiyuaninfo.Activity.JFSCActivity;
import com.yiyuaninfo.Activity.JJActivity;
import com.yiyuaninfo.Activity.MediaMoreActivity;
import com.yiyuaninfo.Activity.MoreInfoActivity;
import com.yiyuaninfo.Activity.MsgActivity;
import com.yiyuaninfo.Activity.MsgInfoActivity;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Activity.MyActivity.MyCenter.MyCenterActivity;
import com.yiyuaninfo.Activity.NewsDetailActivity;
import com.yiyuaninfo.Activity.PushListActivity;
import com.yiyuaninfo.Activity.QSActivity;
import com.yiyuaninfo.Activity.SearchActivity;
import com.yiyuaninfo.Activity.TGActivity;
import com.yiyuaninfo.Activity.XMActivity;
import com.yiyuaninfo.GlideImageloader;
import com.yiyuaninfo.Interface.GetMoreData;
import com.yiyuaninfo.Interface.HomeBiz;
import com.yiyuaninfo.Listener.LoadMoreListener;
import com.yiyuaninfo.Listener.PermissionsResultListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.FindFragmentAdapter;
import com.yiyuaninfo.entity.HomeEntity;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.CommonUtils;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.DynamicTimeFormat;
import com.yiyuaninfo.util.FragmentFactory;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.view.HomeScrollview;
import com.yiyuaninfo.view.WrapContentHeightViewPager;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/2/24.
 */

public class Homefragment extends BaseFragment implements CommonPopupWindow.ViewInterface  {
    private static final int PER_REQUEST_CODE = 2;

    @BindView(R.id.service_banner)
    Banner topbanner;
    @BindView(R.id.textbanner)
    VerticalTextview textbanner;
    @BindView(R.id.bannersmall)
    Banner bannersmall;
    @BindView(R.id.nac_layout)
    LinearLayout layout;
    @BindView(R.id.textNoticeMore)
    TextView textviewmore;
    //    @BindView(R.id.tabPageIndicatorHomeFragment)
//    TabPageIndicator tabPageIndicatorHome;
    @BindView(R.id.tl_home)
    TabLayout colorTrackTabLayout;
    @BindView(R.id.ll_home_float)
    LinearLayout linearLayoutfloat;
    @BindView(R.id.viewPagerHomeFragment)
    public WrapContentHeightViewPager viewPagerHome;
    @BindView(R.id.image_signin)
    ImageView imageViewSignin;
    // @BindView(R.id.linear_headview)
    //LinearLayout  linearLayoutHeadView;
    @BindView(R.id.tv_home_wuping)
    TextView textViewwuping;
    @BindView(R.id.tv_home_time)
    TextView textViewtime;
    @BindView(R.id.tv_home_title)
    TextView textViewtitle;
    @BindView(R.id.tv_home_content)
    TextView textViewcontent;
    @BindView(R.id.ll_tg)
    LinearLayout lltg;
    @BindView(R.id.ll_qs)
    LinearLayout llqs;
    @BindView(R.id.ll_jj)
    LinearLayout lljj;
    @BindView(R.id.ll_xm)
    LinearLayout llxm;
    @BindView(R.id.ll_cp)
    LinearLayout llcp;
    @BindView(R.id.ll_hq)
    LinearLayout llhq;
    @BindView(R.id.ll_dy)
    LinearLayout lldy;
    @BindView(R.id.ll_sc)
    LinearLayout llsc;

    @BindView(R.id.sv_home)
    HomeScrollview scrollview;
    @BindView(R.id.ll_home)
    LinearLayout llhome;
    @BindView(R.id.ll_home_search)
    LinearLayout search;
    @BindView(R.id.iv_home_hqzs)
    ImageView imageView;
    @BindView(R.id.iv_tvbanner_more)
    ImageView tvBannerMore;
    @BindView(R.id.srl_home)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.fl_home)
    FrameLayout frameLayout;
    @BindView(R.id.classheader)

    ClassicsHeader  classicsHeader;
    private ClassicsHeader mClassicsHeader;
    private Drawable mDrawableProgress;

    public   static LoadMoreListener  loadMoreListener;

    public static void setLoadMoreListener(LoadMoreListener listener1) {
        loadMoreListener= listener1;
    }
    int kkk;
    int kkkk;
    int kk;
    int k;
    private CommonPopupWindow popupWindow;
    private View view;

    //顶部轮播图集合
    public static List<HomeEntity.RollPicBean> rollPicBeanList = new ArrayList<>();
    //中间小轮播图集合
    public static List<HomeEntity.SrollPicBean> srollPicBeanList = new ArrayList<>();
    public static List<HomeEntity.RollWordsBean> rollWordsBeanList = new ArrayList<>();
    private int tabHeight;
    private int Height;
    private String[] imageUrls = {"http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg",
            "http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg",
            "http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg",
            "http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg",
            "http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg"
    };
    private Integer[] ints = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};

    public static List<?> images = new ArrayList<>();


    List<Fragment> fragmentList;
    List<String> titleList;
    private static final String[] CHANNELS = new String[]{"热搜榜", "牛人榜"};
    private List<String> ChannelsList = Arrays.asList(CHANNELS);
    private FindFragmentAdapter findFragmentAdapter;
    private Geniusfragment geniusfragment;
    private Hotfragment hotfragment;


    private GetMoreData  getMoreData;


    public Homefragment newInstance(String title, GetMoreData getMoreData) {
        Homefragment.this.getMoreData=getMoreData;
        Homefragment f = new Homefragment();

        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    protected void initListener() {

        scrollview.setOnScrollListener(new HomeScrollview.OnScrollListener() {
            @Override
            public void onScrollchanged(int t) {
                Log.d("haha ", t + "  " + (viewPagerHome.getTop() - kkkk) + "\n" + k+"+++"+viewPagerHome.getTop());
                int translation = Math.max(t + kk, viewPagerHome.getTop() - kkkk);
                linearLayoutfloat.setTranslationY(translation);

                linearLayoutfloat.setVisibility(View.VISIBLE);
//                if(translation>2177){
//                    scrollview.setNestedScrollingEnabled(false);
//                }


            }

            @Override
            public void onScroll(int scrollY) {

            }

            @Override
            public void onTouchUp() {

            }

            @Override
            public void onTouchDown() {

            }
        });






        topbanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                // ToastUtils.showToast("122122");
                CommonUtil.goAactivity(getActivity(), MsgInfoActivity.class, "msgurl",
                        Constants.YIYUAN);
            }
        });
        bannersmall.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                CommonUtil.goAactivity(getActivity(), MsgInfoActivity.class, "msgurl",
                        Constants.YIYUAN);

            }
        });

        viewPagerHome.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                viewPagerHome.resetHeight(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }





    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if(msg.what==2){

                if(msg.obj.toString().equals("1")){
                    smartRefreshLayout.finishLoadmore();

                }else {
                    smartRefreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件

                }
            }
        }
    };





    @Override
    protected void initData() {



        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                frameLayout.setVisibility(View.GONE);
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishRefresh();
                        frameLayout.setVisibility(View.VISIBLE);
                        smartRefreshLayout.setLoadmoreFinished(false);
                    }
                }, 2000);


            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if(loadMoreListener!=null){
                    loadMoreListener.loadmore(refreshlayout);
                }
            }
        });
        smartRefreshLayout.setRefreshHeader(classicsHeader);
        smartRefreshLayout.setHeaderHeight(60);
        int deta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
        mClassicsHeader=(ClassicsHeader)smartRefreshLayout.getRefreshHeader();
        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis()-deta));
        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA));
        mClassicsHeader.setTimeFormat(new DynamicTimeFormat("更新于 %s"));
        mDrawableProgress = mClassicsHeader.getProgressView().getDrawable();
        if (mDrawableProgress instanceof LayerDrawable) {
            mDrawableProgress = ((LayerDrawable) mDrawableProgress).getDrawable(0);
        }





        performRequestPermissions(getString(R.string.permission_desc), new String[]{
                        Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CONTACTS}
                , PER_REQUEST_CODE, new PermissionsResultListener() {
                    @Override
                    public void onPermissionGranted() {
                    }

                    @Override
                    public void onPermissionDenied() {
                        CommonUtil.showToast(getActivity(), "已拒绝权限");
                    }
                });

        //获取数据
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.Home)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HomeBiz homeBiz = retrofit.create(HomeBiz.class);
        Call<HomeEntity> msgCall = homeBiz.getHome("top");
        msgCall.enqueue(new Callback<HomeEntity>() {
            @Override
            public void onResponse(Call<HomeEntity> call, Response<HomeEntity> response) {
                Log.d("首页轮播图数据", response.body().getRoll_pic().toString());
                Log.d("首页中间轮播图数据", response.body().getSroll_pic().toString());
                Log.d("快讯数据", response.body().getRoll_words().toString());
                rollPicBeanList = response.body().getRoll_pic();
                srollPicBeanList = response.body().getSroll_pic();
                rollWordsBeanList = response.body().getRoll_words();

                ImageLoaderUtils.displayImage(response.body().getZhishu().getPicurl(), imageView);

                TopBanner(rollPicBeanList);

                SmallBanner(srollPicBeanList);
                ScrollText(rollWordsBeanList);
                textViewtime.setText(DateUtils.getTimePoint1(response.body().getPost_msg().getAddtime()));
                //textViewtime.setText("08:30:44");
                textViewtitle.setText(response.body().getPost_msg().getTitle());
                //textViewtitle.setText("早上好！");
                textViewcontent.setText("\u3000\u3000" + response.body().getPost_msg().getRemark());
                //textViewcontent.setText("        做人最怕的不是努力了没结果，而是还没尝试就选择放弃。想一千次，不如去做一次，你不逼自己一把，怎么会知道自己有多优秀。与其抱怨，不如改变，你若不成长，谁来替你坚强。");
                switch (response.body().getPost_msg().getKeyword1()) {
                    case "5":
                        textViewwuping.setText("早餐");
                        break;
                    case "6":
                        textViewwuping.setText("早评");

                        break;

                    case "7":
                        textViewwuping.setText("上午分享");

                        break;
                    case "8":
                        textViewwuping.setText("午评");

                        break;
                    case "9":
                        textViewwuping.setText("下午分享");

                        break;
                    case "10":
                        textViewwuping.setText("收评");

                        break;
                    case "11":
                        textViewwuping.setText("夜宵");

                        break;
                    case "12":
                        textViewwuping.setText("及时通知");

                        break;
                    case "13":
                        textViewwuping.setText("风险提示");

                        break;
                    case "14":
                        textViewwuping.setText("获利提示");

                        break;
                    default:
                        textViewwuping.setText(response.body().getPost_msg().getKeyword1());

                }


            }

            @Override
            public void onFailure(Call<HomeEntity> call, Throwable t) {

            }
        });

    }


    private void topbanner() {
        List list = new ArrayList();
        list = Arrays.asList(ints);

        //topbanner.setOffscreenPageLimit(images.size());
        // topbanner.setImages(images).setImageLoader(new GlideImageloader()).start();
        topbanner.setOffscreenPageLimit(ints.length);
        topbanner.setImages(list).setImageLoader(new GlideImageloader()).start();


    }

    private void ScrollText(final  List<HomeEntity.RollWordsBean> rollPicBeanList) {
        ArrayList<String> textString = new ArrayList<>();
        for (int i = 0; i < rollPicBeanList.size(); i++) {
            String text = rollPicBeanList.get(i).getTitle();
            textString.add(text);
        }
        Log.d("快讯轮播集合", textString.toString());
        textbanner.setTextList(textString);//加入显示内容,集合类型
        textbanner.setText(14, 5, Color.BLACK);//设置属性,具体跟踪源码
        textbanner.setTextStillTime(3000);//设置停留时长间隔
        textbanner.setAnimTime(300);//设置进入和退出的时间间隔
        //对单条文字的点击监听
        textbanner.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CommonUtil.goAactivity(getActivity(),MsgInfoActivity.class,"msgurl",Constants.FLASHDETAIL+
                        rollPicBeanList.get(position).getWid());


                // 点击进入 快讯轮播 列表，定位到最新
            }
        });
        textbanner.startAutoScroll();

        tvBannerMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.goAactivity(getActivity(), FlashActivity.class);

            }
        });
    }


    private void SmallBanner(List<HomeEntity.SrollPicBean> srollPicBeanList) {
        List<String> smallbannerlist = new ArrayList<>();
        for (int i = 0; i < srollPicBeanList.size(); i++) {
            String url = srollPicBeanList.get(i).getPicurl();
            smallbannerlist.add(url);
        }
        bannersmall.setOffscreenPageLimit(smallbannerlist.size());
        Log.d("中间图片集合", smallbannerlist.toString());
        bannersmall.setImages(smallbannerlist).setImageLoader(new GlideImageloader()).start();

    }

    private void TopBanner(List<HomeEntity.RollPicBean> rollPicBeanList) {
        List<String> topbannerlist = new ArrayList<>();
        for (int i = 0; i < rollPicBeanList.size(); i++) {
            String topurl = rollPicBeanList.get(i).getPicurl();
            topbannerlist.add(topurl);
        }
        //topbanner.setOffscreenPageLimit(images.size());
        // topbanner.setImages(images).setImageLoader(new GlideImageloader()).start();
        topbanner.setOffscreenPageLimit(topbannerlist.size());
        topbanner.setImages(topbannerlist).setImageLoader(new GlideImageloader()).start();

    }

    @Override
    protected void initView() {


        // topbanner();
        WindowManager wm = getActivity().getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) bannersmall.getLayoutParams();
        params.height = (width * 1) / 5;
        params.width = width;
        bannersmall.setLayoutParams(params);

        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) topbanner.getLayoutParams();
        params1.height = (width * 3) / 5;
        params1.width = width;
        topbanner.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        params2.height = (width * 1) / 4;
        params2.width = width;
        imageView.setLayoutParams(params2);


        llhome.post(new Runnable() {
            @Override
            public void run() {
                Log.d("屏幕的宽度", CommonUtils.px2dip(getActivity(), bannersmall.getWidth()) +
                        "\n" + CommonUtils.px2dip(getActivity(), bannersmall.getHeight()));

                k = llhome.getWidth();
                kkk = llhome.getHeight();
                kkkk = linearLayoutfloat.getHeight();
                kk = layout.getHeight();
                linearLayoutfloat.setTranslationY(viewPagerHome.getTop() - kkkk);

            }
        });
        Log.d("111111", viewPagerHome.getTop() + "@" + colorTrackTabLayout.getHeight());

        scrollview.smoothScrollTo(0, 0);


        //FadingScrollView添加监听
        layout.setAlpha(0);

        scrollview.setFadingView(layout);
        scrollview.setFadingHeightView(topbanner);

        fragmentList = new ArrayList<>();
        hotfragment = new Hotfragment();
       // hotfragment.setViewPage(viewPagerHome, 0);
        fragmentList.add(hotfragment);
        geniusfragment = new Geniusfragment();
       // geniusfragment.setViewPage(viewPagerHome, 1);
        fragmentList.add(geniusfragment);
        findFragmentAdapter = new FindFragmentAdapter(getChildFragmentManager(), fragmentList, ChannelsList);

        viewPagerHome.setAdapter(findFragmentAdapter);
        //限定预加载的页面的个数

        viewPagerHome.setOffscreenPageLimit(ChannelsList.size());
        colorTrackTabLayout.setupWithViewPager(viewPagerHome);
        LinearLayout linearLayout = (LinearLayout) colorTrackTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.drawable.tablayout_divider));
        linearLayout.setDividerPadding(CommonUtil.dip2px(5));

        colorTrackTabLayout.post(new Runnable() {
            @Override
            public void run() {
                //设置最小宽度，使其可以在滑动一部分距离
                ViewGroup slidingTabStrip = (ViewGroup) colorTrackTabLayout.getChildAt(0);
                slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth());
            }
        });

//        colorTrackTabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(colorTrackTabLayout, 30, 30);
//            }
//        });
        //隐藏指示器
        // colorTrackTabLayout.setCurrentItem(Integer.parseInt(intent.getExtras().getString("channelid")));


        // PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
        // viewPagerHome.setAdapter(adapter);


        viewPagerHome.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        //tabPageIndicatorHome.setViewPager(viewPagerHome);
        // setTabPagerIndicator();
        viewPagerHome.resetHeight(0);
//跳转签到界面
        imageViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MsgActivity.class));

            }
        });

        //setFragment();
        //滑动事件监听

//       scrollview.setOnScrollListener(new HomeScrollview.OnScrollListener() {
//            @Override
//            public void onScrollchanged(int t) {
//                //去较大的值
//                int translation = Math.max(t, viewPagerHome.getTop() - tabHeight);
//                if(translation==viewPagerHome.getTop() - tabHeight){
//                    tabPageIndicatorHome.setTranslationY(translation);
//                }else if(translation==t){
//                    tabPageIndicatorHome.setTranslationY(translation);
//                }
//
//            }
//
//            @Override
//            public void onTouchUp() {
//
//            }
//
//            @Override
//            public void onTouchDown() {
//
//            }
//        });
//      linearLayoutHeadView.post(new Runnable() {
//          @Override
//          public void run() {
//              tabHeight=tabPageIndicatorHome.getHeight();
//              Height=linearLayoutHeadView.getHeight();
//          }
//      });


    }


    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_home;
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.textNoticeMore, R.id.ll_cp, R.id.ll_dy, R.id.ll_hq, R.id.ll_jj, R.id.ll_qs, R.id.ll_sc, R.id.ll_tg, R.id.ll_xm, R.id.ll_home_search})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.textNoticeMore:
                startActivity(new Intent(getActivity(), PushListActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.ll_cp:
                CommonUtil.goAactivity(getActivity(), CPActivity.class, "channelid", "0");
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.ll_dy:
                CommonUtil.goAactivity(getActivity(), MediaMoreActivity.class);
                break;
            case R.id.ll_hq:
                CommonUtil.showToast(getActivity(), "暂无行情数据！");
                break;
            case R.id.ll_jj:
                CommonUtil.goAactivity(getActivity(), JJActivity.class, "channelid", "0");
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.ll_qs:
                CommonUtil.goAactivity(getActivity(), QSActivity.class, "channelid", "0");
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.ll_sc:
                if(SPUtil.isLogin(getActivity())){
                CommonUtil.goAactivity(getActivity(), JFSCActivity.class);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                }else {
                    CommonUtil.goAactivity(getActivity(),LogInActivity.class);

                }
                break;
            case R.id.ll_tg:
                CommonUtil.goAactivity(getActivity(), TGActivity.class, "channelid", "0");
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.ll_xm:
                CommonUtil.goAactivity(getActivity(), XMActivity.class, "channelid", "0", "id", 5);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.ll_home_search:

                CommonUtil.goAactivity(getActivity(), SearchActivity.class);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
       // hotfragment.setViewPage(viewPagerHome, 0);
        //geniusfragment.setViewPage(viewPagerHome, 1);

    }
    //    /**
//     * tab指示器
//     */
//    private void setTabPagerIndicator() {
//        tabPageIndicatorHome.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_NOSAME);// 设置模式，一定要先设置模式
//        tabPageIndicatorHome.setDividerColor(Color.parseColor("#b81c22"));// 设置分割线的颜色
//        tabPageIndicatorHome.setDividerPadding(CommonUtils.dip2px(App.getContext(), 10));
//        tabPageIndicatorHome.setIndicatorColor(Color.parseColor("#b81c22"));// 设置底部导航线的颜色
//        tabPageIndicatorHome.setTextColorSelected(Color.parseColor("#b81c22"));// 设置tab标题选中的颜色
//        tabPageIndicatorHome.setTextColor(Color.parseColor("#797979"));// 设置tab标题未被选中的颜色
//        tabPageIndicatorHome.setTextSize(CommonUtils.sp2px(App.getContext(), 16));// 设置字体大小
//    }

    @Override
    public void getChildView(View view, int layoutResId) {


    }



    /**
     * Fragment适配器
     */

    class PagerAdapter extends FragmentStatePagerAdapter {
        String[] titles;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
            this.titles = CommonUtils.getStringArray(R.array.home_titles);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createhome(position, viewPagerHome);
            //return FragmentFactory.createForNoExpand(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }

    private void setFragment() {
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            titleList.add("title" + i);

            FragmentOrder fragmentOrder = new FragmentOrder();
            fragmentOrder.setViewPage(viewPagerHome, i);
            fragmentList.add(fragmentOrder);
        }
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager());
        viewPagerHome.setAdapter(myAdapter);
        viewPagerHome.resetHeight(0);
        //setTabPagerIndicator();
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }



}
