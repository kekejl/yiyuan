package com.yiyuaninfo.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yiyuaninfo.Interface.SearchEntityBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.SearchSectionAdapter;
import com.yiyuaninfo.entity.SearchBean;
import com.yiyuaninfo.entity.SearchEntity;
import com.yiyuaninfo.entity.SearchSection;
import com.yiyuaninfo.util.EmptyUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.view.MyDecoration;
import com.yiyuaninfo.view.Search.ICallBack;
import com.yiyuaninfo.view.Search.IsShow;
import com.yiyuaninfo.view.Search.SearchView;
import com.yiyuaninfo.view.Search.bCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class SearchActivity extends BaseActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<SearchSection> list = new ArrayList<>();
    private SearchSectionAdapter  adapter;
    private EmptyUtil  emptyUtil;
    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isshowToolBar(false);
        searchView = (SearchView) findViewById(R.id.search_view);
        recyclerView = (RecyclerView) findViewById(R.id.rv_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this,MyDecoration.VERTICAL_LIST));
        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        recyclerView.setVisibility(View.GONE);
        emptyUtil=new EmptyUtil(this,recyclerView);
        emptyUtil.initErrorPage();
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                getData(string);


            }
        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });
        searchView.isShowRV(new IsShow() {
            @Override
            public void IsShow(String string) {
                if(string.equals("0")){

                recyclerView.setVisibility(View.GONE);
                }else {
                    recyclerView.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    private void getData(String keyword) {
        Map<String, String> params = new HashMap<>();
        params.put("act", "global");
        params.put("keyword", keyword);
        RetrofitUtil.getretrofit().create(SearchEntityBiz.class).getData(params).enqueue(new Callback<SearchEntity>() {
            @Override
            public void onResponse(Call<SearchEntity> call, Response<SearchEntity> response) {

                if(response.body()==null){
                   emptyUtil.showErrorPage();
                }else {



                recyclerView.setVisibility(View.VISIBLE);
                list.clear();

                if (response.body().getArt_arr().size() != 0) {
                    list.add(new SearchSection(true, "文章资讯"));
                    for (int i = 0; i < response.body().getArt_arr().size(); i++) {
                        SearchBean  searchBean1=response.body().getArt_arr().get(i);
                        searchBean1.setType("1");
                        list.add(new SearchSection(searchBean1));
                    }
                }
                if (response.body().getVid_arr().size() != 0) {
                    list.add(new SearchSection(true, "视频"));
                    for (int i = 0; i < response.body().getVid_arr().size(); i++) {
                        SearchBean  searchBean2=response.body().getVid_arr().get(i);
                        searchBean2.setType("2");
                        list.add(new SearchSection(searchBean2));                    }
                }
                if (response.body().getSh_arr().size() != 0) {
                    list.add(new SearchSection(true, "演出"));
                    for (int i = 0; i < response.body().getSh_arr().size(); i++) {
                        SearchBean  searchBean3=response.body().getSh_arr().get(i);
                        searchBean3.setType("3");
                        list.add(new SearchSection(searchBean3));                    }
                }
                if (response.body().getNr_arr().size() != 0) {
                    list.add(new SearchSection(true, "牛人观点"));
                    for (int i = 0; i < response.body().getNr_arr().size(); i++) {
                        SearchBean  searchBean4=response.body().getNr_arr().get(i);
                        searchBean4.setType("4");
                        list.add(new SearchSection(searchBean4));                    }
                }

                if (response.body().getSm_arr().size() != 0) {
                    list.add(new SearchSection(true, "项目"));
                    for (int i = 0; i < response.body().getSm_arr().size(); i++) {
                        SearchBean  searchBean5=response.body().getSm_arr().get(i);
                        searchBean5.setType("5");
                        list.add(new SearchSection(searchBean5));                    }
                }
                if (response.body().getSa_arr().size() != 0) {
                    list.add(new SearchSection(true, "投顾、券商、基金"));
                    for (int i = 0; i < response.body().getSa_arr().size(); i++) {
                        SearchBean  searchBean6=response.body().getSa_arr().get(i);
                        searchBean6.setType("6");
                        list.add(new SearchSection(searchBean6));                    }
                }
                adapter=new SearchSectionAdapter(R.layout.item_search,R.layout.item_search_header,list,SearchActivity.this);
                recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<SearchEntity> call, Throwable t) {

            }
        });
    }
}