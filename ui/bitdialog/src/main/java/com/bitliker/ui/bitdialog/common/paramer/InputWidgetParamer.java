package com.bitliker.ui.bitdialog.common.paramer;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorRes;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by Bitlike on 2018/1/24.
 */

public class InputWidgetParamer implements Parcelable {
    private int textColorResId;
    private int textColor;
    private int textSize;
    private int textSizeUnit;
    private String text;
    private InputClickListener mInputClickListener;

    public InputWidgetParamer() {
        this("");
    }

    public InputWidgetParamer(String text) {
        textColorResId = 0;
        textColor = -1;
        textSize = -1;
        this.text = text;
        this.textSizeUnit = TypedValue.COMPLEX_UNIT_SP;
    }

    public InputWidgetParamer setWidgetClickListener(InputClickListener mInputClickListener) {
        this.mInputClickListener = mInputClickListener;
        return this;
    }

    public InputWidgetParamer setText(String text) {
        this.text = text;
        return this;
    }

    public InputWidgetParamer setTextColorResId(@ColorRes int textColorResId) {
        this.textColorResId = textColorResId;
        return this;
    }

    public InputWidgetParamer setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public InputWidgetParamer setTextSize(int textSize) {
        this.textSize = textSize;
        return setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public InputWidgetParamer setTextSize(int textSizeUnit, int textSize) {
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

    public InputClickListener getInputClickListener() {
        return mInputClickListener;
    }

    protected InputWidgetParamer(Parcel in) {
        textColorResId = in.readInt();
        textColor = in.readInt();
        textSize = in.readInt();
        textSizeUnit = in.readInt();
        text = in.readString();
        mInputClickListener = (InputClickListener) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(textColorResId);
        dest.writeInt(textColor);
        dest.writeInt(textSize);
        dest.writeInt(textSizeUnit);
        dest.writeString(text);
        dest.writeSerializable(mInputClickListener);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InputWidgetParamer> CREATOR = new Creator<InputWidgetParamer>() {
        @Override
        public InputWidgetParamer createFromParcel(Parcel in) {
            return new InputWidgetParamer(in);
        }

        @Override
        public InputWidgetParamer[] newArray(int size) {
            return new InputWidgetParamer[size];
        }
    };


    public interface InputClickListener extends Serializable {
        boolean onClick(View v, TextView inputTv);
    }
}
