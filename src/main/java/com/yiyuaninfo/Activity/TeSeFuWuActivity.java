package com.yiyuaninfo.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Interface.TeSeOrderBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.TSFWAdapter;
import com.yiyuaninfo.entity.TeSe;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaocongcong on 2018/1/31.
 */

public class TeSeFuWuActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private String orderid;
    private TSFWAdapter   adapter;
    private LinearLayout   linearLayout;
    @Override
    protected int getContentView() {
        return R.layout.activity_tsfw;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("服务记录");
        orderid = getIntent().getStringExtra("orderid");
        recyclerView = (RecyclerView) findViewById(R.id.rv_tsfw);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        linearLayout=(LinearLayout)findViewById(R.id.ll_emptyview) ;

        getData();
    }

    private void getData() {


        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("orderid", orderid);
        params.put("userid", SPUtil.getUser(this).getUserid());
       // params.put("userid", "1507619127fb9qai");

        RetrofitUtil.getretrofit1("http://yyapp.1yuaninfo.com/app/houtai/admin/").create(TeSeOrderBiz.class).getData(params).enqueue(new Callback<TeSe>() {
            @Override
            public void onResponse(Call<TeSe> call, Response<TeSe> response) {
                Log.d("特色服务记录",response.body().getUsarr().toString());
                if(response.body().getUsarr().size()!=0){
                    recyclerView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                    adapter=new TSFWAdapter(R.layout.item_tsfw,response.body().getUsarr());
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            TeSe.UsarrBean  usarrBean=(TeSe.UsarrBean)adapter.getData().get(position);
                            if(usarrBean.getYanbao()==null){

                               // CommonUtil.goAactivity(TeSeFuWuActivity.this,PDFActivity.class);


                                ToastUtils.showToast("暂无研报，请稍后再试");

                            }else {
                                CommonUtil.goAactivity(TeSeFuWuActivity.this,PDFActivity.class);

                                //CommonUtil.goAactivity(TeSeFuWuActivity.this,YanBaoActivity.class,"yanbao",usarrBean.getYanbao());
                            }
                        }
                    });


                }else {
                    recyclerView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }




            }

            @Override
            public void onFailure(Call<TeSe> call, Throwable t) {

            }
        });

    }


}
