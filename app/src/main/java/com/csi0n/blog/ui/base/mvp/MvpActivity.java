package com.csi0n.blog.ui.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.csi0n.blog.ui.base.BaseActivity;

/**
 * Created by csi0n on 10/9/16.
 */

public abstract class MvpActivity<P extends BaseMvpPresenter<V>, V extends IMvpView> extends BaseActivity {
    protected P presenter;
    protected V view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeMvpInit(savedInstanceState);
        onMvpInit();
        if (presenter != null) {
            //注册Activity
            presenter.initMvpPresenter(this, view);
            presenter.registerEventBusListener(this);
            presenter.create(savedInstanceState);
            presenter.initInActivity(savedInstanceState, getIntent());
            afterMvpInit(savedInstanceState);
        }
    }

    public void afterMvpInit(Bundle savedInstanceState) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * 在初始化mvp前，做些事情
     *
     * @param savedInstanceState savedInstanceState
     */
    protected void beforeMvpInit(Bundle savedInstanceState) {

    }

    private void onMvpInit() {
        try {
            initPresenterAndView();
        } catch (Exception e) {
            // 防止子类未使用泛型所可能产生的意外错误
        }
    }

    /**
     * 通过反射获取{@link P}和{@link V}
     */
    protected void initPresenterAndView() {
        MvpHelper<P, V> mvpHelper = new MvpHelper<>(this);
        view = getViewInstance();
        Class<P> pClass = mvpHelper.getPresenterClass();
        if (pClass != null) {
            try {
                presenter = pClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 返回实现{@link V}的实例，默认是当前Activity
     *
     * @return {@link V}的实例
     */
    protected V getViewInstance() {
        try {
            Class<V> vClass = new MvpHelper<P, V>(this).getViewClass();
            if (vClass != null && vClass.isInstance(this)) {
                return (V) this;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
