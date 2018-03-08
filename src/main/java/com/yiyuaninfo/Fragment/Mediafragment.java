package com.yiyuaninfo.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyuaninfo.Activity.MediaActivity;
import com.yiyuaninfo.Activity.MediaArtDetailActivity;
import com.yiyuaninfo.Activity.MediaDetaiActivity;
import com.yiyuaninfo.Activity.MediaMoreActivity;
import com.yiyuaninfo.Activity.MsgInfoActivity;
import com.yiyuaninfo.Activity.NewsDetailActivity;
import com.yiyuaninfo.Activity.VideoDetailActivity;
import com.yiyuaninfo.Activity.WebViewActivity;
import com.yiyuaninfo.Activity.colortrackview.ColorTrackTabLayout;
import com.yiyuaninfo.GlideImageloader;
import com.yiyuaninfo.Interface.GeniusBiz;
import com.yiyuaninfo.Interface.NiuLbBiz;
import com.yiyuaninfo.Listener.OnChannelListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.ChannelPagerAdapter;
import com.yiyuaninfo.adapter.GeniusAdapter;
import com.yiyuaninfo.adapter.GeniusHeaderAdapter;
import com.yiyuaninfo.adapter.MediaHeaderAdapter;
import com.yiyuaninfo.entity.Channel;
import com.yiyuaninfo.entity.Channeltab;
import com.yiyuaninfo.entity.Genius;
import com.yiyuaninfo.entity.NiuLb;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.ConstanceValue;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.DynamicTimeFormat;
import com.yiyuaninfo.util.EmptyUtil;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SharedPreferencesMgr;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.MyDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yiyuaninfo.util.ConstanceValue.TITLE_SELECTED;
import static com.yiyuaninfo.util.ConstanceValue.TITLE_UNSELECTED;

/**
 * Created by Administrator on 2017/2/23.
 */

public class Mediafragment extends BaseFragment {

    private List<Genius.NiuartArrBean> listarrbean = new ArrayList<>();
    private List<Genius.NiuArrBean> listbean = new ArrayList<>();
    private GeniusAdapter adapter;
    @BindView(R.id.rv_media)
    RecyclerView recyclerView;
   // @BindView(R.id.srl_media)
   // SmartRefreshLayout smartRefreshLayout;
    private ClassicsHeader mClassicsHeader;
    private Drawable mDrawableProgress;

    private Banner banner;
    private RecyclerView rvheader;
    private View view,viewheader;
    private Integer[] ints = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
    private MediaHeaderAdapter headerAdapter;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private TextView textView;
    //int kkk;
    //int kk;
    List list = new ArrayList();
    List listlb = new ArrayList();
    private String lastid;

    private EmptyUtil emptyUtil;

