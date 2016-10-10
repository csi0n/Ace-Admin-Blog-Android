package com.csi0n.blog.core.system;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import java.util.Stack;

/**
 * Created by csi0n on 10/10/16.
 */

public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if (instance == null)
            instance = new AppManager();
        return instance;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null)
            activityStack = new Stack<Activity>();
        activityStack.add(activity);
    }

    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++)
            if (null != activityStack.get(i))
                activityStack.get(i).finish();
        activityStack.clear();
    }
    public void restartApplication(Context context) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {

        }
    }
}
