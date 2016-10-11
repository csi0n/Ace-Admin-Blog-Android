package com.csi0n.blog.ui.widget.ext;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by csi0n on 10/11/16.
 */

public abstract class BaseDialog extends Dialog {

    public abstract int GetContView();

    public abstract void init();

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(GetContView());
        ButterKnife.bind(this);
        init();
    }
}
