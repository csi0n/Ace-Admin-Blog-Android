package com.csi0n.blog.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.csi0n.blog.business.callback.AdvancedSubscriber;
import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.business.pojo.response.GetTagIndexResponse;
import com.csi0n.blog.business.pojo.response.GetTagShowResponse;
import com.csi0n.blog.ui.base.mvp.BaseMvpPresenter;
import com.csi0n.blog.ui.base.mvp.IMvpView;
import com.google.inject.Inject;

/**
 * Created by csi0n on 10/12/16.
 */

public class TagShowPresenter extends BaseMvpPresenter<TagShowPresenter.ITagShow> {
    @Inject
    BlogDomain blogDomain;

    public void init(TagShowFragment.TagShowExtraParam extraParam) {
        doGetTagShow(extraParam.tag.id);
    }

    public void onRefresh(int id) {
        doGetTagShow(id);
    }

    public void doGetTagShow(int id) {
        blogDomain.GetTagShow(id).subscribe(new AdvancedSubscriber<GetTagShowResponse>() {
            @Override
            public void onHandleSuccess(GetTagShowResponse response) {
                super.onHandleSuccess(response);
                view.onGetArticles(response);
            }
        });
    }

    public interface ITagShow extends IMvpView, SwipeRefreshLayout.OnRefreshListener {
        void onGetArticles(GetTagShowResponse getTagShowResponse);
    }
}
