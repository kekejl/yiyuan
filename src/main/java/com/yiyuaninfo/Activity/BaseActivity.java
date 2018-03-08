package com.yiyuaninfo.Activity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.message.PushAgent;
import com.yiyuaninfo.ActivityCollector;
import com.yiyuaninfo.Fragment.BaseFragment;
import com.yiyuaninfo.Listener.PermissionListener;
import com.yiyuaninfo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/27.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.image_back)
    ImageView    back;
    @BindView(R.id.viewContent)
    FrameLayout viewContent;
    @BindView(R.id.tvTitle)
    TextView   tvTitle;
    @BindView(R.id.layoutBase)
    LinearLayout  layoutBase;
    private static PermissionListener mListener;
    protected BaseFragment currentFragment;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity","onCreate");

        setContentView(R.layout.activity_base);
       // PushAgent.getInstance(this).onAppStart();
        /*toolbar=(Toolbar)findViewById(R.id.toolbarBase);
        viewContent=(FrameLayout)findViewById(R.id.view_content);
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        layoutBase=(LinearLayout)findViewById(R.id.layoutBase);*/
        ButterKnife.bind(this);
        //StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimaryDark));

        //设置不显示自带的Title
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //设置返回键可用，如果某个页面想隐藏掉返回键比如首页，可以调用mToolbar.setNavigationIcion(null);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //将继承BaseActivity的布局解析到FrameLayout里面
        LayoutInflater.from(BaseActivity.this).inflate(getContentView(), viewContent);
        init(savedInstanceState);

        ActivityCollector.addActivity(this);
        //translucentStatusBar();


    }


    protected  void setToolBarTitle(String title){
        if(!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
    }
    //判断是否显示ToolBar
    protected  void  isshowToolBar(Boolean  boolen){
        if(!boolen){
          layoutBase.setVisibility(View.GONE);
        }

    }

    protected abstract int getContentView();

    protected abstract void init(Bundle savedInstanceState);


//    private void translucentStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        Log.d("BaseActivity","onDestroy");

    }

    public static void requestRuntimePermission(String[] permissions, PermissionListener listener) {
        Activity topActivity = ActivityCollector.getTopActivity();
        if (topActivity == null) {
            return;
        }
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(topActivity, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 添加或者显示 fragment
     *
     *
     *
     */
    /*protected void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(R.id.view_content, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment = (BaseFragment) fragment;

    }*/

    public void doBack(View view) {
        onBackPressed();

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(0, R.anim.slide_out);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
       // MobclickAgent.onResume(this);
        Log.d("BaseActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
       // MobclickAgent.onPause(this);
        Log.d("BaseActivity","onPause");


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("BaseActivity","onPause");

    }
}
