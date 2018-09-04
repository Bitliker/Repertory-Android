package com.bitliker.ui.bitdialog.common.paramer;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorRes;
import android.util.TypedValue;

import com.bitliker.ui.bitdialog.common.listener.WidgetListener;

/**
 * Created by Bitlike on 2018/1/24.
 */

public class WidgetParameter implements Parcelable {
    private int textColorResId;
    private int textColor;
    private int textSize;
    private int textSizeUnit;
    private String text;
    private String hint;
    private WidgetListener mWidgetListener;

    public WidgetParameter() {
        this("");
    }

    public WidgetParameter(String text) {
        textColorResId = 0;
        textColor = -1;
        textSize = -1;
        this.text = text;
        this.textSizeUnit = TypedValue.COMPLEX_UNIT_SP;
    }

    public WidgetParameter setWidgetListener(WidgetListener mWidgetListener) {
        this.mWidgetListener = mWidgetListener;
        return this;
    }

    public WidgetListener getOnWidgetClickListener() {
        return mWidgetListener;
    }



    public String getHint() {
        return hint==null?"":hint;
    }

    public WidgetParameter setHint(String hint) {
        this.hint = hint;
        return this;
    }


    public WidgetParameter setText(String text) {
        this.text = text;
        return this;
    }

    public WidgetParameter setTextColorResId(@ColorRes int textColorResId) {
        this.textColorResId = textColorResId;
        return this;
    }

    public WidgetParameter setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public WidgetParameter setTextSize(int textSize) {
        this.textSize = textSize;
        return setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public WidgetParameter setTextSize(int textSizeUnit, int textSize) {
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


    protected WidgetParameter(Parcel in) {
        textColorResId = in.readInt();
        textColor = in.readInt();
        textSize = in.readInt();
        textSizeUnit = in.readInt();
        text = in.readString();
        hint = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(textColorResId);
        dest.writeInt(textColor);
        dest.writeInt(textSize);
        dest.writeInt(textSizeUnit);
        dest.writeString(text);
        dest.writeString(hint);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WidgetParameter> CREATOR = new Creator<WidgetParameter>() {
        @Override
        public WidgetParameter createFromParcel(Parcel in) {
            return new WidgetParameter(in);
        }

        @Override
        public WidgetParameter[] newArray(int size) {
            return new WidgetParameter[size];
        }
    };



}
