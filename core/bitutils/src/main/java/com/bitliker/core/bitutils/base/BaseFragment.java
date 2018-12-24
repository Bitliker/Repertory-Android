package com.bitliker.core.bitutils.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitliker.core.bitutils.widget.ProgressView;
import com.bitliker.core.bitutils.widget.SimpleProgressView;
import com.bitliker.core.bitutils.widget.WaitDialog;


/**
 * 基础Fragment，提供较便携的功能和封装
 * Created by gongpm on 2016/6/6.
 * updata by gongpengming on 2016/6/09
 */
public abstract class BaseFragment extends Fragment {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    public View rootView;
    protected Activity ct;
    private boolean mInited;
    private WaitDialog mWaitDialog;

    /*防止内存重启*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        setHasOptionsMenu(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isHidden()) {
            mInited = true;
            createView(savedInstanceState);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!mInited && !hidden) {
            mInited = true;
            createView(null);
        }
    }

    /*防止内存重启出现重叠*/
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null)
            outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView != null) {//有缓存,不创建
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        } else {
            int layoutId = inflater();
            if (layoutId > 0) {
                rootView = inflater.inflate(layoutId, container, false);
            }
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ct = (Activity) context;
    }

    protected <T extends View> T findViewById(int id) {
        if (rootView == null) {
            return null;
        } else {
            return rootView.findViewById(id);
        }
    }


    public final void showProgress() {
        showProgress(true, "", null);
    }

    public final void showProgress(CharSequence content) {
        showProgress(true, content, null);
    }

    public final void showProgress(boolean cancelable) {
        showProgress(cancelable, "", null);
    }


    public final void showProgress(boolean cancelable, CharSequence content, DialogInterface.OnDismissListener mDismissListener) {
        if (mWaitDialog == null && ct != null && !ct.isFinishing()) {
            mWaitDialog = new WaitDialog(ct) {
                @Override
                protected ProgressView getProgressView() {
                    return BaseFragment.this.getProgressView();
                }
            };
        }
        mWaitDialog.setOnDismissListener(mDismissListener);
        mWaitDialog.show(cancelable, content);
    }

    public final void dismissProgress() {
        if (mWaitDialog != null && mWaitDialog.isShowing()) {
            mWaitDialog.dismiss();
        }
    }


    public ProgressView getProgressView() {
        return new SimpleProgressView(ct);
    }


    protected abstract int inflater();

    protected abstract void createView(Bundle savedInstanceState);

}
