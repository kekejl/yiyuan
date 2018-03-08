package com.yiyuaninfo.Fragment;

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

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yiyuaninfo.Activity.MsgInfoActivity;
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
import com.yiyuaninfo.entity.InfoArrBean;
import com.yiyuaninfo.entity.TagArrBean;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.view.FlowLayout;
import com.yiyuaninfo.view.MyDecoration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/23.
 */

public class Hotfragment_text extends Fragment {
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
   // WrapContentHeightViewPager vp;
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
    public static Hotfragment_text newInstance(String title) {


        Hotfragment_text f = new Hotfragment_text();
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
        tvmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getChangeData();

                    }
                },1000);
                if(myAlphaAnimation!=null){
                    imageView.startAnimation(myAlphaAnimation);

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
        RetrofitUtil.getretrofit().create(HotEntityBiz.class).getData("change",id%3+1).enqueue(new Callback<HotEntity>() {
            @Override
            public void onResponse(Call<HotEntity> call, final Response<HotEntity> response) {
                Log.v("热点数据",response.body().getTag_arr().toString());
                listinfo=response.body().getInfo_arr();
                listhot=response.body().getHot_arr();
                listtag=response.body().getTag_arr();
                classid=listtag.get(0).getId();
                lastid=response.body().getLastid();
                hotAdapter=new HotAdapter(listinfo,getActivity());

                recyclerViewHot.setAdapter(hotAdapter);
               // hotAdapter.addHeaderView(viewheader);
                hotheaderadapter=new HotHeaderAdapter(listhot);
                recyclerViewHotheader.setAdapter(hotheaderadapter);
                setTags(listtag);
                hotheaderadapter.setOnItemClickListener(new com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(com.chad.library.adapter.base.BaseQuickAdapter adapter, View view, int position) {
                        HotArrBean  entity=(HotArrBean) adapter.getData().get(position);
                        CommonUtil.goAactivity(getActivity(), MsgInfoActivity.class,"msgurl",entity.getRlink());
                    }
                });

                Homefragment.setLoadMoreListener(new LoadMoreListener() {
                    @Override
                    public void loadmore(RefreshLayout refreshLayout) {
                        getmoredata(classid,lastid,refreshLayout);

                    }
                });



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


//                hotAdapter.setOnLoadMoreListener(new com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener() {
//                    @Override
//                    public void onLoadMoreRequested() {
//                        getmoredata(classid,lastid);
//
//                    }
//                },recyclerViewHot);

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


    public   void   getmoredata(String id, String moreid, final RefreshLayout refreshLayout) {
        RetrofitUtil.getretrofit().create(HotEntityMoreBiz.class).getData("loadmorehot",id,moreid).enqueue(new Callback<HotEntityMore>() {
            @Override
            public void onResponse(Call<HotEntityMore> call, Response<HotEntityMore> response) {

                morelist=response.body().getInfo_arr();

                lastid=response.body().getLastid();
                 if(morelist.size()!=0){
                     hotAdapter.addData(morelist);
                     refreshLayout.finishLoadmore();
                 }else {
                     refreshLayout.setLoadmoreFinished(true);
                 }

            }

            @Override
            public void onFailure(Call<HotEntityMore> call, Throwable t) {


            }
        });
    }


    private void getChangeData() {
        RetrofitUtil.getretrofit().create(HotEntityBiz.class).getData("change",id%3+1).enqueue(new Callback<HotEntity>() {
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
                    textViewSelected.setBackgroundResource(R.drawable.popup_corner_tag_false);
                    textViewSelected.setTextColor(getResources().getColor(R.color.black   ));

                    textViewSelected=(TextView) v;
                    textViewSelected.setBackgroundResource(R.drawable.popup_select_corner);
                    textViewSelected.setTextColor(getResources().getColor(R.color.white));
                   // Toast.makeText(getActivity(), "" + ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                     Log.d("点击标签的id", v.getTag(R.id.tag)+"");
                    changetag(v.getTag(R.id.tag)+"");
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

    /**
     *
     * 点击标签 切换数据
     * @param id
     */
    private void changetag( String  id ) {
        Log.d("热点更换数据id",id);


        RetrofitUtil.getretrofit().create(HotEntityChangeBiz.class).getData("classify",id).enqueue(new Callback<HotTagChangeEntity>() {
            @Override
            public void onResponse(Call<HotTagChangeEntity> call, Response<HotTagChangeEntity> response) {
                Log.d("热点更换数据123",response.body().getHot_arr()+"\n"+response.body().getInfo_arr());
                Log.d("热点更换数据123",response.body().toString());
                hotAdapter.setNewData(response.body().getInfo_arr());
                if(response.body().getHot_arr()!=null){
                    hotheaderadapter.setNewData(response.body().getHot_arr());

                }
                //hotheaderadapter.setNewData(response.body().getHot_arr());
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




    private static class MyHandler extends Handler {
        private WeakReference<Hotfragment_text> activityWeakReference;

        public MyHandler(Hotfragment_text activity) {
            activityWeakReference = new WeakReference<Hotfragment_text>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Hotfragment_text activity = activityWeakReference.get();
            if (activity == null) {
                return;
            }
        }
    }



}
