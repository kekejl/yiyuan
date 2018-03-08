package com.yiyuaninfo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.entity.Project;
import com.yiyuaninfo.util.ConstanceValue;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import static com.yiyuaninfo.util.CommonUtils.getResources;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class XMAdapter extends BaseQuickAdapter<Project.RecommendBean, BaseViewHolder> {
    private FlowLayout  flowLayout;
    private List<String> taglist=new ArrayList<>();
    private Context  context;
    public XMAdapter(Context context,List<Project.RecommendBean> data) {
        super(R.layout.item_xm, data);
        Log.d("111111111", data.toString());
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Project.RecommendBean news) {
        switch (news.getLabel()) {
            case "1":
             baseViewHolder.setText(R.id.tv_xm_tags,"热");
                break;
            case "2":
                baseViewHolder.setText(R.id.tv_xm_tags,"推");

                break;
            case "3":
                baseViewHolder.setText(R.id.tv_xm_tags,"荐");

                break;
            default:
                baseViewHolder.setVisible(R.id.tv_xm_tags,false);
        }
        baseViewHolder.setText(R.id.tv_xm_title, news.getTitle());
        baseViewHolder.setText(R.id.tv_xm_describe, news.getDescription());
        baseViewHolder.setText(R.id.tv_xm_guanzhu, news.getHits() + "关注");
       // baseViewHolder.setText(R.id.tv_xm_tag, news.getAuth_tag());

        flowLayout=baseViewHolder.getView(R.id.flowlayout_xm_tag);
        if(news.getAuth_tag().equals("")){
            flowLayout.setVisibility(View.GONE);
        }else {


            String[] tags = news.getAuth_tag().split(",");
            for (int i = 0; i < tags.length; i++) {
                taglist.add(tags[i]);
            }

            Log.d("标签数据", taglist.toString());
            flowLayout.removeAllViews();
            for (int i = 0; i < taglist.size(); i++) {
                final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.item_flow_layout_tag_finder, flowLayout, false);
                tv.setBackgroundResource(R.drawable.popup_corner);
                tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv.setText(taglist.get(i));
                tv.setTextSize(10);
                flowLayout.addView(tv);

            }
            taglist.clear();
        }
        if(news.getPicurl().startsWith("http")){

        ImageLoaderUtils.displayImage(news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.iv_xm));
        }else {
            ImageLoaderUtils.displayImage(Constants.XINGMU + news.getPicurl(), (ImageView) baseViewHolder.getView(R.id.iv_xm));

        }
        baseViewHolder.addOnClickListener(R.id.tv_xm_zx);
    }
}