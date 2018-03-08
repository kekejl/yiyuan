package com.yiyuaninfo.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Activity.WebViewActivity;
import com.yiyuaninfo.Interface.WeeklyBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.WeekAdapter;
import com.yiyuaninfo.entity.Weekly;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.view.MyDecoration;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaocongcong on 2017/9/23.
 */

public class MsgListFragment extends BaseFragment {
    @BindView(R.id.recycler_msglist)
    RecyclerView recyclerView;
    private String channel;
    private String act;
    private String lastid;
    private WeekAdapter  adapter;
    public static MsgListFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id",id);
        MsgListFragment fragment = new MsgListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        final Bundle args = getArguments();
        channel = args != null ? args.getString("id", "0") : "0";
        if(channel.equals("1")){
            act="week";
        }else if(channel.equals("2")){
            act="moon";
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Map<String,String> params=new HashMap<>();
        params.put("act",act);
        RetrofitUtil.getretrofit().create(WeeklyBiz.class).getData(params).enqueue(new Callback<Weekly>() {
            @Override
            public void onResponse(Call<Weekly> call, final Response<Weekly> response) {
                lastid=response.body().getLastid();
                adapter=new WeekAdapter(R.layout.item_weekly,response.body().getInfo(),getActivity());
                recyclerView.setAdapter(adapter);
                adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            getMoreData();
                            }
                        },1000);
                    }
                },recyclerView);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Weekly.InfoBean  entity=(Weekly.InfoBean)adapter.getData().get(position);
                        CommonUtil.goAactivity(getActivity(), WebViewActivity.class,
                                "msgurl", Constants.WEEKLY+"?id="+
                                       entity.getId(),"typ","1");
                    }
                });
            }

            @Override
            public void onFailure(Call<Weekly> call, Throwable t) {

            }
        });

    }

    private void getMoreData() {
        Map<String,String> params=new HashMap<>();
        params.put("act",act);
        params.put("lastid",lastid);
        RetrofitUtil.getretrofit().create(WeeklyBiz.class).getData(params).enqueue(new Callback<Weekly>() {
            @Override
            public void onResponse(Call<Weekly> call, Response<Weekly> response) {
                lastid=response.body().getLastid();
                if(response.body().getInfo().size()!=0){
                  adapter.addData(response.body().getInfo());
                    adapter.notifyDataSetChanged();
                    adapter.loadMoreComplete();
                }else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<Weekly> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initView() {
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_msglist;
    }
}
