package com.yiyuaninfo.entity;
/**
 * 描述：广告信息</br>
 * @author Eden Cheng</br>
 * @version 2015年4月23日 上午11:32:53
 */
public class Register {

	/**
	 * status : 3
	 */

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Register{" +
				"status='" + status + '\'' +
				'}';
	}
}
