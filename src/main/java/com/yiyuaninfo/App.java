package com.yiyuaninfo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.yiyuaninfo.Activity.MainActivity;
import com.yiyuaninfo.Activity.UmengTeSeActivity;
import com.yiyuaninfo.entity.Custom;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.SharedPreferencesMgr;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */

public class App extends Application {
    private static Context context;
    private static App instance;
    private PushAgent  mPushAgent;
    private List<Custom>  customList=new ArrayList<>();


    private Handler  handler;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = this;
        EasyHttp.init(this);

        SharedPreferencesMgr.init(this, "weyye");
        imageloader();
        Config.DEBUG=true;
        UMShareAPI.get(this);

        push();
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);

        handler = new Handler();

        mPushAgent.setDisplayNotificationNumber(5);



        //设置请求头
        EasyHttp.getInstance()
                .debug("RxEasyHttp", true)
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 1000)
                .setConnectTimeout(60 * 1000)
                .setRetryCount(3)//默认网络不好自动重试3次
                .setRetryDelay(500)//每次延时500ms重试
                .setRetryIncreaseDelay(500)//每次延时叠加500ms
                .setBaseUrl(Constants.Home)
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                .setCacheMaxSize(50 * 1024 * 1024)//设置缓存大小为50M
                .setCacheVersion(1)//缓存版本为1
                .setCertificates();//信任所有证书
                //.addConverterFactory(GsonConverterFactory.create(gson))//本框架没有采用Retrofit的Gson转化，所以不用配置
        //.addInterceptor(new HeTInterceptor());//处理自己业务的拦截器





//        UmengMessageHandler messageHandler = new UmengMessageHandler() {
//            /**
//             * 自定义消息的回调方法
//             * */
//            @Override
//            public void dealWithCustomMessage(final Context context, final UMessage msg) {
//                Log.d("友盟","友盟自定义消息"+msg.custom);
//
//                handler.post(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        // 对自定义消息的处理方式，点击或者忽略
//                        boolean isClickOrDismissed = true;
//                        if (isClickOrDismissed) {
//                            //自定义消息的点击统计
//                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
//                        } else {
//                            //自定义消息的忽略统计
//                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
//                        }
//                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
//                        Log.d("友盟接收的消息",msg.custom);
//                    }
//                });
//            }
//
////            /**
////             * 自定义通知栏样式的回调方法
////             * */
////            @Override
////            public Notification getNotification(Context context, UMessage msg) {
////
////                Log.d("友盟","展示到通知栏"+msg.extra+ msg.custom+msg.builder_id);
////
////                switch (msg.builder_id) {
//////                    case 1:
//////                        Notification.Builder builder = new Notification.Builder(context);
//////                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.notification_view);
//////                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
//////                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
//////                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
//////                        myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));
//////                        builder.setContent(myNotificationView)
//////                                .setSmallIcon(getSmallIconId(context, msg))
//////                                .setTicker(msg.ticker)
//////                                .setAutoCancel(true);
//////
//////                        return builder.getNotification();
////                    default:
////                        //默认为0，若填写的builder_id并不存在，也使用默认。
////                        return super.getNotification(context, msg);
////                }
////            }
//        };
     //   mPushAgent.setMessageHandler(messageHandler);


        /**
         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Log.d("友盟","点击通知栏"+msg.custom+"\n"+msg.extra+"++"+msg.extra.get("tip"));
                Log.d("友盟接收","状态栏点击接收消息");
                customList.clear();
//                for (Map.Entry<String, String> entry : msg.extra.entrySet()) {
//                    String key = entry.getKey();
//                    String value = entry.getValue();
//                    Custom custom = new Custom(key, value);
//                    customList.add(custom);
//                }

                 if(msg.custom.equals("特色服务")){

                     Intent  intent=new Intent(context, MainActivity.class);
                     intent.putExtra("umeng",msg.extra.get("id"));
                     intent.putExtra("umengtip",msg.extra.get("tip"));
                     intent.putExtra("umengorderid",msg.extra.get("orderid"));

                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("友盟","友盟专递的参数"+msg.extra.get("id")+msg.extra.get("tip"));
                     startActivity(intent);


                 }

                 if(msg.extra.get("type").equals("sp_sell")){


                     Intent  intent=new Intent(context, UmengTeSeActivity.class);
                     intent.putExtra("umengid","http://yyapp.1yuaninfo.com/app/houtai/sellpush.php?id="+msg.extra.get("id"));
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // Log.d("友盟","友盟专递的参数"+customList.toString()+"\n"+customList.get(0).getValue());
                     startActivity(intent);

                 }

                //Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        //使用自定义的NotificationHandler，来结合友盟统计处理消息通知，参考http://bbs.umeng.com/thread-11112-1-1.html
        //CustomNotificationHandler notificationClickHandler = new CustomNotificationHandler();
        mPushAgent.setNotificationClickHandler(notificationClickHandler);


    }
    /**
     * 注册友盟推送
     */
    private void push() {
        Log.d("用户的devicetoken","+++++++");

        mPushAgent = PushAgent.getInstance(this);
      //  mPushAgent.onAppStart();
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.d("用户的devicetoken",deviceToken);
                SPUtil.setData(context,"deviceToken",deviceToken);

            }

            @Override
            public void onFailure(String s, String s1) {
                Log.d("用户的devicetoken错误",s+"++"+s1);

            }
        });
    }

    /**
     *
     * 友盟分享
     */

    {
        PlatformConfig.setWeixin("wx11d19ee9062c616a","f661786b2c7513137c04b8178b9d90ed");
        PlatformConfig.setQQZone("1106277112","tIjRbNaPk2J6TUbE");
        PlatformConfig.setSinaWeibo("242133346","4f5fbcc22f6998670e806f3e87a7241a","https://api.weibo.com/oauth2/default.html");
    }



    private void imageloader() {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 获取全局的context
     */
    public static Context getContext() {
        return context;
    }

    public static App getInstance() {
        return instance;
    }


    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.d("Application111", "onTerminate");
        super.onTerminate();
    }
    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.d("Application111", "onLowMemory");
        super.onLowMemory();
    }
    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        Log.d("Application111", "onTrimMemory");
        super.onTrimMemory(level);
    }
}
