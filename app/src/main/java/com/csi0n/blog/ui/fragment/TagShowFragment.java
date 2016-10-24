package com.csi0n.blog.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.csi0n.blog.R;
import com.csi0n.blog.business.pojo.model.Tag;
import com.csi0n.blog.business.pojo.response.GetTagShowResponse;
import com.csi0n.blog.ui.adapter.ArticleListAdapter;
import com.csi0n.blog.ui.base.common.CommonExtraParam;
import com.csi0n.blog.ui.base.common.CommonMvpFragment;
import com.csi0n.blog.ui.base.common.FragmentLauncher;

import butterknife.Bind;

/**
 * Created by csi0n on 10/24/16.
 */

public class TagShowFragment extends CommonMvpFragment<TagShowPresenter, TagShowPresenter.ITagShow> implements TagShowPresenter.ITagShow {
    TagShowExtraParam extraParam;

    ArticleListAdapter adapter;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    GetTagShowResponse response;

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
        presenter.init(extraParam);
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh(extraParam.tag.id);
    }

    public static class TagShowExtraParam extends CommonExtraParam {
        public Tag tag;


        @Override
        public String toString() {
            return "TagShowExtraParam{" +
                    "tag=" + tag +
                    '}';
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.frag_sr_and_rv;
    }

    public void startDetail(int position) {
        HomePageDetailFragment.HomePageDetailExtraParam extraParam = new HomePageDetailFragment.HomePageDetailExtraParam();
        extraParam.setFragmentClass(HomePageDetailFragment.class);
        extraParam.article = response.articles[position];
        FragmentLauncher.launch(getContext(), extraParam);
    }

    @Override
    public void onGetArticles(final GetTagShowResponse getTagShowResponse) {
        response = getTagShowResponse;
        mvpActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                adapter.notifyDataSetChanged(getTagShowResponse.articles);
            }
        });
    }
}
