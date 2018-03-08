package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Register;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface ForgetPwdBiz {
     //http://yyapp.1yuaninfo.com/app/application/modify_pwd.php?mobile=17090028712&password=123456&yzm=232122

     @GET("modify_pwd.php")
     Call<Register> getData(@Query("mobile") String mobile,@Query("password") String pwd,@Query("yzm") String code);
}
