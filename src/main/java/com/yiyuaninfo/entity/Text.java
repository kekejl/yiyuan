package com.yiyuaninfo.entity;


import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 描述：广告信息</br>
 * @author Eden Cheng</br>
 * @version 2015年4月23日 上午11:32:53
 */
public class Text  implements MultiItemEntity {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getItemType() {
		return 0;
	}
}