package com.csi0n.blog.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csi0n.blog.R;
import com.csi0n.blog.business.pojo.model.Article;
import com.csi0n.blog.business.pojo.response.GetCommentListsResponse;
import com.csi0n.blog.ui.adapter.CommentsAdapter;
import com.csi0n.blog.ui.base.common.CommonExtraParam;
import com.csi0n.blog.ui.base.common.CommonMvpFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by csi0n on 10/28/16.
 */

public class CommentsFragment extends CommonMvpFragment<CommentsPresenter, CommentsPresenter.IComments> implements CommentsPresenter.IComments {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    CommentsAdapter adapter;

    @Override
    public void onGetComments(final GetCommentListsResponse commentListsResponse) {
        mvpActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged(commentListsResponse.comments);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    public static class CommentsExtraParam extends CommonExtraParam {
        public Article article;

        @Override
        public String toString() {
            return "CommentsExtraParam{" +
                    "article=" + article +
                    '}';
        }
    }

    CommentsExtraParam extraParam;

    @Override
    protected void init(Bundle savedInstanceState) {
        mvpActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mvpActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        extraParam = getReqExtraParam();


        adapter = new CommentsAdapter(mvpActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(false);
        presenter.init(extraParam);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.frag_sr_and_rv;
    }

}
