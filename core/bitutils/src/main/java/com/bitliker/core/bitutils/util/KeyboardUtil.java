package com.bitliker.core.bitutils.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtil {

    /**
     * 打开软键盘
     *
     * @param view 接受键盘的焦点的view
     */
    public static void openKeybord(final View view) {
        if (view != null) {
            view.post(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
                }
            });
        }
    }

    /**
     * 关闭软键盘
     *
     * @param mContext 当前界面
     */
    public static void closeKeybord(Context mContext) {
        if (mContext != null && mContext instanceof Activity) {
            Activity ct = (Activity) mContext;
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive() && ct.getCurrentFocus() != null) {
                if (ct.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(ct.getCurrentFocus().getWindowToken(),  WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
                }
            }
        }
    }

    /**
     * 判断当前软键盘是否打开,该方法判断不可信，当界面中存在获取到焦点的控件，就返回ture
     *
     * @param activity
     * @return
     */
    @Deprecated
    public static boolean isSoftInputShow(Activity activity) {
        //         虚拟键盘隐藏 判断view是否为空
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            // 隐藏虚拟键盘
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            return inputmanger.isActive() && activity.getWindow().getCurrentFocus() != null;
        }
        return false;
    }

}