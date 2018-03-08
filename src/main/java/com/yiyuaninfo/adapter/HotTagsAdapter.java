package com.yiyuaninfo.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.HotEntity;
import com.yiyuaninfo.util.ConstanceValue;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class HotTagsAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public HotTagsAdapter(List<String > data) {
        super(R.layout.item_hot_tag, data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, String  s) {

        baseViewHolder.setText(R.id.tv_hot_tag,s);

    }
}