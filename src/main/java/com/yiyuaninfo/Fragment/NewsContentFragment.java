package com.yiyuaninfo.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyuaninfo.Activity.MsgInfoActivity;
import com.yiyuaninfo.Activity.NewsDetailActivity;
import com.yiyuaninfo.Activity.NewsImageActivity;
import com.yiyuaninfo.Activity.RankActivity;
import com.yiyuaninfo.Activity.ShowDetailActivity;
import com.yiyuaninfo.Activity.VideoDetailActivity;
import com.yiyuaninfo.Activity.WebViewActivity;
import com.yiyuaninfo.GlideImageloader;
import com.yiyuaninfo.Interface.HotEntityMoreBiz;
import com.yiyuaninfo.Interface.MusicBiz;
import com.yiyuaninfo.Interface.NewsEntityBiz;
import com.yiyuaninfo.Interface.NewsEntityMoreBiz;
import com.yiyuaninfo.Interface.ShowEntityBiz;
import com.yiyuaninfo.Interface.ShowMoreEntityBiz;
import com.yiyuaninfo.Interface.VideoEntityBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.CircleImageView;
import com.yiyuaninfo.adapter.MusicAdapter;
import com.yiyuaninfo.adapter.NewsAdapter;
import com.yiyuaninfo.adapter.NewsHeaderAdapter;
import com.yiyuaninfo.adapter.ShowAdapter;
import com.yiyuaninfo.adapter.ShowHeaderAdapter;
import com.yiyuaninfo.adapter.Testadapter;
import com.yiyuaninfo.adapter.VideoAdapter;
import com.yiyuaninfo.entity.HotEntityMore;
import com.yiyuaninfo.entity.InfoArrBean;
import com.yiyuaninfo.entity.Music;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.entity.ShowEntity;
import com.yiyuaninfo.entity.ShowMroeEntity;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.entity.VideoEntity;
import com.yiyuaninfo.entity.XhArrBean;
import com.yiyuaninfo.server.MusicServer;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.ConstanceValue;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.EmptyUtil;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ShareUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.MyDecoration;
import com.yiyuaninfo.view.MyScrollview;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by gaocongcong on 2017/8/1.
 */

