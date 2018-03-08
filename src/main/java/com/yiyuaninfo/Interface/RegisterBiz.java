package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.entity.Register;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface RegisterBiz {
     //http://yyapp.1yuaninfo.com/app/application/register.php?keyword=reg&mobile=17090028712
     @GET("register.php")
     Call<Register> getData(@Query("keyword") String art,@Query("mobile") String mobile);
}
