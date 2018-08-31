package com.bitliker.ui.bitdialog.common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 *
 */
public abstract class BaseDialogBuilder<T extends BaseDialogBuilder> {
    protected final String TAG = getClass().getSimpleName();

    protected FragmentActivity ct;//界面载体
    protected WidgetParamer mTitleWidgetParamer;//标题配置
    protected boolean canceledOnTouchOutside;//是否可以点击外部关闭窗口
    protected boolean cancelable;//是否允许取消
    protected int gravity;//方位
    protected int animationsStyle;//动画效果

    public BaseDialogBuilder(FragmentActivity ct) {
        if (ct == null) {
            new NullPointerException("FragmentActivity  Can't be empty");
        }
        this.ct = ct;
    }


    public T setTitle(String title) {
        return setTitle(new WidgetParamer(title));
    }

    public T setTitle(WidgetParamer mTitleWidgetParamer) {
        return getSubclass();
    }

    /**
     * 设置是否可以取消
     *
     * @param cancelable
     * @return
     */
    public T setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return getSubclass();
    }

    /**
     * 设置是否点击框外关闭
     *
     * @param canceledOnTouchOutside
     * @return
     */
    public T setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
        return getSubclass();
    }

    /**
     * 设置位置|方位
     *
     * @param gravity
     * @return
     */
    public T setGravity(int gravity) {
        this.gravity = gravity;
        return getSubclass();
    }

    /**
     * 设置动画效果
     *
     * @param animationsStyle
     * @return
     */
    public T setAnimationsStyle(int animationsStyle) {
        this.animationsStyle = animationsStyle;
        return getSubclass();
    }

    /**
     * 获取子类对象
     *
     * @return
     */
    public abstract T getSubclass();


    public void saveBundle(Bundle args) {
        args.putParcelable(BitDialogConstants.KEY_TITLE_PARAMER, mTitleWidgetParamer);
        args.putBoolean(BitDialogConstants.CANCELED_ONTOUCH_OUTSIDE, canceledOnTouchOutside);
        args.putBoolean(BitDialogConstants.CANCELABLE, cancelable);
        args.putInt(BitDialogConstants.GRAVITY, gravity);
        args.putInt(BitDialogConstants.ANIMATIONS_STYLE, animationsStyle);
    }

}
