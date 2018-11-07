package com.bitliker.simple.modular.network

import com.bitliker.controller.bitnetwork.HttpClient
import com.bitliker.controller.bitnetwork.response.OnHttpCallback
import com.bitliker.controller.bitnetwork.response.Tags
import com.bitliker.simple.R
import com.bitliker.simple.common.BaseActivity
import kotlinx.android.synthetic.main.activity_net_work.*
import java.lang.StringBuilder

class NetWorkTestActivity : BaseActivity() {
    override fun initLayout(): Int {
        return R.layout.activity_net_work
    }

    override fun init() {
        postBtn?.setOnClickListener {
            HttpClient.post("user/register")
                    .addParam("sex", 1)
                    .addParam("email", "gongpengming@162.com")
                    .addParam("phone", "159976542201")
                    .addParam("password", "159976542201")
                    .addParam("userName", "159976542201")
                    .addParam("nickName", "昵称")
                    .addParam("remarks", "备注")
                    .addParam("description", "描述")
                    .addParam("birthday", "2017-11-11")
                    .addParam("address", "没有地址")
                    .addParam("portrait", "")
                    .what(1)
                    .addTag(1000, "这个是什么")
                    .execute(object : OnHttpCallback {
                        override fun onSuccess(what: Int, message: String?, tags: Tags?) {
                            var logs = StringBuilder("onSuccess\n")
                            logs.append("what:$what\n")
                            logs.append("tags:${tags!![1000]}\n")
                            logs.append("message:$message")
                            questTv?.text=logs.toString()
                        }

                        override fun onFailure(what: Int, message: String?, tags: Tags?) {
                            var logs = StringBuilder("onFailure\n")
                            logs.append("what:$what\n")
                            logs.append("tags:${tags!![1000]}\n")
                            logs.append("message:$message")
                            questTv?.text=logs.toString()
                        }
                    })
        }
        getBtn?.setOnClickListener {
            HttpClient.get("user/register")
                    .addParam("name","159976542201")
                    .addParam("password","159976542201")
                    .execute(object : OnHttpCallback {
                override fun onSuccess(what: Int, message: String?, tags: Tags?) {
                    var logs = StringBuilder("onSuccess\n")
                    logs.append("what:$what\n")
                    logs.append("tags:${tags!![1000]}\n")
                    logs.append("message:$message")
                    questTv?.text=logs.toString()
                }

                override fun onFailure(what: Int, message: String?, tags: Tags?) {
                    var logs = StringBuilder("onFailure\n")
                    logs.append("what:$what\n")
                    logs.append("tags:${tags!![1000]}\n")
                    logs.append("message:$message")
                    questTv?.text=logs.toString()
                }
            })
        }
    }
}