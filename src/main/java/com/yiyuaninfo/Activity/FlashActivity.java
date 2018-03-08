package com.yiyuaninfo.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Interface.FlashBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.FlashAdapter;
import com.yiyuaninfo.entity.Flash;
import com.yiyuaninfo.entity.FlashSection;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.view.MyDecoration;

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

public class FlashActivity extends BaseActivity {
    private RecyclerView  recyclerView;
    private List<FlashSection>  list=new ArrayList<>();
    private List<FlashSection>  listmore=new ArrayList<>();
    private FlashAdapter  adapter;
    private String lastid;
    private View mErrorView;
    private  LinearLayout   webParentView;
    @Override
    protected int getContentView() {
        return R.layout.activity_flash;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        setToolBarTitle("快讯列表");
        recyclerView=(RecyclerView)findViewById(R.id.rv_flash);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.addItemDecoration(new MyDecoration(this,MyDecoration.VERTICAL_LIST));
        webParentView=(LinearLayout)recyclerView.getParent();
        initErrorPage();
        getData();
    }

    private void getData() {
        Map<String,String>  params=new HashMap<>();
        params.put("act","morerollwords");
        RetrofitUtil.getretrofit().create(FlashBiz.class).getData(params).enqueue(new Callback<Flash>() {
            @Override
            public void onResponse(Call<Flash> call, final Response<Flash> response) {

                if(response.body()==null){
                   showErrorPage();
                }else {




                lastid=response.body().getLastdate();
                for (int i = 0; i <response.body().getRoll_words().size() ; i++) {
                    list.add(new FlashSection(true,response.body().getRoll_words().get(i).getDate()));
                    for (int j = 0; j <response.body().getRoll_words().get(i).getInfo().size() ; j++) {
                        list.add(new FlashSection(response.body().getRoll_words().get(i).getInfo().get(j)));
                    }
                }
                adapter=new FlashAdapter(R.layout.item_flash_content,R.layout.item_flash_header,list);
                recyclerView.setAdapter(adapter);
                adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                          getMoreData();

                            }
                        },2000);
                    }
                },recyclerView);
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                        switch (view.getId()){
                            case R.id.tv_flash_title:
                                FlashSection  entity=(FlashSection) adapter.getData().get(position);
                                CommonUtil.goAactivity(FlashActivity.this,MsgInfoActivity.class,"msgurl",Constants.FLASHDETAIL+
                                        entity.t.getWid());
                                break;
                    }
                    }
                });

                }

            }

            @Override
            public void onFailure(Call<Flash> call, Throwable t) {

            }
        });
    }

    private void getMoreData() {

        Map<String,String> params=new HashMap<>();
        params.put("act","morerollwords");
        params.put("lastdate",lastid);
        RetrofitUtil.getretrofit().create(FlashBiz.class).getData(params).enqueue(new Callback<Flash>() {
            @Override
            public void onResponse(Call<Flash> call, Response<Flash> response) {
                if(response.body().getRoll_words().size()!=0){

                    lastid=response.body().getLastdate();
                    for (int i = 0; i <response.body().getRoll_words().size() ; i++) {
                        listmore.add(new FlashSection(true,response.body().getRoll_words().get(i).getDate()));
                        for (int j = 0; j <response.body().getRoll_words().get(i).getInfo().size() ; j++) {
                            listmore.add(new FlashSection(response.body().getRoll_words().get(i).getInfo().get(j)));
                        }
                    }
                    adapter.addData(listmore);
                    adapter.loadMoreComplete();
                }else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<Flash> call, Throwable t) {

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
            mErrorView = View.inflate(this, R.layout.error_view, null);
        }
    }

}