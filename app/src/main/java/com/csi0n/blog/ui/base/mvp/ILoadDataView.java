package com.csi0n.blog.ui.base.mvp;

/**
 * Created by csi0n on 10/9/16.
 */

public interface ILoadDataView extends IView{
    void showLoading();
    void hideLoading();
    void showError(String message);
}
