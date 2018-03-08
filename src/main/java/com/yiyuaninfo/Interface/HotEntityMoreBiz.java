package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.HotEntity;
import com.yiyuaninfo.entity.HotEntityMore;
import com.yiyuaninfo.entity.InfoArrBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface HotEntityMoreBiz {
     //http://yyapp.1yuaninfo.com/app/application/hotspot.php?act=loadmorehot&lastid=17673&classid=12
     @GET("hotspot.php")
     Call<HotEntityMore> getData(@Query("act") String act, @Query("classid") String id, @Query("lastid")  String  lastid);
}
