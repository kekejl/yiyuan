package com.yiyuaninfo.Activity.MyActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lljjcoder.city_20170724.CityPickerView;
import com.lljjcoder.city_20170724.bean.CityBean;
import com.lljjcoder.city_20170724.bean.DistrictBean;
import com.lljjcoder.city_20170724.bean.ProvinceBean;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.yiyuaninfo.Activity.BaseActivity;
import com.yiyuaninfo.Interface.ADDAddressBiz;
import com.yiyuaninfo.Interface.AddressBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Address;
import com.yiyuaninfo.entity.State;
import com.yiyuaninfo.util.RetrofitUtil;
import com.yiyuaninfo.util.SPUtil;
import com.yiyuaninfo.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class AddAddressActivity extends BaseActivity {
    private TextView tvcity, save;
    private EditText etname, etphone, etdizhi;
    private String name, phone, dizhi;
    private ImageView imageView;
    private String province1, city1, district1, act;

    @Override
    protected int getContentView() {
        return R.layout.activity_addaddress;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isshowToolBar(false);
        getData();
        tvcity = (TextView) findViewById(R.id.tv_addaddress_city);
        save = (TextView) findViewById(R.id.tv_addaddress_save);
        imageView = (ImageView) findViewById(R.id.image_addadderss_back);
        etname = (EditText) findViewById(R.id.et_addaddress_name);
        etphone = (EditText) findViewById(R.id.et_addaddress_phone);
        etdizhi = (EditText) findViewById(R.id.et_addaddress_dizhi);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!etname.getText().toString().equals("")&&
                        !etdizhi.getText().toString().equals("")&&
                        !etphone.getText().toString().equals("")&&
                        !province1.equals("")&&
                        !city1.equals("")&&
                        !district1.equals("")
                        ){


                //http://yyapp.1yuaninfo/app/application.com/useraddress.php?
                // act=moduseradd&userid=1506586197qs54uw&prov_city=省市区&detailed_add=详细地址&re_name=字符串&re_phone=13718040895
                Map<String, String> params = new HashMap<>();
                params.put("act", act);
                params.put("userid", SPUtil.getUser(AddAddressActivity.this).getUserid());
                params.put("prov_city", province1 + "," + city1 + "," + district1);
                params.put("detailed_add", etdizhi.getText().toString());
                params.put("re_name", etname.getText().toString());
                params.put("re_phone", etphone.getText().toString());

                RetrofitUtil.getretrofit().create(ADDAddressBiz.class).getData(params).enqueue(new Callback<State>() {
                    @Override
                    public void onResponse(Call<State> call, Response<State> response) {
                        if (response.body().getState().equals("1")) {
                            ToastUtils.showToast("保存成功");
                            act = "moduseradd";

                        } else {
                            ToastUtils.showToast("保存失败");

                        }
                    }

                    @Override
                    public void onFailure(Call<State> call, Throwable t) {

                    }
                });

            }else {
                    ToastUtils.showToast("输入不能为空！！");
                }
            }
        });
    }

    private void getData() {
        final Map<String, String> params = new HashMap<>();
        params.put("act", "getuseradd");
        params.put("userid", SPUtil.getUser(this).getUserid());
        RetrofitUtil.getretrofit().create(AddressBiz.class).getData(params).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                // Log.d("收货地址",response.body().getUser_add().get(0).toString());
                if (response.body().getUser_add().size() != 0) {
                    Log.d("收货地址", response.body().getUser_add().get(0).toString());

                    name = response.body().getUser_add().get(0).getRe_name();
                    phone = response.body().getUser_add().get(0).getRe_phone();
                    dizhi = response.body().getUser_add().get(0).getDetailed_add();
                    String[] tags = response.body().getUser_add().get(0).getProv_city().split(",");
                    province1 = tags[0];
                    city1 = tags[1];
                    district1 = tags[2];
                    act = "moduseradd";
                    tvcity.setText(province1 + "  " + city1 + "   " + district1 + " ");

                } else {

                    name = "";
                    phone = "";
                    dizhi = "";
                    province1="";
                    city1="";
                    district1="";
                    act = "addaddr";
                }

                etname.setText(name);
                etphone.setText(phone);
                etdizhi.setText(dizhi);

                tvcity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CityPicker cityPicker = new CityPicker.Builder(AddAddressActivity.this).textSize(20)
                                .titleTextColor("#000000")
                                .backgroundPop(0xa0000000)
                                .province(province1)
                                .city(city1)
                                .district(district1)
                                .textColor(Color.parseColor("#000000"))
                                .provinceCyclic(true)
                                .cityCyclic(false)
                                .districtCyclic(false)
                                .visibleItemsCount(7)
                                .itemPadding(10)
                                .build();
                        cityPicker.show();
                        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                            @Override
                            public void onSelected(String... citySelected) {


                                province1 = citySelected[0];
                                city1 = citySelected[1];
                                district1 = citySelected[2];
                                tvcity.setText(province1 + "  " + city1 + "   " + district1 + " ");
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
//                        cityPicker.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
//                            @Override
//                            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
//                                //返回结果
//                                tvcity.setText(province.getName() + "  " + city.getName() + "   " + district.getName() + " ");
//                                province1 = province.getName();
//                                city1 = city.getName();
//                                district1 = district.getName();
//
//                            }
//
//                            @Override
//                            public void onCancel() {
//
//                            }
//                        });
                    }
                });

            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {

            }
        });
    }

}
