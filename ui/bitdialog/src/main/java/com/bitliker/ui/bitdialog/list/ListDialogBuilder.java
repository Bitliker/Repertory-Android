package com.bitliker.ui.bitdialog.list;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.Gravity;

import com.bitliker.ui.bitdialog.R;
import com.bitliker.ui.bitdialog.common.BaseDialogBuilder;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.listener.OnMultiSelectListener;
import com.bitliker.ui.bitdialog.common.listener.OnSelectListener;
import com.bitliker.ui.bitdialog.common.paramer.ListParameter;

import java.util.ArrayList;
import java.util.List;

public class ListDialogBuilder extends BaseDialogBuilder<ListDialogBuilder> {

    private ListParameter mListParameter;

    public ListDialogBuilder(FragmentActivity ct) {
        super(ct);
        this.gravity = Gravity.BOTTOM;
        this.showPositiveAble = false;
        this.bgRes = R.drawable.bit_dialog_shape_bg_dialog_transparent;
        this.animationsStyle = R.style.DialogBottomAnim;
        this.mListParameter = new ListParameter();
    }

    @Override
    public ListDialogBuilder getSubclass() {
        return this;
    }

    public ListDialogBuilder setTextColorResId(@ColorRes int textColorResId) {
        mListParameter.setTextColorResId(textColorResId);
        return this;
    }

    public ListDialogBuilder setTextColor(int textColor) {
        mListParameter.setTextColor(textColor);
        return this;
    }

    public ListDialogBuilder setTextSize(int textSize) {
        mListParameter.setTextSize(textSize);
        return setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public ListDialogBuilder setTextSize(int textSizeUnit, int textSize) {
        mListParameter.setTextSize(textSizeUnit, textSize);
        return this;
    }


    public ListDialogFragment show(List<BitDialogModel> models, OnMultiSelectListener onMultiSelectListener) {
        this.showPositiveAble = true;
        mListParameter.setModels(models);
        mListParameter.setOnMultiSelectListener(onMultiSelectListener);
        return show(mListParameter);
    }

    private ListDialogFragment show(ListParameter mListParameter) {
        ListDialogFragment mListDialogFragment = new ListDialogFragment();
        Bundle args = new Bundle();
        saveBundle(args);
        args.putParcelable(BitDialogConstants.LIST_PARAMETER, mListParameter);
        mListDialogFragment.setArguments(args);
        mListDialogFragment.show(ct.getSupportFragmentManager(), TAG);
        return mListDialogFragment;
    }

    public ListDialogFragment show(List<BitDialogModel> models, OnSelectListener onSelectListener) {
        mListParameter.setModels(models);
        mListParameter.setOnSelectListener(onSelectListener);
        return show(mListParameter);
    }


}
