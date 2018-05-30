package com.http.libs.cai.basehttp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.http.libs.cai.httplibrary.http.BaseHttp;
import com.http.libs.cai.httplibrary.http.listener.HttpResult;
import com.http.libs.cai.httplibrary.http.utils.HttpBuild;
import com.http.libs.cai.httplibrary.http.utils.HttpTypeMode;
import com.http.libs.cai.httplibrary.http.utils.HttpUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                HttpBuild build = new HttpBuild();
                build.setRequestMethod(HttpTypeMode.POST);
                BaseHttp.http("http://wtc.bee-pay.cn/photo/getApi", build, null, new HttpResult() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("cai_log"," 成功之后的毁掉"+result);
                    }

                    @Override
                    public void onFailure(int code) {

                    }
                });
//                build.setRequestMethod(HttpTypeMode.PUT);
//                BaseHttp.http("http://wtc.bee-pay.cn/photo/getApi",build,null);
//                build.setRequestMethod(HttpTypeMode.DELETE);
//                BaseHttp.http("http://wtc.bee-pay.cn/photo/getApi",build,null);
//                build.setRequestMethod(HttpTypeMode.GET);
//                BaseHttp.http("http://wtc.bee-pay.cn/photo/getApi",build,null);
    }
}
