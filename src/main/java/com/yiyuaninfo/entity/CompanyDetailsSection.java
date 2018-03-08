package com.yiyuaninfo.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by gaocongcong on 2017/9/11.
 */

public class CompanyDetailsSection extends SectionEntity<CompanyDetailsMode> {
    public CompanyDetailsSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public CompanyDetailsSection(CompanyDetailsMode mall) {
        super(mall);
    }
}
