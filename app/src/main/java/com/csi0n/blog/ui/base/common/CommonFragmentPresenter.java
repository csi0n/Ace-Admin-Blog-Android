package com.csi0n.blog.ui.base.common;

import android.os.Bundle;

import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.ui.base.mvp.BaseMvpPresenter;
import com.csi0n.blog.ui.base.mvp.IMvpView;
import com.csi0n.blog.ui.base.mvp.MvpFragment;
import com.google.inject.Inject;

/**
 * Created by chqss on 2016/10/15.
 */

public class CommonFragmentPresenter extends BaseMvpPresenter<CommonFragmentPresenter.ICommonFragment> {
    @Inject
    BlogDomain blogDomain;

    public void init(MvpFragment mvpFragment){
        view.startFragment(mvpFragment);
    }
    public interface ICommonFragment extends IMvpView{
        void startFragment(MvpFragment mvpFragment);
    }
}
