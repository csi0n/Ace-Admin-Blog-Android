package com.csi0n.blog.ui;

import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.ui.base.mvp.BaseMvpPresenter;
import com.csi0n.blog.ui.base.mvp.IMvpView;
import com.google.inject.Inject;

/**
 * Created by csi0n on 10/12/16.
 */

public class HomePageDetailPresenter extends BaseMvpPresenter<HomePageDetailPresenter.IHomePageDetail> {
    @Inject
    BlogDomain blogDomain;

    public void init(){
        view.initView();
        view.initData();
    }

    public interface IHomePageDetail extends IMvpView{
        void initView();
        void initData();
    }
}
