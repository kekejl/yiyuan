package com.yiyuaninfo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.Activity.PayActivity;
import com.yiyuaninfo.Activity.VipActivity;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.UserOrder;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.DateUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/30.
 */

public class UserOrderAdapter extends BaseQuickAdapter<UserOrder.UserOrderBean, BaseViewHolder> {
    private String  type;
    private Context  context;
    private TextView tvtuidan;
    public UserOrderAdapter(int layoutResId, List<UserOrder.UserOrderBean> data,String type,Context context) {
        super(layoutResId, data);
        this.type=type;
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserOrder.UserOrderBean item) {
        if(type.equals("1")){
            helper.setVisible(R.id.ll_order_yanbao,true);
            helper.setText(R.id.tv_myorder_state,"支付完成");
            helper.setTextColor(R.id.tv_myorder_state,Color.parseColor("#d43c33"));

        }else {

            helper.setText(R.id.tv_myorder_state,"待支付");
            helper.setTextColor(R.id.tv_myorder_state,Color.parseColor("#3589E5"));

            helper.setVisible(R.id.ll_order_yanbao,false);
            helper.setVisible(R.id.tv_myorder_sj,false);
            helper.setVisible(R.id.tv_myorder_time,false);
            helper.setVisible(R.id.tv_myorder_money,false);

            LinearLayout linearLayout=(LinearLayout) helper.getView(R.id.ll_myorder);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonUtil.goAactivity(context, PayActivity.class, "id", "1", "type", "1");

                }
            });

        }

         tvtuidan=(TextView)helper.getView(R.id.tv_order_tuidan);

        helper.setText(R.id.tv_myorder_name,"产品:"+item.getPackname());


        if(item.getPaystatus().equals("3")){
            tvtuidan.setText("申请中");

            tvtuidan.setTextColor(Color.parseColor("#000000"));
            tvtuidan.setBackgroundResource(R.drawable.textview_bg);
        }else if(item.getPaystatus().equals("4")){

            tvtuidan.setText("已终止");
            tvtuidan.setTextColor(Color.parseColor("#8B8B8B"));
            tvtuidan.setBackgroundResource(R.drawable.textview_tuidan_bg);

        }else {



        }


        if(item.getUnique_ordernum()==null){
            helper.setVisible(R.id.tv_myorder_dingdan_copy,false);
            helper.setText(R.id.tv_myorder_dingdan,"订单号:"+"后台添加");

        }else {


            helper.setVisible(R.id.tv_myorder_dingdan_copy,false);

        helper.setText(R.id.tv_myorder_dingdan,"订单号:"+item.getUnique_ordernum());
        }
        helper.setText(R.id.tv_myorder_danjia,"单价(元): "+item.getPrice());
        helper.setText(R.id.tv_myorder_sj,"购买时间:"+ DateUtils.getTimePoint(item.getBuytime()));

        if(item.getExpiretime()!=null){

            if(item.getExpiretime().equals("")){
                helper.setText(R.id.tv_myorder_time,"剩余次数:"+item.getLastime()+"次");

            }else {

                helper.setText(R.id.tv_myorder_time,"有效期:"+item.getExpiretime());
            }
        }




        helper.setText(R.id.tv_myorder_money,"实付金额(元):"+item.getCost());



        if(item.getPaystatus().equals("3")){
            helper.addOnClickListener(R.id.tv_order_tuidan);

        }else if(item.getPaystatus().equals("4")){


        }else {
            helper.addOnClickListener(R.id.tv_order_tuidan);



        }
        //判断365是否显示  申请终止服务
        if(item.getPackname().equals("365会员")){

            helper.setVisible(R.id.tv_order_tuidan,false);

        }

        helper.addOnClickListener(R.id.tv_order_yanbao);




    }
}
