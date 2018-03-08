package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.HomeEntity;
import com.yiyuaninfo.entity.Msg;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface HomeBiz {
     @GET("hom_1.php")
     Call<HomeEntity> getHome(@Query("act")  String art);
}
