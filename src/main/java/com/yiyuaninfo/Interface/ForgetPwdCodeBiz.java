package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Register;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface ForgetPwdCodeBiz {
     //http://yyapp.1yuaninfo.com/app/application/register.php?mobile=17090028712
     @GET("register.php")
     Call<Register> getData( @Query("mobile") String mobile);
}
