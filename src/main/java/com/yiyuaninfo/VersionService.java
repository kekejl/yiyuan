package com.yiyuaninfo;

import android.util.Log;

import com.allenliu.versionchecklib.AVersionService;
import com.google.gson.Gson;
import com.yiyuaninfo.entity.Version;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;

/**
 * Created by gaocongcong on 2017/7/29.
 */

public class VersionService  extends AVersionService{

    private  String  VerName;
    @Override
    public void onResponses(AVersionService service, String response) {

        VerName = CommonUtil.getVerName(App.getContext());
        Log.d("当前版本号", VerName+"***"+response.toString());

        Gson  gson=new Gson();

        Version  version=gson.fromJson(response,Version.class);
        Log.d("版本返回信息",response+"\n"+version.getVersion()+"\n"+version.getMsg());
        if(!VerName.equals(version.getVersion())){

        service.showVersionDialog(Constants.APK,"检测到新版本",version.getMsg());
        }

    }
}
