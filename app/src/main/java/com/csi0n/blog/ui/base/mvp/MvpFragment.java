package com.csi0n.blog.ui.base.mvp;

import android.os.Bundle;
import android.view.View;


import com.csi0n.blog.app.App;
import com.csi0n.blog.core.io.CLog;
import com.csi0n.blog.ui.base.BaseFragment;

import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;

/**
 * Created by chqss on 2016/5/1 0001.
 */
public abstract class MvpFragment<P extends BaseMvpPresenter, V extends IMvpView> extends BaseFragment implements Serializable {
    protected MvpActivity mvpActivity;
    protected P presenter;
    protected V view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpActivity = (MvpActivity) getActivity();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        beforeMvpInit(savedInstanceState);
        onMvpInit();
        if (presenter != null) {
            //注册Fragment
            presenter.initMvpPresenter(mvpActivity, this.view);
            presenter.registerEventBusListener(this);
            presenter.create(savedInstanceState);
            presenter.initInFragment(savedInstanceState, getArguments());
        }
        afterMvpInit(view,savedInstanceState);
    }

    /**
     * 在初始化mvp前，做些事情
     *
     * @param savedInstanceState savedInstanceState
     */
    protected void beforeMvpInit(Bundle savedInstanceState) {

    }

    protected void afterMvpInit(View view,Bundle savedInstanceState){

    }

    private void onMvpInit() {
        try {
            initPresenterAndView();
        } catch (Exception e) {
            // 防止子类未使用泛型所可能产生的意外错误
            CLog.w("onMvpInit fail, e = " + e);
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
        CLog.d("view = " + view);
        CLog.d("presenter = " + presenter);
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
            CLog.w(e.toString());
        }
        return null;
    }

    @Subscribe
    public void onEvent(Object object) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.pause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.stop();
        }
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.unregisterEventBusListener(this);
            presenter.destroy();
        }
        mvpActivity = null;
        presenter = null;
        view = null;
        super.onDestroy();
    }

    public void startActivity(Class<?> classz) {
        mvpActivity.startActivity(classz);
    }

    public void startActivity(Class<?> classz,Bundle bundle){
        mvpActivity.startActivity(classz, bundle);
    }

    public void showError(String message) {
        mvpActivity.showError(message);
    }

    public void showToast(int str) {
        mvpActivity.showToast(str);
    }
}
