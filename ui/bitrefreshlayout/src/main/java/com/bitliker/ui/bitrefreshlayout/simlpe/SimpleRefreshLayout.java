package com.bitliker.ui.bitrefreshlayout.simlpe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.bitliker.ui.bitrefreshlayout.BaseRefreshLayout;
import com.bitliker.ui.bitrefreshlayout.BaseRefreshView;


/**
 * Created by Bitliker on 2017/9/18.
 */

public class SimpleRefreshLayout extends BaseRefreshLayout {

    public SimpleRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseRefreshView getHeader(LayoutInflater mInflater) {
        return new SimpleRefreshHeader(getContext(),mInflater);
    }

    @Override
    protected BaseRefreshView getFooter(LayoutInflater mInflater) {
        return new SimpleRefreshFooter(getContext(),mInflater);
    }
}
