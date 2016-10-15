package com.csi0n.blog.ui.base.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.csi0n.blog.core.string.Constants;
import com.csi0n.blog.ui.base.mvp.MvpActivity;
import com.csi0n.blog.ui.base.mvp.MvpFragment;

/**
 * Created by chqss on 2016/10/15.
 */

public class FragmentLaunch {
    public static final void launch(MvpActivity activity, MvpFragment mvpFragment ) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_COMMON_FRAGMENT_ACTIVITY, mvpFragment);
        activity.startActivity(CommonFragmentActivity.class, bundle);
    }
}
