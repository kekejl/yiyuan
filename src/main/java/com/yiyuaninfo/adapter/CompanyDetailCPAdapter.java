package com.yiyuaninfo.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.CompanyDetailsProduct;
import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaocongcong on 2017/7/20.
 */

public class CompanyDetailCPAdapter extends BaseQuickAdapter<CompanyDetailsProduct,BaseViewHolder> {
    private  FlowLayout  flowlayout;
    private  List<String>  taglist=new ArrayList<>();
    private Context  context;
    public CompanyDetailCPAdapter(Context  context, int layoutResId, List<CompanyDetailsProduct> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder holder, CompanyDetailsProduct proArrBean) {
        holder.setText(R.id.tv_cp_companyname,proArrBean.getYname());
        //holder.setText(R.id.tv_cp_abstract,  proArrBean.getContent());
        holder.setText(R.id.tv_cp_price,  "￥"+proArrBean.getYprice());
        ImageView imageView = holder.getView(R.id.image_cp);
        ImageLoaderUtils.displayAvatar(proArrBean.getCom_pic(),(ImageView)holder.getView(R.id.image_cp));
        TextView   state=holder.getView(R.id.tv_cp_state) ;
        //销售状态
        switch (proArrBean.getYstate()){
            case "1":
                state.setText("在售");
                break;
            case  "2":
                state.setText("售罄");

                break;

        }

    }
}
