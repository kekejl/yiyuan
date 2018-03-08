package com.yiyuaninfo.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Activity.XMDetailActivity;
import com.yiyuaninfo.Interface.FinderMoreBiz;
import com.yiyuaninfo.Interface.JJBiz;
import com.yiyuaninfo.Interface.ProjectBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.FinderAdapter;
import com.yiyuaninfo.adapter.XMAdapter;
import com.yiyuaninfo.entity.Finder;
import com.yiyuaninfo.entity.Project;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.FlowLayout;
import com.yiyuaninfo.view.MyDecoration;
import com.yiyuaninfo.view.WrapContentHeightViewPager;
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

public class XMFragment extends Fragment {
    private String channel;
    private String act;
    private int id;
    private XMAdapter adapter;
    private RecyclerView recycleview;
    private  View  mErrorView;
    private  LinearLayout webParentView;
    //绑定数据
    WrapContentHeightViewPager vp;
    int index = 0;
    private String lastid;

    public void setViewPage(WrapContentHeightViewPager vp, int index) {
        this.vp = vp;
        this.index = index;
    }

    public static XMFragment newInstance(String channel) {

        Bundle args = new Bundle();
        args.putString("channel", channel);
        XMFragment fragment = new XMFragment();
        fragment.setArguments(args);
        return fragment;
    }
    //获取数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        channel = args != null ? args.getString("channel", "0") : "0";
        Log.d("项目频道iD", channel);


        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_find, null);

        recycleview = (RecyclerView) view.findViewById(R.id.recycler_find);
        recycleview.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recycleview.setLayoutManager(manager);
        webParentView=(LinearLayout)recycleview.getParent();
        initErrorPage();
        getData();
        return view;
    }


    private void getData() {
        Map<String, String> params = new HashMap<>();
        if (channel.equals("00")) {
            params.put("act", "xiangmu");

        } else {
            params.put("act", "other");
            params.put("keyword", channel);

        }

        RetrofitUtil.getretrofit().create(ProjectBiz.class).getData(params).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if(response.body()==null){

                    showErrorPage();

                }else {

                    lastid = response.body().getLastid();
                    Log.d("基金数据", response.body().getRecommend().toString());
                    adapter = new XMAdapter(getActivity(), response.body().getRecommend());
                    recycleview.setAdapter(adapter);
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Log.d("adapter111", adapter.getData().get(position).toString());
                            Project.RecommendBean recommendBean = (Project.RecommendBean) adapter.getData().get(position);
                            CommonUtil.goAactivity(getActivity(), XMDetailActivity.class, "xmurl", Constants.XMDETAIL
                                    + recommendBean.getId(), "id", recommendBean.getId());
                        }
                    });
                    adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            switch (view.getId()) {
                                case R.id.tv_xm_zx:
                                   // ToastUtils.showToast("呵呵额呵呵额呵呵哈");
                                    break;
                            }
                        }
                    });
                    adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            recycleview.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(Network.isConnected()){

                                        getMoreData(lastid);

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
            public void onFailure(Call<Project> call, Throwable t) {

            }
        });
    }

    private void getMoreData(final String id) {
        Map<String, String> params = new HashMap<>();
        if (channel.equals("00")) {
            params.put("act", "moretj");
            params.put("lastid", id);

        } else {
            params.put("act", "moreother");
            params.put("keyword", channel);
            params.put("lastid", id);
        }
        RetrofitUtil.getretrofit().create(ProjectBiz.class).getData(params).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {

                if (response.body().getRecommend().size() != 0) {
                    adapter.addData(response.body().getRecommend());
                    adapter.loadMoreComplete();
                    lastid=response.body().getLastid();
                } else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                adapter.loadMoreFail();
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
