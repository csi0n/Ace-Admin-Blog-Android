package com.csi0n.blog.ui;


import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.csi0n.blog.R;
import com.csi0n.blog.ui.base.common.FragmentLauncher;
import com.csi0n.blog.ui.base.mvp.MvpActivity;
import com.csi0n.blog.ui.fragment.ArticleSearchResultFragment;
import com.csi0n.blog.ui.fragment.HomeFragment;
import com.csi0n.blog.ui.fragment.TagFragment;

import java.util.ArrayList;

import br.com.mauker.materialsearchview.MaterialSearchView;
import butterknife.Bind;

public class MainActivity extends MvpActivity<MainPresenter, MainPresenter.IMain> implements MainPresenter.IMain {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.search_view)
    MaterialSearchView searchView;
    private HomeFragment homeFragment;

    @Override
    protected int GetConLayout() {
        return R.layout.aty_main;
    }

    @Override
    public void afterMvpInit(Bundle savedInstanceState) {
        super.afterMvpInit(savedInstanceState);
        setSupportActionBar(toolbar);
        initFragment(savedInstanceState);
        searchView.setOnQueryTextListener(onQueryTextListener);
        searchView.setSearchViewListener(searchViewListener);
        searchView.setOnItemClickListener(onItemClickListener);
        searchView.adjustTintAlpha(0.8f);

    }

    private MaterialSearchView.OnQueryTextListener onQueryTextListener = new MaterialSearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            startShowSearchResult(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private MaterialSearchView.SearchViewListener searchViewListener = new MaterialSearchView.SearchViewListener() {
        @Override
        public void onSearchViewOpened() {

        }

        @Override
        public void onSearchViewClosed() {

        }
    };
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String suggestion = searchView.getSuggestionAtPosition(i);
            searchView.setQuery(suggestion, false);
            startShowSearchResult(suggestion);
        }
    };

    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("homeFragment");
        } else {
            homeFragment = new HomeFragment();
            transaction.add(R.id.fl_body, homeFragment, "homeFragment");
        }
        transaction.commit();
        currentFragment(currentTabPosition);
    }

    private void currentFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.show(homeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.hide(homeFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                searchView.openSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (searchView.isOpen())
            searchView.closeSearch();
        else
            super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        searchView.clearSuggestions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchView.activityResumed();

    }

    public void startShowSearchResult(String key) {
        ArticleSearchResultFragment.ArticleSearchResultExtraParam extraParam = new ArticleSearchResultFragment.ArticleSearchResultExtraParam();
        extraParam.setFragmentClass(ArticleSearchResultFragment.class);
        extraParam.key = key;
        FragmentLauncher.launch(this, extraParam);
    }
}
