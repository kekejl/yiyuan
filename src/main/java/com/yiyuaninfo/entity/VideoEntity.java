package com.yiyuaninfo.entity;

import java.io.Serializable;
import java.util.List;


public class VideoEntity {
	/**
	 * v_arr : [{"id":"1335","v_parid":null,"v_name":"监拍: 女童被公交车挤脚惨遭拖行","v_picture":"uploads/image/20170725/1500952553.png","v_hits":"29358","v_time":"1:30","v_url":"uploads/media/20170911/1505125821.flv","v_tag":"热门","v_source":"优酷视频","v_modtime":"2017-07-25 10:34:27","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1334","v_parid":null,"v_name":"入传青年误销窝点饭量过大遭遣返","v_picture":"uploads/image/20170725/1500955155.jpg","v_hits":"63215","v_time":"1:30","v_url":"uploads/media/20170911/1505125821.flv","v_tag":"热门","v_source":"优酷视频","v_modtime":"2017-07-25 10:30:29","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1333","v_parid":null,"v_name":"因为爱情 伦敦这座地铁站 让你再次相信爱情 说天下 20...","v_picture":"uploads/image/20170725/1500950943.png","v_hits":"69541","v_time":"1:30","v_url":"uploads/media/20170911/1505125821.flv","v_tag":"热门","v_source":"优酷视频","v_modtime":"2017-07-25 10:24:34","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1332","v_parid":null,"v_name":"大脚怪车神来了！女神现场体验解说 点火瞬间燃爆了","v_picture":"uploads/image/20170725/1500950731.jpg","v_hits":"58711","v_time":"1:30","v_url":"uploads/media/20170911/1505125821.flv","v_tag":"热门","v_source":"汽车洋葱圈","v_modtime":"2017-07-25 10:16:21","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1331","v_parid":null,"v_name":"机场穿搭太正被批炒作 杨幂：就是这么有品味","v_picture":"uploads/image/20170725/1500954945.jpg","v_hits":"15812","v_time":"1:30","v_url":"uploads/media/20170911/1505125821.flv","v_tag":"热门","v_source":"娱乐全视角","v_modtime":"2017-07-25 09:55:00","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1330","v_parid":null,"v_name":"燃！实拍中俄联手海上亮剑 导弹齐射现场震耳欲聋","v_picture":"uploads/image/20170725/1500952473.jpg","v_hits":"16386","v_time":"1:30","v_url":"uploads/media/20170911/1505125821.flv","v_tag":"热门","v_source":"燃军事 ","v_modtime":"2017-07-25 09:35:03","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1329","v_parid":null,"v_name":"夏季柠檬正确吃法 零难度清新柠檬雪葩！","v_picture":"uploads/image/20170725/1500949049.jpg","v_hits":"59324","v_time":"1:30","v_url":"uploads/media/20170911/1505125821.flv","v_tag":"热门","v_source":" 罐头视频","v_modtime":"2017-07-25 09:34:04","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1328","v_parid":null,"v_name":"大热天的就别再爆炒花蛤了，交给微波炉体验下最简单的极致美味","v_picture":"uploads/image/20170725/1500947102.jpg","v_hits":"69581","v_time":"1:30","v_url":"uploads/media/20170911/1505125821.flv","v_tag":"热门","v_source":" 罐头视频 ","v_modtime":"2017-07-25 09:18:58","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1327","v_parid":null,"v_name":"小个子女生的穿衣法则 5个技巧让你有大长腿","v_picture":"uploads/image/20170725/1500950892.jpg","v_hits":"7586","v_time":"1:30","v_url":"uploads/media/20170911/1505125821.flv","v_tag":"热门","v_source":"腾讯视频","v_modtime":"2017-07-25 09:14:19","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1326","v_parid":null,"v_name":"简直是街拍界一股清流！倪妮旅行照变pose女王","v_picture":"uploads/image/20170725/1500949130.jpg","v_hits":"36271","v_time":"1:30","v_url":"uploads/media/20170911/1505125821.flv","v_tag":"热门","v_source":"腾讯视频","v_modtime":"2017-07-25 09:12:22","v_sharUrl":null,"delstate":"false","deltime":""}]
	 * lastid : 1326
	 */

