package com.csi0n.blog.ui.fragment;

import android.os.Bundle;

import com.csi0n.blog.business.callback.AdvancedSubscriber;
import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.business.pojo.response.GetTagIndexResponse;
import com.csi0n.blog.ui.base.mvp.BaseMvpPresenter;
import com.csi0n.blog.ui.base.mvp.ILoadDataView;
import com.csi0n.blog.ui.base.mvp.IMvpView;
import com.google.inject.Inject;

import rx.Observable;

/**
 * Created by csi0n on 10/12/16.
 */

public class TagPresenter extends BaseMvpPresenter<TagPresenter.ITag> {
    @Inject
    BlogDomain blogDomain;

    @Override
    public void create(Bundle savedInstanceState) {
        super.create(savedInstanceState);
        doGetTagIndex();
    }

    public void doGetTagIndex() {
        blogDomain.GetTagIndex().subscribe(new AdvancedSubscriber<GetTagIndexResponse>() {
            @Override
            public void onHandleSuccess(GetTagIndexResponse response) {
                super.onHandleSuccess(response);
                view.onGetTag(response);
            }
        });
    }

    public interface ITag extends IMvpView {
        void onGetTag(GetTagIndexResponse getTagIndexResponse);
    }
}
