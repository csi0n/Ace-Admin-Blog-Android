package com.csi0n.blog.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.csi0n.blog.R;
import com.csi0n.blog.ui.base.mvp.MvpActivity;
import com.csi0n.blog.ui.fragment.HomeFragment;

public class MainActivity extends MvpActivity<MainPresenter, MainPresenter.IMain> implements MainPresenter.IMain {

    private HomeFragment homeFragment;

    @Override
    protected int GetConLayout() {
        return R.layout.aty_main;
    }

    @Override
    public void afterMvpInit(Bundle savedInstanceState) {
        super.afterMvpInit(savedInstanceState);
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("homeFragment");
        } else {
            homeFragment = new HomeFragment();
            transaction.add(R.id.fl_body, homeFragment, "homeFragment");
        }
        transaction.commit();
        currentFragment(currentTabPosition);
    }

    private void currentFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.show(homeFragment);
                transaction.commitAllowingStateLoss();
                break;
        }
    }
}
