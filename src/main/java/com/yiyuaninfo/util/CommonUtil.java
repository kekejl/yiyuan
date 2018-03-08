package com.yiyuaninfo.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yiyuaninfo.App;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.ChannelEntity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 联网，格式化时间工具类
 **/
public class CommonUtil {
    /**
     *
     * 点击量
     * @param number
     * @return
     */
    public static  String  getNumber(String number){
        String b="";
        int  a=Integer.parseInt(number);
        if(a<10000){
           b=String.valueOf(a);
        }else {
            DecimalFormat df   = new DecimalFormat("######0.00");
            b=df.format((double)a/10000)+"万";
        }
        return b;
    }
    public static  String  getMonth(int  month){
        String  smonth=Integer.toString(month);
        switch (smonth){
            case "1" :
                smonth="01";
                break;
            case "2" :
                smonth="02";
                break;
            case "3" :
                smonth="03";
                break;
            case "4" :
                smonth="04";
                break;
            case "5" :
                smonth="05";
                break;
            case "6" :
                smonth="06";
                break;
            case "7" :
                smonth="07";
                break;
            case "8" :
                smonth="08";
                break;
            case "9" :
                smonth="09";
                break;
        }

        return smonth;
    }
    public static  String  getStingToString(String string){
        String  smonth=string;
        switch (string){
            case "01" :
                smonth="1";
                break;
            case "02" :
                smonth="2";
                break;
            case "03" :
                smonth="3";
                break;
            case "04" :
                smonth="4";
                break;
            case "05" :
                smonth="5";
                break;
            case "06" :
                smonth="6";
                break;
            case "07" :
                smonth="7";
                break;
            case "08" :
                smonth="8";
                break;
            case "09" :
                smonth="9";
                break;

        }

        return smonth;
    }

