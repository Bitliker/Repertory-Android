package com.bitliker.simple.config

import com.bitliker.controller.bitnetwork.HttpClient
import com.bitliker.controller.bitnetwork.request.HttpRequest
import com.bitliker.controller.bitnetwork.request.OkHttpRequest
import com.bitliker.core.bitutils.base.BaseApplication

class SimpleApplication:BaseApplication() {
    override fun initConfig() {
        HttpClient.init(HttpRequest.Builder()
                .baseUrl("http://192.168.253.195:8080/api/")
                .builder())

    }

    override fun initConfigThread() {
          }
}