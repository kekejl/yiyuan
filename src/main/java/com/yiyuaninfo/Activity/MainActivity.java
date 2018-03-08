package com.yiyuaninfo.Activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allenliu.versionchecklib.AVersionService;
import com.allenliu.versionchecklib.VersionParams;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMShareAPI;
import com.yiyuaninfo.Fragment.BaseFragment;
import com.yiyuaninfo.Fragment.ChannelFragment;
import com.yiyuaninfo.Fragment.Communefragment;
import com.yiyuaninfo.Fragment.Myfragment;
import com.yiyuaninfo.Fragment.Textfragment;
import com.yiyuaninfo.Interface.DevicetokenBiz;
import com.yiyuaninfo.Interface.IsCheckLogInBiz;
import com.yiyuaninfo.Interface.TeSeBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.VersionService;
import com.yiyuaninfo.entity.Custom;
import com.yiyuaninfo.entity.Devicetoken;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.AppManager;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener,CommonPopupWindow.ViewInterface {
    private static final String TAG = "MainActivity";

    @BindView(R.id.rl_home)
    RelativeLayout rl_home;
    @BindView(R.id.image_home)
    ImageView image_home;
    @BindView(R.id.rl_channel)
    RelativeLayout rl_channel;
    @BindView(R.id.image_channel)
    ImageView image_channel;
    @BindView(R.id.rl_commune)
    RelativeLayout rl_commune;
    @BindView(R.id.image_commune)
    ImageView image_commune;
    @BindView(R.id.rl_my)
    RelativeLayout rl_my;
    @BindView(R.id.image_my)
    ImageView image_my;
    @BindView(R.id.tv_home)
    TextView tv_home;
    @BindView(R.id.tv_channel)
    TextView tv_channel;
    @BindView(R.id.tv_commune)
    TextView tv_commune;
    @BindView(R.id.tv_my)
    TextView tv_my;
    private String path;
    private List<Custom>  listcustom=new ArrayList<>();
    //FrameLayout flContent;
    // AHBottomNavigation bottomNavigation;
    private Textfragment textfragment;
    private ChannelFragment channelFragment;
    private Communefragment communefragment;
    private Myfragment myfragment;
    private long mExitTime = 0;
    private FragmentManager fragmentManager;
    private CommonPopupWindow  popupWindow,popupWindowtishi;
    private TextView  umengfalse,umengtrue;
    private PushAgent mPushAgent;
    private Boolean  flag=false;
    private String umsgid,umsgtip,umsgorderid;
    private Intent intent,intentService;



    private String UMENG="http://yyapp.1yuaninfo.com/app/houtai/push.php?id=";
    private String UMENGTYPE="0";




    private  boolean  networkboolean;

    private TextView  umengTishi;
    private    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showpopwindow();//你自己定义的显示PopupWindow的方法
                    break;
                case 1:
                    showpopwindowtishi();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d("MainActivity执行了","onCreate");
        AppManager.getAppManager().addActivity(MainActivity.this);

        setContentView(R.layout.activity_main);

      //  WindowUtils.showPopupWindow(this);


        ButterKnife.bind(this);
        networkboolean= Network.isConnected();
        if(!networkboolean){
            Network.getInstance().setNetworkMethod(this);
        }
        path=this.getExternalFilesDir("yiyuaninfo").getPath()+"/myhead.jpg";

        Log.d("数据123",getIntent().getStringExtra("umeng")+"???");
        umsgid=getIntent().getStringExtra("umeng");
        umsgtip=getIntent().getStringExtra("umengtip");
        umsgorderid=getIntent().getStringExtra("umengorderid");
        Log.d("友盟提示语",umsgid+"??????"+umsgtip+"???");

        if(umsgid!=null){
         //   Log.d("特色服务",umsgtip.equals("有新服务通知，您接受本次服务吗？")+"\n"+umsgtip);

//            if(umsgtip.equals("有新服务通知，您接受本次服务吗？")&&umsgtip.equals("您的持股已达上限（3支以上），再次与您确认是否需要？")){
//
//                Log.d("特色服务","特色服务");
//                UMENGTYPE="1";
//            }
            if(umsgtip!=null){
                if(!umsgtip.equals("")){


                    switch (umsgtip){
                        case "有新服务通知，您接受本次服务吗？":
                            UMENGTYPE="1";

                            break;
                        case "您的持股已达上限（3支以上），再次与您确认是否需要？":

                            UMENGTYPE="1";

                            break;
                        default :UMENGTYPE="0";

                    }
                    handler.sendEmptyMessageDelayed(0,1000);

                }


            }





        }
       // handler.sendEmptyMessageDelayed(0,1000);

       // getUmengId();
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
        //版本更新
        CheckVerUpData();
        //Log.d("SP中的deviceToken", SPUtil.getData(this,"deviceToken",null));



        // Log.d("IP1111111",getIPAddress(this));
         SPUtil.setData(this,"IP",getIPAddress(this));
//        //获取wifi服务
//        WifiManager wifiManager = (WifiManager) getSystemService( App.getContext().WIFI_SERVICE);
//        //判断wifi是否开启
//        if (!wifiManager.isWifiEnabled()) {
//            wifiManager.setWifiEnabled(true);
//        }
//        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//        int ipAddress = wifiInfo.getIpAddress();
//        String ip = intToIp(ipAddress);

        /**
         *
         * 判断是否第一次使用
         */

        if(!SPUtil.getIsFirst(this,"isone",false)){
            Map<String,String> params=new HashMap<>();
            params.put("act","setup");
            params.put("devicetoken",SPUtil.getData(this,"deviceToken",null));
            Log.d("用户的devicetoken",SPUtil.getData(this,"deviceToken",null));
            RetrofitUtil.getretrofit().create(DevicetokenBiz.class).getData(params).enqueue(new Callback<Devicetoken>() {
                @Override
                public void onResponse(Call<Devicetoken> call, Response<Devicetoken> response) {
                    Log.d("第一次使用userid",response.body().getUserid());
                    if(!response.body().getUserid().equals("")){
                        SPUtil.setData(MainActivity.this,"USERID",response.body().getUserid());
                        SPUtil.setIsFirst(MainActivity.this,"isone",true);
                    }
                }

                @Override
                public void onFailure(Call<Devicetoken> call, Throwable t) {

                }
            });
        }else {
        }
        if(SPUtil.isLogin(this)){
       isCheckLogin();
        }

    }


    /**
     *
     * 判断用户是否其他手机登录   未登录删除数据
     */
    private void isCheckLogin() {
     Map<String,String> params=new HashMap<>();
        params.put("devicetoken",SPUtil.getData(this,"deviceToken",null));
        params.put("userid",SPUtil.getUser(this).getUserid());
        RetrofitUtil.getretrofit().create(IsCheckLogInBiz.class).getData(params).enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                if(response.body().getState().equals("1")){

                    handler.sendEmptyMessageDelayed(1,1000);

                }else {

                }
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });



    }

    private void getUmengId() {



    }

    private String intToIp(int i) {

        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;








    }

    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }
    /**
     * 检查版本更新
     */
    private void CheckVerUpData() {

        VersionParams versionParams = new VersionParams().setRequestUrl("http://yyapp.1yuaninfo.com/version.html");
        intentService = new Intent(MainActivity.this, VersionService.class);
        intentService.putExtra(AVersionService.VERSION_PARAMS_KEY, versionParams);
        startService(intentService);



    }

    @OnClick({R.id.rl_channel, R.id.rl_commune, R.id.rl_home, R.id.rl_my})

    public void tabclick(View v) {
        switch (v.getId()) {
            case R.id.rl_home:
                setTabSelection(0);
                break;
            case R.id.rl_channel:
                setTabSelection(1);
                break;
            case R.id.rl_commune:
                //CommonUtil.showToast(this,"敬请期待！");
                setTabSelection(2);
                break;
            case R.id.rl_my:
                setTabSelection(3);
                break;

        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                image_home.setImageResource(R.drawable.icon_home_true);
                tv_home.setTextColor(this.getResources().getColor(R.color.colorPrimary));
                if (textfragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    textfragment = new Textfragment();
                    transaction.add(R.id.view_content, textfragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(textfragment);
                }
                break;
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                image_channel.setImageResource(R.drawable.icon_channel_true);
                tv_channel.setTextColor(this.getResources().getColor(R.color.colorPrimary));

                if (channelFragment == null) {
                    channelFragment = new ChannelFragment();
                    transaction.add(R.id.view_content, channelFragment);
                } else {
                    transaction.show(channelFragment);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                image_commune.setImageResource(R.drawable.icon_commune_true);
                tv_commune.setTextColor(this.getResources().getColor(R.color.colorPrimary));
                if (communefragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    communefragment = new Communefragment();
                    transaction.add(R.id.view_content, communefragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(communefragment);
                }
                break;
            case 3:
            default:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                image_my.setImageResource(R.drawable.icon_my_true);
                tv_my.setTextColor(this.getResources().getColor(R.color.colorPrimary));
                if (myfragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    myfragment = new Myfragment();
                    transaction.add(R.id.view_content, myfragment);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(myfragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        image_home.setImageResource(R.drawable.icon_home);
        tv_home.setTextColor(Color.parseColor("#82858b"));
        image_channel.setImageResource(R.drawable.icon_channel);
        tv_channel.setTextColor(Color.parseColor("#82858b"));
        image_commune.setImageResource(R.drawable.icon_commune);
        tv_commune.setTextColor(Color.parseColor("#82858b"));
        image_my.setImageResource(R.drawable.icon_my);
        tv_my.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (textfragment != null) {
            transaction.hide(textfragment);
        }
        if (channelFragment != null) {
            transaction.hide(channelFragment);
        }
        if (communefragment != null) {
            transaction.hide(communefragment);
        }
        if (myfragment != null) {
            transaction.hide(myfragment);
        }
    }
    /*  @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        Log.d("手机屏幕密度",xdpi+"======"+ydpi+"123");

        //判断是否显示ToolBar
        //isshowToolBar(true);
        //flContent=(FrameLayout)findViewById(R.id.fl_content);
        //bottomNavigation=(AHBottomNavigation)findViewById(R.id.bottom_navigation);
        //permission();
        //initView();
        //initData();
        //initListener();
        Logger.d("执行了permission");
    }*/

   /* public void permission() {
        requestRuntimePermission(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA}, new PermissionListener() {
            @Override
            public void onGranted() {
                Toast.makeText(MainActivity.this, "所有权限都同意了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for (String permission : deniedPermission) {
                    Toast.makeText(MainActivity.this, "被拒绝权限：" + permission, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

    @Override
    public void onFragmentInteraction(Bundle bundle) {
        Log.e(TAG, "onFragmentInteraction: " + bundle.getString("BlankFragment"));
    }

    /*private void initView() {
        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(getResources().getString(R.string.tab_1), R.drawable.icon_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(getResources().getString(R.string.tab_2), R.drawable.icon_channel);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(getResources().getString(R.string.tab_3), R.drawable.icon_commune);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(getResources().getString(R.string.tab_4), R.drawable.icon_my);

        // Add items
        bottomNavigation.addItem(item1);

        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

        // 默认背景颜色
        bottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.bg_bottom_navigation));
        // 切换时颜色的转变
        bottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.accent_bottom_navigation));
        bottomNavigation.setInactiveColor(ContextCompat.getColor(this, R.color.inactive_bottom_navigation));
        // 强制着色
        bottomNavigation.setForceTint(true);
        // 强制展示标题
        bottomNavigation.setForceTitlesDisplay(true);
        // 使用圆圈效果
        bottomNavigation.setColored(false);
        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);
    }*/

    /*private void initData() {
        if (homefragment == null) {
            homefragment = Homefragment.newInstance(getString(R.string.tab_1));
        }

        if (!homefragment.isAdded()) {

            getSupportFragmentManager().beginTransaction().add(R.id.fl_content, homefragment).commit();

            currentFragment = homefragment;
        }
    }

    private void initListener() {
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                Log.d(TAG, "onTabSelected: position:" + position + ",wasSelected:" + wasSelected);

                if (position == 0) {// 首页
                    clickshome();
                } else if (position == 1) {//频道
                    clickchannel();
                } else if (position == 2) {// 公社
                    clickcommune();
                } else if (position == 3) {// 我的
                    clickmy();
                }
                return true;
            }
        });

        //toolbar.setOnMenuItemClickListener(this);
    }

    private void clickshome() {
        if (homefragment == null) {
            homefragment = Homefragment.newInstance(getString(R.string.tab_1));
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), homefragment);
    }


    private void clickchannel() {
        if (channelFragment == null) {
            channelFragment = ChannelFragment.newInstance(getString(R.string.tab_2));
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), channelFragment);
    }

    private void clickcommune() {
        if (communefragment == null) {
            communefragment = Communefragment.newInstance(getString(R.string.tab_3));
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), communefragment);
    }

    private void clickmy() {
        if (myfragment == null) {
            myfragment = Myfragment.newInstance(getString(R.string.tab_4));
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), myfragment);
    }*/

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showToast("再按一次退出程序哦");
           // Snackbar.make(rl_home, R.string.exit_toast, Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
           // ActivityManager am = (ActivityManager)getSystemService (Context.ACTIVITY_SERVICE);
          //  am.restartPackage(getPackageName());
            //finish();
//            int pid=android.os.Process.myPid();
//            android.os.Process.killProcess(pid);
          //  AppManager.getAppManager().finishAllActivity();
//            stopService(intentService);
//
//            android.os.Process.killProcess(android.os.Process.myPid()) ;   //获取PID
//            System.exit(0);
//            finish();
            AppManager.getAppManager().finishAllActivity();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("MainActivity执行了","onResume");
        if(SPUtil.isLogin(this)){
            isCheckLogin();
        }


//        Bundle bun = getIntent().getExtras();
//        if (bun != null) {
//            Set<String> keySet = bun.keySet();
//            for (String key : keySet) {
//                String value = bun.getString(key);
//
//                Log.d("友盟测试接收的参数",value+"");
//
//                if(value.equals("1")){
//                    handler.sendEmptyMessageDelayed(0,1000);
//                }
//            }
//        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity执行了","onRestart");





    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity执行了","onPause");

    }



    private    void showpopwindow() {

        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popwindow_umeng,null);
        //测量View的宽高
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_umeng)
                .setWidthAndHeight(width-300, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(false);
        popupWindow.showAtLocation(rl_home, Gravity.CENTER, 0, 0);

    }
    private void showpopwindowtishi() {

        if (popupWindowtishi != null && popupWindowtishi.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popwindow_tishi,null);
        //测量View的宽高
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowtishi = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_tishi)
                .setWidthAndHeight(width-300, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        popupWindowtishi.setOutsideTouchable(false);
        popupWindowtishi.setFocusable(false);
        popupWindowtishi.showAtLocation(rl_home, Gravity.CENTER, 0, 0);

    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId){
            case R.layout.popwindow_umeng:

          umengfalse=(TextView)view.findViewById(R.id.tv_pop_umeng_false);
          umengtrue=(TextView)view.findViewById(R.id.tv_pop_umeng_true);

                umengTishi=(TextView)view.findViewById(R.id.tv_umeng_tishi);
                umengTishi.setText(umsgtip);
               // umengTishi.setText(Constants.string1);
          umengtrue.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Log.d("是否同意查看",UMENGTYPE.equals("1")+"+++"+UMENGTYPE);


                  if(UMENGTYPE.equals("1")){

                      Log.d("是否同意查看",  UMENG+umsgid+"&type="+UMENGTYPE+"&yesno=1"+"&userid="+SPUtil.getUser(MainActivity.this).getUserid());

                      CommonUtil.goAactivity(MainActivity.this,UmengTeSeActivity.class,"umengid",

                              UMENG+umsgid+"&type="+UMENGTYPE+"&yesno=1"+"&userid="+SPUtil.getUser(MainActivity.this).getUserid()+"&orderid="+umsgorderid);
                  }else {

                      Map<String,String> params=new HashMap<>();
                      params.put("id",umsgid);
                      params.put("type",UMENGTYPE);
                      params.put("yesno","1");
                      params.put("userid",SPUtil.getUser(MainActivity.this).getUserid());
                     RetrofitUtil.getretrofit().create(TeSeBiz.class).getData(params).enqueue(new Callback<String>() {
                         @Override
                         public void onResponse(Call<String> call, Response<String> response) {

                         }

                         @Override
                         public void onFailure(Call<String> call, Throwable t) {

                         }
                     });




                  }

              popupWindow.dismiss();

                  //ToastUtils.showToast("同意");
//                  RetrofitUtil.getretrofit1(Constants.umeng).create(UmengBiz.class).getData(umsgid,SPUtil.getUser(MainActivity.this).getUserid(),"yes").enqueue(new Callback<State>() {
//                      @Override
//                      public void onResponse(Call<State> call, Response<State> response) {
//
//
//                          Log.d("同意打开消息",response.body().getState()+"");
//                          Log.d("同意打开消息","成功");
//                          ToastUtils.showToast(response.body().getState()+"同意");
//                          popupWindow.dismiss();
//                      }
//
//                      @Override
//                      public void onFailure(Call<State> call, Throwable t) {
//                          ToastUtils.showToast("失败");
//                          Log.d("同意打开消息","失败");
//
//                      }
//                  });
              }

          });
        umengfalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UMENGTYPE.equals("1")){
                    CommonUtil.goAactivity(MainActivity.this,UmengTeSeActivity.class,"umengid",

                            UMENG+umsgid+"&type="+UMENGTYPE+"&yesno=1"+"&userid="+SPUtil.getUser(MainActivity.this).getUserid());
                    popupWindow.dismiss();
                }else {
                    Map<String,String> params=new HashMap<>();
                    params.put("id",umsgid);
                    params.put("type",UMENGTYPE);
                    params.put("yesno","0");
                    params.put("userid",SPUtil.getUser(MainActivity.this).getUserid());
                    RetrofitUtil.getretrofit().create(TeSeBiz.class).getData(params).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }


                popupWindow.dismiss();
//                RetrofitUtil.getretrofit1(Constants.umeng).create(UmengBiz.class).getData(umsgid,SPUtil.getUser(MainActivity.this).getUserid(),"no").enqueue(new Callback<State>() {
//                    @Override
//                    public void onResponse(Call<State> call, Response<State> response) {
//                        Log.d("不同意打开消息",response.body().getState()+"");
//                        ToastUtils.showToast(response.body().getState()+"不同意打开");
//                    }
//
//                    @Override
//                    public void onFailure(Call<State> call, Throwable t) {
//
//                    }
//                });
                popupWindow.dismiss();
            }
        });
                break;
            case R.layout.popwindow_tishi:
              TextView  textView= (TextView)view.findViewById(R.id.tv_tishi_qx);
                     textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delFile(path);
                        SPUtil.Exit(MainActivity.this);
                        popupWindowtishi.dismiss();

//                        Intent intent = new Intent("android.text_change");
//                        intent.putExtra("text", "1");
//                        sendBroadcast(intent);
                        myfragment.submit("1");
                    }
                });

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {


        if(popupWindow!=null&&popupWindow.isShowing()){
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    //删除文件
    public  void delFile(String path){
        File file = new File(path);
        Log.d("删除文件",file.isFile()+"11");
        if(file.isFile()){

            file.delete();

        }

        file.exists();
    }

    public interface Submit {
        public void submit(String userStr);
    }
}
