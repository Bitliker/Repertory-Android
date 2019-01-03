package com.bitliker.ui.bitdialog.common.paramer;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorRes;
import android.util.TypedValue;

import com.bitliker.ui.bitdialog.common.listener.OnMultiSelectListener;
import com.bitliker.ui.bitdialog.common.listener.OnSelectListener;
import com.bitliker.ui.bitdialog.common.listener.WidgetListener;
import com.bitliker.ui.bitdialog.list.BitDialogModel;

import java.util.ArrayList;
import java.util.List;

public class ListParameter implements Parcelable {
    private int textColorResId;
    private int textColor;
    private int textSize;
    private int textSizeUnit;
    private List<BitDialogModel> models;
    private WidgetListener mWidgetListener;

    public ListParameter() {
    }

    protected ListParameter(Parcel in) {
        textColorResId = in.readInt();
        textColor = in.readInt();
        textSize = in.readInt();
        textSizeUnit = in.readInt();
    }

    public static final Creator<ListParameter> CREATOR = new Creator<ListParameter>() {
        @Override
        public ListParameter createFromParcel(Parcel in) {
            return new ListParameter(in);
        }

        @Override
        public ListParameter[] newArray(int size) {
            return new ListParameter[size];
        }
    };

    public WidgetListener getOnWidgetClickListener() {
        return mWidgetListener;
    }

    public int getTextColorResId() {
        return textColorResId;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getTextSizeUnit() {
        return textSizeUnit;
    }

    public List<BitDialogModel> getModels() {
        return models;
    }


    public ListParameter setTextColorResId(@ColorRes int textColorResId) {
        this.textColorResId = textColorResId;
        return this;
    }

    public ListParameter setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public ListParameter setTextSize(int textSize) {
        this.textSize = textSize;
        return setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public ListParameter setTextSize(int textSizeUnit, int textSize) {
        this.textSize = textSize;
        this.textSizeUnit = textSizeUnit;
        return this;
    }

    public void setModels(List<BitDialogModel> models) {
        this.models = models;
    }

    public ListParameter setOnSelectListener(OnSelectListener onSelectListener) {
        this.mWidgetListener = onSelectListener;
        return this;
    }

    public ListParameter setOnMultiSelectListener(OnMultiSelectListener onMultiSelectListener) {
        this.mWidgetListener = onMultiSelectListener;
        return this;
    }

    public boolean isMulti() {
        return this.mWidgetListener != null && this.mWidgetListener instanceof OnMultiSelectListener;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(textColorResId);
        dest.writeInt(textColor);
        dest.writeInt(textSize);
        dest.writeInt(textSizeUnit);
    }
}
