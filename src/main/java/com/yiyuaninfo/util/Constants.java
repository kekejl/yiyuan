package com.yiyuaninfo.util;

/**
 * Created by gaocongcong on 2017/7/10.
 */

public class Constants {
    public static final String APP_ID = "wx11d19ee9062c616a";

    //IP地址
    public static final String IP = "yyapp.1yuaninfo.com";
    //获取验证码
    // 参数 mobile, keyword=reg
    // status:1 成功 status:0 失败 3 失败,已经注册
    public static final String CODE = "http://" + "IP" + "/app/application/register.php";
    //登录
    // 参数mobile,password
    // 返回值 status 1 登录失败,
    // userinfo:userid,avatar(头	像),mobile,guling,capital(资金),email,qqnum,weixin,weibo,groupid(会员级别)

    public static final String LOGIN = "http://" + "IP" + "/app/application/usersign.php";
    //注册
    //参数 mobile, yzm, password
    // 返回值 status:0 不能为空,1  成功,2插入数据库失败,3验证码超时,4验证码错误
    public static final String REGIST = "http://" + "IP" + "/app/application/validate.php?";


    //忘记密码
    //手机号获取验证码接口 参数 mobile
    // 返回值 status:1 成功 status:0失败
    //http://192.168.1.21/app/application/register.php?mobile=17090028712

    public static final String FrogetPwdCode = "http://" + "IP" + "/app/application/register.php";

    //修改密码:参数 mobile,password,yzm
    //status:1成功 2验证码错误或者超时,3入库失败(系统错误)
    public static final String FrogetPwd = "http://" + "IP" + "/app/application/modify_pwd.php";

    //首页消息接口
    //返回值  Id ,title,addtime
    public static final String Message = "http://yyapp.1yuaninfo.com/app/application/message.php/";
    //public  static final String    Message="http://"+"IP"+"/app/application/message.php?act=list";
    public static final String MessageInfo = "http://yyapp.1yuaninfo.com/app/application/message.php?act=details&id=";

    //首页轮播图
    //参数:act=top
    //返回参数:roll_pic 轮播图(picurl图片地址,piclink链接地址) ,
    // roll_words快讯(wordscontent文字内容,wordslink文字链接),
    // sroll_pic 小轮播(picurl链接,piclink图片),
    // post_msg消息(id,keyword1,alert/title标题,show_msg简介,addtime时间)
    //keyword1:5 早餐,6早评,7上午分享,8午评,9下午分享,10收评,11夜宵,12即使通知
    /**
     * http://yyapp.1yuaninfo.com/app/application/message.php
     * 消息详情页
     */
    public static final String MSGDETAIL = "http://yyapp.1yuaninfo.com/app/application/message.php?act=detail&id=";
    /**
     *
     * http://yyapp.1yuaninfo.com/app/application/report.php
     * 周刊月刊详情页
     */
    public static final String WEEKLY = "http://yyapp.1yuaninfo.com/app/application/report.php";

    /**
     *
     *积分获取
     */
    public static final String JIFEN = "http://yyapp.1yuaninfo.com/app/application/score.php";

    /**
     *请求数据的地址
     */
    public static final String Home = "http://yyapp.1yuaninfo.com/app/application/";
    /**
     *
     * 微信支付
     */
    public static final String Home5 = "http://yyapp.1yuaninfo.com/app/application/wxappapy/";
    /**
     *图片地址
     */
    public static final String Home3 = "http://yyapp.1yuaninfo.com/app/houtai/";
    /**
     * 三找图片地址
     *
     */
    public static final String FInder = "http://yyfw.1yuaninfo.com/houtai/";
    /**
     * 项目地址URL
     *
     */
    public static final String XINGMU = "http://yyfw.1yuaninfo.com/houtai/";
    /**
     *
     *
     */
    public static final String HomeMUsic = "http://yyfw.1yuaninfo.com/houtai/";
    /**
     *
     * 牛人图片地址
     */
    public static final String Home4 = "http://yyapp.1yuaninfo.com/";
    /**
     * http://yyapp.1yuaninfo.com/app/houtai/
     */
    public static final String Homevideo = "http://yyapp.1yuaninfo.com/app/houtai/";

