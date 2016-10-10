package com.csi0n.blog.ui;

import android.content.Context;

import com.csi0n.blog.R;
import com.csi0n.blog.ui.base.mvp.MvpActivity;

/**
 * Created by csi0n on 10/10/16.
 */

public class SplashActivity extends MvpActivity {
    @Override
    protected int GetConLayout() {
        return R.layout.aty_splash;
    }

    @Override
    public void afterMvpInit() {
        super.afterMvpInit();
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                skipActivity(MainActivity.class);
            }
        }, 3000);
    }
}
