package com.yiyuaninfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.Activity.FinderActivity;
import com.yiyuaninfo.Activity.VipActivity;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.CompanyDetailsSection;
import com.yiyuaninfo.entity.MallSection;
import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.entity.Text;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.URLImageParser;

import java.net.URL;
import java.util.List;

/**
 * Created by gaocongcong on 2017/9/11.
 */

public class CompanyDetailsSectionAdapter extends BaseSectionQuickAdapter<CompanyDetailsSection, BaseViewHolder> {

    private Context context;
    private TextView  textView;
    String html = "<html><head><title>TextView使用HTML</title></head><body><p><strong>强调</strong></p><p><em>斜体</em></p>"
            + "<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p><p><font color=\"#aabb00\">颜色1"
            + "</p><p><font color=\"#00bbaa\">颜色2</p><p><font color=\"#aabb00\">颜色1"
            + "</p><p><font color=\"#00bbaa\">颜色2</p><p><font color=\"#aabb00\">颜色1"
            + "</p><p><font color=\"#00bbaa\">颜色2</p><p><font color=\"#aabb00\">颜色1"
            + "</p><p><font color=\"#00bbaa\">颜色2</p><p><font color=\"#aabb00\">颜色1"
            + "</p><p><font color=\"#00bbaa\">颜色2</p><h1>标题1</h1><h3>标题2</h3><h6>标题3</h6><p>大于>小于<</p><p>"
            + "下面是网络图片</p><img src=\"http://yyapp.1yuaninfo.com/app/application/userhead/1507529879iwlkxj.jpg\"/></body></html>";
    //这里面的resource就是fromhtml函数的第一个参数里面的含有的url
    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Log.i("RG", "source---?>>>" + source);
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                Log.i("RG", "url---?>>>" + url);
                drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            Log.i("RG", "url---?>>>" + url);
            return drawable;
        }
    };
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public CompanyDetailsSectionAdapter(int layoutResId, int sectionHeadResId, List<CompanyDetailsSection> data,Context context) {
        super(layoutResId, sectionHeadResId, data);
        this.context=context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, CompanyDetailsSection item) {
        helper.setText(R.id.tv_mall_header,item.header);

    }

    @Override
    protected void convert(BaseViewHolder helper, final CompanyDetailsSection item) {

        Log.d("项目详情的id",helper.getLayoutPosition()+"");
        textView=(TextView)helper.getView(R.id.tv_companydetail_text);


       if(helper.getLayoutPosition()==1){
           helper.setVisible(R.id.tv_companydetail_text,true);
           helper.setVisible(R.id.ll_companydetail,false);
           if(item.t.getString()==null){
               helper.setText(R.id.tv_companydetail_text,"");
           }else {
              // URLImageParser imageGetter = new URLImageParser(textView);

              // textView.setText(Html.fromHtml(item.t.getString(), imgGetter, null));
               textView.setText(Html.fromHtml(html, imgGetter, null));

              // helper.setText(R.id.tv_companydetail_text,"\u3000\u3000"+item.t.getString().replaceAll("\r\n","\r\n"+"\u3000\u3000"));


           }

       }else if(helper.getLayoutPosition()==3){


           helper.setVisible(R.id.tv_companydetail_text,true);
           helper.setVisible(R.id.ll_companydetail,false);
           if(item.t.getString()==null){
               helper.setText(R.id.tv_companydetail_text,"");
           }else {
               URLImageParser imageGetter = new URLImageParser(textView);

               textView.setText(Html.fromHtml(item.t.getString(), imageGetter, null));


              // helper.setText(R.id.tv_companydetail_text,item.t.getString());


           }
       }else {
           helper.setVisible(R.id.tv_companydetail_text,false);
           helper.setVisible(R.id.ll_companydetail,true);

           ImageLoaderUtils.displayImage(item.t.getProd_arr().getCom_pic(), (ImageView) helper.getView(R.id.iv_companydetail));
           helper.setText(R.id.tv_cp_companyname,item.t.getProd_arr().getYname());
           helper.setText(R.id.tv_cp_price,"￥"+item.t.getProd_arr().getYprice());
       }
      // helper.addOnClickListener(R.id.ll_companydetail_cp);


        helper.getView(R.id.ll_companydetail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product.ProArrBean  entity=new Product.ProArrBean(item.t.getProd_arr().getId());

                Intent intent=new Intent(context, VipActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("entity",entity);
                intent.putExtra("type","2");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }
}
