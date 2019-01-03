package com.bitliker.simple.common.model

import android.os.Bundle
import com.bitliker.simple.common.BaseActivity
import com.bitliker.ui.bitdialog.list.BitDialogModel
import java.util.*

class CommonModel : BitDialogModel {

    override fun getText(): CharSequence {
        return name!!
    }

    override fun setSelected(isSelect: Boolean) {
        this.isSelected=isSelect
    }

    override fun isSelected(): Boolean {
        return isSelected
    }

   fun setName(name:String?):CommonModel{
        this.name=name
       return this
    }
    var id: Int? = null
    var name: String? = null
    var showName: String? = null
    var sub: String? = null
    var cazz: Class<out BaseActivity>? = null
    var mb: Bundle? = null
    private var isSelected = false


}