package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Info;
import com.yiyuaninfo.entity.IsGuanZhu;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface IsGuanZhuBiz {
     //
     @GET("niu_user.php")
     Call<IsGuanZhu> getData(@QueryMap Map<String, String> params);

}
