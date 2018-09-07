package com.bitliker.core.bitutils.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 时间：2017/10/30 17:18
 * 功能介绍：viewpager 懒加载
 */
public abstract class ViewPagerLazyFragment extends BaseFragment {

    private boolean isVisible = false;//当前Fragment是否可见
    private boolean isInitView = false;//是否与View建立起映射关系
    private boolean isFirstLoad = true;//是否是第一次加载数据

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView != null) {
            if (rootView.getParent() != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            }
        }
        isInitView = true;
        lazyLoadData();
        return rootView;
    }

    protected View findViewById(int id) {
        if (rootView == null) {
            return null;
        } else {
            return rootView.findViewById(id);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void lazyLoadData() {
        if (!isFirstLoad || !isVisible || !isInitView) {
            createView(null);
        } else {
            LazyData();
            isFirstLoad = false;
        }
    }


    /**
     * 加载要显示的数据
     */
    protected abstract void LazyData();



    @Override
    protected void createView(Bundle savedInstanceState) {

    }


}
