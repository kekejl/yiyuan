package com.yiyuaninfo.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiyuaninfo.R;
import com.yiyuaninfo.view.WrapContentHeightViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentOrder extends Fragment {
    RecyclerView rvData;
    OrderAdapter adapter;
    WrapContentHeightViewPager vp;
    int index=0;
    //ProgressDialog progressDialog;
    List<String> list;
    int page=1;
    boolean flag=false;

    public void setViewPage(WrapContentHeightViewPager vp,int index){
        this.vp=vp;
        this.index=index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_order, container, false);
        vp.setObjectForPosition(view,index);
        rvData=(RecyclerView)view.findViewById(R.id.rvData);
        getData(1);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // progressDialog=new ProgressDialog(getContext());
       // progressDialog.setMessage("加载中...");
    }

    public void getData( int pages){
        this.page=pages;
//        if(progressDialog!=null){
//            progressDialog.show();
//        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {   //模拟获取网络数据
                    Thread.sleep(1500);
                    int dataSize= new Random().nextInt(10);
                    if(page==1) {
                        list = new ArrayList<>();
                    }
                    for (int i=0;i<50;i++){
                        list.add("data"+(list.size()+i));
                    }
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                if(page==1){
                    adapter=new OrderAdapter(getContext(),list);
                    rvData.setLayoutManager(new LinearLayoutManager(getContext()));
                    rvData.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }
//                if(progressDialog!=null){
//                    progressDialog.dismiss();
//                }
//                MainActivity.mainActivity.refresh.finishRefresh();
//                MainActivity.mainActivity.refresh.finishRefreshing();
//                MainActivity.mainActivity.refresh.finishRefreshLoadMore();
            }
        }
    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
//            getData(page=1);
//        }
    }
}
