package com.datang.datangcarmanager.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.utils
 * @class describe
 * @anthor toby
 * @time 2016/12/2 上午9:02
 * @change
 * @chang time
 * @class describe
 */
public class StateUtils {

    public static boolean isNetworkAvailable(Context context) {
        if(context !=null){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if(info !=null){
                return info.isAvailable();
            }
        }
        return false;
    }
}
