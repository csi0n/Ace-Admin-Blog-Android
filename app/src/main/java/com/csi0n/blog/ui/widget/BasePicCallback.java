package com.csi0n.blog.ui.widget;

import android.widget.ImageView;

import com.squareup.picasso.Callback;

/**
 * Created by chqss on 2016/10/22.
 */

public class BasePicCallback implements Callback {

    private ImageView imageView;
    private  int resource;

    public BasePicCallback(ImageView imageView, int resource) {
        this.imageView = imageView;
        this.resource = resource;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {
        imageView.setImageResource(resource);
    }
}
