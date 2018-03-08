package com.yiyuaninfo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.util.Log;

import com.yiyuaninfo.entity.User;

/**
 * Created by gaocongcong on 2017/7/26.
 */

public class SPUtil {

    private  static SharedPreferences sharedPreferences;

    public  static void setData(Context context, String key, String value) {
        sharedPreferences = context.getSharedPreferences("yiyundata", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);
        editor.commit();
    }
    public  static void setData(Context context, String key, boolean value) {
        sharedPreferences = context.getSharedPreferences("yiyundata", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(key, value);
        editor.commit();
    }

    public  static String getData(Context context, String key, String value) {

        sharedPreferences = context.getSharedPreferences("yiyundata", Context.MODE_PRIVATE);

        return  sharedPreferences.getString(key,value);

    }
    public  static boolean getData(Context context, String key, boolean value) {

        sharedPreferences = context.getSharedPreferences("yiyundata", Context.MODE_PRIVATE);

        return  sharedPreferences.getBoolean(key,value);

    }


    public  static void setIsFirst(Context context, String key, boolean value) {
        sharedPreferences = context.getSharedPreferences("yiyundata", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(key, value);
        editor.commit();
    }

    public  static boolean getIsFirst(Context context, String key, boolean value) {

        sharedPreferences = context.getSharedPreferences("yiyundata", Context.MODE_PRIVATE);

        return  sharedPreferences.getBoolean(key,value);

    }


    public static   Boolean setUser(Context context, User.UserinfoBean  userinfoBean) {
        sharedPreferences = context.getSharedPreferences("yiyun", Context.MODE_PRIVATE);
        /**
         * userid : 15000164281kb3bn
         * username : 13718040895
         * avatar :
         * mobile : 13718040895
         * guling : 0
         * capital : null
         * email : null
         * qqnum : null
         * weixin : null
         * weibo : null
         * groupid : 2
         */
        Log.d("存入用户数据",userinfoBean.toString());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userid",userinfoBean.getUserid());
        editor.putString("username",userinfoBean.getUsername());
        editor.putString("avatar",userinfoBean.getAvatar());
        editor.putString("mobile",userinfoBean.getMobile());
        editor.putString("guling",userinfoBean.getGuling());
        editor.putString("capital",userinfoBean.getCapital());
        editor.putString("email",userinfoBean.getEmail());
        editor.putString("qqnum",userinfoBean.getQqnum());
        editor.putString("weixin",userinfoBean.getWeixin());
        editor.putString("weibo",userinfoBean.getWeibo());
        editor.putString("groupid",userinfoBean.getGroupid());
        editor.putString("expiretime",userinfoBean.getExpiretime());
        Log.d("存入用户数据时间",userinfoBean.getExpiretime());

        editor.putString("integral",userinfoBean.getIntegral());
        Log.d("存入用户数据积分",userinfoBean.getIntegral());

        editor.putBoolean("islogin",true);
        editor.commit();

        return true;
    }
    public static   User.UserinfoBean getUser(Context context) {
        sharedPreferences = context.getSharedPreferences("yiyun", Context.MODE_PRIVATE);
        /**
         * userid : 15000164281kb3bn
         * username : 13718040895
         * avatar :
         * mobile : 13718040895
         * guling : 0
         * capital : null
         * email : null
         * qqnum : null
         * weixin : null
         * weibo : null
         * groupid : 2
         */
        User.UserinfoBean   userinfoBean=new User.UserinfoBean();


        Log.d("取出用户数据",sharedPreferences.getString("userid",null));
        userinfoBean.setUserid(sharedPreferences.getString("userid",null));
        userinfoBean.setUsername(sharedPreferences.getString("username","昵称"));
        userinfoBean.setAvatar(sharedPreferences.getString("avatar",null));
        userinfoBean.setMobile(sharedPreferences.getString("mobile","手机号"));
        userinfoBean.setGuling(sharedPreferences.getString("guling",null));
        userinfoBean.setCapital(sharedPreferences.getString("capital",null));
        userinfoBean.setEmail(sharedPreferences.getString("email",null));
        userinfoBean.setQqnum(sharedPreferences.getString("qqnum",null));
        userinfoBean.setWeixin(sharedPreferences.getString("weixin",null));
        userinfoBean.setWeibo(sharedPreferences.getString("weibo",null));
        userinfoBean.setGroupid(sharedPreferences.getString("groupid",null));
        userinfoBean.setExpiretime(sharedPreferences.getString("expiretime",null));
        userinfoBean.setIntegral(sharedPreferences.getString("integral","0"));


        return userinfoBean;
    }

    public  static  boolean  isLogin(Context  context){
        sharedPreferences = context.getSharedPreferences("yiyun", Context.MODE_PRIVATE);

        boolean  boolen=sharedPreferences.getBoolean("islogin",false);


        return boolen;
    }
    /**
     *
     * 退出删除数据
     */
    public  static   void    Exit(Context  context){
        sharedPreferences = context.getSharedPreferences("yiyun", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();


    }
}
