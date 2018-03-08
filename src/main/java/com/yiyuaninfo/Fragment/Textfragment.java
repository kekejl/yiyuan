package com.yiyuaninfo.Fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrwang.retrofitcacheinterceptor.RetrofitCacheInterceptor;
import com.paradoxie.autoscrolltextview.VerticalTextview;
import com.scrollablelayout.ScrollableHelper;
import com.scrollablelayout.ScrollableLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyuaninfo.Activity.CPActivity;
import com.yiyuaninfo.Activity.FlashActivity;
import com.yiyuaninfo.Activity.JFSCActivity;
import com.yiyuaninfo.Activity.JJActivity;
import com.yiyuaninfo.Activity.MediaMoreActivity;
import com.yiyuaninfo.Activity.MsgActivity;
import com.yiyuaninfo.Activity.MsgInfoActivity;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Activity.PushListActivity;
import com.yiyuaninfo.Activity.QSActivity;
import com.yiyuaninfo.Activity.SearchActivity;
import com.yiyuaninfo.Activity.TGActivity;
import com.yiyuaninfo.Activity.XMActivity;
import com.yiyuaninfo.App;
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
import com.yiyuaninfo.util.DateUtil;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.FragmentFactory;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.MyScrollableHelper;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.view.TabPageIndicator;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/2/24.
 */

public class Textfragment extends BaseFragment implements ViewPager.OnPageChangeListener,  CommonPopupWindow.ViewInterface ,PtrHandler {
    private static final int PER_REQUEST_CODE = 2;

    @BindView(R.id.service_banner)
    Banner topbanner;
    @BindView(R.id.textbanner)
    VerticalTextview textbanner;
    @BindView(R.id.bannersmall)
    Banner bannersmall;
    @BindView(R.id.textNoticeMore)
    TextView textviewmore;
    //    @BindView(R.id.tabPageIndicatorHomeFragment)
//    TabPageIndicator tabPageIndicatorHome;
    @BindView(R.id.tab_home)
    TabPageIndicator colorTrackTabLayout;
    @BindView(R.id.viewpager_home)
    public ViewPager viewPager;
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





    @BindView(R.id.ll_home)
    LinearLayout llhome;
    @BindView(R.id.ll_home_search)
    LinearLayout llsearch;
    @BindView(R.id.iv_home_hqzs)
    ImageView imageView;
    @BindView(R.id.iv_home_msg)
    ImageView ivMsg;

    @BindView(R.id.iv_tvbanner_more)
    ImageView tvBannerMore;
    @BindView(R.id.sl_root)
    ScrollableLayout  scrollableLayout;
    @BindView(R.id.pfl_root)

     PtrClassicFrameLayout pfl_root;

    @BindView(R.id.nac_layout)
    LinearLayout  bar;

//     @BindView(R.id.srl_home)
//     SmartRefreshLayout smartRefreshLayout;


    // @BindView( R.id.appbar_home)
   // AppBarLayout  appBarLayout;
    private ClassicsHeader mClassicsHeader;
    private Drawable mDrawableProgress;

    public   static LoadMoreListener  loadMoreListener;

    public static void setLoadMoreListener(LoadMoreListener listener1) {
        loadMoreListener= listener1;
    }
    int k;
    int kk;
    private CommonPopupWindow popupWindow;
    private View view;

    private String   time;

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
    List<TextBaseFragment> fragmentList;
    List<String> titleList;
    private static final String[] CHANNELS = new String[]{"热搜榜", "牛人榜"};
    private List<String> ChannelsList = Arrays.asList(CHANNELS);
    private FindFragmentAdapter findFragmentAdapter;
    private Geniusfragment geniusfragment;
    private Hotfragment hotfragment;
    private GetMoreData  getMoreData;

