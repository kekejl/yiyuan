package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.SearchEntity;
import com.yiyuaninfo.entity.SearchKeyword;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface SearchKeywordBiz {
     //
     @GET("global_serach.php")
     Call<SearchKeyword> getData(@QueryMap Map<String, String> params);
}
