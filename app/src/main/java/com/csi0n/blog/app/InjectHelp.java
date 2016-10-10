package com.csi0n.blog.app;

import android.app.Application;

import roboguice.RoboGuice;

public class InjectHelp {

    static void init(Application application) {
        // 关闭 RoboGuice 3.+ 编译时生成代码功能。仅使用反射。
        RoboGuice.setUseAnnotationDatabases(false);
        RoboGuice.getOrCreateBaseApplicationInjector(
                application,
                RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(application),
                new BindImplModule()
        );
    }
    public static App getAppContext() {
        return App.getInstance();
    }

    public static void injectMembersWithoutViews(Object object) {
        RoboGuice.getInjector(getAppContext()).injectMembersWithoutViews(object);
    }
}