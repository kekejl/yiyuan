package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Register;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface XuiGaiPgoneCodeBiz {
     //http://yyapp.1yuaninfo.com/app/application/mod_ph_no.php?act=check&phone=17010202123
     @GET("mod_ph_no.php")
     Call<Register> getData(@Query("act") String art, @Query("phone") String mobile);
}
