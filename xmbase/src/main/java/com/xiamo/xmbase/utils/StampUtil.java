package com.xiamo.xmbase.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StampUtil {

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime()/1000;
        res = String.valueOf(ts);
        return res;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(s==null||s.equals("")){
            s="0";
        }
        long lt =Long.parseLong(s)*1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间戳转换为时间 不带秒数
     */
    public static String stampToDateNoSec(String s){

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt =Long.parseLong(s)*1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static Long countStamp(String s){
        //Long a = Long.parseLong(s)*1000+7*24*3600*1000;
        Long a = Long.parseLong(s)*1000;
        Long b = a - System.currentTimeMillis();

        return  b;
    }

    public static Long countStampTenMin(String s){
        //Long a = Long.parseLong(s)*1000+7*24*3600*1000;
        Long a = Long.parseLong(s)*1000+1000*10*60;
        Long b = a - System.currentTimeMillis();

        return  b;
    }


    public static boolean isBegin(String s){
        Long a = Long.parseLong(s)*1000;
        Long b = System.currentTimeMillis();
        return a>b;
    }


    /*
     * 将时间戳转换为时间 不带秒数
     */
    public static String stampToDateNoTime(String s){

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt =Long.parseLong(s)*1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    //返回时间戳
    public static String dateNoTimeToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime()/1000;
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间 不带年
     */
    public static String stampToDateNoYear(String s){

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        long lt =Long.parseLong(s)*1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToTime(String s){

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        long lt =Long.parseLong(s)*1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}