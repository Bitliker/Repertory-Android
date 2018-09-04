package com.bitliker.ui.bitdialog.prompt;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.bitliker.ui.bitdialog.common.BaseDialogBuilder;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.paramer.WidgetParameter;

public class PromptDialogBuilder extends BaseDialogBuilder<PromptDialogBuilder> {

    private WidgetParameter mContentWidgetParameter;//显示内容

    public PromptDialogBuilder(FragmentActivity ct) {
        super(ct);
    }

    @Override
    public PromptDialogBuilder getSubclass() {
        return this;
    }

    public PromptDialogBuilder setContent(String content) {
        return setContent(new WidgetParameter(content));
    }

    public PromptDialogBuilder setContent(String content, int textSize) {
        return setContent(new WidgetParameter(content).setTextSize(textSize));
    }

    public PromptDialogBuilder setContent(WidgetParameter mContentWidgetParameter) {
        this.mContentWidgetParameter = mContentWidgetParameter;
        return this;
    }


    public PromptDialogFragment show() {
        PromptDialogFragment mPromptDialogFragment = new PromptDialogFragment();
        Bundle args = new Bundle();
        saveBundle(args);
        args.putParcelable(BitDialogConstants.KEY_CONTENT_PARAMER, mContentWidgetParameter);
        mPromptDialogFragment.setArguments(args);
        mPromptDialogFragment.show(ct.getSupportFragmentManager(), TAG);
        return mPromptDialogFragment;
    }


}
