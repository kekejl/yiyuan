package com.yiyuaninfo.entity;

import java.util.List;

/**
 * 描述：广告信息</br>
 * @author Eden Cheng</br>
 * @version 2015年4月23日 上午11:32:53
 */
public class Msg {


	/**
	 * date : 04月10日
	 * info : [{"id":"5","title":"文字公告","addtime":"2017-04-10"}]
	 */

	private String date;
	private List<MsgEntity> info;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<MsgEntity> getInfo() {
		return info;
	}

	public void setInfo(List<MsgEntity> info) {
		this.info = info;
	}

}
