package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Music;
import com.yiyuaninfo.entity.Product;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface MusicBiz {
     //http://yyapp.1yuaninfo.com/app/application/channel.php?act=music
     //加载更多
     //http://yyapp.1yuaninfo.com/app/application/channel.php?act=videomore&lastid=LASTID
     @GET("channel.php")
     Call<Music> getData(@QueryMap Map<String, String> params);
}
