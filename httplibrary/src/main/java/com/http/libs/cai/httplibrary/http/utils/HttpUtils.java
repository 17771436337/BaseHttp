package com.http.libs.cai.httplibrary.http.utils;

import android.util.Log;

import com.http.libs.cai.httplibrary.http.listener.HttpResult;

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
public class HttpUtils {

    public void request(String baseUrl,HttpBuild build,HashMap<String, String> paramsMap,HttpResult resultListener) {
        try {
            //合成参数
            StringBuilder tempParams = new StringBuilder();
            byte[] postData = null;
            int pos = 0;
            if (paramsMap != null) {
                for (String key : paramsMap.keySet()) {
                    if (pos > 0) {
                        tempParams.append("&");
                    }
                    tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                    pos++;
                }
                String params =tempParams.toString();
                // 请求的参数转换为byte数组
                postData = params.getBytes();
        }

            if (build == null){
                build = new HttpBuild();
            }

            // 新建一个URL对象
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(build.getConnectTimeout());
            //设置从主机读取数据超时
            urlConn.setReadTimeout(build.getReadTimeout());
            // 设置为Post请求
            urlConn.setRequestMethod(build.getMethod().name());
            //设置请求允许输入 默认是true
            urlConn.setDoInput(build.isDoinput());
            switch (build.getMethod()){
                case GET:
                    // Post请求不能使用缓存
                    urlConn.setUseCaches(build.isUseCaches());
                    // Post请求必须设置允许输出 默认false
                    urlConn.setDoOutput(build.isDooutput());
                    break;
                case POST:
                    // Post请求必须设置允许输出 默认false
                    urlConn.setDoOutput(true);
                    break;
                    default:
                        // Post请求不能使用缓存
                        urlConn.setUseCaches(build.isUseCaches());
                        // Post请求必须设置允许输出 默认false
                        urlConn.setDoOutput(build.isDooutput());
                        break;
            }
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(build.isFollowRedirects());
            // 配置请求Content-Type
            urlConn.setRequestProperty(build.getHeader().key,build.getHeader().value);

            if (build.getmRequestProperty() != null && build.getmRequestProperty().size() > 0){
                for (HttpBuild.RequestProperty bean : build.getmRequestProperty() ){
                    urlConn.addRequestProperty(bean.key,bean.value);
                }
            }


            // 开始连接
            urlConn.connect();
            switch (build.getMethod()){
                case GET:

                    break;
                case POST:
                    // 发送请求参数
                    DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
                    if (postData != null) {
                        dos.write(postData);
                    }
                    dos.flush();
                    dos.close();
                    break;
                default:
                    dos = new DataOutputStream(urlConn.getOutputStream());
                    if (postData != null) {
                        dos.write(postData);
                    }
                    dos.flush();
                    dos.close();
                    break;
            }

            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());

                resultListener.onSuccess(result);
                HttpLog.e(HttpLog.LOG_TAG, build.getMethod().name()+"方式请求成功，result--->" + result);
            } else {
                HttpLog.e(HttpLog.LOG_TAG,build.getMethod().name()+"方式请求失败");
                resultListener.onFailure(urlConn.getResponseCode());
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            HttpLog.e(HttpLog.LOG_TAG,e.toString());
        }
    }


    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return
     */
    private String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            HttpLog.e(HttpLog.LOG_TAG, e.toString());
            return null;
        }
    }
}
