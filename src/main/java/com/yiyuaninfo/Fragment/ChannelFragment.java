package com.yiyuaninfo.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.yiyuaninfo.Activity.CPActivity;
import com.yiyuaninfo.Activity.HQActivity;
import com.yiyuaninfo.Activity.JJActivity;
import com.yiyuaninfo.Activity.QSActivity;
import com.yiyuaninfo.Activity.RDActivity;
import com.yiyuaninfo.Activity.SHActivity;
import com.yiyuaninfo.Activity.TGActivity;
import com.yiyuaninfo.Activity.XMActivity;
import com.yiyuaninfo.Activity.YLActivity;
import com.yiyuaninfo.Listener.OnRecyclerViewItemClickListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.ChannelGroupAdapter;
import com.yiyuaninfo.entity.ChannelEntity;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.ToastUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/16.
 */

public class ChannelFragment extends BaseFragment {
    @BindView(R.id.recycle_channel)
    RecyclerView recyclerView;

    private ChannelGroupAdapter adapter;

    public static ChannelFragment newInstance(String title) {


        ChannelFragment f = new ChannelFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        ChannelEntity entity = CommonUtil.analysisJsonFile(getActivity(), "channel");
        Log.d("频道的数据", entity.getAllChannelList().toString());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ChannelGroupAdapter(getActivity(), R.layout.item_channelgroup, entity.getAllChannelList());
        recyclerView.setAdapter(adapter);
        adapter.OnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener<ChannelEntity.AllChannelListBean>() {

            @Override
            public void onItemClick(View view, ChannelEntity.AllChannelListBean data) {
               // ToastUtils.showToast(data.getGroupName());
                switch (data.getGroupName()) {
                    case "行情":
                        CommonUtil.goAactivity(getActivity(),HQActivity.class,"channelid","0","id",0);
                        break;
                    case "热点":
                        CommonUtil.goAactivity(getActivity(),HQActivity.class,"channelid","0","id",1);

                        //CommonUtil.goAactivity(getActivity(),RDActivity.class,"channelid","0");
                        break;
                    case "娱乐":
                        CommonUtil.goAactivity(getActivity(),HQActivity.class,"channelid","0","id",2);

                        //CommonUtil.goAactivity(getActivity(),YLActivity.class,"channelid","0");
                        break;
                    case "生活":
                        CommonUtil.goAactivity(getActivity(),HQActivity.class,"channelid","0","id",3);

                        // CommonUtil.goAactivity(getActivity(),SHActivity.class,"channelid","0");
                        break;
                    case "产品":
                        //CommonUtil.goAactivity(getActivity(),HQActivity.class,"channelid","0","id",4);

                        CommonUtil.goAactivity(getActivity(),CPActivity.class,"channelid","0");
                        break;
                    case "项目":
                        //CommonUtil.goAactivity(getActivity(),HQActivity.class,"channelid","0","id",5);

                        CommonUtil.goAactivity(getActivity(),XMActivity.class,"channelid","0","id",5);
                        break;
                    case "投顾":
                        //CommonUtil.goAactivity(getActivity(),HQActivity.class,"channelid","0","id",6);

                        CommonUtil.goAactivity(getActivity(),TGActivity.class,"channelid","0");
                        break;
                    case "券商":
                        //CommonUtil.goAactivity(getActivity(),HQActivity.class,"channelid","0","id",7);

                         CommonUtil.goAactivity(getActivity(),QSActivity.class,"channelid","0");
                        break;
                    case "基金":
                        //CommonUtil.goAactivity(getActivity(),HQActivity.class,"channelid","0","id",8);

                         CommonUtil.goAactivity(getActivity(),JJActivity.class,"channelid","0");
                        break;

                }
            }
        });
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_channel;
    }


}
