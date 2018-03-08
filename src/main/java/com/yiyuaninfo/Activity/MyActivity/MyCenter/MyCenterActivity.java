package com.yiyuaninfo.Activity.MyActivity.MyCenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UmengTool;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.BuildConfig;
import com.yiyuaninfo.Interface.FileUploadService;
import com.yiyuaninfo.Interface.XiuGaiUserBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.CircleImageView;
import com.yiyuaninfo.entity.Head;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.entity.UserXiuGai;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2017/2/24.
 */

public class MyCenterActivity extends BaseActivity implements View.OnClickListener, CommonPopupWindow.ViewInterface {
    private LinearLayout head;
    private LinearLayout nickname;
    private LinearLayout phone;
    private LinearLayout guling;
    private LinearLayout zijin;
    private LinearLayout email;
    private LinearLayout qq;
    private LinearLayout weixin;
    private LinearLayout weibo;
    private TextView tvnickname;
    private TextView tvphone;
    private TextView tvguling;
    private TextView tvzijin;
    private TextView tvemail;
    private TextView tvqq;
    private TextView tvweixin;
    private TextView tvweibo;
    private CircleImageView imagehead;
    private User.UserinfoBean userinfoBean;
    private CommonPopupWindow popupWindow;
    private String  stringQQ,stringSNA,stringWEIXIN;
    private Bitmap bitmap;// 头像Bitmap
    private static File path;
    //"/sdcard/yiyuan/myhead";// sd路径
    private static final int PER_REQUEST_CODE = 2;
    //"/storage/emulated/0/DCIM/Camera/IMG_20170713_141153.jpg"
    private static String profilePicFileName = "/myhead.jpg";//图片名称
    private Uri imageUri;//图片路径Uri
    private UMShareAPI  mShareAPI;
    @Override
    protected int getContentView() {
        return R.layout.activity_mycenter;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("个人中心");
        path = this.getExternalFilesDir("yiyuaninfo");

        //path = new File("/sdcard/yiyuan/myhead/");
        // Log.d("图片路径",path.toString());
        intview();
        setData();

       // UmengTool.getSignature(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("执行了", "onStart ");
    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.d("执行了", "onResume ");
        setData();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setData();
        Log.d("执行了", "onRestart ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("执行了", "onPause ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("执行了", "onStop ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("执行了", "onDestroy ");

    }

    private void setData() {
        if (SPUtil.isLogin(this)) {
            userinfoBean = SPUtil.getUser(this);
            Log.d("返回用户信息", userinfoBean.toString());
        }
        // path = this.getExternalCacheDir();

        //Bitmap bt = BitmapFactory.decodeFile(path+profilePicFileName);// 从SD卡中找头像，转换成Bitmap
        Bitmap bt = BitmapFactory.decodeFile(path.getPath() + "/myhead.jpg");// 从SD卡中找头像，转换成Bitmap

        if (bt != null) {

            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            imagehead.setImageDrawable(drawable);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
            if (SPUtil.isLogin(this)) {

                EasyHttp.downLoad(userinfoBean.getAvatar())
                        .savePath(path.getPath())
                        .saveName("myhead.jpg")
                        .execute(new DownloadProgressCallBack<String>() {
                            @Override
                            public void onStart() {


                            }

                            @Override
                            public void onError(ApiException e) {

                            }

                            @Override
                            public void update(long bytesRead, long contentLength, boolean done) {
                                //ToastUtils.showToast("下载完成");
                                //Log.d("下载完成", "下载完成");


                            }

                            @Override
                            public void onComplete(String path) {
                                File mfile = new File(path);
                                Log.d("下载头像地址", path);

                                if (mfile.exists()) {
                                    Bitmap bm = BitmapFactory.decodeFile(path);
                                    Drawable drawable = new BitmapDrawable(bm);// 转换成drawable
                                    imagehead.setImageDrawable(drawable);

                                }


                            }
                        });
            } else {
                imagehead.setImageResource(R.drawable.head_default);
            }

        }

        if (userinfoBean == null) {
            tvnickname.setText("");
            tvphone.setText("");
            tvguling.setText("");
            tvzijin.setText("");
            tvemail.setText("");
            tvqq.setText("");
            tvweibo.setText("");
            tvweixin.setText("");
        } else {
            if (userinfoBean.getUsername() == null) {
                tvnickname.setText("");
            } else {
                tvnickname.setText(userinfoBean.getUsername());
            }
            if (userinfoBean.getMobile() == null) {
                tvphone.setText("");
            } else {
                tvphone.setText(userinfoBean.getMobile());
            }
            if (userinfoBean.getGuling() == null) {
                tvguling.setText("");
            } else {
                tvguling.setText(userinfoBean.getGuling() + "年");
            }
            if (userinfoBean.getCapital() == null) {
                tvzijin.setText("");
            } else {
                tvzijin.setText(userinfoBean.getCapital() + "万");
            }
            if (userinfoBean.getEmail() == null) {
                tvemail.setText("");
            } else {
                tvemail.setText(userinfoBean.getEmail());
            }


            if(userinfoBean.getQqnum()!=null){

            if (userinfoBean.getQqnum().equals("")) {
                tvqq.setText("未绑定");
            } else {
                tvqq.setText("已绑定");
            }
            }

            if(userinfoBean.getWeibo()!=null){
                if (userinfoBean.getWeibo().length()==0) {
                tvweibo.setText("未绑定");
            } else {
                tvweibo.setText("已绑定");
            }
            }



            if(userinfoBean.getWeixin()!=null){

            if (userinfoBean.getWeixin().length()==0) {
                tvweixin.setText("未绑定");
            } else {
                tvweixin.setText("已绑定");
            }
            }

        }
    }

    private void intview() {
        mShareAPI=UMShareAPI.get(this);
        head = (LinearLayout) findViewById(R.id.ll_center_head);
        nickname = (LinearLayout) findViewById(R.id.ll_center_nickname);
        phone = (LinearLayout) findViewById(R.id.ll_center_phone);
        guling = (LinearLayout) findViewById(R.id.ll_center_guling);
        zijin = (LinearLayout) findViewById(R.id.ll_center_zijin);
        qq = (LinearLayout) findViewById(R.id.ll_center_qq);
        weibo = (LinearLayout) findViewById(R.id.ll_center_weibo);
        weixin = (LinearLayout) findViewById(R.id.ll_center_weixin);
        email = (LinearLayout) findViewById(R.id.ll_center_email);
        head.setOnClickListener(this);
        nickname.setOnClickListener(this);
        phone.setOnClickListener(this);
        guling.setOnClickListener(this);
        zijin.setOnClickListener(this);
        qq.setOnClickListener(this);
        email.setOnClickListener(this);
        weixin.setOnClickListener(this);
        weibo.setOnClickListener(this);
        tvnickname = (TextView) findViewById(R.id.tv_center_nickname);
        tvphone = (TextView) findViewById(R.id.tv_center_phone);
        tvguling = (TextView) findViewById(R.id.tv_center_guling);
        tvzijin = (TextView) findViewById(R.id.tv_center_zijin);
        tvqq = (TextView) findViewById(R.id.tv_center_qq);
        tvweibo = (TextView) findViewById(R.id.tv_center_weibo);
        tvweixin = (TextView) findViewById(R.id.tv_center_weixin);
        tvemail = (TextView) findViewById(R.id.tV_Center_email);
        imagehead = (CircleImageView) findViewById(R.id.image_center_head);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_center_head:
                showAll();
                break;
            case R.id.ll_center_nickname:
                CommonUtil.goAactivity(MyCenterActivity.this, XiuGaiUserActivity.class, "key", "username","name","昵称","value",userinfoBean.getUsername());
                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.ll_center_guling:
                CommonUtil.goAactivity(MyCenterActivity.this, XiuGaiUserActivity.class, "key", "guling","name","股龄(年)","value",userinfoBean.getGuling());
                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.ll_center_zijin:
                CommonUtil.goAactivity(MyCenterActivity.this, XiuGaiUserActivity.class, "key", "capital","name","资金量(万)","value",userinfoBean.getCapital());
                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.ll_center_email:
                CommonUtil.goAactivity(MyCenterActivity.this, XiuGaiUserActivity.class, "key", "email","name","邮箱","value",userinfoBean.getEmail());
                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.ll_center_qq:
                if(tvqq.getText().equals("未绑定")){

               getPlat(SHARE_MEDIA.QQ);
                }else {
                    ToastUtils.showToast("您已绑定");
                }


                break;
            case R.id.ll_center_weibo:

                if(tvweibo.getText().equals("未绑定")){

                    getPlat(SHARE_MEDIA.SINA);
                }else {
                    ToastUtils.showToast("您已绑定");
                }



                break;
            case R.id.ll_center_weixin:

                if(tvweixin.getText().equals("未绑定")){

                    getPlat(SHARE_MEDIA.WEIXIN);
                }else {
                    ToastUtils.showToast("您已绑定");
                }


                break;
            case R.id.tv_photo:
                //调用相册
                photo();
                break;
            case R.id.tv_picture:
                //调用相机
                picture();
                break;
            case R.id.tv_cancle:
                popupWindow.dismiss();
                break;
        }
    }

    /**
     *
     * 获取第三方平台的信息
     *
     *
     */
    private void getPlat(SHARE_MEDIA  type) {


        mShareAPI.getPlatformInfo(MyCenterActivity.this, type, umAuthListener);





    }


    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            if(platform==SHARE_MEDIA.QQ){

             stringQQ=data.get("openid");
                upData("qqnum",stringQQ);

            }else if(platform==SHARE_MEDIA.SINA){
              stringSNA=data.get("uid");

                Log.d("微博账号",stringSNA);

                upData("weibo",stringSNA);


            }else if(platform==SHARE_MEDIA.WEIXIN){
             stringWEIXIN=data.get("openid");
                upData("weixin",stringWEIXIN);


            }






        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {

        }
    };