    /**
     * 获取软件版本号
     * @param context
     * @return
     */
    public static String  getVerName(Context context) {
        String verName ="";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("msg",e.getMessage());
        }
        return verName;
    }


    private static   Toast  toast;
    public static  void  showToast(Context context, String content){
        if(toast==null){
            toast= Toast.makeText(context,content,Toast.LENGTH_SHORT);
        }else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     *
     * 读取本地json数据
     * @param context
     * @param fileName
     * @return
     */

    public static ChannelEntity analysisJsonFile(Context context, String fileName){
        String content = FileUtils.readJsonFile(context,fileName);
        Gson gson = new Gson();
        ChannelEntity entity = gson.fromJson(content, ChannelEntity .class);
        return  entity;

    }

    /**
     * 跳转Activity带参数
     * @param context
     * @param activity
     * @param name
     * @param string
     */

    public static  void  goAactivity(   Context context , Class<? extends Activity>  activity,  String  name , String string){
        Intent intent=new Intent(context,activity);
        intent.putExtra(name,string);
        context.startActivity(intent);



    }
    /*
   * 跳转Activity带参数
   * */
    public static  void  goAactivity(Context  context , Class<? extends Activity>  activity,  String  name , String string,String namemsg,String msg){
        Intent intent=new Intent(context,activity);
        intent.putExtra(name,string);
        intent.putExtra(namemsg,msg);
        context.startActivity(intent);
    }
    public static  void  goAactivity(Context  context , Class<? extends Activity>  activity,  String  name0 , String string0,String name1,String string1,String name2,String string2){
        Intent intent=new Intent(context,activity);
        intent.putExtra(name0,string0);
        intent.putExtra(name1,string1);
        intent.putExtra(name2,string2);
        context.startActivity(intent);
    }
    public static  void  goAactivity(Context  context , Class<? extends Activity>  activity,  String  name0 , String string0,String name1,String string1,
                                     String name2, String string2,
                                     String name3, String string3,
                                     String name4, String string4
    ){
        Intent intent=new Intent(context,activity);
        intent.putExtra(name0,string0);
        intent.putExtra(name1,string1);
        intent.putExtra(name2,string2);
        intent.putExtra(name3,string3);
        intent.putExtra(name4,string4);
        context.startActivity(intent);
    }
    /*
 * 跳转Activity带参数
 * */
    public static  void  goAactivity(Context  context , Class<? extends Activity>  activity,  String  name , String string,String namemsg,int msg){
        Intent intent=new Intent(context,activity);
        intent.putExtra(name,string);
        intent.putExtra(namemsg,msg);
        context.startActivity(intent);
    }
    public  static  void  goAactivity(Context  context , Class<? extends Activity>  activity){
        Intent intent=new Intent(context,activity);
        context.startActivity(intent);

    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }
    public static int measureWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return  view.getMeasuredWidth();
    }
    public static int measureHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return  view.getMeasuredHeight();
    }
    /**
     * MD5加密
     *
     * @param paramString
     * @return
     */
    public static String md5(String paramString) {
        String returnStr;
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update((paramString+Constants.FLAG).getBytes());
            returnStr = byteToHexString(localMessageDigest.digest());
            return returnStr;
        } catch (Exception e) {
            return paramString;
        }
    }

    public static void callPhone(Context context, final String phone) {
        Intent phoneIntent = new Intent();
        phoneIntent.setAction(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + phone));
        context.startActivity(phoneIntent);
    }

    /**
     * 将指定byte数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    /**
     * 判断当前网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            return (info != null && info.isAvailable());
        }
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
     */

    @SuppressLint("SimpleDateFormat")
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {

        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * 根据屏幕宽度确定图片显示的高度
     *
     * @param screenWidth
     * @param bitmap
     * @return
     */
    public static int getScreenPicHeight(int screenWidth, Bitmap bitmap) {
        int picWidth = bitmap.getWidth();
        int picHeight = bitmap.getHeight();
        int picScreenHeight = 0;
        picScreenHeight = (screenWidth * picHeight) / picWidth;
        return picScreenHeight;
    }
    /**
     *
     * 根据屏幕宽度显示轮播图的高度
      */
    public  static  int getTopBannerHeight(int screenWidth){

        return (screenWidth*3)/5;
    }
    public  static  int getSmallBannerHeight(int screenWidth){
        return screenWidth/5;
    }
    /**
     * 读取流中的数据
     *
     * @param is
     * @return
     * @throws Exception
     */
    public static String readFromStream(InputStream is) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        is.close();
        String result = bos.toString();
        bos.close();
        return result;
    }

    /**
     * 将Bitmap转换成Base64字符串
     *
     * @param bmp
     * @return
     */
    public static String bitmap2StrByBase64(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        return Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT);
    }

    /**
     * 将String转换成Base64字符串
     *
     * @return
     */
    public static String encodeBase64(String str) {
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
    }

    /**
     * 获取sha1值
     *
     * @param val
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static byte[] sha1(String val) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        byte[] data = val.getBytes("utf-8");
        MessageDigest mDigest = MessageDigest.getInstance("sha1");
        return mDigest.digest(data);
    }


    /**
     * 判断某个服务是否正在运行的方法
     *
     * @param mContext
     * @param serviceName 是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public static boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(40);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }


    /**
     * 判读手机网络是否是wifi
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        return info != null && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /*
      * 获得应用版本名称
      */
    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            // 获得清单文件的信息
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取最大音量
     *
     * @param context
     * @return
     */
    public static int getMaxVolume(Context context) {
        return ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE))
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * 获取当前音量
     *
     * @param context
     * @return
     */
    public static int getCurVolume(Context context) {
        return ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE))
                .getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * 设置当前音量
     *
     * @param context
     * @param index
     */
    public static void setCurVolume(Context context, int index) {
        ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE))
                .setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
    }

    /**
     * 手机号验证
     *
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone, boolean toast) {
        if (TextUtils.isEmpty(phone)) {
            if (toast) ToastUtils.showToast("手机号不能为空");
            return false;
        }
        if (phone.length() != 11 ){
            if (toast) ToastUtils.showToast("手机号格式不对");
            return false;
        }
//        if (phone.length() != 11 || !phone
//                .matches("^((13)|(14)|(15)|(17)|(18))\\d{9}$")) {
//            if (toast) ToastUtils.showToast("手机号格式不对");
//            return false;
//        }
        return true;
    }

    /**
     *邮箱验证
     */
    public static boolean checkEmall(String emall, boolean toast) {
        if (TextUtils.isEmpty(emall)) {
            if (toast) ToastUtils.showToast("邮箱不能为空");
            return false;
        }
        if ( !emall.matches("^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")) {
            if (toast) ToastUtils.showToast("邮箱号格式不对");
            return false;
        }
        return true;
    }

    /**
     *
     * 判断密码最少6位字母加数字组合
     * @param
     * @param
     * @return
     */
    public static boolean checkPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
             ToastUtils.showToast("密码不能为空");
            return false;
        }
        //(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}
        if (!pwd.matches("[0-9A-Za-z]{6,}")) {
            ToastUtils.showToast("密码格式不正确");
            return false;
        }
        return true;
    }
    public static boolean checkCode(String code) {
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showToast("验证码不能为空");
            return false;
        }
        if (!code.matches("(?<![0-9])([0-9]{6})(?![0-9])")) {
            ToastUtils.showToast("验证码格式不正确");
            return false;
        }
        return true;
    }




    /**
     * 手机号验证
     *
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {

        return checkPhone(phone, true);
    }
    /**
     *
     * 注册前判断格式是否正确
     *
     */
    public static boolean checkRegist(String phone,String code,String pwd) {
        if (!checkPhone(phone)) {
            //ToastUtils.showToast("手机号格式不正确");
            return false;
        }
        if (!checkPwd(pwd)) {
            //ToastUtils.showToast("密码格式不正确");
            return false;
        }
        if (!checkCode(code)) {
            //ToastUtils.showToast("密码格式不正确");
            return false;
        }
        return true;
    }

    /**
     *
     * 登录前判断账号和密码
     * @param phone
     * @param pwd
     * @return
     */
    public static boolean checkLogin(String phone,String pwd) {
        if (!checkPhone(phone)) {
            //ToastUtils.showToast("手机号格式不正确");
            return false;
        }
        if (!checkPwd(pwd)) {
            //ToastUtils.showToast("密码格式不正确");
            return false;
        }

        return true;
    }

    /**
     *
     *
     * 验证新密码和旧密码格式
     * @param old
     * @param pwd
     * @return
     */
    public static boolean checkOldPwd(String old,String pwd) {
        if (!checkPwd(old)) {
            //ToastUtils.showToast("手机号格式不正确");
            return false;
        }
        if (!checkPwd(pwd)) {
            //ToastUtils.showToast("密码格式不正确");
            return false;
        }

        return true;
    }
    /**
     * 设置屏幕的背景透明度
     *
     * @param bgAlpha 0.0-1.0
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

}
