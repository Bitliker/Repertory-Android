package com.bitliker.ui.bitdialog.prompt;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.bitliker.ui.bitdialog.common.BaseDialogBuilder;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.listener.PromptWidgetListener;
import com.bitliker.ui.bitdialog.common.paramer.WidgetParamer;

public class PromptDialogBuilder extends BaseDialogBuilder<PromptDialogBuilder> {

    private boolean showPositiveAble;
    private WidgetParamer mContentWidgetParamer;//显示内容
    private WidgetParamer mPositiveWidgetParamer;//确定配置
    private WidgetParamer mNegativeWidgetParamer;//取消配置

    public PromptDialogBuilder(FragmentActivity ct) {
        super(ct);
        this.showPositiveAble = true;
    }

    @Override
    public PromptDialogBuilder getSubclass() {
        return this;
    }

    //是否显示消极动作
    public PromptDialogBuilder showPositiveAble(boolean showAble) {
        this.showPositiveAble = showAble;
        return getSubclass();
    }

    //设置消极动作
    public PromptDialogBuilder setNegative(String negative) {
        return setNegative(new WidgetParamer(negative));
    }

    //设置消极动作
    public PromptDialogBuilder setNegative(String negative, int textSize) {
        return setNegative(new WidgetParamer(negative).setTextSize(textSize));
    }

    //设置消极动作
    public PromptDialogBuilder setNegative(String negative, PromptWidgetListener mPromptWidgetListener) {
        return setNegative(new WidgetParamer(negative).setWidgetListener(mPromptWidgetListener));
    }

    //设置消极动作
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

    public PromptDialogBuilder setPositive(String positive, PromptWidgetListener mPromptWidgetListener) {
        return setPositive(new WidgetParamer(positive).setWidgetListener(mPromptWidgetListener));
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
        args.putParcelable(BitDialogConstants.NEGATIVE_PARAMER, mNegativeWidgetParamer);
        args.putBoolean(BitDialogConstants.NEGATIVE_SHOW_ABLE, showPositiveAble);
        mPromptDialogFragment.setArguments(args);
        mPromptDialogFragment.show(ct.getSupportFragmentManager(), TAG);
        return mPromptDialogFragment;
    }


}
