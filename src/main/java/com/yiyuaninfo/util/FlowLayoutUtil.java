package com.yiyuaninfo.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.yiyuaninfo.R;
import com.yiyuaninfo.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import static com.yiyuaninfo.util.CommonUtils.getResources;

/**
 * Created by gaocongcong on 2017/11/2.
 */

public class FlowLayoutUtil {

    private FlowLayout  flowLayout;
    private Context  context;
    private List<String> list=new ArrayList<>();
    public FlowLayoutUtil(FlowLayout flowLayout, Context context) {
        this.flowLayout = flowLayout;
        this.context = context;
    }
    public void showFlowLayout(String string){
        String[]  tags=string.split(" ");
        Log.d("标签分割的数据",tags.toString()+"\n"+string);
        for (int i = 0; i <tags.length ; i++) {
            String tag=tags[i];
            if(!tag.equals("")){
                list.add(tag);

            }
        }
        Log.d("标签数据", list.toString());
        flowLayout.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.item_flow_layout_tag_finder, flowLayout, false);
            tv.setBackgroundResource(R.drawable.popup_corner);
            tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            tv.setText(list.get(i));
            tv.setTextSize(10);
            flowLayout.addView(tv);

        }
        list.clear();
    }
}