public class NewsContentFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    RecyclerView ry;
    private RecyclerView recyclerView;

    private String channel;
    private List<NewsEntity.HangqingBean> newslist = new ArrayList<>();
    private List<NewsEntity.HangqingBean> morelist = new ArrayList<>();
    private List<NewsEntity.RankArrBean> newsranklist = new ArrayList<>();
    private List<VideoEntity.VArrBean> videolist = new ArrayList<>();
    private List<VideoEntity.VArrBean> videomorelist = new ArrayList<>();
    private List<Music.MArrBean> musiclist = new ArrayList<>();
    private List<Music.MArrBean> musicmorelist = new ArrayList<>();
    private User.UserinfoBean userinfoBean;
    private NewsAdapter adapter;
    private VideoAdapter videoadapter;
    private List<XhArrBean> xhlist;
    private List<XhArrBean> xhlistmore = new ArrayList<>();
    private List<ShowEntity.TjArrBean> tjlist;
    private List<ShowEntity.LbArrBean> lblist;
    private Banner banner;
    private ShowAdapter showAdapter;
    public static List<String> images = new ArrayList<>();
    private MyScrollview scrollview;
    private View headview;
    private ShowHeaderAdapter showheaderAdapter;
    private NewsHeaderAdapter newsHeaderAdapter;
    private LinearLayout layout;
    private View viewph;
    private RecyclerView rvph;
    private TextView tvphmore;
    private View viewempty;
    private Integer[] ints = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
    List list = new ArrayList();
    private LinearLayout llmusic;
    private MusicAdapter musicAdapter;
    private TextView musicname;
    private TextView musicsinger;
    private TextView timecurr;
    private TextView timetotal;
    private CircleImageView imageViewmusic;
    private ImageView musicplay;
    private boolean isPlaying = false;
    private MusicReceiver mMusicReceiver;
    private int musicIndex = -1;
    private SeekBar mSeekBar;

    private boolean isErr = true;
    private int mCurrentCounter = 0;
    private int mMusicCounter = 0;
    private int mVideoCounter = 0;
    private int mShowCounter = 0;
    private String lastid;
    private String Musiclastid;
    private String Videolastid;
    private String Showlastid;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String ranklastid;
    private Intent serverIntent;
    private EmptyUtil  emptyUtil;
    NewsEntity.HangqingBean  entity;
    public static NewsContentFragment newInstance(String page) {

        Bundle args = new Bundle();
        args.putString("channel", page);
        NewsContentFragment fragment = new NewsContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (!isVisibleToUser) {
            JCVideoPlayer.releaseAllVideos();
            if (serverIntent != null) {

                if (isPlaying) {
                    musicplay.setImageResource(R.drawable.music_bf);
                    isPlaying = false;
                    serverIntent.putExtra(MusicServer.EXEC_COMMAND, MusicServer.PAUSE_MUSIC);
                    getActivity().startService(serverIntent);
                }
            }

        }

        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        channel = args != null ? args.getString("channel", "0") : "0";
        Log.d("频道fragment获取的数据", channel);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_newscontent, null);
        llmusic = (LinearLayout) view.findViewById(R.id.ll_news_music);
        musicname = (TextView) view.findViewById(R.id.tv_music_play_name);
        musicsinger = (TextView) view.findViewById(R.id.tv_music_play_singer);
        timecurr = (TextView) view.findViewById(R.id.tv_music_curr);
        timetotal = (TextView) view.findViewById(R.id.tv_music_total);
        imageViewmusic = (CircleImageView) view.findViewById(R.id.iv_music_head);
        musicplay = (ImageView) view.findViewById(R.id.iv_music_play);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar_music);
        musicplay.setImageResource(R.drawable.music_bf);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout_news);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#d43c33"));
        isPlaying = false;
        musicplay.setOnClickListener(this);


        headview = LayoutInflater.from(getActivity()).inflate(R.layout.item_show_header_image, null);
        viewph = LayoutInflater.from(getActivity()).inflate(R.layout.item_news_header, null);
        viewph.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        viewempty = LayoutInflater.from(getActivity()).inflate(R.layout.view_empty, null);
        viewempty.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


        layout = (LinearLayout) headview.findViewById(R.id.ll_show_header);
        layout.setVisibility(View.GONE);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_newscontent);
        ry = (RecyclerView) headview.findViewById(R.id.recycle_showheader);
        ry.setFocusableInTouchMode(false);

        rvph = (RecyclerView) viewph.findViewById(R.id.rv_news_header);
        rvph.setFocusableInTouchMode(false);
        tvphmore = (TextView) viewph.findViewById(R.id.tv_news_ph_more);
        tvphmore.setOnClickListener(this);
        tvphmore.setVisibility(View.GONE);
        banner = (Banner) headview.findViewById(R.id.banner_show);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(getActivity());
        LinearLayoutManager managerheader = new LinearLayoutManager(getActivity());
        managerheader.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvph.setLayoutManager(manager1);
        ry.setLayoutManager(managerheader);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        rvph.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));


