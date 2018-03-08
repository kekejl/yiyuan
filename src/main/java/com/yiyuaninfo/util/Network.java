package com.yiyuaninfo.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

import com.yiyuaninfo.App;

/**
 * Created by gaocongcong on 2018/2/7.
 */

public class Network {
    private volatile static  Network instance;

    public Network(){
        instance=this;
    }
    public synchronized static Network getInstance() {
        if (instance == null) {
            synchronized (Network.class) {
                instance = instance == null ? new Network() : instance;
            }
        }
        return instance;
    }

    public static boolean isConnected() {
        Context context = App.getContext();
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        if (activeInfo != null) {
            return activeInfo.isConnected();
        }
        return false;
    }
//    public static boolean isNetConnected() {
//        ConnectivityManager cm = (ConnectivityManager) JXHDCApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = cm != null ? cm.getActiveNetworkInfo() : null;
//        return info != null && info.isConnected();
//    }

    public static boolean isWifiConnected() {
        boolean isConnected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            isConnected = info.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnected;
    }

    public static boolean isMobileConnected() {
        boolean isConnected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            isConnected = info.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnected;
    }

    /*
    * 打开设置网络界面
    * */
    AlertDialog.Builder builder=null;
    public void setNetworkMethod(final Context context){
        if (builder!=null){
            builder.setCancelable(true);
        }
        //提示对话框
        builder=new AlertDialog.Builder(context);
        builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Intent intent=null;
                //判断手机系统的版本  即API大于10 就是3.0或以上版本
                if(android.os.Build.VERSION.SDK_INT>10){
                    intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                }else{
                    intent=new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
//                    intent = new Intent();
//                    ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
//                    intent.setComponent(component);
//                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).show();
    }
    /**
     * 网络异常提示界面

     */
    public  void setNetworkAnomaly(final Context context){
        //提示对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("网络异常提示").setMessage("网络连接异常，请再试一次?").setPositiveButton("是", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
               // EventBus.getDefault().post("0","request_data");
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).show();
    }



}
