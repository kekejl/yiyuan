package com.yiyuaninfo.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by gaocongcong on 2017/9/11.
 */

public class SearchSection extends SectionEntity<SearchBean> {
    public SearchSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SearchSection(SearchBean mall) {
        super(mall);
    }
}
