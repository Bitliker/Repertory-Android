package com.bitliker.ui.bitdialog.common.listener;

import com.bitliker.ui.bitdialog.list.BitDialogModel;

import java.io.Serializable;
import java.util.List;

/**
 * 多选回调
 */
public interface OnMultiSelectListener<T> extends Serializable {

    boolean select(boolean sure, List<BitDialogModel<T>> selectModels);

}
