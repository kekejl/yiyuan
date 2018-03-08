package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.entity.VideoEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface VideoEntityBiz {
     @GET("channel.php")
     Call<VideoEntity> getData(@QueryMap Map<String, String> params);
}
