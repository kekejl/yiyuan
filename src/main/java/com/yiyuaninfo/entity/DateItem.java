package com.yiyuaninfo.entity;

/**
 * Created by ZZ on 2016/3/16.
 */
public class DateItem {

    private int day;//日
    private int week;//周
    private int year;//年
    private int month;//月
    private boolean isLastMonth;//
    private boolean isNextMonth;
    private boolean selected;//是否选中
    private boolean current;//是否当前

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public boolean isLastMonth() {
        return isLastMonth;
    }

    public void setIsLastMonth(boolean isLastMonth) {
        this.isLastMonth = isLastMonth;
    }

    public boolean isNextMonth() {
        return isNextMonth;
    }

    public void setIsNextMonth(boolean isNextMonth) {
        this.isNextMonth = isNextMonth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

//    @Override
//    public String toString() {
//        return "DateItem{" +
//                "day=" + day +
//                ", week=" + week +
//                ", year=" + year +
//                ", month=" + month +
//                ", isLastMonth=" + isLastMonth +
//                ", isNextMonth=" + isNextMonth +
//                ", selected=" + selected +
//                '}' + "\n";
//    }


    @Override
    public String toString() {
        return "DateItem{" +
                "day=" + day +
                ", week=" + week +
                ", year=" + year +
                ", month=" + month +
                ", isLastMonth=" + isLastMonth +
                ", isNextMonth=" + isNextMonth +
                ", selected=" + selected +
                ", current=" + current +
                '}'+ "\n";
    }
}
