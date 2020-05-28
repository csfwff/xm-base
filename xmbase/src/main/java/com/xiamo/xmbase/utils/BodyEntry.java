package com.xiamo.xmbase.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BodyEntry {
    private static String TIMESTAMP = "timestamp";
    private static String SIGN = "sign";

    public static Map<String,Object> entry(Map<String,Object> data,String signString){

        String stamp = String.valueOf(System.currentTimeMillis()/1000);
        data.put(TIMESTAMP,stamp);
        // data.put("current_version",String.valueOf(Constant.nowVersion));

        List<Map.Entry<String,Object>> infoIds = new ArrayList<Map.Entry<String,Object>>(data.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return (o1.getKey().compareTo(o2.getKey()));
            }
        });

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<infoIds.size();i++){
//            LogUtils.e(infoIds.get(i).getKey());
            sb.append(infoIds.get(i).getKey()).append(infoIds.get(i).getValue());
        }
        sb.append(signString);
        // LogUtils.e(sb.toString());
        String sign = StringToMD5.stringToMD5(sb.toString());
        data.put(SIGN,sign);

        return data;
    }


    public static String getBody(Map<String,Object>data){
        Iterator iter = data.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String s = sb.toString();
        s = s.substring(0,s.length()-1);
        return s;
    }
}