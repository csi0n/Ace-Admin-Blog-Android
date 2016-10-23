package com.csi0n.blog.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi0n.blog.R;
import com.csi0n.blog.business.pojo.model.Article;
import com.csi0n.blog.core.string.Constants;
import com.csi0n.blog.core.string.HtmlUtil;
import com.csi0n.blog.ui.base.common.CommonExtraParam;
import com.csi0n.blog.ui.base.common.CommonMvpFragment;
import com.csi0n.blog.ui.widget.BasePicCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import me.gujun.android.taggroup.TagGroup;

/**
 * Created by chqss on 2016/10/15.
 */

public class HomePageDetailFragment extends CommonMvpFragment<HomePageDetailPresenter,HomePageDetailPresenter.IHomePageDetail> implements HomePageDetailPresenter.IHomePageDetail {
    @Override
    protected void init(Bundle savedInstanceState) {
        extraParam=getReqExtraParam();
        presenter.init(extraParam.article);
    }

    public static class HomePageDetailExtraParam extends CommonExtraParam{
        public Article article;

        @Override
        public String toString() {
            return "HomePageDetailExtraParam{" +
                    "article=" + article +
                    '}';
        }
    }
    @Bind(R.id.iv_header)
    ImageView ivHeader;
    @Bind(R.id.tv_source)
    TextView tvSource;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.wv_news)
    WebView wvNews;
    @Bind(R.id.nested_view)
    NestedScrollView nestedView;
    @Bind(R.id.tag_group)
    TagGroup tagGroup;
    @Bind(R.id.cv_tag)
    CardView cvTag;

    HomePageDetailExtraParam extraParam;
    @Override
    protected int getFragmentLayout() {
        return R.layout.frag_home_page_detail;
    }


    @Override
    public void initView() {
        mvpActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mvpActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //setHasOptionsMenu(true);
        //mNestedScrollView.setElevation(0);
        //mWvNews.setElevation(0);
        wvNews.getSettings().setLoadsImagesAutomatically(true);
        //设置 缓存模式
        wvNews.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        wvNews.getSettings().setDomStorageEnabled(true);
        collapsingToolbarLayout.setTitle(extraParam.article.title);
    }

    @Override
    public void initTagGroup(List<String> strings) {
        if (strings != null && strings.size() == 0) {
            cvTag.setVisibility(View.GONE);
        } else {
            tagGroup.setTags(strings);
        }
    }

    @Override
    public void initData(Article article) {
        Picasso.with(mvpActivity)
                .load(Constants.BaseImgUrl + extraParam.article.thumb)
                .placeholder(R.mipmap.ic_launcher)
                .into(ivHeader, new BasePicCallback(ivHeader,R.mipmap.bg));
        String html=HtmlUtil.createHtmlData(extraParam.article.content,extraParam.article.css);
        wvNews.loadData(html, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }
}
