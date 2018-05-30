package com.http.libs.cai.httplibrary.http.utils;

import java.util.ArrayList;

/**
 * 描述：
 *
 * @author cmy
 * @e-mail 1020233514@qq.com
 * @time 2018/5/30
 */
public class HttpBuild {
    /**连接主机超时时间*/
    private int connectTimeout = 5 * 1000;
    /**从主机读取数据超时*/
    private int readTimeout = 5 * 1000;
    /**是否使用缓存(POST设置无效)*/
    private boolean useCaches = true;
    /**请求模式*/
    private   HttpTypeMode method;
    /**Post请求必须设置允许输出 默认false*/
    private boolean dooutput = false;
    /**请求允许输入 默认是true*/
    private boolean doinput = true;
    /**设置客户端与服务连接类型*/
    private ArrayList<RequestProperty> mRequestProperty;
    /**设置请求中的媒体类型信息《请求头《"Content-Type", "application/json"》》*/
    private RequestProperty Header;
    /**本次连接是否自动处理重定向*/
    private boolean followRedirects = true;

    public boolean isFollowRedirects() {
        return followRedirects;
    }

    public boolean isDooutput() {
        return dooutput;
    }

    public boolean isDoinput() {
        return doinput;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public boolean isUseCaches() {
        return useCaches;
    }

    public HttpTypeMode getMethod() {
        return method;
    }

    public ArrayList<RequestProperty> getmRequestProperty() {
        return mRequestProperty;
    }

    public RequestProperty getHeader() {
        return Header;
    }


    public HttpBuild() {
        if (Header == null){
            Header = new RequestProperty("Content-Type", "application/json");
        }
        method = HttpTypeMode.GET;
    }

    public void Build(){
    }

    /**设置连接主机超时时间*/
    public HttpBuild setConnectTimeout(int connectTimeout){
        this.connectTimeout = connectTimeout;
        return this;
    }

    /**
     * 设置从主机读取数据超时
     * @param readTimeout
     * @return
     */
    public HttpBuild setReadTimeout(int readTimeout){
        this.readTimeout = readTimeout;
        return this;
    }

    /**
     * Post请求必须设置允许输出 默认false
     * @param dooutput
     * @return
     */
    public HttpBuild setDoOutput(boolean dooutput){
        this.dooutput = dooutput;
        return this;
    }

    /**
     * 设置请求允许输入 默认是true
     * @param doinput
     * @return
     */
    public HttpBuild setDoInput(boolean doinput){
        this.doinput = doinput;
        return this;
    }

    /**
     *  设置是否使用缓存  默认是true
     * @param useCaches
     * @return
     */
    public HttpBuild setUseCaches(boolean useCaches){
        this.useCaches = useCaches;
        return this;
    }


    /**设置请求模式*/
    public HttpBuild setRequestMethod(HttpTypeMode method){
        this.method = method;
        return this;
    }

    /**设置请求中的媒体类型信息。*/
    public HttpBuild setRequestProperty(String key, String value){
        Header = new RequestProperty(key,value);

        return this;
    }

    /**设置客户端与服务连接类型*/
    public HttpBuild addRequestProperty(String key, String value){
        if (mRequestProperty == null ){
            mRequestProperty = new ArrayList<>();
        }
        RequestProperty bean = new RequestProperty(key,value);
        mRequestProperty.add(bean);
        return this;
    }

    /**
     * 设置本次连接是否自动处理重定向
     * @param followRedirects
     * @return
     */
    public HttpBuild setInstanceFollowRedirects(boolean followRedirects){
        this.followRedirects = followRedirects;
        return this;
    }


    /***置请求中的媒体类型信息的实体类**/
    public static class RequestProperty{
        public String key;
        public String value;

        public RequestProperty(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
