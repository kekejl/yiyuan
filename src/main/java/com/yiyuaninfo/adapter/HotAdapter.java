package com.yiyuaninfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.Activity.NewsDetailActivity;
import com.yiyuaninfo.Activity.NewsImageActivity;
import com.yiyuaninfo.Interface.HotEntityChangeBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.HotTagChangeEntity;
import com.yiyuaninfo.entity.InfoArrBean;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.entity.TagArrBean;
import com.yiyuaninfo.util.ConstanceValue;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.FlowLayoutUtil;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.view.FlowLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yiyuaninfo.util.CommonUtils.getResources;

/**
 * Created by gaocongcong on 2017/8/1.
 */

//public class HotAdapter extends BaseMultiItemQuickAdapter<InfoArrBean,BaseViewHolder> {
//    private FlowLayout  flowLayout, flowLayout1 ;
//    private Context   context;
//    private HotHeaderAdapter  hotheaderadapter;
//    private RecyclerView   recyclerViewHeader;
//    private  TextView  textViewSelected;
//    private  ImageView   imageView;
//    public  HotAdapter(List<InfoArrBean> data  ,Context  context) {
//        super(data);
//         this.context=context;
//        addItemType(InfoArrBean.Header,R.layout.fragment_hot_header);
//        addItemType(InfoArrBean.Content,R.layout.item_news);
//
//
//
//    }

public class HotAdapter extends BaseQuickAdapter<InfoArrBean, BaseViewHolder> {
    private Context context;
    private List<String> list = new ArrayList<>();
    private FlowLayout flowLayout;

