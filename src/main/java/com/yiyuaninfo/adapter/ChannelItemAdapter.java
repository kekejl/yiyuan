package com.yiyuaninfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiyuaninfo.Listener.OnRecyclerViewItemClickListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.ChannelEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ChannelItemAdapter extends CommonAdapter<ChannelEntity.AllChannelListBean.GroupListBean> implements View.OnClickListener {

    private OnRecyclerViewItemClickListener<ChannelEntity.AllChannelListBean.GroupListBean> onRecyclerViewItemClickListenerBean=null;

    public void OnRecyclerViewItemClickListener  (OnRecyclerViewItemClickListener listener){
        this.onRecyclerViewItemClickListenerBean=listener;
    }


    public ChannelItemAdapter(Context context, int layoutId, List<ChannelEntity.AllChannelListBean.GroupListBean> datas) {
        super(context, layoutId, datas);

    }

    @Override
    protected void convert(ViewHolder holder, ChannelEntity.AllChannelListBean.GroupListBean groupListBean, int position) {

        holder.setText(R.id.tv_channel_name,groupListBean.getChannelname());
        TextView   textViewChannel=holder.getView(R.id.tv_channel_name);
        textViewChannel.setOnClickListener(this);
        textViewChannel.setTag(groupListBean);
    }

    @Override
    public void onClick(View v) {
        if(onRecyclerViewItemClickListenerBean!=null){
            onRecyclerViewItemClickListenerBean.onItemClick(v,(ChannelEntity.AllChannelListBean.GroupListBean)v.getTag());
        }

    }
}
