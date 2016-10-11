package com.csi0n.blog.ui;

import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.ui.base.mvp.BaseMvpPresenter;
import com.csi0n.blog.ui.base.mvp.IMvpView;
import com.google.inject.Inject;

import rx.Observable;

/**
 * Created by csi0n on 10/11/16.
 */

public class SplashPresenter extends BaseMvpPresenter<SplashPresenter.ISplash> {
    @Inject
    BlogDomain blogDomain;

    public interface ISplash extends IMvpView {
        void start();
    }
}
