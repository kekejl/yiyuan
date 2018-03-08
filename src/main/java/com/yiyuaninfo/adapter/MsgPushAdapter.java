package com.yiyuaninfo.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.MsgPush;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.MsgUtil;

import java.util.List;

import jaydenxiao.com.expandabletextview.ExpandableTextView;

/**
 * Created by gaocongcong on 2017/9/13.
 */

public class MsgPushAdapter extends BaseQuickAdapter<MsgPush.MsglistArrBean,BaseViewHolder> {
    private Context context;
    private List<MsgPush.MsglistArrBean> data;
    private TextView  top,bottom;
    private RelativeLayout  rlBottom;
    private ExpandableTextView  expandableTextView;
    private String  title;
    private int position;
    public MsgPushAdapter(Context  context  ,List<MsgPush.MsglistArrBean> data) {
        super(R.layout.item_msgpush,data);
        this.context=context;
        this.data=data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MsgPush.MsglistArrBean item) {
        //rlBottom=helper.getView(R.id.rl_bottom);
       // zhankai=helper.getView(R.id.tv_tip);
       // imageView=helper.getView(R.id.iv_arrow);

        position=helper.getLayoutPosition();
         expandableTextView=helper.getView(R.id.etv);

        if(helper.getLayoutPosition()==0){
            top=helper.getView(R.id.tvTopLine);
            top.setVisibility(View.INVISIBLE);
        }else {
            helper.setVisible(R.id.tvTopLine,true);

        }
        if(helper.getLayoutPosition()==data.size()-1){
            bottom=helper.getView(R.id.tvBottomLine);
            bottom.setVisibility(View.INVISIBLE);
        }else {
            helper.setVisible(R.id.tvBottomLine,true);

        }
        helper.setText(R.id.tv_pushlist_time, DateUtils.getTimehour( item.getChecktime()));



       switch (item.getKeyword1()){
          case "5":
              helper.setText(R.id.tv_msgpushname,"【早餐】");
              title="【早餐】";
              break;

           case "6":
               helper.setText(R.id.tv_msgpushname,"【早评】");
               title="【早评】";

               break;

           case "7":
               helper.setText(R.id.tv_msgpushname,"【上午分享】");
               title="【上午分享】";

               break;

           case "8":
               helper.setText(R.id.tv_msgpushname,"【午评】");
               title="【午评】";


               break;

           case "9":
               helper.setText(R.id.tv_msgpushname,"【下午分享】");
               title="【下午分享】";

               break;

           case "10":
               helper.setText(R.id.tv_msgpushname,"【收评】");
               title="【收评】";

               break;

           case "11":
               helper.setText(R.id.tv_msgpushname,"【夜宵】");
               title="【夜宵】";

               break;
           case "12":
               helper.setText(R.id.tv_msgpushname,"【及时通知】");
               title="【及时通知】";

               break;
           case "13":
               helper.setText(R.id.tv_msgpushname,"【风险提示】");
               title="【风险提示】";

               break;
           case "14":
               helper.setText(R.id.tv_msgpushname,"【获利提示】");
               title="【获利提示】";

               break;
           default:
           helper.setText(R.id.tv_msgpushname,"【"+item.getKeyword1()+"】");
               title="【"+item.getKeyword1()+"】";
       }
        expandableTextView.setText(title+"\r\n"+MsgUtil.SwitchString(item),position);




//            expandableTextView.setText("　 早上好，周二两市再度低开，沪指快速拉升震荡上涨收复3400点，上证50指数再创新高，创业板冲高随即回落围绕平盘线宽幅震荡。盘面上，券商、保险、家用电器板块涨幅居前，总体上权重股与小盘股分化严重，操作难度较大。而短线操作上，周三如大盘再冲高，仍建议继续择机减仓。\n" +
//                    "温馨提示:\n" +
//                    "1.股市投资中应将资金等分为3、4份滚动操作，小而稳定的收益累积起来绝对惊人。\n" +
//                    "2.当下行情是否有推荐，我们说了算，是否要操作，您说了算！切忌挑肥拣瘦！\n" +
//                    "3.炒股和做生意一样请将盈利部分，及时从证券账户转到银行账户，切勿让您的本金和利润一起“滚雪球”。\n",position);

        Log.d("推送数据11",MsgUtil.SwitchString(item));


//        linearLayout= helper.getView(R.id.el_pushlist);
//        linearLayout.removeAllViews();
//        //消息内容
//        if(item.getRemark()!=null&&!item.getRemark().equals("")){
//            View view=View.inflate(context,R.layout.item_pushlist1,null);
//            PushList  entity=new PushList(null,item.getRemark());
//            ViewHolder1 viewHolder = new ViewHolder1(view, entity);
//            viewHolder.refreshUI1();
//            linearLayout.addItem(view);
//
//        }
//        //分享时间
//        if(item.getFxtime()!=null&&!item.getFxtime().equals("")){
//            View view=View.inflate(context,R.layout.item_pushlist2,null);
//            PushList  entity=new PushList("分享时间",item.getFxtime());
//            ViewHolder2 viewHolder = new ViewHolder2(view, entity);
//            viewHolder.refreshUI1();
//            linearLayout.addItem(view);
//
//        }
//        //代码名称
//        if(item.getDmname()!=null&&!item.getDmname().equals("")){
//            View view=View.inflate(context,R.layout.item_pushlist2,null);
//            PushList  entity=new PushList("代码名称",item.getDmname());
//            ViewHolder2 viewHolder = new ViewHolder2(view, entity);
//            viewHolder.refreshUI1();
//            linearLayout.addItem(view);
//        }
//        //分享理由
//        if(item.getFxcontent()!=null&&!item.getFxcontent().equals("")){
//            View view=View.inflate(context,R.layout.item_pushlist3,null);
//            PushList  entity=new PushList("分享理由",item.getFxcontent());
//            ViewHolder3 viewHolder = new ViewHolder3(view, entity);
//            viewHolder.refreshUI1();
//            linearLayout.addItem(view);
//        }
//        //介入区间
//        if(item.getSection()!=null&&!item.getSection().equals("")){
//            View view=View.inflate(context,R.layout.item_pushlist2,null);
//            PushList  entity=new PushList("介入区间",item.getSection());
//            ViewHolder2 viewHolder = new ViewHolder2(view, entity);
//            viewHolder.refreshUI1();
//            linearLayout.addItem(view);
//        }
//        //当前价格
//        if(item.getFxmoney()!=null&&!item.getFxtime().equals("")){
//            View view=View.inflate(context,R.layout.item_pushlist2,null);
//            PushList  entity=new PushList("当前价格",item.getFxmoney());
//            ViewHolder2 viewHolder = new ViewHolder2(view, entity);
//            viewHolder.refreshUI1();
//            linearLayout.addItem(view);
//
//        }
//        //止损价格
//        if(item.getZsmoney()!=null&&!item.getZsmoney().equals("")){
//            View view=View.inflate(context,R.layout.item_pushlist2,null);
//            PushList  entity=new PushList("止损价格",item.getZsmoney());
//            ViewHolder2 viewHolder = new ViewHolder2(view, entity);
//            viewHolder.refreshUI1();
//            linearLayout.addItem(view);
//
//        }
//        //温馨提示
//        if(item.getMention()!=null&&!item.getMention().equals("")){
//            View view=View.inflate(context,R.layout.item_pushlist3,null);
//            PushList  entity=new PushList("温馨提示",item.getMention());
//            ViewHolder3 viewHolder = new ViewHolder3(view, entity);
//            viewHolder.refreshUI1();
//            linearLayout.addItem(view);
//        }
//
//        linearLayout.setOnStateChangeListener(new ExpandableLinearLayout.OnStateChangeListener() {
//            @Override
//            public void onStateChanged(boolean isExpanded) {
//                Log.d("展开状态",isExpanded+"");
//                doArrowAnim(isExpanded);//根据状态箭头旋转
//
//                //根据状态更改文字提示
//                if (isExpanded) {
//                    //展开
//                    zhankai.setText("收起");
//                } else {
//                    zhankai.setText("展开");
//                }
//            }
//        });
//
//
//        rlBottom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showToast(helper.getLayoutPosition()+"");
//                linearLayout.toggle();
//            }
//        });







    }





