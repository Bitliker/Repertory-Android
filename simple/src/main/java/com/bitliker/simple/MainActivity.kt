package com.bitliker.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bitliker.controller.bitjson.JSONUtils
import com.bitliker.simple.config.TestConstants

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.mButton -> {
               var jsonObject= JSONUtils.parseObject(TestConstants.JSONOBJECT)

                Log.d("gong", "JSONUtils=" + JSONUtils.parseObject(TestConstants.ERROR_JSONOBJECT))

            }

        }
    }
}
