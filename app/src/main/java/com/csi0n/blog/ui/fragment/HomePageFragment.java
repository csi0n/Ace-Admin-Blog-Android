package com.csi0n.blog.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.csi0n.blog.R;
import com.csi0n.blog.business.pojo.model.Cate;
import com.csi0n.blog.core.io.CLog;
import com.csi0n.blog.core.string.Constants;
import com.csi0n.blog.ui.adapter.ArticleListAdapter;
import com.csi0n.blog.ui.base.common.FragmentLauncher;
import com.csi0n.blog.ui.base.mvp.MvpFragment;

import butterknife.Bind;

/**
 * Created by csi0n on 10/12/16.
 */

public class HomePageFragment extends MvpFragment<HomePagePresenter, HomePagePresenter.IHomePage> implements HomePagePresenter.IHomePage {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    ArticleListAdapter adapter;

    Cate currentCate;

    public static HomePageFragment newInstance(Cate cate) {

        Bundle args = new Bundle();
        args.putSerializable(Constants.KEY_CATE, cate);
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.frag_home_page;
    }

    @Override
    protected void afterMvpInit(View view, Bundle savedInstanceState) {
        super.afterMvpInit(view, savedInstanceState);
        currentCate = (Cate) getArguments().getSerializable(Constants.KEY_CATE);
        if (currentCate == null)
            return;
        adapter = new ArticleListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);


        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged(currentCate.articles);
    }

    public void startDetail(int position) {
        HomePageDetailFragment.HomePageDetailExtraParam extraParam = new HomePageDetailFragment.HomePageDetailExtraParam();
        extraParam.setFragmentClass(HomePageDetailFragment.class);
        extraParam.article = currentCate.articles[position];
        FragmentLauncher.launch(getContext(), extraParam);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged(currentCate.articles);
    }
}
