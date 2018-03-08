package com.yiyuaninfo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.library.BaseQuickAdapter;
import com.scrollablelayout.ScrollableHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.yiyuaninfo.Activity.MsgInfoActivity;
import com.yiyuaninfo.Activity.NewsDetailActivity;
import com.yiyuaninfo.Activity.NewsImageActivity;
import com.yiyuaninfo.Interface.HotEntityBiz;
import com.yiyuaninfo.Interface.HotEntityChangeBiz;
import com.yiyuaninfo.Interface.HotEntityMoreBiz;
import com.yiyuaninfo.Listener.LoadMoreListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.HotAdapter;
import com.yiyuaninfo.adapter.HotHeaderAdapter;
import com.yiyuaninfo.entity.HotArrBean;
import com.yiyuaninfo.entity.HotEntity;
import com.yiyuaninfo.entity.HotEntityMore;
import com.yiyuaninfo.entity.HotTagChangeEntity;
import com.yiyuaninfo.entity.Info;
import com.yiyuaninfo.entity.InfoArrBean;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.entity.TagArrBean;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.ConstanceValue;
import com.yiyuaninfo.util.MyScrollableHelper;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.FlowLayout;
import com.yiyuaninfo.view.MyDecoration;
import com.yiyuaninfo.view.WrapContentHeightViewPager;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2017/2/23.
 */

public class Hotfragment extends TextBaseFragment    {
    FlowLayout flowLayout;
   RecyclerView  recyclerViewHotheader;
    RecyclerView  recyclerViewHot;
    //全局变量   选中的标签
    private  TextView  textViewSelected;
    private HotAdapter hotAdapter;
    private HotHeaderAdapter  hotheaderadapter;
    private View    viewheader;
    private List<TagArrBean>  listtag=new ArrayList<>();
    private List<InfoArrBean>  listinfo=new ArrayList<>();
    private List<InfoArrBean>  morelist=new ArrayList<>();
    private List<HotArrBean>  listhot=new ArrayList<>();
    private int  id=0;
    private TextView   tvmore;
    private View  view;
    WrapContentHeightViewPager vp;
    int index=0;
    private static final int DELAY_MILLIS = 1500;

    private int mShowType = 0;
    private ImageView  imageView;
    private Animation myAlphaAnimation;//声明Animation类的对象
    //每次请求数据的条数
    private static final int PAGE_SIZE = 10;
    private boolean isErr=true;
    private int mCurrentCounter = 0;
    private static final int TOTAL_COUNTER = 18;
    private String  classid;



//    public void setViewPage(WrapContentHeightViewPager vp, int index){
//      // this.vp=vp;
//        this.index=index;
//    }
    public void setViewPage(){
    }

    private MyHandler mHandler = new MyHandler(this);

    private int count=0;

    private String type,state;

    private String lastid;
    InfoArrBean  news;
    public static Hotfragment newInstance(String title) {


        Hotfragment f = new Hotfragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_hot, container, false);
       viewheader= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hot_header, null);
        imageView=(ImageView)viewheader.findViewById(R.id.iv_hotheader_refresh);
        imageView.setImageResource(R.drawable.home_refresh);
        myAlphaAnimation= AnimationUtils.loadAnimation(getActivity(), R.anim.loading);
        myAlphaAnimation.setInterpolator(new LinearInterpolator());

//        if(vp!=null){
//
//        vp.setObjectForPosition(view,index);
//        }
        flowLayout=(FlowLayout)viewheader.findViewById(R.id.flowlayout_hot_header);
        recyclerViewHotheader=(RecyclerView)viewheader.findViewById(R.id.recycle_hot_header);
        recyclerViewHot=(RecyclerView)view.findViewById(R.id.recycle_hot);
        tvmore=(TextView)viewheader.findViewById(R.id.tv_hot_more);
        RecyclerView.LayoutManager  manager=new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager  manager1=new LinearLayoutManager(getActivity());

        recyclerViewHot.setLayoutManager(manager);
        recyclerViewHotheader.setLayoutManager(manager1);
        recyclerViewHot.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));


        recyclerViewHotheader.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        recyclerViewHotheader.setFocusableInTouchMode(false);

        tvmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Network.isConnected()){


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getChangeData();

                    }
                },1000);
                if(myAlphaAnimation!=null){
                    imageView.startAnimation(myAlphaAnimation);

                }
                }else {
                    ToastUtils.showToast("暂无网络，加载失败");

                }

            }
        });




        recyclerViewHot.setNestedScrollingEnabled(true);
        getData();




