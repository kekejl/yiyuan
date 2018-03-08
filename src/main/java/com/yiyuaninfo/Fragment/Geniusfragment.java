package com.yiyuaninfo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scrollablelayout.ScrollableHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yiyuaninfo.Activity.MediaActivity;
import com.yiyuaninfo.Activity.MediaArtDetailActivity;
import com.yiyuaninfo.Activity.MediaDetaiActivity;
import com.yiyuaninfo.Activity.NewsDetailActivity;
import com.yiyuaninfo.GlideImageloader;
import com.yiyuaninfo.Interface.GeniusBiz;
import com.yiyuaninfo.Listener.LoadMoreListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.GeniusAdapter;
import com.yiyuaninfo.adapter.GeniusHeaderAdapter;
import com.yiyuaninfo.entity.Genius;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.view.MyDecoration;
import com.yiyuaninfo.view.WrapContentHeightViewPager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/23.
 */

public class Geniusfragment extends TextBaseFragment  {
    private List<Genius.NiuartArrBean>  listarrbean=new ArrayList<>();
    private List<Genius.NiuArrBean>  listbean=new ArrayList<>();
    private GeniusAdapter adapter;
    @BindView(R.id.rv_media)
    RecyclerView recyclerView;
    private RecyclerView  rvheader;
    private View view;
    private View view1;
    private GeniusHeaderAdapter headerAdapter;
    private String lastid;
   // WrapContentHeightViewPager vp;

    int index=0;


//    public  void setViewPage(WrapContentHeightViewPager vp, int index){
//        this.vp=vp;
//        this.index=index;
//    }

    public  void setViewPage(){



    }
    public static Geniusfragment newInstance(String title) {


        Geniusfragment f = new Geniusfragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        view= LayoutInflater.from(getActivity()).inflate(R.layout.item_genius_header, null);
        view1= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_media, null);
        rvheader=(RecyclerView)view.findViewById(R.id.rv_media_header);
        recyclerView=(RecyclerView)view1.findViewById(R.id.rv_media) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvheader.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        rvheader.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        rvheader.setFocusableInTouchMode(false);

//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//
//       ViewGroup.LayoutParams.MATCH_PARENT));

//        if(vp!=null){
//
//        vp.setObjectForPosition(view1,index);
//        }
       getData();

        return view1;
    }


    private void getData() {
        Map<String,String> params=new HashMap<>();
        RetrofitUtil.getretrofitContext(getActivity()).create(GeniusBiz.class).getData(params).enqueue(new Callback<Genius>() {
            @Override
            public void onResponse(Call<Genius> call,final   Response<Genius> response) {
                lastid=response.body().getLastid();
                Log.d("牛人的数据",response.body().getNiu_arr().toString());
                Log.d("牛人的数据",response.body().getNiuart_arr().toString());
                Log.d("牛人的数据", DateUtils.getShortTime1(1499650846)+"@1502194661318@"+System.currentTimeMillis());
                adapter=new GeniusAdapter(response.body().getNiuart_arr());
                recyclerView.setAdapter(adapter);
                adapter.addHeaderView(view);

                headerAdapter=new GeniusHeaderAdapter(response.body().getNiu_arr());
                rvheader.setAdapter(headerAdapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                        CommonUtil.goAactivity(getActivity(), MediaArtDetailActivity.class, "newsurl",
                                Constants.NIU.concat("id=" + response.body().getNiuart_arr().get(position).getId() ),
                                "newsid",response.body().getNiuart_arr().get(position).getId(),
                                "newstitle",response.body().getNiuart_arr().get(position).getTitle(),
                                "description",response.body().getNiuart_arr().get(position).getDescription(),
                                "image",response.body().getNiuart_arr().get(position).getPicurl()



                                );
                    }
                });
                headerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Genius.NiuArrBean  entity=(Genius.NiuArrBean)adapter.getData().get(position);
                        Intent intent=new Intent(getActivity(),MediaActivity.class);

                        Bundle  bundle=new Bundle();
                        bundle.putSerializable("entity",entity);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

//                Homefragment.setLoadMoreListener(new LoadMoreListener() {
//                    @Override
//                    public void loadmore(RefreshLayout refreshLayout) {
//                        getMoreData(refreshLayout);
//                    }
//                });
                adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        getMoreData();
                    }
                },recyclerView);
            }

            @Override
            public void onFailure(Call<Genius> call, Throwable t) {

            }
        });
    }

    private void getMoreData() {

        Map<String, String> params = new HashMap<>();
        params.put("lastid",lastid);
        params.put("act","loadmoreniu");
        RetrofitUtil.getretrofitContext(getActivity()).create(GeniusBiz.class).getData(params).enqueue(new Callback<Genius>() {
            @Override
            public void onResponse(Call<Genius> call, final Response<Genius> response) {

                if(response.body().getNiuart_arr().size()!=0){
                    lastid=response.body().getLastid();
                    adapter.addData(response.body().getNiuart_arr());
                    adapter.loadMoreComplete();
                }else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<Genius> call, Throwable t) {
                adapter.loadMoreFail();
            }
        });


    }


    @Override
    public View getScrollableView() {
        return recyclerView;
    }

    @Override
    public void pullToRefresh() {

    }

    @Override
    public void refreshComplete() {

    }

    @Override
    public void recycleviewTop() {

    }
}