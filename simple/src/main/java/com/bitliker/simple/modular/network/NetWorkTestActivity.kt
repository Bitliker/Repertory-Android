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
            HttpClient.post("http://218.18.115.198:8888/ERP/mobile/oa/workdata.action")
                    .addParam("date", "20181119")
                    .addParam("emcode","U0736")
                    .addParam("pageSize", 1000)
                    .addParam("page", 1)
                    .addParam("caller", "CardLog")
                    .addParam("condition", "cl_emcode='" + "U0736"  + "'  and to_char(cl_time,'yyyy-MM-dd')='2018-11-19'")
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