    // 箭头的动画
//    private void doArrowAnim(boolean isExpand) {
//        if (isExpand) {
//            // 当前是展开，箭头由下变为上
//            ObjectAnimator.ofFloat(imageView, "rotation", 0, 180).start();
//        } else {
//            // 当前是收起，箭头由上变为下
//            ObjectAnimator.ofFloat(imageView, "rotation", -180, 0).start();
//        }
//    }




//    class ViewHolder1 {
//        @BindView(R.id.tv_pushlist1)
//        TextView textView;
//
//        PushList pushList;
//
//        public ViewHolder1(View view, PushList pushList) {
//            ButterKnife.bind(this, view);
//            this.pushList = pushList;
//        }
//
//        private void refreshUI1() {
//            textView.setText( pushList.getContent());
//        }
//    }
//    class ViewHolder2 {
//        @BindView(R.id.tv_pushlist2_title)
//        TextView title;
//        @BindView(R.id.tv_pushlist2_content)
//        TextView content;
//        PushList pushList;
//
//        public ViewHolder2(View view, PushList pushList) {
//            ButterKnife.bind(this, view);
//            this.pushList = pushList;
//        }
//
//        private void refreshUI1() {
//            content.setText( pushList.getContent());
//            title.setText( pushList.getName());
//        }
//    }
//    class ViewHolder3 {
//        @BindView(R.id.tv_pushlist3_title)
//        TextView title;
//        @BindView(R.id.tv_pushlist3_content)
//        TextView content;
//        PushList pushList;
//
//        public ViewHolder3(View view, PushList pushList) {
//            ButterKnife.bind(this, view);
//            this.pushList = pushList;
//        }
//
//        private void refreshUI1() {
//            content.setText( pushList.getContent());
//            title.setText( pushList.getName());
//        }
//    }

}
