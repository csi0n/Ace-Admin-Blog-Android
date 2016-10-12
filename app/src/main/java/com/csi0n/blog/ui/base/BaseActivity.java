package com.csi0n.blog.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.csi0n.blog.R;
import com.csi0n.blog.app.App;
import com.csi0n.blog.core.system.AppManager;
import com.csi0n.blog.ui.base.mvp.ILoadDataView;
import com.csi0n.blog.ui.widget.ProgressLoading;

import butterknife.ButterKnife;
import roboguice.activity.RoboActivity;
import roboguice.activity.RoboFragmentActivity;

/**
 * Created by csi0n on 10/9/16.
 */

public abstract class BaseActivity extends RoboFragmentActivity implements ILoadDataView {
    protected abstract int GetConLayout();

    protected Handler uiHandler;

    protected ProgressLoading loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(GetConLayout());
        ButterKnife.bind(this);
        uiHandler = new Handler(getMainLooper());
        AppManager.getAppManager().addActivity(this);
    }

    public void skipActivity(Class<?> classz) {
        startActivity(this, classz, null);
        finish();
    }

    public void startActivity(Class<?> classz) {
        startActivity(this, classz, null);
    }

    private void startActivity(Context context, Class<?> classz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, classz);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {
        loading = new ProgressLoading(this, R.string.loading);
        loading.show();
    }

    @Override
    public void hideLoading() {
        if (loading != null && loading.isShowing())
            loading.dismiss();
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }


    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int str) {
        showToast(App.getInstance().getString(str));
    }
}
