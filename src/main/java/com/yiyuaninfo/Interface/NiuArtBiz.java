package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Genius;
import com.yiyuaninfo.entity.NiuArt;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface NiuArtBiz {
     @GET("niu.php")
     Call<NiuArt> getData(@QueryMap Map<String, String> params);
}
