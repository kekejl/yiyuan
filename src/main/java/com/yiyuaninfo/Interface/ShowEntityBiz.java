package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.ShowEntity;
import com.yiyuaninfo.entity.VideoEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface ShowEntityBiz {
     @GET("channel.php")
     Call<ShowEntity> getData(@QueryMap Map<String ,String> params);
}