//        if (SPUtil.isLogin(getActivity())) {
//            userinfoBean = SPUtil.getUser(getActivity());
//        }

        if (channel.equals("20")) {
            swipeRefreshLayout.setOnRefreshListener(this);

            getVideoData();
            llmusic.setVisibility(View.GONE);
        } else if (channel.equals("22")) {
            swipeRefreshLayout.setEnabled(false);
            getMusicData();
            llmusic.setVisibility(View.VISIBLE);
        } else if (channel.equals("23")) {
            swipeRefreshLayout.setOnRefreshListener(this);

            layout.setVisibility(View.VISIBLE);
            getShowData();
            llmusic.setVisibility(View.GONE);

        } else {
            swipeRefreshLayout.setOnRefreshListener(this);

            getData();
            llmusic.setVisibility(View.GONE);
        }

        emptyUtil=new EmptyUtil(getActivity(),recyclerView);
        emptyUtil.initErrorPage();

        return view;

    }

    /**
     * 获取音乐的数据
     */
    private void getMusicData() {


        mMusicReceiver = new MusicReceiver();
        IntentFilter intentFilter = new IntentFilter(MusicServer.UPDATE_PROGRESS);
        getActivity().registerReceiver(mMusicReceiver, intentFilter);


        final Map<String, String> params = new HashMap<>();

        params.put("act", "music");
        RetrofitUtil.getretrofit().create(MusicBiz.class).getData(params).enqueue(new Callback<Music>() {
            @Override
            public void onResponse(Call<Music> call, final Response<Music> response) {

                if(response.body()==null){
                    emptyUtil.showErrorPage();
                    llmusic.setVisibility(View.GONE);
                }else {


                Log.d("音乐的数据", response.body().getM_arr().toString());
                musiclist = response.body().getM_arr();
                Musiclastid = response.body().getLastid();
                musicAdapter = new MusicAdapter(musiclist);
                recyclerView.setAdapter(musicAdapter);
                musicname.setText(musiclist.get(0).getSname());
                musicsinger.setText(musiclist.get(0).getSinger());


                ImageLoaderUtils.displayImage(Constants.Home3 + musiclist.get(0).getPicurl(), imageViewmusic);

                //这里只获取路径，传递给音乐服务类。
                ArrayList<String> musicPathList = new ArrayList<String>();
                for (Music.MArrBean m : musiclist) {
                    musicPathList.add(Constants.Home3 + m.getURL());
                }
                serverIntent = new Intent(getActivity(), MusicServer.class);
                serverIntent.putExtra(MusicServer.EXEC_COMMAND, MusicServer.INIT_MUSIC_PATH);
                serverIntent.putStringArrayListExtra("music_list", musicPathList);
                getActivity().startService(serverIntent);

                musicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Log.v("点击音乐", position + "位置");
                        musicname.setText(musiclist.get(position).getSname());
                        musicsinger.setText(musiclist.get(position).getSinger());
                        ImageLoaderUtils.displayImage(Constants.Home3 + musiclist.get(position).getPicurl(), imageViewmusic);

                        Intent serverIntent = new Intent(getActivity(), MusicServer.class);


                        //启动服务,传递所点击的position，服务会播放所点击的音乐
                        serverIntent.putExtra(MusicServer.EXEC_COMMAND, MusicServer.ONCLICK_MUSIC_LIST);
                        serverIntent.putExtra("position", position);
                        getActivity().startService(serverIntent);
                        musicplay.setImageResource(R.drawable.music_stop);
                        isPlaying = true;

                    }
                });

                musicAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {

                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("能否被整除", mCurrentCounter % 10 + "");
                                if (mMusicCounter % 10 != 0) {
                                    //数据全部加载完毕
                                    musicAdapter.loadMoreEnd();
                                } else {
                                    if (Network.isConnected()){


                                    if (isErr) {
                                        //成功获取更多数据
                                        musicAdapter.addData(getMusicMoreData(Musiclastid));
                                        mMusicCounter = musicAdapter.getData().size();
                                        musicAdapter.loadMoreComplete();
                                    } else {
                                        //获取更多数据失败
                                        musicAdapter.loadMoreFail();

                                    }
                                    }else {
                                        ToastUtils.showToast("暂无网络");
                                    }

                                }
                            }

                        }, 1000);


                    }
                }, recyclerView);

            }
            }

            @Override
            public void onFailure(Call<Music> call, Throwable t) {

            }
        });


    }

    /**
     * 音乐加载更多数据
     *
     * @param lastid
     * @return
     */
    private List<Music.MArrBean> getMusicMoreData(String lastid) {
        //http://yyapp.1yuaninfo.com/app/application/channel.php?act=videomore&lastid=LASTID

        Map<String, String> params = new HashMap<>();

        params.put("act", "musicmore");
        params.put("lastid", lastid);

        RetrofitUtil.getretrofit().create(MusicBiz.class).getData(params).enqueue(new Callback<Music>() {
            @Override
            public void onResponse(Call<Music> call, Response<Music> response) {
                musicmorelist = response.body().getM_arr();
                Musiclastid = response.body().getLastid();
                Log.d("音乐更多数据", musicmorelist.toString() + "\n" + Musiclastid);
            }

            @Override
            public void onFailure(Call<Music> call, Throwable t) {

            }
        });


        return musicmorelist;
    }

    /**
     * 获取演出的数据
     */
    private void getShowData() {

        Map<String, String> params = new HashMap<>();

        params.put("act", "show");
        RetrofitUtil.getretrofit().create(ShowEntityBiz.class).getData(params).enqueue(new Callback<ShowEntity>() {
            @Override
            public void onResponse(Call<ShowEntity> call, Response<ShowEntity> response) {

                if(response.body()==null){
                   emptyUtil.showErrorPage();
                }else {


                Log.d("演出列表数据", channel + "@" + response.body().getXh_arr().toString());

                lblist = response.body().getLb_arr();
                banner.setOffscreenPageLimit(3);
                for (int i = 0; i < lblist.size(); i++) {
                    images.add(lblist.get(i).getPicurl());
                }
                list = Arrays.asList(ints);

                banner.setOffscreenPageLimit(ints.length);

                banner.setImages(list)
                        .setImageLoader(new GlideImageloader()).start();
                banner.setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        CommonUtil.goAactivity(getActivity(), MsgInfoActivity.class, "msgurl",
                                lblist.get(position-1).getPiclink());

                    }
                });
                tjlist = response.body().getTj_arr();
                xhlist = response.body().getXh_arr();
                showAdapter = new ShowAdapter(xhlist);
                recyclerView.setAdapter(showAdapter);
                showAdapter.addHeaderView(headview);
                showheaderAdapter = new ShowHeaderAdapter(tjlist);
                ry.setAdapter(showheaderAdapter);
                Showlastid = response.body().getLastid();
                showAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                        CommonUtil.goAactivity(getActivity(), ShowDetailActivity.class, "showurl",
                                Constants.SHOWURL.concat("id=" + xhlist.get(position).getId()));
                    }
                });
                showheaderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        CommonUtil.goAactivity(getActivity(), ShowDetailActivity.class, "showurl",
                                Constants.SHOWURL.concat("id=" + tjlist.get(position).getId()));
                    }
                });

                showAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("能否被整除", mShowCounter % 10 + "");
                                if (mShowCounter % 10 != 0) {
                                    //数据全部加载完毕
                                    showAdapter.loadMoreEnd();
                                } else {

                                    if(Network.isConnected()){


                                    if (isErr) {
                                        //成功获取更多数据
                                        showAdapter.addData(getShowMoreData(Showlastid));
                                        mShowCounter = showAdapter.getData().size();
                                        showAdapter.loadMoreComplete();
                                    } else {
                                        //获取更多数据失败
                                        showAdapter.loadMoreFail();

                                    }
                                    }else {
                                        ToastUtils.showToast("暂无网络");
                                    }
                                }
                            }

                        }, 1000);


                    }
                });

            }
            }

            @Override
            public void onFailure(Call<ShowEntity> call, Throwable t) {

            }
        });
    }

    private List<XhArrBean> getShowMoreData(String id) {


        Map<String, String> params = new HashMap<>();

        params.put("act", "showmore");
        params.put("lastid", id);
        RetrofitUtil.getretrofit().create(ShowMoreEntityBiz.class).getData(params).enqueue(new Callback<ShowMroeEntity>() {
            @Override
            public void onResponse(Call<ShowMroeEntity> call, Response<ShowMroeEntity> response) {
                xhlistmore = response.body().getXh_arr();
                Showlastid = response.body().getLastid();
                Log.d("演出加载更多", xhlistmore.toString());
            }

            @Override
            public void onFailure(Call<ShowMroeEntity> call, Throwable t) {
                Log.d("演出加载更多", xhlistmore.toString());

            }
        });

        return xhlistmore;
    }

    /**
     * 获取视频的数据
     */
    private void getVideoData() {


        Map<String, String> params = new HashMap<>();

        params.put("act", "video");
        RetrofitUtil.getretrofit().create(VideoEntityBiz.class).getData(params).enqueue(new Callback<VideoEntity>() {
            @Override
            public void onResponse(final Call<VideoEntity> call, Response<VideoEntity> response) {
                if(response.body()==null){
                    emptyUtil.showErrorPage();
                }else {

                Log.d("行情列表数据", channel + "@" + response.body().getV_arr().toString());
                videolist = response.body().getV_arr();
                videoadapter = new VideoAdapter(videolist,getActivity());
                recyclerView.setAdapter(videoadapter);
                Videolastid = response.body().getLastid();
                recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                    @Override
                    public void onChildViewAttachedToWindow(View view) {

                    }

                    @Override
                    public void onChildViewDetachedFromWindow(View view) {


                        if (JCVideoPlayerManager.getCurrentJcvd() != null) {
                            JCVideoPlayer videoPlayer = JCVideoPlayerManager.getCurrentJcvd();
                            if (((ViewGroup) view).indexOfChild(videoPlayer) != -1 && videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
                                //当滑动的时，正在播放的视频移除屏幕，取消播放这个视频
                                JCVideoPlayer.releaseAllVideos();
                            }
                        }

                    }
                });


                videoadapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {

                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("能否被整除", mVideoCounter % 10 + "");
                                if (mVideoCounter % 10 != 0) {
                                    //数据全部加载完毕
                                    videoadapter.loadMoreEnd();
                                } else {
                                    if(Network.isConnected()){


                                    if (isErr) {
                                        //成功获取更多数据
                                        videoadapter.addData(getVideoMoreData(Videolastid));
                                        mVideoCounter = videoadapter.getData().size();
                                        videoadapter.loadMoreComplete();
                                    } else {
                                        //获取更多数据失败
                                        videoadapter.loadMoreFail();

                                    }
                                    }else {
                                        ToastUtils.showToast("暂无网络");
                                    }
                                }
                            }

                        }, 1000);


                    }
                }, recyclerView);


                videoadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                        if(Network.isConnected()){

                        VideoEntity.VArrBean entity = (VideoEntity.VArrBean) adapter.getData().get(position);
                        Intent intent = new Intent(getActivity(), VideoDetailActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("entity", entity);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        }else {
                            ToastUtils.showToast("暂无网络");
                        }

                    }
                });
                videoadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                          VideoEntity.VArrBean entity=(VideoEntity.VArrBean)adapter.getData().get(position);

                        if(Network.isConnected()){

                            ShareUtil shareUtil = new ShareUtil(getActivity(),
                                                            recyclerView,
                                                            getActivity(),
                                entity.getV_name(),
                                entity.getV_picture(),
                                entity.getV_name(),
                                Constants.VIDEOSHARE+entity.getId()


                          );
                        shareUtil.ShowPopupWindow();
                        }else {
                            ToastUtils.showToast("暂无网络");
                        }

                    }
                });



            }
            }

            @Override
            public void onFailure(Call<VideoEntity> call, Throwable t) {

            }
        });

    }

    private List<VideoEntity.VArrBean> getVideoMoreData(final String id) {
        Log.d("视频最后一个id", id + "");
        Map<String, String> params = new HashMap<>();

        params.put("act", "videomore");
        params.put("lastid", id);
        RetrofitUtil.getretrofit().create(VideoEntityBiz.class).getData(params).enqueue(new Callback<VideoEntity>() {
            @Override
            public void onResponse(Call<VideoEntity> call, Response<VideoEntity> response) {
                videomorelist = response.body().getV_arr();
                Videolastid = response.body().getLastid();
            }

            @Override
            public void onFailure(Call<VideoEntity> call, Throwable t) {

            }
        });


        return videomorelist;
    }


    /**
     * 获取资讯列表数据
     */
    private void getData() {
        RetrofitUtil.getretrofit().create(NewsEntityBiz.class).getData("info", channel).enqueue(new Callback<NewsEntity>() {
            @Override
            public void onResponse(Call<NewsEntity> call, Response<NewsEntity> response) {

                if(response.body()==null){
                   emptyUtil.showErrorPage();



                }else {


                Log.d("行情列表数据", channel + "@" + response.body().getHangqing().toString());
                newslist = response.body().getHangqing();
                adapter = new NewsAdapter(getActivity(), newslist);
                lastid = response.body().getLastid();
                if (response.body().getRank_arr().size() != 0) {

                    ranklastid = response.body().getRank_arr().get(response.body().getRank_arr().size() - 1).getPop_value();
                }
                Log.d("更多排行", ranklastid + "");
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Log.d("news111111111", newslist.get(position).toString());
                        Log.d("news111111111", newslist.get(position).getPicstate().equals(ConstanceValue.ARTICLE_GENRE_THREE) + newslist.get(position).getPicstate());
                        if (newslist.get(position).getPicstate().equals(ConstanceValue.ARTICLE_GENRE_GALLERY)) {
                            Intent intent = new Intent(getActivity(), NewsImageActivity.class);
                            intent.putExtra("PicarrBean", (Serializable) newslist.get(position).getPicarr());
                            getActivity().startActivity(intent);
                            //CommonUtil.goAactivity(getActivity(), NewsImageActivity.class);
                        } else {


                            entity = (NewsEntity.HangqingBean) adapter.getData().get(position);
                            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("entity", entity);
                            intent.putExtras(bundle);
                            startActivityForResult(intent,2);


                           // startActivity(intent);

//
//                            CommonUtil.goAactivity(getActivity(), NewsDetailActivity.class, "newsurl",
//                                    Constants.NEWSURL.concat("id=" + newslist.get(position).getId() + "&userid="),
//                                     "newsid",newslist.get(position).getId(),
//                                      "newstitle",newslist.get(position).getTitle() );
//

                        }
                    }
                });


                adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("能否被整除", mCurrentCounter % 10 + "");
                                if (mCurrentCounter % 10 != 0) {
                                    //数据全部加载完毕
                                    adapter.loadMoreEnd();
                                } else {

                                    if (Network.isConnected()){


                                    if (isErr) {
                                        //成功获取更多数据
                                        adapter.addData(getMoreData(channel, lastid));
                                        mCurrentCounter = adapter.getData().size();
                                        adapter.loadMoreComplete();
                                    } else {
                                        //获取更多数据失败
                                        adapter.loadMoreFail();

                                    }
                                    }else {
                                        ToastUtils.showToast("暂无网络");
                                    }
                                }
                            }

                        }, 1000);


                    }
                }, recyclerView);
                //adapter.setUpFetchEnable(true);

                newsranklist = response.body().getRank_arr();
                Log.d("行情排行数据", channel + "@" + response.body().getRank_arr().toString());
                if (newsranklist.size() == 0) {
                    tvphmore.setVisibility(View.GONE);
                } else {
                    tvphmore.setVisibility(View.VISIBLE);
                    adapter.addHeaderView(viewph);
                    newsHeaderAdapter = new NewsHeaderAdapter(newsranklist);
                    rvph.setAdapter(newsHeaderAdapter);
                    newsHeaderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            NewsEntity.RankArrBean entity = (NewsEntity.RankArrBean) adapter.getData().get(position);
                            CommonUtil.goAactivity(getActivity(), WebViewActivity.class, "msgurl", entity.getSelf_link(), "typ", "1");
                        }
                    });
                }
                }

            }

            public List<NewsEntity.HangqingBean> getMoreData(String id, String moreid) {
                //ToastUtils.showToast("频道ID" + id + "\n" + lastid);
                RetrofitUtil.getretrofit().create(NewsEntityMoreBiz.class).getData("infomore", id, moreid).enqueue(new Callback<NewsEntity>() {
                    @Override
                    public void onResponse(Call<NewsEntity> call, Response<NewsEntity> response) {

                        morelist = response.body().getHangqing();

                        lastid = response.body().getLastid();
                        Log.d("热点更多数据", morelist.toString() + lastid);
                    }

                    @Override
                    public void onFailure(Call<NewsEntity> call, Throwable t) {
                        isErr = false;

                    }
                });
                return morelist;
            }


            @Override
            public void onFailure(Call<NewsEntity> call, Throwable t) {
                adapter.setEmptyView(viewempty);
                viewempty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast("请稍后再试");
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(Network.isConnected()){


        Intent serverIntent = new Intent(getActivity(), MusicServer.class);

        switch (v.getId()) {
            case R.id.tv_news_ph_more:
                CommonUtil.goAactivity(getActivity(), RankActivity.class, "classid", channel, "ranklastid", ranklastid);
                break;
            case R.id.iv_music_play:
                if (isPlaying) {
                    musicplay.setImageResource(R.drawable.music_bf);
                    isPlaying = false;
                    serverIntent.putExtra(MusicServer.EXEC_COMMAND, MusicServer.PAUSE_MUSIC);
                    getActivity().startService(serverIntent);
                } else {//如果音乐没有在播放
                    serverIntent.putExtra(MusicServer.EXEC_COMMAND, MusicServer.START_MUSIC);
                    getActivity().startService(serverIntent);
                    musicplay.setImageResource(R.drawable.music_stop);
                    isPlaying = true;
                }
                break;
        }
        }else {
            ToastUtils.showToast("暂无网络");
        }
    }


    /**
     * 刷新数据
     */

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (channel.equals("20")) {
                    getVideoRefreshData();

                } else if (channel.equals("23")) {
                    getShowRefreshData();

                } else {

                    getRefreshData();
                }
            }
        }, 2000);

    }

    /**
     * 演出刷新
     */
    private void getShowRefreshData() {

        Map<String, String> params = new HashMap<>();

        params.put("act", "show");
        RetrofitUtil.getretrofit().create(ShowEntityBiz.class).getData(params).enqueue(new Callback<ShowEntity>() {
            @Override
            public void onResponse(Call<ShowEntity> call, Response<ShowEntity> response) {
                lblist = response.body().getLb_arr();
                banner.setOffscreenPageLimit(3);
                for (int i = 0; i < lblist.size(); i++) {
                    images.add(lblist.get(i).getPicurl());
                }
                list = Arrays.asList(ints);

                banner.setOffscreenPageLimit(ints.length);

                banner.setImages(list)
                        .setImageLoader(new GlideImageloader()).start();
                banner.setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        CommonUtil.goAactivity(getActivity(), NewsDetailActivity.class, "newsurl",
                                "http://yyapp.1yuaninfo.com/app/yyfwapp/showdetails.php?id=50");

                    }
                });
                tjlist = response.body().getTj_arr();
                xhlist = response.body().getXh_arr();
                showAdapter.setNewData(xhlist);
                showheaderAdapter.setNewData(tjlist);
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<ShowEntity> call, Throwable t) {

            }
        });


    }

    /**
     * 视频刷新
     */
    private void getVideoRefreshData() {
        Map<String, String> params = new HashMap<>();
        params.put("act", "video");
        RetrofitUtil.getretrofit().create(VideoEntityBiz.class).getData(params).enqueue(new Callback<VideoEntity>() {
            @Override
            public void onResponse(Call<VideoEntity> call, Response<VideoEntity> response) {
                videolist = response.body().getV_arr();
                videoadapter.setNewData(videolist);
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<VideoEntity> call, Throwable t) {

            }
        });


    }

    /**
     * 资讯刷新
     */
    private void getRefreshData() {

        RetrofitUtil.getretrofit().create(NewsEntityBiz.class).getData("info", channel).enqueue(new Callback<NewsEntity>() {
            @Override
            public void onResponse(Call<NewsEntity> call, Response<NewsEntity> response) {
                Log.d("行情列表数据", channel + "@" + response.body().getHangqing().toString());
                newslist = response.body().getHangqing();
                lastid = response.body().getLastid();
                adapter.setNewData(newslist);
                newsranklist = response.body().getRank_arr();
                if (newsranklist.size() != 0) {
                    newsHeaderAdapter.setNewData(newsranklist);

                }
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<NewsEntity> call, Throwable t) {
                adapter.setEmptyView(viewempty);
                viewempty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast("请稍后再试");
                    }
                });
            }
        });


    }


    class MusicReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            int index = intent.getIntExtra(MusicServer.MUSIC_INDEX, 0);
            int curr = intent.getIntExtra(MusicServer.MUSIC_CURR, 0);
            int total = intent.getIntExtra(MusicServer.MUSIC_TOTAL, 0);


            if (musicIndex != index) {
                musicIndex = index;
                mSeekBar.setMax(total);
                timetotal.setText(format(total));
            }
            mSeekBar.setProgress(curr);
            timecurr.setText(format(curr));
        }
    }


    private static String format(long time) {
        time = time / 1000;
        if (time < 60) {
            return "00:" + fromatLong("00", time);
        } else {
            return fromatLong("00", time / 60) + ":" + fromatLong("00", time % 60);
        }
    }

    private static String fromatLong(String format, long num) {
        return new DecimalFormat(format).format(num);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //getActivity().unregisterReceiver(mMusicReceiver);

    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("TAG111","requestCode="+requestCode+"resultCode"+resultCode);
       if(requestCode==2){

           if(resultCode==RESULT_OK){
           newslist.remove(entity);
           adapter.notifyDataSetChanged();
           }
       }


    }
}
