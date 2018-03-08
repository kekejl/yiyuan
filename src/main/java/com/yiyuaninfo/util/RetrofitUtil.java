package com.yiyuaninfo.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.mrwang.retrofitcacheinterceptor.RetrofitCacheInterceptor;
import com.yiyuaninfo.App;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaocongcong on 2017/7/28.
 */

public class RetrofitUtil {

    private static Retrofit retrofit;
    private static Retrofit retrofit1;


    public static Retrofit getretrofit() {





        if (retrofit==null) {
            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(Constants.Home)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getretrofitContext(Context  context) {



        RetrofitCacheInterceptor retrofitCacheInterceptor =
                new RetrofitCacheInterceptor(context);

        File cacheDir = Environment.getExternalStorageDirectory();
        Cache cache = new Cache(cacheDir, 20 * 1024 * 1024);


        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(retrofitCacheInterceptor)
                .cache(cache)
                .build();

        if (retrofit==null) {
            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(Constants.Home)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getretrofit1(String url) {
        if (retrofit1==null) {
            retrofit1=new Retrofit
                    .Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit1;
    }
}
