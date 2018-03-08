package com.yiyuaninfo.Activity.MyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Activity.MediaArtDetailActivity;
import com.yiyuaninfo.Activity.NewsDetailActivity;
import com.yiyuaninfo.Activity.XMDetailActivity;
import com.yiyuaninfo.Interface.CollectionBiz;
import com.yiyuaninfo.Interface.IsCollectionBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.CollectionAdapter;
import com.yiyuaninfo.entity.Collection;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
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

public class MyFavoriteActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<Collection> list = new ArrayList<>();
    private CollectionAdapter adapter;
    private User.UserinfoBean userinfoBean;
    private String lastid;
    private LinearLayout  linearLayout;
    private TextView  textView;

    @Override
    protected int getContentView() {
        return R.layout.activity_myfavoritet;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("我的收藏");
        linearLayout=(LinearLayout)findViewById(R.id.ll_emptyview);
        textView=(TextView)findViewById(R.id.tv_nodata);
        recyclerView = (RecyclerView) findViewById(R.id.rv_collection);
        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        intView();


    }

    private void intView() {

        if(SPUtil.isLogin(this)){
            userinfoBean = SPUtil.getUser(this);

            getData();
        }else {
            recyclerView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            textView.setText("未登录，点击可登录");
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonUtil.goAactivity(MyFavoriteActivity.this,LogInActivity.class);

                }
            });

        }




    }

    private void getData() {

        Map<String, String> params = new HashMap<>();
        params.put("act", "query");
        params.put("userid", userinfoBean.getUserid());
        Log.d("用户的userid", userinfoBean.getUserid());
        RetrofitUtil.getretrofit().create(CollectionBiz.class).getData(params).enqueue(new Callback<Collection>() {
            @Override
            public void onResponse(final Call<Collection> call, Response<Collection> response) {
                if(response.body().getUsercollect().size()!=0){

                 recyclerView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                lastid = response.body().getLastid();
                adapter = new CollectionAdapter(R.layout.item_collection, response.body().getUsercollect());
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
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                        Collection.UsercollectBean entity = (Collection.UsercollectBean) adapter.getData().get(position);

                        switch (view.getId()) {
                            case R.id.btnDelete:


                                Map<String, String> params = new HashMap<>();
                                params.put("act", "del");
                                params.put("colid", entity.getId());
                                params.put("userid", userinfoBean.getUserid());
                                RetrofitUtil.getretrofit().create(IsCollectionBiz.class).getData(params).enqueue(new Callback<State>() {
                                    @Override
                                    public void onResponse(Call<State> call, Response<State> response) {
                                        Log.d("点击收藏返回的状态1", response.body().getState());
                                        if (response.body().getState().equals("1")) {
                                            ToastUtils.showToast("删除成功");
                                            adapter.remove(position);

                                        } else {
                                            ToastUtils.showToast("失败!");
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<State> call, Throwable t) {

                                    }
                                });
                                break;

                            case R.id.ll_collection:


                                if (entity.getCol_type().equals("1")) {

                                    /**
                                     * id : 10
                                     * col_uid : 1499064765j6qavy
                                     * col_img : http://yyapp.1yuaninfo.com/app/houtai/uploads/image/20170919/1505802437.jpg
                                     * col_title :  美军摆放军靴 纪念两次撞船死亡士兵
                                     * col_id : 18546
                                     * col_type : 1
                                     * col_time : 2017-09-23 21:34:49
                                     * col_state : 0
                                     * col_deltime : null
                                     */



                                        /**
                                         * id : 17673
                                         * title : 金砖国家领导人第九次会晤举行
                                         * source : 人民网
                                         * description : 图集摘要
                                         * picurl :
                                         * picarr : [{"img":"uploads/image/20170906/1504661674.jpg","description":"图片1的描述"},{"img":"uploads/image/20170906/1504669411.jpg","description":"图片2的描述"},{"img":"uploads/image/20170906/1504666992.jpg","description":"图片3的描述"},{"img":"uploads/image/20170906/1504664929.jpg","description":"图片4的描述"}]
                                         * posttime : 2017-09-06 09:26:58
                                         * keywords : 图集
                                         * picstate : 4
                                         */
                                    NewsEntity.HangqingBean entity1 = new NewsEntity.HangqingBean(
                                            entity.getCol_id(),
                                            entity.getCol_title(),
                                            "",
                                            "",
                                            entity.getCol_img(),
                                            null,
                                            "",
                                            "",
                                            ""

                                    );
                                    Intent intent = new Intent(MyFavoriteActivity.this, NewsDetailActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("entity", entity1);
                                    intent.putExtras(bundle);
                                    startActivity(intent);


//                                    CommonUtil.goAactivity(MyFavoriteActivity.this, NewsDetailActivity.class, "newsurl",
//                                            Constants.NEWSURL.concat("id=" + entity.getCol_id() + "&userid="),
//                                            "newsid", entity.getCol_id(),
//                                            "newstitle", entity.getCol_title());


                                } else if (entity.getCol_type().equals("3")) {
                                    CommonUtil.goAactivity(MyFavoriteActivity.this, MediaArtDetailActivity.class, "newsurl",
                                            Constants.NIU.concat("id=" + entity.getCol_id()),
                                            "newsid", entity.getCol_id(),
                                            "newstitle", entity.getCol_title(),
                                            "description","",
                                            "image",entity.getCol_img()



                                    );


                                } else if (entity.getCol_type().equals("5")) {

                                    CommonUtil.goAactivity(MyFavoriteActivity.this, XMDetailActivity.class, "xmurl", Constants.XMDETAIL
                                            + entity.getCol_id(), "id", entity.getCol_id());


                                }


                                break;
                        }


                    }
                });
            }else {
                    recyclerView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    textView.setText("暂无收藏");

                }
            }

            @Override
            public void onFailure(Call<Collection> call, Throwable t) {

            }
        });
    }

    private void getMoreData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "query");
        params.put("userid", userinfoBean.getUserid());
        params.put("lastid", lastid);
        Log.d("用户的userid", userinfoBean.getUserid());
        RetrofitUtil.getretrofit().create(CollectionBiz.class).getData(params).enqueue(new Callback<Collection>() {
            @Override
            public void onResponse(Call<Collection> call, Response<Collection> response) {
                lastid = response.body().getLastid();
                if (response.body().getUsercollect().size() != 0) {
                    adapter.addData(response.body().getUsercollect());
                    adapter.loadMoreComplete();
                } else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<Collection> call, Throwable t) {
                adapter.loadMoreFail();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        intView();
    }
}
