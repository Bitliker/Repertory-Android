package com.bitliker.controller.bitnetwork.request;


import android.util.SparseArray;

import com.bitliker.controller.bitnetwork.cache.CacheMode;
import com.bitliker.controller.bitnetwork.ssl.SSLConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by Bitliker on 2017/7/3.
 */

public abstract class HttpRequest<Call> implements RequestImp {
    /*默认基本配置*/
    public static final long DEF_READ_TIMEOUT = 1000;//设置读取超时时间s
    public static final long DEF_WRITE_TIMEOUT = 1000;//设置写入超时时间s
    public static final long DEF_CONNECT_TIMEOUT = 1000;//设置链接超时时间s
    public static final long DEF_RETRY_TIMEOUT = 5;//重试间隔时间s
    public static final int DEF_MAX_RETRY_COUNT = 1;// 最大重试次数
    /*默认缓存配置*/
    public static final CacheMode DEF_CHCHE_MODE = CacheMode.DEFAULT;//全局缓存模式
    public static final File DEF_CHCHE_DIRECTORY = null;//缓存目录
    public static final long DEF_CHCHE_MAX_SIZE = 2 * 1024 * 1024;//最大缓存大小
    public static final long DEF_CACHE_OUT_TIME = -1;    //全局缓存过期时间,默认永不过期

    /*基本配置*/
    protected long readTimeout = DEF_READ_TIMEOUT;//设置读取超时时间
    protected long writeTimeout = DEF_WRITE_TIMEOUT;//设置写入超时时间
    protected long connectTimeout = DEF_CONNECT_TIMEOUT;//设置链接超时时间
    protected long retryTimeout = DEF_RETRY_TIMEOUT;//重试间隔时间
    protected int maxRetryCount = DEF_MAX_RETRY_COUNT;// 最大重试次数
    /*缓存配置*/
    protected CacheMode cacheMode = DEF_CHCHE_MODE;//全局缓存模式
    protected File chcheDirectory = DEF_CHCHE_DIRECTORY;//缓存目录
    protected long chcheMaxSize = DEF_CHCHE_MAX_SIZE;//最大缓存大小
    protected long cacheOutTime = DEF_CACHE_OUT_TIME;    //全局缓存过期时间,默认永不过期
    //拦截器
    protected List<Interceptor> interceptors;
    //SSL 请求配置
    protected SSLConfig sslConfig;

    protected String baseUrl;

    private SparseArray<Call> calls;

    protected abstract HttpRequest init();


    //判断是否存在基本网址
    public String mergeUrl(String url) {
        if (url != null && !url.startsWith("http") && baseUrl != null && !baseUrl.isEmpty()) {
            return baseUrl + url;
        }
        return url;
    }

    public void addRequest(int what, Call call) {
        if (calls != null) {
            calls.append(what, call);
        }
    }

    public void removeRequest(int what) {
        if (calls != null) {
            calls.remove(what);
        }
    }

    //取消请求
    public void cancelRequest(int what) {
        if (cancelRequest(what, calls)) {
            removeRequest(what);
        }
    }

    public abstract boolean cancelRequest(int what, SparseArray<Call> calls);


    protected HttpRequest() {
        calls = new SparseArray<>();
    }

    public Builder newBuilder() {
        return new Builder(this)
                .readTimeout(readTimeout)
                .writeTimeout(writeTimeout)
                .connectTimeout(connectTimeout)
                .retryTimeout(retryTimeout)
                .maxRetryCount(maxRetryCount)
                .cacheMode(cacheMode)
                .chcheDirectory(chcheDirectory)
                .chcheMaxSize(chcheMaxSize)
                .cacheOutTime(cacheOutTime)
                .interceptors(interceptors)
                .sslConfig(sslConfig);
    }

    public static class Builder {
        private HttpRequest httpRequest;

        public Builder() {
            httpRequest = new OkHttpRequest();

        }

        public Builder(HttpRequest httpRequest) {
            this.httpRequest = httpRequest;
        }


        public Builder baseUrl(String baseUrl) {
            this.httpRequest.baseUrl = baseUrl;
            return this;
        }

        public Builder readTimeout(long readTimeout) {
            httpRequest.readTimeout = readTimeout;
            return this;
        }

        public Builder writeTimeout(long writeTimeout) {
            httpRequest.writeTimeout = writeTimeout;
            return this;
        }

        public Builder connectTimeout(long connectTimeout) {
            httpRequest.connectTimeout = connectTimeout;
            return this;
        }

        public Builder retryTimeout(long retryTimeout) {
            httpRequest.retryTimeout = retryTimeout;
            return this;
        }

        public Builder maxRetryCount(int maxRetryCount) {
            httpRequest.maxRetryCount = maxRetryCount;
            return this;
        }

        public Builder cacheMode(CacheMode cacheMode) {
            httpRequest.cacheMode = cacheMode;
            return this;
        }

        public Builder chcheDirectory(File chcheDirectory) {
            httpRequest.chcheDirectory = chcheDirectory;
            return this;
        }

        public Builder chcheMaxSize(long chcheMaxSize) {
            httpRequest.chcheMaxSize = chcheMaxSize;
            return this;
        }

        public Builder cacheOutTime(long cacheOutTime) {
            httpRequest.cacheOutTime = cacheOutTime;
            return this;
        }


        public Builder interceptors(List<Interceptor> interceptors) {
            httpRequest.interceptors = interceptors;
            return this;
        }


        public Builder addInterceptors(Interceptor interceptors) {
            if (httpRequest.interceptors == null)
                httpRequest.interceptors = new ArrayList<>();
            httpRequest.interceptors.add(interceptors);
            return this;
        }

        public Builder clearInterceptors() {
            if (httpRequest.interceptors != null)
                httpRequest.interceptors.clear();
            return this;
        }

        public Builder sslConfig(SSLConfig sslConfig) {
            httpRequest.sslConfig = sslConfig;
            return this;
        }

        public HttpRequest builder() {
            return httpRequest.init();
        }


    }
}
