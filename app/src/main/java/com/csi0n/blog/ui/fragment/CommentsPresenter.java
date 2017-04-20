package com.csi0n.blog.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.csi0n.blog.business.callback.AdvancedSubscriber;
import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.business.pojo.model.Article;
import com.csi0n.blog.business.pojo.response.GetCommentListsResponse;
import com.csi0n.blog.ui.base.mvp.BaseMvpPresenter;
import com.csi0n.blog.ui.base.mvp.IMvpView;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csi0n on 10/12/16.
 */

public class CommentsPresenter extends BaseMvpPresenter<CommentsPresenter.IComments> {
    @Inject
    BlogDomain blogDomain;

    CommentsFragment.CommentsExtraParam extraParam;

    public void init(CommentsFragment.CommentsExtraParam extraParam) {
        this.extraParam = extraParam;
        doGetComments(extraParam.article);
    }

    public void onRefresh() {
        doGetComments(extraParam.article);
    }

    public void doGetComments(Article article) {
        blogDomain.GetCommentsLists(article.id).subscribe(new AdvancedSubscriber<GetCommentListsResponse>() {
            @Override
            public void onHandleSuccess(GetCommentListsResponse response) {
                super.onHandleSuccess(response);
                view.onGetComments(response);
            }
        });
    }

    public interface IComments extends IMvpView , SwipeRefreshLayout.OnRefreshListener {
        void onGetComments(GetCommentListsResponse commentListsResponse);
    }
}
