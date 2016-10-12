package com.csi0n.blog.ui.fragment;

import com.csi0n.blog.business.callback.AdvancedSubscriber;
import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.ui.base.mvp.BaseMvpPresenter;
import com.csi0n.blog.ui.base.mvp.ILoadDataView;
import com.csi0n.blog.ui.base.mvp.IMvpView;
import com.google.inject.Inject;

/**
 * Created by csi0n on 10/12/16.
 */

public class HomePagePresenter extends BaseMvpPresenter<HomePagePresenter.IHomePage> {
    @Inject
    BlogDomain blogDomain;


    public interface IHomePage extends IMvpView {
    }
}
