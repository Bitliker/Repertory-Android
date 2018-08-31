package com.bitliker.ui.bitdialog.common;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public abstract class BaseDialogFragment extends AppCompatDialogFragment {


    private View rootView;

    /**
     * 创建视图
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getInflater(), container, false);
        return rootView;
    }

    /**
     * 视图创建完成
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        WidgetParamer mTitleWidgetParamer = null;
        if (args != null) {
            mTitleWidgetParamer = args.getParcelable(BitDialogConstants.KEY_TITLE_PARAMER);
        }
        if (mTitleWidgetParamer == null) {
            mTitleWidgetParamer = new WidgetParamer(getAppName(getContext()));
        }
        initView(view, mTitleWidgetParamer);
    }


    /**
     * 创建窗口
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), getDialogStyle());

        Bundle args = getArguments();
        if (args != null) {
            boolean cancelAble = args.getBoolean(BitDialogConstants.CANCELABLE, true);
            boolean canceledOnTouchOutside = args.getBoolean(BitDialogConstants.CANCELED_ONTOUCH_OUTSIDE, true);
            int animationsStyle = args.getInt(BitDialogConstants.ANIMATIONS_STYLE, 0);
            int gravity = args.getInt(BitDialogConstants.ANIMATIONS_STYLE, Gravity.CENTER);
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            dialog.setCancelable(cancelAble);
            dialog.getWindow().setWindowAnimations(animationsStyle);
            dialog.getWindow().setGravity(gravity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }
    public  void paramer2Text(TextView tv, WidgetParamer mWidgetParamer,View.OnClickListener mOnClickListener) {
        CharSequence title = mWidgetParamer.getText();
        tv.setText(title);
        if (mWidgetParamer.getTextColor() > 0) {
            tv.setTextColor(mWidgetParamer.getTextColor());
        }
        if (mWidgetParamer.getTextSize() > 0) {
            if (mWidgetParamer.getOnWidgetClickListener() != null) {
                tv.setTag(mWidgetParamer.getOnWidgetClickListener());
                tv.setOnClickListener(mOnClickListener);
            }
            tv.setTextSize(mWidgetParamer.getTextSizeUnit(), mWidgetParamer.getTextSize());
        }
    }

    public abstract void initView(View view, WidgetParamer mTitleWidgetParamer);


    public abstract int getInflater();


    /**
     * 默认没有主题
     *
     * @return 当前窗口主题
     */
    public int getDialogStyle() {
        return 0;
    }

    /**
     * 获取应用程序名称
     */
    public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
