package com.yiyuaninfo.util;

import com.scrollablelayout.ScrollableHelper;

/**
 * Created by gaocongcong on 2018/1/23.
 */

public class MyScrollableHelper extends ScrollableHelper {
    private ScrollableContainer container;

    @Override
    public void setCurrentScrollableContainer(ScrollableContainer scrollableContainer) {
        this.container = scrollableContainer;
    }

    @Override
    public boolean isTop() {
        if (container.getScrollableView()==null)return true;

        if (container.getScrollableView() instanceof FindRecyclerView) {
            FindRecyclerView scrollableView = (FindRecyclerView) container.getScrollableView();
            if (scrollableView.isTop()) return true;
            else return false;
        }
        return super.isTop();
    }
}
