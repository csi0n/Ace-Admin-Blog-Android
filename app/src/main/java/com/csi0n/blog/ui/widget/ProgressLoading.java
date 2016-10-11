package com.csi0n.blog.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.csi0n.blog.R;
import com.csi0n.blog.app.App;
import com.csi0n.blog.ui.widget.ext.BaseDialog;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by csi0n on 10/11/16.
 */

public class ProgressLoading extends BaseDialog {
    private String text;
    @BindView(R.id.tvText)
    private TextView tvText;

    public ProgressLoading(Context context, String text) {
        super(context, R.style.ProgressLoadingDialog);
        this.text = text;
    }

    public ProgressLoading(Context context, int str) {
        super(context, R.style.ProgressLoadingDialog);
        this.text = App.getInstance().getString(str);
    }

    @Override
    public int GetContView() {
        return R.layout.view_progress_loading_dialog;
    }

    @Override
    public void init() {
        tvText.setText(text);
    }
}
