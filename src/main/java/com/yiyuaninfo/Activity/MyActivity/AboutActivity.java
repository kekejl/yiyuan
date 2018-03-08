package com.yiyuaninfo.Activity.MyActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allenliu.versionchecklib.AVersionService;
import com.allenliu.versionchecklib.VersionParams;
import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Activity.MainActivity;
import com.yiyuaninfo.Activity.UmengMessageActivity;
import com.yiyuaninfo.Activity.WebViewActivity;
import com.yiyuaninfo.App;
import com.yiyuaninfo.Interface.FileUploadService;
import com.yiyuaninfo.Interface.PostBiz;
import com.yiyuaninfo.Interface.VersionBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.Ui.PullScrollView;
import com.yiyuaninfo.VersionService;
import com.yiyuaninfo.entity.Head;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.Version;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.Constants;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.FieldMap;

/**
 * Created by Administrator on 2017/2/24.
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout  fankui;
    private LinearLayout  pingfen;
    private LinearLayout  jieshao;
    private LinearLayout  version;
    private LinearLayout  xieyi;
    private TextView   textView;
    private TextView   tvVersion;
    @Override
    protected int getContentView() {
        return R.layout.activity_about;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        setToolBarTitle("关于我们");
        textView=(TextView)findViewById(R.id.tv_about_Version);
        fankui=(LinearLayout)findViewById(R.id.ll_about_fk);
        pingfen=(LinearLayout)findViewById(R.id.ll_about_pf);
        jieshao=(LinearLayout)findViewById(R.id.ll_about_jieshao);
        version=(LinearLayout)findViewById(R.id.ll_about_version);
        xieyi=(LinearLayout)findViewById(R.id.ll_about_xy);
        tvVersion=(TextView)findViewById(R.id.tv_about_Version);
        fankui.setOnClickListener(this);
        pingfen.setOnClickListener(this);
        jieshao.setOnClickListener(this);
        version.setOnClickListener(this);
        xieyi.setOnClickListener(this);
         textView.setText("当前版本 V "+CommonUtil.getVerName(App.getContext()));

//
//        Map<String,String>  map=new HashMap<>();
//        //@{@"produtid":@"1",@"goodtype":@"1",USERID:user.userid,@"apple_receipt":base64Str
//        map.put("produtid","1");
//        map.put("goodtype","1");
//        map.put("userid","1507604192petx5v");
//        map.put("apple_receipt","1");
//
//        //map.put("apple_receipt","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");
//        RetrofitUtil.getretrofit().create(PostBiz.class).upload(map
//        ).enqueue(new Callback<State>() {
//            @Override
//            public void onResponse(Call<State> call, Response<State> response) {
//                Log.v("上传成功1111 ", "111111"+response.body().getState());
//
//            }
//
//            @Override
//            public void onFailure(Call<State> call, Throwable t) {
//
//            }
//        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_about_fk:
                CommonUtil.goAactivity(this,YJFKActivity.class);
                break;
            case R.id.ll_about_xy:
                CommonUtil.goAactivity(AboutActivity.this, WebViewActivity.class, "msgurl",Constants.agreement,"title","用户声明及协议");
                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.ll_about_version:

                RetrofitUtil.getretrofit1(Constants.VERSION).create(VersionBiz.class).getData().enqueue(new Callback<Version>() {
                    @Override
                    public void onResponse(Call<Version> call, Response<Version> response) {
                        Log.d("123123123",response.body()+"");
                        if(CommonUtil.getVerName(App.getContext()).equals(response.body().getVersion())){
                            ToastUtils.showToast("当前版本已是最新版本！！");
                        }else {

                            VersionParams versionParams = new VersionParams().setRequestUrl(Constants.VERSIONCODE);
                            Intent intent = new Intent(AboutActivity.this, VersionService.class);
                            intent.putExtra(AVersionService.VERSION_PARAMS_KEY, versionParams);
                            startService(intent);

                        }

                    }

                    @Override
                    public void onFailure(Call<Version> call, Throwable t) {

                    }
                });

                break;
            case R.id.ll_about_jieshao:
                CommonUtil.goAactivity(AboutActivity.this, WebViewActivity.class, "msgurl",Constants.announcement,"title","壹元介绍");
                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.ll_about_pf:

                Uri uri = Uri.parse("market://details?id="+getPackageName());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
