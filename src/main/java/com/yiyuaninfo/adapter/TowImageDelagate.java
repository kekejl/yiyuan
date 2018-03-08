package com.yiyuaninfo.adapter;

import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.ImageInfoType;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2017/4/1.
 */

public class TowImageDelagate implements ItemViewDelegate<ImageInfoType> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_recycleview;
    }

    @Override
    public boolean isForViewType(ImageInfoType item, int position) {

       return   item.getType()==2;
    }

    @Override
    public void convert(ViewHolder holder, ImageInfoType imageInfoType, int position) {
       holder.setText(R.id.text,imageInfoType.getName());
        holder.setImageResource(R.id.image,imageInfoType.getImagesid().get(0));
    }
}
