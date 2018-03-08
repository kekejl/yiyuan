package com.yiyuaninfo.Interface;

import com.nostra13.universalimageloader.utils.L;
import com.yiyuaninfo.entity.Msg;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface MsgBiz {
     @GET("message.php")
     Call<List<Msg>> getMsg(@Query("act") String  art);
}
