package com.yiyuaninfo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.view.FlowLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.yiyuaninfo.util.CommonUtils.getResources;

/**
 * Created by gaocongcong on 2017/7/20.
 */

public class CPAdapter extends BaseQuickAdapter<Product.ProArrBean,BaseViewHolder> {
    private  FlowLayout  flowlayout;
    private  List<String>  taglist=new ArrayList<>();
    private Context  context;
    public CPAdapter(Context  context,int layoutResId,  List<Product.ProArrBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder holder, Product.ProArrBean proArrBean) {
        holder.setText(R.id.tv_cp_companyname,proArrBean.getYname());
        //holder.setText(R.id.tv_cp_abstract,  proArrBean.getContent());
        holder.setText(R.id.tv_cp_price,  "￥"+proArrBean.getYprice());
        ImageView imageView = holder.getView(R.id.image_cp);
        ImageLoaderUtils.displayAvatar(proArrBean.getYlogo(),(ImageView)holder.getView(R.id.image_cp));

        //Glide.with(context).load(R.drawable.huiyuan).into(imageView);
//        flowlayout = holder.getView(R.id.flowlayout_cp_tag);
//        Log.d("字符串截取1", proArrBean.getPart());
//
//        String[] tags = proArrBean.getPart().split(",");
//        Log.d("字符串截取2", tags.toString());
//        taglist= Arrays.asList(tags);
//        flowlayout.removeAllViews();
//        for (String tag:tags){
//            final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.item_flow_layout, flowlayout, false);
//            tv.setBackgroundResource(R.drawable.popup_corner);
//            tv.setTextColor(getResources().getColor(R.color.black));
//            tv.setText(tag);
//            tv.setTextSize(10);
//            flowlayout.addView(tv);
//        }
//        tags=null;
//        Log.d("标签数据2", taglist.toString());
//        TextView  textView=holder.getView(R.id.tv_cp_tag);
//        switch (proArrBean.getLabel()){
//            case "0":
//
//                textView.setVisibility(View.GONE);
//                break;
//            case "1":
//                textView.setVisibility(View.VISIBLE);
//                textView.setText("热");
//                break;
//            case "2":
//                textView.setVisibility(View.VISIBLE);
//                textView.setText("推");
//                break;
//            case "3":
//                textView.setVisibility(View.VISIBLE);
//                textView.setText("荐");
//                break;
//        }
        TextView   state=holder.getView(R.id.tv_cp_state) ;
        //销售状态
        switch (proArrBean.getYstate()){
            case "1":
                state.setText("在售");
                break;
            case  "2":
                state.setText("售罄");

                break;

        }

    }
}
