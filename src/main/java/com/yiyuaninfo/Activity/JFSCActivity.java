package com.yiyuaninfo.Activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Interface.MallEntityBiz;
import com.yiyuaninfo.Interface.UserInfoBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.MallSectionAdapter;
import com.yiyuaninfo.entity.MallEntity;
import com.yiyuaninfo.entity.MallSection;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.EmptyUtil;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.DividerGridItemDecoration;
import com.yiyuaninfo.view.GridDivider;
import com.yiyuaninfo.view.MyItemDecoration;

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

public class JFSCActivity extends BaseActivity  implements View.OnClickListener{
    private RecyclerView  recyclerView;
    private List<MallSection>  list=new ArrayList<>();
    private List<MallSection>  listmore=new ArrayList<>();
    private MallSectionAdapter  adapter;
    private TextView   textViewhuoqu,dhjl,szmx;
    private TextView   tvBuy,jifen;
    private CommonPopupWindow popupWindow;
    private ImageView  imageview;
    private String lastid;

    private EmptyUtil  emptyUtil;
    @Override
    protected int getContentView() {
        return R.layout.activity_jfsc;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isshowToolBar(false);
        textViewhuoqu=(TextView)findViewById(R.id.tv_jfsc_huiqu);
        jifen=(TextView)findViewById(R.id.tv_jfsc_jifen);
        dhjl=(TextView)findViewById(R.id.tv_jfsc_dh);
        szmx=(TextView)findViewById(R.id.tv_jfsc_szmx);
        tvBuy=(TextView)findViewById(R.id.tv_jfsc_buy) ;
        imageview=(ImageView)findViewById(R.id.image_back_jfsc);
        imageview.setOnClickListener(this);
        tvBuy.setOnClickListener(this);
        textViewhuoqu.setOnClickListener(this);
        dhjl.setOnClickListener(this);
        szmx.setOnClickListener(this);

        recyclerView=(RecyclerView)findViewById(R.id.rv_jfsc);
        GridLayoutManager  gridLayoutManager=new GridLayoutManager(this,2);
       // recyclerView.addItemDecoration(new GridDivider(this,30,this.getResources().getColor(R.color.home_divider)));
        recyclerView.setLayoutManager(gridLayoutManager);

        emptyUtil=new EmptyUtil(this,recyclerView);
        emptyUtil.initErrorPage();
        getUserInfo();
        setData();
        getDate();


    }

    private void getUserInfo() {
        if(SPUtil.isLogin(this)){
            Map<String,String> map=new HashMap<>();
            map.put("userid",SPUtil.getUser(this).getUserid());
            RetrofitUtil.getretrofit().create(UserInfoBiz.class).getUser(map).enqueue(new Callback<User.UserinfoBean>() {
                @Override
                public void onResponse(Call<User.UserinfoBean> call, Response<User.UserinfoBean> response) {
                    Log.d("请求用户的数据",response.body().toString());
                    if(SPUtil.setUser(JFSCActivity.this,response.body())){

                        setData();
                    }
                }

                @Override
                public void onFailure(Call<User.UserinfoBean> call, Throwable t) {

                }
            });
        }

    }

    private void setData() {
        jifen.setText(SPUtil.getUser(this).getIntegral());
    }

    private void getDate() {

         Map<String,String> params=new HashMap<>();
        params.put("act","goods");
        RetrofitUtil.getretrofit().create(MallEntityBiz.class).getData(params).enqueue(new Callback<MallEntity>() {
            @Override
            public void onResponse(Call<MallEntity> call, Response<MallEntity> response) {

                if(response.body()==null){
                  emptyUtil.showErrorPage();
                }else {



                lastid=response.body().getLastid();
                list.add(new MallSection(true,"热门兑换"));
                for (int i = 0; i <response.body().getHot_arr().size() ; i++) {
                   list.add(new MallSection(response.body().getHot_arr().get(i)));
                }
                list.add(new MallSection(true,"为您推荐"));
                for (int i = 0; i < response.body().getTj_arr().size(); i++) {
                    list.add(new MallSection(response.body().getTj_arr().get(i)));
                }
                adapter=new MallSectionAdapter(JFSCActivity.this,R.layout.item_mall,R.layout.item_mall_header,list);
                recyclerView.setAdapter(adapter);
                adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(Network.isConnected()){

                                    getMoreData();
                                }else {
                                    adapter.loadMoreFail();
                                    ToastUtils.showToast("暂无网络");
                                }

                            }
                        },1000);
                    }
                },recyclerView);
                }

            }

            @Override
            public void onFailure(Call<MallEntity> call, Throwable t) {

            }
        });
    }

    private void getMoreData() {
        Map<String,String> params=new HashMap<>();
        params.put("act","moretj");
        params.put("lastid",lastid);
        Log.d("商品最后的id",lastid);
        RetrofitUtil.getretrofit().create(MallEntityBiz.class).getData(params).enqueue(new Callback<MallEntity>() {
            @Override
            public void onResponse(Call<MallEntity> call, Response<MallEntity> response) {

                if(response.body().getTj_arr().size()!=0){
                    lastid=response.body().getLastid();
                    for (int i = 0; i <response.body().getTj_arr().size() ; i++) {

                        listmore.add(new MallSection(response.body().getTj_arr().get(i)));
                    }
                    adapter.addData(listmore);
                    adapter.loadMoreComplete();
                }else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<MallEntity> call, Throwable t) {
                     adapter.loadMoreFail();
            }
        });


    }

    @Override
    public void onClick(View v) {

        if(Network.isConnected()){


        switch (v.getId()){
            case R.id.tv_jfsc_huiqu:
                CommonUtil.goAactivity(this,WebViewActivity.class,"msgurl", Constants.JIFEN);
                break;
            case R.id.tv_jfsc_dh:
                CommonUtil.goAactivity(this,DHJLActivity.class);
                break;

            case R.id.tv_jfsc_szmx:
                CommonUtil.goAactivity(this,SZMXActivity.class);
                break;
            case R.id.tv_jfsc_buy:

                CommonUtil.goAactivity(this,BuyJiFenActivity.class);

                break;
            case R.id.image_back_jfsc:
                finish();
                break;
        }
        }else {
            ToastUtils.showToast("暂无网络");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfo();
    }
}
