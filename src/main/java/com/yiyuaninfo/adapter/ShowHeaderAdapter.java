package com.yiyuaninfo.adapter;

import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.ShowEntity;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class ShowHeaderAdapter extends BaseQuickAdapter<ShowEntity.TjArrBean,BaseViewHolder> {
    public ShowHeaderAdapter(List<ShowEntity.TjArrBean> data) {
        super(R.layout.item_show_header, data);
        Log.d("111111111", data.toString());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ShowEntity.TjArrBean show) {
        if(show.getIndeximg().startsWith("http")){

        ImageLoaderUtils.displayImage(show.getIndeximg(), (ImageView) baseViewHolder.getView(R.id.iv_show_header));
        }else {
        ImageLoaderUtils.displayImage(Constants.FInder+show.getIndeximg(), (ImageView) baseViewHolder.getView(R.id.iv_show_header));

        }


}

}

