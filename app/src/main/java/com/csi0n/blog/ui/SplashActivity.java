package com.csi0n.blog.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.csi0n.blog.R;
import com.csi0n.blog.ui.base.mvp.MvpActivity;

/**
 * Created by csi0n on 10/10/16.
 */

public class SplashActivity extends MvpActivity<SplashPresenter, SplashPresenter.ISplash> implements SplashPresenter.ISplash {
    @Override
    protected int GetConLayout() {
        return R.layout.aty_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.init();
    }

    @Override
    public void start() {
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                skipActivity(MainActivity.class);
            }
        }, 3000);
    }
}
