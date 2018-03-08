package com.yiyuaninfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Music;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */

public class Testadapter extends RecyclerView.Adapter<Testadapter.MyViewHolder> implements View.OnClickListener {
    private Context  context;
    private List<Music.MArrBean>   data;

    private OnItemClickListener1 mOnItemClickListener = null;

    //define interface
    public static interface OnItemClickListener1 {
        void onItemClick(View view , int position);
    }
    public Testadapter(List<Music.MArrBean>  list, Context context) {
        this.context = context;
        this.data=list;
    }
    public void setOnItemClickListener(OnItemClickListener1 listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public Testadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View   view= LayoutInflater.from(context).inflate(R.layout.item_music_list, parent, false);
        Testadapter.MyViewHolder viewHolder=new Testadapter.MyViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Testadapter.MyViewHolder holder, int position) {

     holder.textView.setText(data.get(position).getSname());

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public   class MyViewHolder extends  RecyclerView.ViewHolder {

         private TextView textView;

         public MyViewHolder(View itemView) {
             super(itemView);
             textView = (TextView) itemView.findViewById(R.id.tv_music_name);

         }
     }
}
