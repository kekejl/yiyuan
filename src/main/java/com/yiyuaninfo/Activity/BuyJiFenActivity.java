package com.yiyuaninfo.Activity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yiyuaninfo.Interface.MallEntityBiz;
import com.yiyuaninfo.Interface.PayBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.MallSectionAdapter;
import com.yiyuaninfo.entity.JiFen;
import com.yiyuaninfo.entity.MallEntity;
import com.yiyuaninfo.entity.MallSection;
import com.yiyuaninfo.entity.Pay;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.text.NumberFormat;
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

public class BuyJiFenActivity extends BaseActivity  implements View.OnClickListener {
    private RecyclerView  recyclerView;
    BaseQuickAdapter<JiFen, BaseViewHolder> mAdapter;
    private SparseBooleanArray mBooleanArray;
    private String money="";
    private  Pay.ParamsBean   pay;
    private IWXAPI api;

    private Button  button;
    @Override
    protected int getContentView() {
        return R.layout.activity_buyjifen;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("购买积分");
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);

        recyclerView=(RecyclerView)findViewById(R.id.rv_buyjifen);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        button=(Button)findViewById(R.id.button_buyjifen);
        button.setOnClickListener(this);

        recyclerView.setAdapter(mAdapter = new BaseQuickAdapter<JiFen, BaseViewHolder>(R.layout.item_jifen, getDatas()) {
            @Override
            protected void convert(final BaseViewHolder helper, final JiFen item) {
                helper.setText(R.id.tv_buyjifen_jifen,item.getJifen());
                helper.setText(R.id.tv_buyjifen_money,item.getMoney());
                if (mBooleanArray.get(helper.getAdapterPosition())) {

                   // helper.setBackgroundColor(R.id.ll_buyjifen_bg, Color.parseColor("#d43c33"));
                    helper.setBackgroundRes(R.id.ll_buyjifen_bg,R.drawable.button_money_press_border);
                    helper.setTextColor(R.id.tv_buyjifen_jifen,Color.parseColor("#ffffff"));
                    helper.setTextColor(R.id.tv_buyjifen_money,Color.parseColor("#ffffff"));
                    helper.setTextColor(R.id.tv_rmb,Color.parseColor("#ffffff"));
                } else {
                    helper.setBackgroundRes(R.id.ll_buyjifen_bg,R.drawable.button_money_border);
                    helper.setTextColor(R.id.tv_buyjifen_jifen,Color.parseColor("#000000"));
                    helper.setTextColor(R.id.tv_buyjifen_money,Color.parseColor("#000000"));
                    helper.setTextColor(R.id.tv_rmb,Color.parseColor("#000000"));
                    //helper.setBackgroundColor(R.id.ll_buyjifen_bg, Color.parseColor("#FFFFFF"));
                }

                helper.setOnClickListener(R.id.ll_buyjifen_bg, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setItemChecked(helper.getAdapterPosition());
                        money=item.getMoney();
                        //ToastUtils.showToast(money);
                    }
                });

            }
        });



    }
    public List<JiFen> getDatas() {
        List<JiFen> datas = new ArrayList<>();
        datas.add(new JiFen("1200积分","20"));
        datas.add(new JiFen("1800积分","30"));
        datas.add(new JiFen("3000积分","50"));
        datas.add(new JiFen("4800积分","80"));
        datas.add(new JiFen("6000积分","100"));
        datas.add(new JiFen("12000积分","200"));
        mBooleanArray = new SparseBooleanArray(datas.size());

        return datas;
    }


    private int mLastCheckedPosition = -1;

    /**
     * @param position
     */
    public void setItemChecked(int position) {
        if (mLastCheckedPosition == position)
            return;

        mBooleanArray.put(position, true);

        if (mLastCheckedPosition > -1) {
            mBooleanArray.put(mLastCheckedPosition, false);
            mAdapter.notifyItemChanged(mLastCheckedPosition);
        }

        mAdapter.notifyDataSetChanged();

        mLastCheckedPosition = position;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_buyjifen:
                if(!money.equals("")){
                    Map<String,String>  params=new HashMap<>();
                    params.put("ip",SPUtil.getData(this,"IP","10.150.33.201"));
                    params.put("userid",SPUtil.getUser(this).getUserid());
                    params.put("type","1");
                    params.put("productid","2");
                    params.put("num",money);
                    RetrofitUtil.getretrofit1(Constants.Home5).create(PayBiz.class).getData(params).enqueue(new Callback<Pay>() {
                        @Override
                        public void onResponse(Call<Pay> call, Response<Pay> response) {
                            Log.d("支付信息",response.body().getParams().toString());
                            pay=response.body().getParams();


                            if(pay.getPrepayid()!=null){


                                PayReq request = new PayReq();
                                request.appId = pay.getAppid();
                                request.partnerId = pay.getPartnerid();
                                request.prepayId = pay.getPrepayid();
                                request.packageValue =pay.getPackageX();
                                request.nonceStr =pay.getNoncestr();
                                request.timeStamp = pay.getTimestamp();
                                request.sign =pay.getSign();
                                api.sendReq(request);


                            }else {
                                ToastUtils.showToast("未知错误，请重试！");
                            }
                        }
                        @Override
                        public void onFailure(Call<Pay> call, Throwable t) {

                        }
                    });


                }else {

                    ToastUtils.showToast("请选择购买的积分");

                }
                break;


        }

    }
}
