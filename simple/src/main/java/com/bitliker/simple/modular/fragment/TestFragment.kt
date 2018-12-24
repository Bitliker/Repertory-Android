package com.bitliker.simple.modular.fragment

import android.os.Bundle
import android.view.View
import com.bitliker.core.bitutils.base.BaseFragment
import com.bitliker.simple.R
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : BaseFragment() ,View.OnClickListener{

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.show -> {
                showProgress()
            }
            R.id.hide -> {
                dismissProgress()
            }

        }

    }
    override fun inflater(): Int {
        return R.layout.fragment_test
    }

    override fun createView(savedInstanceState: Bundle?) {
        show.setOnClickListener(this)
        hide.setOnClickListener(this)
    }


}