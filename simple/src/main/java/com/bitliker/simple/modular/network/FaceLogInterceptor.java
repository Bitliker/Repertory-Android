package com.bitliker.simple.modular.network;

import android.util.Log;

import com.bitliker.controller.bitnetwork.interceptor.LogInterceptor;

public class FaceLogInterceptor extends LogInterceptor {
    @Override
    public boolean showLogAble() {
        return true;
    }

    @Override
    public boolean result(String url, String message) {
        Log.i("gong", url + ":\n" + message);
        return false;
    }

    @Override
    public void log(String logMsg) {
        Log.i("gong", "logMsg=" + logMsg);
    }
}