	private String lastid;
	private List<VArrBean> v_arr;

	public String getLastid() {
		return lastid;
	}

	public void setLastid(String lastid) {
		this.lastid = lastid;
	}

	public List<VArrBean> getV_arr() {
		return v_arr;
	}

	public void setV_arr(List<VArrBean> v_arr) {
		this.v_arr = v_arr;
	}

	public static class VArrBean  implements Serializable{
		/**
		 * id : 1335
		 * v_parid : null
		 * v_name : 监拍: 女童被公交车挤脚惨遭拖行
		 * v_picture : uploads/image/20170725/1500952553.png
		 * v_hits : 29358
		 * v_time : 1:30
		 * v_url : uploads/media/20170911/1505125821.flv
		 * v_tag : 热门
		 * v_source : 优酷视频
		 * v_modtime : 2017-07-25 10:34:27
		 * v_sharUrl : null
		 * delstate : false
		 * deltime :
		 */

		private String id;
		private String v_parid;
		private String v_name;
		private String v_picture;
		private String v_hits;
		private String v_time;
		private String v_url;
		private String v_tag;
		private String v_source;
		private String v_modtime;
		private String v_sharUrl;
		private String delstate;
		private String deltime;

		public VArrBean(String id, String v_parid, String v_name, String v_picture, String v_hits, String v_time, String v_url, String v_tag, String v_source, String v_modtime, String v_sharUrl, String delstate, String deltime) {
			this.id = id;
			this.v_parid = v_parid;
			this.v_name = v_name;
			this.v_picture = v_picture;
			this.v_hits = v_hits;
			this.v_time = v_time;
			this.v_url = v_url;
			this.v_tag = v_tag;
			this.v_source = v_source;
			this.v_modtime = v_modtime;
			this.v_sharUrl = v_sharUrl;
			this.delstate = delstate;
			this.deltime = deltime;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Object getV_parid() {
			return v_parid;
		}

		public void setV_parid(String v_parid) {
			this.v_parid = v_parid;
		}

		public String getV_name() {
			return v_name;
		}

		public void setV_name(String v_name) {
			this.v_name = v_name;
		}

		public String getV_picture() {
			return v_picture;
		}

		public void setV_picture(String v_picture) {
			this.v_picture = v_picture;
		}

		public String getV_hits() {
			return v_hits;
		}

		public void setV_hits(String v_hits) {
			this.v_hits = v_hits;
		}

		public String getV_time() {
			return v_time;
		}

		public void setV_time(String v_time) {
			this.v_time = v_time;
		}

		public String getV_url() {
			return v_url;
		}

		public void setV_url(String v_url) {
			this.v_url = v_url;
		}

		public String getV_tag() {
			return v_tag;
		}

		public void setV_tag(String v_tag) {
			this.v_tag = v_tag;
		}

		public String getV_source() {
			return v_source;
		}

		public void setV_source(String v_source) {
			this.v_source = v_source;
		}

		public String getV_modtime() {
			return v_modtime;
		}

		public void setV_modtime(String v_modtime) {
			this.v_modtime = v_modtime;
		}

		public Object getV_sharUrl() {
			return v_sharUrl;
		}

		public void setV_sharUrl(String v_sharUrl) {
			this.v_sharUrl = v_sharUrl;
		}

		public String getDelstate() {
			return delstate;
		}

		public void setDelstate(String delstate) {
			this.delstate = delstate;
		}

		public String getDeltime() {
			return deltime;
		}

		public void setDeltime(String deltime) {
			this.deltime = deltime;
		}

		@Override
		public String toString() {
			return "VArrBean{" +
					"id='" + id + '\'' +
					", v_parid=" + v_parid +
					", v_name='" + v_name + '\'' +
					", v_picture='" + v_picture + '\'' +
					", v_hits='" + v_hits + '\'' +
					", v_time='" + v_time + '\'' +
					", v_url='" + v_url + '\'' +
					", v_tag='" + v_tag + '\'' +
					", v_source='" + v_source + '\'' +
					", v_modtime='" + v_modtime + '\'' +
					", v_sharUrl=" + v_sharUrl +
					", delstate='" + delstate + '\'' +
					", deltime='" + deltime + '\'' +
					'}';
		}
	}


//	/**
//	 * v_arr : [{"id":"1335","v_name":"监拍: 女童被公交车挤脚惨遭拖行","v_picture":"uploads/image/20170725/1500952553.png","v_hits":"29358","v_time":"1:30","v_url":"http://yyapp.1yuaninfo.com/uploads/media/1492590379.mp4","v_tag":"热门","v_source":"优酷视频","v_modtime":"2017-07-25 10:34:27","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1334","v_name":"入传青年误销窝点饭量过大遭遣返","v_picture":"uploads/image/20170725/1500955155.jpg","v_hits":"63215","v_time":"1:30","v_url":"http://yyapp.1yuaninfo.com/uploads/media/1492590379.mp4","v_tag":"热门","v_source":"优酷视频","v_modtime":"2017-07-25 10:30:29","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1333","v_name":"因为爱情 伦敦这座地铁站 让你再次相信爱情 说天下 20...","v_picture":"uploads/image/20170725/1500950943.png","v_hits":"69541","v_time":"1:30","v_url":"http://yyapp.1yuaninfo.com/uploads/media/1492590379.mp4","v_tag":"热门","v_source":"优酷视频","v_modtime":"2017-07-25 10:24:34","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1332","v_name":"大脚怪车神来了！女神现场体验解说 点火瞬间燃爆了","v_picture":"uploads/image/20170725/1500950731.jpg","v_hits":"58711","v_time":"1:30","v_url":"http://yyapp.1yuaninfo.com/uploads/media/1492590379.mp4","v_tag":"热门","v_source":"汽车洋葱圈","v_modtime":"2017-07-25 10:16:21","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1331","v_name":"机场穿搭太正被批炒作 杨幂：就是这么有品味","v_picture":"uploads/image/20170725/1500954945.jpg","v_hits":"15812","v_time":"1:30","v_url":"http://yyapp.1yuaninfo.com/uploads/media/1492590379.mp4","v_tag":"热门","v_source":"娱乐全视角","v_modtime":"2017-07-25 09:55:00","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1330","v_name":"燃！实拍中俄联手海上亮剑 导弹齐射现场震耳欲聋","v_picture":"uploads/image/20170725/1500952473.jpg","v_hits":"16386","v_time":"1:30","v_url":"http://yyapp.1yuaninfo.com/uploads/media/1492590379.mp4","v_tag":"热门","v_source":"燃军事 ","v_modtime":"2017-07-25 09:35:03","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1329","v_name":"夏季柠檬正确吃法 零难度清新柠檬雪葩！","v_picture":"uploads/image/20170725/1500949049.jpg","v_hits":"59324","v_time":"1:30","v_url":"http://yyapp.1yuaninfo.com/uploads/media/1492590379.mp4","v_tag":"热门","v_source":" 罐头视频","v_modtime":"2017-07-25 09:34:04","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1328","v_name":"大热天的就别再爆炒花蛤了，交给微波炉体验下最简单的极致美味","v_picture":"uploads/image/20170725/1500947102.jpg","v_hits":"69581","v_time":"1:30","v_url":"http://yyapp.1yuaninfo.com/uploads/media/1492590379.mp4","v_tag":"热门","v_source":" 罐头视频 ","v_modtime":"2017-07-25 09:18:58","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1327","v_name":"小个子女生的穿衣法则 5个技巧让你有大长腿","v_picture":"uploads/image/20170725/1500950892.jpg","v_hits":"7586","v_time":"1:30","v_url":"http://yyapp.1yuaninfo.com/uploads/media/1492590379.mp4","v_tag":"热门","v_source":"腾讯视频","v_modtime":"2017-07-25 09:14:19","v_sharUrl":null,"delstate":"false","deltime":""},{"id":"1326","v_name":"简直是街拍界一股清流！倪妮旅行照变pose女王","v_picture":"uploads/image/20170725/1500949130.jpg","v_hits":"36271","v_time":"1:30","v_url":"http://yyapp.1yuaninfo.com/uploads/media/1492590379.mp4","v_tag":"热门","v_source":"腾讯视频","v_modtime":"2017-07-25 09:12:22","v_sharUrl":null,"delstate":"false","deltime":""}]
//	 * lastid : [{lastid:1326}]
//	 */
//
//	private String lastid;
//	private List<VArrBean> v_arr;
//
//	public String getLastid() {
//		return lastid;
//	}
//
//	public void setLastid(String lastid) {
//		this.lastid = lastid;
//	}
//
//	public List<VArrBean> getV_arr() {
//		return v_arr;
//	}
//
//	public void setV_arr(List<VArrBean> v_arr) {
//		this.v_arr = v_arr;
//	}
//
//	public static class VArrBean {
//		/**
//		 * id : 1335
//		 * v_name : 监拍: 女童被公交车挤脚惨遭拖行
//		 * v_picture : uploads/image/20170725/1500952553.png
//		 * v_hits : 29358
//		 * v_time : 1:30
//		 * v_url : http://yyapp.1yuaninfo.com/uploads/media/1492590379.mp4
//		 * v_tag : 热门
//		 * v_source : 优酷视频
//		 * v_modtime : 2017-07-25 10:34:27
//		 * v_sharUrl : null
//		 * delstate : false
//		 * deltime :
//		 */
//
//		private String id;
//		private String v_name;
//		private String v_picture;
//		private String v_hits;
//		private String v_time;
//		private String v_url;
//		private String v_tag;
//		private String v_source;
//		private String v_modtime;
//		private Object v_sharUrl;
//		private String delstate;
//		private String deltime;
//
//		public String getId() {
//			return id;
//		}
//
//		public void setId(String id) {
//			this.id = id;
//		}
//
//		public String getV_name() {
//			return v_name;
//		}
//
//		public void setV_name(String v_name) {
//			this.v_name = v_name;
//		}
//
//		public String getV_picture() {
//			return v_picture;
//		}
//
//		public void setV_picture(String v_picture) {
//			this.v_picture = v_picture;
//		}
//
//		public String getV_hits() {
//			return v_hits;
//		}
//
//		public void setV_hits(String v_hits) {
//			this.v_hits = v_hits;
//		}
//
//		public String getV_time() {
//			return v_time;
//		}
//
//		public void setV_time(String v_time) {
//			this.v_time = v_time;
//		}
//
//		public String getV_url() {
//			return v_url;
//		}
//
//		public void setV_url(String v_url) {
//			this.v_url = v_url;
//		}
//
//		public String getV_tag() {
//			return v_tag;
//		}
//
//		public void setV_tag(String v_tag) {
//			this.v_tag = v_tag;
//		}
//
//		public String getV_source() {
//			return v_source;
//		}
//
//		public void setV_source(String v_source) {
//			this.v_source = v_source;
//		}
//
//		public String getV_modtime() {
//			return v_modtime;
//		}
//
//		public void setV_modtime(String v_modtime) {
//			this.v_modtime = v_modtime;
//		}
//
//		public Object getV_sharUrl() {
//			return v_sharUrl;
//		}
//
//		public void setV_sharUrl(Object v_sharUrl) {
//			this.v_sharUrl = v_sharUrl;
//		}
//
//		public String getDelstate() {
//			return delstate;
//		}
//
//		public void setDelstate(String delstate) {
//			this.delstate = delstate;
//		}
//
//		public String getDeltime() {
//			return deltime;
//		}
//
//		public void setDeltime(String deltime) {
//			this.deltime = deltime;
//		}
//	}
}