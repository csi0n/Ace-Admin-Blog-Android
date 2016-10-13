package com.csi0n.blog.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csi0n.blog.R;
import com.csi0n.blog.business.pojo.model.Cate;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.core.io.CLog;
import com.csi0n.blog.core.my.MyUtils;
import com.csi0n.blog.core.string.Constants;
import com.csi0n.blog.ui.adapter.BaseFragmentAdapter;
import com.csi0n.blog.ui.base.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by csi0n on 10/12/16.
 */

public class HomeFragment extends MvpFragment<HomePresenter, HomePresenter.IHome> implements HomePresenter.IHome {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private BaseFragmentAdapter fragmentAdapter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.frag_home;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.doGetHomeIndex(mvpActivity);
    }

    @Override
    public void onGetHome(GetHomeIndexResponse homeIndexResponse) {
        if (homeIndexResponse != null) {
            List<String> names = new ArrayList<>();
            List<Fragment> mFragments = new ArrayList<>();
            for (int i = 0; i < homeIndexResponse.cates.length; i++) {
                names.add(homeIndexResponse.cates[i].name);
                mFragments.add(createListFragments(homeIndexResponse.cates[i]));
            }
            fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mFragments, names);
            viewPager.setAdapter(fragmentAdapter);
            tabs.setupWithViewPager(viewPager);
            MyUtils.dynamicSetTabLayoutMode(tabs);
            setPageChangeListener();
        }
    }

    private void setPageChangeListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private HomePageFragment createListFragments(Cate cate) {
        HomePageFragment fragment = HomePageFragment.newInstance(cate);
        return fragment;
    }
}
