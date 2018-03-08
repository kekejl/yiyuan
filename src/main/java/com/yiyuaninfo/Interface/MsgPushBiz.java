package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.MsgPush;
import com.yiyuaninfo.entity.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface MsgPushBiz {
     @GET("history_msg_list.php")
     Call<MsgPush> getData(@Query("date") String art);
}
