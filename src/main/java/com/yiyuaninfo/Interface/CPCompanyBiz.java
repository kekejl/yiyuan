package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.CPCompany;
import com.yiyuaninfo.entity.Product;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface CPCompanyBiz {
     @GET("product.php")
     Call<CPCompany> getData(@QueryMap Map<String, String> params);
}
