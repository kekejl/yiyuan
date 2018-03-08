package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Register;
import com.yiyuaninfo.entity.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/8/15.
 */

public interface Registinfo {
    //http://yyapp.1yuaninfo.com/app/application/validate.php?mobile=17090028712&yzm=5785010&password=123321
    //http://yyapp.1yuaninfo.com/app/application/validate.php?mobile=18855970767&yzm=307608&password=123456
    @GET("validate.php")
    Call<User> getData(@QueryMap Map<String,String> params);
}
