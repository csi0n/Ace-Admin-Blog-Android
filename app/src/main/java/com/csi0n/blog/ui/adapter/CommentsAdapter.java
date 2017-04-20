package com.csi0n.blog.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi0n.blog.R;
import com.csi0n.blog.business.pojo.model.Comment;
import com.csi0n.blog.ui.widget.RoundImageView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by csi0n on 10/28/16.
 */

public class CommentsAdapter extends RecyclerView.Adapter {

    Context mContext;

    ArrayList<Comment> comments;


    public CommentsAdapter(Context mContext) {
        this.mContext = mContext;
        comments = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ViewHolder(layoutInflater.inflate(R.layout.adapter_comment_item, parent, false), mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder item = (ViewHolder) holder;
            item.bind(comments.get(position));
        }
    }

    public void notifyDataSetChanged(Comment[] arts) {
        comments.clear();
        comments.addAll(Arrays.asList(arts));
        super.notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        Context mContext;

        @Bind(R.id.iv_icon)
        RoundImageView ivIcon;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_content)
        TextView tvContent;

        public ViewHolder(View itemView, Context mContext) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.mContext = mContext;
        }

        void bind(Comment comment) {
            itemView.setTag(comment);
            tvTitle.setText(comment.authorName);
            tvContent.setText(comment.message);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


}
