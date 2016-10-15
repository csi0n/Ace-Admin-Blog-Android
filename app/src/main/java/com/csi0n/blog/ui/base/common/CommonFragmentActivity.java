package com.csi0n.blog.ui.base.common;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.csi0n.blog.R;
import com.csi0n.blog.core.string.Constants;
import com.csi0n.blog.ui.base.mvp.MvpActivity;
import com.csi0n.blog.ui.base.mvp.MvpFragment;

/**
 * Created by chqss on 2016/10/15.
 */

public class CommonFragmentActivity extends MvpActivity<CommonFragmentPresenter, CommonFragmentPresenter.ICommonFragment> implements CommonFragmentPresenter.ICommonFragment {

    MvpFragment currentFragment;

    @Override
    protected int GetConLayout() {
        return R.layout.aty_common_fragment;
    }

    @Override
    public void afterMvpInit(Bundle savedInstanceState) {
        super.afterMvpInit(savedInstanceState);
        currentFragment = (MvpFragment) savedInstanceState.getSerializable(Constants.KEY_COMMON_FRAGMENT_ACTIVITY);
        if (currentFragment == null) {
            return;
        }
        presenter.init(currentFragment);
    }

    @Override
    public void startFragment(MvpFragment mvpFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_body, mvpFragment);
        transaction.commit();
        transaction.show(mvpFragment);
    }
}
