package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.MallEntity;
import com.yiyuaninfo.entity.Product;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface MallEntityBiz {
     //http://yyapp.1yuaninfo.com/app/application/shoppingmall.php?act=moretj&lastid=LASTID
     @GET("shoppingmall.php")
     Call<MallEntity> getData(@QueryMap Map<String ,String> params);
}
