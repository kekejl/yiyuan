package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Interface.CompanyDetailsBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.CompanyDetailCPAdapter;
import com.yiyuaninfo.adapter.CompanyDetailsSectionAdapter;
import com.yiyuaninfo.adapter.NoteAdapter;
import com.yiyuaninfo.adapter.RecyclerViewAdapter;
import com.yiyuaninfo.adapter.TextAdapter;
import com.yiyuaninfo.entity.CompanyDetails;
import com.yiyuaninfo.entity.CompanyDetailsMode;
import com.yiyuaninfo.entity.CompanyDetailsProduct;
import com.yiyuaninfo.entity.CompanyDetailsSection;
import com.yiyuaninfo.entity.Note;
import com.yiyuaninfo.entity.Product;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.URLImageParser;
import com.yiyuaninfo.view.FullLinearLayout1;
import com.yiyuaninfo.view.MyDecoration;
import com.yiyuaninfo.view.ScrollViewTabIndicator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaocongcong on 2017/8/13.
 */

public class FinderActivity extends BaseActivity   implements NestedScrollView.OnScrollChangeListener {
    private List<CompanyDetailsProduct> list = new ArrayList<>();
    private CompanyDetailCPAdapter  adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView  recyclerView;
    private String id;
    private ImageView  imageView;
    private TextView  name,zijin ,tag;
    private ScrollViewTabIndicator   mTab;
    private ScrollViewTabIndicator   mTab2;
    private NestedScrollView mSv;
    private int[] mTabMiddleLocation = new int[2];
    private int[] mTabTopLocation = new int[2];
    private TextView  jianjie,dongtai;
    private View  mErrorView;
    private LinearLayout   relativeLayout;
    @Override
    protected int getContentView() {
        return R.layout.activity_finder;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        //setToolBarTitle("1111111");
        Intent  intent=getIntent();
        id=intent.getStringExtra("comid");
        mSv = (NestedScrollView) findViewById(R.id.sv);
        mTab=(ScrollViewTabIndicator)findViewById(R.id.tab);
        mTab2=(ScrollViewTabIndicator)findViewById(R.id.tab2);
        relativeLayout=(LinearLayout)mSv.getParent();
        initErrorPage();

        View view1 = findViewById(R.id.ll_finder_gsjj);
        View view2 = findViewById(R.id.ll_finder_gsdt);
        View view3 = findViewById(R.id.ll_finder_cptj);
        List<String> names = new ArrayList<>();
        names.add("公司简介");
        names.add("公司动态");
        names.add("产品推荐");
        List<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        mTab.setScrollView(mSv,this,names,views);
        //将mTab本身作为参数传入mTab2已达到同步状态
        mTab2.setScrollView(mSv,mTab,names,views);

        jianjie=(TextView)findViewById(R.id.tv_finder_gsjj);
        dongtai=(TextView)findViewById(R.id.tv_finder_gsdt);
        imageView=(ImageView)findViewById(R.id.iv_companydetail_logo);
        name=(TextView)findViewById(R.id.tv_companydetail_name);
        zijin=(TextView)findViewById(R.id.tv_companydetail_zj);
        tag=(TextView)findViewById(R.id.tv_finder_tag);
        recyclerView=(RecyclerView)findViewById(R.id.rv_finder_cp);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        intData();
        Log.d("tab点击的id", id);
    }
    private void intData() {
        RetrofitUtil.getretrofit().create(CompanyDetailsBiz.class).getData(id).enqueue(new Callback<CompanyDetails>() {
            @Override
            public void onResponse(Call<CompanyDetails> call, Response<CompanyDetails> response) {
                 if(response.body()==null){
                     showErrorPage();
                 }else {

                Log.d("公司详情数据",response.body().toString());


                if(response.body().getCom_intro_arr().getLogo().startsWith("http")){
                    ImageLoaderUtils.displayImage(response.body().getCom_intro_arr().getLogo(),imageView);

                }else {

                ImageLoaderUtils.displayImage(Constants.XINGMU+response.body().getCom_intro_arr().getLogo(),imageView);
                }
                name.setText(response.body().getCom_intro_arr().getGname());
                zijin.setText("资金量:"+response.body().getCom_intro_arr().getRegmoney()+"万元");
                tag.setText(response.body().getCom_intro_arr().getAuth_tag());

                URLImageParser imageGetter = new URLImageParser(jianjie);

                jianjie.setText(Html.fromHtml(response.body().getCom_intro_arr().getIntroduction(), imageGetter, null));


                URLImageParser imageGetterdongtai = new URLImageParser(dongtai);
                dongtai.setText(Html.fromHtml(response.body().getCom_intro_arr().getTrendcontent(), imageGetterdongtai, null));

               // dongtai.setText(response.body().getCom_intro_arr().getTrendcontent());

                     if(response.body().getProd_arr().size()!=0){
                     list=response.body().getProd_arr();
                         adapter=new CompanyDetailCPAdapter(FinderActivity.this,R.layout.item_cp,list);
                         recyclerView.setAdapter(adapter);


                         adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                             @Override
                             public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                                 /**
                                  * id : 1
                                  * com_pic : logo
                                  * label : 1
                                  * introduce : 按时间的
                                  * part : 股票型
                                  * yname : 画龙点睛
                                  * ystate : 1
                                  * yprice : 1889
                                  */

                                 CompanyDetailsProduct   companyentity=(CompanyDetailsProduct)adapter.getData().get(position);
                                 /**
                                  *      id : 111
                                  * classid : 41
                                  *         ylogo : http://yyapp.1yuaninfo.com/app/application/img/1243.jpg
                                  *         yname : 红运当头
                                  * ystate : 0
                                  * datelimit : 12
                                  * expectcomment :
                                  * fitpeople : 短线操作思路
                                  * jgtime :
                                  * cgdate :
                                  * expectget :
                                  * lossprice :
                                  * history :
                                  * servermodel : 微信平台推送</br></br> 推送时间：每个交易日盘中实时推送（行情正常情况下）
                                  * addserver :
                                  *           yprice : 1680
                                  * ynum : 12
                                  * sellmodel : 0
                                  * content : 精选1支受当日热点新闻事件推动并在盘中有股价异动的优质上市公司，该公司股价预期在短时间内有大幅波动，结合近期披露的研报及新闻报道进行综合参考。
                                  * serverdetails :
                                  * addtime : 2017-08-02 11:17:41
                                  * type : 首证产品
                                  * top : false
                                  * deltime : 0
                                  * delstate :
                                  */
                                 Product.ProArrBean entity =new Product.ProArrBean(companyentity.getId());
                                 Intent intent = new Intent(FinderActivity.this, VipActivity.class);
                                 Bundle bundle = new Bundle();
                                 bundle.putSerializable("entity", entity);
                                 intent.putExtra("type", "2");
                                 intent.putExtras(bundle);
                                 startActivity(intent);

                             }
                         });

                     }else {

                     }
              //  adapter = new CompanyDetailsSectionAdapter(R.layout.item_companydetail, R.layout.item_mall_header, list,FinderActivity.this );
               //recyclerView.setAdapter(adapter);
                 }


            }

            @Override
            public void onFailure(Call<CompanyDetails> call, Throwable t) {

            }
        });

    }


    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        setVisibleAndGone();

    }

    private void setVisibleAndGone() {
        mTab2.getLocationOnScreen(mTabMiddleLocation);
        mTab.getLocationOnScreen(mTabTopLocation);
        if (mTabMiddleLocation[1] <= mTabTopLocation[1]) {
            mTab.setVisibility(View.VISIBLE);
            mTab2.setVisibility(View.INVISIBLE);
        } else {
            mTab.setVisibility(View.INVISIBLE);
            mTab2.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    public void showErrorPage() {
        relativeLayout.removeAllViews(); //移除加载网页错误时，默认的提示信息
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.addView(mErrorView, 0, layoutParams); //添加自定义的错误提示的View

    }

    /***
     * 显示加载失败时自定义的网页
     */
    public   void initErrorPage() {
        if (mErrorView == null) {
            mErrorView = View.inflate(this, R.layout.error_view, null);
        }
    }
}



















