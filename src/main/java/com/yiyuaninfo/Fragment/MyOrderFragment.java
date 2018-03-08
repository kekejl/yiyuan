package com.yiyuaninfo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Activity.CommentActivity;
import com.yiyuaninfo.Activity.CommentDetailActivity;
import com.yiyuaninfo.Activity.MainActivity;
import com.yiyuaninfo.Activity.MyActivity.LogInActivity;
import com.yiyuaninfo.Activity.TeSeFuWuActivity;
import com.yiyuaninfo.Activity.TuiDanActivity;
import com.yiyuaninfo.Activity.YanBaoActivity;
import com.yiyuaninfo.Interface.BackOrderBiz;
import com.yiyuaninfo.Interface.TeSeBiz;
import com.yiyuaninfo.Interface.UserOrderBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.UserOrderAdapter;
import com.yiyuaninfo.entity.FirstCommentBean;
import com.yiyuaninfo.entity.UserOrder;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by gaocongcong on 2017/7/19.
 */

public class MyOrderFragment extends Fragment implements CommonPopupWindow.ViewInterface {
    private RecyclerView recyclerView;
    private String channel;
    private UserOrderAdapter adapter;
    private String lastid, act;
    private LinearLayout linearLayout;
    private TextView textView;
    private  int p;
    private String TYPE = "1";
    private String yanbao = "http://yyapp.1yuaninfo.com/app/houtai/admin/pdfReport.php?orderid=";
   // private String yanbao = "http://www.baidu.com";
    private String orderid;
    private CommonPopupWindow popupWindowtishi;
    UserOrder.UserOrderBean   userOrder,userOrderBean;
    private List<UserOrder.UserOrderBean> list;
     private  int  index;
    //绑定数据
    public static MyOrderFragment newInstance(String channel) {

        Bundle args = new Bundle();
        args.putString("channel", channel);
        MyOrderFragment fragment = new MyOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //获取数据
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle args = getArguments();
        channel = args != null ? args.getString("channel", "0") : "0";
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_myorder, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_myorder);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        textView = (TextView) view.findViewById(R.id.tv_nodata);
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_emptyview);


        intView();


        return view;
    }

    private void intView() {
        if (SPUtil.isLogin(getActivity())) {
            getData();
        } else {
            recyclerView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            textView.setText("未登录，点击可登录");
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonUtil.goAactivity(getActivity(), LogInActivity.class);
                }
            });

        }


    }

    private void getData() {
        Map<String, String> params = new HashMap<>();
        switch (channel) {
            case "已完成订单":
                params.put("act", "orderover");
                params.put("userid", SPUtil.getUser(getActivity()).getUserid());
                act = "orderover";
                TYPE = "1";
                break;

            case "未完成订单":
                params.put("act", "orderunover");
                params.put("userid", SPUtil.getUser(getActivity()).getUserid());
                act = "orderunover";
                TYPE = "0";
                break;
        }
        RetrofitUtil.getretrofit().create(UserOrderBiz.class).getData(params).enqueue(new Callback<UserOrder>() {
            @Override
            public void onResponse(final Call<UserOrder> call, Response<UserOrder> response) {
                Log.d("订单数据", response.body().getUser_order().toString());

                if (response.body().getUser_order().size() != 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                    lastid = response.body().getLastid();
                    list=response.body().getUser_order();
                    adapter = new UserOrderAdapter(R.layout.item_myorder,list , TYPE,getActivity());
                    recyclerView.setAdapter(adapter);


                    adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            recyclerView.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            }, 1);
                            getMoreData();

                        }
                    }, recyclerView);


                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                            userOrder = (UserOrder.UserOrderBean) adapter.getData().get(position);
                            orderid = userOrder.getId();
                            Log.d("订单id",orderid);

                            CommonUtil.goAactivity(getActivity(), TeSeFuWuActivity.class,"orderid",orderid);

                        }
                    });


                    adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                           userOrder = (UserOrder.UserOrderBean) adapter.getData().get(position);
                            orderid = userOrder.getId();
                            p=position;
                            Log.d("订单id",orderid);
                            index=position;
                            switch (view.getId()) {
                                case R.id.tv_order_tuidan:
                                    Intent intent = new Intent(getActivity(), TuiDanActivity.class);

                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("entity", userOrder);
                                   // Log.d("返回的数据",position+"+++"+comment.toString());

                                    intent.putExtras(bundle);
                                    startActivityForResult(intent,1);



                                  //  CommonUtil.goAactivity(getActivity(), TuiDanActivity.class,"orderid",orderid,"type",userOrder.getPaystatus());

                                    //showpopwindowtishi();

                                    break;
                                case R.id.tv_order_yanbao:
                                    CommonUtil.goAactivity(getActivity(), YanBaoActivity.class, "yanbao", yanbao+userOrder.getId() );
                                    break;
                            }
                        }
                    });




                } else {

                    recyclerView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    textView.setText("暂无订单");
                }


            }

            @Override
            public void onFailure(Call<UserOrder> call, Throwable t) {

            }
        });


    }

    private void getMoreData() {

        Map<String, String> params = new HashMap<>();

        params.put("act", act);
        params.put("userid", SPUtil.getUser(getActivity()).getUserid());
        params.put("lastid", lastid);
        RetrofitUtil.getretrofit().create(UserOrderBiz.class).getData(params).enqueue(new Callback<UserOrder>() {
            @Override
            public void onResponse(Call<UserOrder> call, Response<UserOrder> response) {



                if (response.body().getUser_order().size() != 0) {
                    lastid = response.body().getLastid();
                    adapter.addData(response.body().getUser_order());
                    adapter.loadMoreComplete();
                } else {
                    adapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(Call<UserOrder> call, Throwable t) {
                adapter.loadMoreFail();
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();



    }


//    @Override
//    public void startActivityForResult(Intent intent, int requestCode) {
//        super.startActivityForResult(intent, requestCode);
//
//        if (requestCode==1){
//            userOrderBean=(UserOrder.UserOrderBean) intent.getSerializableExtra("entity");
//                Log.d("返回的数据",userOrderBean.toString());
//                list.remove(p);
//                list.add(p,userOrderBean);
//               // Log.d("返回的数据",comment.toString());
//
//                adapter.notifyDataSetChanged();
//
//        }else {
//            Log.d("返回的数据","12331333");
//
//        }
//
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1){
            if(resultCode==RESULT_OK){
                userOrderBean=(UserOrder.UserOrderBean) data.getSerializableExtra("entity");
                Log.d("返回的数据",userOrderBean.toString());
                list.remove(p);
                list.add(p,userOrderBean);
                Log.d("返回的数据",userOrderBean.toString());

                adapter.notifyDataSetChanged();
            }

        }else {
            Log.d("返回的数据","12331333");

        }

    }


    private void showpopwindowtishi() {

        if (popupWindowtishi != null && popupWindowtishi.isShowing()) return;
        View upView = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_tuidan, null);
        //测量View的宽高
        WindowManager wm = getActivity().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowtishi = new CommonPopupWindow.Builder(getActivity())
                .setView(R.layout.popwindow_tuidan)
                .setWidthAndHeight(width - 300, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        popupWindowtishi.setOutsideTouchable(false);
        popupWindowtishi.setFocusable(false);
        popupWindowtishi.showAtLocation(recyclerView, Gravity.CENTER, 0, 0);



    }


    @Override
    public void getChildView(View view, int layoutResId) {
        TextView textView = (TextView) view.findViewById(R.id.tv_pop_umeng_false);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_pop_umeng_true);
        TextView  tuidan=(TextView)view.findViewById(R.id.tv_tuidan_tishi);
        tuidan.setText("您是否要取消("+userOrder.getPackname()+")这个订单");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowtishi.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // http://yyapp.1yuaninfo.com/app/houtai/admin/backOrder.php?userid=&orderid=
                Map<String, String> params = new HashMap<>();
                params.put("orderid", orderid);
                params.put("userid", SPUtil.getUser(getActivity()).getUserid());
                Log.d("退单地址",orderid+"++"+SPUtil.getUser(getActivity()).getUserid());

//                RetrofitUtil.getretrofit1("http://yyapp.1yuaninfo.com/app/houtai/admin/").create(BackOrderBiz.class).getData(params).enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        ToastUtils.showToast("已提交退单申请，稍后工作人员会与您联系，请保持通讯畅通");
//                        userOrder.setPaystatus("3");
//                        adapter.notifyItemChanged(index);
//                        popupWindowtishi.dismiss();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    }
//
//
//
//                });


            }
        });

    }
}
