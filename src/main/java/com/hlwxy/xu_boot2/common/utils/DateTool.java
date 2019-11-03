package com.hlwxy.xu_boot2.common.utils;

import java.util.Calendar;
import java.util.Locale;

public class DateTool {
    Calendar date = Calendar.getInstance();
    //获取当前年份
    public String year(){
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }

    //获取下一年份
    public String yearadd(){
        String yearadd = String.valueOf(date.get(Calendar.YEAR)+1);
        return yearadd;
    }

    //获取当前月份
    public String month(){
        Integer month = date.get(Calendar.MONTH) + 1;
        String s;
        if (month<10){
            s="0"+month;
        }else {
            s= String.valueOf(month);
        }
        return s;
    }

    //获取下一月份
    public String monthadd(){
        Integer monthadd = date.get(Calendar.MONTH) + 1 + 1;
        String s;
        if (monthadd<10){
            s="0"+monthadd;
        }else {
            s= String.valueOf(monthadd);
        }
        return s;
    }

    //获取当天是本月得第几周
    public Integer weeks(){
        Integer week = date.get(Calendar.WEEK_OF_MONTH);
        return week;
    }
    //获取当天的下周是本月的第几周
    public Integer weeksadd(){
        Integer week = date.get(Calendar.WEEK_OF_MONTH)+1;
        return week;
    }

    /**
     * 如果本周大于四，则该计划的时间为
     */


    //获取当天是本周的第几天
    public Integer week(){
        // 当前是星期几 java中一周第一天为星期天，所以1代表星期日，2代表星期一，以此类推，7代表星期6
        Integer week = date.get(Calendar.DAY_OF_WEEK)+1;
        return week;
    }

    //获取当天是本月的第几天
    public Integer ady(){
        Integer day=date.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    //获取当天是本月的明天
    public Integer adyadd(){
        Integer day=date.get(Calendar.DAY_OF_MONTH)+1;
        return day;
    }

    //获取当前月的天数
    public Integer getDayOfMonth(){
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day=aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    //获取当前年月
    public String years(){
        String years =year()+"年"+month()+"月";
        return years;
    }
    //获取下月
    public String yearsAdd(){
        String years =year()+"年"+month()+"月";
        return years;
    }

    //获取当前年月最后一天
    public String yearss(){
        String years =year()+"年"+month()+"月"+getDayOfMonth()+"日";
        return years;
    }

    //获取下一年月
    public String yearsadd(){
        String yearsadd =yearadd()+"年"+monthadd()+"月";
        return yearsadd;
    }

    //获取当前年月周
    public String WeekAndWeek(){
        String yearsadd =year()+"年"+month()+"月"+"第"+weeks()+"周";
        return yearsadd;
    }

    //获取下一年月周
    public String WeekAndWeekAdd(){
        String yearsadd =yearadd()+"年"+monthadd()+"月"+"第"+(weeks()+1)+"周";
        return yearsadd;
    }

    //获取当前年月日
    public String adyAndDay(){
        String yearsadd =year()+"年"+month()+"月"+ady()+"日";
        return yearsadd;
    }

    //获取明天当前年月日
    public String adyAndDayAdd(){
        String yearsadd =yearadd()+"年"+monthadd()+"月"+(ady()+1)+"日";
        return yearsadd;
    }

}
