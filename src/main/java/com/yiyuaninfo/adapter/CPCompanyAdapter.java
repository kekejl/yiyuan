package com.yiyuaninfo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.CPCompany;
import com.yiyuaninfo.entity.Finder;
import com.yiyuaninfo.entity.FinderBean;
import com.yiyuaninfo.entity.Project;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import static com.yiyuaninfo.util.CommonUtils.getResources;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class CPCompanyAdapter extends BaseQuickAdapter<FinderBean, BaseViewHolder> {
    private FlowLayout  flowLayout;
    private List<String> taglist=new ArrayList<>();
    private Context  context;
    public CPCompanyAdapter(Context context, List<FinderBean> data) {
        super(R.layout.item_cpcompany, data);
        Log.d("111111111", data.toString());
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FinderBean news) {
        baseViewHolder.setText(R.id.tv_cpcompany_title, news.getGname());
        baseViewHolder.setText(R.id.tv_cpcompany_describe, "资金量:"+news.getRegmoney()+"万元");
        if(!news.getAuth_tag().equals("")){

        baseViewHolder.setText(R.id.tv_cpcompany_tag, news.getAuth_tag());
        }else {
            baseViewHolder.setVisible(R.id.tv_cpcompany_tag,false);
        }
        if(news.getLogo().startsWith("http")){

        ImageLoaderUtils.displayImage(news.getLogo(), (ImageView) baseViewHolder.getView(R.id.iv_cpcompany));
        }else {
            ImageLoaderUtils.displayImage(Constants.XINGMU + news.getLogo(), (ImageView) baseViewHolder.getView(R.id.iv_cpcompany));

        }
        baseViewHolder.addOnClickListener(R.id.tv_xm_zx);
    }
}