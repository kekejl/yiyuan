package com.yiyuaninfo.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.necer.ncalendar.calendar.MonthCalendar;
import com.necer.ncalendar.listener.OnClickMonthCalendarListener;
import com.necer.ncalendar.listener.OnMonthCalendarPageChangeListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.util.DateUtil;
import com.yiyuaninfo.util.DateUtils;

import org.joda.time.DateTime;


/**
 * Created by gaocongcong on 2017/10/20.
 */

public class MonthCalendarActivity extends BaseActivity{
    private MonthCalendar monthCalendar;
    private String  date;
    private String year,month,day,week;
    private Intent intent;

    private ImageView  back;
    private TextView  title,tvtrue;
    int a,b,c;
    @Override
    protected int getContentView() {
        return R.layout.activity_month;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isshowToolBar(false);
        intent=getIntent();
        date=intent.getStringExtra("date");
        //date="2017-10-20";
        a=Integer.parseInt(DateUtils.getTimeYear(date));
        b=Integer.parseInt(DateUtils.getTimeMonth(date));
        c=Integer.parseInt(DateUtils.getTimeDay(date));
        monthCalendar=(MonthCalendar)findViewById(R.id.monthCalendar);
        back=(ImageView)findViewById(R.id.image_month_back);
        title=(TextView) findViewById(R.id.tv_month_Title);
        tvtrue=(TextView) findViewById(R.id.tv_month_true);

        monthCalendar.postDelayed(new Runnable() {
            @Override
            public void run() {
                monthCalendar.setDate(a,b,c,true);
            }
        },1);

        Log.d("接收的日期", date+"++"+DateUtils.getTimeYear(date)+"++"+DateUtils.getTimeMonth(date)+"++"+DateUtils.getTimeDay(date));


        monthCalendar.setOnClickMonthCalendarListener(new OnClickMonthCalendarListener() {
            @Override
            public void onClickMonthCalendar(DateTime dateTime) {



               // Toast.makeText(MonthCalendarActivity.this, "选择了：：" + dateTime.toLocalDate(), Toast.LENGTH_SHORT).show();
                date=dateTime.toLocalDate()+"";


                week= dateTime.toLocalDate().getDayOfWeek()+"";
                month=dateTime.toDateTime().getMonthOfYear()+"";
                year=dateTime.toDateTime().getYear()+"";


            }
        });

        monthCalendar.setOnMonthCalendarPageChangeListener(new OnMonthCalendarPageChangeListener() {
            @Override
            public void onMonthCalendarPageSelected(DateTime dateTime) {
                title.setText(dateTime.getYear() + "年" + dateTime.getMonthOfYear() + "月");

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvtrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(month==null){
                    finish();
                }else {


                    Log.d("选择的日期", month + "++" + year + "+++" + week + "++" + date);
                    Intent intent = new Intent();
                    intent.putExtra("date", date);
                    intent.putExtra("week", week);
                    intent.putExtra("month", month);
                    intent.putExtra("year", year);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });

    }
}
