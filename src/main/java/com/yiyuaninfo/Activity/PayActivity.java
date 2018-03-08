package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yiyuaninfo.Activity.colortrackview.ColorTrackTabLayout;
import com.yiyuaninfo.Fragment.CPFragment;
import com.yiyuaninfo.Interface.PayBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.FindFragmentAdapter;
import com.yiyuaninfo.entity.Pay;
import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.wxapi.WXPayEntryActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class PayActivity extends BaseActivity {
    private Button button;
    private IWXAPI api;
    private PayReq req;
    private  Pay.ParamsBean   pay;
    private String  id,type;
    @Override
    protected int getContentView() {
        return R.layout.activity_pay;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
      setToolBarTitle("支付方式");
        Log.d("支付信息", "121212");
        Intent  intent=getIntent();

        id=intent.getStringExtra("id");
        type=intent.getStringExtra("type");
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        req = new PayReq();

        button=(Button)findViewById(R.id.bt_pay);
        final Map<String,String>  params=new HashMap<>();
        Log.d("支付参数",SPUtil.getData(this,"IP","10.150.33.200")+"\n"+SPUtil.getUser(this).getUserid()
        +"\n"+type+"\n"+id);
        params.put("ip",SPUtil.getData(this,"IP","10.150.33.201"));
        params.put("userid",SPUtil.getUser(this).getUserid());
        params.put("type",type);
        params.put("productid",id);
        params.put("num","1");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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

            }
        });

}

}