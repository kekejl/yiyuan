package com.yiyuaninfo.adapter;

import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.ImageInfoType;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2017/4/1.
 */

public class BigImageDelagate implements ItemViewDelegate<ImageInfoType> {




    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_big_recycleview;
    }

    @Override
    public boolean isForViewType(ImageInfoType item, int position) {

       return   item.getType()==1;
    }



    @Override
    public void convert(ViewHolder holder, ImageInfoType imageInfoType, int position) {
       holder.setText(R.id.text_big,imageInfoType.getName());
        holder.setImageResource(R.id.image_big,imageInfoType.getImagesid().get(0));
    }
}
