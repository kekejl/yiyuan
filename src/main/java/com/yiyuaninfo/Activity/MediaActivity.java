package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Interface.AddNiuBiz;
import com.yiyuaninfo.Interface.DelNiuBiz;
import com.yiyuaninfo.Interface.GeniusBiz;
import com.yiyuaninfo.Interface.IsGuanZhuBiz;
import com.yiyuaninfo.Interface.NiuArtBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.CircleImageView;
import com.yiyuaninfo.adapter.GeniusAdapter;
import com.yiyuaninfo.adapter.NiuArtAdapter;
import com.yiyuaninfo.entity.Genius;
import com.yiyuaninfo.entity.Info;
import com.yiyuaninfo.entity.IsGuanZhu;
import com.yiyuaninfo.entity.NiuArt;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.EmptyUtil;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
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

public class MediaActivity extends BaseActivity  implements View.OnClickListener{
    private RecyclerView recyclerView;
    private Genius.NiuArrBean  entity;
    private ImageView   ivBack;
    private CircleImageView  circleImageView;
    private TextView   tvGuanzhu;
    private boolean  isGuanZhu=false;
    private User.UserinfoBean  user;
    private NiuArtAdapter adapter;
    private String lastid;
    private EmptyUtil  emptyUtil;
    @Override
    protected int getContentView() {
        return R.layout.activity_media;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
           isshowToolBar(false);
        Intent intent=getIntent();

        entity=(Genius.NiuArrBean)intent.getSerializableExtra("entity");
        ivBack=(ImageView)findViewById(R.id.iv_media_back);
        circleImageView=(CircleImageView)findViewById(R.id.civ_media);
        tvGuanzhu=(TextView)findViewById(R.id.tv_media_guanzhu);
        ivBack.setOnClickListener(this);
        tvGuanzhu.setOnClickListener(this);
        ImageLoaderUtils.displayImage(entity.getNiu_img(),circleImageView);
        recyclerView=(RecyclerView)findViewById(R.id.rv_media1);
        recyclerView.addItemDecoration(new MyDecoration(this,MyDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        emptyUtil=new EmptyUtil(MediaActivity.this,recyclerView);
        emptyUtil.initErrorPage();
        if(SPUtil.isLogin(this)){
            user=SPUtil.getUser(this);
        isGuanZhu();
        }
        getData();

    }

    private void getData() {
        Map<String,String> params=new HashMap<>();
        params.put("act","niuarticle");
        params.put("niuid",entity.getId());
       RetrofitUtil.getretrofit().create(NiuArtBiz.class).getData(params).enqueue(new Callback<NiuArt>() {
           @Override
           public void onResponse(Call<NiuArt> call,  final  Response<NiuArt>  response) {

               if(response.body()==null){
                  emptyUtil.showErrorPage();
               }else {

                 adapter=new NiuArtAdapter(response.body().getNiu_arr());
               recyclerView.setAdapter(adapter);
               lastid=response.body().getLastid();
               adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                   @Override
                   public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                       CommonUtil.goAactivity(MediaActivity.this, MediaArtDetailActivity.class, "newsurl",
                               Constants.NIU.concat("id=" + response.body().getNiu_arr().get(position).getId()),
                               "newsid",response.body().getNiu_arr().get(position).getId(),
                               "newstitle",response.body().getNiu_arr().get(position).getTitle(),
                               "description",response.body().getNiu_arr().get(position).getDescription(),
                               "image",response.body().getNiu_arr().get(position).getPicurl()


                       );



                   }
               });

               adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                   @Override
                   public void onLoadMoreRequested() {
                          if(Network.isConnected()){

                              getMoreData();
                          }else {
                              ToastUtils.showToast("暂无数据");
                          }
                   }
               });
               }

           }

           @Override
           public void onFailure(Call<NiuArt> call, Throwable t) {

           }
       });



    }

    private void getMoreData() {

        Map<String,String> params=new HashMap<>();
        params.put("act","niuarticle");
        params.put("niuid",entity.getId());
        params.put("lastid",lastid);
        RetrofitUtil.getretrofit().create(NiuArtBiz.class).getData(params).enqueue(new Callback<NiuArt>() {
            @Override
            public void onResponse(Call<NiuArt> call,  final  Response<NiuArt>  response) {
                   if(response.body().getNiu_arr().size()!=0){
                       adapter.addData(response.body().getNiu_arr());
                       adapter.loadMoreComplete();
                   }else {
                       adapter.loadMoreEnd();
                   }
            }

            @Override
            public void onFailure(Call<NiuArt> call, Throwable t) {
                    adapter.loadMoreFail();
            }
        });




    }

    private void isGuanZhu() {
        Map<String,String>  params=new HashMap<>();
        params.put("act","quebyniuid");
        params.put("userid",user.getUserid());
        params.put("niu_id",entity.getId());
        RetrofitUtil.getretrofit().create(IsGuanZhuBiz.class).getData(params).enqueue(new Callback<IsGuanZhu>() {
            @Override
            public void onResponse(Call<IsGuanZhu> call, Response<IsGuanZhu> response) {

                if(Network.isConnected()){
                    Log.d("是否关注",response.body().getInfo()+"");
                    if(response.body().getInfo().equals("1")){
                        isGuanZhu=true;
                        tvGuanzhu.setText("已关注");
                    }
                }else {
                    ToastUtils.showToast("暂无网络");
                    tvGuanzhu.setText("关注");

                }


            }

            @Override
            public void onFailure(Call<IsGuanZhu> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.iv_media_back:
                     finish();
               break;
             case R.id.tv_media_guanzhu:
                 if(Network.isConnected()) {


                     if (SPUtil.isLogin(this)) {


                         if (isGuanZhu) {
                             delete();
                         } else {
                             add();
                         }
                     } else {
                         CommonUtil.goAactivity(MediaActivity.this, LogInActivity.class);

                     }
                 }else {
                     ToastUtils.showToast("暂无网络");
                 }
           break;
       }
    }

    private void add() {
        Map<String,String>  params=new HashMap<>();
        params.put("act","add");
        params.put("userid",user.getUserid());
        params.put("niu_id",entity.getId());
        RetrofitUtil.getretrofit().create(AddNiuBiz.class).getData(params).enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Log.d("是否关注",response.body().getInfo()+"");

                if(response.body().getInfo()==1){
                    isGuanZhu=true;
                    tvGuanzhu.setText("已关注");
                    ToastUtils.showToast("关注成功");
                }
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {

            }
        });



    }

    private void delete() {

        Map<String,String>  params=new HashMap<>();
        params.put("act","delbyniuid");
        params.put("userid",user.getUserid());
        params.put("niu_id",entity.getId());
        RetrofitUtil.getretrofit().create(DelNiuBiz.class).getData(params).enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                Log.d("是否关注",response.body().getState()+"");

                if(response.body().getState().equals("1")){
                    isGuanZhu=false;
                    tvGuanzhu.setText("关注");
                    ToastUtils.showToast("已取消关注");
                }
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });

    }
}
