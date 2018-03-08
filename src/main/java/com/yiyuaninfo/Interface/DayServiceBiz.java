package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.DayService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/31.
 */

public interface DayServiceBiz {
    //http://yyapp.1yuaninfo.com/app/application/setmsg.php?act=dayservice&userid=1502788292kfi13x
    @GET("setmsg.php")
    Call<DayService> getData(@Query("act") String act,@Query("userid") String   userid);
}
