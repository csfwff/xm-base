package com.xiamo.xmbase.utils;


import java.util.Locale;

public class StringFormat {
    public static String formatString(String s){
        double d = Double.parseDouble(s);
        return formatString(d);
    }

    public static String formatString(double d){
        String s1 = String.format(Locale.CHINA,"%.6f",d);
//        if (s1.endsWith(".000")) {
//            s1 = s1.substring(0, s1.length() - 4);
//        }
//        if (s1.endsWith(".00")) {
//            s1 = s1.substring(0, s1.length() - 3);
//        }
//        if (s1.endsWith(".0")) {
//            s1 = s1.substring(0, s1.length() - 2);
//        }
//        while (s1.endsWith(".")||s1.endsWith("0")){
//            s1 = s1.substring(0, s1.length() - 1);
//        }
        if(s1.indexOf(".") > 0){
            s1 = s1.replaceAll("0+?$", "");//去掉多余的0
            s1 = s1.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return  s1;
    }
}