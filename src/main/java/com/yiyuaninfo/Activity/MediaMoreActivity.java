package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Interface.GeniusBiz;
import com.yiyuaninfo.Interface.GeniusMoreBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.MediaHeaderAdapter;
import com.yiyuaninfo.entity.Genius;
import com.yiyuaninfo.util.EmptyUtil;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.MyDecoration;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by gaocongcong on 2017/9/26.
 */

public class MediaMoreActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private MediaHeaderAdapter adapter;
    private String lastid;
    private  EmptyUtil  emptyUtil;

    @Override
    protected int getContentView() {
        return R.layout.activity_mediamore;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("牛人榜");
        recyclerView = (RecyclerView) findViewById(R.id.rv_mediamore);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        emptyUtil=new EmptyUtil(this,recyclerView);
        emptyUtil.initErrorPage();
        getData();
    }

    private void getData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "niumore");
        RetrofitUtil.getretrofit().create(GeniusMoreBiz.class).getData(params).enqueue(new Callback<Genius>() {
            @Override
            public void onResponse(Call<Genius> call, Response<Genius> response) {
                if(response.body()==null){
                   emptyUtil.showErrorPage();
                }else {

                adapter = new MediaHeaderAdapter(response.body().getNiu_arr());
                lastid = response.body().getLastid();
                recyclerView.setAdapter(adapter);
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
                }, recyclerView);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Genius.NiuArrBean  entity=(Genius.NiuArrBean)adapter.getData().get(position);
                        Intent intent=new Intent(MediaMoreActivity.this,MediaActivity.class);

                        Bundle  bundle=new Bundle();
                        bundle.putSerializable("entity",entity);
                        intent.putExtras(bundle);
                        startActivity(intent);
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
        params.put("act", "niumore");
        params.put("lastid", lastid);

        RetrofitUtil.getretrofit().create(GeniusMoreBiz.class).getData(params).enqueue(new Callback<Genius>() {
            @Override
            public void onResponse(Call<Genius> call, Response<Genius> response) {

                if (response.body().getNiu_arr().size() != 0) {
                    lastid=response.body().getLastid();
                    adapter.addData(response.body().getNiu_arr());
                    adapter.loadMoreComplete();
                } else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<Genius> call, Throwable t) {
                adapter.loadMoreFail();
            }
        });


    }
}