    private void upData(final String key, String value){

        Map<String, String> params = new HashMap<>();

        params.put("act",key);
        params.put(key,value);
        params.put("userid", SPUtil.getUser(this).getUserid());
        RetrofitUtil.getretrofit().create(XiuGaiUserBiz.class).getData(params).enqueue(new Callback<UserXiuGai>() {
            @Override
            public void onResponse(Call<UserXiuGai> call, Response<UserXiuGai> response) {
                Log.d("修改用户信息返回的数据",response.body().getStatus()+"\n"+response.body().getUserinfo());
                if(response.body().getStatus().equals("1")){
                    if(SPUtil.setUser(MyCenterActivity.this, response.body().getUserinfo())){
                        //ToastUtils.showToast("修改成功");

                        if(key.equals("qqnum")){
                            tvqq.setText("已绑定");
                        }else if(key.equals("weibo")){
                            tvweibo.setText("已绑定");
                        }else if(key.equals("weixin")){
                            tvweixin.setText("已绑定");

                        }


                        if(response.body().getAddintegral()!=null&&!response.body().getAddintegral().equals("")){
                            ToastUtils.showToast("成功，+"+response.body().getAddintegral()+"积分");

                        }
                    }
                }else {
                    ToastUtils.showToast("修改失败");

                }
            }

            @Override
            public void onFailure(Call<UserXiuGai> call, Throwable t) {

            }
        });
    }


