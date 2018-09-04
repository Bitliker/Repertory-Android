package com.bitliker.ui.bitdialog.input;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.bitliker.ui.bitdialog.common.BaseDialogBuilder;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.paramer.WidgetParameter;

public class InputDialogBuilder extends BaseDialogBuilder<InputDialogBuilder> {

    private WidgetParameter mContentWidgetParameter;//显示内容

    public InputDialogBuilder(FragmentActivity ct) {
        super(ct);
    }

    @Override
    public InputDialogBuilder getSubclass() {
        return this;
    }


    public InputDialogBuilder setContent(String content) {
        return setContent(new WidgetParameter(content));
    }

    public InputDialogBuilder setContent(String content, int textSize) {
        return setContent(new WidgetParameter(content).setTextSize(textSize));
    }

    public InputDialogBuilder setContent(WidgetParameter mContentWidgetParameter) {
        this.mContentWidgetParameter = mContentWidgetParameter;
        return this;
    }


    public InputDialogFragment show() {
        InputDialogFragment mInputDialogFragment = new InputDialogFragment();
        Bundle args = new Bundle();
        saveBundle(args);
        args.putParcelable(BitDialogConstants.KEY_CONTENT_PARAMER, mContentWidgetParameter);
        mInputDialogFragment.setArguments(args);
        mInputDialogFragment.show(ct.getSupportFragmentManager(), TAG);
        return mInputDialogFragment;
    }


}
