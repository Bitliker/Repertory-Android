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
    public void initView(View view) {
        AppCompatTextView titleTv = view.findViewById(R.id.titleTv);
        contentEt = view.findViewById(R.id.contentEt);
        AppCompatTextView sureTv = view.findViewById(R.id.sureTv);
        AppCompatTextView cancelTv = view.findViewById(R.id.cancelTv);
        Bundle args = getArguments();
        if (args != null) {
            //标题
            doCommonTitle(titleTv, args, this);
            //确定按钮
            doCommonSure(sureTv, args, this);
            //取消按钮
            doCommonCancel(cancelTv, args, this);
            //确定按钮和取消按钮显示的背景图切换
            doSureAndCanCelaShow(sureTv,cancelTv);
            //内容
            WidgetParameter mContentWidgetParameter = args.getParcelable(BitDialogConstants.KEY_CONTENT_PARAMER);
            if (mContentWidgetParameter != null) {
                paramer2Text(contentEt, mContentWidgetParameter, null, false);
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
