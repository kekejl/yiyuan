package com.yiyuaninfo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.FinderBean;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import static com.yiyuaninfo.util.CommonUtils.getResources;

/**
 * Created by gaocongcong on 2017/7/20.
 */

public class FinderAdapter  extends BaseQuickAdapter<FinderBean,BaseViewHolder>{
        private Context context;
    private FlowLayout flowlayout;
    private List<String> taglist=new ArrayList<>();
    private String name;

    public FinderAdapter(Context context,List<FinderBean> data,String name) {
        super(R.layout.item_finder, data);
        Log.d("111111111", data.toString());
        this.context=context;
        this.name=name;
    }
    @Override
    protected void convert(BaseViewHolder holder, FinderBean otherBean) {
        holder.setText(R.id.tv_finder_title, otherBean.getGname());
        holder.setText(R.id.tv_finder_zj, "资金量：" + otherBean.getRegmoney().replace(".00","")+"万元");
        if(otherBean.getLogo().startsWith("http")){
            ImageLoaderUtils.displayImage(otherBean.getLogo(), (ImageView)holder.getView(R.id.image_finder));

        }else {

        ImageLoaderUtils.displayImage(Constants.FInder + otherBean.getLogo(), (ImageView)holder.getView(R.id.image_finder));
        }
        flowlayout = holder.getView(R.id.flowlayout_finder_tag);
        Log.d("字符串截取1", otherBean.getPart());
        if(otherBean.getPart().length()==0&&otherBean.getAuth_tag().length()==0){
            flowlayout.setVisibility(View.GONE);
        }else {


        String[] tags = otherBean.getPart().split(",");
        Log.d("字符串截取2", tags.toString()+tags.length);
        for (int i = 0; i < tags.length; i++) {
            Log.d("字符串截取3", tags[i]);
            String tag = tags[i];
            switch (tag) {

                case "1":
                    switch (name){
                        case "投顾":
                            taglist.add("荐股");
                            break;
                        case  "券商":
                            taglist.add("QDII/QFII");
                            break;
                        case  "基金":
                            taglist.add("股票型");

                            break;
                    }

                    break;
                case "2":
                    switch (name){
                        case "投顾":
                            taglist.add("工具");
                            break;
                        case  "券商":
                            taglist.add("交易");
                            break;
                        case  "基金":
                            taglist.add("货币型");

                            break;
                    }
                    break;
                case "3":
                    switch (name){
                        case "投顾":
                            taglist.add("方法");
                            break;
                        case  "券商":
                            taglist.add("资管");
                            break;
                        case  "基金":
                            taglist.add("债券");

                            break;
                    }
                    break;
            }

        }
        if (otherBean.getAuth_tag() != null) {
           taglist.add(otherBean.getAuth_tag());
        }
        Log.d("标签数据", taglist.toString());
        flowlayout.removeAllViews();



        for (int i = 0; i < taglist.size(); i++) {
            final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.item_flow_layout_tag_finder, flowlayout, false);
            tv.setBackgroundResource(R.drawable.popup_corner);
            tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            tv.setText(taglist.get(i));
            tv.setTextSize(10);
            flowlayout.addView(tv);

        }
        taglist.clear();

        Log.d("标签数据2", taglist.toString  ());
    }
    }
}


//    @Override
//    protected void convert(BaseViewHolder holder, FinderBean otherBean) {
//      ());
//
//
//
//
//    }



