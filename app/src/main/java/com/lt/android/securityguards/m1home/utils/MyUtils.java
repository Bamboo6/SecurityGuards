package com.lt.android.securityguards.m1home.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by lt on 2017/10/20.
 */

public class MyUtils {
    public static String getVersion(Context context){
        //PackageManager可以获取清单文件中的所有信息
        PackageManager packageManager = context.getPackageManager();
        try{
            //getPackageInfo获取当前程序的包名
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
