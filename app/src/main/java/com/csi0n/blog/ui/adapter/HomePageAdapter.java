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
import com.csi0n.blog.ui.fragment.HomePageFragment;
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

public class HomePageAdapter extends RecyclerView.Adapter {
    HomePageFragment mContext;

    public HomePageAdapter(HomePageFragment mContext) {
        this.mContext = mContext;
        articles = new ArrayList<>();
    }

    List<Article> articles;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return new ViewHolder(layoutInflater.inflate(R.layout.adapter_home_page_item, parent, false), mContext);
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

        public ViewHolder(View itemView, HomePageFragment mContext) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.mContext = mContext;
        }

        void bind(Article article) {
            itemView.setTag(article);
            tvTitle.setText(article.title);
            tvContent.setText(article.content);
            if (!article.thumb.isEmpty()) {
                Picasso.with(icon.getContext())
                        .load(Constants.BaseImgUrl + article.thumb)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(icon);
            }
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
            mContext.startDetail(getAdapterPosition());
        }
    }
}
