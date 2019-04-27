package com.example.wangyimusic;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String dateChnage(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        String now_month=str.substring(5,7);
        String date_month=date.substring(5,7);
        Log.d("tag",now_month+"+"+date_month);
        String now_day=str.substring(8,10);
        String date_day=date.substring(8,10);
        if (date_month.equals(now_month)){
            if (Integer.valueOf(now_day)==Integer.valueOf(date_day)){
                return "今天 "+date.substring(11,16);
            }else if (Integer.valueOf(now_day)==Integer.valueOf(date_day)+1){
                return "昨天 "+date.substring(11,16);
            }else if (Integer.valueOf(now_day)==Integer.valueOf(date_day)+2){
                return "前天 "+date.substring(11,16);
            }else {
                return date_month+"月"+date_day+"日 "+date.substring(11,16);
            }
        }else {
            return date_month+"月"+date_day+"日 "+date.substring(11,16);
        }
    }
    public static String nowDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str.substring(0,10);
    }
}
