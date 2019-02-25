package com.bitliker.ui.bitdialog.common.listener;

import android.view.View;
import android.widget.EditText;

/**
 * 输入按钮事件监听，仅限在输入框下使用
 */
public interface InputWidgetListener extends WidgetListener {
    boolean onClick(View v, EditText ed);
}
