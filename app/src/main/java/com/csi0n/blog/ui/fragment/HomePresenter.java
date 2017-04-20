package com.csi0n.blog.ui.fragment;

import com.csi0n.blog.business.api.BlogApi;
import com.csi0n.blog.business.callback.AdvancedSubscriber;
import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.ui.base.mvp.BaseMvpPresenter;
import com.csi0n.blog.ui.base.mvp.ILoadDataView;
import com.csi0n.blog.ui.base.mvp.IMvpView;
import com.csi0n.blog.ui.base.mvp.MvpFragment;
import com.google.inject.Inject;

import rx.Observable;

/**
 * Created by csi0n on 10/12/16.
 */

public class HomePresenter extends BaseMvpPresenter<HomePresenter.IHome> {
    @Inject
    BlogDomain blogDomain;

    public void doGetHomeIndex(ILoadDataView iLoadDataView) {
        doGetHomeIndex().subscribe(new AdvancedSubscriber<GetHomeIndexResponse>(iLoadDataView) {
            @Override
            public void onHandleSuccess(GetHomeIndexResponse response) {
                super.onHandleSuccess(response);
                view.onGetHome(response);
            }
        });
    }

    public Observable<GetHomeIndexResponse> doGetHomeIndex() {
        return blogDomain.GetHomeIndex();
    }

    public interface IHome extends IMvpView {
        void onGetHome(GetHomeIndexResponse homeIndexResponse);
    }
}
