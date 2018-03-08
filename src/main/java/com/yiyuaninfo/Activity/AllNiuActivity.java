package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Interface.AllNiuBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.AllNiuAdapter;
import com.yiyuaninfo.entity.AllNiu;
import com.yiyuaninfo.entity.Genius;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.view.MyDecoration;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaocongcong on 2017/9/27.
 */

public class AllNiuActivity  extends   BaseActivity{
    private RecyclerView  recyclerView;
    private String  lastid;
    private AllNiuAdapter  adapter;
    @Override
    protected int getContentView() {
        return R.layout.activity_allniu;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("我的订阅");
        recyclerView=(RecyclerView)findViewById(R.id.rv_allniu);
        recyclerView.addItemDecoration(new MyDecoration(this,MyDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
    }

    private void getData() {

        Map<String,String>  params=new HashMap<>();
        params.put("act","queall");
        params.put("userid", SPUtil.getUser(this).getUserid());
        RetrofitUtil.getretrofit().create(AllNiuBiz.class).getData(params).enqueue(new Callback<AllNiu>() {
            @Override
            public void onResponse(Call<AllNiu> call, Response<AllNiu> response) {
                lastid=response.body().getLastid();
                if(response.body().getAllsubarr().size()!=0){

                    adapter=new AllNiuAdapter(response.body().getAllsubarr());
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            /**
                             * id : 11
                             * userid : 1499064765j6qavy
                             * niu_id : 3
                             * niu_introduction : 【牛气表现】20世纪90年代初，方风雷参与筹建中国首家中外合资投资银行——中国国际金融有限公司，并出任副总裁，成为“第一代中国本土投资银行家”。从中金到中银，到工商东亚，再到高盛高华，以及如今的厚朴基金，投身投行十多年来，方风雷一直站在资本市场的最前沿。
                             * niu_name : 方风雷
                             * niu_head : http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170801/fangfenglei.jpg
                             * posttime : 2017-09-27 17:41:15
                             */

                            AllNiu.AllsubarrBean entity1=(AllNiu.AllsubarrBean)adapter.getData().get(position);
                            /**
                             * id : 1
                             * niu_name : 胡说
                             * niu_introduce :  小胡说的是八戒也不知道
                             * niu_tag : 小虎 八戒
                             * niu_img : http://img.bimg.126.net/photo/ZWdvRTBDrymgJueo73kgpw==/449797012800471966.jpg
                             * niu_pop : 112
                             * niu_modtime : 11133111
                             * niu_follow : 1231
                             * niu_type : 0
                             */
                            Genius.NiuArrBean  entity=new Genius.NiuArrBean(entity1.getNiu_id(),entity1.getNiu_name(),entity1.getNiu_introduction(),"",entity1.getNiu_head(),
                                    "","","","");

                            Intent intent=new Intent(AllNiuActivity.this,MediaActivity.class);

                            Bundle  bundle=new Bundle();
                            bundle.putSerializable("entity",entity);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });



                }else {
                   // adapter.setEmptyView();
                }
               if(adapter!=null){
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
            }





            }

            @Override
            public void onFailure(Call<AllNiu> call, Throwable t) {

            }
        });

    }

    private void getMoreData() {
        Map<String,String>  params=new HashMap<>();
        params.put("act","queall");
        params.put("userid",SPUtil.getUser(this).getUserid());
        params.put("lastid",lastid);
        RetrofitUtil.getretrofit().create(AllNiuBiz.class).getData(params).enqueue(new Callback<AllNiu>() {
            @Override
            public void onResponse(Call<AllNiu> call, Response<AllNiu> response) {
                lastid=response.body().getLastid();
                if(response.body().getAllsubarr().size()!=0){
                    adapter.addData(response.body().getAllsubarr());
                    adapter.loadMoreComplete();
                }else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<AllNiu> call, Throwable t) {

            }
        });
    }
}
