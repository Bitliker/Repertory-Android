package com.bitliker.ui.bitdialog.prompt;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bitliker.ui.bitdialog.R;
import com.bitliker.ui.bitdialog.common.BaseDialogFragment;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.WidgetParamer;

public class PromptDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    @Override
    public int getInflater() {
        return R.layout.view_dialog_prompt;
    }

    @Override
    public void initView(View view, WidgetParamer mTitleWidgetParamer) {
        AppCompatTextView titleTv = view.findViewById(R.id.titleTv);
        AppCompatTextView contentTv = view.findViewById(R.id.contentTv);
        AppCompatTextView sureTv = view.findViewById(R.id.sureTv);
        AppCompatTextView cancelTv = view.findViewById(R.id.cancelTv);
        paramer2Text(titleTv, mTitleWidgetParamer, this);
        Bundle args = getArguments();
        if (args != null) {
            WidgetParamer mContentWidgetParamer = args.getParcelable(BitDialogConstants.KEY_CONTENT_PARAMER);
            if (mContentWidgetParamer != null) {
                paramer2Text(contentTv, mContentWidgetParamer, this);
            }
            WidgetParamer mPositiveWidgetParamer = args.getParcelable(BitDialogConstants.POSITIVE_PARAMER);
            if (mPositiveWidgetParamer != null) {
                paramer2Text(sureTv, mPositiveWidgetParamer, this);
            }
            WidgetParamer mNegativeWidgetParamer = args.getParcelable(BitDialogConstants.NEGATIVE_PARAMER);
            if (mNegativeWidgetParamer != null) {
                paramer2Text(cancelTv, mNegativeWidgetParamer, this);
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.titleTv && v.getTag() != null && v.getTag() instanceof WidgetParamer.WidgetClickListener) {
            WidgetParamer.WidgetClickListener mWidgetClickListener = (WidgetParamer.WidgetClickListener) v.getTag();
            mWidgetClickListener.onClick(v);
        }
    }
}