    private  boolean  networkboolean;
    public Textfragment newInstance(String title, GetMoreData getMoreData) {
        Textfragment.this.getMoreData=getMoreData;
        Textfragment f = new Textfragment();

        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    protected void initListener() {
        bannersmall.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                CommonUtil.goAactivity(getActivity(), MsgInfoActivity.class, "msgurl",
                        srollPicBeanList.get(position-1).getPiclink());
            }
        });

        topbanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                // ToastUtils.showToast("122122");
                Log.d("轮播position",position+"");
                CommonUtil.goAactivity(getActivity(), MsgInfoActivity.class, "msgurl",
                        rollPicBeanList.get(position-1).getPiclink() );
            }
        });

    }

    @Override
    protected void initData() {
        bar.setAlpha(0);
        pfl_root.setEnabledNextPtrAtOnce(true);
        pfl_root.setLastUpdateTimeRelateObject(this);
        pfl_root.setPtrHandler(this);
        pfl_root.setKeepHeaderWhenRefresh(true);

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
       // RetrofitUtil.getretrofit().create(HomeBiz.class).e
        //获取数据


        RetrofitCacheInterceptor retrofitCacheInterceptor =
                new RetrofitCacheInterceptor(getActivity());



        File cacheDir = Environment.getExternalStorageDirectory();
        Cache cache = new Cache(cacheDir, 20 * 1024 * 1024);


        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(retrofitCacheInterceptor)
                .cache(cache)
                .build();





        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.Home)
                .client(okHttpClient)
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

                time= DateUtils.getTimePoint(response.body().getPost_msg().getAddtime());
                Log.d("首页时间",time);



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
                        textViewwuping.setText("即时通知");

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

//        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.getLayout().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        smartRefreshLayout.finishRefresh();
//                        smartRefreshLayout.setLoadmoreFinished(false);
//                    }
//                }, 2000);
//            }
//        });


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


    private void SmallBanner(final List<HomeEntity.SrollPicBean> srollPicBeanList) {
        List<String> smallbannerlist = new ArrayList<>();
        for (int i = 0; i < srollPicBeanList.size(); i++) {
            String url = srollPicBeanList.get(i).getPicurl();
            smallbannerlist.add(url);
        }
        bannersmall.setOffscreenPageLimit(smallbannerlist.size());
        Log.d("中间图片集合", smallbannerlist.toString());
        bannersmall.setImages(smallbannerlist).setImageLoader(new GlideImageloader()).start();





    }

    private void TopBanner(final List<HomeEntity.RollPicBean> rollPicBeanList) {
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
//        try {//由于该框架ScrollableLayout存在不完善的地方，所以重写了ScrollableHelper通反射注入到了框架中
//            Field field = scrollableLayout.getClass().getDeclaredField("mHelper");
//            field.setAccessible(true);
//            MyScrollableHelper myScrollableHelper = new MyScrollableHelper();
//            myScrollableHelper.setCurrentScrollableContainer(this);
//            field.set(scrollableLayout,myScrollableHelper);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }






        // topbanner();
        WindowManager wm = getActivity().getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) bannersmall.getLayoutParams();
        params.height = (width * 1) / 5;
        params.width = width;
        bannersmall.setLayoutParams(params);
        bannersmall.setBannerStyle(BannerConfig.NOT_INDICATOR);


        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) topbanner.getLayoutParams();
        params1.height = (width * 1) / 2;
        params1.width = width;
        topbanner.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        params2.height = (width * 1) / 4;
        params2.width = width;
        imageView.setLayoutParams(params2);


        topbanner.post(new Runnable() {
            @Override
            public void run() {
                k=topbanner.getMeasuredHeight();
                kk=bar.getMeasuredHeight();
                Log.d("bannder的高度",k+"");
            }
        });






        fragmentList = new ArrayList<>();
        hotfragment = new Hotfragment();
        hotfragment.setViewPage();
        fragmentList.add(hotfragment);
        geniusfragment = new Geniusfragment();
        geniusfragment.setViewPage();
        fragmentList.add(geniusfragment);
        findFragmentAdapter = new FindFragmentAdapter(getChildFragmentManager(), fragmentList, ChannelsList);

        viewPager.setAdapter(findFragmentAdapter);
        //限定预加载的页面的个数

        viewPager.setOffscreenPageLimit(ChannelsList.size());
       // colorTrackTabLayout.setupWithViewPager(viewPager);
        colorTrackTabLayout.setViewPager(viewPager);
         setTabPagerIndicator();

       scrollableLayout.getHelper().setCurrentScrollableContainer(fragmentList.get(0));
       scrollableLayout.getHelper().headerview(bar);
        scrollableLayout.setOnScrollListener(new ScrollableLayout.OnScrollListener() {
            @Override
            public void onScroll(int currentY, int maxY) {

                currentY=currentY>k-kk?k-kk:currentY;
                float  alpha=(float) currentY/(k-kk);

                Log.d("变化的值",alpha+"+++"+currentY+"+++"+maxY+"+++"+k);

                bar.setAlpha(alpha);
            }
        });

       colorTrackTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
               Log.d("viewpager选中的位置",position+"");

               scrollableLayout.getHelper().setCurrentScrollableContainer(fragmentList.get(position));

           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });


