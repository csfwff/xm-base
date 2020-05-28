package com.xiamo.xmbase.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class CheckApps {
    /**
     * 判断 用户是否安装某应用
     */
    public static boolean isAppAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAlipayAvilible(Context context) {
        return isAppAvilible(context,"com.eg.android.AlipayGphone");
    }

    public static boolean isWechatAvilible(Context context){
        return isAppAvilible(context,"com.tencent.mm");
    }

    public static boolean isYybAvilible(Context context){
        return isAppAvilible(context,"com.tencent.android.qqdownloader");
    }

}