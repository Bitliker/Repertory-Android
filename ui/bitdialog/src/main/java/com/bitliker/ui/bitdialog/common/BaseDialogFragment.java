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

import com.bitliker.ui.bitdialog.R;
import com.bitliker.ui.bitdialog.common.paramer.WidgetParameter;

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
        initView(view);
    }

    public void doSureAndCanCelaShow(TextView sureTv,TextView cancelTv){
        if (cancelTv.getVisibility() == View.VISIBLE && sureTv.getVisibility() == View.VISIBLE) {//两个都显示
            sureTv.setBackgroundResource(R.drawable.bit_dialog_selector_bg_b_l_radian);
            cancelTv.setBackgroundResource(R.drawable.bit_dialog_selector_bg_b_r_radian);
        } else if (cancelTv.getVisibility() == View.VISIBLE && sureTv.getVisibility() == View.GONE) {
            //显示取消不显示确定
            cancelTv.setBackgroundResource(R.drawable.bit_dialog_selector_bg_b_radian);
        } else if (cancelTv.getVisibility() == View.GONE && sureTv.getVisibility() == View.VISIBLE) {
            //显示确定不显示取消
            sureTv.setBackgroundResource(R.drawable.bit_dialog_selector_bg_b_radian);
        }
    }


    public void doCommonTitle(TextView titleTv, Bundle args, View.OnClickListener mOnClickListener) {
        WidgetParameter mTitleWidgetParameter = args.getParcelable(BitDialogConstants.KEY_TITLE_PARAMER);
        if (mTitleWidgetParameter == null) {
            mTitleWidgetParameter = new WidgetParameter(getAppName(getContext()));
        }
        paramer2Text(titleTv, mTitleWidgetParameter, mOnClickListener, false);
    }


    public void doCommonSure(TextView sureTv, Bundle args, View.OnClickListener mOnClickListener) {
        if (args.getBoolean(BitDialogConstants.POSITIVE_SHOW_ABLE, true)) {
            sureTv.setVisibility(View.VISIBLE);
            WidgetParameter mPositiveWidgetParameter = args.getParcelable(BitDialogConstants.POSITIVE_PARAMER);
            if (mPositiveWidgetParameter != null) {
                paramer2Text(sureTv, mPositiveWidgetParameter, mOnClickListener, true);
            } else {
                sureTv.setOnClickListener(mOnClickListener);
            }
        } else {
            sureTv.setVisibility(View.GONE);
        }
    }

    public void doCommonCancel(TextView cancelTv, Bundle args, View.OnClickListener mOnClickListener) {
        if (args.getBoolean(BitDialogConstants.NEGATIVE_SHOW_ABLE, true)) {
            WidgetParameter mNegativeWidgetParameter = args.getParcelable(BitDialogConstants.NEGATIVE_PARAMER);
            if (mNegativeWidgetParameter != null) {
                paramer2Text(cancelTv, mNegativeWidgetParameter, mOnClickListener, true);
            } else {
                cancelTv.setOnClickListener(mOnClickListener);
            }
        } else {
            cancelTv.setVisibility(View.GONE);
        }
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
            int gravity = args.getInt(BitDialogConstants.GRAVITY, Gravity.CENTER);
            int bgRes = args.getInt(BitDialogConstants.BG_DIALOG, R.drawable.bit_dialog_shape_bg_dialog);
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            dialog.setCancelable(cancelAble);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            Window mWindow = dialog.getWindow();
            mWindow.setWindowAnimations(animationsStyle);
            mWindow.setBackgroundDrawableResource(bgRes);
            mWindow.setGravity(gravity);
        }
        return dialog;
    }

    public void paramer2Text(TextView tv, WidgetParameter mWidgetParameter, View.OnClickListener mOnClickListener, boolean bindClickNotListener) {
        String text = mWidgetParameter.getText();
        String hint = mWidgetParameter.getHint();
        tv.setText(text);
        tv.setHint(hint);
        if (mWidgetParameter.getTextColorResId() != 0) {
            tv.setTextColor(getResources().getColor(mWidgetParameter.getTextColorResId()));
        } else if (mWidgetParameter.getTextColor() != -1) {
            tv.setTextColor(mWidgetParameter.getTextColor());
        }
        if (mWidgetParameter.getTextSize() > 0) {
            tv.setTextSize(mWidgetParameter.getTextSizeUnit(), mWidgetParameter.getTextSize());
        }
        if (mWidgetParameter.getOnWidgetClickListener() != null) {
            tv.setTag(mWidgetParameter.getOnWidgetClickListener());
            tv.setOnClickListener(mOnClickListener);
        } else if (bindClickNotListener) {
            tv.setOnClickListener(mOnClickListener);
        }
    }

    public abstract void initView(View view);


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
