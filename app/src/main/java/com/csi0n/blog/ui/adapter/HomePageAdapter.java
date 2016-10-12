package com.csi0n.blog.ui.adapter;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chqss on 2016/10/12.
 */

public class HomePageAdapter extends RecyclerView.Adapter {
    Context mContext;

    public HomePageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    List<Article> articles;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return new ViewHolder(layoutInflater.inflate(R.layout.adapter_home_page_item, parent, false));
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

        @Bind(R.id.text)
        TextView textView;

        @Bind(R.id.icon)
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bind(Article article) {
            itemView.setTag(article);
            textView.setText(article.title);
            if (!article.thumb.isEmpty()) {
                Picasso.with(icon.getContext())
                        .load(article.thumb)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(icon);
            }
        }

        @Override
        public void onClick(View view) {

        }
    }
}
