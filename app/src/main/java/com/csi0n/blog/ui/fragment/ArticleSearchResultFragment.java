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
import com.csi0n.blog.business.pojo.response.GetArticleSearchResponse;
import com.csi0n.blog.ui.adapter.ArticleListAdapter;
import com.csi0n.blog.ui.base.common.CommonExtraParam;
import com.csi0n.blog.ui.base.common.CommonMvpFragment;
import com.csi0n.blog.ui.base.common.FragmentLauncher;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by csi0n on 10/24/16.
 */

public class ArticleSearchResultFragment extends CommonMvpFragment<ArticleSearchResultPresenter, ArticleSearchResultPresenter.IArticleSearchResult> implements ArticleSearchResultPresenter.IArticleSearchResult {
    ArticleSearchResultExtraParam extraParam;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    ArticleListAdapter adapter;

    GetArticleSearchResponse response;

    public static class ArticleSearchResultExtraParam extends CommonExtraParam {
        public String key;

        @Override
        public String toString() {
            return "ArticleSearchResultExtraParam{" +
                    "key='" + key + '\'' +
                    '}';
        }
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        extraParam = getReqExtraParam();

        mvpActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mvpActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        adapter = new ArticleListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(false);

        presenter.init(extraParam.key);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.frag_sr_and_rv;
    }

    @Override
    public void onGetSearchResult(final GetArticleSearchResponse getArticleSearchResponse) {
        response = getArticleSearchResponse;
        mvpActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                adapter.notifyDataSetChanged(getArticleSearchResponse.articles);
            }
        });
    }

    public void startDetail(int position) {
        HomePageDetailFragment.HomePageDetailExtraParam extraParam = new HomePageDetailFragment.HomePageDetailExtraParam();
        extraParam.setFragmentClass(HomePageDetailFragment.class);
        extraParam.article = response.articles[position];
        FragmentLauncher.launch(getContext(), extraParam);
    }
}
