package com.bitliker.ui.bitdialog.common.listener;

import android.view.View;
import android.widget.EditText;

public interface InputWidgetListener extends WidgetListener {
    boolean onClick(View v, EditText ed);
}
