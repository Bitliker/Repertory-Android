package com.bitliker.simple.config

import com.bitliker.controller.bitnetwork.HttpClient
import com.bitliker.controller.bitnetwork.request.HttpRequest
import com.bitliker.core.bitutils.base.BaseApplication
import com.bitliker.simple.modular.network.FaceLogInterceptor
import com.bitliker.simple.modular.network.FaceParameterInterceptor

class SimpleApplication : BaseApplication() {
    override fun initConfig() {
        val httpBuilder = HttpRequest.Builder()
                .baseUrl("http://218.18.115.198:8888/ERP/")
                .cacheOutTime(10)
                .connectTimeout(10)
                .readTimeout(10)
                .retryTimeout(10)
                .writeTimeout(10)
        httpBuilder.addInterceptors(FaceParameterInterceptor())
        httpBuilder.addInterceptors(FaceLogInterceptor())
        HttpClient.init(httpBuilder.builder())
    }

    override fun initConfigThread() {
    }
}