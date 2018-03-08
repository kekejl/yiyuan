package com.yiyuaninfo.Activity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.entity.PicarrBean;
import com.yiyuaninfo.photoview.HackyViewPager;
import com.yiyuaninfo.photoview.PhotoView;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaocongcong on 2017/8/24.
 */

public class NewsImageActivity extends BaseActivity {
    private HackyViewPager mViewPager;
    //显示页数和当前页数
    private TextView picture_iv_index;
    //返回按钮
    private ImageView picture_iv_back;
    private List<PicarrBean> list = new ArrayList<>();
    private LinearLayout  linearLayout;
    @Override
    protected int getContentView() {
        return R.layout.activity_news_image_pager;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isshowToolBar(false);
        list = (List<PicarrBean>) getIntent().getSerializableExtra("PicarrBean");
        Log.d("接收图集的数据", list.toString());
        Log.d("接收图集的数据", "111");
        Logger.t("接收图集的数据").d(list);
        initView();
        linearLayout=(LinearLayout)findViewById(R.id.ll_newsimage);
        initParams();
    }

    //初始化布局控件
    private void initView() {
        Log.d("接收图集的数据", "222");

        mViewPager = (HackyViewPager) findViewById(R.id.photo_vp);
        picture_iv_back = (ImageView) findViewById(R.id.picture_iv_back);
        picture_iv_index = (TextView) findViewById(R.id.picture_iv_index);
    }

    //初始化参数
    private void initParams() {
        //picture_iv_index.setText("1/" + images.length + descs[0]);
        picture_iv_index.setText("1/" + list.size() + list.get(0).getDescription()
        );
        // 绑定适配器
        mViewPager.setAdapter(new ViewPagerAdapter());
        //设置可以滑动监听(viewpager改变的时候调用)
        mViewPager.setOnPageChangeListener(new ViewPagerChangeListener());
        mViewPager.setCurrentItem(0);
        //设置返回监听事件
        picture_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsImageActivity.this.finish();

            }
        });



    }

    // 查看大图viewpager适配器
    private class ViewPagerAdapter extends PagerAdapter {

        @SuppressLint("InflateParams")
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = getLayoutInflater().inflate(R.layout.item_news_picture, null);
            PhotoView picture_iv_item = (PhotoView) view.findViewById(R.id.picture_iv_item);
            // 给imageview设置一个tag，保证异步加载图片时不会乱序
            //picture_iv_item.setImageURI(imageUrls[position]);
            // picture_iv_item.setImageURI(new Uri(imageUrls[position]) );
            if(list.get(position).getImg().startsWith("http")){

            ImageLoaderUtils.displayAvatar(list.get(position).getImg(), picture_iv_item);
            }else {
                ImageLoaderUtils.displayAvatar(Constants.Home3+list.get(position).getImg(), picture_iv_item);

            }
            //Glide.with(NewsImageActivity.this).load(imageUrls[position]).into(picture_iv_item);
            //把view加载到父容器中
            container.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearLayout.setVisibility(View.GONE);
                }
            });
            return view;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

    }


    // viewpager切换监听器
    private class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            linearLayout.setVisibility(View.VISIBLE);

            //设置文字
            picture_iv_index.setText((arg0 + 1) + "/" + list.size() + " " + list.get(0 + arg0).getDescription());
        }

    }

}
