package com.yiyuaninfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.yiyuaninfo.Listener.OnRecyclerViewItemClickListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.VideoConstant;
import com.yiyuaninfo.entity.VideoInfo;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class RecyclerViewVideoAdapter extends RecyclerView.Adapter<RecyclerViewVideoAdapter.MyViewHolder> implements View.OnClickListener {
    ImageView tvMore;
    View  view;
    private Context context;
    public static final String TAG = "RecyclerViewVideoAdapter";
    private OnRecyclerViewItemClickListener<VideoInfo>  onRecyclerViewItemClickListener=null;
    private List<VideoInfo>  videoInfoList;
    public RecyclerViewVideoAdapter(Context context,List<VideoInfo> list) {
        this.context = context;
        this.videoInfoList=list;
    }
   public void OnRecyclerViewItemClickListener  (OnRecyclerViewItemClickListener listener){
           this.onRecyclerViewItemClickListener=listener;
   }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view=LayoutInflater.from(context).inflate(R.layout.item_videoview, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        tvMore=(ImageView)view.findViewById(R.id.tvMore);
        //将创建的view添加点击事件
        tvMore.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder [" + holder.jcVideoPlayer.hashCode() + "] position=" + position);

        holder.jcVideoPlayer.setUp(videoInfoList.get(position).getUrl(),
                 JCVideoPlayer.SCREEN_LAYOUT_LIST,
               videoInfoList.get(position).getTitle());
        Picasso.with(holder.jcVideoPlayer.getContext())
                .load(videoInfoList.get(position).getImage())
                .into(holder.jcVideoPlayer.thumbImageView);
        //将数据保存在itemview的Tag中，以便点击时进行获取
        holder.itemView.setTag(videoInfoList.get(position));



    }



    @Override
    public int getItemCount() {
        return videoInfoList.size();
    }

    @Override
    public void onClick(View v) {
       if(onRecyclerViewItemClickListener!=null){
           onRecyclerViewItemClickListener.onItemClick(v,(VideoInfo)v.getTag());
       }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        JCVideoPlayerStandard jcVideoPlayer;

        public MyViewHolder(View itemView) {
            super(itemView);
            jcVideoPlayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
        }
    }

}
