package com.bitliker.ui.bitdialog.prompt;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import com.bitliker.ui.bitdialog.R;
import com.bitliker.ui.bitdialog.common.BaseDialogFragment;
import com.bitliker.ui.bitdialog.common.WidgetParamer;

public class PromptDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    @Override
    public int getInflater() {
        return R.layout.view_dialog_prompt;
    }
    @Override
    public void initView(View view,WidgetParamer mTitleWidgetParamer) {
        AppCompatTextView titleTv = view.findViewById(R.id.titleTv);
        AppCompatTextView contentTv = view.findViewById(R.id.contentTv);
        AppCompatTextView sureTv = view.findViewById(R.id.sureTv);
        AppCompatTextView cancelTv = view.findViewById(R.id.cancelTv);


        Bundle args = getArguments();
        if (args != null) {
            CharSequence title = args.getCharSequence(KEY_TITLE);
            CharSequence content = args.getCharSequence(KEY_CONTENT);
            if (!TextUtils.isEmpty(title)) {
                titleTv.setText(title);
            }
            if (!TextUtils.isEmpty(content)) {
                contentTv.setText(content);
            }
        }

        WidgetParamer sureWidgetParamer = getWidgetParamer(KEY_SURE_PARAMER);
        if (sureWidgetParamer == null) {
            sureTv.setVisibility(View.GONE);
            cancelTv.setBackgroundResource(R.drawable.selector_b_white_hint_bg);
        } else {
            sureTv.setText(TextUtils.isEmpty(sureWidgetParamer.text) ? "" : sureWidgetParamer.text);
            if (sureWidgetParamer.textColor > 0) {
                sureTv.setTextColor(sureWidgetParamer.textColor);
            }
            if (sureWidgetParamer.textSize > 0) {
                sureTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, sureWidgetParamer.textSize);
            }
            sureTv.setOnClickListener(this);
        }
        WidgetParamer cancelWidgetParamer = getWidgetParamer(KEY_CANCEL_PARAMER);
        if (cancelWidgetParamer == null) {
            cancelTv.setVisibility(View.GONE);
            if (sureTv.getVisibility() == View.VISIBLE) {
                sureTv.setBackgroundResource(R.drawable.selector_b_white_hint_bg);
            } else {
                findViewById(R.id.baseRl).setBackgroundResource(R.drawable.radian_white_bg);
                findViewById(R.id.bottomv).setVisibility(View.GONE);
            }
        } else {
            cancelTv.setText(TextUtils.isEmpty(cancelWidgetParamer.text) ? "" : cancelWidgetParamer.text);
            if (cancelWidgetParamer.textColor > 0) {
                cancelTv.setTextColor(cancelWidgetParamer.textColor);
            }
            if (cancelWidgetParamer.textSize > 0) {
                cancelTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, cancelWidgetParamer.textSize);
            }
        }
        cancelTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