//        recyclerViewHot.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                    Log.d("recycleview的状态",recyclerView.canScrollVertically(1)+"");
//                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
//
//                }
//            }
//        });


        return view;


    }


    public void getData() {
        RetrofitUtil.getretrofitContext(getActivity()).create(HotEntityBiz.class).getData("change",id%3+1).enqueue(new Callback<HotEntity>() {
            @Override
            public void onResponse(Call<HotEntity> call, final Response<HotEntity> response) {
               // Log.v("热点数据",response.body().getTag_arr().toString());
                listinfo=response.body().getInfo_arr();
                listhot=response.body().getHot_arr();
                listtag=response.body().getTag_arr();
                classid=listtag.get(0).getId();
                lastid=response.body().getLastid();
                hotAdapter=new HotAdapter(listinfo,getActivity());
                hotheaderadapter=new HotHeaderAdapter(listhot);
                recyclerViewHot.setAdapter(hotAdapter);
                hotAdapter.addHeaderView(viewheader);
                recyclerViewHotheader.setAdapter(hotheaderadapter);
                setTags(listtag);

                hotheaderadapter.setOnItemClickListener(new com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(com.chad.library.adapter.base.BaseQuickAdapter adapter, View view, int position) {
                        HotArrBean  entity=(HotArrBean) adapter.getData().get(position);
                        CommonUtil.goAactivity(getActivity(), MsgInfoActivity.class,"msgurl",entity.getRlink());
                    }
                });

                hotAdapter.setOnItemClickListener(new com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(com.chad.library.adapter.base.BaseQuickAdapter adapter, View view, int position) {
                          news=(InfoArrBean) adapter.getData().get(position);
                        if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_GALLERY)) {
                            Intent intent = new Intent(getActivity(), NewsImageActivity.class);
                            intent.putExtra("PicarrBean", (Serializable) news.getPicarr());
                            getActivity().startActivity(intent);
                            //CommonUtil.goAactivity(getActivity(), NewsImageActivity.class);
                        } else {


                            /**
                             * id : 17673
                             * title : 金砖国家领导人第九次会晤举行
                             * source : 人民网
                             * description : 图集摘要
                             * picurl :
                             * picarr : [{"img":"uploads/image/20170906/1504661674.jpg","description":"图片1的描述"},{"img":"uploads/image/20170906/1504669411.jpg","description":"图片2的描述"},{"img":"uploads/image/20170906/1504666992.jpg","description":"图片3的描述"},{"img":"uploads/image/20170906/1504664929.jpg","description":"图片4的描述"}]
                             * posttime : 2017-09-06 09:26:58
                             * keywords : 图集
                             * picstate : 4
                             */
                            NewsEntity.HangqingBean entity = new NewsEntity.HangqingBean(
                                    news.getId(),
                                    news.getTitle(),
                                    news.getSource(),
                                    news.getDescription(),
                                    news.getPicurl(),
                                    news.getPicarr(),
                                    news.getPosttime(),
                                    news.getKeywords(),
                                    news.getPicstate()

                            );
                            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("entity", entity);
                            intent.putExtras(bundle);
                            //startActivity(intent);
                            startActivityForResult(intent,2);

//
//                    CommonUtil.goAactivity(context, NewsDetailActivity.class, "newsurl",
//                            Constants.NEWSURL.concat("id=" + news.getId() + "&userid="),
//                            "newsid",news.getId(),
//                            "newstitle",news.getTitle() );


                        }


                    }
                });
//                baseViewHolder.setOnClickListener(R.id.ll_home_item, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //  CommonUtil.goAactivity(context, NewsDetailActivity.class, "newsurl",
//                        //                  Constants.NEWSURL.concat("id=" +news.getId() + "&userid=" ));
//                        baseViewHolder.setOnClickListener(R.id.ll_home_item, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                //  CommonUtil.goAactivity(context, NewsDetailActivity.class, "newsurl",
//                                //                  Constants.NEWSURL.concat("id=" +news.getId() + "&userid=" ));
//
//                                if (news.getPicstate().equals(ConstanceValue.ARTICLE_GENRE_GALLERY)) {
//                                    Intent intent = new Intent(context, NewsImageActivity.class);
//                                    intent.putExtra("PicarrBean", (Serializable) news.getPicarr());
//                                    context.startActivity(intent);
//                                    //CommonUtil.goAactivity(getActivity(), NewsImageActivity.class);
//                                } else {
//
//
//                                    /**
//                                     * id : 17673
//                                     * title : 金砖国家领导人第九次会晤举行
//                                     * source : 人民网
//                                     * description : 图集摘要
//                                     * picurl :
//                                     * picarr : [{"img":"uploads/image/20170906/1504661674.jpg","description":"图片1的描述"},{"img":"uploads/image/20170906/1504669411.jpg","description":"图片2的描述"},{"img":"uploads/image/20170906/1504666992.jpg","description":"图片3的描述"},{"img":"uploads/image/20170906/1504664929.jpg","description":"图片4的描述"}]
//                                     * posttime : 2017-09-06 09:26:58
//                                     * keywords : 图集
//                                     * picstate : 4
//                                     */
//                                    NewsEntity.HangqingBean entity = new NewsEntity.HangqingBean(
//                                            news.getId(),
//                                            news.getTitle(),
//                                            news.getSource(),
//                                            news.getDescription(),
//                                            news.getPicurl(),
//                                            news.getPicarr(),
//                                            news.getPosttime(),
//                                            news.getKeywords(),
//                                            news.getPicstate()
//
//                                    );
//                                    Intent intent = new Intent(context, NewsDetailActivity.class);
//                                    Bundle bundle = new Bundle();
//                                    bundle.putSerializable("entity", entity);
//                                    intent.putExtras(bundle);
//                                    context.startActivity(intent);
//
////
////                    CommonUtil.goAactivity(context, NewsDetailActivity.class, "newsurl",
////                            Constants.NEWSURL.concat("id=" + news.getId() + "&userid="),
////                            "newsid",news.getId(),
////                            "newstitle",news.getTitle() );
//
//
//                                }
//
//                            }
//                        });
//
//
//
//                    }
//                });

