package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.TeSe;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface TeSeOrderBiz {
     //
     @GET("user_order_gupiao.php")
     Call<TeSe> getData(@QueryMap Map<String, String> params);
}
