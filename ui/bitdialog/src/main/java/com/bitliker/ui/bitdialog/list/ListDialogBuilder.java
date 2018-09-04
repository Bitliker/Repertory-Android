package com.bitliker.ui.bitdialog.list;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;

import com.bitliker.ui.bitdialog.R;
import com.bitliker.ui.bitdialog.common.BaseDialogBuilder;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.listener.OnMultiSelectListener;
import com.bitliker.ui.bitdialog.common.listener.OnSelectListener;
import com.bitliker.ui.bitdialog.prompt.PromptDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class ListDialogBuilder<T> extends BaseDialogBuilder<ListDialogBuilder> {


    public ListDialogBuilder(FragmentActivity ct) {
        super(ct);
        this.gravity = Gravity.BOTTOM;
        this.bgRes = R.drawable.bit_dialog_shape_bg_dialog_transparent;
        this.animationsStyle = R.style.DialogBottomAnim;
    }

    @Override
    public ListDialogBuilder getSubclass() {
        return this;
    }


    public ListDialogFragment show(ArrayList<BitDialogModel<T>> models, OnMultiSelectListener<T> onMultiSelectListener) {
        ListDialogFragment mListDialogFragment = new ListDialogFragment();
        Bundle args = new Bundle();
        saveBundle(args);
        args.putParcelableArrayList(BitDialogConstants.LIST_MODELS, models);
        args.putParcelableArrayList(BitDialogConstants.LIST_MODELS, models);
        args.putSerializable(BitDialogConstants.LIST_LISTENER, onMultiSelectListener);
        mListDialogFragment.setArguments(args);
        mListDialogFragment.show(ct.getSupportFragmentManager(), TAG);
        return mListDialogFragment;
    }

    public ListDialogFragment show(ArrayList<BitDialogModel<T>> models, OnSelectListener<T> onSelectListener) {
        ListDialogFragment mListDialogFragment = new ListDialogFragment();
        Bundle args = new Bundle();
        saveBundle(args);
        args.putParcelableArrayList(BitDialogConstants.LIST_MODELS, models);
        args.putParcelableArrayList(BitDialogConstants.LIST_MODELS, models);
        args.putSerializable(BitDialogConstants.LIST_LISTENER, onSelectListener);
        mListDialogFragment.setArguments(args);
        mListDialogFragment.show(ct.getSupportFragmentManager(), TAG);
        return mListDialogFragment;
    }



    }