    public static Mediafragment newInstance(String title) {


        Mediafragment f = new Mediafragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    protected void initListener() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.goAactivity(getActivity(), MediaMoreActivity.class);
            }
        });

    }

    @Override
    protected void initData() {
        getData();
        list = Arrays.asList(ints);

        //topbanner.setOffscreenPageLimit(images.size());
        // topbanner.setImages(images).setImageLoader(new GlideImageloader()).start();
        banner.setOffscreenPageLimit(ints.length);


        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                // ToastUtils.showToast("122122");
                CommonUtil.goAactivity(getActivity(), MsgInfoActivity.class, "msgurl",
                        Constants.YIYUAN);
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
//        int deta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
//        mClassicsHeader=(ClassicsHeader)smartRefreshLayout.getRefreshHeader();
//        mClassicsHeader.setTextSizeTitle(12);
//        mClassicsHeader.setTextSizeTime(10);
//        mClassicsHeader.setDrawableProgressSize(15);
//        mClassicsHeader.setDrawableArrowSize(15);
//        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis()-deta));
//        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA));
//        mClassicsHeader.setTimeFormat(new DynamicTimeFormat("更新于 %s"));
//        mDrawableProgress = mClassicsHeader.getProgressView().getDrawable();
//        if (mDrawableProgress instanceof LayerDrawable) {
//            mDrawableProgress = ((LayerDrawable) mDrawableProgress).getDrawable(0);
//        }
    }

    private void getLBData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "rollpic");
        RetrofitUtil.getretrofit().create(NiuLbBiz.class).getData(params).enqueue(new Callback<NiuLb>() {
            @Override
            public void onResponse(Call<NiuLb> call, Response<NiuLb> response) {
                if (response.body().getLb_arr().size() != 0) {
                    for (int i = 0; i < response.body().getLb_arr().size(); i++) {

                        listlb.add(response.body().getLb_arr().get(i).getPicurl());
                    }
                }

                if (listlb.size() != 0) {
                    banner.setImages(listlb).setImageLoader(new GlideImageloader()).start();

                } else {
                    banner.setImages(list).setImageLoader(new GlideImageloader()).start();

                }
            }

            @Override
            public void onFailure(Call<NiuLb> call, Throwable t) {

            }
        });

    }

    private void getData() {
        Map<String, String> params = new HashMap<>();


        RetrofitUtil.getretrofit().create(GeniusBiz.class).getData(params).enqueue(new Callback<Genius>() {
            @Override
            public void onResponse(Call<Genius> call, final Response<Genius> response) {
                if(response.body()==null){
                 emptyUtil.showErrorPage();
                }else {


                lastid=response.body().getLastid();
                Log.d("牛人的数据", response.body().getNiu_arr().toString());
                Log.d("牛人的数据", response.body().getNiuart_arr().toString());
                Log.d("牛人的数据", DateUtils.getShortTime1(1499650846) + "@1502194661318@" + System.currentTimeMillis());
                adapter = new GeniusAdapter(response.body().getNiuart_arr());
                recyclerView.setAdapter(adapter);
                if (headerAdapter==null){

                    headerAdapter = new MediaHeaderAdapter(response.body().getNiu_arr());

                }
                rvheader.setAdapter(headerAdapter);
                getLBData();
                adapter.addHeaderView(view);

                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        CommonUtil.goAactivity(getActivity(), MediaArtDetailActivity.class, "newsurl",
                                Constants.NIU.concat("id=" + response.body().getNiuart_arr().get(position).getId() ),
                                "newsid",response.body().getNiuart_arr().get(position).getId(),
                                "newstitle",response.body().getNiuart_arr().get(position).getTitle(),
                                "description",response.body().getNiuart_arr().get(position).getDescription(),
                                "image",response.body().getNiuart_arr().get(position).getPicurl()


                        );
//                        CommonUtil.goAactivity(getActivity(), MediaArtDetailActivity.class, "newsurl",
//                                Constants.NIU.concat("id=" + response.body().getNiuart_arr().get(position).getId()));
                    }
                });
                headerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Genius.NiuArrBean entity = (Genius.NiuArrBean) adapter.getData().get(position);
                        Intent intent = new Intent(getActivity(), MediaActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("entity", entity);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

                adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        if(Network.isConnected()){

                            getMoreData();
                        }else {
                            ToastUtils.showToast("暂无网络");
                            adapter.loadMoreFail();
                        }
                    }
                });
            }
            }

            @Override
            public void onFailure(Call<Genius> call, Throwable t) {

            }
        });
    }

    private void getMoreData() {

        Map<String, String> params = new HashMap<>();
        params.put("lastid",lastid);
        params.put("act","loadmoreniu");
        RetrofitUtil.getretrofit().create(GeniusBiz.class).getData(params).enqueue(new Callback<Genius>() {
            @Override
            public void onResponse(Call<Genius> call, final Response<Genius> response) {
                if(response.body().getNiuart_arr().size()!=0){

                lastid=response.body().getLastid();
                    adapter.addData(response.body().getNiuart_arr());
                    adapter.loadMoreComplete();
                }else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<Genius> call, Throwable t) {
                  adapter.loadMoreFail();
            }
        });


    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_media;
    }


    @Override
    protected void initView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.item_media_header, null);
        viewheader = LayoutInflater.from(getActivity()).inflate(R.layout.item_media_header_header, null);
        textView = (TextView) view.findViewById(R.id.tv_media_more);
        banner = (Banner) view.findViewById(R.id.banner_media);
        rvheader = (RecyclerView) view.findViewById(R.id.rv_media_header);
        // linearLayout1 = (LinearLayout) view.findViewById(R.id.ll_media_tuijian);
        // linearLayout2 = (LinearLayout) view.findViewById(R.id.ll_media_niu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvheader.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        rvheader.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        rvheader.setFocusableInTouchMode(false);
        emptyUtil=new EmptyUtil(getActivity(),recyclerView);
        emptyUtil.initErrorPage();
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
//        linearLayout1.post(new Runnable() {
//            @Override
//            public void run() {
//                kkk = linearLayout1.getHeight();
//                kk = linearLayout2.getHeight();
//                Log.d("距离", kkk + "===" + kk);
//            }
//        });
        WindowManager wm = getActivity().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) banner.getLayoutParams();
        params1.height = (width * 2) / 5;
        params1.width = width;
        banner.setLayoutParams(params1);

    }

}
