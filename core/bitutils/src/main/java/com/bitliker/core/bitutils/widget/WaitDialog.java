package com.bitliker.core.bitutils.widget;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Window;

import com.bitliker.core.bitutils.R;


/**
 * Created by Bitlike on 2018/1/25.
 */

public abstract class WaitDialog extends AppCompatDialog {

    private final ProgressView mProgressView;

    public WaitDialog(Context context) {
        this(context, R.style.LoadDialogStyle);

    }

    public WaitDialog(Context context, int theme) {
        super(context, theme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressView = getProgressView();
        setContentView(mProgressView);
    }

    protected abstract ProgressView getProgressView();


    private boolean cancelable;
    private CharSequence content;

    public final void show(boolean cancelable, CharSequence content) {
        setWaitCancelable(cancelable);
        show();
        mProgressView.startAnimation(content);
        this.cancelable = cancelable;
        this.content = content;
    }


    private void setWaitCancelable(boolean cancelable) {
        setCancelable(cancelable);
        setCanceledOnTouchOutside(cancelable);
    }

    public final boolean getCancelable() {
        return cancelable;
    }

    public final CharSequence getContent() {
        return content;
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }
}
