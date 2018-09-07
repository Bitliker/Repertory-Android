package com.bitliker.core.bitutils.widget;

import android.content.Context;
import android.widget.FrameLayout;

import java.io.Serializable;

/**
 * Created by Bitlike on 2018/1/2.
 */

public abstract class ProgressView extends FrameLayout implements Serializable {

    public ProgressView(Context context) {
        super(context);
    }


    protected abstract void startAnimation(CharSequence message);

    protected abstract void stopAnimation();

}
