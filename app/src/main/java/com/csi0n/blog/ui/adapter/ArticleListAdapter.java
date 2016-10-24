package com.csi0n.blog.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi0n.blog.R;
import com.csi0n.blog.business.pojo.model.Article;
import com.csi0n.blog.business.pojo.model.Cate;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.core.string.Constants;
import com.csi0n.blog.ui.fragment.ArticleSearchResultFragment;
import com.csi0n.blog.ui.fragment.HomePageFragment;
import com.csi0n.blog.ui.fragment.TagShowFragment;
import com.csi0n.blog.ui.widget.BasePicCallback;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;

/**
 * Created by chqss on 2016/10/12.
 */

public class ArticleListAdapter extends RecyclerView.Adapter {
    HomePageFragment mContext;

    TagShowFragment mShow;

    ArticleSearchResultFragment mArticle;

    public ArticleListAdapter(HomePageFragment mContext) {
        this.mContext = mContext;
        articles = new ArrayList<>();
    }

    public ArticleListAdapter(TagShowFragment mContext) {
        this.mShow = mContext;
        articles = new ArrayList<>();
    }

    public ArticleListAdapter(ArticleSearchResultFragment articleSearchResultFragment) {
        this.mArticle = articleSearchResultFragment;
        articles = new ArrayList<>();
    }

    List<Article> articles;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext != null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return new ViewHolder(layoutInflater.inflate(R.layout.adapter_home_page_item, parent, false), mContext);
        } else if (mShow != null) {
            LayoutInflater layoutInflater = (LayoutInflater) mShow.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return new ViewHolder(layoutInflater.inflate(R.layout.adapter_home_page_item, parent, false), mShow);
        } else if (mArticle != null) {
            LayoutInflater layoutInflater = (LayoutInflater) mArticle.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return new ViewHolder(layoutInflater.inflate(R.layout.adapter_home_page_item, parent, false), mArticle);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder item = (ViewHolder) holder;
            item.bind(articles.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void notifyDataSetChanged(Article[] arts) {
        articles.clear();
        articles.addAll(Arrays.asList(arts));
        super.notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.tv_title)
        TextView tvTitle;

        @Bind(R.id.tv_content)
        TextView tvContent;

        @Bind(R.id.tag_group)
        TagGroup tagGroup;

        @Bind(R.id.iv_icon)
        ImageView icon;

        HomePageFragment mContext;

        TagShowFragment mShow;

        ArticleSearchResultFragment mArticle;

        public ViewHolder(View itemView, HomePageFragment mContext) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.mContext = mContext;
        }

        public ViewHolder(View itemView, TagShowFragment mShow) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.mShow = mShow;
        }

        public ViewHolder(View itemView, ArticleSearchResultFragment articleSearchResultFragment) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.mArticle = articleSearchResultFragment;
        }

        void bind(Article article) {
            itemView.setTag(article);
            tvTitle.setText(article.title);
            tvContent.setText(article.describe);
            Picasso.with(icon.getContext())
                    .load(Constants.BaseImgUrl + article.thumb)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(icon, new BasePicCallback(icon, R.mipmap.bg));
            if (article.tags != null) {
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < article.tags.length; i++) {
                    strings.add(article.tags[i].name);
                }
                tagGroup.setTags(strings);
            }
        }

        @Override
        public void onClick(View view) {
            if (mContext != null)
                mContext.startDetail(getAdapterPosition());
            else if (mShow != null) {
                mShow.startDetail(getAdapterPosition());
            } else if (mArticle != null) {
                mArticle.startDetail(getAdapterPosition());
            }
        }
    }
}
