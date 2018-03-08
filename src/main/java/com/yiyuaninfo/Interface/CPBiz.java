package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Finder;
import com.yiyuaninfo.entity.Product;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface CPBiz {
     @GET("product.php")
     Call<Product> getData(@QueryMap Map<String,String> params);
}
