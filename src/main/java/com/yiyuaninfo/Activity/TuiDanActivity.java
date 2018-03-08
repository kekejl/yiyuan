package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.yiyuaninfo.Interface.BackOrderBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.NoteAdapter;
import com.yiyuaninfo.entity.FirstCommentBean;
import com.yiyuaninfo.entity.Note;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.entity.User;
import com.yiyuaninfo.entity.UserOrder;
import com.yiyuaninfo.popup.PopupWindow.CommonPopupWindow;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.FullLinearLayout;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by gaocongcong on 2017/8/14.
 */

public class TuiDanActivity extends BaseActivity   implements CommonPopupWindow.ViewInterface{
    private Button button;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private EditText editText;
    private String orderid;
    private String numres, res;
    private String type;
    private CommonPopupWindow  popupWindowtishi;
    private UserOrder.UserOrderBean  entity;
    @Override
    protected int getContentView() {
        return R.layout.activity_tuidan;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        setToolBarTitle("申请终止服务");
        entity=(UserOrder.UserOrderBean) getIntent().getSerializableExtra("entity");

        type = entity.getPaystatus();

        button = (Button) findViewById(R.id.btn_tuidan);
        checkBox1 = (CheckBox) findViewById(R.id.cb_tuidan1);
        checkBox2 = (CheckBox) findViewById(R.id.cb_tuidan2);
        checkBox3 = (CheckBox) findViewById(R.id.cb_tuidan3);
        checkBox4 = (CheckBox) findViewById(R.id.cb_tuidan4);
        editText = (EditText) findViewById(R.id.edit_tuidan);
       // orderid = getIntent().getStringExtra("orderid");
        orderid = entity.getId();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(type.equals("3")){

                   showpopwindowtishi();

               }else {
                  // CommonUtil.goAactivity(TuiDanActivity.this, TuiDanTiShiActivity.class);

                  todo();
               }


//             EasyHttp.get("http://yyapp.1yuaninfo.com/app/houtai/admin/backOrder.php")
//                     .params("","")
//                     .params("","")
//                     .params("","")
//                     .params("","")
//                     .execute(new SimpleCallBack<>() {
//             })

            }



            private void todo() {

                numres = "";
                if (checkBox1.isChecked()) {
                    numres = ",1";
                }
                if (checkBox2.isChecked()) {
                    numres = numres + ",2";
                }
                if (checkBox3.isChecked()) {
                    numres = numres + ",3";

                }
                if (checkBox4.isChecked()) {
                    numres = numres + ",4";

                }

                if (!numres.equals("")) {
                    numres = numres.substring(1, numres.length());

                }
                if (editText.getText().toString() == null) {
                    res = "";
                } else {

                    res = editText.getText().toString();
                }

                if (numres.equals("") && res.equals("")) {
                    ToastUtils.showToast("请选择理由");
                } else {


                    Log.d("退单理由", numres + "+++" + res + "++" + orderid + "+++" + SPUtil.getUser(TuiDanActivity.this).getUserid());
                    Map<String, String> params = new HashMap<>();

                    params.put("userid", SPUtil.getUser(TuiDanActivity.this).getUserid());
                    params.put("orderid", orderid);
                    params.put("numres", numres);
                    params.put("res", res);

                    RetrofitUtil.getretrofit1("http://yyapp.1yuaninfo.com/app/houtai/admin/").create(BackOrderBiz.class).getData(params)

                            .enqueue(new Callback<State>() {
                                @Override
                                public void onResponse(Call<State> call, Response<State> response) {
                                    //  ToastUtils.showToast("已提交退单申请，稍后工作人员会与您联系，请保持通讯畅通");

                                    entity.setPaystatus("3");

                                    Bundle bundle =new Bundle();
                                    bundle.putSerializable("entity",entity);
                                    setResult(RESULT_OK,new Intent().putExtras(bundle));

                                   CommonUtil.goAactivity(TuiDanActivity.this, TuiDanTiShiActivity.class);
                                    finish();
                                    Log.d("退单理由", response.body().toString() + "+++" + call.toString());

                                }

                                @Override
                                public void onFailure(Call<State> call, Throwable t) {
                                }


                            });

                }
            }
        });


    }


    @Override
    public void getChildView(View view, int layoutResId) {
        TextView textView = (TextView) view.findViewById(R.id.tv_tishi_qx);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowtishi.dismiss();
            }
        });

    }



    private void showpopwindowtishi() {

        if (popupWindowtishi != null && popupWindowtishi.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popwindow_tuidan, null);
        //测量View的宽高
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        CommonUtil.measureWidthAndHeight(upView);
        popupWindowtishi = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_tuidan)
                .setWidthAndHeight(width - 300, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUpkf)
                .setViewOnclickListener(this)
                .create();
        popupWindowtishi.setOutsideTouchable(false);
        popupWindowtishi.setFocusable(false);
        popupWindowtishi.showAtLocation(button, Gravity.CENTER, 0, 0);



    }


    @Override
    public void onBackPressed() {




        finish();
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();

    }
}
