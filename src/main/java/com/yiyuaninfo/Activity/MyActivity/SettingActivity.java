package com.yiyuaninfo.Activity.MyActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.App;
import com.yiyuaninfo.Interface.LogOutBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CacheUtil;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.FontSliderBar;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener,CommonPopupWindow.ViewInterface {
    LinearLayout layoutAddress;
    LinearLayout layoutMemorry;
    Button layoutBack;
    LinearLayout layoutTextSIZE;
    LinearLayout layoutaccount;
    LinearLayout layoutnotice;
    LinearLayout layoutdingyue;
    TextView tv,tvfalse,tvtrue;
    TextView  tvHuanCun ,tvSize;

    int yourChoice;
    private int  numsize;
    private CheckBox  checkBoxwifi;

    private boolean  ischeck;
    private CommonPopupWindow popupWindow ,popupWindowhuancun ;

    private User.UserinfoBean userinfoBean;

    private FontSliderBar  sliderBar;

    private String path;
    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        path=this.getExternalFilesDir("yiyuaninfo").getPath()+"/myhead.jpg";
        setToolBarTitle("设置");
        userinfoBean = SPUtil.getUser(this);
        layoutAddress = (LinearLayout) findViewById(R.id.ll_setting_address);
        layoutMemorry = (LinearLayout) findViewById(R.id.ll_setting_Memorry);
        layoutTextSIZE = (LinearLayout) findViewById(R.id.ll_setting_textsize);
        layoutaccount = (LinearLayout) findViewById(R.id.ll_setting_account);
        layoutnotice = (LinearLayout) findViewById(R.id.ll_setting_notice);
        layoutdingyue = (LinearLayout) findViewById(R.id.ll_setting_dy);
        layoutBack = (Button) findViewById(R.id.btn_Back);
        tv = (TextView) findViewById(R.id.tv_settong_youmeng);
        tvHuanCun = (TextView) findViewById(R.id.tv_setting_huancun);
        tvSize= (TextView) findViewById(R.id.tv_setting_size);


        switch (SPUtil.getData(SettingActivity.this,"size","0")){
            case "0":
                tvSize.setText("标准");
                break;
            case  "1":
                tvSize.setText("大");

                break;
            case  "2":
                tvSize.setText("极大");

                break;
            case  "3":
                tvSize.setText("超级大");

                break;
        }



        checkBoxwifi=(CheckBox)findViewById(R.id.checkbox_wifi);
        ischeck=SPUtil.getData(SettingActivity.this,"ischeck",true);
        checkBoxwifi.setChecked(ischeck);

        checkBoxwifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  SPUtil.setData(SettingActivity.this,"ischeck",isChecked);
            }
        });

        layoutAddress.setOnClickListener(this);
        layoutMemorry.setOnClickListener(this);
        layoutTextSIZE.setOnClickListener(this);
        layoutaccount.setOnClickListener(this);
        layoutnotice.setOnClickListener(this);
        layoutdingyue.setOnClickListener(this);
        layoutBack.setOnClickListener(this);

        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage uMessage) {
                for (Map.Entry<String, String> entry : uMessage.extra.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Log.d("添加的参数", key + value);
                }
            }
        };

        getCache();
    }

    private void getCache() {

        try {
            tvHuanCun.setText(CacheUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_address:
                startActivity(new Intent(this, AddAddressActivity.class));
                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.ll_setting_Memorry:

             showpopwindow();


                break;
            case R.id.ll_setting_textsize:
                showSingleChoiceDialog();
                break;
            case R.id.ll_setting_account:
                startActivity(new Intent(this, AccountActivity.class));
                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.ll_setting_notice:

                break;
            case R.id.ll_setting_dy:
                startActivity(new Intent(this, SubscriptionActivity.class));
                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;

            case R.id.btn_Back:
                final Map<String, String> params = new HashMap<>();
                params.put("act", "logout");
                params.put("userid", userinfoBean.getUserid());
                params.put("mobile", userinfoBean.getMobile());
                RetrofitUtil.getretrofit().create(LogOutBiz.class).getData(params).enqueue(new Callback<State>() {
                    @Override
                    public void onResponse(Call<State> call, Response<State> response) {
                        Log.d("退出",response.body().getState());
                        if (response.body().getState().equals("1")) {

                            delFile(path);
                            SPUtil.Exit(SettingActivity.this);
                            ToastUtils.showToast("退出成功");
                            finish();

                        }
                    }

                    @Override
                    public void onFailure(Call<State> call, Throwable t) {

                    }
                });

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bun = getIntent().getExtras();
        if (bun != null) {
            Set<String> keySet = bun.keySet();
            for (String key : keySet) {
                String value = bun.getString(key);
                Log.d("添加的参数", value);
                ToastUtils.showToast(value);
                tv.setText("key：" + key + "   value:" + value);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }




    /**
     * 弹出分享壹元服务
     */
    public void showAll() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popwindow_size, null);
        sliderBar = (FontSliderBar)upView.findViewById(R.id.font);
        sliderBar.setTickCount(4).setTickHeight(30).setBarColor(Color.BLACK)
                .setTextColor(Color.BLACK).setTextPadding(20).setTextSize(20)
                .setThumbRadius(20).setThumbColorNormal(Color.BLACK).setThumbColorPressed(Color.BLACK)
                .withAnimation(false).applay();

        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_size)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAtLocation(layoutAddress, Gravity.CENTER, 0, 0);





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
    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId) {
            case R.layout.popwindow_huancun:

                tvfalse = (TextView) view.findViewById(R.id.tv_pop_umeng_false);
                tvtrue = (TextView) view.findViewById(R.id.tv_pop_umeng_true);
                tvfalse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindowhuancun.dismiss();
                    }
                });
                tvtrue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CacheUtil.clearAllCache(SettingActivity.this);
                        popupWindowhuancun.dismiss();
                      ToastUtils.showToast("缓存已清理");
                        tvHuanCun.setText("0K");
                    }
                });
        }
    }

    private void showpopwindow() {

        if (popupWindowhuancun != null && popupWindowhuancun.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popwindow_huancun,null);
        //测量View的宽高
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowhuancun = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_huancun)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        popupWindowhuancun.showAtLocation(layoutAddress, Gravity.CENTER, 0, 0);

    }

    private void showSingleChoiceDialog(){
        final String[] items = { "标准","大","极大","超级大" };
        yourChoice = -1;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(SettingActivity.this);
        singleChoiceDialog.setTitle("字体大小");
        // 第二个参数是默认选项，此处设置为0
        numsize=Integer.parseInt(SPUtil.getData(SettingActivity.this,"size","0"));
        singleChoiceDialog.setSingleChoiceItems(items, numsize,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice != -1) {
//                            Toast.makeText(SettingActivity.this,
//                                    "你选择了" + items[yourChoice]+"+++"+yourChoice,
//                                    Toast.LENGTH_SHORT).show();
                            SPUtil.setData(SettingActivity.this,"size",yourChoice+"");

                            switch (yourChoice){
                                case  0:
                                    tvSize.setText("标准");
                                    break;
                                case  1:
                                    tvSize.setText("大");

                                    break;
                                case  2:
                                    tvSize.setText("极大");

                                    break;
                                case  3:
                                    tvSize.setText("超级大");

                                    break;

                            }

                        }
                    }
                });
        singleChoiceDialog.show();
    }

}
