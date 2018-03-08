package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.ShowEntity;
import com.yiyuaninfo.entity.ShowMroeEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface ShowMoreEntityBiz {
     @GET("channel.php")
     Call<ShowMroeEntity> getData(@QueryMap Map<String, String> params);
}
