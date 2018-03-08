package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Address;
import com.yiyuaninfo.entity.QianDao;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface QianDaoBiz {
     //
     @GET("usersigndata.php")
     Call<QianDao> getData(@QueryMap Map<String, String> params);
}
