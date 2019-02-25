package com.bitliker.simple.common.utils;

import android.content.Context;

import com.bitliker.simple.R;
import com.bitliker.ui.bitrectclerviewutils.widget.RecyclerLineDecoration;

public class RecyclerDecoration extends RecyclerLineDecoration {


    public RecyclerDecoration(Context ct) {
        super(1, ct.getResources().getColor(R.color.itemLine));
    }

    public RecyclerDecoration(int dividerHeight, int dividerColor) {
        super(dividerHeight, dividerColor);
    }
}
