package com.bitliker.ui.bitdialog.list;

import android.os.Parcel;
import android.os.Parcelable;

public class BitDialogModel<T> implements Parcelable {
    private int id;
    private boolean selected;
    private float sort;
    private String showValues;
    private T data;

    public BitDialogModel(String showValues) {
        this.showValues = showValues;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public float getSort() {
        return sort;
    }

    public void setSort(float sort) {
        this.sort = sort;
    }

    public String getShowValues() {
        return showValues==null?"":showValues;
    }

    public void setShowValues(String showValues) {
        this.showValues = showValues;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    protected BitDialogModel(Parcel in) {
        id = in.readInt();
        selected = in.readByte() != 0;
        sort = in.readFloat();
        showValues = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (selected ? 1 : 0));
        dest.writeFloat(sort);
        dest.writeString(showValues);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BitDialogModel> CREATOR = new Creator<BitDialogModel>() {
        @Override
        public BitDialogModel createFromParcel(Parcel in) {
            return new BitDialogModel(in);
        }

        @Override
        public BitDialogModel[] newArray(int size) {
            return new BitDialogModel[size];
        }
    };
}
