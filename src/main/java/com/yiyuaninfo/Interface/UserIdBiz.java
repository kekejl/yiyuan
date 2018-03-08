package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Devicetoken;
import com.yiyuaninfo.entity.UserId;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface UserIdBiz {
     //
     @GET("user_setup.php")
     Call<UserId> getData(@QueryMap Map<String, String> params);
}
