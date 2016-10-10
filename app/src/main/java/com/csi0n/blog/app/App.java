package com.csi0n.blog.app;

import android.app.Application;

import com.csi0n.blog.core.string.Constants;
import com.csi0n.blog.core.io.SharePreferenceManager;

/**
 * Created by csi0n on 10/9/16.
 */

public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SharePreferenceManager.init(this, Constants.Preference_Name);
        initInject();
    }

    public void initInject() {
        InjectHelp.init(getInstance());
    }
}
