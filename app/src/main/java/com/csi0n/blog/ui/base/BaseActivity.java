package com.csi0n.blog.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.csi0n.blog.core.system.AppManager;
import com.csi0n.blog.ui.base.mvp.ILoadDataView;

import butterknife.ButterKnife;

/**
 * Created by csi0n on 10/9/16.
 */

public abstract class BaseActivity extends AppCompatActivity implements ILoadDataView {
    protected abstract int GetConLayout();

    protected Handler uiHandler;

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

    private void startActivity(Context context, Class<?> classz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, classz);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
