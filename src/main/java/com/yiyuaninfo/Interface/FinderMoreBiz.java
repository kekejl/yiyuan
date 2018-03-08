package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Finder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface FinderMoreBiz {
     @GET("tougu.php")
     Call<Finder> getData(@Query("act") String art, @Query("keyword") int id,@Query("lastid") String  lastid);
}
