package com.http.libs.cai.httplibrary.http.utils;

/**
 * 描述：请求方式
 *
 * @author cmy
 * @e-mail 1020233514@qq.com
 * @time 2018/5/30
 */
public enum HttpTypeMode {
    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    PUT("PUT"),
    DELETE("DELETE"),
    TRACE("TRACE");

    private String str;
    HttpTypeMode(String str) {
        this.str = str;
    }
}
