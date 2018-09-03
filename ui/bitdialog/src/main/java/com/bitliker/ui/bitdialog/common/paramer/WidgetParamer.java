package com.bitliker.ui.bitdialog.common.paramer;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorRes;
import android.util.TypedValue;

import com.bitliker.ui.bitdialog.common.listener.WidgetListener;

/**
 * Created by Bitlike on 2018/1/24.
 */

public class WidgetParamer implements Parcelable {
    private int textColorResId;
    private int textColor;
    private int textSize;
    private int textSizeUnit;
    private String text;
    private WidgetListener mWidgetListener;

    public WidgetParamer() {
        this("");
    }

    public WidgetParamer(String text) {
        textColorResId = 0;
        textColor = -1;
        textSize = -1;
        this.text = text;
        this.textSizeUnit = TypedValue.COMPLEX_UNIT_SP;
    }

    public WidgetParamer setWidgetListener(WidgetListener mWidgetListener) {
        this.mWidgetListener = mWidgetListener;
        return this;
    }

    public WidgetListener getOnWidgetClickListener() {
        return mWidgetListener;
    }


    public WidgetParamer setText(String text) {
        this.text = text;
        return this;
    }

    public WidgetParamer setTextColorResId(@ColorRes int textColorResId) {
        this.textColorResId = textColorResId;
        return this;
    }

    public WidgetParamer setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public WidgetParamer setTextSize(int textSize) {
        this.textSize = textSize;
        return setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public WidgetParamer setTextSize(int textSizeUnit, int textSize) {
        this.textSize = textSize;
        this.textSizeUnit = textSizeUnit;
        return this;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextColorResId() {
        return textColorResId;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getTextSizeUnit() {
        return textSizeUnit;
    }

    public String getText() {
        return text == null ? "" : text;
    }


    protected WidgetParamer(Parcel in) {
        textColorResId = in.readInt();
        textColor = in.readInt();
        textSize = in.readInt();
        textSizeUnit = in.readInt();
        text = in.readString();
        mWidgetListener = (WidgetListener) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(textColorResId);
        dest.writeInt(textColor);
        dest.writeInt(textSize);
        dest.writeInt(textSizeUnit);
        dest.writeString(text);
        dest.writeSerializable(mWidgetListener);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WidgetParamer> CREATOR = new Creator<WidgetParamer>() {
        @Override
        public WidgetParamer createFromParcel(Parcel in) {
            return new WidgetParamer(in);
        }

        @Override
        public WidgetParamer[] newArray(int size) {
            return new WidgetParamer[size];
        }
    };



}
