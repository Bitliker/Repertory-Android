package com.bitliker.simple

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bitliker.simple.common.BaseActivity
import com.bitliker.simple.common.CommonSelectAdapter
import com.bitliker.simple.common.model.CommonModel
import com.bitliker.simple.common.utils.OnRecyclerClickLister
import com.bitliker.simple.modular.dialog.DialogTestActivity
import com.bitliker.simple.modular.fragment.TestFragmentActivity
import com.bitliker.simple.modular.network.NetWorkTestActivity
import com.bitliker.simple.modular.recyclerview.RecyclerTestActivity
import com.bitliker.simple.modular.scheduler.SchedulerTestActivity
import com.bitliker.simple.modular.supadapter.SuperAdapterActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var mCommonSelectAdapter: CommonSelectAdapter? = null

    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        initView()
    }


    private fun initView() {
        mRecyclerView.layoutManager = LinearLayoutManager(ct)
        mRecyclerView.addOnItemTouchListener(object : OnRecyclerClickLister(mRecyclerView) {
            override fun onItemLongClick(vh: RecyclerView.ViewHolder?) {

            }

            override fun onItemClick(vh: RecyclerView.ViewHolder?) {
                var model = mCommonSelectAdapter!!.models!![vh!!.layoutPosition]
                startActivity(Intent(ct, model.cazz))
            }

        })
        mCommonSelectAdapter = CommonSelectAdapter(ct!!, getModel())

        mRecyclerView.adapter = mCommonSelectAdapter
    }

    private fun getModel(): List<CommonModel> {



        var models = ArrayList<CommonModel>()
        var model: CommonModel? = null
        model = CommonModel()
        model.name = "RecyclerView"
        model.cazz = RecyclerTestActivity::class.java
        models.add(model)
        model = CommonModel()
        model.name = "SchedulerTestActivity"
        model.cazz = SchedulerTestActivity::class.java
        models.add(model)
        model = CommonModel()
        model.name = "Dialog"
        model.cazz = DialogTestActivity::class.java
        models.add(model)
        model = CommonModel()
        model.name = "NetWork"
        model.cazz = NetWorkTestActivity::class.java
        models.add(model)
        model = CommonModel()
        model.name = "fragment"
        model.cazz = TestFragmentActivity::class.java
        model = CommonModel()
        model.name = "superAdapter"
        model.cazz = SuperAdapterActivity::class.java
        models.add(model)
        return models

    }


}
