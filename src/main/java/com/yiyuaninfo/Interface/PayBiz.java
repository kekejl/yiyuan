package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Pay;
import com.yiyuaninfo.entity.Product;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface PayBiz {
     //http://192.168.0.104/app/application/wxappapy/wxpay.php?goods=会员&price=1&ip=127.0.0.1
     //http://yyapp.1yuaninfo.com/app/application/wxappapy/wxpay.php?ip=127.0.0.1&userid=1499064765j6qavy&productid=5&num=1
     @GET("wxpay.php")
     Call<Pay> getData(@QueryMap Map<String,String> params);

}
