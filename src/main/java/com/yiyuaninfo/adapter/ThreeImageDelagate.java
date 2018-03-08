package com.yiyuaninfo.adapter;

import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.ImageInfoType;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2017/4/1.
 */

public class ThreeImageDelagate implements ItemViewDelegate<ImageInfoType> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_three_recycleview;
    }

    @Override
    public boolean isForViewType(ImageInfoType item, int position) {

       return   item.getType()==3;
    }

    @Override
    public void convert(ViewHolder holder, ImageInfoType imageInfoType, int position) {
       holder.setText(R.id.text_three,imageInfoType.getName());
        holder.setImageResource(R.id.image_three1,imageInfoType.getImagesid().get(0));
        holder.setImageResource(R.id.image_three2,imageInfoType.getImagesid().get(1));
        holder.setImageResource(R.id.image_three3,imageInfoType.getImagesid().get(2));
    }
}
