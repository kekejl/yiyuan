package com.yiyuaninfo.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Activity.FinderActivity;
import com.yiyuaninfo.Interface.FinderBiz;
import com.yiyuaninfo.Interface.FinderMoreBiz;
import com.yiyuaninfo.Interface.TouGuBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.FinderAdapter;
import com.yiyuaninfo.entity.Finder;
import com.yiyuaninfo.entity.FinderBean;
import com.yiyuaninfo.entity.TouGu;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.FlowLayout;
import com.yiyuaninfo.view.MyDecoration;
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

public class TGFragment extends Fragment {
    private String channel;
    private String act;
    private int id;
    private FinderAdapter  finderAdapter;
    private FinderAdapter  finderAdapterHeaer;
    private RecyclerView recycleview;
    private RecyclerView recycleviewheader;
    private List<String> taglist = new ArrayList<>();
    private FlowLayout flowlayout;
    private LoadMoreWrapper  loadMoreWrapper;
    private List<FinderBean>  listFinder=new ArrayList<>();
    private List<FinderBean>  tougulist=new ArrayList<>();
    private List<FinderBean>  finderlist=new ArrayList<>();
    private View viewheader;
    private TextView  textView;
    private TextView   textViewChange;
    private ImageView  imageview;

    private Animation myAlphaAnimation;//声明Animation类的对象
    private  int touguCounter=0;
    private   View  mErrorView;
    private   LinearLayout webParentView;
    private boolean isErr = true;
    private String  tougulastid;
    private String  finderlastid;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    finderAdapter.loadMoreEnd();
                    break;
                case 1:
                    finderAdapter.addData(tougulist);
                    break;

            }
            super.handleMessage(msg);
        }
    };

    //绑定数据
    public static TGFragment newInstance(String channel) {

        Bundle args = new Bundle();
        args.putString("channel", channel);
        TGFragment fragment = new TGFragment();
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
        viewheader = LayoutInflater.from(getActivity()).inflate(R.layout.item_tougu_header, null);

        recycleview = (RecyclerView) view.findViewById(R.id.recycler_find);
        recycleviewheader = (RecyclerView) viewheader.findViewById(R.id.rv_tougu_header);
        textView = (TextView) viewheader.findViewById(R.id.tv_tougu_header);
        textViewChange=(TextView)viewheader.findViewById(R.id.tv_tg_change);
        imageview=(ImageView)viewheader.findViewById(R.id.iv_tg_change);
        myAlphaAnimation= AnimationUtils.loadAnimation(getActivity(), R.anim.loading);
        myAlphaAnimation.setInterpolator(new LinearInterpolator());


        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(getActivity());
        recycleview.setLayoutManager(manager);
        recycleviewheader.setLayoutManager(manager1);
        recycleviewheader.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        recycleview.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        recycleviewheader.setFocusableInTouchMode(false);
        webParentView=(LinearLayout)recycleview.getParent();
        initErrorPage();
        getString(channel);


        textViewChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(Network.isConnected()){


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gettjData();

                    }
                },1000);
                if(myAlphaAnimation!=null){
                    imageview.startAnimation(myAlphaAnimation);
                }
                }else {
                   ToastUtils.showToast("暂无网络");
               }
            }
        });
        return view;
    }

    private void gettjData() {
        Map<String,String> params=new HashMap<>();
        params.put("act","touguchange");
        RetrofitUtil.getretrofit().create(TouGuBiz.class).getData(params).enqueue(new Callback<TouGu>() {
            @Override
            public void onResponse(Call<TouGu> call, Response<TouGu> response) {

                finderAdapterHeaer.setNewData(response.body().getRecommend());
                        imageview.clearAnimation();
            }

            @Override
            public void onFailure(Call<TouGu> call, Throwable t) {

            }
        });

    }

    private void getString(String channel) {
        switch (channel) {
            case "全部":
                act = "tougu";
                getTouGu();
                break;
            case "荐股":
                act = "notall";
                id = 1;
                getData();
                break;
            case "工具":
                act = "notall";
                id = 2;
                getData();
                break;
            case "方法":
                act = "notall";
                id = 3;
                getData();
                break;
            case "0":
                CommonUtil.showToast(getActivity(), "暂无数据");
                break;
        }

    }

    /**
     *
     * 投顾的数据
     */
    private void getTouGu() {


        Map<String, String> params = new HashMap<>();
        params.put("act", "tougu");
        RetrofitUtil.getretrofit().create(TouGuBiz.class).getData(params).enqueue(new Callback<TouGu>() {
            @Override
            public void onResponse(Call<TouGu> call, final Response<TouGu> response) {

                if(response.body()==null){

                    showErrorPage();

                }else {
                    Log.d("三找数据", response.body().getOther().toString());
                    finderAdapter = new FinderAdapter(getActivity(), response.body().getOther(), "投顾");
                    recycleview.setAdapter(finderAdapter);
                    finderAdapter.addHeaderView(viewheader);
                    finderAdapterHeaer = new FinderAdapter(getActivity(), response.body().getRecommend(), "投顾");
                    recycleviewheader.setAdapter(finderAdapterHeaer);
                    textView.setText(response.body().getRoll_word().get(0).getContent());
                    tougulastid = response.body().getLastid();


                    finderAdapter.disableLoadMoreIfNotFullPage(recycleview);
                    finderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {


                            recycleview.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(Network.isConnected()){

                                    getTouguMoreData(tougulastid);
                                    }else {
                                        ToastUtils.showToast("暂无网络");
                                        finderAdapter.loadMoreFail();
                                    }

                                }

                            }, 1000);


                        }
                    }, recycleview);
                    finderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            FinderBean entity = (FinderBean) adapter.getData().get(position);
                            CommonUtil.goAactivity(getActivity(), FinderActivity.class, "comid", entity.getId());

                        }
                    });
                    finderAdapterHeaer.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            FinderBean entity = (FinderBean) adapter.getData().get(position);
                            CommonUtil.goAactivity(getActivity(), FinderActivity.class, "comid", entity.getId());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<TouGu> call, Throwable t) {

            }
        });

    }

    private List<FinderBean> getTouguMoreData(String id) {

        Map<String, String> params = new HashMap<>();

        params.put("act", "allmore");
        params.put("lastid",id);
        RetrofitUtil.getretrofit().create(TouGuBiz.class).getData(params).enqueue(new Callback<TouGu>() {
            @Override
            public void onResponse(Call<TouGu> call, Response<TouGu> response) {
                tougulist=response.body().getOther();

                tougulastid=response.body().getLastid();
                Log.d("全部加在更多数据",tougulist.toString()+"!!!"+response.body().getLastid());
                if(tougulist.size()==0){
                   // handler.sendEmptyMessage(0);
                    finderAdapter.loadMoreEnd();
                }else {
                   // handler.sendEmptyMessage(1);
                    finderAdapter.addData(tougulist);
                    finderAdapter.loadMoreComplete();
                }
            }

            @Override
            public void onFailure(Call<TouGu> call, Throwable t) {
                    finderAdapter.loadMoreFail();
            }
        });
      return   tougulist;
    }

    private void getData() {
        Map<String,String> params=new HashMap<>();
        params.put("act","notall");
        params.put("keyword",id+"");

   RetrofitUtil.getretrofit().create(FinderBiz.class).getData(params).enqueue(new Callback<Finder>() {
            @Override
            public void onResponse(Call<Finder> call, final Response<Finder> response) {
                if(response.body()==null){

                    showErrorPage();

                }else {
                    Log.d("三找数据", response.body().getOther().toString());
                    listFinder = response.body().getOther();
                    Log.d("三找数据", listFinder.size() + "!!!!!!");
                    finderAdapter = new FinderAdapter(getActivity(), listFinder, "投顾");
                    recycleview.setAdapter(finderAdapter);
                    finderlastid = response.body().getLastid();
                    finderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            CommonUtil.goAactivity(getActivity(), FinderActivity.class, "comid", listFinder.get(position).getId());

                        }
                    });
                    finderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            recycleview.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                  if(Network.isConnected()){

                                      getmoreData();
                                  }else {
                                      ToastUtils.showToast("暂无网络");
                                      finderAdapter.loadMoreFail();
                                  }
                                }
                            }, 1000);
                        }
                    }, recycleview);
                }

            }
            @Override
            public void onFailure(Call<Finder> call, Throwable t) {

            }
        });

    }

    private void getmoreData() {
        Map<String,String> params=new HashMap<>();
        params.put("act","othermore");
        params.put("keyword",id+"");
        params.put("lastid",finderlastid);
        RetrofitUtil.getretrofit().create(FinderBiz.class).getData(params).enqueue(new Callback<Finder>() {
            @Override
            public void onResponse(Call<Finder> call, Response<Finder> response) {
                finderlist=response.body().getOther();

                finderlastid=response.body().getLastid();
                if(finderlist.size()==0){
                    // handler.sendEmptyMessage(0);
                    finderAdapter.loadMoreEnd();
                }else {
                    // handler.sendEmptyMessage(1);
                    finderAdapter.addData(finderlist);
                    finderAdapter.loadMoreComplete();
                }
            }

            @Override
            public void onFailure(Call<Finder> call, Throwable t) {

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
