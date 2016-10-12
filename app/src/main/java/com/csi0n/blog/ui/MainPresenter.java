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

public class MainPresenter extends BaseMvpPresenter<MainPresenter.IMain> {
    @Inject
    BlogDomain blogDomain;

    public interface IMain extends IMvpView {
    }
}
