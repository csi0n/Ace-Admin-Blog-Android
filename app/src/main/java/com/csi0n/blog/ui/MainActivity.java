package com.csi0n.blog.ui;


import com.csi0n.blog.R;
import com.csi0n.blog.business.callback.AdvancedSubscriber;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.core.io.CLog;
import com.csi0n.blog.ui.base.mvp.MvpActivity;

public class MainActivity extends MvpActivity<MainPresenter, MainPresenter.IMain> implements MainPresenter.IMain {

    @Override
    protected int GetConLayout() {
        return R.layout.aty_main;
    }

    @Override
    public void afterMvpInit() {
        super.afterMvpInit();
        presenter.doGetHomeIndex().subscribe(new AdvancedSubscriber<GetHomeIndexResponse>(this) {
            @Override
            public void onHandleSuccess(GetHomeIndexResponse response) {
                super.onHandleSuccess(response);
                CLog.w("123", response);
            }
        });
    }

    @Override
    public void GetHomeIndex() {
        CLog.w("123", 456);
        presenter.doGetHomeIndex().subscribe(new AdvancedSubscriber<GetHomeIndexResponse>(this) {
            @Override
            public void onHandleSuccess(GetHomeIndexResponse response) {
                super.onHandleSuccess(response);
                CLog.w("123", response);
            }
        });
    }
}
