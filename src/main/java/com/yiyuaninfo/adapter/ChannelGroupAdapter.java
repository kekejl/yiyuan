package com.yiyuaninfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyuaninfo.Activity.CPActivity;
import com.yiyuaninfo.Activity.HQActivity;
import com.yiyuaninfo.Activity.JJActivity;
import com.yiyuaninfo.Activity.QSActivity;
import com.yiyuaninfo.Activity.RDActivity;
import com.yiyuaninfo.Activity.SHActivity;
import com.yiyuaninfo.Activity.TGActivity;
import com.yiyuaninfo.Activity.XMActivity;
import com.yiyuaninfo.Activity.YLActivity;
import com.yiyuaninfo.Listener.OnRecyclerViewItemClickListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.ChannelEntity;
import com.yiyuaninfo.entity.VideoInfo;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by gaocongcong on 2017/7/6.
 */

public class ChannelGroupAdapter  extends CommonAdapter<ChannelEntity.AllChannelListBean> implements View.OnClickListener {
    private Context  context;
    private OnRecyclerViewItemClickListener<ChannelEntity.AllChannelListBean> onRecyclerViewItemClickListener=null;
    private OnRecyclerViewItemClickListener<ChannelEntity.AllChannelListBean.GroupListBean> onRecyclerViewItemClickListenerBean=null;

    public ChannelGroupAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
        this.context=context;
    }
    public void OnRecyclerViewItemClickListener  (OnRecyclerViewItemClickListener listener){
        this.onRecyclerViewItemClickListener=listener;
    }


    @Override
    protected void convert(ViewHolder holder, ChannelEntity.AllChannelListBean allChannelListBean, int position) {
        holder.setText(R.id.tv_GroupName,allChannelListBean.getGroupName());
        LinearLayout linearLayout=holder.getView(R.id.ll_channel_more);
        switch (allChannelListBean.getGroupName()){
            case "行情":
                holder.setImageResource(R.id.image_group,R.drawable.pd_hq);
                break;
            case "热点":
                holder.setImageResource(R.id.image_group,R.drawable.pd_rd);
                break;
            case "娱乐":
                holder.setImageResource(R.id.image_group,R.drawable.pd_yl);
                break;
            case "生活":
                holder.setImageResource(R.id.image_group,R.drawable.pd_sh);
                break;
            case "产品":
                holder.setImageResource(R.id.image_group,R.drawable.pd_cp);
                break;
            case "项目":
                holder.setImageResource(R.id.image_group,R.drawable.pd_xm);
                break;
            case "投顾":
                holder.setImageResource(R.id.image_group,R.drawable.pd_tg);
                break;
            case "券商":
                holder.setImageResource(R.id.image_group,R.drawable.pd_qs);
                break;
            case "基金":
                holder.setImageResource(R.id.image_group,R.drawable.pd_jj);
                break;


        }
        linearLayout.setOnClickListener(this);
        linearLayout.setTag(allChannelListBean);
        RecyclerView  recyclerView= holder.getView(R.id.recycle_channel_item);
        GridLayoutManager  manager=new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(manager);
        ChannelItemAdapter  adapter=new ChannelItemAdapter(context,R.layout.item_channel_item,allChannelListBean.getGroupList());
        recyclerView.setAdapter(adapter);
        adapter.OnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener<ChannelEntity.AllChannelListBean.GroupListBean>() {

            @Override
            public void onItemClick(View view, ChannelEntity.AllChannelListBean.GroupListBean data) {
                //ToastUtils.showToast(data.getChannelname());
                switch (data.getGroup()) {
                    case "行情":
                        CommonUtil.goAactivity(context,HQActivity.class,"channelid",data.getId(),"id",0);
                        break;
                    case "热点":
                       // CommonUtil.goAactivity(context,RDActivity.class,"channelid",data.getId());
                        CommonUtil.goAactivity(context,HQActivity.class,"channelid",data.getId(),"id",1);

                        break;
                    case "娱乐":
                        CommonUtil.goAactivity(context,HQActivity.class,"channelid",data.getId(),"id",2);

                       // CommonUtil.goAactivity(context,YLActivity.class,"channelid",data.getId());
                        break;
                    case "生活":
                        CommonUtil.goAactivity(context,HQActivity.class,"channelid",data.getId(),"id",3);

                        //CommonUtil.goAactivity(context,SHActivity.class,"channelid",data.getId());
                        break;
                    case "产品":
                        CommonUtil.goAactivity(context,CPActivity.class,"channelid",data.getId());
                        break;
                    case "项目":
                        CommonUtil.goAactivity(context,XMActivity.class,"channelid",data.getId(),"id",5);
                        break;
                    case "投顾":
                        CommonUtil.goAactivity(context,TGActivity.class,"channelid",data.getId());
                        break;
                    case "券商":
                        CommonUtil.goAactivity(context,QSActivity.class,"channelid",data.getId());
                        break;
                    case "基金":
                        CommonUtil.goAactivity(context,JJActivity.class,"channelid",data.getId());
                        break;

                }

            }
        });
    }

    @Override
    public void onClick(View v) {


        if(onRecyclerViewItemClickListener!=null){
            onRecyclerViewItemClickListener.onItemClick(v,(ChannelEntity.AllChannelListBean)v.getTag());
        }
    }
}
