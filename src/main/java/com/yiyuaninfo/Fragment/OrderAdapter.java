package com.yiyuaninfo.Fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiyuaninfo.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyHolder>{

    Context context;
    List<String> list;

    public OrderAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder view=new MyHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false));
        return view;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
