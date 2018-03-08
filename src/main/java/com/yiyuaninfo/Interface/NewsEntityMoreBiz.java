package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.NewsEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface NewsEntityMoreBiz {
     //http://yyapp.1yuaninfo.com/app/application/channel.php?act=infomore&classid=3&lastid=17953
     @GET("channel.php")
     Call<NewsEntity> getData(@Query("act") String art, @Query("classid") String id,@Query("lastid") String lastid);
}
