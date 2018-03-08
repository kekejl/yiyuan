package com.yiyuaninfo.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yiyuaninfo.GlideImageloader;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.BigImageDelagate;
import com.yiyuaninfo.adapter.ThreeImageDelagate;
import com.yiyuaninfo.adapter.TowImageDelagate;
import com.yiyuaninfo.entity.ImageInfoType;
import com.yiyuaninfo.view.MyDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */

public class ContentFragment extends Fragment {
    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            };
    private String[] titles = {"行情中心",
            "沪深港通",
            "全球指数",
            "财经日历",
    };
    public static List<String > title = new ArrayList<>();
    public static List<?> images = new ArrayList<>();
    private Banner  banner;
    private View view;
    private View bannerview;
    private RecyclerView rcview;
    private LoadMoreWrapper loadMoreWrapper;
    private List<ImageInfoType>  imageInfoTypes=new ArrayList<>();
    private HeaderAndFooterWrapper  headerAndFooterWrapper;
    private int channel_id;
    private int type;
    private String text;

    public static ContentFragment newInstance(String   page) {

        Bundle args = new Bundle();
        args.putString("key", page);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {
        Bundle args = getArguments();
        text = args != null ? args.getString("text") : "";
        channel_id = args != null ? args.getInt("id", 0) : 0;
        type = args != null ? args.getInt("type", 0) : 0;

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_content,null);
        bannerview= LayoutInflater.from(getActivity()).inflate(R.layout.banner_hq,null);
        banner=(Banner)bannerview.findViewById(R.id.content_banner);
        rcview=(RecyclerView)view.findViewById(R.id.recycler_news);
        initView();
        getdata();
        return view;
    }
    private void initView() {
        List list = Arrays.asList(imageUrls);
        List list1=Arrays.asList(titles);
        images = new ArrayList(list);
        title=new ArrayList<>(list1);
        banner.setImages(images);
        banner.setBannerTitles(title);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new GlideImageloader());
        banner.start();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        rcview.setLayoutManager(manager);
        rcview.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
//        多种Item效果
        MultiItemTypeAdapter multadapter=new MultiItemTypeAdapter(getActivity(),imageInfoTypes);

        multadapter.addItemViewDelegate(new BigImageDelagate());
        multadapter.addItemViewDelegate(new TowImageDelagate());
        multadapter.addItemViewDelegate(new ThreeImageDelagate());




        loadMoreWrapper =new LoadMoreWrapper(multadapter);
        loadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        ImageInfoType msg = null;
                        msg = new ImageInfoType(3,"刷新的数据", Arrays.asList(R.drawable.a7,R.drawable.a6,R.drawable.a4));
                        imageInfoTypes.add(msg);
                        loadMoreWrapper.notifyDataSetChanged();

                    }
                }, 3000);
            }
        });
        multadapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(getActivity(),"点击",Toast.LENGTH_LONG).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rcview.setAdapter(loadMoreWrapper);
        if (type==1){
            headerAndFooterWrapper=new HeaderAndFooterWrapper(multadapter);
            headerAndFooterWrapper.addHeaderView(bannerview);
            rcview.setAdapter(headerAndFooterWrapper);
            headerAndFooterWrapper.notifyDataSetChanged();
        }


    }
    private void getdata() {
        imageInfoTypes.add(new ImageInfoType(1,"雄安概念股遭热捧 京津冀78只个股涨停", Arrays.asList(R.drawable.a1)));
        imageInfoTypes.add(new ImageInfoType(1,"股指震荡运行 向上趋势不变", Arrays.asList(R.drawable.a1)));
        imageInfoTypes.add(new ImageInfoType(2,"市场热点纷呈“雄安”“一带一路”主题共舞", Arrays.asList(R.drawable.a1)));
        imageInfoTypes.add(new ImageInfoType(1,"雄安概念股遭热捧 京津冀78只个股涨停", Arrays.asList(R.drawable.a1)));
        imageInfoTypes.add(new ImageInfoType(3,"股指震荡运行 向上趋势不变", Arrays.asList(R.drawable.a1,R.drawable.a2,R.drawable.a4)));
        imageInfoTypes.add(new ImageInfoType(1,"雄安概念股遭热捧 京津冀78只个股涨停", Arrays.asList(R.drawable.a1)));
        imageInfoTypes.add(new ImageInfoType(1,"股指震荡运行 向上趋势不变", Arrays.asList(R.drawable.a1)));
        imageInfoTypes.add(new ImageInfoType(3,"雄安概念股遭热捧 京津冀78只个股涨停", Arrays.asList(R.drawable.a1,R.drawable.a2,R.drawable.a6)));
        imageInfoTypes.add(new ImageInfoType(1,"雄安概念股遭热捧 京津冀78只个股涨停", Arrays.asList(R.drawable.a1)));
        imageInfoTypes.add(new ImageInfoType(1,"雄安概念股遭热捧 京津冀78只个股涨停", Arrays.asList(R.drawable.a1)));
        imageInfoTypes.add(new ImageInfoType(2,"雄安概念股遭热捧 京津冀78只个股涨停", Arrays.asList(R.drawable.a1)));
        imageInfoTypes.add(new ImageInfoType(1,"雄安概念股遭热捧 京津冀78只个股涨停", Arrays.asList(R.drawable.a1)));
        imageInfoTypes.add(new ImageInfoType(1,"雄安概念股遭热捧 京津冀78只个股涨停", Arrays.asList(R.drawable.a1)));
        imageInfoTypes.add(new ImageInfoType(3,"雄安概念股遭热捧 京津冀78只个股涨停", Arrays.asList(R.drawable.a1,R.drawable.a2,R.drawable.a5)));
    }

}
