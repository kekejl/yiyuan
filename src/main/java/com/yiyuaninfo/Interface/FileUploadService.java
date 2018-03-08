package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.Head;
import com.yiyuaninfo.entity.Product;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface FileUploadService {
     @Multipart
     @POST("updateUserHead.php")
     //@POST()
     //Call<ResponseBody> upload(@Part MultipartBody.Part   part,@Part("userid" ) RequestBody  userid );
     //Call<ResponseBody> upload(@Part List<MultipartBody.Part> partList);
     Call<Head> upload(@Part List<MultipartBody.Part> partList);
}
