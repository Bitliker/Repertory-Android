package com.bitliker.controller.bitnetwork.response;


/**
 * Created by Bitliker on 2017/7/18.
 */

public interface OnHttpCallback{
    void onSuccess(int what, String message, Tags tags);

    void onFailure(int what, String message, Tags tags);
}
