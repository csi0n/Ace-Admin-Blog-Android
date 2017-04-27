package com.csi0n.blog.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.csi0n.blog.R;

/**
 * Created by csi0n on 27/04/2017.
 */

public class RecyclerViewWithEmpty extends RecyclerView {
    private Context mContext;
    private View view;

    public RecyclerViewWithEmpty(Context context) {
        super(context);
        init(context);
    }

    public RecyclerViewWithEmpty(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RecyclerViewWithEmpty(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(adapterDataObserver);
        }
        adapterDataObserver.onChanged();
    }

    private void init(Context mContext) {
        this.mContext = mContext;
        view = View.inflate(mContext, R.layout.view_recyclerview_with_empty, null);
    }

    private AdapterDataObserver adapterDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (adapter != null && view != null) {
                if (adapter.getItemCount() == 0) {
                    view.setVisibility(VISIBLE);
                    setVisibility(GONE);
                    Log.d("blog----", "onChanged: 0");
                } else {
                    view.setVisibility(GONE);
                    setVisibility(VISIBLE);
                    Log.d("blog----", "onChanged: 1");
                }
            }
        }
    };
}
