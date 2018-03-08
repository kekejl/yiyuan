package com.yiyuaninfo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.util.ConstanceValue;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.FlowLayoutUtil;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.view.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.yiyuaninfo.util.CommonUtils.getResources;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsEntity.HangqingBean,BaseViewHolder> {
    private  FlowLayout flowLayout;
    private List<String>  list=new ArrayList<>();
    private Context context;
    public NewsAdapter(Context  context,List<NewsEntity.HangqingBean> data) {
        super(R.layout.item_news, data);
        Log.d("111111111", data.toString());
        this.context=context;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsEntity.HangqingBean news) {
       //防止复用View没有改变主题，重新设置
        // ColorUiUtil.changeTheme(baseViewHolder.convertView, mContext.getTheme());


        setGone(baseViewHolder);
        if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_LIFT)) {

            if (!TextUtils.isEmpty(news.getPicurl())) {
                //单图片文章
                if(news.getPicurl().startsWith("http")){
                    ImageLoaderUtils.displayImage(news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.ivRightImg1));

                }else {
                    ImageLoaderUtils.displayImage( Constants.Home3+news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.ivRightImg1));


                }

                baseViewHolder.setVisible(R.id.rlRightImg, true)
                        .setVisible(R.id.viewFill, true);

            }





        } else if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_THREE)) {

            //3张图片
            baseViewHolder.setVisible(R.id.llCenterImg, true);
            baseViewHolder.setVisible(R.id.tv_item_news_title,true);
            baseViewHolder.setText(R.id.tv_item_news_title, news.getTitle());
            baseViewHolder.setVisible(R.id.tvName,false);
            try {
                if(news.getPicarr().get(0).getImg().startsWith("http")){
                    ImageLoaderUtils.displayImage(news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg1));

                }else {
                    ImageLoaderUtils.displayImage( Constants.Home3+news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg1));


                }
                if(news.getPicarr().get(1).getImg().startsWith("http")){
                    ImageLoaderUtils.displayImage(news.getPicarr().get(1).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg2));

                }else {
                    ImageLoaderUtils.displayImage( Constants.Home3+news.getPicarr().get(1).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg2));


                }
                if(news.getPicarr().get(2).getImg().startsWith("http")){
                    ImageLoaderUtils.displayImage(news.getPicarr().get(2).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg3));

                }else {
                    ImageLoaderUtils.displayImage( Constants.Home3+news.getPicarr().get(2).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg3));


                }
           //     ImageLoaderUtils.displayImage(news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg1));
           //     ImageLoaderUtils.displayImage(news.getPicarr().get(1).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg2));
           //     ImageLoaderUtils.displayImage(news.getPicarr().get(2).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg3));


            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_GALLERY)) {
            baseViewHolder.setVisible(R.id.tv_item_news_title,true);
            baseViewHolder.setText(R.id.tv_item_news_title, news.getTitle());
            baseViewHolder.setVisible(R.id.tvName,false);


            //画廊类型
           // if (news.image_list == null) {
            //    ImageLoaderUtils.displayImage(Constants.Home3+news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
            //    baseViewHolder.setVisible(R.id.rlRightImg, true)
            //            .setVisible(R.id.viewFill, true);
           // } else {

            if(news.getPicarr().size()!=0){
                if(news.getPicarr().get(0).getImg().startsWith("http")){

                ImageLoaderUtils.displayBigImage(news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivBigImg));
                }else {
                    ImageLoaderUtils.displayBigImage(Constants.Home3+news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivBigImg));

                }

            }else {
                baseViewHolder.setImageResource(R.id.ivBigImg,R.drawable.image_loading);
            }
            baseViewHolder.setVisible(R.id.rlBigImg, true)
                    .setText(R.id.tvImgCount, news.getPicarr().size() + "图");
            //}

          /*  //视频类型
            ImageLoaderUtils.displayImage(news.image_url, (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
            baseViewHolder.setVisible(R.id.rlRightImg, true)
                    .setVisible(R.id.viewFill, true)
                    .setVisible(R.id.llVideo, true).setText(R.id.tvDuration, news.video_duration_str);*/
        } else if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_BIG)) {

            ImageLoaderUtils.displayImage(news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.ivBigImg));
            baseViewHolder.setVisible(R.id.rlBigImg,true);
            baseViewHolder.setVisible(R.id.tv_item_news_title,true);
            baseViewHolder.setText(R.id.tv_item_news_title, news.getTitle());
            baseViewHolder.setVisible(R.id.tvName,false);
        }
        Log.d("关键词",news.getKeywords());
        if(news.getKeywords()==null||news.getKeywords().equals("")){

            baseViewHolder.setVisible(R.id.flowlayout_news_tag,false);

        }else {
            baseViewHolder.setVisible(R.id.flowlayout_news_tag,true);
            // baseViewHolder.setText(R.id.tvKeyword,news.getKeywords());
            flowLayout=baseViewHolder.getView(R.id.flowlayout_news_tag);
            FlowLayoutUtil flowLayoutUtil=new FlowLayoutUtil(flowLayout,context);
            flowLayoutUtil.showFlowLayout(news.getKeywords());
//            String[]  tags=news.getKeywords().split(" ");
//            for (int i = 0; i <tags.length ; i++) {
//                String tag=tags[i];
//                list.add(tag);
//            }
//            Log.d("标签数据", list.toString());
//            flowLayout.removeAllViews();
//            for (int i = 0; i < list.size(); i++) {
//                final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.item_flow_layout_tag_finder, flowLayout, false);
//                tv.setBackgroundResource(R.drawable.popup_corner);
//                tv.setTextColor(getResources().getColor(R.color.colorPrimary));
//                tv.setText(list.get(i));
//                tv.setTextSize(10);
//                flowLayout.addView(tv);
//
//            }
//            list.clear();

        }
        baseViewHolder.setVisible(R.id.tv_item_news_title, false);
        baseViewHolder.setVisible(R.id.tvName, true);
        Log.d("资讯标题",news.getTitle());
        baseViewHolder.setText(R.id.tvName, news.getTitle())
                .setText(R.id.tvAuthorName, news.getSource())
                .setText(R.id.tvTime, DateUtils.getShortTime(news.getPosttime()));
               Log.d("时间",news.getPosttime()+"11111"+DateUtils.getShortTime(news.getPosttime()));

    }

    private void setGone(BaseViewHolder baseViewHolder) {
        baseViewHolder.setVisible(R.id.viewFill, false)
                .setVisible(R.id.llCenterImg, false)
                .setVisible(R.id.rlBigImg, false)
                .setVisible(R.id.llVideo, false)
                .setVisible(R.id.flowlayout_news_tag,false)
                .setVisible(R.id.rlRightImg, false);

    }
}

