package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.DayService;
import com.yiyuaninfo.entity.Status;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/31.
 */

public interface SaveDayServiceBiz {
    //http://yyapp.1yuaninfo.com/app/application/setmsg.php?act=moddayservice&dayservice=0,1,1,1,1,1,1,1&userid=1502788292kfi13x
    @GET("setmsg.php")
    Call<Status> getData(@Query("act") String act, @Query("dayservice") String string, @Query("userid") String userid);
}
