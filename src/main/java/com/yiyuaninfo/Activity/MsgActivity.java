package com.yiyuaninfo.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Interface.MsgBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.MsgAdapter;
import com.yiyuaninfo.entity.Msg;
import com.yiyuaninfo.entity.MsgSection;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.EmptyUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.view.MyDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MsgActivity extends BaseActivity {
    private static final String TAG = "MsgActivity";
    private RecyclerView recyclerView;
    private List<MsgSection>  list=new ArrayList<>();
    private MsgAdapter  adapter;
    private EmptyUtil  emptyUtil;
    @Override
    protected int getContentView() {
        return R.layout.activity_msg;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        setToolBarTitle("消息");
        recyclerView = (RecyclerView) findViewById(R.id.rcy_msg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this,MyDecoration.VERTICAL_LIST));
        emptyUtil=new EmptyUtil(this,recyclerView);
        emptyUtil.initErrorPage();
        //获取数据
   RetrofitUtil.getretrofit().create(MsgBiz.class).getMsg("list").enqueue(new Callback<List<Msg>>() {
       @Override
       public void onResponse(Call<List<Msg>> call, Response<List<Msg>> response) {
           if(response.body()==null){
               emptyUtil.showErrorPage();
           }else {


               Log.d(TAG, "123");
               for (int i = 0; i < response.body().size(); i++) {
                   list.add(new MsgSection(true, response.body().get(i).getDate()));
                   for (int j = 0; j < response.body().get(i).getInfo().size(); j++) {
                       list.add(new MsgSection(response.body().get(i).getInfo().get(j)));
                   }
               }
               adapter = new MsgAdapter(R.layout.item_flash_content, R.layout.item_flash_header, list);
               recyclerView.setAdapter(adapter);
               adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                   @Override
                   public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                       MsgSection entity = (MsgSection) adapter.getData().get(position);
                       CommonUtil.goAactivity(MsgActivity.this, MsgInfoActivity.class, "msgurl",
                               Constants.MSGDETAIL + entity.t.getId());
                   }
               });
           }
       }

       @Override
       public void onFailure(Call<List<Msg>> call, Throwable t) {

       }
   });

    }


}
