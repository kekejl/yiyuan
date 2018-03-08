package com.yiyuaninfo.entity;

import java.util.List;

/**
 * 描述：广告信息</br>
 * @author Eden Cheng</br>
 * @version 2015年4月23日 上午11:32:53
 */
public class MsgEntity {

	/**
	 * id : 5
	 * title : 文字公告
	 * addtime : 2017-04-10
	 */

	private String id;
	private String title;
	private String addtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
}