    public HotAdapter(List<InfoArrBean> data, Context context) {
        super(R.layout.item_news, data);
        this.context = context;
        Log.d("111111111", data.toString());
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, final InfoArrBean news) {


        //防止复用View没有改变主题，重新设置
        // ColorUiUtil.changeTheme(baseViewHolder.convertView, mContext.getTheme());
        setGone(baseViewHolder);
        if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_LIFT)) {

            if (!TextUtils.isEmpty(news.getPicurl())) {
                //单图片文章
                if (news.getPicurl().startsWith("http")) {
                    ImageLoaderUtils.displayImage(news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.ivRightImg1));

                } else {
                    ImageLoaderUtils.displayImage(Constants.Home3 + news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.ivRightImg1));

                }
                baseViewHolder.setVisible(R.id.rlRightImg, true)
                        .setVisible(R.id.viewFill, true);

            }


        } else if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_THREE)) {

            //3张图片
            baseViewHolder.setVisible(R.id.llCenterImg, true);
            baseViewHolder.setVisible(R.id.tv_item_news_title, true);
            baseViewHolder.setText(R.id.tv_item_news_title, news.getTitle());
            baseViewHolder.setVisible(R.id.tvName, false);

            try {
                Log.d("++++", news.getPicarr().get(0).getImg().startsWith("http") + "");
                if (news.getPicarr().get(0).getImg().startsWith("http")) {
                    ImageLoaderUtils.displayImage(news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg1));

                } else {
                    ImageLoaderUtils.displayImage(Constants.Home3 + news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg1));


                }
                if (news.getPicarr().get(1).getImg().startsWith("http")) {
                    ImageLoaderUtils.displayImage(news.getPicarr().get(1).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg2));

                } else {
                    ImageLoaderUtils.displayImage(Constants.Home3 + news.getPicarr().get(1).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg2));


                }
                if (news.getPicarr().get(2).getImg().startsWith("http")) {
                    ImageLoaderUtils.displayImage(news.getPicarr().get(2).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg3));

                } else {
                    ImageLoaderUtils.displayImage(Constants.Home3 + news.getPicarr().get(2).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg3));


                }
                // ImageLoaderUtils.displayImage(news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg1));
                // ImageLoaderUtils.displayImage(news.getPicarr().get(1).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg2));
                // ImageLoaderUtils.displayImage(news.getPicarr().get(2).getImg(), (ImageView) baseViewHolder.getView(R.id.ivCenterImg3));
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_GALLERY)) {
            baseViewHolder.setVisible(R.id.tv_item_news_title, true);
            baseViewHolder.setText(R.id.tv_item_news_title, news.getTitle());
            baseViewHolder.setVisible(R.id.tvName, false);

            //画廊类型
            // if (news.image_list == null) {
            //    ImageLoaderUtils.displayImage(Constants.Home3+news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
            //    baseViewHolder.setVisible(R.id.rlRightImg, true)
            //            .setVisible(R.id.viewFill, true);
            // } else {
            if (news.getPicarr().size() != 0) {


                if (news.getPicarr().get(0).getImg().startsWith("http")) {

                    ImageLoaderUtils.displayImage(news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivBigImg));
                } else {
                    ImageLoaderUtils.displayImage(Constants.Home3 + news.getPicarr().get(0).getImg(), (ImageView) baseViewHolder.getView(R.id.ivBigImg));

                }
            } else {
                baseViewHolder.setImageResource(R.id.ivBigImg, R.drawable.image_loading);
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
            baseViewHolder.setVisible(R.id.rlBigImg, true);
            baseViewHolder.setVisible(R.id.tv_item_news_title, true);
            baseViewHolder.setText(R.id.tv_item_news_title, news.getTitle());
            baseViewHolder.setVisible(R.id.tvName, false);

        }
        if (news.getKeywords() == null || news.getKeywords().equals("")) {

            baseViewHolder.setVisible(R.id.flowlayout_news_tag, false);

        } else {
            // 人大   监察试点
            baseViewHolder.setVisible(R.id.flowlayout_news_tag, true);
            // baseViewHolder.setText(R.id.tvKeyword,news.getKeywords());
            flowLayout = baseViewHolder.getView(R.id.flowlayout_news_tag);
            FlowLayoutUtil flowLayoutUtil = new FlowLayoutUtil(flowLayout, context);
            flowLayoutUtil.showFlowLayout(news.getKeywords());
//            String[]  tags=news.getKeywords().split(" ");
//            Log.d("标签分割的数据",tags.toString()+"\n"+news.getKeywords());
//            for (int i = 0; i <tags.length ; i++) {
//                String tag=tags[i];
//                if(!tag.equals("")){
//                list.add(tag);
//
//                }
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
        baseViewHolder.setText(R.id.tvName, news.getTitle())
                .setText(R.id.tvAuthorName, news.getSource())
                .setText(R.id.tvTime, DateUtils.getShortTime(news.getPosttime()));
        Log.d("时间", news.getPosttime() + "11111" + DateUtils.getShortTime(news.getPosttime()));

//        baseViewHolder.setOnClickListener(R.id.ll_home_item, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //  CommonUtil.goAactivity(context, NewsDetailActivity.class, "newsurl",
//                //                  Constants.NEWSURL.concat("id=" +news.getId() + "&userid=" ));
//
//                if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_GALLERY)) {
//                    Intent intent = new Intent(context, NewsImageActivity.class);
//                    intent.putExtra("PicarrBean", (Serializable) news.getPicarr());
//                    context.startActivity(intent);
//                    //CommonUtil.goAactivity(getActivity(), NewsImageActivity.class);
//                } else {
//
//
//                    /**
//                     * id : 17673
//                     * title : 金砖国家领导人第九次会晤举行
//                     * source : 人民网
//                     * description : 图集摘要
//                     * picurl :
//                     * picarr : [{"img":"uploads/image/20170906/1504661674.jpg","description":"图片1的描述"},{"img":"uploads/image/20170906/1504669411.jpg","description":"图片2的描述"},{"img":"uploads/image/20170906/1504666992.jpg","description":"图片3的描述"},{"img":"uploads/image/20170906/1504664929.jpg","description":"图片4的描述"}]
//                     * posttime : 2017-09-06 09:26:58
//                     * keywords : 图集
//                     * picstate : 4
//                     */
//                    NewsEntity.HangqingBean entity = new NewsEntity.HangqingBean(
//                            news.getId(),
//                            news.getTitle(),
//                            news.getSource(),
//                            news.getDescription(),
//                            news.getPicurl(),
//                            news.getPicarr(),
//                            news.getPosttime(),
//                            news.getKeywords(),
//                            news.getPicstate()
//
//                    );
//                    Intent intent = new Intent(context, NewsDetailActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("entity", entity);
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//
////
////                    CommonUtil.goAactivity(context, NewsDetailActivity.class, "newsurl",
////                            Constants.NEWSURL.concat("id=" + news.getId() + "&userid="),
////                            "newsid",news.getId(),
////                            "newstitle",news.getTitle() );
//
//
//                }
//
//            }
//        });


    }

    private void setGone(BaseViewHolder baseViewHolder) {
        baseViewHolder.setVisible(R.id.viewFill, false)
                .setVisible(R.id.llCenterImg, false)
                .setVisible(R.id.rlBigImg, false)
                .setVisible(R.id.llVideo, false)
                .setVisible(R.id.rlRightImg, false);

    }

}

