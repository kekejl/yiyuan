package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.Status;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface UmengBiz {
     //http://yyapp.1yuaninfo.com/app/houtai/admin/re_postnews.php?keyword=11|2&userid=1499064765j6qavy
     //http://yyapp.1yuaninfo.com/app/application/re_postnews.php
     @GET("re_postnews.php")
     Call<State> getData(@Query("keyword") String art, @Query("userid")  String   userid, @Query("act") String act);
}
