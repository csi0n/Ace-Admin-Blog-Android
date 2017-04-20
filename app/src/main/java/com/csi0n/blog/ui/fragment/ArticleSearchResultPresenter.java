package com.csi0n.blog.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;

import com.csi0n.blog.business.callback.AdvancedSubscriber;
import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.business.pojo.response.GetArticleSearchResponse;
import com.csi0n.blog.ui.base.mvp.BaseMvpPresenter;
import com.csi0n.blog.ui.base.mvp.IMvpView;
import com.google.inject.Inject;

/**
 * Created by csi0n on 10/12/16.
 */

public class ArticleSearchResultPresenter extends BaseMvpPresenter<ArticleSearchResultPresenter.IArticleSearchResult> {
    @Inject
    BlogDomain blogDomain;

    private String key;

    public void init(String key) {
        this.key = key;
        doGetSearchResult();
    }

    public void onRefresh() {
        doGetSearchResult();
    }

    public void doGetSearchResult() {
        blogDomain.GetArticleSearch(key).subscribe(new AdvancedSubscriber<GetArticleSearchResponse>() {
            @Override
            public void onHandleSuccess(GetArticleSearchResponse response) {
                super.onHandleSuccess(response);
                view.onGetSearchResult(response);
            }
        });
    }

    public interface IArticleSearchResult extends IMvpView, SwipeRefreshLayout.OnRefreshListener {
        void onGetSearchResult(GetArticleSearchResponse getArticleSearchResponse);
    }
}
