package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.entity.Version;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface VersionBiz {
     @GET("version.html")
     Call<Version> getData();
}
