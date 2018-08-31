package com.bitliker.simple.modular.dialog

import android.os.Bundle
import android.view.View
import com.bitliker.simple.R
import com.bitliker.simple.common.BaseActivity
import com.bitliker.ui.bitdialog.BitDialog

class DialogTestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_test)
    }
    fun onClick(v: View){
        when(v.id){
            R.id.promptBtn->{
                BitDialog.createPrompt(this)
                        .setContent("这个是内容")
                        .setTitle("这个是标题")
                        .show()


            }
        }
    }
}
