package com.yiyuaninfo.view.Search;

import android.content.Context;

import com.yiyuaninfo.R;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/27.
 */

public class adapter  extends CommonAdapter<String> {
    public adapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        viewHolder.setText(R.id.text,item);
    }
}
