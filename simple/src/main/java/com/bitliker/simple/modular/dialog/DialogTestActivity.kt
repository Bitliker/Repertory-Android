package com.bitliker.simple.modular.dialog

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.bitliker.controller.bitjson.JSONUtil
import com.bitliker.simple.R
import com.bitliker.simple.common.BaseActivity
import com.bitliker.ui.bitdialog.BitDialog
import com.bitliker.ui.bitdialog.common.listener.InputWidgetListener
import com.bitliker.ui.bitdialog.common.listener.OnMultiSelectListener
import com.bitliker.ui.bitdialog.common.listener.OnSelectListener
import com.bitliker.ui.bitdialog.common.listener.PromptWidgetListener
import com.bitliker.ui.bitdialog.common.paramer.WidgetParameter
import com.bitliker.ui.bitdialog.list.BitDialogModel

class DialogTestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_test)
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.promptBtn -> {
                BitDialog.createPrompt(this)
                        .showPositiveAble(true)
                        .setContent(WidgetParameter()
                                .setText("这个是内容")
                                .setWidgetListener(PromptWidgetListener {
                                    Log.i("gong", "点击内容！！")
                                    true
                                })
                                .setTextSize(10)
                                .setTextColorResId(R.color.testColor))


                        .setPositive(WidgetParameter("去顶")
                                .setTextSize(TypedValue.COMPLEX_UNIT_PX, 20)
                                .setWidgetListener(PromptWidgetListener {
                                    Toast.makeText(ct, "点击了确定！！", Toast.LENGTH_SHORT).show()
                                    true
                                }))
                        .setTitle(WidgetParameter("这个是标题")
                                .setTextColor(Color.GREEN)
                                .setTextSize(20)
                                .setWidgetListener(PromptWidgetListener {
                                    Log.i("gong", "点击标题！！")
                                    false
                                }))
                        .show()


            }
            R.id.inputBtn -> {
                BitDialog.createInput(this)
                        .showPositiveAble(true)
                        .setContent(WidgetParameter()
                                .setHint("输入内容")
                                .setWidgetListener(PromptWidgetListener {
                                    Log.i("gong", "点击内容！！")
                                    true
                                })
                                .setTextSize(10)
                                .setTextColorResId(R.color.testColor))
                        .setPositive(WidgetParameter("确定的")
                                .setTextSize(TypedValue.COMPLEX_UNIT_PX, 20)
                                .setWidgetListener(InputWidgetListener { _: View, ed: EditText ->
                                    Toast.makeText(ct, ed.text.toString(), Toast.LENGTH_SHORT).show()
                                    false
                                }))
                        .setNegative(WidgetParameter("取消的")
                                .setTextSize(TypedValue.COMPLEX_UNIT_PX, 30)
                                .setWidgetListener(InputWidgetListener { _: View, ed: EditText ->
                                    ed.setText("")
                                    true
                                }))
                        .setTitle(WidgetParameter("这个是标题")
                                .setTextColor(Color.GREEN)
                                .setTextSize(20)
                                .setWidgetListener(PromptWidgetListener {
                                    Log.i("gong", "点击标题！！")
                                    false
                                }))
                        .show()
            }
            R.id.listBtn -> {
                var models = ArrayList<BitDialogModel>()
                for (i in 1..20) {
                    models.add(BitDialogModel("这个是$i"))
                }

                BitDialog.createList(this)
                        .setPositive(WidgetParameter("确定的")
                                .setTextSize(TypedValue.COMPLEX_UNIT_PX, 20)
                                .setWidgetListener(InputWidgetListener { _: View, ed: EditText ->
                                    Toast.makeText(ct, ed.text.toString(), Toast.LENGTH_SHORT).show()
                                    false
                                }))
                        .setNegative(WidgetParameter("取消的")
                                .setTextSize(TypedValue.COMPLEX_UNIT_PX, 30)
                                .setWidgetListener(InputWidgetListener { _: View, ed: EditText ->
                                    ed.setText("")
                                    true
                                }))
                        .setTitle(WidgetParameter("这个是标题")
                                .setTextColor(Color.GREEN)
                                .setTextSize(20)
                                .setWidgetListener(PromptWidgetListener {
                                    Log.i("gong", "点击标题！！")
                                    false
                                }))
                        .show(models, OnMultiSelectListener { sure, selectModels ->
                                Toast.makeText(ct,"点击了这个=${JSONUtil.toJSONString(selectModels)}",Toast.LENGTH_SHORT).show()
                                false
                        })
            }
        }
    }
}
