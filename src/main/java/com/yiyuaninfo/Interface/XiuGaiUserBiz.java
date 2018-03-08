package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Register;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.entity.UserXiuGai;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface XiuGaiUserBiz {
     //http://yyapp.1yuaninfo.com/app/application/updateUserInfo.php?act=username&username=老王

     @GET("updateUserInfo.php")
     Call<UserXiuGai> getData(@QueryMap Map<String, String> params);
}
