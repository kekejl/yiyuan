package com.yiyuaninfo.Activity.MyActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Interface.AddressBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Address;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class AddressActivity extends BaseActivity {
    private Button  button;
    @Override
    protected int getContentView() {
        return R.layout.activity_address;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("我的地址");
        button=(Button)findViewById(R.id.bt_address);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.goAactivity(AddressActivity.this,AddAddressActivity.class);
            }
        });
        getData();
    }

    private void getData() {
        Map<String,String> params=new HashMap<>();
        params.put("act","getuseradd");
        params.put("userid", SPUtil.getUser(this).getUserid());
        RetrofitUtil.getretrofit().create(AddressBiz.class).getData(params).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {

            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {

            }
        });
    }

}