//                Homefragment.setLoadMoreListener(new LoadMoreListener() {
//                    @Override
//                    public void loadmore(RefreshLayout refreshLayout) {
//                        getmoredata(classid,lastid,refreshLayout);
//
//                    }
//                });



//                hotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                        Log.d("点击热搜榜的文章样式",listinfo.get(position).getPicstate());
//                        System.out.print(listinfo.get(position).getPicstate()+"打印打印");
//                        if(listinfo.get(position).getPicstate().equals(ConstanceValue.ARTICLE_GENRE_GALLERY)){
//                            Intent intent=new Intent(getActivity(),NewsImageActivity.class);
//                            intent.putExtra("PicarrBean",(Serializable)listinfo.get(position).getPicarr());
//                            getActivity().startActivity(intent);
//                            //CommonUtil.goAactivity(getActivity(), NewsImageActivity.class);
//                        }else {
//
//                            CommonUtil.goAactivity(getActivity(), NewsDetailActivity.class, "newsurl",
//                                    Constants.NEWSURL.concat("id=" + listinfo.get(position).getId() + "&userid="));
//
//
//                        }
//
//                    }
//
//                });


                hotAdapter.setOnLoadMoreListener(new com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {

                        if(Network.isConnected()){
                        getmoredata(classid,lastid);
                        }else {

                            ToastUtils.showToast("暂无网络，加载失败");
                            hotAdapter.loadMoreFail();
                        }

                    }
                },recyclerViewHot);

                //hotAdapter.setLoadMoreType(LoadMoreType.APAY);

    //                hotAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
    //                    @Override
    //                    public void onLoadMoreRequested() {
    //                     mHandler.postDelayed(new Runnable() {
    //                         @Override
    //                         public void run() {
    //                             getMoreData(classid,lastid);
    //                             hotAdapter.loadMoreEnd();
    //                         }
    //                     },1000);
    //
    //                    }
    //                });
//                hotAdapter.setOnLoadMoreListener(new com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener() {
//                    @Override
//                    public void onLoadMoreRequested() {
//                        recyclerViewHot.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                getMoreData(classid,lastid);
//
//                            }
//                        },3000);
//
//
//
//
//                    }
//                },recyclerViewHot);
//
           }

            @Override
            public void onFailure(Call<HotEntity> call, Throwable t) {

            }
        });
        id++;




    }


    public   void   getmoredata(String id, String moreid) {
        RetrofitUtil.getretrofitContext(getActivity()).create(HotEntityMoreBiz.class).getData("loadmorehot",id,moreid).enqueue(new Callback<HotEntityMore>() {
            @Override
            public void onResponse(Call<HotEntityMore> call, Response<HotEntityMore> response) {

                morelist=response.body().getInfo_arr();

                lastid=response.body().getLastid();
                 if(morelist.size()!=0){
                     hotAdapter.addData(morelist);
                     hotAdapter.loadMoreComplete();
                 }else {
                     hotAdapter.loadMoreEnd();
                 }

            }

            @Override
            public void onFailure(Call<HotEntityMore> call, Throwable t) {


            }
        });
    }
