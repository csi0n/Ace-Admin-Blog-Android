package com.csi0n.blog.ui;

import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.business.pojo.model.Article;
import com.csi0n.blog.ui.base.mvp.BaseMvpPresenter;
import com.csi0n.blog.ui.base.mvp.IMvpView;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csi0n on 10/12/16.
 */

public class HomePageDetailPresenter extends BaseMvpPresenter<HomePageDetailPresenter.IHomePageDetail> {
    @Inject
    BlogDomain blogDomain;

    public void init(Article article) {
        view.initView();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < article.tags.length; i++) {
            strings.add(article.tags[i].name);
        }
        view.initTagGroup(strings);
        view.initData(article);
    }

    public interface IHomePageDetail extends IMvpView {
        void initView();

        void initTagGroup(List<String> strings);

        void initData(Article article);
    }
}
