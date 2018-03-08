package com.yiyuaninfo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Activity.FinderActivity;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Activity.NewsDetailActivity;
import com.yiyuaninfo.Activity.PayActivity;
import com.yiyuaninfo.Activity.VipActivity;
import com.yiyuaninfo.Interface.CPBiz;
import com.yiyuaninfo.Interface.CPCompanyBiz;
import com.yiyuaninfo.Interface.FinderBiz;
import com.yiyuaninfo.Interface.FinderMoreBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.CPAdapter;
import com.yiyuaninfo.adapter.CPCompanyAdapter;
import com.yiyuaninfo.adapter.FinderAdapter;
import com.yiyuaninfo.entity.CPCompany;
import com.yiyuaninfo.entity.Finder;
import com.yiyuaninfo.entity.FinderBean;
import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.FlowLayout;
import com.yiyuaninfo.view.MyDecoration;
import com.yiyuaninfo.view.WrapContentHeightViewPager;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
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

public class CPFragment extends Fragment {
    private String channel;
    private String act;
    private CPAdapter cpAdapter;
    private RecyclerView recycleview;
    private List<Product.ProArrBean> list = new ArrayList<>();
    private CPCompanyAdapter adapter;
    //绑定数据
    private String lastid;
    private String cplastid;
    // WrapContentHeightViewPager vp;
    int index = 0;
    private View  viewheader;
    private TextView   name,price,state;
    private LinearLayout  linearLayout;
    private ImageView  imageView;
    public void setViewPage(WrapContentHeightViewPager vp, int index) {
        // this.vp=vp;
        this.index = index;
    }
    private   View   mErrorView;
    private  LinearLayout webParentView;

    public static CPFragment newInstance(String channel) {

        Bundle args = new Bundle();
        args.putString("channel", channel);
        CPFragment fragment = new CPFragment();
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
         viewheader = LayoutInflater.from(getActivity()).inflate(R.layout.item_cp_vip, null);
        imageView=(ImageView)viewheader.findViewById(R.id.image_cp_vip);
        linearLayout=(LinearLayout)viewheader.findViewById(R.id.ll_cp_vip);
        name=(TextView)viewheader.findViewById(R.id.tv_cp_companyname_vip);
        price=(TextView)viewheader.findViewById(R.id.tv_cp_price_vip);
        state=(TextView)viewheader.findViewById(R.id.tv_cp_state_vip);
        recycleview = (RecyclerView) view.findViewById(R.id.recycler_find);

        recycleview.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recycleview.setLayoutManager(manager);
        webParentView=(LinearLayout)recycleview.getParent();
        initErrorPage();
        getString(channel);
        return view;
    }

    private void getString(String channel) {
        switch (channel) {
            case "产品分类":
                getData();
                break;
            case "公司分类":
                getCompanyData();
                break;
            case "0":
                CommonUtil.showToast(getActivity(), "暂无数据");
                break;
        }

    }

    private void getCompanyData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "company");
        RetrofitUtil.getretrofit().create(CPCompanyBiz.class).getData(params).enqueue(new Callback<CPCompany>() {
            @Override
            public void onResponse(Call<CPCompany> call, final Response<CPCompany> response) {

                if(response.body()==null){

                    showErrorPage();

                }else {
                    lastid = response.body().getLastid();
                    adapter = new CPCompanyAdapter(getActivity(), response.body().getCom_arr());
                    recycleview.setAdapter(adapter);
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            FinderBean entity = (FinderBean) adapter.getData().get(position);
                            CommonUtil.goAactivity(getActivity(), FinderActivity.class, "comid", entity.getId());


                        }
                    });

                    adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            recycleview.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(Network.isConnected()){

                                        getCPCompanyData();
                                    }else {
                                        ToastUtils.showToast("暂无网络");
                                        adapter.loadMoreFail();
                                    }
                                }
                            }, 1000);
                        }
                    }, recycleview);
                    adapter.disableLoadMoreIfNotFullPage();
                }
            }

            @Override
            public void onFailure(Call<CPCompany> call, Throwable t) {

            }
        });


    }

    private void getCPCompanyData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "commore");
        params.put("lastid", lastid);
        RetrofitUtil.getretrofit().create(CPCompanyBiz.class).getData(params).enqueue(new Callback<CPCompany>() {
            @Override
            public void onResponse(Call<CPCompany> call, Response<CPCompany> response) {

                    if (response.body().getCom_arr().size() != 0) {
                        lastid = response.body().getLastid();
                        adapter.addData(response.body().getCom_arr());
                        adapter.loadMoreComplete();
                    } else {
                        adapter.loadMoreEnd();

                    }
            }

            @Override
            public void onFailure(Call<CPCompany> call, Throwable t) {

            }
        });

    }

    private void getData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "product");
        RetrofitUtil.getretrofit().create(CPBiz.class).getData(params).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, final Response<Product> response) {
                if(response.body()==null){

                    showErrorPage();

                }else {
                    Log.d("产品数据", response.body() + "1221");
                    cplastid = response.body().getLastid();
                    list = response.body().getPro_arr();
                    Log.d("产品数据", list.size() + "!!!!!!");
                    cpAdapter = new CPAdapter(getActivity(), R.layout.item_cp, list);
                    recycleview.setAdapter(cpAdapter);
                    cpAdapter.setHeaderView(viewheader);
                    name.setText(response.body().getVip().getTitle());
                    price.setText("￥" + response.body().getVip().getExpense() + "/年");
                    ImageLoaderUtils.displayImage(response.body().getVip().getTitleimg(), imageView);
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonUtil.goAactivity(getActivity(), VipActivity.class, "type", "1", "id", response.body().getVip().getId());
                        }
                    });
                    cpAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Product.ProArrBean entity = (Product.ProArrBean) adapter.getData().get(position);
                            Intent intent = new Intent(getActivity(), VipActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("entity", entity);
                            intent.putExtra("type", "2");

                            intent.putExtras(bundle);
                            startActivity(intent);


                        }
                    });

                    cpAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            recycleview.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(Network.isConnected()){

                                        getMoreData();
                                    }else {
                                        ToastUtils.showToast("暂无网络");
                                        adapter.loadMoreFail();
                                    }

                                }
                            }, 1000);
                        }


                    }, recycleview);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });


    }

    private void getMoreData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "promore");
        params.put("lastid", cplastid);
        RetrofitUtil.getretrofit().create(CPBiz.class).getData(params).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                cplastid=response.body().getLastid();
                if(response.body().getPro_arr().size()!=0){
                    cpAdapter.addData(response.body().getPro_arr());
                    cpAdapter.loadMoreComplete();
                }else {
                    cpAdapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

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
