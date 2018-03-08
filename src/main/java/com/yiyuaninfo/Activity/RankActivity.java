package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nostra13.universalimageloader.utils.L;
import com.yiyuaninfo.Interface.RankArrBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.RankArrAdapter;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.entity.RankArr;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.view.MyDecoration;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaocongcong on 2017/9/26.
 */

public class RankActivity extends BaseActivity {
    private RecyclerView recyclerView;

    private String classid;
    private String ranklastid;
    private RankArrAdapter  adapter;
    @Override
    protected int getContentView() {
        return R.layout.activity_rank;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Intent  intent=getIntent();
        classid=intent.getStringExtra("classid");
        ranklastid=intent.getStringExtra("ranklastid");
        setToolBarTitle("更多排行");
        recyclerView = (RecyclerView) findViewById(R.id.rv_rank);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        getData();
    }

    private void getData() {
        Map<String,String> params=new HashMap<>();
        params.put("act","rankmore");
        params.put("classid",classid);
        params.put("rank_lastid",ranklastid);
        Log.d("更多排行",classid+"++++"+ranklastid);
        RetrofitUtil.getretrofit().create(RankArrBiz.class).getData(params).enqueue(new Callback<RankArr>() {
            @Override
            public void onResponse(Call<RankArr> call, Response<RankArr> response) {
                adapter=new RankArrAdapter(response.body().getRank_arr());
                ranklastid=response.body().getRank_lastid();
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
                        RankArr.RankArrBean  entity=(RankArr.RankArrBean) adapter.getData().get(position);
                        CommonUtil.goAactivity(RankActivity.this, WebViewActivity.class, "msgurl", entity.getSelf_link(),"typ","1");
                    }
                });
            }

            @Override
            public void onFailure(Call<RankArr> call, Throwable t) {

            }
        });
    }

    private void getMoreData() {
        Map<String,String> params=new HashMap<>();
        params.put("act","rankmore");
        params.put("classid",classid);
        params.put("rank_lastid",ranklastid);
        Log.d("更多排行",classid+"++++"+ranklastid);
        RetrofitUtil.getretrofit().create(RankArrBiz.class).getData(params).enqueue(new Callback<RankArr>() {
            @Override
            public void onResponse(Call<RankArr> call, Response<RankArr> response) {
                ranklastid = response.body().getRank_lastid();
                if(response.body().getRank_arr().size()!=0){
                    adapter.addData(response.body().getRank_arr());
                    adapter.loadMoreComplete();
                }else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<RankArr> call, Throwable t) {

            }
        });


    }
}
