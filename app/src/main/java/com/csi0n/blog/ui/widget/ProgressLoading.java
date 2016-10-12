package com.csi0n.blog.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.csi0n.blog.R;
import com.csi0n.blog.app.App;

/**
 * Created by csi0n on 10/11/16.
 */

public class ProgressLoading extends Dialog {
    TextView tvText;
    private String text;

    public ProgressLoading(Context context, String text) {
        super(context, R.style.ProgressLoadingDialog);
        this.text = text;
    }

    public ProgressLoading(Context context, int str) {
        super(context, R.style.ProgressLoadingDialog);
        this.text = App.getInstance().getString(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_progress_loading_dialog);
        tvText = (TextView) findViewById(R.id.tvText);
        init();
    }

    public void init() {
        tvText.setText(text.isEmpty() ? "请稍后..." : text);
    }
}
