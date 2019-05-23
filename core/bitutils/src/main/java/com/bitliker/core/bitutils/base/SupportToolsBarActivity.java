package com.bitliker.core.bitutils.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bitliker.core.bitutils.R;
import com.bitliker.core.bitutils.base.swipebacklayout.SwipeBackActivity;
import com.bitliker.core.bitutils.base.swipebacklayout.layout.SwipeBackLayout;
import com.bitliker.core.bitutils.widget.ProgressView;
import com.bitliker.core.bitutils.widget.SimpleProgressView;
import com.bitliker.core.bitutils.widget.WaitDialog;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 通用的Toolbar actitivty界面
 * 开放接口
 * preCreate() : 在super.onCreate 前面的方法，主要用于设置方向等信息
 * needCommonToolBar()： 是否需要通用的初始化toolbar方法
 * getToolBarId():当前界面toolbar id ，仅在needCommonToolBar()为ture时候使用
 * initLayout()：layout布局
 * initRootView(): 当前界面跟布局，当这个为空时候，才使用initLayout的布局
 * getSwipeMode() : 设置滑动退出的模式
 * <p>
 * Created by Bitlike on 2017/12/6.
 */
public abstract class SupportToolsBarActivity extends SwipeBackActivity {
    private static final String KEY_SHOW_WAIT = "show_wait";
    private static final String KEY_CANCELABLE_WAIT = "cancelable_wait";
    private static final String KEY_CONTENT_WAIT = "content_wait";


    protected SupportToolsBarActivity ct;
    private Toolbar commonToolBar;
    private TextView commonTitleTv;
    private FrameLayout contentRl;
    private WaitDialog mWaitDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ct = this;
        preCreate();
        super.onCreate(savedInstanceState);
        //初始化界面跟布局
        super.setContentView(R.layout.bitutils_base_bar_layout);
        contentRl = findViewById(R.id.contentRl);
        //设置当前界面具体内容
        View rootView = initRootView();
        if (rootView == null) {
            setContentView(initLayout());
        } else {
            setContentView(rootView);
        }
        //设置toolbar
        if (needCommonToolBar()) {
            initCommonToolbar();
        }

        initSwipeBackLayout();
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化滑动退出配置
     */
    private final void initSwipeBackLayout() {
        int mode = getSwipeMode();
        if (mode == SwipeBackLayout.EDGE_NULL) {
            getSwipeBackLayout().setEnableGesture(false);
        } else {
            getSwipeBackLayout().setEdgeTrackingEnabled(mode);

        }
        getSwipeBackLayout().setScreenScllor(getSwipeScreen());
    }

    /**
     * 是否全屏滑动退出
     *
     * @return
     */
    public boolean getSwipeScreen() {
        return false;
    }

    /**
     * 设置滑动退出的方向
     *
     * @return {@link SwipeBackLayout}
     */
    public @Duration
    int getSwipeMode() {
        return SwipeBackLayout.EDGE_LEFT;
    }


    @Override
    public void setContentView(View contentView) {
        setContentView(contentView, null);
    }

    @SuppressLint("ResourceType")
    @Override
    public void setContentView(@LayoutRes int layoutId) {
        if (layoutId > 0) {
            if (contentRl != null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(layoutId, contentRl);
            } else {
                super.setContentView(layoutId);
            }
        }
    }

