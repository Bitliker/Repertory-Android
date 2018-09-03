package com.bitliker.ui.bitdialog.input;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bitliker.ui.bitdialog.R;
import com.bitliker.ui.bitdialog.common.BaseDialogFragment;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.paramer.WidgetParamer;

public class InputDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    @Override
    public int getInflater() {
        return R.layout.view_dialog_input;
    }

    @Override
    public void initView(View view, WidgetParamer mTitleWidgetParamer) {

        AppCompatTextView titleTv = view.findViewById(R.id.titleTv);
        AppCompatEditText contentEt = view.findViewById(R.id.contentEt);
        AppCompatTextView sureTv = view.findViewById(R.id.sureTv);
        AppCompatTextView cancelTv = view.findViewById(R.id.cancelTv);
        paramer2Text(titleTv, mTitleWidgetParamer, this, false);
        Bundle args = getArguments();
        if (args != null) {
            WidgetParamer mContentWidgetParamer = args.getParcelable(BitDialogConstants.KEY_CONTENT_PARAMER);
            if (mContentWidgetParamer != null) {
                paramer2Text(contentEt, mContentWidgetParamer, null, false);
            }



            WidgetParamer mPositiveWidgetParamer = args.getParcelable(BitDialogConstants.POSITIVE_PARAMER);
            if (mPositiveWidgetParamer != null) {
                paramer2Text(sureTv, mPositiveWidgetParamer, this, true);
            } else {
                sureTv.setOnClickListener(this);
            }
            boolean showPositiveAble = args.getBoolean(BitDialogConstants.NEGATIVE_SHOW_ABLE, true);
            if (showPositiveAble) {
                //显示取消时候
                sureTv.setBackgroundResource(R.drawable.bit_dialog_selector_bg_b_l_radian);
                cancelTv.setBackgroundResource(R.drawable.bit_dialog_selector_bg_b_r_radian);
                cancelTv.setVisibility(View.VISIBLE);
                WidgetParamer mNegativeWidgetParamer = args.getParcelable(BitDialogConstants.NEGATIVE_PARAMER);
                if (mNegativeWidgetParamer != null) {
                    paramer2Text(cancelTv, mNegativeWidgetParamer, this, true);
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
//        WidgetParamer.WidgetClickListener mWidgetClickListener = null;
//        Object object = v.getTag();
//        int id = v.getId();
//        if (object != null && object instanceof WidgetParamer.WidgetClickListener) {
//            mWidgetClickListener = (WidgetParamer.WidgetClickListener) object;
//        }
//        if (id == R.id.titleTv || R.id.contentTv == id) {
//            if (mWidgetClickListener != null) {
//                mWidgetClickListener.onClick(v,null);
//            }
//        } else if (R.id.sureTv == id || R.id.cancelTv == id) {
//            if (mWidgetClickListener != null) {
//                if (mWidgetClickListener.onClick(v,null)) {
//                    dismiss();
//                }
//            } else {
//                dismiss();
//            }
//        }
    }
}
