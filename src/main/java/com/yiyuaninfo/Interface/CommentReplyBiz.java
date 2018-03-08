package com.yiyuaninfo.Interface;

import com.yiyuaninfo.entity.CommentList;
import com.yiyuaninfo.entity.CommentReply;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by gaocongcong on 2017/7/11.
 */

public interface CommentReplyBiz {
     //
     @GET("articlecomment.php")
     Call<CommentReply> getData(@QueryMap Map<String, String> params);
}
