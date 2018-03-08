package com.yiyuaninfo.Activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yiyuaninfo.Interface.MsgPushBiz;
import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.DayAdapter;
import com.yiyuaninfo.adapter.MsgPushAdapter;
import com.yiyuaninfo.entity.DateItem;
import com.yiyuaninfo.entity.MsgPush;
import com.yiyuaninfo.util.CommonUtil;
import com.yiyuaninfo.util.DateUtil;
import com.yiyuaninfo.util.DateUtils;
import com.yiyuaninfo.util.EmptyUtil;
import com.yiyuaninfo.util.RetrofitUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by gaocongcong on 2017/8/11.
 */

public class PushListActivity extends  BaseActivity  implements View.OnClickListener {
    private RecyclerView rv,recyclerView;
    private DayAdapter adapter;
    private int minYear;
    private int curYear;
    private int curMonth;
    private int curDay;
    private int curWeek;
    private int selectedYear;
    private int selectedMonth;
    private int selectedDay;
    private int selectedWeek;
    private int firstWeek;
    private int curMonthLastDay;
    private boolean showPickDialog;
    private int unitColor;
    private int yearTextColor;
    private int monthTextColor;
    private OnDaySelectedListener daySelectedListener;
    private OnYearMonthClickListener onYearMonthClickListener;
    private int dayTextColorSelected;
    private int dayTextColorNormal;
    private int daySelectionColor;
    private int weekTextColor;
    private int pressShapeSelectorId;
    private int todayPointColor;
    private MsgPushAdapter  msgadapter;
    private SimpleDateFormat formatter;
    private  String  datenow;
    private ImageView  imageViewCalendar ,back;
    private String month,year,day,week;
    private View  notDataView,errorView;
    private LinearLayout  linearLayout;
    private EmptyUtil  emptyUtil;
    private String  timeintent;
    @Override
    protected int getContentView() {
        return R.layout.activity_pushlist;

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isshowToolBar(false);
        timeintent=getIntent().getStringExtra("time");
        Log.d("接收的时间",timeintent+"");
        imageViewCalendar=(ImageView) findViewById(R.id.image_calendar);
        linearLayout=(LinearLayout)findViewById(R.id.ll_emptyview) ;
        back=(ImageView) findViewById(R.id.image_calendar_back);
        imageViewCalendar.setOnClickListener(this);
        back.setOnClickListener(this);
        rv=(RecyclerView)findViewById(R.id.rv_calendar);
        recyclerView=(RecyclerView) findViewById(R.id.rv_listpush);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

        emptyUtil=new EmptyUtil(this,recyclerView);
        emptyUtil.initErrorPage();


        TypedArray a = this.obtainStyledAttributes( R.styleable.ZzHorizontalCalenderView);
        showPickDialog = a.getBoolean(R.styleable.ZzHorizontalCalenderView_zhc_show_pick_dialog, true);
        minYear = a.getInteger(R.styleable.ZzHorizontalCalenderView_zhc_min_year, 2012);
        unitColor = a.getColor(R.styleable.ZzHorizontalCalenderView_zhc_unit_color, 0xff3498DB);
        yearTextColor = a.getColor(R.styleable.ZzHorizontalCalenderView_zhc_year_text_color, 0xff000000);
        monthTextColor = a.getColor(R.styleable.ZzHorizontalCalenderView_zhc_month_text_color, 0xff000000);
        dayTextColorSelected = a.getColor(R.styleable.ZzHorizontalCalenderView_zhc_day_selected_text_color, 0xffffffff);
        dayTextColorNormal = a.getColor(R.styleable.ZzHorizontalCalenderView_zhc_day_unselected_text_color, 0xff000000);
        weekTextColor = a.getColor(R.styleable.ZzHorizontalCalenderView_zhc_week_text_color, 0xff000000);
        daySelectionColor = a.getColor(R.styleable.ZzHorizontalCalenderView_zhc_selection_color, 0xffd43c33);
        pressShapeSelectorId = a.getResourceId(R.styleable.ZzHorizontalCalenderView_zhc_press_shape_selector, R.drawable.month_year_bg_selector);
        todayPointColor = a.getColor(R.styleable.ZzHorizontalCalenderView_zhc_today_point_color, 0xffed9f24);
        a.recycle();
        initData();


        if(timeintent==null){
            datenow=DateUtil.getCurrentYear()+"-"+CommonUtil.getMonth(DateUtil.getCurrentMonth())+"-"+CommonUtil.getMonth(DateUtil.getCurrentDay());

            setSelectedDate(DateUtil.getCurrentYear(),DateUtil.getCurrentMonth(),DateUtil.getCurrentDay(),DateUtil.getCurrentWeek());
            checkMonth(CommonUtil.getMonth(DateUtil.getCurrentMonth()));

        }else {
            datenow=timeintent;
            Log.d("传递过来的年月日周",DateUtils.getTimeMonth(datenow)+"+++"+DateUtils.getTimeDay(datenow)+"+++");
            checkMonth(DateUtils.getTimeMonth(datenow));

            int dateyear=Integer.parseInt(DateUtils.getTimeYear(datenow));
            int datemonth=Integer.parseInt(CommonUtil.getStingToString(DateUtils.getTimeMonth(datenow)));
            int dateday=Integer.parseInt(CommonUtil.getStingToString(DateUtils.getTimeDay(datenow)));
            int dateweek=DateUtil.calWeek(dateyear,datemonth,dateday);
            Log.d("传递过来的年月日周",dateyear+"++"+datemonth+"+"+dateday+"+"+dateweek+"");

              setSelectedDate(dateyear,datemonth,dateday,dateweek);

        }

        Log.d("当前时间",datenow+"---"+DateUtil.getCurrentMonth());
        getData();

//        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
//        notDataView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onRefresh();
//            }
//        });
//        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) recyclerView.getParent(), false);
//        errorView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onRefresh();
//            }
//        });

   }
//    private boolean mError = true;
//    private boolean mNoData = true;
//
//    private void onRefresh() {
//        msgadapter.setEmptyView(R.layout.loading_view, (ViewGroup) mRecyclerView.getParent());
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (mError) {
//                    msgadapter.setEmptyView(errorView);
//                    mError = false;
//                } else {
//                    if (mNoData) {
//                        msgadapter.setEmptyView(notDataView);
//                        mNoData = false;
//                    } else {
//                        msgadapter.setNewData(DataServer.getSampleData(10));
//                    }
//                }
//            }
//        }, 1000);
//    }
    private void getData() {
        RetrofitUtil.getretrofit().create(MsgPushBiz.class).getData(datenow).enqueue(new Callback<MsgPush>() {
            @Override
            public void onResponse(Call<MsgPush> call, Response<MsgPush> response) {

                 if(response.body()==null){
                     emptyUtil.showErrorPage();
                     Log.d("推送数据","加载失败");

                 }else {


                if(response.body().getMsglist_arr().size()!=0) {
                    Log.d("推送数据",response.body().getMsglist_arr().toString());
                    recyclerView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);

                    Log.d("推送列表数据", response.body().getMsglist_arr().toString());
                    msgadapter = new MsgPushAdapter(PushListActivity.this, response.body().getMsglist_arr());
                  //MsgPushListAdapter  msgPushListAdapter = new MsgPushListAdapter(PushListActivity.this, R.layout.item_msgpush,response.body().getMsglist_arr());

                    recyclerView.setAdapter(msgadapter);

                    recyclerView.smoothScrollToPosition(msgadapter.getItemCount()-1);
                }else {
                    Log.d("推送数据","无数据");

                    linearLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                 }

            }

            @Override
            public void onFailure(Call<MsgPush> call, Throwable t) {

            }
        });

    }
//    private void getDataRefresh() {
//        RetrofitUtil.getretrofit().create(MsgPushBiz.class).getData(datenow).enqueue(new Callback<MsgPush>() {
//            @Override
//            public void onResponse(Call<MsgPush> call, Response<MsgPush> response) {
//                if(response.body().getMsglist_arr().size()!=0) {
//                    recyclerView.setVisibility(View.VISIBLE);
//                    linearLayout.setVisibility(View.GONE);
//
//                    Log.d("推送列表数据", response.body().getMsglist_arr().toString());
//                    msgadapter = new MsgPushAdapter(PushListActivity.this, response.body().getMsglist_arr());
//                    recyclerView.setAdapter(msgadapter);
//                }else {
//                    linearLayout.setVisibility(View.VISIBLE);
//                    recyclerView.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MsgPush> call, Throwable t) {
//
//            }
//        });
//
//    }

    private void initData() {
        curYear = DateUtil.getCurrentYear();
        curMonth = DateUtil.getCurrentMonth();
        curDay = DateUtil.getCurrentDay();
        curWeek = DateUtil.getCurrentWeek();
        selectedYear = curYear;
        selectedMonth = curMonth;
        selectedDay = curDay;
        selectedWeek = curWeek;


        List<DateItem> items = getItems(selectedYear,selectedMonth,selectedDay);

        Log.d("数据",items.toString());


        adapter = new DayAdapter(this, items, dayTextColorSelected, dayTextColorNormal, daySelectionColor, weekTextColor, pressShapeSelectorId, todayPointColor);
        adapter.setItemClick(new DayAdapter.OnItemClick() {
            @Override
            public void onItemClick(int year, int month, int day, int week) {

                Log.d("当前时间1",year+"=="+month+"++"+day);
                datenow=year+"-"+CommonUtil.getMonth(month)+"-"+CommonUtil.getMonth(day);
                Log.d("当前时间1",datenow);

                getData();
                setSelectedDate(year, month, day, week);






            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        rv.offsetChildrenHorizontal(3);
        rv.setAdapter(adapter);
        rv.scrollToPosition(curDay - 4);
    }

    private List<DateItem> getItems(int dateyear,int datemonth,int dateday) {
        List<DateItem> items = new ArrayList<DateItem>();
        firstWeek = getFirstDayOfWeek(dateyear, datemonth);
        int tempWeek = firstWeek;

        int lastMonthDay = getLastDayOfMonth(dateyear, datemonth) - firstWeek;
        curMonthLastDay = DateUtil.calDayOfMonth(dateyear, datemonth);
        int nextMonthDay = 1;

        DateItem item = null;
        for (int i = 0; i < 42; i++) {
            item = new DateItem();
            if (i < firstWeek) {
                lastMonthDay++;
                item.setIsLastMonth(true);
                item.setDay(lastMonthDay);
            } else {
                item.setIsLastMonth(false);
                int d = i - firstWeek + 1;
                if (d <= curMonthLastDay) {
                    item.setIsNextMonth(false);
                    if (d == dateday) {
                        item.setSelected(true);
                    }
                    item.setDay(i - firstWeek + 1);
                    item.setWeek(tempWeek % 7);
                    item.setYear(dateyear);
                    item.setMonth(datemonth);
                    if (dateyear == curYear && datemonth == curMonth && d == curDay) {
                        item.setCurrent(true);
                    }
                    items.add(item);
                    tempWeek++;
                } else {
                    item.setIsNextMonth(true);
                    item.setDay(nextMonthDay);
                    nextMonthDay++;
                }
            }
        }
        return items;
    }
    public int getFirstDayOfWeek(int y, int m) {
        return DateUtil.calWeek(y, m, 1);
    }
    public int getLastDayOfMonth(int y, int m) {
        //TODO FIXME
        if (m == 1) {
            return DateUtil.calDayOfMonth(y - 1, 12);
        } else {
            return DateUtil.calDayOfMonth(y, m - 1);
        }
    }
    private void setSelectedDate(int year, int month, int day, int week) {
        int lastYear = selectedYear;
        int lastMonth = selectedMonth;
        int lastDay = selectedDay;
        this.selectedYear = year;
        this.selectedMonth = month;
        this.selectedDay = day;
        this.selectedWeek = week;
        if (daySelectedListener != null) {
            daySelectedListener.onSelected(lastYear != year || lastMonth != month || lastDay != day,
                    year, month, day, selectedWeek);
        }
        adapter.setDatas(getItems(selectedYear,selectedMonth,selectedDay));
        adapter.notifyDataSetChanged();
        rv.scrollToPosition(selectedDay - 4);
        rv.smoothScrollToPosition(selectedDay + 2);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.image_calendar:
                Intent  intent=new Intent(this,MonthCalendarActivity.class);
                intent.putExtra("date",datenow);
                startActivityForResult(intent,1);
                break;
            case R.id.image_calendar_back:
                finish();
                break;
        }


    }

    public interface OnYearMonthClickListener {
        void onYearClick(int selectedYear, int selectedMonth);

        void onMonthClick(int selectedYear, int selectedMonth);
    }

    public interface OnDaySelectedListener {
        void onSelected(boolean hasChanged, int year, int month, int day, int week);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){

                int a,b,c,d;

                 datenow= data.getStringExtra("date");
                d=Integer.parseInt(data.getStringExtra("week"));
                a=Integer.parseInt(DateUtils.getTimeYear(datenow));
                b=Integer.parseInt(DateUtils.getTimeMonth(datenow));
                c=Integer.parseInt(DateUtils.getTimeDay(datenow));
                adapter.setDatas(getItems(a,b,c));
                adapter.notifyDataSetChanged();

                setSelectedDate(a,b, c, d);

                getData();
                checkMonth(DateUtils.getTimeMonth(datenow));

            }
        }

    }

    private void checkMonth(String s) {
        switch (s){
            case "01":
                imageViewCalendar.setBackgroundResource(R.drawable.month1);
                break;
            case "02":
                imageViewCalendar.setBackgroundResource(R.drawable.month2);
                break;
            case "03":
                imageViewCalendar.setBackgroundResource(R.drawable.month3);
                break;
            case "04":
                imageViewCalendar.setBackgroundResource(R.drawable.month4);
                break;
            case "05":
                imageViewCalendar.setBackgroundResource(R.drawable.month5);
                break;
            case "06":
                imageViewCalendar.setBackgroundResource(R.drawable.month6);
                break;
            case "07":
                imageViewCalendar.setBackgroundResource(R.drawable.month7);
                break;
            case "08":
                imageViewCalendar.setBackgroundResource(R.drawable.month8);
                break;
            case "09":
                imageViewCalendar.setBackgroundResource(R.drawable.month9);
                break;
            case "10":
                imageViewCalendar.setBackgroundResource(R.drawable.month10);
                break;
            case "11":
                imageViewCalendar.setBackgroundResource(R.drawable.month11);
                break;
            case "12":
                imageViewCalendar.setBackgroundResource(R.drawable.month12);
                break;



        }

    }
}
