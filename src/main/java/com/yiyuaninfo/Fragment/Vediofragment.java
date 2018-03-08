package com.yiyuaninfo.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.yiyuaninfo.Interface.NVideoEntityBiz;
import com.yiyuaninfo.Interface.VideoEntityBiz;
import com.yiyuaninfo.Listener.OnRecyclerViewItemClickListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.RecyclerViewVideoAdapter;
import com.yiyuaninfo.adapter.VideoAdapter;
import com.yiyuaninfo.entity.VideoEntity;
import com.yiyuaninfo.entity.VideoInfo;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.EmptyUtil;
import com.yiyuaninfo.util.Network;
import com.yiyuaninfo.util.NetworkUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.MyDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/23.
 */

public class Vediofragment extends BaseFragment  implements CommonPopupWindow.ViewInterface,View.OnClickListener{

    private LinearLayout sharecancle;
    private ImageView ivweixin, ivqq, ivfriend, ivqzone, ivsina;
    private static final String TAG = "Vediofragment";
    @BindView(R.id.recyclerviewVideo)
    RecyclerView recyclerView;
    private CommonPopupWindow  popupWindow;
    private ShareAction mShareAction;
    private UMShareListener mShareListener;
    RecyclerViewVideoAdapter adapterVideoList;
    private RecyclerView.OnScrollListener  onScrollListener;
    private List<VideoEntity.VArrBean>  videolist=new ArrayList<>();
    private List<VideoEntity.VArrBean>  videomorelist=new ArrayList<>();
    private VideoAdapter  videoadapter;
    private String Videolastid;
    private int mVideoCounter=0;
    private Boolean  isErr=true;
    private UMShareListener  shareListener;
    private VideoEntity.VArrBean  video;
    private EmptyUtil  emptyUtil;
    public static Vediofragment newInstance(String title) {


        Vediofragment f = new Vediofragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        //Log.d("fragment是否显示",isVisibleToUser+"");

        if(!isVisibleToUser){
            JCVideoPlayer.releaseAllVideos();

        }

        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {


    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_vedio;
    }


    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        emptyUtil=new EmptyUtil(getActivity(),recyclerView);
        emptyUtil.initErrorPage();
        getVideoData();
    }
    /**
     * 获取视频的数据
     */
    private void getVideoData() {

        Map<String, String> params = new HashMap<>();

        params.put("act", "cvideo");
        RetrofitUtil.getretrofit().create(NVideoEntityBiz.class).getData(params).enqueue(new Callback<VideoEntity>() {
            @Override
            public void onResponse(Call<VideoEntity> call, Response<VideoEntity> response) {

                if (response.body()==null){
                   emptyUtil.showErrorPage();
                }else {



                videolist = response.body().getV_arr();
                videoadapter = new VideoAdapter(videolist,getActivity());
                recyclerView.setAdapter(videoadapter);
                Videolastid=response.body().getLastid();
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
                                android.util.Log.d("能否被整除", mVideoCounter % 10 + "");
                                if (mVideoCounter % 10 != 0) {
                                    //数据全部加载完毕
                                    videoadapter.loadMoreEnd();
                                } else {
                                    if (isErr) {
                                        if (Network.isConnected()){
                                            //成功获取更多数据
                                            videoadapter.addData(getVideoMoreData( Videolastid));
                                            mVideoCounter = videoadapter.getData().size();
                                            videoadapter.loadMoreComplete();
                                        }else {
                                            ToastUtils.showToast("暂无网络");
                                            videoadapter.loadMoreFail();
                                        }


                                    } else {
                                        //获取更多数据失败
                                        videoadapter.loadMoreFail();

                                    }
                                }
                            }

                        }, 1000);





                    }
                },recyclerView);

                videoadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            video=(VideoEntity.VArrBean) adapter.getData().get(position);

                        switch (view.getId()){


                            case R.id.ivMore:


                                showAll();

                                break;
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
        android.util.Log.d("视频最后一个id",id+"");
        Map<String, String> params = new HashMap<>();
        params.put("act", "videomore");
        params.put("lastid", id);
        RetrofitUtil.getretrofit().create(VideoEntityBiz.class).getData(params).enqueue(new Callback<VideoEntity>() {
            @Override
            public void onResponse(Call<VideoEntity> call, Response<VideoEntity> response) {
                videomorelist=response.body().getV_arr();
                Videolastid=response.body().getLastid();
            }

            @Override
            public void onFailure(Call<VideoEntity> call, Throwable t) {

            }
        });


        return  videomorelist;
    }

    //全屏弹出
    public void showAll() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_share, null);
        //测量View的宽高
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(getActivity())
                .setView(R.layout.popwindow_share)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAtLocation(recyclerView, Gravity.BOTTOM, 0, 0);
    }




    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
        Log.d(TAG,"onPause视频");
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId){

            case R.layout.popwindow_share:
                sharecancle = (LinearLayout) view.findViewById(R.id.ll_my_share_cancle);
                ivfriend = (ImageView) view.findViewById(R.id.iv_my_share_friend);
                ivqq = (ImageView) view.findViewById(R.id.iv_my_share_qq);
                ivqzone = (ImageView) view.findViewById(R.id.iv_my_share_qzone);
                ivweixin = (ImageView) view.findViewById(R.id.iv_my_share_weixin);
                ivsina = (ImageView) view.findViewById(R.id.iv_my_share_sina);
                LinearLayout  text=(LinearLayout)view.findViewById(R.id.ll_share_text);
                LinearLayout  link=(LinearLayout)view.findViewById(R.id.ll_share_link);
                text.setVisibility(View.INVISIBLE);
                link.setVisibility(View.INVISIBLE);
                ivfriend.setOnClickListener(this);
                ivqq.setOnClickListener(this);
                ivqzone.setOnClickListener(this);
                ivweixin.setOnClickListener(this);
                ivsina.setOnClickListener(this);
                sharecancle.setOnClickListener(this);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_my_share_friend:
                ShareWeb(SHARE_MEDIA.WEIXIN_CIRCLE);
                popupWindow.dismiss();

                break;
            case R.id.iv_my_share_qq:
                ShareWeb(SHARE_MEDIA.QQ);
                popupWindow.dismiss();
                break;
            case R.id.iv_my_share_weixin:
                ShareWeb(SHARE_MEDIA.WEIXIN);
                popupWindow.dismiss();
                break;
            case R.id.iv_my_share_qzone:
                ShareWeb(SHARE_MEDIA.QZONE);
                popupWindow.dismiss();
                break;
            case R.id.iv_my_share_sina:
                ShareWeb(SHARE_MEDIA.SINA);
                popupWindow.dismiss();
                break;
            case R.id.ll_my_share_cancle:
                popupWindow.dismiss();
                break;
        }

    }
    private void ShareWeb(SHARE_MEDIA  type) {
        UMImage thumb = new UMImage(getActivity(),video.getV_picture());
        UMWeb web = new UMWeb(Constants.VIDEOSHARE+video.getId());
        Log.d("分享视频地址", Constants.VIDEOSHARE+video.getId());
        web.setThumb(thumb);
        web.setDescription(video.getV_name());
        web.setTitle(video.getV_name());
        new ShareAction(getActivity()).withMedia(web).setPlatform(type).setCallback(shareListener).share();
        shareListener=new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                ToastUtils.showToast("正在分享");
            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {
                ToastUtils.showToast("分享成功");

            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                ToastUtils.showToast("分享失败");

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                ToastUtils.showToast("分享取消");

            }
        };
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume视频");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop视频");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView视频");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy视频");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach视频");

    }

}
