package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Head;
import com.yiyuaninfo.entity.Info;
import com.yiyuaninfo.entity.State;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface PostBiz {
     //http://yyapp.1yuaninfo.com/app/application/apple_pay.php
     @FormUrlEncoded
     @POST("apple_pay.php")
     //@POST()
     //Call<ResponseBody> upload(@Part MultipartBody.Part   part,@Part("userid" ) RequestBody  userid );
     //Call<ResponseBody> upload(@Part List<MultipartBody.Part> partList);
    // Call<State> upload(@Part List<MultipartBody.Part> partList);
     Call<State> upload(@FieldMap Map<String, String> fields);
    // Call<State> upload(@Field("produtid") String phone, @Field("goodtype") String pwd,@Field("userid") String phone1, @Field("apple_receipt") String pwd1);

}
