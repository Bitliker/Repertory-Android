package com.bitliker.ui.bitdialog;


import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.bitliker.ui.bitdialog.input.InputDialogBuilder;
import com.bitliker.ui.bitdialog.list.ListDialogBuilder;
import com.bitliker.ui.bitdialog.prompt.PromptDialogBuilder;

/**
 * 创建窗体入口
 */
public class BitDialog {


    public static PromptDialogBuilder createPrompt(FragmentActivity ct) {
        return new PromptDialogBuilder(ct);
    }

    public static InputDialogBuilder createInput(FragmentActivity ct) {
        return new InputDialogBuilder(ct);
    }

    public static ListDialogBuilder createList(FragmentActivity ct) {
        return new ListDialogBuilder(ct);
    }

}
