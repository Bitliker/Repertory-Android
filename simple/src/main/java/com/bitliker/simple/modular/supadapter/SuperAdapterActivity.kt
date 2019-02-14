package com.bitliker.simple.modular.supadapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.bitliker.core.bitutils.util.LogUtil
import com.bitliker.simple.R
import com.bitliker.simple.common.BaseActivity
import com.bitliker.simple.common.model.CommonModel
import com.bitliker.simple.common.utils.RecyclerDecoration
import com.bitliker.ui.bitdialog.BitDialog
import com.bitliker.ui.bitdialog.common.listener.OnSelectListener
import com.bitliker.ui.bitrectclerviewutils.listener.OnRecyclerItemClickListener
import com.gxut.ui.superadapter.SingleAdapter
import com.gxut.ui.superadapter.SuperViewHolder
import kotlinx.android.synthetic.main.activity_super_adapter.*
import kotlinx.android.synthetic.main.fragment_test.*

class SuperAdapterActivity : BaseActivity() {


    override fun initLayout(): Int {
        return R.layout.activity_super_adapter

    }

    override fun init() {
        mRecyclerView.layoutManager = GridLayoutManager(ct, 3) as RecyclerView.LayoutManager?
        mRecyclerView.addItemDecoration(RecyclerDecoration(ct))

        var mAdapter = object : SingleAdapter<CommonModel>(ct, R.layout.item_common_list) {
            override fun bindData(holder: SuperViewHolder?, item: CommonModel?) {
                var nameTv = holder!!.getView<TextView>(R.id.name)
                nameTv!!.setText(item!!.name)

            }

        }
        mAdapter.setData(testData())
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addOnItemTouchListener(object : OnRecyclerItemClickListener(ct) {
            override fun onItemTouch(rv: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, position: Int) {

                val datas = ArrayList<CommonModel>()
                datas.add(CommonModel().setName("删除"))
                datas.add(CommonModel().setName("添加"))
                datas.add(CommonModel().setName("修改"))


                BitDialog.createList(ct)
                        .show(datas, OnSelectListener<CommonModel> { sure, selectModel ->
                            when (selectModel.name) {
                                "删除" -> {
                                    mAdapter.removeData(position)
                                }
                                "添加" -> {
                                    mAdapter.addData(position, testData(), true)
                                }
                                "修改" -> {
                                    mAdapter.updateData(position, CommonModel().setName("修改过的"), true)
                                }
                                else -> {
                                }
                            }

                            false
                        })

            }

        })
    }

    private fun testData(): List<CommonModel> {
        var models = ArrayList<CommonModel>()

        for (i in 1..20) {
            models.add(CommonModel().setName("name:$i"))
        }
        return models
    }
}