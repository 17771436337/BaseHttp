package com.http.libs.cai.httplibrary.http.utils;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * 描述：
 *
 * @author cmy
 * @e-mail 1020233514@qq.com
 * @time 2018/5/30
 */
public class HttpLog {
    public static final String LOG_TAG = "cai_log";
    public static boolean DeBug = true;

    public static void e(String msg){
        if (DeBug) {
            Log.e(LOG_TAG, msg);
        }
    }

    public static void e(String tag,String msg){
        if (DeBug) {
            Log.e(tag, msg);
        }
    }

}
