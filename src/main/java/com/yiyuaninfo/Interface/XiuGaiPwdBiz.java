package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Register;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface           XiuGaiPwdBiz {

     //http://yyapp.1yuaninfo.com/app/application/mod_pwd.php?act=modpwd&userid=USERID&newpwd=PWD&oldpwd=oldpwd
     @GET("mod_pwd.php")
     Call<Register> getData(@Query("act") String act, @Query("userid") String userid, @Query("oldpwd") String oldpwd, @Query("newpwd") String newpwd);
}
