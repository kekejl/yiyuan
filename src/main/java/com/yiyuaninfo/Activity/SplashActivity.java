package com.yiyuaninfo.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;


import com.yiyuaninfo.R;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.SharedPreferencesUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Eric on 2017/1/21.
 */

public class SplashActivity extends AppCompatActivity {

    private static final int ANIMATION_DURATION = 2000;
    private static final float SCALE_END = 1.13F;

//    private static final int[] SPLASH_ARRAY = {
//            R.drawable.splash0,
//            R.drawable.splash1,
//            R.drawable.splash2,
//            R.drawable.splash3,
//            R.drawable.splash4,
//
//            R.drawable.splash6,
//            R.drawable.splash7,
//            R.drawable.splash8,
//            R.drawable.splash9,
//            R.drawable.splash10,
//            R.drawable.splash11,
//            R.drawable.splash12,
//            R.drawable.splash13,
//            R.drawable.splash14,
//            R.drawable.splash15,
//            R.drawable.splash16,
//    };

    @BindView(R.id.iv_splash)
    ImageView mIvSplash;
    @BindView(R.id.btn_splash)
    Button   button;
    //时间倒计时
    private CountDownTimer  countDownTimer=new CountDownTimer(4000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            button.setText("跳过(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            button.setText("跳过(" + 0 + "s)");
            CommonUtil.goAactivity(SplashActivity.this,MainActivity.class);
            //®MainActivity.start(SplashActivity.this);
            SplashActivity.this.finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash1);
        ButterKnife.bind(this);

        // 判断是否是第一次开启应用
        boolean isFirstOpen = SharedPreferencesUtil.getBoolean(this, SharedPreferencesUtil.FIRST_OPEN, true);
        // 如果是第一次启动，则先进入功能引导页
        if (isFirstOpen) {
            //Intent intent = new Intent(this, GuideActivity.class);
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        //生成随机数
        Random r = new Random(SystemClock.elapsedRealtime());
       // mIvSplash.setImageResource(SPLASH_ARRAY[r.nextInt(SPLASH_ARRAY.length)]);
        mIvSplash.setImageResource(R.drawable.start1);
        //animateImage();
        countDownTimer.start();

    }

    private void animateImage() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIvSplash, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIvSplash, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                MainActivity.start(SplashActivity.this);
                SplashActivity.this.finish();
            }
        });
    }
    @OnClick(R.id.btn_splash)
    public   void  onClick(){
        CommonUtil.goAactivity(SplashActivity.this,MainActivity.class);
        SplashActivity.this.finish();
        countDownTimer.cancel();

    }

}
