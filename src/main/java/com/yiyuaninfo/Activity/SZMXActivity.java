package com.yiyuaninfo.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Interface.DuiHuanBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.DuiHuanAdapter;
import com.yiyuaninfo.adapter.SZMXAdapter;
import com.yiyuaninfo.entity.DuiHuan;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.view.MyDecoration;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class SZMXActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private SZMXAdapter adapter;
    private String lastid;

    @Override
    protected int getContentView() {
        return R.layout.activity_szmx;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("收支明细");
        recyclerView = (RecyclerView) findViewById(R.id.rv_szmx);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        getDate();


    }

    private void getDate() {

        Map<String, String> params = new HashMap<>();
        params.put("userid", SPUtil.getUser(this).getUserid());
        params.put("act", "inall");
        RetrofitUtil.getretrofit().create(DuiHuanBiz.class).getData(params).enqueue(new Callback<DuiHuan>() {
            @Override
            public void onResponse(Call<DuiHuan> call, Response<DuiHuan> response) {
                lastid = response.body().getLastid();
                adapter = new SZMXAdapter(R.layout.item_szmx, response.body().getInout());
                recyclerView.setAdapter(adapter);
                adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                getMoreData();
                            }
                        }, 1000);

                    }
                }, recyclerView);
            }

            @Override
            public void onFailure(Call<DuiHuan> call, Throwable t) {

            }
        });

    }

    private void getMoreData() {
        Map<String, String> params = new HashMap<>();
        params.put("userid",  SPUtil.getUser(this).getUserid());
        params.put("act", "out");
        params.put("lastid", lastid);
        RetrofitUtil.getretrofit().create(DuiHuanBiz.class).getData(params).enqueue(new Callback<DuiHuan>() {
            @Override
            public void onResponse(Call<DuiHuan> call, Response<DuiHuan> response) {
                if (response.body().getInout().size() != 0) {
                    lastid=response.body().getLastid();
                    adapter.addData(response.body().getInout());
                    adapter.loadMoreComplete();
                } else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<DuiHuan> call, Throwable t) {

            }
        });

    }

}
