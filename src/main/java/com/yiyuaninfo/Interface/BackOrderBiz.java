package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.State;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface BackOrderBiz {
     //
     @GET("backOrder.php")
     Call<State> getData(@QueryMap Map<String, String> params);
}
