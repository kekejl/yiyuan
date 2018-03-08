package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.yiyuaninfo.R;
import com.yiyuaninfo.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eric on 2017/1/21.
 */

public class WelcomeActivity extends AppCompatActivity  {
    private ViewPager  viewPager;
    private  ImageView  imageView;
    private  ImageView  into;
    private List<View> viewList;
    private static final int GUIDE_PAGE_COUNT = 3;
    //private int[] imgResArr = new int[]{R.drawable.a, R.drawable.c, R.drawable.b};
    private int[] imgResArr = new int[]{R.drawable.welcome1, R.drawable.welcome2, R.drawable.welcome3};
    private int duration;
    private Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 2:
                    into.setVisibility(View.VISIBLE);
                    into.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(WelcomeActivity.this, SplashActivity.class);
                            startActivity(intent);
                            SharedPreferencesUtil.putBoolean(WelcomeActivity.this, SharedPreferencesUtil.FIRST_OPEN, false);
                            finish();
                        }
                    });
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        viewPager=(ViewPager)findViewById(R.id.vp_guide);

        viewList = new ArrayList<View>(GUIDE_PAGE_COUNT);
        for (int i = 0; i < GUIDE_PAGE_COUNT; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.activity_guide_item, null);
            view.setBackgroundResource(R.color.white);
            imageView=(ImageView) view.findViewById(R.id.iv_guide);
            Glide.with(this)
                    .load(imgResArr[i]).into(imageView);
//                    .listener(new RequestListener<Integer, GlideDrawable>() {
//
//                        @Override//加载出错
//                        public boolean onException(Exception arg0, Integer arg1,
//                                                   Target<GlideDrawable> arg2, boolean arg3) {
//                            return false;
//                        }
//
//                        @Override//加载完成
//                        public boolean onResourceReady(GlideDrawable resource,
//                                                       Integer model, Target<GlideDrawable> target,
//                                                       boolean isFromMemoryCache, boolean isFirstResource) {
//                            // 计算动画时长
//                            GifDrawable drawable = (GifDrawable) resource;
//                            GifDecoder decoder = drawable.getDecoder();
//                            for (int i = 0; i < drawable.getFrameCount(); i++) {
//                                duration += decoder.getDelay(i);
//                            }
//                            //发送延时消息，通知动画结束
//                            handler.sendEmptyMessageDelayed(1,
//                                    duration);
//                            return false;
//                        }
//                    })
 //                   .into(new GlideDrawableImageViewTarget(imageView, 1));
            viewList.add(view);

        }

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              imageView =(ImageView) viewList.get(position).findViewById(R.id.iv_guide);
              into =(ImageView) viewList.get(position).findViewById(R.id.iv_into);
                Glide.with(WelcomeActivity.this).load(imgResArr[position]).into(new GlideDrawableImageViewTarget(imageView, 1));
              if(position==2){
                  Glide.with(WelcomeActivity.this)
                          .load(imgResArr[2]).into(imageView);
                  Message message=new Message();
                                  message.what=2;
                                  handler.sendMessage(message);
//                          .listener(new RequestListener<Integer, GlideDrawable>() {
//
//                              @Override//加载出错
//                              public boolean onException(Exception arg0, Integer arg1,
//                                                         Target<GlideDrawable> arg2, boolean arg3) {
//                                  return false;
//                              }
//
//                              @Override//加载完成
//                              public boolean onResourceReady(GlideDrawable resource,
//                                                             Integer model, Target<GlideDrawable> target,
//                                                             boolean isFromMemoryCache, boolean isFirstResource) {
//                                  // 计算动画时长
//                                  GifDrawable drawable = (GifDrawable) resource;
//                                  GifDecoder decoder = drawable.getDecoder();
//                                  for (int i = 0; i < drawable.getFrameCount(); i++) {
//                                      duration += decoder.getDelay(i);
//                                  }
//                                  //发送延时消息，通知动画结束
//                                  Message message=new Message();
//                                  message.what=2;
//                                  handler.sendMessage(message);
//                                  return false;
//                              }
//                          })
//                          .into(new GlideDrawableImageViewTarget(imageView, 1));

              }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