//
//        LinearLayout linearLayout = (LinearLayout) colorTrackTabLayout.getChildAt(0);
//        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
//                R.drawable.tablayout_divider));
//        linearLayout.setDividerPadding(CommonUtil.dip2px(10));

//        colorTrackTabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                //设置最小宽度，使其可以在滑动一部分距离
//                ViewGroup slidingTabStrip = (ViewGroup) colorTrackTabLayout.getChildAt(0);
//                slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth());
//            }
//        });

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


       // viewPagerHome.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        //tabPageIndicatorHome.setViewPager(viewPagerHome);
        // setTabPagerIndicator();
       // viewPagerHome.resetHeight(0);
//跳转签到界面




    }


    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_home_text;
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.textNoticeMore,
            R.id.ll_cp,
            R.id.ll_dy,
            R.id.ll_hq,
            R.id.ll_jj,
            R.id.ll_qs,
            R.id.ll_sc,
            R.id.ll_tg,
            R.id.ll_xm,
            R.id.iv_home_msg,
            R.id.ll_home_search,
            R.id.tv_home_content})
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
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.ll_hq:
                CommonUtil.showToast(getActivity(), "暂无行情数据！");
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

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

            case R.id.iv_home_msg:
                CommonUtil.goAactivity(getActivity(), MsgActivity.class);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.ll_home_search:

                CommonUtil.goAactivity(getActivity(), SearchActivity.class);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.tv_home_content:

                CommonUtil.goAactivity(getActivity(), PushListActivity.class,"time",time);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;

        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }


        /**
     * tab指示器
     */
    private void setTabPagerIndicator() {
        colorTrackTabLayout.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_SAME);// 设置模式，一定要先设置模式
        colorTrackTabLayout.setBackgroundColor(Color.parseColor("#ffffff"));

        colorTrackTabLayout.setDividerPadding(CommonUtils.dip2px(App.getContext(), 10));
        colorTrackTabLayout.setIndicatorHeight(10);
        colorTrackTabLayout.setIndicatorColor(Color.parseColor("#d43c33"));// 设置底部导航线的颜色
        colorTrackTabLayout.setTextColorSelected(Color.parseColor("#d43c33"));// 设置tab标题选中的颜色
        colorTrackTabLayout.setTextColor(Color.parseColor("#000000"));// 设置tab标题未被选中的颜色
        colorTrackTabLayout.setTextSize(CommonUtils.sp2px(App.getContext(), 16));// 设置字体大小

    }

    @Override
    public void getChildView(View view, int layoutResId) {


    }




    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return false;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }





    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
            return FragmentFactory.createhome(position);
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





}
