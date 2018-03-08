package com.yiyuaninfo.Fragment;

import android.support.v4.app.Fragment;

import com.scrollablelayout.ScrollableHelper;

/**
 * Created by gaocongcong on 2017/12/4.
 */

public    abstract   class TextBaseFragment  extends Fragment implements ScrollableHelper.ScrollableContainer{

    public abstract void pullToRefresh();
    public abstract void refreshComplete();
    public abstract void recycleviewTop();
}