    /*
    *
    * 调用相机
    * */
    private void picture() {
        initFile();//定义用户存储拍摄图片的文件
        // 启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, 2);
    }

    //创建文件并获取其路径的Uri
    private void initFile() {
        // 创建File对象，用于存储拍照后生成的图片
        File outputImage = new File(path.getParent() + profilePicFileName);//使用应用关联缓存目录存放图片
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 24) {
            //7.0之前，调用Uri的FromFIle()方法将File对象转换为Uri对象，这个Uri对象标识着user_profile_picture.jpg这张图片的本地真实路径
            imageUri = Uri.fromFile(outputImage);
        } else {
            //7.0之后，直接使用本地真实路径的Uri被认为是不安全的，会抛出FileUriExposed异常。
            //因此使用FileProvider的getUriForFile方法将Uri转换成一个封装过的Uri对象
            //FileProvider是一个特殊的内容提供器，我们需要在Manifest中对其进行定义(具体请查询Manifest中<provider>..<provider/>)
            imageUri = FileProvider.getUriForFile(MyCenterActivity.this, BuildConfig.APPLICATION_ID + ".fileprovider", outputImage);
        }
    }

    /*
    *
    * 调用相册选取
    * */
    private void photo() {
        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent1, 1);
        popupWindow.dismiss();
    }

    //全屏弹出
    public void showAll() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popwindow_head, null);
        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_head)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAtLocation(head, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        TextView photo = (TextView) view.findViewById(R.id.tv_photo);
        TextView picture = (TextView) view.findViewById(R.id.tv_picture);
        TextView cancle = (TextView) view.findViewById(R.id.tv_cancle);
        photo.setOnClickListener(this);
        picture.setOnClickListener(this);
        cancle.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {






        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                    Log.d("相册", data.getData().toString());
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    //File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    //cropPhoto(Uri.fromFile(temp));// 裁剪图片
                    //cropPhoto(data.getData());
                    try {
                        // 将拍摄的照片存储起来
                        Bitmap bitmap = BitmapFactory.decodeStream(MyCenterActivity.this.getContentResolver().openInputStream(imageUri));
                        setPicToView(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //打印展示uri是哪种类型
                    Log.d("Result", "TAKE_PHOTO: " + imageUri.getScheme());//imageUri已转化为content类型，使用普通方式处理，直接传入即可
                    cropPhoto(imageUri);

                }

                break;
            case 3:
                if (resultCode == RESULT_OK) {


                    if (data != null) {
                        Bundle extras = data.getExtras();
                        bitmap = extras.getParcelable("data");
                        if (bitmap != null) {
                            /**
                             * 上传服务器代码
                             */
                            setPicToView(bitmap);// 保存在SD卡中


                            headupload();

                            imagehead.setImageBitmap(bitmap);// 用ImageView显示出来
                        }
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);

        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);




    }

    private void headupload() {
        File file = new File(path.getPath() + profilePicFileName);
        Log.v("上传成功 ", file+"");

        ///storage/emulated/0/Android/data/com.yiyuaninfo/files/myhead.jpg

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
                .addFormDataPart("userid", userinfoBean.getUserid());//ParamKey.TOKEN 自定义参数key常量类，即参数名
        RequestBody imageBody = RequestBody.create(MediaType.parse("image/png"), file);
        builder.addFormDataPart("file", file.getName(), imageBody);//imgfile 后台接收图片流的参数名

        List<MultipartBody.Part> parts = builder.build().parts();
        RetrofitUtil.getretrofit().create(FileUploadService.class).upload(parts).enqueue(new Callback<Head>() {
            @Override
            public void onResponse(Call<Head> call, Response<Head> response) {
                Log.v("上传成功 ", "success");
                Log.v("上传成功 ", response.toString() + "\n" + response.body().getState() + "\n" +
                        response.body().getUserhead());

            }

            @Override
            public void onFailure(Call<Head> call, Throwable t) {

            }
        });


    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {

        FileOutputStream b = null;
        String profilePicFileWholePath = path.getPath() + "/myhead.jpg";// 图片完整路径
        File outputImage = new File(profilePicFileWholePath);//使用了与之前保存拍照图片一样的文件保存裁剪后的图片，可以用不同文件将完整图片和剪裁后的都保存下来
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            b = new FileOutputStream(outputImage);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件 100表示压缩率为
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
