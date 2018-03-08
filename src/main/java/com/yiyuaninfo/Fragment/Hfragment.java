package com.yiyuaninfo.Fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import com.paradoxie.autoscrolltextview.VerticalTextview;
import com.yiyuaninfo.Activity.CPActivity;
import com.yiyuaninfo.Activity.FlashActivity;
import com.yiyuaninfo.Activity.JFSCActivity;
import com.yiyuaninfo.Activity.JJActivity;
import com.yiyuaninfo.Activity.MediaMoreActivity;
import com.yiyuaninfo.Activity.MsgInfoActivity;
import com.yiyuaninfo.Activity.PushListActivity;
import com.yiyuaninfo.Activity.QSActivity;
import com.yiyuaninfo.Activity.TGActivity;
import com.yiyuaninfo.Activity.XMActivity;
import com.yiyuaninfo.App;
import com.yiyuaninfo.GlideImageloader;
import com.yiyuaninfo.Interface.HomeBiz;
import com.yiyuaninfo.Listener.PermissionsResultListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.HomeEntity;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.CommonUtils;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.FragmentFactory;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.view.TabPageIndicator;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class Hfragment extends BaseFragment implements CommonPopupWindow.ViewInterface {
    private static final int PER_REQUEST_CODE = 2;

    @BindView(R.id.service_banner)
    Banner topbanner;
    @BindView(R.id.textbanner)
    VerticalTextview textbanner;
    @BindView(R.id.bannersmall)
    Banner bannersmall;
    @BindView(R.id.textNoticeMore)
    TextView textviewmore;
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

    @BindView(R.id.tabPageIndicator_home)
    TabPageIndicator  tabPageIndicator;
    @BindView(R.id.viewpager_home)
    ViewPager  viewPager;
    @BindView(R.id.iv_home_hqzs)
            ImageView   imageView;
    //顶部轮播图集合
    public static List<HomeEntity.RollPicBean> rollPicBeanList = new ArrayList<>();
    //中间小轮播图集合
    public static List<HomeEntity.SrollPicBean> srollPicBeanList = new ArrayList<>();
    public static List<HomeEntity.RollWordsBean> rollWordsBeanList = new ArrayList<>();
    private String[] imageUrls = {"http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg",
            "http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg",
            "http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg",
            "http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg",
            "http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg"
    };
    private Integer[] ints = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};

    public static List<?> images = new ArrayList<>();


    List<Fragment> fragmentList;
    private static final String[] CHANNELS = new String[]{"热搜榜", "牛人榜"};
    private List<String> ChannelsList = Arrays.asList(CHANNELS);
    private Geniusfragment  geniusfragment;
    private Hotfragment   hotfragment;
    public static Hfragment newInstance(String title) {
        Hfragment f = new Hfragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    protected void initListener() {
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



    }

    @Override
    protected void initData() {


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

                ImageLoaderUtils.displayImage(response.body().getZhishu().getPicurl(),imageView);

                TopBanner(rollPicBeanList);

                SmallBanner(srollPicBeanList);
                ScrollText(rollWordsBeanList);
                textViewtime.setText(DateUtils.getTimePoint1(response.body().getPost_msg().getAddtime()));
                //textViewtime.setText("08:30:44");
                textViewtitle.setText(response.body().getPost_msg().getTitle());
                //textViewtitle.setText("早上好！");
                textViewcontent.setText("\u3000\u3000"+response.body().getPost_msg().getRemark());
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

    private void ScrollText(List<HomeEntity.RollWordsBean> rollPicBeanList) {
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
                CommonUtil.goAactivity(getActivity(), FlashActivity.class);
                // 点击进入 快讯轮播 列表，定位到最新
            }
        });
        textbanner.startAutoScroll();
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
        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) bannersmall.getLayoutParams();
        params.height=(width*1)/5;
        params.width=width;
        bannersmall.setLayoutParams(params);

        LinearLayout.LayoutParams params1= (LinearLayout.LayoutParams) topbanner.getLayoutParams();
        params1.height=(width*3)/5;
        params1.width=width;
        topbanner.setLayoutParams(params1);

        LinearLayout.LayoutParams params2= (LinearLayout.LayoutParams) imageView.getLayoutParams();
        params2.height=(width*1)/4;
        params2.width=width;
        imageView.setLayoutParams(params2);


        BasePagerAdapter adapter = new BasePagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tabPageIndicator.setViewPager(viewPager);
        setTabPagerIndicator();

    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_h;
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.textNoticeMore, R.id.ll_cp, R.id.ll_dy, R.id.ll_hq, R.id.ll_jj, R.id.ll_qs, R.id.ll_sc, R.id.ll_tg, R.id.ll_xm})
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
                CommonUtil.goAactivity(getActivity(), JFSCActivity.class);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.ll_tg:
                CommonUtil.goAactivity(getActivity(), TGActivity.class, "channelid", "0");
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.ll_xm:
                CommonUtil.goAactivity(getActivity(), XMActivity.class, "channelid", "0","id",5);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
        }

    }

    private void setTabPagerIndicator() {
        tabPageIndicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_SAME);// 设置模式，一定要先设置模式
        tabPageIndicator.setDividerColor(Color.parseColor("#ffffff"));// 设置分割线的颜色
        tabPageIndicator.setDividerPadding(CommonUtils.dip2px(App.getContext(), 10));
        tabPageIndicator.setIndicatorColor(Color.parseColor("#b81c22"));// 设置底部导航线的颜色
        tabPageIndicator.setTextColorSelected(Color.parseColor("#b81c22"));// 设置tab标题选中的颜色
        tabPageIndicator.setTextColor(Color.parseColor("#000000"));// 设置tab标题未被选中的颜色
        tabPageIndicator.setTextSize(CommonUtils.sp2px(App.getContext(), 16));// 设置字体大小
    }

    class BasePagerAdapter extends FragmentStatePagerAdapter {
        String[] titles;

        public BasePagerAdapter(FragmentManager fm) {
            super(fm);
            this.titles =CHANNELS;
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createhome(position);
        }

        @Override
        public int getCount() {
            return CHANNELS.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CHANNELS[position];
        }
    }

    @Override
    public void getChildView(View view, int layoutResId) {


    }


}