    /**
     *新闻地址
     */
         // http://yyapp.1yuaninfo.com/app/yyfwapp/news-details.php?id=''&userid=''

    public static final String NEWSURL = "http://yyapp.1yuaninfo.com/app/yyfwapp/news-details.php?";
    /**
     *
     * 快讯详情地址
     *http://yyapp.1yuaninfo.com/app/yyfwapp/newsflash.php?wid=1
     */
    public static final String FLASHDETAIL = "http://yyapp.1yuaninfo.com/app/yyfwapp/newsflash.php?wid=";

    public static final String NIU = "http://yyapp.1yuaninfo.com/app/yyfwapp/niu_article.php?";
    /**
     *
     * http://yyapp.1yuaninfo.com/app/yyfwapp/xiangmu-details.php?xmid=570
     */
    public  static  final String XMDETAIL="http://yyapp.1yuaninfo.com/app/yyfwapp/xiangmu-details.php?xmid=";
    /**
     *演出地址
     */
    public static final String SHOWURL = "http://yyapp.1yuaninfo.com/app/yyfwapp/showdetails.php?";
    /**
     *http://yyapp.1yuaninfo.com/app/yyfwapp/showdetails.php?id=50
     */
    public static final String Home1 = "http://192.168.0.103/app/application/";
    public static final String umeng = "http://yyapp.1yuaninfo.com/app/houtai/admin/";
    /**
     *
     */
    public static final String Home2 = "http://192.168.0.163/app/application/updateUserHead.php";
    /**
     * 版本更新请求地址
     *
     */
    public static final String VERSION = "http://yyapp.1yuaninfo.com/";
    public static final String VERSIONCODE = "http://yyapp.1yuaninfo.com/version.html";
    /**
     * APK下载地址
     *
     */
    public static final String APK = "http://yyapp.1yuaninfo.com/yiyuan.apk";
    /**
     *官网地址
     */
    public static final String YIYUAN = "http://www.1yuaninfo.com";
    /**
     * http://yyapp.1yuaninfo.com/app/houtai/push.php?id=1
     * 友盟推送的消息
     */

    public static final String UmengMessage = "http://yyapp.1yuaninfo.com/app/houtai/push.php?id=";

    public static final String announcement = "http://yyapp.1yuaninfo.com/app/yyfwapp/announcement.html";
    public static final String agreement = "http://yyapp.1yuaninfo.com/app/yyfwapp/agreement.html";
    // http://192.168.0.133/app/application/channel.php?act=video
    //版本更新下载地址
    public static final String DownLoadAPK = "http://www.apk3.com/uploads/soft/guiguangbao/UCllq.apk";

    /**
     * beijingjingyiyuanzixunfuwuyouxiangongsi
     * 密码后缀
     */
    public static final String FLAG = "beijingjingyiyuanzixunfuwuyouxiangongsi";
    /**
     *
     * 视频分享的地址
     */
    public static final String VIDEOSHARE = "http://yyapp.1yuaninfo.com/app/share/video_share.php?id=";

    public  static final String  string1="尊敬的用户，您尚未成为会员，请联系客服010-87777898。";
    public  static final String  string2="尊敬的会员，您尚未购买此服务，请联系客服010-87777898。";
    public  static final String  string3="尊敬的会员，您已达服务期限已过。";
    public  static final String  string4="尊敬的会员，您已达持股上限3支（以上），再次与您确认是否需要？";
    public  static final String  string5="尊敬的会员，您有新的股票信息，是否确认需要。";
    public  static final String  string6="未购买次服务的。";

}
