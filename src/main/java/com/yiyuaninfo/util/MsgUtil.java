package com.yiyuaninfo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yiyuaninfo.entity.MsgPush;
import com.yiyuaninfo.entity.User;

/**
 * Created by gaocongcong on 2017/7/26.
 */

public class MsgUtil {
   private  static String  strings;
    private static  String  string1="\u3000\u3000";
    private static  String  string2="\r\n";
    private static  String  string5="\r\n";
    private static  String  string4="\r\n\r\n\r\n\t";
    private static  String  string3="\r\n\u3000\u3000";
    private static  String  string6="\n";
  public static  String   SwitchString(MsgPush.MsglistArrBean entity){
       strings="";
      //消息内容
      if(entity.getRemark()!=null&&!entity.getRemark().equals("")){
          strings=entity.getRemark().replaceAll(string4,string3);
      }
      //分享时间
      if(entity.getFxtime()!=null&&!entity.getFxtime().equals("")){
        strings+=string5+"分享日期:"+entity.getFxtime();
      }
      //代码名称
      if(entity.getDmname()!=null&&!entity.getDmname().equals("")){
         strings+=string5+"代码名称:"+entity.getDmname();
      }
      //分享理由
      if(entity.getFxcontent()!=null&&!entity.getFxcontent().equals("")){
         strings+=string5+"分享理由:"+entity.getFxcontent().replaceAll(string4,string6).replace(string2,string6);
      }
      //介入区间
      if(entity.getSection()!=null&&!entity.getSection().equals("")){
        strings+=string5+"介入区间:"+entity.getSection();
      }
      //当前价格
      if(entity.getFxmoney()!=null&&!entity.getFxmoney().equals("")){
          strings+=string5+"当前价格:"+entity.getFxmoney();

      }
      //止损价格
      if(entity.getZsmoney()!=null&&!entity.getZsmoney().equals("")){
          strings+=string5+"止损价格:"+entity.getZsmoney();

      }
      //温馨提示
      if(entity.getMention()!=null&&!entity.getMention().equals("")){
         strings+=string5+"温馨提示:"+entity.getMention().replaceAll(string4,string3);
      }
      return strings;
  }

}
