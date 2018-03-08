package com.yiyuaninfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyuaninfo.Activity.FinderActivity;
import com.yiyuaninfo.Activity.MediaArtDetailActivity;
import com.yiyuaninfo.Activity.NewsDetailActivity;
import com.yiyuaninfo.Activity.ShowDetailActivity;
import com.yiyuaninfo.Activity.VideoDetailActivity;
import com.yiyuaninfo.Activity.XMDetailActivity;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.MallSection;
import com.yiyuaninfo.entity.NewsEntity;
import com.yiyuaninfo.entity.SearchSection;
import com.yiyuaninfo.entity.VideoEntity;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.ImageLoaderUtils;
import com.yiyuaninfo.util.ToastUtils;

import java.util.List;

/**
 * Created by gaocongcong on 2017/9/11.
 */

public class SearchSectionAdapter extends BaseSectionQuickAdapter<SearchSection, BaseViewHolder> {
    private Context  context;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */

    public SearchSectionAdapter(int layoutResId, int sectionHeadResId, List<SearchSection> data,Context  context) {
        super(layoutResId, sectionHeadResId, data);
        this.context=context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SearchSection item) {

        helper.setText(R.id.tv_search_header, item.header);

    }

    @Override
    protected void convert(BaseViewHolder helper, final SearchSection item) {
        helper.setText(R.id.tv_search_title, item.t.getTitle());

        helper.getView(R.id.ll_search_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (item.t.getType()) {
                    case "1":

                        NewsEntity.HangqingBean  entity1=new NewsEntity.HangqingBean(
                                item.t.getId(),
                                item.t.getTitle(),
                                "",
                                "",
                                "",
                                null,
                                "",
                                "",
                                ""
                                );
                        Intent intent1=new Intent(context, NewsDetailActivity.class);
                        Bundle  bundle1=new Bundle();
                        bundle1.putSerializable("entity",entity1);
                        intent1.putExtras(bundle1);
                       context.startActivity(intent1);







                        break;
                    case "2":
                        VideoEntity.VArrBean  entity2=new VideoEntity.VArrBean(
                                item.t.getId(),
                                "",
                                item.t.getTitle(),
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                ""
                                );

                        Intent intent2=new Intent(context,VideoDetailActivity.class);

                        Bundle bundle2=new Bundle();
                        bundle2.putSerializable("entity",entity2);
                        intent2.putExtras(bundle2);
                        context.startActivity(intent2);
                        break;

                    case "3":

                        CommonUtil.goAactivity(context, ShowDetailActivity.class, "showurl",
                                Constants.SHOWURL.concat("id=" + item.t.getId()));


                        break;

                    case "4":

                        CommonUtil.goAactivity(context, MediaArtDetailActivity.class, "newsurl",
                                Constants.NIU.concat("id=" + item.t.getId() ),
                                "newsid",item.t.getId(),
                                "newstitle",item.t.getTitle(),
                                "description","",
                                "image","");






                        break;

                    case "5":
                        CommonUtil.goAactivity(context, XMDetailActivity.class, "xmurl", Constants.XMDETAIL
                                + item.t.getId(),"id",item.t.getId());
                        break;
                    case "6":

                        CommonUtil.goAactivity(context, FinderActivity.class,"comid",item.t.getId());


                        break;

                }


            }
        });


    }
}
