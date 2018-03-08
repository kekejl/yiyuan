package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.QuestionAll;
import com.yiyuaninfo.entity.WenDa;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface QuestionAllBiz {
     //http://yyapp.1yuaninfo.com/app/application/commune.php?act=wendawz&userid=15000164281kb3bn

     @GET("commune.php")
     Call<QuestionAll> getData(@QueryMap Map<String, String> params);
}
