package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.entity.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface UserBiz {
     @GET("usersign.php")
     Call<User> getUser(@QueryMap Map<String,String> params);
}
