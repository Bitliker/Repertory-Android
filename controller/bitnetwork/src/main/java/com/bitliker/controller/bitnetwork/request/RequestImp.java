package com.bitliker.controller.bitnetwork.request;


import com.bitliker.controller.bitnetwork.HttpClient;
import com.bitliker.controller.bitnetwork.response.OnDownloadListener;
import com.bitliker.controller.bitnetwork.response.OnHttpCallback;

/**
 * Created by Bitliker on 2017/7/18.
 */

public interface RequestImp {

    void request(HttpClient.Builder httpBuilder, OnHttpCallback onHttpCallback);

    void download(HttpClient.DownloadBuilder httpBuilder, OnDownloadListener OnDownloadListener);

    void removeRequest(int record);

}
