package com.yiyuaninfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiyuaninfo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaocongcong on 2017/8/15.
 */

public class TextAdapter  extends RecyclerView.Adapter<TextAdapter.ViewHolder> {
    private List<String> mData = new ArrayList<>();
    private Context  context;
    public TextAdapter(Context  context,List<String> mData) {
        this.mData = mData;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View   view=LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
        ViewHolder  viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("位置",position+"+++"+mData.get(position));
        if(position==0){

            holder.mTextView.setText ( "简介简介");
        }else if(position==1){

            holder.mTextView.setText( "动态动态");
        }else {
            holder.mTextView.setText (mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text12);
        }
    }
}
