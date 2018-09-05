package com.bitliker.ui.bitdialog.prompt;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bitliker.ui.bitdialog.R;
import com.bitliker.ui.bitdialog.common.BaseDialogFragment;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.listener.PromptWidgetListener;
import com.bitliker.ui.bitdialog.common.paramer.WidgetParameter;

public class PromptDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    @Override
    public int getInflater() {
        return R.layout.view_dialog_prompt;
    }

    @Override
    public void initView(View view) {
        AppCompatTextView titleTv = view.findViewById(R.id.titleTv);
        AppCompatTextView contentTv = view.findViewById(R.id.contentTv);
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
                paramer2Text(contentTv, mContentWidgetParameter, this, false);
            }
        }
    }


    @Override
    public void onClick(View v) {
        PromptWidgetListener mPromptWidgetListener = null;
        Object object = v.getTag();
        int id = v.getId();
        if (object != null && object instanceof PromptWidgetListener) {
            mPromptWidgetListener = (PromptWidgetListener) object;
        }
        if (id == R.id.titleTv || R.id.contentTv == id) {
            if (mPromptWidgetListener != null) {
                mPromptWidgetListener.onClick(v);
            }
        } else if (R.id.sureTv == id || R.id.cancelTv == id) {
            if (mPromptWidgetListener != null) {
                if (!mPromptWidgetListener.onClick(v)) {
                    dismiss();
                }
            } else {
                dismiss();
            }
        }
    }
}
