package com.csi0n.blog.core.io;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by csi0n on 10/9/16.
 */

public class SharePreferenceManager {
    static SharedPreferences sp;
    public static void init(Context context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }
}
