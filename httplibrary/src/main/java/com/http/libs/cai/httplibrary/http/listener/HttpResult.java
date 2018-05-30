package com.http.libs.cai.httplibrary.http.listener;

/**
 * 描述：
 *
 * @author cmy
 * @e-mail 1020233514@qq.com
 * @time 2018/5/30
 */
public interface HttpResult {

   void onSuccess(String result);
   void onFailure(int code);
}
