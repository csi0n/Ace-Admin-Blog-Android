package com.csi0n.blog.ui.fragment;

import com.csi0n.blog.R;
import com.csi0n.blog.ui.base.mvp.MvpFragment;

/**
 * Created by csi0n on 10/12/16.
 */

public class HomePageFragment extends MvpFragment<HomePagePresenter, HomePagePresenter.IHomePage> implements HomePagePresenter.IHomePage {
    @Override
    protected int getFragmentLayout() {
        return R.layout.frag_home_page;
    }
}
