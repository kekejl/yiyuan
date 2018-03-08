package com.yiyuaninfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.yiyuaninfo.Activity.NewsDetailActivity;
import com.yiyuaninfo.Activity.NewsImageActivity;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.InfoArrBean;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.ConstanceValue;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class HotAdapter1 extends CommonAdapter<InfoArrBean> {
    private Context  context;

    public HotAdapter1(Context context, int layoutId, List<InfoArrBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder baseViewHolder, final InfoArrBean news,int   position ) {
       //防止复用View没有改变主题，重新设置
        // ColorUiUtil.changeTheme(baseViewHolder.convertView, mContext.getTheme());
        setGone(baseViewHolder);
        if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_LIFT)) {

            if (!TextUtils.isEmpty(news.getPicurl())) {
                //单图片文章
                ImageLoaderUtils.displayImage(news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
                baseViewHolder.setVisible(R.id.rlRightImg, true)
                        .setVisible(R.id.viewFill, true);

            }

        } else if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_THREE)) {

            //3张图片
           baseViewHolder.setVisible(R.id.llCenterImg, true);
            try {
                ImageLoaderUtils.displayImage(news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg1));
                ImageLoaderUtils.displayImage(news.getPicarr().get(1).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg2));
                ImageLoaderUtils.displayImage(news.getPicarr().get(2).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg3));
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_GALLERY)) {

            //画廊类型
           // if (news.image_list == null) {
            //    ImageLoaderUtils.displayImage(Constants.Home3+news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
            //    baseViewHolder.setVisible(R.id.rlRightImg, true)
            //            .setVisible(R.id.viewFill, true);
           // } else {
                if(news.getPicarr().size()!=0){
                    ImageLoaderUtils.displayImage(news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivBigImg));

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

        }
        if(news.getKeywords()==null||news.getKeywords().equals("")){

            //baseViewHolder.setVisible(R.id.tvKeyword,false);

        }else {
            //baseViewHolder.setVisible(R.id.tvKeyword,true);
           // baseViewHolder.setText(R.id.tvKeyword,news.getKeywords());
        }
        baseViewHolder.setText(R.id.tvTitle, news.getTitle())
                .setText(R.id.tvAuthorName, news.getSource())
                .setText(R.id.tvTime, DateUtils.getShortTime(news.getPosttime()));
               Log.d("时间",news.getPosttime()+"11111"+DateUtils.getShortTime(news.getPosttime()));

        baseViewHolder.setOnClickListener(R.id.ll_home_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  CommonUtil.goAactivity(context, NewsDetailActivity.class, "newsurl",
              //                  Constants.NEWSURL.concat("id=" +news.getId() + "&userid=" ));

                if(news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_GALLERY)){
                            Intent intent=new Intent(context,NewsImageActivity.class);
                            intent.putExtra("PicarrBean",(Serializable)news.getPicarr());
                            context.startActivity(intent);
                            //CommonUtil.goAactivity(getActivity(), NewsImageActivity.class);
                        }else {

                            CommonUtil.goAactivity(context, NewsDetailActivity.class, "newsurl",
                                    Constants.NEWSURL.concat("id=" +news.getId() + "&userid="));


                        }

            }
        });

    }

    private void setGone(ViewHolder baseViewHolder) {
        baseViewHolder.setVisible(R.id.viewFill, false)
                .setVisible(R.id.llCenterImg, false)
                .setVisible(R.id.rlBigImg, false)
                .setVisible(R.id.llVideo, false)
                .setVisible(R.id.rlRightImg, false);

    }
}

