package com.bitliker.simple.modular.fragment

import com.bitliker.simple.R
import com.bitliker.simple.common.BaseActivity

class TestFragmentActivity : BaseActivity() {


    override fun initLayout(): Int {
        return R.layout.activity_test_fragment

    }

    override fun init() {
        val mAddFragment = TestFragment()
        val mTransaction = supportFragmentManager.beginTransaction()
        if (!mAddFragment.isAdded) {
            mTransaction.add(R.id.contantFl, mAddFragment, "TAG")
        } else {
            mTransaction.show(mAddFragment)
        }
        mTransaction.commitAllowingStateLoss()
    }

}