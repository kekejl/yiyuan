package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.CompanyDetails;
import com.yiyuaninfo.entity.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface CompanyDetailsBiz {
     //http://yyapp.1yuaninfo.com/app/application/company_info.php?comid=1
     @GET("company_info.php")
     Call<CompanyDetails> getData(@Query("comid") String id);
}