//    public   void   getmoredata(String id, String moreid, final RefreshLayout refreshLayout) {
//        RetrofitUtil.getretrofit().create(HotEntityMoreBiz.class).getData("loadmorehot",id,moreid).enqueue(new Callback<HotEntityMore>() {
//            @Override
//            public void onResponse(Call<HotEntityMore> call, Response<HotEntityMore> response) {
//
//                morelist=response.body().getInfo_arr();
//
//                lastid=response.body().getLastid();
//                 if(morelist.size()!=0){
//                     hotAdapter.addData(morelist);
//                     refreshLayout.finishLoadmore();
//                 }else {
//                     refreshLayout.setLoadmoreFinished(true);
//                 }
//
//            }
//
//            @Override
//            public void onFailure(Call<HotEntityMore> call, Throwable t) {
//
//
//            }
//        });
//    }


    private void getChangeData() {
        RetrofitUtil.getretrofitContext(getActivity()).create(HotEntityBiz.class).getData("change",id%3+1).enqueue(new Callback<HotEntity>() {
            @Override
            public void onResponse(Call<HotEntity> call, Response<HotEntity> response) {
                Log.d("热点数据",response.body().getTag_arr().toString());

                listinfo=response.body().getInfo_arr();
                listhot=response.body().getHot_arr();
                listtag=response.body().getTag_arr();
                hotAdapter.setNewData(listinfo);
                hotheaderadapter.setNewData(listhot);
                setTags(listtag);

            }

            @Override
            public void onFailure(Call<HotEntity> call, Throwable t) {

            }
        });
        id++;

    }

    private void setTags(final List<TagArrBean>  list ) {
        textViewSelected=new TextView(getActivity());
        flowLayout.removeAllViews();
         int  i=0;
        for ( int j = 0; j < list.size(); j++) {


            final   TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.item_flow_layout, flowLayout, false);
            tv.setBackgroundResource(R.drawable.popup_corner_tag_false);
            tv.setTextColor(getResources().getColor(R.color.black));
            tv.setText(list.get(j).getClassname());
            tv.setTag(R.id.tag,list.get(j).getId());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Network.isConnected()) {


                        textViewSelected.setBackgroundResource(R.drawable.popup_corner_tag_false);
                        textViewSelected.setTextColor(getResources().getColor(R.color.black));

                        textViewSelected = (TextView) v;
                        textViewSelected.setBackgroundResource(R.drawable.popup_select_corner);
                        textViewSelected.setTextColor(getResources().getColor(R.color.white));
                        // Toast.makeText(getActivity(), "" + ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                        Log.d("点击标签的id", v.getTag(R.id.tag) + "");
                        changetag(v.getTag(R.id.tag) + "");
                    }else {
                        ToastUtils.showToast("暂无网络");

                    }
                }
            });
            flowLayout.addView(tv);

            if(i==0){
                textViewSelected=tv;
                textViewSelected.setTextColor(getResources().getColor(R.color.white));
                textViewSelected.setBackgroundResource(R.drawable.popup_select_corner);
            }
            i++;
        }

    }






    private void changetag( String  id ) {
        Log.d("热点更换数据id",id);


        RetrofitUtil.getretrofitContext(getActivity()).create(HotEntityChangeBiz.class).getData("classify",id).enqueue(new Callback<HotTagChangeEntity>() {
            @Override
            public void onResponse(Call<HotTagChangeEntity> call, Response<HotTagChangeEntity> response) {
                Log.d("热点更换数据123",response.body().getHot_arr()+"\n"+response.body().getInfo_arr());
                Log.d("热点更换数据123",response.body().toString());
                hotAdapter.setNewData(response.body().getInfo_arr());
                if(response.body().getHot_arr()!=null){
                    hotheaderadapter.setNewData(response.body().getHot_arr());

                }
                hotheaderadapter.setNewData(response.body().getHot_arr());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.clearAnimation();

                    }
                },2000);
            }

            @Override
            public void onFailure(Call<HotTagChangeEntity> call, Throwable t) {

            }
        });
    }




    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
//            getData(page=1);
//        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // progressDialog=new ProgressDialog(getContext());
        // progressDialog.setMessage("加载中...");
    }

    @Override
    public View getScrollableView() {


        return recyclerViewHot;
    }

    @Override
    public void pullToRefresh() {

    }

    @Override
    public void refreshComplete() {

    }


    @Override
    public void recycleviewTop() {




    }


    private static class MyHandler extends Handler {
        private WeakReference<Hotfragment> activityWeakReference;

        public MyHandler(Hotfragment activity) {
            activityWeakReference = new WeakReference<Hotfragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Hotfragment activity = activityWeakReference.get();
            if (activity == null) {
                return;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("TAG111","requestCode="+requestCode+"resultCode"+resultCode);
        if(requestCode==2){

            if(resultCode==RESULT_OK){
                listinfo.remove(news);
                hotAdapter.notifyDataSetChanged();
            }
        }
    }
}
