package com.yiyuaninfo.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nostra13.universalimageloader.utils.L;
import com.yiyuaninfo.Activity.FinderActivity;
import com.yiyuaninfo.Interface.FinderBiz;
import com.yiyuaninfo.Interface.FinderMoreBiz;
import com.yiyuaninfo.Interface.JJAllBiz;
import com.yiyuaninfo.Interface.JJBiz;
import com.yiyuaninfo.Interface.QSAllBiz;
import com.yiyuaninfo.Interface.QSBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.FinderAdapter;
import com.yiyuaninfo.entity.Finder;
import com.yiyuaninfo.entity.FinderBean;
import com.yiyuaninfo.entity.TouGu;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.FlowLayout;
import com.yiyuaninfo.view.MyDecoration;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaocongcong on 2017/7/19.
 */

public class JJFragment extends Fragment {
    private String channel;
    private String act;
    private int id;
    private FinderAdapter  finderAdapter;
    private FinderAdapter  finderAdapterHeaer;
    private RecyclerView recycleview;
    private RecyclerView recycleviewheader;
    private List<String> taglist = new ArrayList<>();
    private FlowLayout flowlayout;
    private LoadMoreWrapper  loadMoreWrapper;
    private List<FinderBean>  listFinder;
    private View   viewheader;
    private TextView  textView;
    private String lastid,lastidtj;
    private TextView   textViewChange;
    private ImageView imageview;
    private Animation myAlphaAnimation;//声明Animation类的对象
    private  View  mErrorView;
    private  LinearLayout  webParentView;
    //绑定数据
    public static JJFragment newInstance(String channel) {

        Bundle args = new Bundle();
        args.putString("channel", channel);
        JJFragment fragment = new JJFragment();
        fragment.setArguments(args);
        return fragment;
    }
    //获取数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        channel = args != null ? args.getString("channel", "0") : "0";
        Log.d("fragment获取的数据", channel);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_find, null);
        viewheader = LayoutInflater.from(getActivity()).inflate(R.layout.item_tougu_header, null);

        recycleview = (RecyclerView) view.findViewById(R.id.recycler_find);
        recycleviewheader = (RecyclerView) viewheader.findViewById(R.id.rv_tougu_header);

        textViewChange=(TextView)viewheader.findViewById(R.id.tv_tg_change);
        imageview=(ImageView)viewheader.findViewById(R.id.iv_tg_change);
        textView = (TextView) viewheader.findViewById(R.id.tv_tougu_header);
        myAlphaAnimation= AnimationUtils.loadAnimation(getActivity(), R.anim.loading);
        myAlphaAnimation.setInterpolator(new LinearInterpolator());

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(getActivity());
        recycleview.setLayoutManager(manager);
        recycleviewheader.setLayoutManager(manager1);
        recycleview.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        recycleviewheader.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        recycleviewheader.setFocusableInTouchMode(false);
        webParentView=(LinearLayout)recycleview.getParent();
        initErrorPage();
        getString(channel);

        textViewChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(Network.isConnected()){

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gettjData();

                    }
                },1000);
                if(myAlphaAnimation!=null){
                    imageview.startAnimation(myAlphaAnimation);

                }
                }else {
                     ToastUtils.showToast("暂无网络");
                 }
            }
        });
        return view;
    }
    private void gettjData() {

        Map<String,String> params=new HashMap<>();
        params.put("act","jjchange");
        RetrofitUtil.getretrofit().create(JJAllBiz.class).getData(params).enqueue(new Callback<TouGu>() {
            @Override
            public void onResponse(Call<TouGu> call, Response<TouGu> response) {
                if(response.body().getRecommend().size()!=0){
                    finderAdapterHeaer.setNewData(response.body().getRecommend());
                    imageview.clearAnimation();

                }
            }

            @Override
            public void onFailure(Call<TouGu> call, Throwable t) {

            }
        });



    }
    private void getString(String channel) {
        switch (channel) {
            case "全部":
                act = "jijin";
                textView.setVisibility(View.GONE);
                getAllData();
                break;
            case "股票型":
                act = "notall";
                id = 1;
                getData();
                break;
            case "货币型":
                act = "notall";
                id = 2;
                getData();
                break;
            case "债券型":
                act = "notall";
                id = 3;
                getData();
                break;
            case "0":
                CommonUtil.showToast(getActivity(), "暂无数据");
                break;
        }

    }
    private void getAllData() {

        Map<String,String>  params=new HashMap<>();
        params.put("act",act);
        RetrofitUtil.getretrofit().create(JJAllBiz.class).getData(params).enqueue(new Callback<TouGu>() {
            @Override
            public void onResponse(Call<TouGu> call, final Response<TouGu> response) {

                if(response.body()==null){

                    showErrorPage();

                }else {

                    lastidtj = response.body().getLastid();
                    Log.d("券商全部数据", response.body().getOther().toString());
                    finderAdapter = new FinderAdapter(getActivity(), response.body().getOther(), "券商");
                    recycleview.setAdapter(finderAdapter);
                    finderAdapter.addHeaderView(viewheader);
                    finderAdapterHeaer = new FinderAdapter(getActivity(), response.body().getRecommend(), "券商");
                    recycleviewheader.setAdapter(finderAdapterHeaer);
                    finderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            recycleview.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(Network.isConnected()){

                                        getAllMoreData();
                                    }else {
                                        ToastUtils.showToast("暂无网络");
                                        finderAdapter.loadMoreFail();
                                    }                                  }
                            }, 1000);
                        }
                    }, recycleview);
                    finderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            FinderBean entity = (FinderBean) adapter.getData().get(position);
                            CommonUtil.goAactivity(getActivity(), FinderActivity.class, "comid", entity.getId());

                        }
                    });
                    finderAdapterHeaer.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            FinderBean entity = (FinderBean) adapter.getData().get(position);
                            CommonUtil.goAactivity(getActivity(), FinderActivity.class, "comid", entity.getId());

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<TouGu> call, Throwable t) {

            }

        });


    }

    private void getAllMoreData() {
        Map<String,String> params=new HashMap<>();
        params.put("act","allmore");
        params.put("lastid",lastidtj);
        RetrofitUtil.getretrofit().create(JJAllBiz.class).getData(params).enqueue(new Callback<TouGu>() {
            @Override
            public void onResponse(Call<TouGu> call, Response<TouGu> response) {
                lastidtj=response.body().getLastid();
                if(response.body().getOther().size()!=0){
                    finderAdapter.addData(response.body().getOther());
                    finderAdapter.loadMoreComplete();
                }else {
                    finderAdapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<TouGu> call, Throwable t) {
                finderAdapter.loadMoreFail();
            }
        });


    }
    private void getData() {

        Map<String,String>  params=new HashMap<>();
        params.put("act",act);
        params.put("keyword",id+"");
        RetrofitUtil.getretrofit().create(JJBiz.class).getData(params).enqueue(new Callback<Finder>() {
            @Override
            public void onResponse(Call<Finder> call, final Response<Finder> response) {
                if(response.body()==null){

                    showErrorPage();

                }else {
                    Log.d("基金数据", response.body().getOther().toString());
                    lastid = response.body().getLastid();
                    listFinder = response.body().getOther();
                    finderAdapter = new FinderAdapter(getActivity(), listFinder, "基金");
                    recycleview.setAdapter(finderAdapter);
                    finderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            recycleview.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(Network.isConnected()){

                                        getMoreData();
                                    }else {
                                        ToastUtils.showToast("暂无网络");
                                        finderAdapter.loadMoreFail();
                                    }  ;
                                }
                            }, 1000);
                        }
                    }, recycleview);
                    finderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            FinderBean entity = (FinderBean) adapter.getData().get(position);
                            CommonUtil.goAactivity(getActivity(), FinderActivity.class, "comid", entity.getId());

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<Finder> call, Throwable t) {

            }
        });

    }
    private void getMoreData() {
        Map<String,String>  params=new HashMap<>();
        params.put("act","othermore");
        params.put("keyword",id+"");
        params.put("lastid",lastid);
        RetrofitUtil.getretrofit().create(JJBiz.class).getData(params).enqueue(new Callback<Finder>() {
            @Override
            public void onResponse(Call<Finder> call, Response<Finder> response) {
                lastid=response.body().getLastid();
                if(response.body().getOther().size()!=0){
                    finderAdapter.addData(response.body().getOther());
                    finderAdapter.loadMoreComplete();
                }else {
                    finderAdapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<Finder> call, Throwable t) {
                finderAdapter.loadMoreFail();
            }
        });

    }

    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    private void showErrorPage() {
        webParentView.removeAllViews(); //移除加载网页错误时，默认的提示信息
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        webParentView.addView(mErrorView, 0, layoutParams); //添加自定义的错误提示的View

    }

    /***
     * 显示加载失败时自定义的网页
     */
    private void initErrorPage() {
        if (mErrorView == null) {
            mErrorView = View.inflate(getActivity(), R.layout.error_view, null);
        }
    }

}
