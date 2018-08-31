package com.bitliker.ui.bitdialog.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by Bitlike on 2018/1/24.
 */

public class WidgetParamer implements Parcelable {
    private int textColor;
    private int textSize;
    private int textSizeUnit;
    private String text;
    private WidgetClickListener onWidgetClickListener;

    public WidgetParamer() {
        this("");
    }

    public WidgetParamer(String text) {
        textColor = -1;
        textSize = -1;
        this.text = text;
        this.textSizeUnit = TypedValue.COMPLEX_UNIT_SP;
    }

    public WidgetParamer setWidgetClickListener(WidgetClickListener onWidgetClickListener) {
        this.onWidgetClickListener = onWidgetClickListener;
        return this;
    }

    public WidgetParamer setText(String text) {
        this.text = text;
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

    public int getTextSize() {
        return textSize;
    }

    public int getTextSizeUnit() {
        return textSizeUnit;
    }

    public String getText() {
        return text==null?"":text;
    }

    public WidgetClickListener getOnWidgetClickListener() {
        return onWidgetClickListener;
    }

    protected WidgetParamer(Parcel in) {
        textColor = in.readInt();
        textSize = in.readInt();
        textSizeUnit = in.readInt();
        text = in.readString();
        onWidgetClickListener = (WidgetClickListener) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(textColor);
        dest.writeInt(textSize);
        dest.writeInt(textSizeUnit);
        dest.writeString(text);
        dest.writeSerializable(onWidgetClickListener);
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


    public interface WidgetClickListener extends Serializable {
        boolean onClick(View v);
    }
}
