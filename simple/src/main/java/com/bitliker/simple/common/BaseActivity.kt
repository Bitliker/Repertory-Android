package com.bitliker.simple.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open class BaseActivity:AppCompatActivity() {
     var ct: AppCompatActivity?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ct=this
    }
}