package com.bitliker.controller.bitnetwork.request;


import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;

import com.bitliker.controller.bitnetwork.HttpClient;
import com.bitliker.controller.bitnetwork.response.OnHttpCallback;
import com.bitliker.controller.bitnetwork.response.Tags;
import com.bitliker.controller.bitnetwork.ssl.DefaultSSLConfig;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by Bitliker on 2017/7/3.
 */

public class OkHttpRequest extends HttpRequest<Call> {
    private OkHttpClient okHttpClient;
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    public OkHttpRequest() {
        super();
    }

    @Override
    protected HttpRequest init() {
        if (sslConfig == null) sslConfig = new DefaultSSLConfig();
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(readTimeout, TimeUnit.SECONDS)// 设置超时时间
                .readTimeout(writeTimeout, TimeUnit.SECONDS)// 设置读取超时时间
                .writeTimeout(connectTimeout, TimeUnit.SECONDS)// 设置写入超时时间
                .retryOnConnectionFailure(maxRetryCount > 0)
                .sslSocketFactory(sslConfig.createSSLSocketFactory(),
                        sslConfig.createTrustAllCerts())
                .hostnameVerifier(sslConfig.createHostnameVerifier());
        if (chcheDirectory != null)
            builder.cache(new Cache(chcheDirectory, chcheMaxSize));
        if (interceptors != null && interceptors.size() > 0) {
            for (Interceptor i : interceptors)
                builder.addInterceptor(i);
        }
        okHttpClient = builder.build();
        return this;
    }

    @Override
    public boolean cancelRequest(int what, SparseArray<Call> calls) {
        if (calls != null) {
            Call call = calls.get(what);
            if (call != null && call.isExecuted()) {
                try {
                    call.cancel();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private void postRequest(HttpClient.Builder mBuilder, final OnHttpCallback callback) throws Exception {
        Request.Builder builder = new Request.Builder();
        builder.url(mergeUrl(mBuilder.getUrl()));
        RequestBody body = getBody(mBuilder);
        if (mBuilder.getMode() == HttpClient.POST_JSON) {
            builder.post(RequestBody.create(MediaType.parse(mBuilder.getMediaType()), bodyToString(body)));
        } else {
            builder.post(body);
        }
        Headers2Builder(builder, mBuilder);
        enqueue(mBuilder, builder, callback);
    }

    private void getRequest(HttpClient.Builder mBuilder, final OnHttpCallback callback) throws Exception {
        StringBuilder urlBuilder = new StringBuilder(mergeUrl(mBuilder.getUrl()));
        if (!mBuilder.getParams().isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, Object> e : mBuilder.getParams().entrySet()) {
                String value = e.getValue().toString();
                try {
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e1) {
                }
                urlBuilder.append(String.format("%s=%s&", e.getKey(), value));
            }
            String urlEnd = urlBuilder.toString();
            if (urlEnd.endsWith("?") || urlEnd.endsWith("&")) {
                urlBuilder.deleteCharAt(urlBuilder.length() - 1);
            }
        }
        Request.Builder builder = new Request.Builder().url(urlBuilder.toString());
        Headers2Builder(builder, mBuilder);
        enqueue(mBuilder, builder, callback);
    }

    private void enqueue(HttpClient.Builder mBuilder, Request.Builder builder, final OnHttpCallback callback) {
        final Tags tags = mBuilder.getTags();
        final int what = tags.getWhat();
        final Call call = okHttpClient.newCall(builder.build());
        ThreadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final Response mResponse = call.execute();
                    final String message = mResponse.body().string();
                    if (mResponse == null || mResponse.code() != 200) {
                        //错误
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    callback.onFailure(what, message, tags);
                                } catch (Exception e) {

                                }
                            }
                        });
                    } else {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    callback.onSuccess(what, message, tags);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (IOException e) {

                }
            }
        });
        addRequest(what, call);
    }

    private RequestBody getBody(HttpClient.Builder httpBuilder) {
        if (httpBuilder.getMode() == HttpClient.POST) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            if (!httpBuilder.getParams().isEmpty()) {
                for (Map.Entry<String, Object> e : httpBuilder.getParams().entrySet()) {
                    if (e.getValue() == null || TextUtils.isEmpty(e.getKey()))
                        continue;
                    bodyBuilder.add(e.getKey(), e.getValue().toString());
                }
            }
            return bodyBuilder.build();
        } else {
            return new FormBody.Builder().build();
        }
    }

    private void Headers2Builder(Request.Builder builder, HttpClient.Builder mBuilder) {
        if (!mBuilder.getHeaders().isEmpty()) {
            for (Map.Entry<String, String> e : mBuilder.getHeaders().entrySet()) {
                if (e.getValue() == null || TextUtils.isEmpty(e.getKey()))
                    continue;
                builder.addHeader(e.getKey(), e.getValue());
            }
        }
        builder.addHeader("Content-Type", mBuilder.getMediaType());
    }

    public static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }


    @Override
    public void request(HttpClient.Builder httpBuilder, OnHttpCallback onHttpCallback) {
        try {
            switch (httpBuilder.getMode()) {
                case HttpClient.POST:
                case HttpClient.POST_JSON:
                    postRequest(httpBuilder, onHttpCallback);
                    break;
                case HttpClient.GET:
                default:
                    getRequest(httpBuilder, onHttpCallback);
            }
        } catch (Exception e) {
            if (onHttpCallback != null) {
                try {
                    onHttpCallback.onFailure(httpBuilder.getTags().getWhat(), e == null ? "" : e.getMessage(), httpBuilder.getTags());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


}
