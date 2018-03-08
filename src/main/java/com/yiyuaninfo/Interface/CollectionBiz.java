package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Collection;
import com.yiyuaninfo.entity.Comment;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface CollectionBiz {
     //
     @GET("usercollect.php")
     Call<Collection> getData(@QueryMap Map<String, String> params);
}
