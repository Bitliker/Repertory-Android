package com.bitliker.ui.bitdialog.input;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.bitliker.ui.bitdialog.common.BaseDialogBuilder;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.paramer.InputWidgetParamer;
import com.bitliker.ui.bitdialog.common.paramer.WidgetParamer;

public class InputDialogBuilder extends BaseDialogBuilder<InputDialogBuilder> {

    private boolean showPositiveAble;
    private WidgetParamer mContentWidgetParamer;//显示内容
    private InputWidgetParamer mPositiveWidgetParamer;//确定配置
    private InputWidgetParamer mNegativeWidgetParamer;//取消配置

    public InputDialogBuilder(FragmentActivity ct) {
        super(ct);
        this.showPositiveAble = true;
    }

    @Override
    public InputDialogBuilder getSubclass() {
        return this;
    }

    //是否显示消极动作
    public InputDialogBuilder showPositiveAble(boolean showAble) {
        this.showPositiveAble = showAble;
        return getSubclass();
    }

    //设置消极动作
    public InputDialogBuilder setNegative(String negative) {
        return setNegative(new InputWidgetParamer(negative));
    }

    //设置消极动作
    public InputDialogBuilder setNegative(String negative, int textSize) {
        return setNegative(new InputWidgetParamer(negative).setTextSize(textSize));
    }

    //设置消极动作
    public InputDialogBuilder setNegative(String negative, InputWidgetParamer.InputClickListener mInputClickListener) {
        return setNegative(new InputWidgetParamer(negative).setWidgetClickListener(mInputClickListener));
    }

    //设置消极动作
    public InputDialogBuilder setNegative(InputWidgetParamer mNegativeWidgetParamer) {
        this.mNegativeWidgetParamer = mNegativeWidgetParamer;
        return this;
    }


    public InputDialogBuilder setPositive(String positive) {
        return setPositive(new InputWidgetParamer(positive));
    }

    public InputDialogBuilder setPositive(String positive, int textSize) {
        return setPositive(new InputWidgetParamer(positive).setTextSize(textSize));
    }

    public InputDialogBuilder setPositive(String positive, InputWidgetParamer.InputClickListener mInputClickListener) {
        return setPositive(new InputWidgetParamer(positive).setWidgetClickListener(mInputClickListener));
    }

    public InputDialogBuilder setPositive(InputWidgetParamer mSureWidgetParamer) {
        this.mPositiveWidgetParamer = mSureWidgetParamer;
        return this;
    }


    public InputDialogBuilder setContent(String content) {
        return setContent(new WidgetParamer(content));
    }

    public InputDialogBuilder setContent(String content, int textSize) {
        return setContent(new WidgetParamer(content).setTextSize(textSize));
    }

    public InputDialogBuilder setContent(WidgetParamer mContentWidgetParamer) {
        this.mContentWidgetParamer = mContentWidgetParamer;
        return this;
    }

    public InputDialogFragment show() {
        InputDialogFragment mInputDialogFragment = new InputDialogFragment();
        Bundle args = new Bundle();
        saveBundle(args);
        args.putParcelable(BitDialogConstants.KEY_CONTENT_PARAMER, mContentWidgetParamer);
        args.putParcelable(BitDialogConstants.POSITIVE_PARAMER, mPositiveWidgetParamer);
        args.putParcelable(BitDialogConstants.NEGATIVE_PARAMER, mNegativeWidgetParamer);
        args.putBoolean(BitDialogConstants.NEGATIVE_SHOW_ABLE, showPositiveAble);
        mInputDialogFragment.setArguments(args);
        mInputDialogFragment.show(ct.getSupportFragmentManager(), TAG);
        return mInputDialogFragment;
    }

}
