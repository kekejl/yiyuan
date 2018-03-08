package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.HotEntity;
import com.yiyuaninfo.entity.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface HotEntityBiz {

     @GET("hotspot.php")
     Call<HotEntity> getData(@Query("act") String art,@Query("changeid")int   id);
}
