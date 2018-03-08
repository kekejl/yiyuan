package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.AllNiu;
import com.yiyuaninfo.entity.Collection;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface AllNiuBiz {
     //
     @GET("niu_user.php")
     Call<AllNiu> getData(@QueryMap Map<String, String> params);
}
