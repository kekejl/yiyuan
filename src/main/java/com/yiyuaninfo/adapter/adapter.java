package com.yiyuaninfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/6/14.
 */

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {
    private Context  context;

    public adapter(Context context) {
        this.context = context;
    }

    @Override
    public adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(adapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public   class MyViewHolder extends  RecyclerView.ViewHolder {
         private TextView textView;

         public MyViewHolder(View itemView) {
             super(itemView);
         }
     }
}
