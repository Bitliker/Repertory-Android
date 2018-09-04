package com.bitliker.ui.bitdialog.input;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bitliker.ui.bitdialog.R;
import com.bitliker.ui.bitdialog.common.BaseDialogFragment;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.listener.InputWidgetListener;
import com.bitliker.ui.bitdialog.common.listener.PromptWidgetListener;
import com.bitliker.ui.bitdialog.common.paramer.WidgetParameter;

public class InputDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    private AppCompatEditText contentEt;

    @Override
    public int getInflater() {
        return R.layout.view_dialog_input;
    }

    @Override
    public void initView(View view, WidgetParameter mTitleWidgetParameter) {
        AppCompatTextView titleTv = view.findViewById(R.id.titleTv);
        contentEt = view.findViewById(R.id.contentEt);
        AppCompatTextView sureTv = view.findViewById(R.id.sureTv);
        AppCompatTextView cancelTv = view.findViewById(R.id.cancelTv);
        paramer2Text(titleTv, mTitleWidgetParameter, this, false);
        Bundle args = getArguments();
        if (args != null) {
            WidgetParameter mContentWidgetParameter = args.getParcelable(BitDialogConstants.KEY_CONTENT_PARAMER);
            if (mContentWidgetParameter != null) {
                paramer2Text(contentEt, mContentWidgetParameter, null, false);
            }
            WidgetParameter mPositiveWidgetParameter = args.getParcelable(BitDialogConstants.POSITIVE_PARAMER);
            if (mPositiveWidgetParameter != null) {
                paramer2Text(sureTv, mPositiveWidgetParameter, this, true);
            } else {
                sureTv.setOnClickListener(this);
            }
            boolean showPositiveAble = args.getBoolean(BitDialogConstants.NEGATIVE_SHOW_ABLE, true);
            if (showPositiveAble) {
                //显示取消时候
                sureTv.setBackgroundResource(R.drawable.bit_dialog_selector_bg_b_l_radian);
                cancelTv.setBackgroundResource(R.drawable.bit_dialog_selector_bg_b_r_radian);
                cancelTv.setVisibility(View.VISIBLE);
                WidgetParameter mNegativeWidgetParameter = args.getParcelable(BitDialogConstants.NEGATIVE_PARAMER);
                if (mNegativeWidgetParameter != null) {
                    paramer2Text(cancelTv, mNegativeWidgetParameter, this, true);
                } else {
                    cancelTv.setOnClickListener(this);
                }
            } else {
                //不显示取消
                cancelTv.setVisibility(View.GONE);
                sureTv.setBackgroundResource(R.drawable.bit_dialog_selector_bg_b_radian);
            }
        }
    }


    @Override
    public void onClick(View v) {
        InputWidgetListener mInputWidgetListener = null;
        PromptWidgetListener mPromptWidgetListener = null;
        Object object = v.getTag();
        int id = v.getId();
        if (object != null && object instanceof InputWidgetListener) {
            mInputWidgetListener = (InputWidgetListener) object;
        } else if (object != null && object instanceof PromptWidgetListener) {
            mPromptWidgetListener = (PromptWidgetListener) object;
        }
        if (id == R.id.titleTv || R.id.contentTv == id) {
            if (mInputWidgetListener != null) {
                mInputWidgetListener.onClick(v, contentEt);
            } else if (mPromptWidgetListener != null) {
                mPromptWidgetListener.onClick(v);
            }
        } else if (R.id.sureTv == id || R.id.cancelTv == id) {
            if (mInputWidgetListener != null) {
                if (!mInputWidgetListener.onClick(v, contentEt)) {
                    dismiss();
                }
            } else if (mPromptWidgetListener != null) {
                if (!mPromptWidgetListener.onClick(v)) {
                    dismiss();
                }
            } else {
                dismiss();
            }
        }
    }
}
