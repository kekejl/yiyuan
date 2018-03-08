package com.yiyuaninfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyuaninfo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaocongcong on 2017/8/13.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_IMAGE = 0;
    private static final int TYPE_GROUP = 1;
    private List<String> mData = new ArrayList<>();
    private Context mContext;

    private boolean isTitle(int pos){
        if(mData.get(pos).startsWith("this is title:")) {
            return true;
        }
        return false;
    }

    public RecyclerViewAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from (mContext);
//        switch ( viewType ) {
//            case TYPE_IMAGE:
//                ViewGroup vImage = ( ViewGroup ) mInflater.inflate ( R.layout.item_image, parent, false );
//                ImageViewHolder vhImage = new ImageViewHolder ( vImage );
//                return vhImage;
//            case TYPE_GROUP:
//
//        }
        ViewGroup vGroup = ( ViewGroup ) mInflater.inflate ( R.layout.item_text, parent, false );
        GroupViewHolder vhGroup = new GroupViewHolder ( vGroup );
        return vhGroup;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {



//        switch ( holder.getItemViewType () ) {
//            case TYPE_IMAGE:
//                ImageViewHolder imageViewHolder = ( ImageViewHolder ) holder;
//                imageViewHolder.mImage.setImageResource ( R.drawable.a);
//                imageViewHolder.mTitle.setText(mData.get(position));
//                break;
//            case TYPE_GROUP:
//                GroupViewHolder groupViewHolder = ( GroupViewHolder ) holder;
//                groupViewHolder.mTitle.setText ( mData.get(position));
//                break;
//        }
        Log.d("位置",position+"+++"+mData.get(position));
         if(position==0){

             GroupViewHolder groupViewHolder = ( GroupViewHolder ) holder;
             groupViewHolder.mTitle.setText ( "简介简介");
         }else if(position==1){

             GroupViewHolder groupViewHolder = ( GroupViewHolder ) holder;
             groupViewHolder.mTitle.setText ( "动态动态");
         }else {
             GroupViewHolder groupViewHolder = ( GroupViewHolder ) holder;
             groupViewHolder.mTitle.setText (mData.get(position-2));
         }
//        switch ( position ) {
//            case TYPE_IMAGE:
//                ImageViewHolder imageViewHolder = ( ImageViewHolder ) holder;
//                imageViewHolder.mImage.setImageResource ( R.drawable.a);
//                imageViewHolder.mTitle.setText(mData.get(position));
//                break;
//            case TYPE_GROUP:
//                GroupViewHolder groupViewHolder = ( GroupViewHolder ) holder;
//                groupViewHolder.mTitle.setText ( mData.get(position));
//                break;
//        }
    }

    @Override
    public int getItemCount() {
        return mData.size ();
    }

    @Override
    public int getItemViewType ( int position ) {
        int viewType;
        if (!isTitle(position) ) {
            viewType = TYPE_GROUP;
        } else {
            viewType = TYPE_IMAGE;
        }
        return viewType;
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        public GroupViewHolder ( View itemView ) {
            super(itemView);
            mTitle= (TextView) itemView.findViewById(R.id.text);
        }

    }
    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        ImageView mImage;
        public ImageViewHolder ( View itemView ) {
            super(itemView );
            mTitle= (TextView) itemView.findViewById(R.id.text);
            mImage= (ImageView) itemView.findViewById(R.id.image);
        }
    }

}