    @Override
    public void setContentView(View contentView, ViewGroup.LayoutParams params) {
        if (contentView != null) {
            if (contentRl != null) {
                contentRl.removeAllViews();
                if (params == null) {
                    params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                }
                contentRl.addView(contentView, params);
            } else {
                super.setContentView(contentView);
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mWaitDialog != null) {
            outState.putBoolean(KEY_SHOW_WAIT, mWaitDialog.isShowing());
            outState.putBoolean(KEY_CANCELABLE_WAIT, mWaitDialog.getCancelable());
            outState.putCharSequence(KEY_CONTENT_WAIT, mWaitDialog.getContent());
            mWaitDialog.dismiss();
            mWaitDialog = null;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            boolean showWait = savedInstanceState.getBoolean(KEY_SHOW_WAIT, false);
            boolean cancelable = savedInstanceState.getBoolean(KEY_CANCELABLE_WAIT, true);
            CharSequence content = savedInstanceState.getCharSequence(KEY_CONTENT_WAIT, "");
            if (showWait) {
                showProgress(cancelable, content, null);
            }
        }
        super.onRestoreInstanceState(savedInstanceState);
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
                    return ct.getProgressView();
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
        return new SimpleProgressView(this);
    }


    public @IdRes
    int getToolBarId() {
        return R.id.commonToolBar;
    }

    private void initCommonToolbar() {
        int toolbarId = getToolBarId();
        if (toolbarId == R.id.commonToolBar) {
            ViewStub stub = findViewById(R.id.toolbarVs);
            stub.inflate();
            setCommonTitleTv(R.id.commonTitleTv);
        }
        commonToolBar = findViewById(toolbarId);
        if (commonToolBar != null) {
            commonToolBar.setContentInsetStartWithNavigation(0);
            commonToolBar.setContentInsetsAbsolute(0, 0);
            setSupportActionBar(commonToolBar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        if (needNavigation()) {
            setNavigation(0, null);
        }

    }

    public TextView getCommonTitleTv() {
        return commonTitleTv;
    }

    public Toolbar getCommonToolBar() {
        return commonToolBar;
    }

    public void setCommonTitleTv(@IdRes int resId) {
        commonTitleTv = findViewById(resId);
        setCommonTitleTv(commonTitleTv);
    }

    public void setCommonTitleTv(TextView mTitleTv) {
        if (commonTitleTv == null || commonTitleTv != mTitleTv) {
            commonTitleTv = mTitleTv;
        }
        commonTitleTv.setText(getTitle());
    }

    /*是否需要通用导航栏*/
    public boolean needCommonToolBar() {
        return true;
    }


    public final void setNavigation(Drawable icon, View.OnClickListener onClickListener) {
        this.initNavigation(0, icon, onClickListener);
    }

    public final void setNavigation(int iconId, View.OnClickListener onClickListener) {
        this.initNavigation(iconId, null, onClickListener);
    }

    private final void initNavigation(int iconId, Drawable icon, View.OnClickListener onClickListener) {
        if (iconId > 0) {
            commonToolBar.setNavigationIcon(iconId);
        } else if (icon != null) {
            commonToolBar.setNavigationIcon(icon);
        } else {
            commonToolBar.setNavigationIcon(R.drawable.bitutils_ic_back);
        }
        if (onClickListener == null) {
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            };
        }
        //设置返回按钮的点击事件
        commonToolBar.setNavigationOnClickListener(onClickListener);
    }

    /*隐藏toolbar*/
    protected final void hideToolBar() {
        if (commonToolBar != null) {
            commonToolBar.setVisibility(View.GONE);
        }
    }

    /*显示toolbar*/
    public final void showToolBar() {
        if (commonToolBar != null && commonToolBar.getVisibility() == View.GONE) {
            commonToolBar.setVisibility(View.VISIBLE);
        }
    }

    /*设置toolbar的点击监听器*/
    protected final void setToolBarMenuClickListener(Toolbar.OnMenuItemClickListener onclick) {
        if (commonToolBar != null) {
            commonToolBar.setOnMenuItemClickListener(onclick);
        }
    }

    /*设置标题*/
    public final void setTitle(CharSequence title) {
        super.setTitle(title);
        if (!TextUtils.isEmpty(title)) {
            if (commonTitleTv != null) {
                commonTitleTv.setText(title);
            }
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }
        }
    }


    /*如果toobar存在的话，是否需要返回键*/
    public boolean needNavigation() {
        return true;
    }

    /*初始化界面，在super.onCreate 之前*/
    public void preCreate() {
    }


    public View initRootView() {
        return null;
    }

    /*初始化布局*/
    public abstract int initLayout();

    /*初始化*/
    public abstract void init() throws Exception;


    @IntDef({SwipeBackLayout.EDGE_LEFT, SwipeBackLayout.EDGE_ALL,
            SwipeBackLayout.EDGE_BOTTOM, SwipeBackLayout.EDGE_RIGHT, SwipeBackLayout.EDGE_NULL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }
}
