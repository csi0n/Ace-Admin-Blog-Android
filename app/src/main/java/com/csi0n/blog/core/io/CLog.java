package com.csi0n.blog.core.io;

import android.util.Log;

/**
 * Created by csi0n on 10/10/16.
 */

public class CLog {
    static final String IBLOG="IBlog";
    public static int v(String msg, Object... args) {
        if (args != null && args.length != 0) {
            msg = String.format(msg, args);
        }
        return Log.v(IBLOG, msg);
    }

    public static int d(String msg, Object... args) {
        if (args != null && args.length != 0) {
            msg = String.format(msg, args);
        }
        return Log.d(IBLOG, msg);
    }

    public static int i(String msg, Object... args) {
        if (args != null && args.length != 0) {
            msg = String.format(msg, args);
        }
        return Log.i(IBLOG, msg);
    }

    public static int w(String msg, Object... args) {
        if (args != null && args.length != 0) {
            msg = String.format(msg, args);
        }
        return Log.w(IBLOG, msg);
    }

    public static int w(Throwable tr) {
        return Log.w(IBLOG, tr);
    }

    public static int e(String msg, Object... args) {
        if (args != null && args.length != 0) {
            msg = String.format(msg, args);
        }
        return Log.e(IBLOG, msg);
    }

    public static int e(String msg, Throwable tr) {
        return Log.e(IBLOG, msg, tr);
    }
}
