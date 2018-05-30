package com.http.libs.cai.httplibrary.http;

import android.util.Log;

import com.http.libs.cai.httplibrary.http.listener.HttpResult;
import com.http.libs.cai.httplibrary.http.utils.HttpBuild;
import com.http.libs.cai.httplibrary.http.utils.HttpUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * 描述：
 *
 * @author cmy
 * @e-mail 1020233514@qq.com
 * @time 2018/5/30
 */
public class BaseHttp {
public static void http(final String url, final HttpBuild build, final HashMap<String, String> paramsMap, final HttpResult resultListener){
    new Thread(new Runnable(){
        @Override
        public void run() {
             HttpUtils httpUtils = new HttpUtils();
             httpUtils.request(url,build,paramsMap,resultListener);
        }
        }).start();
    }

}
