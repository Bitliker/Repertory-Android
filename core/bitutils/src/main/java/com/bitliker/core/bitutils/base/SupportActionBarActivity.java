package com.bitliker.core.bitutils.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.bitliker.core.bitutils.base.swipebacklayout.SwipeBackActivity;
import com.bitliker.core.bitutils.base.swipebacklayout.layout.SwipeBackLayout;
import com.bitliker.core.bitutils.widget.ProgressView;
import com.bitliker.core.bitutils.widget.SimpleProgressView;
import com.bitliker.core.bitutils.widget.WaitDialog;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Bitlike on 2018/4/16.
 */

public abstract class SupportActionBarActivity extends SwipeBackActivity {
    public SupportActionBarActivity ct;
    private WaitDialog mWaitDialog;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ct = this;
        preCreate();
        super.onCreate(savedInstanceState);
        View view = initLayoutView();
        if (view != null) {
            setContentView(view);
        } else {
            int layoutId = initLayout();
            if (layoutId > 0) {
                setContentView(layoutId);
            }
        }
        initActionBar();
        initSwipeBackLayout();
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void initSwipeBackLayout() {
        int mode = getSwipeMode();
        if (mode == SwipeBackLayout.EDGE_NULL) {
            getSwipeBackLayout().setEnableGesture(false);
        } else {
            getSwipeBackLayout().setEdgeTrackingEnabled(mode);

        }
        getSwipeBackLayout().setScreenScllor(getSwipeScreen());
    }

    private void initActionBar() {
        if (needNavigation()) {
            ActionBar mActionBar = getSupportActionBar();
            if (mActionBar != null) {
                mActionBar.setHomeButtonEnabled(true);
                mActionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    /*设置标题*/
    public final void setTitle(CharSequence title) {
        if (title != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    //隐藏actionbar
    protected void hintActionBar() {
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null && mActionBar.isShowing()) {
            mActionBar.hide();
        }
    }

    public void showActionBar() {
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null && !mActionBar.isShowing()) {
            mActionBar.show();
        }
    }

    public @Duration
    int getSwipeMode() {
        return SwipeBackLayout.EDGE_LEFT;
    }


    public void preCreate() {

    }

    public boolean needNavigation() {
        return true;
    }


    public View initLayoutView() {
        return null;
    }


    public boolean getSwipeScreen() {
        return false;
    }

    public final void showProgress() {
        showProgress(true, "", null);
    }

    public final void showProgress(CharSequence content) {
        showProgress(true, content, null);
    }


    public final void dismissProgress() {
        if (mWaitDialog != null && mWaitDialog.isShowing()) {
            mWaitDialog.dismiss();
        }
    }

    public final void showProgress(boolean cancelable, CharSequence content, DialogInterface.OnDismissListener mDismissListener) {
        if (mWaitDialog == null && ct != null && !ct.isFinishing()) {
            mWaitDialog = new WaitDialog(ct) {
                @Override
                protected ProgressView getProgressView() {
                    return SupportActionBarActivity.this.getProgressView();
                }
            };
        }
        mWaitDialog.setOnDismissListener(mDismissListener);
        mWaitDialog.show(cancelable, content);
    }


    public ProgressView getProgressView() {
        return new SimpleProgressView(this);
    }

    public abstract int initLayout();

    public abstract void init();

    @IntDef({SwipeBackLayout.EDGE_LEFT, SwipeBackLayout.EDGE_ALL,
            SwipeBackLayout.EDGE_BOTTOM, SwipeBackLayout.EDGE_RIGHT,SwipeBackLayout.EDGE_NULL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }
}
