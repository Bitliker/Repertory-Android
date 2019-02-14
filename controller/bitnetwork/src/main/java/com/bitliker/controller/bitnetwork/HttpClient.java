package com.bitliker.controller.bitnetwork;


import com.bitliker.controller.bitnetwork.request.HttpRequest;
import com.bitliker.controller.bitnetwork.response.OnDownloadListener;
import com.bitliker.controller.bitnetwork.response.OnHttpCallback;
import com.bitliker.controller.bitnetwork.response.Tags;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求的入口
 * 1.HttpClient   全局通用请求头有拦截器提供
 * Created by Bitliker on 2017/7/17.
 */
public class HttpClient {
    public static final int GET = 0x11;
    public static final int POST = 0x12;
    public static final int POST_JSON = 0x13;
    public static final int DOWNLOAD = 0x14;
    public static final int UPLOAD = 0x15;

    public static final String MEDIA_TYPE_XWWW = "application/x-www-form-urlencoded;charset=utf-8";
    public static final String MEDIA_TYPE_JSON = "application/json;charset=utf-8";
    public static final String MEDIA_TYPE_PLAIN = "text/plain;charset=utf-8";
    public static final String MEDIA_TYPE_STREAM = "application/octet-stream;charset=utf-8";
    public static final String MEDIA_TYPE_MULTIPART = "multipart/form-data;charset=utf-8";


    /*************************请求方式开启请求************************/
    public static Builder get(String url) {
        return new Builder(GET, url);
    }

    public static Builder post(String url) {
        return post(false, url);
    }

    public static Builder post(boolean json, String url) {
        return new Builder(json ? POST_JSON : POST, url);
    }

    public DownloadBuilder downLoad(String url) {
        return new DownloadBuilder(url);
    }

    public Builder upload(String url) {
        return new Builder(UPLOAD, url);
    }

    /*******关闭请求***********/
    public static void cancelRequest(int what) {
        if (HttpClient.mHttpRequest != null) {
            HttpClient.mHttpRequest.cancelRequest(what);
        }
    }


    private static HttpRequest mHttpRequest;

    public static void init(HttpRequest mHttpRequest) {
        HttpClient.mHttpRequest = mHttpRequest;
    }


    public static class DownloadBuilder {
        private String url;//请求的url
        private Map<String, String> headers;//头文件
        private Tags tags;//记录单个请求的转递值
        private String filePath;
        private String fileName;

        public String getFilePath() {
            return filePath;
        }

        public String getFileName() {
            return fileName;
        }

        public String getUrl() {
            return url;
        }

        public DownloadBuilder(String url) {
            this.url = url;
        }

        public Map<String, String> getHeaders() {
            return headers == null ? headers = new HashMap<>() : headers;
        }

        public Tags getTags() {
            return tags == null ? tags = new Tags() : tags;
        }

        public DownloadBuilder headers(Map<String, String> header) {
            this.headers = header;
            return this;
        }

        public DownloadBuilder addHeader(String key, String value) {
            this.getHeaders().put(key, value);
            return this;
        }

        public DownloadBuilder addTag(int key, Object values) {
            this.getTags().put(key, values);
            return this;
        }

        public DownloadBuilder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public DownloadBuilder setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public void execute(OnDownloadListener onDownloadListener) {
            if (mHttpRequest != null) {
                mHttpRequest.download(this, onDownloadListener);
            }
        }
    }

    //创建单个请求
    public static class Builder {
        private int mode = GET;//请求模式
        private String url;//请求的url
        private String mediaType = MEDIA_TYPE_JSON;//请求体
        private Map<String, Object> params;//参数
        private Map<String, String> headers;//头文件
        private Tags tags;//记录单个请求的转递值

        public int getMode() {
            return mode;
        }

        public String getUrl() {
            return url;
        }

        public String getMediaType() {
            return mediaType == null ? MEDIA_TYPE_JSON : mediaType;
        }

        public Map<String, Object> getParams() {
            return params == null ? params = new HashMap<>() : params;
        }

        public Map<String, String> getHeaders() {
            return headers == null ? headers = new HashMap<>() : headers;
        }

        public Tags getTags() {
            return tags == null ? tags = new Tags() : tags;
        }

        public Builder(int mode, String url) {
            this.url = url;
            this.mode = mode;

        }

        public Builder mediaType(String mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public Builder headers(Map<String, String> header) {
            this.headers = header;
            return this;
        }

        public Builder addHeader(String key, String value) {
            this.getHeaders().put(key, value);
            return this;
        }

        public Builder params(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public Builder addParam(String key, Object value) {
            this.getParams().put(key, value);
            return this;
        }

        public Builder what(int record) {
            this.getTags().what(record);
            return this;
        }

        public Builder addTag(int key, Object values) {
            this.getTags().put(key, values);
            return this;
        }

        public void execute(OnHttpCallback onHttpCallback) {
            if (mHttpRequest != null) {
                mHttpRequest.request(this, onHttpCallback);
            }
        }
    }


}
