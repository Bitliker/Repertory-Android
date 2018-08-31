package com.bitliker.ui.bitdialog.prompt;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.bitliker.ui.bitdialog.common.BaseDialogBuilder;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.WidgetParamer;

public class PromptDialogBuilder extends BaseDialogBuilder<PromptDialogBuilder> {

    private WidgetParamer mContentWidgetParamer;//显示内容
    private WidgetParamer mPositiveWidgetParamer;//确定配置
    private WidgetParamer mNegativeWidgetParamer;//取消配置

    public PromptDialogBuilder(FragmentActivity ct) {
        super(ct);
    }

    @Override
    public PromptDialogBuilder getSubclass() {
        return this;
    }

    public PromptDialogBuilder setNegative(String negative) {
        return setNegative(new WidgetParamer(negative));
    }

    public PromptDialogBuilder setNegative(String negative, int textSize) {
        return setNegative(new WidgetParamer(negative).setTextSize(textSize));
    }

    public PromptDialogBuilder setNegative(String negative, WidgetParamer.WidgetClickListener onWidgetClickListener) {
        return setNegative(new WidgetParamer(negative).setWidgetClickListener(onWidgetClickListener));
    }

    public PromptDialogBuilder setNegative(WidgetParamer mNegativeWidgetParamer) {
        this.mNegativeWidgetParamer = mNegativeWidgetParamer;
        return this;
    }




    public PromptDialogBuilder setPositive(String positive) {
        return setPositive(new WidgetParamer(positive));
    }

    public PromptDialogBuilder setPositive(String positive, int textSize) {
        return setPositive(new WidgetParamer(positive).setTextSize(textSize));
    }

    public PromptDialogBuilder setPositive(String positive, WidgetParamer.WidgetClickListener onWidgetClickListener) {
        return setPositive(new WidgetParamer(positive).setWidgetClickListener(onWidgetClickListener));
    }

    public PromptDialogBuilder setPositive(WidgetParamer mSureWidgetParamer) {
        this.mPositiveWidgetParamer = mSureWidgetParamer;
        return this;
    }


    public PromptDialogBuilder setContent(String content) {
        return setContent(new WidgetParamer(content));
    }

    public PromptDialogBuilder setContent(String content, int textSize) {
        return setContent(new WidgetParamer(content).setTextSize(textSize));
    }

    public PromptDialogBuilder setContent(WidgetParamer mContentWidgetParamer) {
        this.mContentWidgetParamer = mContentWidgetParamer;
        return this;
    }


    public PromptDialogFragment show() {
        PromptDialogFragment mPromptDialogFragment = new PromptDialogFragment();
        Bundle args = new Bundle();
        saveBundle(args);
        args.putParcelable(BitDialogConstants.KEY_CONTENT_PARAMER, mContentWidgetParamer);
        args.putParcelable(BitDialogConstants.POSITIVE_PARAMER, mPositiveWidgetParamer);
        args.putParcelable(BitDialogConstants.POSITIVE_PARAMER, mPositiveWidgetParamer);
        mPromptDialogFragment.setArguments(args);
        mPromptDialogFragment.show(ct.getSupportFragmentManager(), TAG);
        return mPromptDialogFragment;
    }


}
