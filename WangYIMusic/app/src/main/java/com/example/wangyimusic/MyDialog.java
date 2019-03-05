package com.example.wangyimusic;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MyDialog extends AlertDialog {
    protected MyDialog(Context context) {
        super(context,R.style.quick_option_dialog);
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = 1000;
        getWindow().setAttributes(layoutParams);
        getWindow().setWindowAnimations(R.style.Myanimation);
    }
}
