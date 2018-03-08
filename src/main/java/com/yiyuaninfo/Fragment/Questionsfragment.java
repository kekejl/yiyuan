package com.yiyuaninfo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Activity.MediaActivity;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Activity.QuestionActivity;
import com.yiyuaninfo.Interface.QuestionBiz;
import com.yiyuaninfo.Listener.OnChannelListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.QuestionAdapter;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.entity.WenDa;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.EmptyUtil;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/23.
 */

public class Questionsfragment extends Fragment {
    private RecyclerView  recyclerView;
    private QuestionAdapter  adapter;
    private String lastid;
    private LinearLayout  linearLayout;
    private TextView  textView;
    private String   userid;
    private EmptyUtil  emptyUtil;
    public static Questionsfragment newInstance(String title) {


        Questionsfragment f = new Questionsfragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_questions, null);
        linearLayout=(LinearLayout)view.findViewById(R.id.ll_emptyview);
        textView=(TextView)view.findViewById(R.id.tv_nodata);
         recyclerView=(RecyclerView)view.findViewById(R.id.rv_question);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        emptyUtil=new EmptyUtil(getActivity(),recyclerView);
        emptyUtil.initErrorPage();
        getData();

        return view;
    }

    private void getData() {
        if (SPUtil.isLogin(getActivity())){
            recyclerView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
            userid=SPUtil.getUser(getActivity()).getUserid();
        Map<String,String> params=new HashMap<>();
        params.put("act","wendawz");
        params.put("userid", userid);
        RetrofitUtil.getretrofit().create(QuestionBiz.class).getData(params).enqueue(new Callback<WenDa>() {
            @Override
            public void onResponse(Call<WenDa> call, Response<WenDa> response) {
                 if (response.body()==null){
                  emptyUtil.showErrorPage();
                 }else {

                if(response.body().getWd_arr().size()!=0) {


                    adapter = new QuestionAdapter(response.body().getWd_arr());
                    recyclerView.setAdapter(adapter);
                    lastid = response.body().getLastid();
                    adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            recyclerView.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if (Network.isConnected()){

                                        getMoreData();
                                    }else {
                                        ToastUtils.showToast("暂无数据");
                                        adapter.loadMoreFail();
                                    }
                                }
                            }, 1000);
                        }
                    }, recyclerView);

                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            if (Network.isConnected()) {

                                WenDa.WdArrBean entity = (WenDa.WdArrBean) adapter.getData().get(position);
                                Intent intent = new Intent(getActivity(), QuestionActivity.class);

                                Bundle bundle = new Bundle();
                                bundle.putSerializable("entity", entity);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }else {
                                ToastUtils.showToast("暂无数据");
                            }

                        }
                    });
                }else {
                    linearLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    textView.setText("暂无数据，快去提问吧");
                }
                 }

            }

            @Override
            public void onFailure(Call<WenDa> call, Throwable t) {

            }
        });


        }else {
            linearLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            textView.setText("未登录，点击可登录");
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonUtil.goAactivity(getActivity(),LogInActivity.class);

                }
            });
        }

    }

    private void getMoreData() {

        Map<String,String> params=new HashMap<>();
        params.put("act","wendawz");
        params.put("lastid",lastid);
        params.put("userid", userid);

        RetrofitUtil.getretrofit().create(QuestionBiz.class).getData(params).enqueue(new Callback<WenDa>() {
            @Override
            public void onResponse(Call<WenDa> call, Response<WenDa> response) {
                if(response.body().getWd_arr().size()!=0){
                    lastid=response.body().getLastid();
                    adapter.addData(response.body().getWd_arr());
                    adapter.loadMoreComplete();
                }else {
                    adapter.loadMoreEnd();
                }

            }

            @Override
            public void onFailure(Call<WenDa> call, Throwable t) {
                  adapter.loadMoreFail();
            }
        });





    }


    @Override
    public void onResume() {
        super.onResume();


            getData();
    }
}
