package com.yiyuaninfo.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Interface.PayBiz;
import com.yiyuaninfo.Interface.UserInfoBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Pay;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

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
        api.handleIntent(getIntent(), this);

        req = new PayReq();

        button=(Button)findViewById(R.id.bt_pay);
        final Map<String,String> params=new HashMap<>();
        Log.d("支付参数", SPUtil.getData(this,"IP","10.150.33.200")+"\n"+SPUtil.getUser(this).getUserid()
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


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
    @Override
    public void onReq(BaseReq baseReq) {
        Log.d("微信支付结果BaseReq",baseReq.toString());

    }

    @Override
    public void onResp(BaseResp baseResp) {


        Log.d("支付回调", "onPayFinish, errCode = " + baseResp.errCode);

        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setTitle(R.string.app_tip);
//			builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//			builder.show();
            //参考这里 https://www.jianshu.com/p/c97639279d2e
            if (baseResp.errCode == 0){
                Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();

                getUserInfo();

                finish();

            }else {
                Toast.makeText(this, "支付失败，请重试", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void getUserInfo() {
        if (SPUtil.isLogin(WXPayEntryActivity.this)) {


            Map<String, String> map = new HashMap<>();
            map.put("userid", SPUtil.getUser(WXPayEntryActivity.this).getUserid());
            RetrofitUtil.getretrofit().create(UserInfoBiz.class).getUser(map).enqueue(new Callback<User.UserinfoBean>() {
                @Override
                public void onResponse(Call<User.UserinfoBean> call, Response<User.UserinfoBean> response) {
                    Log.d("请求用户的数据", response.body().toString());
                    if (SPUtil.setUser(WXPayEntryActivity.this, response.body())) {

                    }
                }

                @Override
                public void onFailure(Call<User.UserinfoBean> call, Throwable t) {

                }
            });
        }
    }

}